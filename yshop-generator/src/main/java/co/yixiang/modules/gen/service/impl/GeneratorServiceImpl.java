/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.gen.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ZipUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.gen.domain.ColumnConfig;
import co.yixiang.modules.gen.domain.GenConfig;
import co.yixiang.modules.gen.domain.vo.TableInfo;
import co.yixiang.modules.gen.service.GeneratorService;
import co.yixiang.modules.gen.service.mapper.ColumnInfoMapper;
import co.yixiang.modules.gen.utils.GenUtil;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Zheng Jie
 * @date 2019-01-02
 */
@Service
@SuppressWarnings({"unchecked", "all"})
public class GeneratorServiceImpl extends BaseServiceImpl<ColumnInfoMapper, ColumnConfig> implements GeneratorService {


    @Override
    public Object getTables() {
        return baseMapper.selectTables();
    }

    @Override
    public Object getTables(String name, Integer page, Integer size) {
        IPage<TableInfo> pages = null;
        if (page >= 0) page = page + 1;
        Page<TableInfo> pageModel = new Page<>(page, size);
        pages = baseMapper.selectTablePage(pageModel, name);
        Integer totalElements = 0;
        return PageUtil.toPage(pages.getRecords(), pages.getTotal());
    }

    @Override
    public List<ColumnConfig> getColumns(String tableName) {
        List<ColumnConfig> columnInfos = this.list(new LambdaQueryWrapper<ColumnConfig>()
                .eq(ColumnConfig::getTableName, tableName).orderByAsc(ColumnConfig::getId));
        if (CollectionUtil.isNotEmpty(columnInfos)) {
            return columnInfos;
        } else {
            columnInfos = query(tableName);
            this.saveBatch(columnInfos);
            return columnInfos;
        }
    }

    @Override
    public List<ColumnConfig> query(String tableName) {
        List<ColumnConfig> columnInfos = new ArrayList<>();
        List<Map<String, Object>> result = baseMapper.queryByTableName(tableName);
        for (Map<String, Object> map : result) {

            columnInfos.add(
                    new ColumnConfig(
                            tableName,
                            map.get("COLUMN_NAME").toString(),
                            "NO".equals(map.get("IS_NULLABLE").toString()),
                            map.get("DATA_TYPE").toString(),
                            ObjectUtil.isNotNull(map.get("COLUMN_COMMENT")) ? map.get("COLUMN_COMMENT").toString() : null,
                            ObjectUtil.isNotNull(map.get("COLUMN_KEY")) ? map.get("COLUMN_KEY").toString() : null,
                            ObjectUtil.isNotNull(map.get("EXTRA")) ? map.get("EXTRA").toString() : null)
            );
        }
        return columnInfos;
    }

    @Override
    public void sync(List<ColumnConfig> columnInfos, List<ColumnConfig> columnInfoList) {
        // 第一种情况，数据库类字段改变或者新增字段
        for (ColumnConfig columnInfo : columnInfoList) {
            // 根据字段名称查找
            List<ColumnConfig> columns = new ArrayList<ColumnConfig>(columnInfos.stream().filter(c -> c.getColumnName().equals(columnInfo.getColumnName())).collect(Collectors.toList()));
            // 如果能找到，就修改部分可能被字段
            if (CollectionUtil.isNotEmpty(columns)) {
                ColumnConfig column = columns.get(0);
                column.setColumnType(columnInfo.getColumnType());
                column.setExtra(columnInfo.getExtra());
                column.setKeyType(columnInfo.getKeyType());
                if (StringUtils.isBlank(column.getRemark())) {
                    column.setRemark(columnInfo.getRemark());
                }
                this.saveOrUpdate(column);
            } else {
                // 如果找不到，则保存新字段信息
                this.save(columnInfo);
            }
        }
        // 第二种情况，数据库字段删除了
        for (ColumnConfig columnInfo : columnInfos) {
            // 根据字段名称查找
            List<ColumnConfig> columns = new ArrayList<ColumnConfig>(columnInfoList.stream().filter(c -> c.getColumnName().equals(columnInfo.getColumnName())).collect(Collectors.toList()));
            // 如果找不到，就代表字段被删除了，则需要删除该字段
            if (CollectionUtil.isEmpty(columns)) {
                this.removeById(columnInfo.getId());
            }
        }
    }

    @Override
    public void save(List<ColumnConfig> columnInfos) {
        this.saveOrUpdateBatch(columnInfos);
    }

    @Override
    public void generator(GenConfig genConfig, List<ColumnConfig> columns) {
        if (genConfig.getId() == null) {
            throw new BadRequestException("请先配置生成器");
        }
        try {
            GenUtil.generatorCode(columns, genConfig);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequestException("生成失败，请手动处理已生成的文件");
        }
    }

    @Override
    public ResponseEntity<Object> preview(GenConfig genConfig, List<ColumnConfig> columns) {
        if (genConfig.getId() == null) {
            throw new BadRequestException("请先配置生成器");
        }
        List<Map<String, Object>> genList = GenUtil.preview(columns, genConfig);
        return new ResponseEntity<>(genList, HttpStatus.OK);
    }

    @Override
    public void download(GenConfig genConfig, List<ColumnConfig> columns, HttpServletRequest request, HttpServletResponse response) {
        if (genConfig.getId() == null) {
            throw new BadRequestException("请先配置生成器");
        }
        try {
            File file = new File(GenUtil.download(columns, genConfig));
            String zipPath = file.getPath() + ".zip";
            ZipUtil.zip(file.getPath(), zipPath);
            FileUtil.downloadFile(request, response, new File(zipPath), true);
        } catch (IOException e) {
            throw new BadRequestException("打包失败");
        }
    }
}
