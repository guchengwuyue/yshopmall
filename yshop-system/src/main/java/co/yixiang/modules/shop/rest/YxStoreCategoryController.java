package co.yixiang.modules.shop.rest;

import co.yixiang.aop.log.Log;
import co.yixiang.modules.shop.domain.YxStoreCategory;
import co.yixiang.modules.shop.service.YxStoreCategoryService;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryDTO;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

/**
* @author hupeng
* @date 2019-10-03
*/
@Api(tags = "商品分类管理")
@RestController
@RequestMapping("api")
public class YxStoreCategoryController {

    @Autowired
    private YxStoreCategoryService yxStoreCategoryService;

    @Log("查询商品分类")
    @ApiOperation(value = "查询商品分类")
    @GetMapping(value = "/yxStoreCategory")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECATEGORY_ALL','YXSTORECATEGORY_SELECT')")
    public ResponseEntity getYxStoreCategorys(YxStoreCategoryQueryCriteria criteria, Pageable pageable){
        List<YxStoreCategoryDTO> categoryDTOList = yxStoreCategoryService.queryAll(criteria);
        return new ResponseEntity(yxStoreCategoryService.buildTree(categoryDTOList),HttpStatus.OK);
    }

    @Log("新增商品分类")
    @ApiOperation(value = "新增商品分类")
    @PostMapping(value = "/yxStoreCategory")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECATEGORY_ALL','YXSTORECATEGORY_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCategory resources){
        return new ResponseEntity(yxStoreCategoryService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改商品分类")
    @ApiOperation(value = "修改商品分类")
    @PutMapping(value = "/yxStoreCategory")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECATEGORY_ALL','YXSTORECATEGORY_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCategory resources){
        yxStoreCategoryService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除商品分类")
    @ApiOperation(value = "删除商品分类")
    @DeleteMapping(value = "/yxStoreCategory/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTORECATEGORY_ALL','YXSTORECATEGORY_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxStoreCategoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}