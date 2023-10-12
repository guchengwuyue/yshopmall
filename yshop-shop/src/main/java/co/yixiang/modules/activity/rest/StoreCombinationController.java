/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.activity.rest;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import co.yixiang.constant.ShopConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.SpecTypeEnum;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreCombination;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationDto;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationQueryCriteria;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.product.domain.YxStoreProductAttrResult;
import co.yixiang.modules.product.domain.YxStoreProductAttrValue;
import co.yixiang.modules.product.service.YxStoreProductAttrResultService;
import co.yixiang.modules.product.service.YxStoreProductAttrValueService;
import co.yixiang.modules.product.service.YxStoreProductRuleService;
import co.yixiang.modules.product.service.dto.ProductFormatDto;
import co.yixiang.modules.template.domain.YxShippingTemplates;
import co.yixiang.modules.template.service.YxShippingTemplatesService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author hupeng
* @date 2019-11-18
*/
@Api(tags = "商城:拼团管理")
@RestController
@RequestMapping("api")
public class StoreCombinationController {

    private final YxStoreCombinationService yxStoreCombinationService;
    private final YxShippingTemplatesService yxShippingTemplatesService;
    private final YxStoreProductRuleService yxStoreProductRuleService;
    private final YxStoreProductAttrResultService yxStoreProductAttrResultService;
    private final  YxStoreProductAttrValueService storeProductAttrValueService;
    private final IGenerator generator;
    public StoreCombinationController(YxStoreCombinationService yxStoreCombinationService, YxShippingTemplatesService yxShippingTemplatesService, YxStoreProductRuleService yxStoreProductRuleService, YxStoreProductAttrResultService yxStoreProductAttrResultService, YxStoreProductAttrValueService storeProductAttrValueService, IGenerator generator) {
        this.yxStoreCombinationService = yxStoreCombinationService;
        this.yxShippingTemplatesService = yxShippingTemplatesService;
        this.yxStoreProductRuleService = yxStoreProductRuleService;
        this.yxStoreProductAttrResultService = yxStoreProductAttrResultService;
        this.storeProductAttrValueService = storeProductAttrValueService;
        this.generator = generator;
    }

    @Log("查询拼团")
    @ApiOperation(value = "查询拼团")
    @GetMapping(value = "/yxStoreCombination")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOMBINATION_ALL','YXSTORECOMBINATION_SELECT')")
    public ResponseEntity getYxStoreCombinations(YxStoreCombinationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxStoreCombinationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @Log("新增拼团")
    @ApiOperation(value = "新增拼团")
    @PostMapping(value = "/yxStoreCombination")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOMBINATION_ALL','YXSTORECOMBINATION_EDIT')")
    public ResponseEntity add(@Validated @RequestBody YxStoreCombinationDto resources){
            return new ResponseEntity<>(yxStoreCombinationService.saveCombination(resources),HttpStatus.CREATED);
    }

    @ApiOperation(value = "获取商品信息")
    @GetMapping(value = "/yxStoreCombination/info/{id}")
    public ResponseEntity info(@PathVariable Long id){
        Map<String,Object> map = new LinkedHashMap<>(3);

        //运费模板
        List<YxShippingTemplates> shippingTemplatesList = yxShippingTemplatesService.list();
        map.put("tempList", shippingTemplatesList);

        //商品规格
        map.put("ruleList",yxStoreProductRuleService.list());


        if(id == 0){
            return new ResponseEntity<>(map,HttpStatus.OK);
        }

        //处理商品详情
        YxStoreCombination yxStoreCombination = yxStoreCombinationService.getById(id);
        YxStoreCombinationDto productDto = new YxStoreCombinationDto();
        BeanUtil.copyProperties(yxStoreCombination,productDto,"images");
        productDto.setSliderImage(Arrays.asList(yxStoreCombination.getImages().split(",")));
        YxStoreProductAttrResult storeProductAttrResult = yxStoreProductAttrResultService
                .getOne(Wrappers.<YxStoreProductAttrResult>lambdaQuery()
                        .eq(YxStoreProductAttrResult::getProductId,yxStoreCombination.getProductId()).last("limit 1"));
        JSONObject result = JSON.parseObject(storeProductAttrResult.getResult());
        List<YxStoreProductAttrValue> attrValues = storeProductAttrValueService.list(new LambdaQueryWrapper<YxStoreProductAttrValue>().eq(YxStoreProductAttrValue::getProductId, yxStoreCombination.getProductId()));
        List<ProductFormatDto> productFormatDtos =attrValues.stream().map(i ->{
            ProductFormatDto productFormatDto = new ProductFormatDto();
            BeanUtils.copyProperties(i,productFormatDto);
            productFormatDto.setPic(i.getImage());
            return productFormatDto;
        }).collect(Collectors.toList());
        if(SpecTypeEnum.TYPE_1.getValue().equals(yxStoreCombination.getSpecType())){
            productDto.setAttr(new ProductFormatDto());
            productDto.setAttrs(productFormatDtos);
            productDto.setItems(result.getObject("attr",ArrayList.class));
        }else{
            productFormat(productDto, result);
        }

        map.put("productInfo",productDto);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    /**
     * 获取商品属性
     * @param productDto
     * @param result
     */
    private void productFormat(YxStoreCombinationDto productDto, JSONObject result) {
        Map<String,Object> mapAttr = (Map<String,Object>) result.getObject("value",ArrayList.class).get(0);
        ProductFormatDto productFormatDto = ProductFormatDto.builder()
                .pic(mapAttr.get("pic").toString())
                .price(Double.valueOf(mapAttr.get("price").toString()))
                .cost(Double.valueOf(mapAttr.get("cost").toString()))
                .otPrice(Double.valueOf(mapAttr.get("otPrice").toString()))
                .stock(Integer.valueOf(mapAttr.get("stock").toString()))
                .barCode(mapAttr.get("barCode").toString())
                .weight(Double.valueOf(mapAttr.get("weight").toString()))
                .volume(Double.valueOf(mapAttr.get("volume").toString()))
                .value1(mapAttr.get("value1").toString())
                .brokerage(Double.valueOf(mapAttr.get("brokerage").toString()))
                .brokerageTwo(Double.valueOf(mapAttr.get("brokerageTwo").toString()))
                .pinkPrice(Double.valueOf(mapAttr.get("pinkPrice").toString()))
                .pinkStock(Integer.valueOf(mapAttr.get("pinkStock").toString()))
                .seckillPrice(Double.valueOf(mapAttr.get("seckillPrice").toString()))
                .seckillStock(Integer.valueOf(mapAttr.get("seckillStock").toString()))
                .build();
        productDto.setAttr(productFormatDto);
    }

    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @Log("修改拼团")
    @ApiOperation(value = "新增/修改拼团")
    @PutMapping(value = "/yxStoreCombination")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOMBINATION_ALL','YXSTORECOMBINATION_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCombination resources){
        if(ObjectUtil.isNull(resources.getId())){
            return new ResponseEntity<>(yxStoreCombinationService.save(resources),HttpStatus.CREATED);
        }else{
            yxStoreCombinationService.saveOrUpdate(resources);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @ForbidSubmit
    @ApiOperation(value = "开启关闭")
    @PostMapping(value = "/yxStoreCombination/onsale/{id}")
    public ResponseEntity onSale(@PathVariable Long id,@RequestBody String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Integer status = jsonObject.getInteger("status");
        yxStoreCombinationService.onSale(id,status);
        return new ResponseEntity(HttpStatus.OK);
    }
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @ForbidSubmit
    @Log("删除拼团")
    @ApiOperation(value = "删除拼团")
    @DeleteMapping(value = "/yxStoreCombination/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSTORECOMBINATION_ALL','YXSTORECOMBINATION_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        yxStoreCombinationService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
