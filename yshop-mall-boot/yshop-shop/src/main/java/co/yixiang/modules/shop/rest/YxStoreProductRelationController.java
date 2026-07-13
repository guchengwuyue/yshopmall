/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.shop.rest;
import java.util.Arrays;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.aop.ForbidSubmit;
import lombok.AllArgsConstructor;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.product.domain.YxStoreProductRelation;
import co.yixiang.modules.product.service.YxStoreProductRelationService;
import co.yixiang.modules.product.service.dto.YxStoreProductRelationQueryCriteria;
import co.yixiang.modules.product.service.dto.YxStoreProductRelationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import co.yixiang.domain.PageResult;
/**
 * @author hupeng
 * @date 2020-09-03
 */
@AllArgsConstructor
@Api(tags = "ProductRelation管理")
@RestController
@RequestMapping("/api/yxStoreProductRelation")
public class YxStoreProductRelationController {

    private final YxStoreProductRelationService yxStoreProductRelationService;
    private final IGenerator generator;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','yxStoreProductRelation:list')")
    public void download(HttpServletResponse response, YxStoreProductRelationQueryCriteria criteria) throws IOException {
        yxStoreProductRelationService.download(generator.convert(yxStoreProductRelationService.queryAll(criteria), YxStoreProductRelationDto.class), response);
    }

    @GetMapping
    @Log("查询ProductRelation")
    @ApiOperation("查询ProductRelation")
    @PreAuthorize("@el.check('admin','yxStoreProductRelation:list')")
    public ResponseEntity<PageResult<YxStoreProductRelationDto>> getYxStoreProductRelations(YxStoreProductRelationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxStoreProductRelationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增ProductRelation")
    @ApiOperation("新增ProductRelation")
    @PreAuthorize("@el.check('admin','yxStoreProductRelation:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxStoreProductRelation resources){
        return new ResponseEntity<>(yxStoreProductRelationService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改ProductRelation")
    @ApiOperation("修改ProductRelation")
    @PreAuthorize("@el.check('admin','yxStoreProductRelation:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody YxStoreProductRelation resources){
        yxStoreProductRelationService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除ProductRelation")
    @ApiOperation("删除ProductRelation")
    @PreAuthorize("@el.check('admin','yxStoreProductRelation:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        Arrays.asList(ids).forEach(id->{
            yxStoreProductRelationService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
