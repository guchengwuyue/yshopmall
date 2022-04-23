/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.rest;

import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.NoRepeatSubmit;
import co.yixiang.modules.shop.domain.YxUserRecharge;
import co.yixiang.modules.shop.service.YxUserRechargeService;
import co.yixiang.modules.shop.service.dto.YxUserRechargeDto;
import co.yixiang.modules.shop.service.dto.YxUserRechargeQueryCriteria;
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
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author hupeng
 * @date 2020-03-02
 */
@Api(tags = "充值管理管理")
@RestController
@RequestMapping("/api/yxUserRecharge")
public class UserRechargeController {

    private final YxUserRechargeService yxUserRechargeService;
    private final IGenerator generator;

    public UserRechargeController(YxUserRechargeService yxUserRechargeService, IGenerator generator) {
        this.yxUserRechargeService = yxUserRechargeService;
        this.generator = generator;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('yxUserRecharge:list')")
    public void download(HttpServletResponse response, YxUserRechargeQueryCriteria criteria) throws IOException {
        yxUserRechargeService.download(generator.convert(yxUserRechargeService.queryAll(criteria), YxUserRechargeDto.class), response);
    }

    @GetMapping
    @Log("查询充值管理")
    @ApiOperation("查询充值管理")
    @PreAuthorize("@el.check('yxUserRecharge:list')")
    public ResponseEntity<Object> getYxUserRecharges(YxUserRechargeQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(yxUserRechargeService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @NoRepeatSubmit
    @PostMapping
    @Log("新增充值管理")
    @ApiOperation("新增充值管理")
    @PreAuthorize("@el.check('yxUserRecharge:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody YxUserRecharge resources) {
        return new ResponseEntity<>(yxUserRechargeService.save(resources), HttpStatus.CREATED);
    }

    @NoRepeatSubmit
    @Log("删除充值管理")
    @ApiOperation("删除充值管理")
    @PreAuthorize("@el.check('yxUserRecharge:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {

        yxUserRechargeService.removeByIds(new ArrayList<>(Arrays.asList(ids)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
