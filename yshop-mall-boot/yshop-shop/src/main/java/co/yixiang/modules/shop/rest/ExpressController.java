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
import co.yixiang.modules.order.domain.YxExpress;
import co.yixiang.modules.order.service.YxExpressService;
import co.yixiang.modules.order.service.dto.YxExpressQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

/**
* @author hupeng
* @date 2019-12-12
*/
@Api(tags = "商城:快递管理")
@RestController
@RequestMapping("api")
public class ExpressController {


    private final YxExpressService yxExpressService;

    public ExpressController(YxExpressService yxExpressService) {
        this.yxExpressService = yxExpressService;
    }

    @Log("查询快递")
    @ApiOperation(value = "查询快递")
    @GetMapping(value = "/yxExpress")
    @PreAuthorize("hasAnyRole('admin','YXEXPRESS_ALL','YXEXPRESS_SELECT')")
    public ResponseEntity getYxExpresss(YxExpressQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxExpressService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @ForbidSubmit
    @Log("新增快递")
    @ApiOperation(value = "新增快递")
    @PostMapping(value = "/yxExpress")
    @PreAuthorize("hasAnyRole('admin','YXEXPRESS_ALL','YXEXPRESS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxExpress resources){
        return new ResponseEntity<>(yxExpressService.save(resources),HttpStatus.CREATED);
    }

    @ForbidSubmit
    @Log("修改快递")
    @ApiOperation(value = "修改快递")
    @PutMapping(value = "/yxExpress")
    @PreAuthorize("hasAnyRole('admin','YXEXPRESS_ALL','YXEXPRESS_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxExpress resources){
        yxExpressService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ForbidSubmit
    @Log("删除快递")
    @ApiOperation(value = "删除快递")
    @DeleteMapping(value = "/yxExpress/{id}")
    @PreAuthorize("hasAnyRole('admin','YXEXPRESS_ALL','YXEXPRESS_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxExpressService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
