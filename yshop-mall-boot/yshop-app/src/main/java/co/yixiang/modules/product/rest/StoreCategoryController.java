/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.product.rest;

import co.yixiang.annotation.AnonymousAccess;
import co.yixiang.api.ApiResult;
import co.yixiang.modules.category.service.YxStoreCategoryService;
import co.yixiang.utils.CateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品分类前端控制器
 * </p>
 *
 * @author hupeng
 * @since 2019-10-22
 */
@Slf4j
@RestController
@Api(value = "商品分类", tags = "商城:商品分类")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreCategoryController {

    private final YxStoreCategoryService yxStoreCategoryService;


    /**
     * 商品分类列表
     */
    @AnonymousAccess
    @GetMapping("/category")
    @ApiOperation(value = "商品分类列表",notes = "商品分类列表")
    public ApiResult<List<CateDTO>> getYxStoreCategoryPageList(){
        return ApiResult.ok(yxStoreCategoryService.getList());
    }

}

