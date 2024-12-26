/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.shop.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.CommonEnum;
import co.yixiang.modules.shop.domain.YxSystemGroupData;
import co.yixiang.modules.shop.service.YxSystemGroupDataService;
import co.yixiang.modules.shop.service.dto.YxSystemGroupDataDto;
import co.yixiang.modules.shop.service.dto.YxSystemGroupDataQueryCriteria;
import co.yixiang.modules.shop.service.mapper.SystemGroupDataMapper;
import co.yixiang.utils.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



/**
* @author hupeng
* @date 2020-05-12
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemGroupDataServiceImpl extends BaseServiceImpl<SystemGroupDataMapper, YxSystemGroupData> implements YxSystemGroupDataService {

    private final IGenerator generator;


    /**
     * 获取配置数据
     * @param name 配置名称
     * @return List
     */
    @Override
    //@Cacheable(value = "yshop:configDatas",key = "#name")
    public List<JSONObject> getDatas(String name) {
        List<YxSystemGroupData> systemGroupDatas = this.baseMapper
                .selectList(Wrappers.<YxSystemGroupData>lambdaQuery()
                        .eq(YxSystemGroupData::getGroupName,name)
                        .eq(YxSystemGroupData::getStatus,CommonEnum.SHOW_STATUS_1.getValue())
                        .orderByDesc(YxSystemGroupData::getSort));

        List<JSONObject> list = systemGroupDatas
                .stream()
                .map(YxSystemGroupData::getValue)
                .map(JSONObject::parseObject)
                .collect(Collectors.toList());

        return list;
    }


    //===============管理后台==============

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxSystemGroupDataQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxSystemGroupData> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        List<YxSystemGroupDataDto> systemGroupDataDTOS = new ArrayList<>();
        for (YxSystemGroupData systemGroupData : page.getList()) {

            YxSystemGroupDataDto systemGroupDataDTO = generator.convert(systemGroupData,YxSystemGroupDataDto.class);
            systemGroupDataDTO.setMap(JSON.parseObject(systemGroupData.getValue()));
            systemGroupDataDTOS.add(systemGroupDataDTO);
        }
        map.put("content",systemGroupDataDTOS);
        map.put("totalElements",page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxSystemGroupData> queryAll(YxSystemGroupDataQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxSystemGroupData.class, criteria));
    }


    @Override
    public void download(List<YxSystemGroupDataDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxSystemGroupDataDto yxSystemGroupData : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("对应的数据名称", yxSystemGroupData.getGroupName());
            map.put("数据组对应的数据值（json数据）", yxSystemGroupData.getValue());
            map.put("添加数据时间", yxSystemGroupData.getAddTime());
            map.put("数据排序", yxSystemGroupData.getSort());
            map.put("状态（1：开启；2：关闭；）", yxSystemGroupData.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
