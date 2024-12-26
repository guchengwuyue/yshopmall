/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.category.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.api.YshopException;
import co.yixiang.constant.ShopConstants;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.category.domain.YxStoreCategory;
import co.yixiang.modules.category.service.YxStoreCategoryService;
import co.yixiang.modules.category.service.dto.YxStoreCategoryDto;
import co.yixiang.modules.category.service.dto.YxStoreCategoryQueryCriteria;
import co.yixiang.modules.product.domain.YxStoreProduct;
import co.yixiang.modules.product.service.YxStoreProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
* @author hupeng
* @date 2019-10-03
*/
@Api(tags = "商城:商品分类管理")
@RestController
@RequestMapping("api")
public class StoreCategoryController {


    private final YxStoreCategoryService yxStoreCategoryService;
    private final YxStoreProductService yxStoreProductService;


    public StoreCategoryController(YxStoreCategoryService yxStoreCategoryService,
                                   YxStoreProductService yxStoreProductService) {
        this.yxStoreCategoryService = yxStoreCategoryService;
        this.yxStoreProductService = yxStoreProductService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/yxStoreCategory/download")
    @PreAuthorize("@el.check('admin','YXSTORECATEGORY_SELECT')")
    public void download(HttpServletResponse response, YxStoreCategoryQueryCriteria criteria) throws IOException {
        yxStoreCategoryService.download(yxStoreCategoryService.queryAll(criteria), response);
    }


    @Log("查询商品分类")
    @ApiOperation(value = "查询商品分类")
    @GetMapping(value = "/yxStoreCategory")
    @PreAuthorize("hasAnyRole('admin','YXSTORECATEGORY_ALL','YXSTORECATEGORY_SELECT')")
    public ResponseEntity getYxStoreCategorys(YxStoreCategoryQueryCriteria criteria, Pageable pageable){
        List<YxStoreCategoryDto> categoryDTOList = yxStoreCategoryService.queryAll(criteria);
        return new ResponseEntity<>(yxStoreCategoryService.buildTree(categoryDTOList),HttpStatus.OK);
    }

    @ForbidSubmit
    @Log("新增商品分类")
    @ApiOperation(value = "新增商品分类")
    @PostMapping(value = "/yxStoreCategory")
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @PreAuthorize("hasAnyRole('admin','YXSTORECATEGORY_ALL','YXSTORECATEGORY_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCategory resources){
        if(resources.getPid() != null && resources.getPid() > 0 && StrUtil.isBlank(resources.getPic())) {
            throw new YshopException("子分类图片必传");
        }

        boolean checkResult = yxStoreCategoryService.checkCategory(resources.getPid());
        if(!checkResult) {
            throw new YshopException("分类最多能添加2级哦");
        }

        return new ResponseEntity<>(yxStoreCategoryService.save(resources),HttpStatus.CREATED);
    }

    @ForbidSubmit
    @Log("修改商品分类")
    @ApiOperation(value = "修改商品分类")
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @PutMapping(value = "/yxStoreCategory")
    @PreAuthorize("hasAnyRole('admin','YXSTORECATEGORY_ALL','YXSTORECATEGORY_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCategory resources){
        if(resources.getPid() != null && resources.getPid() > 0 && StrUtil.isBlank(resources.getPic())) {
            throw new YshopException("子分类图片必传");
        }

        if(resources.getId().equals(resources.getPid())){
            throw new YshopException("自己不能选择自己哦");
        }

        boolean checkResult = yxStoreCategoryService.checkCategory(resources.getPid());

        if(!checkResult) {
            throw new YshopException("分类最多能添加2级哦");
        }

        yxStoreCategoryService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除商品分类")
    @ApiOperation(value = "删除商品分类")
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY,allEntries = true)
    @DeleteMapping(value = "/yxStoreCategory/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSTORECATEGORY_ALL','YXSTORECATEGORY_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        String[] ids = id.split(",");
        for (String newId: ids) {
            this.delCheck(Integer.valueOf(newId));
            yxStoreCategoryService.removeById(Integer.valueOf(newId));
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * 检测删除分类
     * @param id 分类id
     */
    private void delCheck(Integer id){
        Long count = yxStoreCategoryService.lambdaQuery()
                .eq(YxStoreCategory::getPid,id)
                .count();
        if(count > 0) {
            throw new YshopException("请先删除子分类");
        }

        Long countP = yxStoreProductService.lambdaQuery()
                .eq(YxStoreProduct::getCateId,id)
                .count();

        if(countP > 0) {
            throw new YshopException("当前分类下有商品不可删除");
        }
    }
}
