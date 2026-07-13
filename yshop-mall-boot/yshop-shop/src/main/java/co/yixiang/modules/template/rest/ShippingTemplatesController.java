/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.template.rest;

import co.yixiang.constant.ShopConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.product.domain.YxStoreProduct;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.modules.template.domain.YxSystemCity;
import co.yixiang.modules.template.service.YxShippingTemplatesService;
import co.yixiang.modules.template.service.YxSystemCityService;
import co.yixiang.modules.template.service.dto.ShippingTemplatesDto;
import co.yixiang.modules.template.service.dto.YxShippingTemplatesDto;
import co.yixiang.modules.template.service.dto.YxShippingTemplatesQueryCriteria;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
* @author hupeng
* @date 2020-06-29
*/
@AllArgsConstructor
@Api(tags = "运费模板管理")
@RestController
@RequestMapping("/api/yxShippingTemplates")
public class ShippingTemplatesController {

    private final YxShippingTemplatesService yxShippingTemplatesService;
    private final YxSystemCityService yxSystemCityService;
    private final IGenerator generator;
    private final YxStoreProductService yxStoreProductService;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','yxShippingTemplates:list')")
    public void download(HttpServletResponse response, YxShippingTemplatesQueryCriteria criteria) throws IOException {
        yxShippingTemplatesService.download(generator.convert(yxShippingTemplatesService.queryAll(criteria), YxShippingTemplatesDto.class), response);
    }

    @GetMapping
    @Log("查询运费模板")
    @ApiOperation("查询运费模板")
    @PreAuthorize("@el.check('admin','yxShippingTemplates:list')")
    public ResponseEntity<Object> getYxShippingTemplatess(YxShippingTemplatesQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxShippingTemplatesService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @ForbidSubmit
    @PostMapping("/save/{id}")
    @Log("新增/保存运费模板")
    @ApiOperation("新增/保存运费模板")
    @PreAuthorize("hasAnyRole('admin','yxShippingTemplates:add','yxShippingTemplates:edit')")
    public ResponseEntity<Object> create(@PathVariable Integer id,
                                         @Validated @RequestBody ShippingTemplatesDto shippingTemplatesDto){
        yxShippingTemplatesService.addAndUpdate(id,shippingTemplatesDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @ForbidSubmit
    @Log("删除运费模板")
    @ApiOperation("删除运费模板")
    @PreAuthorize("@el.check('admin','yxShippingTemplates:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        List<YxStoreProduct> productList = yxStoreProductService.list();
        Arrays.asList(ids).forEach(id->{
            for (YxStoreProduct yxStoreProduct : productList) {
                if(id.equals(yxStoreProduct.getTempId())){
                    throw new BadRequestException("运费模板存在商品关联，请删除对应商品");
                }
            }
            yxShippingTemplatesService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * 获取城市列表
     */
    @Cacheable(cacheNames = ShopConstants.YSHOP_REDIS_SYS_CITY_KEY)
    @GetMapping("/citys")
    public ResponseEntity<Object> cityList()
    {
        List<YxSystemCity> cityList = yxSystemCityService.list(Wrappers.<YxSystemCity>lambdaQuery()
                .eq(YxSystemCity::getParentId,0));

        for (YxSystemCity systemCity : cityList){
            List<YxSystemCity> children = yxSystemCityService.list(Wrappers
                    .<YxSystemCity>lambdaQuery()
                    .eq(YxSystemCity::getParentId,systemCity.getCityId()));

            systemCity.setChildren(children);
        }

        return new ResponseEntity<>(cityList,HttpStatus.OK);
    }

}
