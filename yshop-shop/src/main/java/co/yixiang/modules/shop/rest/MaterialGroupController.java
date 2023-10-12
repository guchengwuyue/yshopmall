/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.shop.rest;

import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.shop.domain.YxMaterialGroup;
import co.yixiang.modules.shop.service.YxMaterialGroupService;
import co.yixiang.modules.shop.service.dto.YxMaterialGroupQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author hupeng
* @date 2020-01-09
*/
@Api(tags = "商城:素材分组管理")
@RestController
@RequestMapping("/api/materialgroup")
public class MaterialGroupController {

    private final YxMaterialGroupService yxMaterialGroupService;

    public MaterialGroupController(YxMaterialGroupService yxMaterialGroupService) {
        this.yxMaterialGroupService = yxMaterialGroupService;
    }



    @GetMapping(value = "/page")
    @Log("查询素材分组")
    @ApiOperation("查询素材分组")
    public ResponseEntity<Object> getYxMaterialGroups(YxMaterialGroupQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxMaterialGroupService.queryAll(criteria,pageable),HttpStatus.OK);
    }


    @GetMapping(value = "/list")
    @Log("查询所有素材分组")
    @ApiOperation("查询所有素材分组")
    public ResponseEntity<Object> getYxMaterialGroupsList(YxMaterialGroupQueryCriteria criteria){
        return new ResponseEntity<>(yxMaterialGroupService.queryAll(criteria),HttpStatus.OK);
    }


    @PostMapping
    @Log("新增素材分组")
    @ApiOperation("新增素材分组")
    public ResponseEntity<Object> create(@Validated @RequestBody YxMaterialGroup resources){
        return new ResponseEntity<>(yxMaterialGroupService.save(resources),HttpStatus.CREATED);
    }

    @ForbidSubmit
    @PutMapping
    @Log("修改素材分组")
    @ApiOperation("修改素材分组")
    public ResponseEntity<Object> update(@Validated @RequestBody YxMaterialGroup resources){
        yxMaterialGroupService.saveOrUpdate(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除素材分组")
    @ApiOperation("删除素材分组")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAll(@PathVariable String id) {
        yxMaterialGroupService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
