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
import co.yixiang.modules.activity.domain.YxStoreSeckill;
import co.yixiang.modules.activity.service.YxStoreSeckillService;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillDto;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillQueryCriteria;
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
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hupeng
 * @date 2019-12-14
 */
@Api(tags = "商城:秒杀管理")
@RestController
@RequestMapping("api")
public class StoreSeckillController {

    private final IGenerator generator;
    private final YxStoreSeckillService yxStoreSeckillService;
    private final YxShippingTemplatesService yxShippingTemplatesService;
    private final YxStoreProductRuleService yxStoreProductRuleService;
    private final YxStoreProductAttrValueService storeProductAttrValueService;
    private final YxStoreProductAttrResultService yxStoreProductAttrResultService;

    public StoreSeckillController(IGenerator generator, YxStoreSeckillService yxStoreSeckillService, YxShippingTemplatesService yxShippingTemplatesService,
                                  YxStoreProductRuleService yxStoreProductRuleService, YxStoreProductAttrValueService storeProductAttrValueService,
                                  YxStoreProductAttrResultService yxStoreProductAttrResultService) {
        this.generator = generator;
        this.yxStoreSeckillService = yxStoreSeckillService;
        this.yxShippingTemplatesService = yxShippingTemplatesService;
        this.yxStoreProductRuleService = yxStoreProductRuleService;
        this.storeProductAttrValueService = storeProductAttrValueService;
        this.yxStoreProductAttrResultService = yxStoreProductAttrResultService;
    }

    @Log("列表")
    @ApiOperation(value = "列表")
    @GetMapping(value = "/yxStoreSeckill")
    @PreAuthorize("hasAnyRole('admin','YXSTORESECKILL_ALL','YXSTORESECKILL_SELECT')")
    public ResponseEntity getYxStoreSeckills(YxStoreSeckillQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(yxStoreSeckillService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @ForbidSubmit
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY, allEntries = true)
    @Log("发布")
    @ApiOperation(value = "发布")
    @PutMapping(value = "/yxStoreSeckill")
    @PreAuthorize("hasAnyRole('admin','YXSTORESECKILL_ALL','YXSTORESECKILL_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreSeckill resources){
        if(ObjectUtil.isNull(resources.getId())){
            return new ResponseEntity<>(yxStoreSeckillService.save(resources),HttpStatus.CREATED);
        }else{
            yxStoreSeckillService.saveOrUpdate(resources);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY, allEntries = true)
    @ForbidSubmit
    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreSeckill/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSTORESECKILL_ALL','YXSTORESECKILL_DELETE')")
    public ResponseEntity delete(@PathVariable Long id) {
        yxStoreSeckillService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY, allEntries = true)
    @Log("新增秒杀")
    @ApiOperation(value = "新增秒杀")
    @PostMapping(value = "/yxStoreSeckill")
    @PreAuthorize("hasAnyRole('admin','YXSTORESECKILL_ALL','YXSTORESECKILL_EDIT')")
    public ResponseEntity add(@Validated @RequestBody YxStoreSeckillDto resources) {
        return new ResponseEntity<>(yxStoreSeckillService.saveSeckill(resources), HttpStatus.CREATED);
    }

    @ApiOperation(value = "获取商品信息")
    @GetMapping(value = "/yxStoreSecKill/info/{id}")
    public ResponseEntity info(@PathVariable Long id) {
        Map<String, Object> map = new LinkedHashMap<>(3);

        //运费模板
        List<YxShippingTemplates> shippingTemplatesList = yxShippingTemplatesService.list();
        map.put("tempList", shippingTemplatesList);

        //商品规格
        map.put("ruleList", yxStoreProductRuleService.list());


        if (id == 0) {
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        //处理商品详情
        YxStoreSeckill yxStoreSeckill = yxStoreSeckillService.getById(id);
        YxStoreSeckillDto productDto = new YxStoreSeckillDto();
        BeanUtil.copyProperties(yxStoreSeckill, productDto, "images");
        productDto.setSliderImage(Arrays.asList(yxStoreSeckill.getImages().split(",")));
        YxStoreProductAttrResult storeProductAttrResult = yxStoreProductAttrResultService
                .getOne(Wrappers.<YxStoreProductAttrResult>lambdaQuery()
                        .eq(YxStoreProductAttrResult::getProductId, yxStoreSeckill.getProductId()).last("limit 1"));
        JSONObject result = JSON.parseObject(storeProductAttrResult.getResult());

        List<YxStoreProductAttrValue> attrValues = storeProductAttrValueService.list(new LambdaQueryWrapper<YxStoreProductAttrValue>().eq(YxStoreProductAttrValue::getProductId, yxStoreSeckill.getProductId()));
        List<ProductFormatDto> productFormatDtos = attrValues.stream().map(i -> {
            ProductFormatDto productFormatDto = new ProductFormatDto();
            BeanUtils.copyProperties(i, productFormatDto);
            productFormatDto.setPic(i.getImage());
            return productFormatDto;
        }).collect(Collectors.toList());
        if (SpecTypeEnum.TYPE_1.getValue().equals(yxStoreSeckill.getSpecType())) {
            productDto.setAttr(new ProductFormatDto());
            productDto.setAttrs(productFormatDtos);
            productDto.setItems(result.getObject("attr", ArrayList.class));
        } else {
            productFormat(productDto, result);
        }


        map.put("productInfo", productDto);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 获取商品属性
     * @param productDto
     * @param result
     */
    private void productFormat(YxStoreSeckillDto productDto, JSONObject result) {
        Map<String, Object> mapAttr = (Map<String, Object>) result.getObject("value", ArrayList.class).get(0);
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
}
