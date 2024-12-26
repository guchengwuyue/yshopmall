/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.user.rest;

import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.user.service.YxUserRechargeService;
import co.yixiang.modules.user.service.dto.YxUserRechargeDto;
import co.yixiang.modules.user.service.dto.YxUserRechargeQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object> getYxUserRecharges(YxUserRechargeQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxUserRechargeService.queryAll(criteria,pageable),HttpStatus.OK);
    }




    @ForbidSubmit
    @Log("删除充值管理")
    @ApiOperation("删除充值管理")
    @PreAuthorize("@el.check('yxUserRecharge:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        yxUserRechargeService.removeByIds(new ArrayList<>(Arrays.asList(ids)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
