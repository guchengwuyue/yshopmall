package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.annotation.AnonymousAccess;
import co.yixiang.aop.log.Log;
import co.yixiang.constant.ShopConstants;
import co.yixiang.enums.OrderInfoEnum;
import co.yixiang.exception.BadRequestException;
import co.yixiang.express.ExpressService;
import co.yixiang.express.dao.ExpressInfo;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.activity.service.dto.YxStorePinkDTO;
import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.domain.YxStoreOrderStatus;
import co.yixiang.modules.shop.service.*;
import co.yixiang.modules.shop.service.dto.*;
import co.yixiang.modules.shop.service.param.ExpressParam;
import co.yixiang.mp.service.WxMpTemplateMessageService;
import co.yixiang.mp.service.YxTemplateService;
import co.yixiang.mp.service.YxWechatTemplateService;
import co.yixiang.utils.OrderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author hupeng
 * @date 2019-10-14
 */
@Api(tags = "商城:订单管理")
@RestController
@RequestMapping("api")
@Slf4j
public class StoreOrderController {


    private final YxStoreOrderService yxStoreOrderService;
    private final YxStoreOrderStatusService yxStoreOrderStatusService;
    private final YxExpressService yxExpressService;
    private final YxWechatUserService wechatUserService;
    private final RedisTemplate<String, String> redisTemplate;
    private final YxTemplateService templateService;
    private final YxStorePinkService  storePinkService;
    private final ExpressService expressService;


    public StoreOrderController(YxStoreOrderService yxStoreOrderService, YxStoreOrderStatusService yxStoreOrderStatusService,
                                YxExpressService yxExpressService, YxWechatUserService wechatUserService,
                                RedisTemplate<String, String> redisTemplate,
                                YxTemplateService templateService,YxStorePinkService storePinkService,
                                ExpressService expressService) {
        this.yxStoreOrderService = yxStoreOrderService;
        this.yxStoreOrderStatusService = yxStoreOrderStatusService;
        this.yxExpressService = yxExpressService;
        this.wechatUserService = wechatUserService;
        this.redisTemplate = redisTemplate;
        this.templateService = templateService;
        this.storePinkService = storePinkService;
        this.expressService = expressService;
    }

    /**@Valid
     * 根据商品分类统计订单占比
     */
    @GetMapping("/yxStoreOrder/orderCount")
    @ApiOperation(value = "根据商品分类统计订单占比",notes = "根据商品分类统计订单占比",response = ExpressParam.class)
    public ResponseEntity orderCount(){
        OrderCountDto orderCountDto  = yxStoreOrderService.getOrderCount();
        return new ResponseEntity(orderCountDto, HttpStatus.OK);
    }

    @GetMapping(value = "/data/count")
    @AnonymousAccess
    public ResponseEntity getCount() {
        return new ResponseEntity(yxStoreOrderService.getOrderTimeData(), HttpStatus.OK);
    }

    @GetMapping(value = "/data/chart")
    @AnonymousAccess
    public ResponseEntity getChart() {
        return new ResponseEntity(yxStoreOrderService.chartCount(), HttpStatus.OK);
    }


    @ApiOperation(value = "查询订单")
    @GetMapping(value = "/yxStoreOrder")
    @PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_SELECT')")
    public ResponseEntity getYxStoreOrders(YxStoreOrderQueryCriteria criteria,
                                           Pageable pageable,
                                           @RequestParam(name = "orderStatus") String orderStatus,
                                           @RequestParam(name = "orderType") String orderType) {


        criteria.setShippingType(1);//默认查询所有快递订单
        //订单状态查询
        if (StrUtil.isNotEmpty(orderStatus)) {
            switch (orderStatus) {
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
        //订单类型查询
        if (StrUtil.isNotEmpty(orderType)) {
            switch (orderType) {
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
                case "5":
                    criteria.setShippingType(2);
                    break;
            }
        }


        return new ResponseEntity(yxStoreOrderService.queryAll(criteria, pageable), HttpStatus.OK);
    }


    @ApiOperation(value = "发货")
    @PutMapping(value = "/yxStoreOrder")
    @PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreOrder resources) {
        if (StrUtil.isBlank(resources.getDeliveryName())) throw new BadRequestException("请选择快递公司");
        if (StrUtil.isBlank(resources.getDeliveryId())) throw new BadRequestException("快递单号不能为空");
        YxExpressDTO expressDTO = yxExpressService.findById(Integer.valueOf(resources
                .getDeliveryName()));
        if (ObjectUtil.isNull(expressDTO)) {
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
        storeOrderStatus.setChangeMessage("已发货 快递公司：" + resources.getDeliveryName()
                + " 快递单号：" + resources.getDeliveryId());
        storeOrderStatus.setChangeTime(OrderUtil.getSecondTimestampTwo());

        yxStoreOrderStatusService.create(storeOrderStatus);

        //模板消息通知
        try {
            YxWechatUserDTO wechatUser = wechatUserService.findById(resources.getUid());
            if (ObjectUtil.isNotNull(wechatUser)) {
                //公众号与小程序打通统一公众号模板通知
                if (StrUtil.isNotBlank(wechatUser.getOpenid())) {
                    templateService.deliverySuccessNotice(resources.getOrderId(),
                            expressDTO.getName(),resources.getDeliveryId(),wechatUser.getOpenid());
                }
            }
        } catch (Exception e) {
            log.info("当前用户不是微信用户不能发送模板消息哦!");
        }

        //加入redis，7天后自动确认收货
        String redisKey = String.valueOf(StrUtil.format("{}{}",
                ShopConstants.REDIS_ORDER_OUTTIME_UNCONFIRM,resources.getId()));
        redisTemplate.opsForValue().set(redisKey, resources.getOrderId(),
                ShopConstants.ORDER_OUTTIME_UNCONFIRM, TimeUnit.DAYS);


        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "订单核销")
    @PutMapping(value = "/yxStoreOrder/check")
    @PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity check(@Validated @RequestBody YxStoreOrder resources) {
        if (StrUtil.isBlank(resources.getVerifyCode())) throw new BadRequestException("核销码不能为空");
        YxStoreOrderDTO storeOrderDTO = yxStoreOrderService.findById(resources.getId());
        if(!resources.getVerifyCode().equals(storeOrderDTO.getVerifyCode())){
            throw new BadRequestException("核销码不对");
        }
        if(OrderInfoEnum.PAY_STATUS_0.getValue().equals(storeOrderDTO.getPaid())){
            throw new BadRequestException("订单未支付");
        }
        if(storeOrderDTO.getStatus() > 0) throw new BadRequestException("订单已核销");

        if(storeOrderDTO.getCombinationId() > 0 && storeOrderDTO.getPinkId() > 0){
            YxStorePinkDTO storePinkDTO = storePinkService.findById(storeOrderDTO.getPinkId());
            if(!OrderInfoEnum.PINK_STATUS_2.getValue().equals(storePinkDTO.getStatus())){
                throw new BadRequestException("拼团订单暂未成功无法核销");
            }
        }

        resources.setStatus(OrderInfoEnum.STATUS_2.getValue());
        yxStoreOrderService.update(resources);


        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = "退款")
    @PostMapping(value = "/yxStoreOrder/refund")
    @PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity refund(@Validated @RequestBody YxStoreOrder resources) {
        yxStoreOrderService.refund(resources);

        //模板消息通知
        try {
            YxWechatUserDTO wechatUser = wechatUserService.findById(resources.getUid());
            if (ObjectUtil.isNotNull(wechatUser)) {
                //公众号与小程序打通统一公众号模板通知
                if (StrUtil.isNotBlank(wechatUser.getOpenid())) {
                    templateService.refundSuccessNotice(resources.getOrderId(),
                            resources.getPayPrice().toString(),wechatUser.getOpenid(),
                            OrderUtil.stampToDate(resources.getAddTime().toString()));
                }
            }
        } catch (Exception e) {
            log.info("当前用户不是微信用户不能发送模板消息哦!");
        }


        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreOrder/{id}")
    @PreAuthorize("@el.check('admin','YXSTOREORDER_ALL','YXSTOREORDER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id) {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxStoreOrderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @Log("修改订单")
    @ApiOperation(value = "修改订单")
    @PostMapping(value = "/yxStoreOrder/edit")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity editOrder(@RequestBody YxStoreOrder resources) {
        if (ObjectUtil.isNull(resources.getPayPrice())) throw new BadRequestException("请输入支付金额");
        if (resources.getPayPrice().doubleValue() < 0) throw new BadRequestException("金额不能低于0");

        YxStoreOrderDTO storeOrder = yxStoreOrderService.findById(resources.getId());
        //判断金额是否有变动,生成一个额外订单号去支付

        int res = NumberUtil.compare(storeOrder.getPayPrice().doubleValue(), resources.getPayPrice().doubleValue());
        if (res != 0) {
            String orderSn = IdUtil.getSnowflake(0, 0).nextIdStr();
            resources.setExtendOrderId(orderSn);
        }


        yxStoreOrderService.update(resources);

        YxStoreOrderStatus storeOrderStatus = new YxStoreOrderStatus();
        storeOrderStatus.setOid(resources.getId());
        storeOrderStatus.setChangeType("order_edit");
        storeOrderStatus.setChangeMessage("修改订单价格为：" + resources.getPayPrice());
        storeOrderStatus.setChangeTime(OrderUtil.getSecondTimestampTwo());

        yxStoreOrderStatusService.create(storeOrderStatus);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("修改订单备注")
    @ApiOperation(value = "修改订单备注")
    @PostMapping(value = "/yxStoreOrder/remark")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity editOrderRemark(@RequestBody YxStoreOrder resources) {
        if (StrUtil.isBlank(resources.getRemark())) throw new BadRequestException("请输入备注");
        yxStoreOrderService.update(resources);
        return new ResponseEntity(HttpStatus.OK);
    }


    /**@Valid
     * 获取物流信息,根据传的订单编号 ShipperCode快递公司编号 和物流单号,
     */
    @PostMapping("/yxStoreOrder/express")
    @ApiOperation(value = "获取物流信息",notes = "获取物流信息",response = ExpressParam.class)
    public ResponseEntity express( @RequestBody ExpressParam expressInfoDo){
        ExpressInfo expressInfo = expressService.getExpressInfo(expressInfoDo.getOrderCode(),
                expressInfoDo.getShipperCode(), expressInfoDo.getLogisticCode());
        if(!expressInfo.isSuccess()) throw new BadRequestException(expressInfo.getReason());
        return new ResponseEntity(expressInfo, HttpStatus.OK);
    }

}