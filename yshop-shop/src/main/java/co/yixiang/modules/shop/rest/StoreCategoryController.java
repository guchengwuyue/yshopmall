package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxStoreCategory;
import co.yixiang.modules.shop.service.YxStoreCategoryService;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryDTO;
import co.yixiang.modules.shop.service.dto.YxStoreCategoryQueryCriteria;
import co.yixiang.utils.OrderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    public StoreCategoryController(YxStoreCategoryService yxStoreCategoryService) {
        this.yxStoreCategoryService = yxStoreCategoryService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/yxStoreCategory/download")
    @PreAuthorize("@el.check('admin','cate:list')")
    public void download(HttpServletResponse response, YxStoreCategoryQueryCriteria criteria) throws IOException {
        yxStoreCategoryService.download(yxStoreCategoryService.queryAll(criteria), response);
    }


    @Log("查询商品分类")
    @ApiOperation(value = "查询商品分类")
    @GetMapping(value = "/yxStoreCategory")
    @PreAuthorize("@el.check('admin','YXSTORECATEGORY_ALL','YXSTORECATEGORY_SELECT')")
    public ResponseEntity getYxStoreCategorys(YxStoreCategoryQueryCriteria criteria, Pageable pageable){

        List<YxStoreCategoryDTO> categoryDTOList = yxStoreCategoryService.queryAll(criteria);
        return new ResponseEntity(yxStoreCategoryService.buildTree(categoryDTOList),HttpStatus.OK);
    }

    @Log("新增商品分类")
    @ApiOperation(value = "新增商品分类")
    @PostMapping(value = "/yxStoreCategory")
    @PreAuthorize("@el.check('admin','YXSTORECATEGORY_ALL','YXSTORECATEGORY_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreCategory resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        if(resources.getPid() > 0 && StrUtil.isBlank(resources.getPic())) {
            throw new BadRequestException("子分类图片必传");
        }

        resources.setAddTime(OrderUtil.getSecondTimestampTwo());
        return new ResponseEntity(yxStoreCategoryService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改商品分类")
    @ApiOperation(value = "修改商品分类")
    @PutMapping(value = "/yxStoreCategory")
    @PreAuthorize("@el.check('admin','YXSTORECATEGORY_ALL','YXSTORECATEGORY_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCategory resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        if(resources.getPid() > 0 && StrUtil.isBlank(resources.getPic())) {
            throw new BadRequestException("子分类图片必传");
        }
        yxStoreCategoryService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除商品分类")
    @ApiOperation(value = "删除商品分类")
    @DeleteMapping(value = "/yxStoreCategory/{id}")
    @PreAuthorize("@el.check('admin','YXSTORECATEGORY_ALL','YXSTORECATEGORY_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        String[] ids = id.split(",");
        for (String newId: ids) {
            yxStoreCategoryService.delete(Integer.valueOf(newId));
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}