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
import co.yixiang.common.aop.NoRepeatSubmit;
import co.yixiang.common.bean.LocalUser;
import co.yixiang.common.interceptor.AuthCheck;
import co.yixiang.constant.ShopConstants;
import co.yixiang.enums.BillInfoEnum;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.order.vo.UserOrderCountVo;
import co.yixiang.modules.product.service.YxStoreProductRelationService;
import co.yixiang.modules.product.vo.YxStoreProductRelationQueryVo;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.shop.service.YxSystemGroupDataService;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.param.UserEditParam;
import co.yixiang.modules.user.service.YxUserBillService;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.service.YxUserSignService;
import co.yixiang.modules.user.vo.SignVo;
import co.yixiang.modules.user.vo.YxUserQueryVo;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static co.yixiang.constant.SystemConfigConstants.YSHOP_SHOW_RECHARGE;

/**
 * <p>
 * 用户控制器
 * </p>
 *
 * @author hupeng
 * @since 2019-10-16
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "用户中心", tags = "用户:用户中心")
public class UserController {

    private final YxUserService yxUserService;
    private final YxSystemGroupDataService systemGroupDataService;
    private final YxStoreOrderService orderService;
    private final YxStoreProductRelationService relationService;
    private final YxUserSignService userSignService;
    private final YxUserBillService userBillService;
    private final YxSystemConfigService systemConfigService;


    /**
     * 用户资料
     */
    @AuthCheck
    @GetMapping("/userinfo")
    @ApiOperation(value = "获取用户信息",notes = "获取用户信息",response = YxUserQueryVo.class)
    public ApiResult<Object> userInfo(){
        YxUser yxUser = LocalUser.getUser();
        return ApiResult.ok(yxUserService.getNewYxUserById(yxUser));
    }

    /**
     * 获取个人中心菜单
     */
    @GetMapping("/menu/user")
    @ApiOperation(value = "获取个人中心菜单",notes = "获取个人中心菜单")
    public ApiResult<Map<String,Object>> userMenu(){
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("routine_my_menus",systemGroupDataService.getDatas(ShopConstants.YSHOP_MY_MENUES));
        return ApiResult.ok(map);
    }



    /**
     * 订单统计数据
     */
    @AppLog(value = "查看订单统计数据", type = 1)
    @AuthCheck
    @GetMapping("/order/data")
    @ApiOperation(value = "订单统计数据",notes = "订单统计数据")
    public ApiResult<UserOrderCountVo> orderData(){
        Long uid = LocalUser.getUser().getUid();
        return ApiResult.ok(orderService.orderData(uid));
    }

    /**
     * 获取收藏产品
     */
    @AuthCheck
    @GetMapping("/collect/user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码,默认为1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "页大小,默认为10", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "type", value = "foot为足迹,collect为收藏", paramType = "query", dataType = "String")
    })
    @ApiOperation(value = "获取收藏产品,或足迹",notes = "获取收藏产品,或足迹")
    public ApiResult<List<YxStoreProductRelationQueryVo>> collectUser(@RequestParam(value = "page",defaultValue = "1") int page,
                                                                      @RequestParam(value = "limit",defaultValue = "10") int limit,
                                                                      @RequestParam(value = "type") String type){
        Long uid = LocalUser.getUser().getUid();
        return ApiResult.ok(relationService.userCollectProduct(page,limit,uid,type));
    }

    /**
     * 用户资金统计
     */
    @AppLog(value = "查看用户资金统计", type = 1)
    @AuthCheck
    @GetMapping("/user/balance")
    @ApiOperation(value = "用户资金统计",notes = "用户资金统计")
    public ApiResult<Object> userBalance(){
        YxUser yxUser = LocalUser.getUser();
        Map<String,Object> map = Maps.newHashMap();
        Double[] userMoneys = yxUserService.getUserMoney(yxUser.getUid());
        map.put("now_money",yxUser.getNowMoney());
        map.put("orderStatusSum",userMoneys[0]);
        map.put("recharge",userMoneys[1]);
        map.put("is_hide",systemConfigService.getData(YSHOP_SHOW_RECHARGE));
        return ApiResult.ok(map);
    }


    /**
     * 签到用户信息
     */
    @AppLog(value = "签到用户信息", type = 1)
    @AuthCheck
    @PostMapping("/sign/user")
    @ApiOperation(value = "签到用户信息",notes = "签到用户信息")
    public ApiResult<YxUserQueryVo> sign(){
        YxUser yxUser = LocalUser.getUser();
        return ApiResult.ok(userSignService.userSignInfo(yxUser));
    }

    /**
     * 签到配置
     */
    @GetMapping("/sign/config")
    @ApiOperation(value = "签到配置",notes = "签到配置")
    public ApiResult<Object> signConfig(){
        return ApiResult.ok(systemGroupDataService.getDatas(ShopConstants.YSHOP_SIGN_DAY_NUM));
    }

    /**
     * 签到列表
     */
    @AppLog(value = "查看签到列表", type = 1)
    @AuthCheck
    @GetMapping("/sign/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码,默认为1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "页大小,默认为10", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "签到列表",notes = "签到列表")
    public ApiResult<List<SignVo>> signList(@RequestParam(value = "page",defaultValue = "1") int page,
                                            @RequestParam(value = "limit",defaultValue = "10") int limit){
        Long uid = LocalUser.getUser().getUid();
        return ApiResult.ok(userSignService.getSignList(uid,page,limit));
    }

    /**
     * 签到列表（年月）
     */

    @AuthCheck
    @GetMapping("/sign/month")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码,默认为1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "页大小,默认为10", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "签到列表（年月）",notes = "签到列表（年月）")
    public ApiResult<Object> signMonthList(@RequestParam(value = "page",defaultValue = "1") int page,
                                           @RequestParam(value = "limit",defaultValue = "10") int limit){
        Long uid = LocalUser.getUser().getUid();
        return ApiResult.ok(userBillService.getUserBillList(page, limit,uid, BillInfoEnum.SIGN_INTEGRAL.getValue()));
    }

    /**
     * 开始签到
     */
    @AppLog(value = "开始签到", type = 1)
    @NoRepeatSubmit
    @AuthCheck
    @PostMapping("/sign/integral")
    @ApiOperation(value = "开始签到",notes = "开始签到")
    public ApiResult<Object> signIntegral(){
        YxUser yxUser = LocalUser.getUser();
        int integral = userSignService.sign(yxUser);

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("integral",integral);
        return ApiResult.ok(map,"签到获得" + integral + "积分");
    }

    @AppLog(value = "用户修改信息", type = 1)
    @AuthCheck
    @PostMapping("/user/edit")
    @ApiOperation(value = "用户修改信息",notes = "用修改信息")
    public ApiResult<Object> edit(@Validated @RequestBody UserEditParam param){
        YxUser yxUser = LocalUser.getUser();
        yxUser.setAvatar(param.getAvatar());
        yxUser.setNickname(param.getNickname());

        yxUserService.updateById(yxUser);

        return ApiResult.ok("修改成功");
    }




}

