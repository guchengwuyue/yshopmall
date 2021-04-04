/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.exception.BadRequestException;
import co.yixiang.logging.aop.log.Log;
import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.service.YxUserExtractService;
import co.yixiang.modules.activity.service.dto.YxUserExtractQueryCriteria;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.service.YxUserBillService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.WechatUserDto;
import co.yixiang.modules.shop.service.dto.YxUserDto;
import co.yixiang.modules.shop.service.dto.YxWechatUserDto;
import co.yixiang.mp.service.YxPayService;
import co.yixiang.utils.OrderUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.binarywang.wxpay.exception.WxPayException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hupeng
 * @date 2019-11-14
 */
@Api(tags = "商城:提现管理")
@RestController
@RequestMapping("api")
public class UserExtractController {

    private final YxUserExtractService yxUserExtractService;
    private final YxUserService yxUserService;
    private final YxUserBillService yxUserBillService;
    private final YxUserService userService;
    private final YxPayService payService;
    private final IGenerator generator;

    public UserExtractController(YxUserExtractService yxUserExtractService, YxUserService yxUserService,
                                 YxUserBillService yxUserBillService, YxUserService userService,
                                 YxPayService payService, IGenerator generator) {
        this.yxUserExtractService = yxUserExtractService;
        this.yxUserService = yxUserService;
        this.yxUserBillService = yxUserBillService;
        this.userService = userService;
        this.payService = payService;
        this.generator = generator;
    }

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxUserExtract")
    @PreAuthorize("hasAnyRole('admin','YXUSEREXTRACT_ALL','YXUSEREXTRACT_SELECT')")
    public ResponseEntity getYxUserExtracts(YxUserExtractQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity(yxUserExtractService.queryAll(criteria, pageable), HttpStatus.OK);
    }


    @Log("修改")
    @ApiOperation(value = "修改审核")
    @PutMapping(value = "/yxUserExtract")
    @PreAuthorize("hasAnyRole('admin','YXUSEREXTRACT_ALL','YXUSEREXTRACT_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxUserExtract resources) {
        if (resources.getStatus() == null) {
            throw new BadRequestException("请选择审核状态");
        }

        if(ShopCommonEnum.EXTRACT_0.getValue().equals(resources.getStatus())){
            throw new BadRequestException("请选择审核状态");
        }

        if(ShopCommonEnum.EXTRACT_MINUS_1.getValue().equals(resources.getStatus())){
            if (StrUtil.isEmpty(resources.getFailMsg())) {
                throw new BadRequestException("请填写失败原因");
            }
            String mark = "提现失败,退回佣金" + resources.getExtractPrice() + "元";
            YxUserDto userDTO = generator.convert(yxUserService.getOne(new LambdaQueryWrapper<YxUser>().eq(YxUser::getUid, resources.getUid())), YxUserDto.class);

            //增加流水
            YxUserBill userBill = new YxUserBill();
            userBill.setTitle("提现失败");
            userBill.setUid(resources.getUid());
            userBill.setCategory("now_money");
            userBill.setType("extract");
            userBill.setNumber(resources.getExtractPrice());
            userBill.setLinkId(resources.getId().toString());
            userBill.setBalance(NumberUtil.add(userDTO.getBrokeragePrice(), resources.getExtractPrice()));
            userBill.setMark(mark);
            userBill.setStatus(1);
            userBill.setPm(1);
            yxUserBillService.save(userBill);

            //返回提现金额
            yxUserService.incBrokeragePrice(resources.getExtractPrice().doubleValue()
                    , resources.getUid());

            resources.setFailTime(new Date());

        }
        //todo 此处为企业付款，没经过测试
        boolean isTest = true;
        if (!isTest) {
            String openid = this.getUserOpenid(resources.getUid());
            if (StrUtil.isNotBlank(openid)) {
                try {
                    payService.entPay(openid, resources.getId().toString(),
                            resources.getRealName(),
                            resources.getExtractPrice().multiply(new BigDecimal(100)).intValue());
                } catch (WxPayException e) {
                    throw new BadRequestException(e.getMessage());
                }
            } else {
                throw new BadRequestException("不是微信用户无法退款");
            }

        }
        yxUserExtractService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * 获取openid
     * @param uid uid
     * @return String
     */
    private String getUserOpenid(Long uid){
        YxUser yxUser = userService.getById(uid);
        if(yxUser == null) {
            return "";
        }

        WechatUserDto wechatUserDto = yxUser.getWxProfile();
        if(wechatUserDto == null) {
            return "";
        }
        if(StrUtil.isBlank(wechatUserDto.getOpenid())) {
            return "";
        }
        return wechatUserDto.getOpenid();

    }

}
