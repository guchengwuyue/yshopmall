/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.order.rest;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.annotation.AnonymousAccess;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.OrderInfoEnum;
import co.yixiang.enums.OrderLogEnum;
import co.yixiang.enums.ShipperCodeEnum;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.aop.ForbidSubmit;
import co.yixiang.modules.order.domain.YxStoreOrder;
import co.yixiang.modules.order.domain.YxStoreOrderStatus;
import co.yixiang.modules.order.param.ExpressParam;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.order.service.YxStoreOrderStatusService;
import co.yixiang.modules.order.service.dto.*;
import co.yixiang.modules.tools.express.ExpressService;
import co.yixiang.modules.tools.express.config.ExpressAutoConfiguration;
import co.yixiang.modules.tools.express.dao.ExpressInfo;
import co.yixiang.modules.tools.express.dao.Traces;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hupeng
 * @date 2019-10-14
 */
@Api(tags = "商城:订单管理")
@RestController
@RequestMapping("api")
@Slf4j
@SuppressWarnings("unchecked")
public class StoreOrderController {

    @Value("${yshop.apiUrl}")
    private String apiUrl;

    private final IGenerator generator;
    private final YxStoreOrderService yxStoreOrderService;
    private final YxStoreOrderStatusService yxStoreOrderStatusService;


    public StoreOrderController(IGenerator generator, YxStoreOrderService yxStoreOrderService,
                                YxStoreOrderStatusService yxStoreOrderStatusService) {
        this.generator = generator;
        this.yxStoreOrderService = yxStoreOrderService;
        this.yxStoreOrderStatusService = yxStoreOrderStatusService;
    }

    /**@Valid
     * 根据商品分类统计订单占比
     */
    @GetMapping("/yxStoreOrder/orderCount")
    @ApiOperation(value = "根据商品分类统计订单占比",notes = "根据商品分类统计订单占比",response = ExpressParam.class)
    public ResponseEntity orderCount(){
        OrderCountDto orderCountDto  = yxStoreOrderService.getOrderCount();
        return new ResponseEntity<>(orderCountDto, HttpStatus.OK);
    }

    /**
     * 首页订单/用户等统计
     * @return OrderTimeDataDto
     */
    @GetMapping(value = "/data/count")
    @AnonymousAccess
    public ResponseEntity getCount() {
        return new ResponseEntity<>(yxStoreOrderService.getOrderTimeData(), HttpStatus.OK);
    }

    /**
     * 返回本月订单金额与数量chart
     * @return map
     */
    @GetMapping(value = "/data/chart")
    @AnonymousAccess
    public ResponseEntity getChart() {
        return new ResponseEntity<>(yxStoreOrderService.chartCount(), HttpStatus.OK);
    }


    @ApiOperation(value = "查询订单")
    @GetMapping(value = "/yxStoreOrder")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_SELECT','YXEXPRESS_SELECT')")
    public ResponseEntity getYxStoreOrders(YxStoreOrderQueryCriteria criteria,
                                           Pageable pageable,
                                           @RequestParam(name = "orderStatus") String orderStatus,
                                           @RequestParam(name = "orderType") String orderType) {

        YxStoreOrderQueryCriteria newCriteria = this.handleQuery(criteria,orderStatus,orderType);

        return new ResponseEntity<>(yxStoreOrderService.queryAll(newCriteria, pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "根据订单id获取订单详情")
    @GetMapping(value = "/getStoreOrderDetail/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_SELECT','YXEXPRESS_SELECT')")
    public ResponseEntity getYxStoreOrders(@PathVariable Long id) {
        return new ResponseEntity<>(yxStoreOrderService.getOrderDetail(id), HttpStatus.OK);
    }
    @ApiOperation(value = "查询订单当前状态流程")
    @GetMapping(value = "/getNowOrderStatus/{id}")
    public ResponseEntity getNowOrderStatus(@PathVariable Long id) {
        List<String> statusList = new ArrayList<>();
        statusList.add(OrderLogEnum.CREATE_ORDER.getValue());
        statusList.add(OrderLogEnum.PAY_ORDER_SUCCESS.getValue());
        statusList.add(OrderLogEnum.DELIVERY_GOODS.getValue());
        statusList.add(OrderLogEnum.TAKE_ORDER_DELIVERY.getValue());
        statusList.add(OrderLogEnum.EVAL_ORDER.getValue());
        List<YxStoreOrderStatus> orderStatusLogList = yxStoreOrderStatusService.list(new LambdaQueryWrapper<YxStoreOrderStatus>().eq(YxStoreOrderStatus::getOid, id).in(YxStoreOrderStatus::getChangeType, statusList).orderByDesc(YxStoreOrderStatus::getChangeTime));
        List<YxStoreOrderStatusDto> dtoList = getOrderStatusDto(orderStatusLogList);
        YxOrderNowOrderStatusDto yxOrderNowOrderStatusDto = new YxOrderNowOrderStatusDto();
        yxOrderNowOrderStatusDto.setSize(dtoList.size());
        dtoList.forEach(dto -> {
            if (OrderLogEnum.CREATE_ORDER.getDesc().equals(dto.getChangeType())) {
                yxOrderNowOrderStatusDto.setCacheKeyCreateOrder(dto.getChangeTime());
            }
            if (OrderLogEnum.PAY_ORDER_SUCCESS.getDesc().equals(dto.getChangeType())) {
                yxOrderNowOrderStatusDto.setPaySuccess(dto.getChangeTime());
            }
            if (OrderLogEnum.DELIVERY_GOODS.getDesc().equals(dto.getChangeType())) {
                yxOrderNowOrderStatusDto.setDeliveryGoods(dto.getChangeTime());
            }
            if (OrderLogEnum.TAKE_ORDER_DELIVERY.getDesc().equals(dto.getChangeType())) {
                yxOrderNowOrderStatusDto.setUserTakeDelivery(dto.getChangeTime());
                yxOrderNowOrderStatusDto.setOrderVerific(dto.getChangeTime());
            }
            if (OrderLogEnum.EVAL_ORDER.getDesc().equals(dto.getChangeType())) {
                yxOrderNowOrderStatusDto.setCheckOrderOver(dto.getChangeTime());
            }
        });


   statusList = new ArrayList<>();
        statusList.add(OrderLogEnum.REFUND_ORDER_APPLY.getValue());
        statusList.add(OrderLogEnum.REFUND_ORDER_SUCCESS.getValue());
        orderStatusLogList = yxStoreOrderStatusService.list(new LambdaQueryWrapper<YxStoreOrderStatus>().eq(YxStoreOrderStatus::getOid, id).in(YxStoreOrderStatus::getChangeType, statusList).orderByDesc(YxStoreOrderStatus::getChangeTime));
        dtoList = getOrderStatusDto(orderStatusLogList);
        dtoList.forEach(dto -> {
            if (OrderLogEnum.REFUND_ORDER_APPLY.getDesc().equals(dto.getChangeType())) {
                yxOrderNowOrderStatusDto.setApplyRefund(dto.getChangeTime());
            }
            if (OrderLogEnum.REFUND_ORDER_SUCCESS.getDesc().equals(dto.getChangeType())) {
                yxOrderNowOrderStatusDto.setRefundOrderSuccess(dto.getChangeTime());
            }

        });

        return new ResponseEntity(yxOrderNowOrderStatusDto, HttpStatus.OK);
    }
    public List<YxStoreOrderStatusDto> getOrderStatusDto(List<YxStoreOrderStatus> orderStatusLogList) {
        List<YxStoreOrderStatusDto> dtoList = orderStatusLogList.stream().map(log -> {
            YxStoreOrderStatusDto dto = generator.convert(log, YxStoreOrderStatusDto.class);
            dto.setChangeType(OrderLogEnum.getDesc(dto.getChangeType()));
            dto.setChangeTime(log.getChangeTime());
            return dto;
        }).collect(Collectors.toList());
        return dtoList;
    }
    @ApiOperation(value = "发货")
    @PutMapping(value = "/yxStoreOrder")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreOrder resources) {
        if (StrUtil.isBlank(resources.getDeliveryName())) {
            throw new BadRequestException("请选择快递公司");
        }
        if (StrUtil.isBlank(resources.getDeliveryId())) {
            throw new BadRequestException("快递单号不能为空");
        }

        yxStoreOrderService.orderDelivery(resources.getOrderId(),resources.getDeliveryId(),
                resources.getDeliveryName(),resources.getDeliveryType());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "修改快递单号")
    @PutMapping(value = "/yxStoreOrder/updateDelivery")
    @PreAuthorize("hasAnyAuthority('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity updateDelivery(@Validated @RequestBody YxStoreOrder resources) {
        if (StrUtil.isBlank(resources.getDeliveryName())) {
            throw new BadRequestException("请选择快递公司");
        }
        if (StrUtil.isBlank(resources.getDeliveryId())) {
            throw new BadRequestException("快递单号不能为空");
        }

        yxStoreOrderService.updateDelivery(resources.getOrderId(),resources.getDeliveryId(),
                resources.getDeliveryName(),resources.getDeliveryType());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



    @ApiOperation(value = "订单核销")
    @PutMapping(value = "/yxStoreOrder/check")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity check(@Validated @RequestBody YxStoreOrder resources) {
        if (StrUtil.isBlank(resources.getVerifyCode())) {
            throw new BadRequestException("核销码不能为空");
        }
        YxStoreOrderDto storeOrderDTO = generator.convert(yxStoreOrderService.getById(resources.getId()),YxStoreOrderDto.class);
        if(!resources.getVerifyCode().equals(storeOrderDTO.getVerifyCode())){
            throw new BadRequestException("核销码不对");
        }
        if(OrderInfoEnum.PAY_STATUS_0.getValue().equals(storeOrderDTO.getPaid())){
            throw new BadRequestException("订单未支付");
        }

        yxStoreOrderService.verifyOrder(resources.getVerifyCode(),
                OrderInfoEnum.CONFIRM_STATUS_1.getValue(),null);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = "退款")
    @PostMapping(value = "/yxStoreOrder/refund")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity refund(@Validated @RequestBody YxStoreOrder resources) {
        yxStoreOrderService.orderRefund(resources.getOrderId(),resources.getPayPrice(),
                ShopCommonEnum.AGREE_1.getValue(), null);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @ForbidSubmit
    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreOrder/{id}")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_DELETE')")
    public ResponseEntity delete(@PathVariable Long id) {
        yxStoreOrderService.removeById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @Log("修改订单")
    @ApiOperation(value = "修改订单")
    @PostMapping(value = "/yxStoreOrder/edit")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity editOrder(@RequestBody YxStoreOrder resources) {
        if (ObjectUtil.isNull(resources.getPayPrice())) {
            throw new BadRequestException("请输入支付金额");
        }
        if (resources.getPayPrice().doubleValue() < 0) {
            throw new BadRequestException("金额不能低于0");
        }
        YxStoreOrderDto storeOrder = generator.convert(yxStoreOrderService.getById(resources.getId()),YxStoreOrderDto.class);
        //判断金额是否有变动,生成一个额外订单号去支付
        int res = NumberUtil.compare(storeOrder.getPayPrice().doubleValue(), resources.getPayPrice().doubleValue());
        if (res != 0) {
            String orderSn = IdUtil.getSnowflake(0, 0).nextIdStr();
            resources.setExtendOrderId(orderSn);
        }

        yxStoreOrderService.saveOrUpdate(resources);

        yxStoreOrderStatusService.create(resources.getId(),OrderLogEnum.ORDER_EDIT.getValue(),
                "修改订单价格为：" + resources.getPayPrice());
        return new ResponseEntity(HttpStatus.OK);
    }


    @Log("修改订单备注")
    @ApiOperation(value = "修改订单备注")
    @PostMapping(value = "/yxStoreOrder/remark")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity editOrderRemark(@RequestBody YxStoreOrder resources) {
        if (StrUtil.isBlank(resources.getRemark())) {
            throw new BadRequestException("请输入备注");
        }
        yxStoreOrderService.saveOrUpdate(resources);
        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * 快递查询
     */
    @PostMapping("/yxStoreOrder/express")
    @ApiOperation(value = "获取物流信息",notes = "获取物流信息",response = ExpressParam.class)
    public ResponseEntity express( @RequestBody ExpressParam expressInfoDo){

        //顺丰轨迹查询处理
        String lastFourNumber = "";
        if (expressInfoDo.getShipperCode().equals(ShipperCodeEnum.SF.getValue())) {
            YxStoreOrderDto yxStoreOrderDto;
            yxStoreOrderDto = yxStoreOrderService.getOrderDetail(Long.valueOf(expressInfoDo.getOrderCode()));
            lastFourNumber = yxStoreOrderDto.getUserPhone();
            if (lastFourNumber.length()==11) {
                lastFourNumber = StrUtil.sub(lastFourNumber,lastFourNumber.length(),-4);
            }
        }

        ExpressService expressService = ExpressAutoConfiguration.expressService();
        ExpressInfo expressInfo = expressService.getExpressInfo(expressInfoDo.getOrderCode(),
                expressInfoDo.getShipperCode(), expressInfoDo.getLogisticCode(),lastFourNumber);
        List<Traces> expressInfoTraces = expressInfo.getTraces();
        // 根据时间倒序
        expressInfoTraces.sort((o1, o2) -> o2.getAcceptTime().compareTo(o1.getAcceptTime()));
        if(!expressInfo.isSuccess()) {
            throw new BadRequestException(expressInfo.getReason());
        }
        return new ResponseEntity<>(expressInfo, HttpStatus.OK);
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/yxStoreOrder/download")
    @PreAuthorize("hasAnyRole('admin','YXSTOREORDER_SELECT')")
    public void download(HttpServletResponse response,
                         YxStoreOrderQueryCriteria criteria,
                         Pageable pageable,
                         @RequestParam(name = "orderStatus") String orderStatus,
                         @RequestParam(name = "orderType") String orderType,
                         @RequestParam(name = "listContent") String listContent) throws IOException, ParseException {
        List<YxStoreOrderDto> list;
        if(StringUtils.isEmpty(listContent)){
            list =  (List)getYxStoreList(criteria, pageable, orderStatus, orderType).get("content");
        }else {
            List<String> idList = JSONArray.parseArray(listContent).toJavaList(String.class);
            list = (List)yxStoreOrderService.queryAll(idList).get("content");
        }
        yxStoreOrderService.download(list, response);
    }

    /**
     * 下载数据
     * @param criteria criteria
     * @param pageable pageable
     * @param orderStatus orderStatus
     * @param orderType orderType
     * @return Map
     */
    private Map<String,Object> getYxStoreList(YxStoreOrderQueryCriteria criteria,
                                             Pageable pageable,
                                             String orderStatus,
                                             String orderType){

        YxStoreOrderQueryCriteria newCriteria = this.handleQuery(criteria,orderStatus,orderType);
        return yxStoreOrderService.queryAll(newCriteria, pageable);
    }



    /**
     * 处理订单查询
     * @param criteria YxStoreOrderQueryCriteria
     * @param orderStatus 订单状态
     * @param orderType 订单类型
     * @return YxStoreOrderQueryCriteria
     */
    private YxStoreOrderQueryCriteria handleQuery(YxStoreOrderQueryCriteria criteria,String orderStatus,
                                                  String orderType){

        //默认查询所有快递订单
        criteria.setShippingType(OrderInfoEnum.SHIPPIING_TYPE_1.getValue());
        //订单状态查询
        if (StrUtil.isNotEmpty(orderStatus)) {
            switch (orderStatus) {
                case "0":
                    criteria.setPaid(OrderInfoEnum.PAY_STATUS_0.getValue());
                    criteria.setStatus(OrderInfoEnum.STATUS_0.getValue());
                    criteria.setRefundStatus(OrderInfoEnum.REFUND_STATUS_0.getValue());
                    break;
                case "1":
                    criteria.setPaid(OrderInfoEnum.PAY_STATUS_1.getValue());
                    criteria.setStatus(OrderInfoEnum.STATUS_0.getValue());
                    criteria.setRefundStatus(OrderInfoEnum.REFUND_STATUS_0.getValue());
                    break;
                case "2":
                    criteria.setPaid(OrderInfoEnum.PAY_STATUS_1.getValue());
                    criteria.setStatus(OrderInfoEnum.STATUS_1.getValue());
                    criteria.setRefundStatus(OrderInfoEnum.REFUND_STATUS_0.getValue());
                    break;
                case "3":
                    criteria.setPaid(OrderInfoEnum.PAY_STATUS_1.getValue());
                    criteria.setStatus(OrderInfoEnum.STATUS_2.getValue());
                    criteria.setRefundStatus(OrderInfoEnum.REFUND_STATUS_0.getValue());
                    break;
                case "4":
                    criteria.setPaid(OrderInfoEnum.PAY_STATUS_1.getValue());
                    criteria.setStatus(OrderInfoEnum.STATUS_3.getValue());
                    criteria.setRefundStatus(OrderInfoEnum.REFUND_STATUS_0.getValue());
                    break;
                case "-1":
                    criteria.setPaid(OrderInfoEnum.PAY_STATUS_1.getValue());
                    criteria.setRefundStatus(OrderInfoEnum.REFUND_STATUS_1.getValue());
                    break;
                case "-2":
                    criteria.setPaid(OrderInfoEnum.PAY_STATUS_1.getValue());
                    criteria.setRefundStatus(OrderInfoEnum.REFUND_STATUS_2.getValue());
                    break;
                default:
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
                case "6":
                    criteria.setPayIntegral(new BigDecimal("0.00"));
                    break;
                default:
            }
        }

        return criteria;
    }



}
