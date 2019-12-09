package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.domain.YxStoreOrderStatus;
import co.yixiang.modules.shop.service.YxStoreOrderService;
import co.yixiang.aop.log.Log;
import co.yixiang.modules.shop.service.YxStoreOrderStatusService;
import co.yixiang.modules.shop.service.dto.YxStoreOrderQueryCriteria;
import co.yixiang.modules.system.service.DictDetailService;
import co.yixiang.modules.system.service.dto.DictDetailDTO;
import co.yixiang.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author hupeng
* @date 2019-10-14
*/
@Api(tags = "订单管理")
@RestController
@RequestMapping("api")
public class YxStoreOrderController {

    @Autowired
    private YxStoreOrderService yxStoreOrderService;

    @Autowired
    private YxStoreOrderStatusService yxStoreOrderStatusService;

    @Autowired
    private DictDetailService dictDetailService;

    @GetMapping(value = "/data/count")
    //@PreAuthorize("hasAnyRole('ADMIN','YXSTOREORDER_ALL','YXSTOREORDER_SELECT')")
    public ResponseEntity getCount(){
        return new ResponseEntity(yxStoreOrderService.getOrderTimeData(),HttpStatus.OK);
    }

    @GetMapping(value = "/data/chart")
    //@PreAuthorize("hasAnyRole('ADMIN','YXSTOREORDER_ALL','YXSTOREORDER_SELECT')")
    public ResponseEntity getChart(){
        return new ResponseEntity(yxStoreOrderService.chartCount(),HttpStatus.OK);
    }



    @ApiOperation(value = "查询订单")
    @GetMapping(value = "/yxStoreOrder")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREORDER_ALL','YXSTOREORDER_SELECT')")
    public ResponseEntity getYxStoreOrders(YxStoreOrderQueryCriteria criteria,
                                           Pageable pageable,
                                           @RequestParam(name = "orderStatus") String orderStatus){


        if(StrUtil.isNotEmpty(orderStatus)){
            switch (orderStatus){
                case "0":
                    criteria.setIsDel(0);
                    criteria.setPaid(0);
                    criteria.setStatus(0);
                    criteria.setRefundStatus(0);
                    break;
                case "1":
                    System.out.println(orderStatus);
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

        return new ResponseEntity(yxStoreOrderService.queryAll(criteria,pageable),HttpStatus.OK);
    }




    @ApiOperation(value = "发货")
    @PutMapping(value = "/yxStoreOrder")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreOrder resources){

        DictDetailDTO dictDetailDTO = dictDetailService.findById(Long.valueOf(resources
                .getDeliveryName()));
        if(ObjectUtil.isNull(dictDetailDTO)){
            throw new BadRequestException("请先添加快递公司");
        }
        resources.setStatus(1);
        resources.setDeliveryType("express");
        resources.setDeliveryName(dictDetailDTO.getLabel());
        resources.setDeliverySn(dictDetailDTO.getValue());

        yxStoreOrderService.update(resources);

        YxStoreOrderStatus storeOrderStatus = new YxStoreOrderStatus();
        storeOrderStatus.setOid(resources.getId());
        storeOrderStatus.setChangeType("delivery_goods");
        storeOrderStatus.setChangeMessage("已发货 快递公司："+resources.getDeliveryName()
                +" 快递单号："+resources.getDeliveryId());
        storeOrderStatus.setChangeTime(OrderUtil.getSecondTimestampTwo());

        yxStoreOrderStatusService.create(storeOrderStatus);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = "退款")
    @PostMapping(value = "/yxStoreOrder/refund")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREORDER_ALL','YXSTOREORDER_EDIT')")
    public ResponseEntity refund(@Validated @RequestBody YxStoreOrder resources){
        yxStoreOrderService.refund(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @Log("删除")
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/yxStoreOrder/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREORDER_ALL','YXSTOREORDER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxStoreOrderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}