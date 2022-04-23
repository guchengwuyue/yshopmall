/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.service.impl;

import cn.hutool.core.util.ObjectUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.tools.service.LocalStorageService;
import co.yixiang.modules.tools.service.mapper.LocalStorageMapper;
import co.yixiang.modules.tools.domain.LocalStorage;
import co.yixiang.modules.tools.service.dto.LocalStorageDto;
import co.yixiang.modules.tools.service.dto.LocalStorageQueryCriteria;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.SecurityUtils;
import co.yixiang.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Service
//@CacheConfig(cacheNames = "localStorage")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LocalStorageServiceImpl extends BaseServiceImpl<LocalStorageMapper, LocalStorage> implements LocalStorageService {

    private final IGenerator generator;
    @Value("${file.path}")
    private String path;

    @Value("${file.maxSize}")
    private long maxSize;

    public LocalStorageServiceImpl(IGenerator generator) {
        this.generator = generator;
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(LocalStorageQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<LocalStorage> page = new PageInfo<>(baseMapper.selectList(QueryHelpPlus.getPredicate(LocalStorage.class, criteria)));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), LocalStorageDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<LocalStorageDto> queryAll(LocalStorageQueryCriteria criteria) {
        return generator.convert(baseMapper.selectList(QueryHelpPlus.getPredicate(LocalStorage.class, criteria)), LocalStorageDto.class);
    }

    @Override
    public LocalStorageDto findById(Long id) {
        LocalStorage localStorage = this.getById(id);
        return generator.convert(localStorage, LocalStorageDto.class);
    }

    @Override
    public LocalStorageDto create(String name, MultipartFile multipartFile) {
        FileUtil.checkSize(maxSize, multipartFile.getSize());
        String suffix = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
        String type = FileUtil.getFileType(suffix);
        File file = FileUtil.upload(multipartFile, path + type + File.separator);
        if (ObjectUtil.isNull(file)) {
            throw new BadRequestException("上传失败");
        }
        try {
            name = StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
            LocalStorage localStorage = new LocalStorage(
                    file.getName(),
                    name,
                    suffix,
                    file.getPath(),
                    type,
                    FileUtil.getSize(multipartFile.getSize()),
                    SecurityUtils.getUsername()
            );
            this.save(localStorage);
            return generator.convert(localStorage, LocalStorageDto.class);
        } catch (Exception e) {
            FileUtil.del(file);
            throw e;
        }
    }


    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            LocalStorage storage = this.getById(id);
            FileUtil.del(storage.getPath());
            this.removeById(id);
        }
    }


    @Override
    public void download(List<LocalStorageDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LocalStorageDto localStorage : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("文件真实的名称", localStorage.getRealName());
            map.put("文件名", localStorage.getName());
            map.put("后缀", localStorage.getSuffix());
//            map.put("路径", localStorage.getPath());
            map.put("类型", localStorage.getType());
            map.put("大小", localStorage.getSize());
            map.put("操作人", localStorage.getOperate());
            map.put("创建日期", localStorage.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void updateLocalStorage(LocalStorageDto resources) {
        LocalStorage localStorage = this.getById(resources.getId());
        BeanUtils.copyProperties(resources, localStorage);
        this.saveOrUpdate(localStorage);
    }
}
