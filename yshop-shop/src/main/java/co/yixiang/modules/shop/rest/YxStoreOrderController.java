package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.domain.YxStoreOrderStatus;
import co.yixiang.modules.shop.service.YxExpressService;
import co.yixiang.modules.shop.service.YxStoreOrderService;
import co.yixiang.modules.shop.service.YxStoreOrderStatusService;
import co.yixiang.modules.shop.service.dto.YxExpressDTO;
import co.yixiang.modules.shop.service.dto.YxStoreOrderDTO;
import co.yixiang.modules.shop.service.dto.YxStoreOrderQueryCriteria;
import co.yixiang.modules.wechat.service.YxWechatUserService;
import co.yixiang.modules.wechat.service.dto.YxWechatUserDTO;
import co.yixiang.mp.domain.YxWechatTemplate;
import co.yixiang.mp.service.WxMpTemplateMessageService;
import co.yixiang.mp.service.YxWechatTemplateService;
import co.yixiang.utils.OrderUtil;
import co.yixiang.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-14
*/
@Api(tags = "订单管理")
@RestController
@RequestMapping("api")
@Slf4j
public class YxStoreOrderController {

    @Autowired
    private YxStoreOrderService yxStoreOrderService;

    @Autowired
    private YxStoreOrderStatusService yxStoreOrderStatusService;

    @Autowired
    private YxExpressService yxExpressService;

    @Autowired
    private YxWechatUserService wechatUserService;

    @Autowired
    private WxMpTemplateMessageService templateMessageService;

    @Autowired
    private YxWechatTemplateService yxWechatTemplateService;


    @GetMapping(value = "/data/count")
    //@PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_SELECT')")
    public ResponseEntity getCount(){
        return new ResponseEntity(yxStoreOrderService.getOrderTimeData(),HttpStatus.OK);
    }

    @GetMapping(value = "/data/chart")
    //@PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_SELECT')")
    public ResponseEntity getChart(){
        return new ResponseEntity(yxStoreOrderService.chartCount(),HttpStatus.OK);
    }



    @ApiOperation(value = "查询订单")
    @GetMapping(value = "/yxStoreOrder")
    @PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_SELECT')")
    public ResponseEntity getYxStoreOrders(YxStoreOrderQueryCriteria criteria,
                                           Pageable pageable,
                                           @RequestParam(name = "orderStatus") String orderStatus,
                                           @RequestParam(name = "orderType") String orderType){


        if(StrUtil.isNotEmpty(orderStatus)){
            switch (orderStatus){
                case "0":
                    criteria.setIsDel(0);
                    criteria.setPaid(0);
                    criteria.setStatus(0);
                    criteria.setRefundStatus(0);
                    break;
                case "1":
                    criteria.setIsDel(0);
                    criteria.setPaid(1);
                    criteria.setStatus(0);
                    criteria.setRefundStatus(0);
                    break;
                case "2":
                    criteria.setIsDel(0);
                    criteria.setPaid(1);
                    criteria.setStatus(1);
                    criteria.setRefundStatus(0);
                    break;
                case "3":
                    criteria.setIsDel(0);
                    criteria.setPaid(1);
                    criteria.setStatus(2);
                    criteria.setRefundStatus(0);
                    break;
                case "4":
                    criteria.setIsDel(0);
                    criteria.setPaid(1);
                    criteria.setStatus(3);
                    criteria.setRefundStatus(0);
                    break;
                case "-1":
                    criteria.setIsDel(0);
                    criteria.setPaid(1);
                    criteria.setRefundStatus(1);
                    break;
                case "-2":
                    criteria.setIsDel(0);
                    criteria.setPaid(1);
                    criteria.setRefundStatus(2);
                    break;
                case "-4":
                    criteria.setIsDel(1);
                    break;
            }
        }
        if(StrUtil.isNotEmpty(orderType)){
            switch (orderType){
                case "1":
                    criteria.setBargainId(0);
                    criteria.setCombinationId(0);
                    criteria.setSeckillId(0);
                    break;
                case "2":
                    criteria.setNewCombinationId(0);
                    break;
                case "3":
                    criteria.setNewSeckillId(0);
                    break;
                case "4":
                    criteria.setNewBargainId(0);
                    break;
            }
        }


        return new ResponseEntity(yxStoreOrderService.queryAll(criteria,pageable),HttpStatus.OK);
    }




    @ApiOperation(value = "发货")
    @PutMapping(value = "/yxStoreOrder")
    @PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreOrder resources){
        if(StrUtil.isBlank(resources.getDeliveryName())) throw new BadRequestException("请选择快递公司");
        if(StrUtil.isBlank(resources.getDeliveryId())) throw new BadRequestException("快递单号不能为空");
        YxExpressDTO expressDTO = yxExpressService.findById(Integer.valueOf(resources
                .getDeliveryName()));
        if(ObjectUtil.isNull(expressDTO)){
            throw new BadRequestException("请先添加快递公司");
        }
        resources.setStatus(1);
        resources.setDeliveryType("express");
        resources.setDeliveryName(expressDTO.getName());
        resources.setDeliverySn(expressDTO.getCode());

        yxStoreOrderService.update(resources);

        YxStoreOrderStatus storeOrderStatus = new YxStoreOrderStatus();
        storeOrderStatus.setOid(resources.getId());
        storeOrderStatus.setChangeType("delivery_goods");
        storeOrderStatus.setChangeMessage("已发货 快递公司："+resources.getDeliveryName()
                +" 快递单号："+resources.getDeliveryId());
        storeOrderStatus.setChangeTime(OrderUtil.getSecondTimestampTwo());

        yxStoreOrderStatusService.create(storeOrderStatus);

        //模板消息通知
        String siteUrl = RedisUtil.get("site_url");
        try{
            YxWechatUserDTO wechatUser =  wechatUserService.findById(resources.getUid());
            if(ObjectUtil.isNotNull(wechatUser)){
                if(StrUtil.isNotBlank(wechatUser.getOpenid())){
                    YxWechatTemplate WechatTemplate = yxWechatTemplateService
                            .findByTempkey("OPENTM200565259");
                    Map<String,String> map = new HashMap<>();
                    map.put("first","亲，宝贝已经启程了，好想快点来到你身边。");
                    map.put("keyword1",resources.getOrderId());//订单号
                    map.put("keyword2",expressDTO.getName());
                    map.put("keyword3",resources.getDeliveryId());
                    map.put("remark","yshop电商系统为你服务！");
                    templateMessageService.sendWxMpTemplateMessage( wechatUser.getOpenid()
                            ,WechatTemplate.getTempid(),
                            siteUrl+"/order/detail/"+resources.getOrderId(),map);
                }else if(StrUtil.isNotBlank(wechatUser.getRoutineOpenid())){
                    //todo 小程序通知
                }

            }
        }catch (Exception e){
            log.info("当前用户不是微信用户不能发送模板消息哦!");
        }


        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = "退款")
    @PostMapping(value = "/yxStoreOrder/refund")
    @PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity refund(@Validated @RequestBody YxStoreOrder resources){
        yxStoreOrderService.refund(resources);

        //模板消息通知
        String siteUrl = RedisUtil.get("site_url");
        try{
            YxWechatUserDTO wechatUser =  wechatUserService.findById(resources.getUid());
            if(ObjectUtil.isNotNull(wechatUser)){
                if(StrUtil.isNotBlank(wechatUser.getOpenid())){
                    YxWechatTemplate WechatTemplate = yxWechatTemplateService
                            .findByTempkey("OPENTM410119152");
                    Map<String,String> map = new HashMap<>();
                    map.put("first","您在yshop的订单退款申请被通过，钱款将很快还至您的支付账户。");
                    map.put("keyword1",resources.getOrderId());//订单号
                    map.put("keyword2",resources.getPayPrice().toString());
                    map.put("keyword3", OrderUtil.stampToDate(resources.getAddTime().toString()));
                    map.put("remark","yshop电商系统为你服务！");
                    templateMessageService.sendWxMpTemplateMessage( wechatUser.getOpenid()
                            ,WechatTemplate.getTempid(),
                            siteUrl+"/order/detail/"+resources.getOrderId(),map);
                }else if(StrUtil.isNotBlank(wechatUser.getRoutineOpenid())){
                    //todo 小程序通知
                }

            }
        }catch (Exception e){
            log.info("当前用户不是微信用户不能发送模板消息哦!");
        }


        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreOrder/{id}")
    @PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxStoreOrderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }



    @Log("修改订单")
    @ApiOperation(value = "修改订单")
    @PostMapping(value = "/yxStoreOrder/edit")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity editOrder(@RequestBody YxStoreOrder resources){
        if(ObjectUtil.isNull(resources.getPayPrice())) throw new BadRequestException("请输入支付金额");
        if(resources.getPayPrice().doubleValue() < 0) throw new BadRequestException("金额不能低于0");

        YxStoreOrderDTO storeOrder = yxStoreOrderService.findById(resources.getId());
        //判断金额是否有变动,生成一个额外订单号去支付

        int res = NumberUtil.compare(storeOrder.getPayPrice().doubleValue(),resources.getPayPrice().doubleValue());
        if(res != 0){
            String orderSn = IdUtil.getSnowflake(0,0).nextIdStr();
            resources.setExtendOrderId(orderSn);
        }


        yxStoreOrderService.update(resources);

        YxStoreOrderStatus storeOrderStatus = new YxStoreOrderStatus();
        storeOrderStatus.setOid(resources.getId());
        storeOrderStatus.setChangeType("order_edit");
        storeOrderStatus.setChangeMessage("修改订单价格为："+resources.getPayPrice());
        storeOrderStatus.setChangeTime(OrderUtil.getSecondTimestampTwo());

        yxStoreOrderStatusService.create(storeOrderStatus);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("修改订单备注")
    @ApiOperation(value = "修改订单备注")
    @PostMapping(value = "/yxStoreOrder/remark")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity editOrderRemark(@RequestBody YxStoreOrder resources){
        if(StrUtil.isBlank(resources.getRemark())) throw new BadRequestException("请输入备注");
        yxStoreOrderService.update(resources);
        return new ResponseEntity(HttpStatus.OK);
    }
}