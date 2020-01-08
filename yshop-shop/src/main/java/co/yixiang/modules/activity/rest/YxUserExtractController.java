package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.service.YxUserExtractService;
import co.yixiang.modules.activity.service.dto.YxUserExtractQueryCriteria;
import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.service.YxUserBillService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.YxUserDTO;
import co.yixiang.modules.wechat.service.YxWechatUserService;
import co.yixiang.modules.wechat.service.dto.YxWechatUserDTO;
import co.yixiang.utils.OrderUtil;
import co.yixiang.utils.RedisUtil;
import com.github.binarywang.wxpay.bean.entpay.EntPayRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
* @author hupeng
* @date 2019-11-14
*/
@Api(tags = "提现管理")
@RestController
@RequestMapping("api")
public class YxUserExtractController {

    @Autowired
    private YxUserExtractService yxUserExtractService;

    @Autowired
    private YxUserService yxUserService;

    @Autowired
    private YxUserBillService yxUserBillService;

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private YxWechatUserService wechatUserService;

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxUserExtract")
    @PreAuthorize("@el.check('admin','YXUSEREXTRACT_ALL','YXUSEREXTRACT_SELECT')")
    public ResponseEntity getYxUserExtracts(YxUserExtractQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxUserExtractService.queryAll(criteria,pageable),HttpStatus.OK);
    }



    @Log("修改")
    @ApiOperation(value = "修改审核")
    @PutMapping(value = "/yxUserExtract")
    @PreAuthorize("@el.check('admin','YXUSEREXTRACT_ALL','YXUSEREXTRACT_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxUserExtract resources){
        if(StrUtil.isEmpty(resources.getStatus().toString())){
            throw new BadRequestException("请选择审核状态");
        }
        if(resources.getStatus() != -1 && resources.getStatus() != 1){
            throw new BadRequestException("请选择审核状态");
        }
        if(resources.getStatus() == -1){
            if(StrUtil.isEmpty(resources.getFailMsg())){
                throw new BadRequestException("请填写失败原因");
            }
            String mark = "提现失败,退回佣金"+resources.getExtractPrice()+"元";
            YxUserDTO userDTO = yxUserService.findById(resources.getUid());

            //增加流水
            YxUserBill userBill = new YxUserBill();
            userBill.setTitle("提现失败");
            userBill.setUid(resources.getUid());
            userBill.setCategory("now_money");
            userBill.setType("extract");
            userBill.setNumber(resources.getExtractPrice());
            userBill.setLinkId(resources.getId().toString());
            userBill.setBalance(NumberUtil.add(userDTO.getBrokeragePrice(),resources.getExtractPrice()));
            userBill.setMark(mark);
            userBill.setStatus(1);
            userBill.setAddTime(OrderUtil.getSecondTimestampTwo());
            userBill.setPm(1);
            yxUserBillService.create(userBill);

            //返回提现金额
            yxUserService.incBrokeragePrice(resources.getExtractPrice().doubleValue()
                    ,resources.getUid());

            resources.setFailTime(OrderUtil.getSecondTimestampTwo());

        }
        //todo 此处为企业付款，没经过测试
        boolean isTest = true;
        if(!isTest){
            YxWechatUserDTO wechatUser =  wechatUserService.findById(resources.getUid());
            if(ObjectUtil.isNotNull(wechatUser)){
                String apiUrl = RedisUtil.get("api_url");
                if(StrUtil.isBlank(apiUrl)) throw new BadRequestException("请配置api地址");
                //读取redis配置
                String appId = RedisUtil.get("wxpay_appId");
                String mchId = RedisUtil.get("wxpay_mchId");
                if(StrUtil.isBlank(appId) || StrUtil.isBlank(mchId)){
                    throw new BadRequestException("请配置微信支付");
                }
                EntPayRequest entPayRequest = new EntPayRequest();
                try {
                    entPayRequest.setAppid(appId);
                    entPayRequest.setMchId(mchId);
                    entPayRequest.setOpenid(wechatUser.getOpenid());
                    entPayRequest.setPartnerTradeNo(resources.getId().toString());
                    entPayRequest.setCheckName("FORCE_CHECK");
                    entPayRequest.setReUserName(resources.getRealName());
                    entPayRequest.setAmount(resources.getExtractPrice()
                            .multiply(new BigDecimal(100)).intValue());
                    entPayRequest.setDescription("佣金提现");
                    entPayRequest.setSpbillCreateIp("127.0.0.1");
                    wxPayService.getEntPayService().entPay(entPayRequest);
                } catch (WxPayException e) {
                    throw new BadRequestException(e.getMessage());
                }
            }else{
                throw new BadRequestException("不是微信用户无法退款");
            }

        }
        yxUserExtractService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}