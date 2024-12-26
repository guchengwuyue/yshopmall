/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.template.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import co.yixiang.api.BusinessException;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.template.domain.YxShippingTemplates;
import co.yixiang.modules.template.domain.YxShippingTemplatesFree;
import co.yixiang.modules.template.domain.YxShippingTemplatesRegion;
import co.yixiang.modules.template.service.YxShippingTemplatesFreeService;
import co.yixiang.modules.template.service.YxShippingTemplatesRegionService;
import co.yixiang.modules.template.service.YxShippingTemplatesService;
import co.yixiang.modules.template.service.dto.AppointInfoDto;
import co.yixiang.modules.template.service.dto.RegionChildrenDto;
import co.yixiang.modules.template.service.dto.RegionDto;
import co.yixiang.modules.template.service.dto.RegionInfoDto;
import co.yixiang.modules.template.service.dto.ShippingTemplatesDto;
import co.yixiang.modules.template.service.dto.YxShippingTemplatesDto;
import co.yixiang.modules.template.service.dto.YxShippingTemplatesQueryCriteria;
import co.yixiang.modules.template.service.mapper.YxShippingTemplatesMapper;
import co.yixiang.utils.FileUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
* @author hupeng
* @date 2020-06-29
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxShippingTemplatesServiceImpl extends BaseServiceImpl<YxShippingTemplatesMapper, YxShippingTemplates> implements YxShippingTemplatesService {

    private final IGenerator generator;


    private final YxShippingTemplatesRegionService yxShippingTemplatesRegionService;
    private final YxShippingTemplatesFreeService yxShippingTemplatesFreeService;


    /**
     * 新增与更新模板
     * @param id 模板id
     * @param shippingTemplatesDto ShippingTemplatesDto
     */
    @Override
    public void addAndUpdate(Integer id,ShippingTemplatesDto shippingTemplatesDto) {
        if(ShopCommonEnum.ENABLE_1.getValue().equals(shippingTemplatesDto.getAppoint())
                && shippingTemplatesDto.getAppointInfo().isEmpty()){
            throw new YshopException("请指定包邮地区");
        }
        YxShippingTemplates shippingTemplates = new YxShippingTemplates();
        BeanUtil.copyProperties(shippingTemplatesDto,shippingTemplates);
        shippingTemplates.setRegionInfo(JSON.toJSONString(shippingTemplatesDto.getRegionInfo()));
        shippingTemplates.setAppointInfo(JSON.toJSONString(shippingTemplatesDto.getAppointInfo()));
        if(id != null && id > 0){
            shippingTemplates.setId(id);
            this.updateById(shippingTemplates);
        }else{
            this.save(shippingTemplates);
        }

        this.saveRegion(shippingTemplatesDto,shippingTemplates.getId());
        this.saveFreeReigion(shippingTemplatesDto,shippingTemplates.getId());
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxShippingTemplatesQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxShippingTemplates> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxShippingTemplatesDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxShippingTemplates> queryAll(YxShippingTemplatesQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxShippingTemplates.class, criteria));
    }


    @Override
    public void download(List<YxShippingTemplatesDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxShippingTemplatesDto yxShippingTemplates : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("模板名称", yxShippingTemplates.getName());
            map.put("计费方式", yxShippingTemplates.getType());
            map.put("地域以及费用", yxShippingTemplates.getRegionInfo());
            map.put("指定包邮开关", yxShippingTemplates.getAppoint());
            map.put("指定包邮内容", yxShippingTemplates.getAppointInfo());
            map.put("添加时间", yxShippingTemplates.getCreateTime());
            map.put(" updateTime",  yxShippingTemplates.getUpdateTime());
            map.put(" isDel",  yxShippingTemplates.getIsDel());
            map.put("排序", yxShippingTemplates.getSort());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 保存包邮区域
     * @param yxShippingTemplates ShippingTemplatesDto
     * @param tempId 模板id
     */
    private void saveFreeReigion(ShippingTemplatesDto yxShippingTemplates,Integer tempId){

        if(yxShippingTemplates.getAppointInfo() == null
                || yxShippingTemplates.getAppointInfo().isEmpty()){
            return;
        }

        Long count = yxShippingTemplatesFreeService.count(Wrappers
                .<YxShippingTemplatesFree>lambdaQuery()
                .eq(YxShippingTemplatesFree::getTempId,tempId));
        if(count > 0) {
            yxShippingTemplatesFreeService.remove(Wrappers
                    .<YxShippingTemplatesFree>lambdaQuery()
                    .eq(YxShippingTemplatesFree::getTempId,tempId));
        }

        List<YxShippingTemplatesFree> shippingTemplatesFrees = new ArrayList<>();


        List<AppointInfoDto> appointInfo = yxShippingTemplates.getAppointInfo();
        for (AppointInfoDto appointInfoDto : appointInfo){
            String uni = IdUtil.simpleUUID();

            if(appointInfoDto.getPlace() != null && !appointInfoDto.getPlace().isEmpty()){
                for (RegionDto regionDto : appointInfoDto.getPlace()){
                    if(regionDto.getChildren() != null && !regionDto.getChildren().isEmpty()){
                        for (RegionChildrenDto childrenDto : regionDto.getChildren()){
                            YxShippingTemplatesFree shippingTemplatesFree = YxShippingTemplatesFree.builder()
                                    .tempId(tempId)
                                    .number(new BigDecimal(appointInfoDto.getA_num()))
                                    .price(new BigDecimal(appointInfoDto.getA_price()))
                                    .type(yxShippingTemplates.getType())
                                    .uniqid(uni)
                                    .provinceId(Integer.valueOf(regionDto.getCity_id()))
                                    .cityId(Integer.valueOf(childrenDto.getCity_id()))
                                    .build();
                            shippingTemplatesFrees.add(shippingTemplatesFree);
                        }
                    }
                }
            }
        }


        if(shippingTemplatesFrees.isEmpty()) {
            throw new YshopException("请添加包邮区域");
        }

        yxShippingTemplatesFreeService.saveBatch(shippingTemplatesFrees);


    }

    /**
     * 保存模板设置的区域价格
     * @param yxShippingTemplates ShippingTemplatesDTO
     * @param tempId 运费模板id
     */
    private void saveRegion(ShippingTemplatesDto yxShippingTemplates,Integer tempId){
        Long count = yxShippingTemplatesRegionService.count(Wrappers
                .<YxShippingTemplatesRegion>lambdaQuery()
                .eq(YxShippingTemplatesRegion::getTempId,tempId));
        if(count > 0) {
            yxShippingTemplatesRegionService.remove(Wrappers
                    .<YxShippingTemplatesRegion>lambdaQuery()
                    .eq(YxShippingTemplatesRegion::getTempId,tempId));
        }

        List<YxShippingTemplatesRegion> shippingTemplatesRegions = new ArrayList<>();


        List<RegionInfoDto> regionInfo = yxShippingTemplates.getRegionInfo();


        for (RegionInfoDto regionInfoDto : regionInfo){
            String uni = IdUtil.simpleUUID();
            if(regionInfoDto.getRegion() != null && !regionInfoDto.getRegion().isEmpty()){
                for (RegionDto regionDto : regionInfoDto.getRegion()){
                    if(regionDto.getChildren() != null && !regionDto.getChildren().isEmpty()){
                        for (RegionChildrenDto childrenDtp : regionDto.getChildren()){
                            YxShippingTemplatesRegion shippingTemplatesRegion = YxShippingTemplatesRegion.builder()
                                    .tempId(tempId)
                                    .first(new BigDecimal(regionInfoDto.getFirst()))
                                    .firstPrice(new BigDecimal(regionInfoDto.getPrice()))
                                    .continues(new BigDecimal(regionInfoDto.get_continue()))
                                    .continuePrice(new BigDecimal(regionInfoDto.getContinue_price()))
                                    .type(yxShippingTemplates.getType())
                                    .uniqid(uni)
                                    .provinceId(Integer.valueOf(regionDto.getCity_id()))
                                    .cityId(Integer.valueOf(childrenDtp.getCity_id()))
                                    .build();
                            shippingTemplatesRegions.add(shippingTemplatesRegion);
                        }
                    }else{
                        YxShippingTemplatesRegion shippingTemplatesRegion = YxShippingTemplatesRegion.builder()
                                .tempId(tempId)
                                .first(new BigDecimal(regionInfoDto.getFirst()))
                                .firstPrice(new BigDecimal(regionInfoDto.getPrice()))
                                .continues(new BigDecimal(regionInfoDto.get_continue()))
                                .continuePrice(new BigDecimal(regionInfoDto.getContinue_price()))
                                .type(yxShippingTemplates.getType())
                                .uniqid(uni)
                                .provinceId(Integer.valueOf(regionDto.getCity_id()))
                                .build();
                        shippingTemplatesRegions.add(shippingTemplatesRegion);
                    }
                }
            }
        }

        if(shippingTemplatesRegions.isEmpty()) {
            throw new BusinessException("请添加区域");
        }

        yxShippingTemplatesRegionService.saveBatch(shippingTemplatesRegions);

    }


}
