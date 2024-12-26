/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.rest;


import co.yixiang.api.ApiResult;
import co.yixiang.modules.logging.aop.log.AppLog;
import co.yixiang.common.bean.LocalUser;
import co.yixiang.common.interceptor.AuthCheck;
import co.yixiang.constant.SystemConfigConstants;
import co.yixiang.modules.activity.param.UserExtParam;
import co.yixiang.modules.activity.service.YxUserExtractService;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.user.domain.YxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 用户提现 前端控制器
 * </p>
 *
 * @author hupeng
 * @since 2019-11-11
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "用户提现", tags = "用户:用户提现")
public class UserExtractController {

    private final YxUserExtractService userExtractService;
    private final YxSystemConfigService systemConfigService;

    /**
     * 提现参数
     */
    @AuthCheck
    @GetMapping("/extract/bank")
    @ApiOperation(value = "提现参数",notes = "提现参数")
    public ApiResult<Object> bank(){
        YxUser yxUser = LocalUser.getUser();
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("commissionCount",yxUser.getBrokeragePrice());
        map.put("minPrice",systemConfigService.getData(SystemConfigConstants.USER_EXTRACT_MIN_PRICE));
        return ApiResult.ok(map);
    }


    /**
    * 用户提现
    */
    @AppLog(value = "用户提现", type = 1)
    @AuthCheck
    @PostMapping("/extract/cash")
    @ApiOperation(value = "用户提现",notes = "用户提现")
    public ApiResult<String> addYxUserExtract(@Valid @RequestBody UserExtParam param){
        YxUser yxUser = LocalUser.getUser();
        userExtractService.userExtract(yxUser,param);
        return ApiResult.ok("申请提现成功");
    }




}

