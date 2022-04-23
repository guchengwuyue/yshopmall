/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.exception.EntityExistException;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.shop.domain.*;
import co.yixiang.modules.shop.service.*;
import co.yixiang.modules.shop.service.dto.*;
import co.yixiang.modules.shop.service.mapper.StoreOrderMapper;
import co.yixiang.modules.shop.service.mapper.StoreProductMapper;
import co.yixiang.modules.shop.service.mapper.UserMapper;
import co.yixiang.modules.mp.service.YxMiniPayService;
import co.yixiang.modules.mp.service.YxPayService;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.OrderUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Slf4j
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxStoreOrder")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreOrderServiceImpl extends BaseServiceImpl<StoreOrderMapper, YxStoreOrder> implements YxStoreOrderService {

    private final IGenerator generator;
    private YxUserService userService;
    private UserMapper userMapper;
    private YxStorePinkService storePinkService;
    private YxStoreOrderCartInfoService storeOrderCartInfoService;
    private final YxUserBillService yxUserBillService;
    private final YxStoreOrderStatusService yxStoreOrderStatusService;
    private final YxPayService payService;
    private final YxMiniPayService miniPayService;
    private final YxSystemStoreService systemStoreService;
    private final YxStoreCartService storeCartService;
    private final StoreOrderMapper yxStoreOrderMapper;
    private final StoreProductMapper yxStoreProductMapper;

    @Override
    public OrderCountDto getOrderCount() {
        //获取所有订单转态为已支付的
        List<CountDto> nameList = storeCartService.findCateName();
        Map<String, Integer> childrenMap = new HashMap<>();
        nameList.forEach(i -> {
            if (i != null) {
                if (childrenMap.containsKey(i.getCatename())) {
                    childrenMap.put(i.getCatename(), childrenMap.get(i.getCatename()) + 1);
                } else {
                    childrenMap.put(i.getCatename(), 1);
                }
            }

        });
        List<OrderCountDto.OrderCountData> list = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        childrenMap.forEach((k, v) -> {
            OrderCountDto.OrderCountData orderCountData = new OrderCountDto.OrderCountData();
            orderCountData.setName(k);
            orderCountData.setValue(v);
            columns.add(k);
            list.add(orderCountData);
        });
        OrderCountDto orderCountDto = new OrderCountDto();
        orderCountDto.setColumn(columns);
        orderCountDto.setOrderCountDatas(list);
        return orderCountDto;
    }

    @Override
    // 此处方法已经废弃
    public OrderTimeDataDto getOrderTimeData() {
        int today = OrderUtil.dateToTimestampT(DateUtil.beginOfDay(new Date()));
        int yesterday = OrderUtil.dateToTimestampT(DateUtil.beginOfDay(DateUtil.
                yesterday()));
        int lastWeek = OrderUtil.dateToTimestampT(DateUtil.beginOfDay(DateUtil.lastWeek()));
        int nowMonth = OrderUtil.dateToTimestampT(DateUtil
                .beginOfMonth(new Date()));
        OrderTimeDataDto orderTimeDataDTO = new OrderTimeDataDto();

        orderTimeDataDTO.setTodayCount(yxStoreOrderMapper.countByPayTimeGreaterThanEqual(today));


        orderTimeDataDTO.setProCount(yxStoreOrderMapper
                .countByPayTimeLessThanAndPayTimeGreaterThanEqual(today, yesterday));


        orderTimeDataDTO.setLastWeekCount(yxStoreOrderMapper.countByPayTimeGreaterThanEqual(lastWeek));


        orderTimeDataDTO.setMonthCount(yxStoreOrderMapper.countByPayTimeGreaterThanEqual(nowMonth));


        orderTimeDataDTO.setUserCount(userMapper.selectCount(new LambdaQueryWrapper<YxUser>()));
        orderTimeDataDTO.setOrderCount(yxStoreOrderMapper.selectCount(new LambdaQueryWrapper<YxStoreOrder>()));
        orderTimeDataDTO.setPriceCount(yxStoreOrderMapper.sumTotalPrice());
        orderTimeDataDTO.setGoodsCount(yxStoreProductMapper.selectCount(new LambdaQueryWrapper<YxStoreProduct>()));

        return orderTimeDataDTO;
    }

    @Override
    public Map<String, Object> chartCount() {
        Map<String, Object> map = new LinkedHashMap<>();
        int nowMonth = OrderUtil.dateToTimestampT(DateUtil
                .beginOfMonth(new Date()));

        map.put("chart", yxStoreOrderMapper.chartList(nowMonth));
        map.put("chartT", yxStoreOrderMapper.chartListT(nowMonth));

        return map;
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreOrderQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreOrder> page = new PageInfo<>(queryAll(criteria));
        List<YxStoreOrderDto> storeOrderDTOS = new ArrayList<>();
        for (YxStoreOrder yxStoreOrder : page.getList()) {
            orderList(storeOrderDTOS, yxStoreOrder);

        }
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", storeOrderDTOS);
        map.put("totalElements", page.getTotal());
        return map;
    }

    /**
     * 代码提取
     * @param storeOrderDTOS
     * @param yxStoreOrder
     */
    private void orderList(List<YxStoreOrderDto> storeOrderDTOS, YxStoreOrder yxStoreOrder) {
        YxStoreOrderDto yxStoreOrderDto = generator.convert(yxStoreOrder, YxStoreOrderDto.class);
        Integer _status = OrderUtil.orderStatus(yxStoreOrder.getPaid(), yxStoreOrder.getStatus(),
                yxStoreOrder.getRefundStatus());

        if (yxStoreOrder.getStoreId() > 0) {
            YxSystemStore systemStore = systemStoreService.getById(yxStoreOrder.getStoreId());
            if (Objects.nonNull(systemStore)) {
                String storeName = systemStore.getName();
                yxStoreOrderDto.setStoreName(storeName);
            }
        }

        //订单状态
        String orderStatusStr = OrderUtil.orderStatusStr(yxStoreOrder.getPaid()
                , yxStoreOrder.getStatus(), yxStoreOrder.getShippingType()
                , yxStoreOrder.getRefundStatus());

        if (_status == 3) {
            String refundTime = OrderUtil.stampToDate(String.valueOf(yxStoreOrder
                    .getRefundReasonTime()));
            String str = "<b style='color:#f124c7'>申请退款</b><br/>" +
                    "<span>退款原因：" + yxStoreOrder.getRefundReasonWap() + "</span><br/>" +
                    "<span>备注说明：" + yxStoreOrder.getRefundReasonWapExplain() + "</span><br/>" +
                    "<span>退款时间：" + refundTime + "</span><br/>";
            orderStatusStr = str;
        }
        yxStoreOrderDto.setStatusName(orderStatusStr);

        yxStoreOrderDto.set_status(_status);

        String payTypeName = OrderUtil.payTypeName(yxStoreOrder.getPayType()
                , yxStoreOrder.getPaid());
        yxStoreOrderDto.setPayTypeName(payTypeName);

        yxStoreOrderDto.setPinkName(orderType(yxStoreOrder.getId()
                , yxStoreOrder.getPinkId(), yxStoreOrder.getCombinationId()
                , yxStoreOrder.getSeckillId(), yxStoreOrder.getBargainId(),
                yxStoreOrder.getShippingType()));

        List<YxStoreOrderCartInfo> cartInfos = storeOrderCartInfoService.list(
                new LambdaQueryWrapper<YxStoreOrderCartInfo>().eq(YxStoreOrderCartInfo::getOid, yxStoreOrder.getId()));
        List<StoreOrderCartInfoDto> cartInfoDTOS = new ArrayList<>();
        for (YxStoreOrderCartInfo cartInfo : cartInfos) {
            StoreOrderCartInfoDto cartInfoDTO = new StoreOrderCartInfoDto();
            cartInfoDTO.setCartInfoMap(JSON.parseObject(cartInfo.getCartInfo()));

            cartInfoDTOS.add(cartInfoDTO);
        }
        yxStoreOrderDto.setCartInfoList(cartInfoDTOS);
        yxStoreOrderDto.setUserDTO(generator.convert(userService.getById(yxStoreOrder.getUid()), YxUserDto.class));
        if (yxStoreOrderDto.getUserDTO() == null) {
            yxStoreOrderDto.setUserDTO(new YxUserDto());
        }
        storeOrderDTOS.add(yxStoreOrderDto);
    }


    @Override
    //@Cacheable
    public List<YxStoreOrder> queryAll(YxStoreOrderQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreOrder.class, criteria));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreOrderDto create(YxStoreOrder resources) {
        if (this.getOne(new LambdaQueryWrapper<YxStoreOrder>().eq(YxStoreOrder::getUnique, resources.getUnique())) != null) {
            throw new EntityExistException(YxStoreOrder.class, "unique", resources.getUnique());
        }
        this.save(resources);
        return generator.convert(resources, YxStoreOrderDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreOrder resources) {
        YxStoreOrder yxStoreOrder = this.getById(resources.getId());
        YxStoreOrder yxStoreOrder1 = this.getOne(new LambdaQueryWrapper<YxStoreOrder>().eq(YxStoreOrder::getUnique, resources.getUnique()));
        if (yxStoreOrder1 != null && !yxStoreOrder1.getId().equals(yxStoreOrder.getId())) {
            throw new EntityExistException(YxStoreOrder.class, "unique", resources.getUnique());
        }
        yxStoreOrder.copy(resources);
        this.saveOrUpdate(yxStoreOrder);
    }


    @Override
    public void download(List<YxStoreOrderDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreOrderDto yxStoreOrder : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("订单号", yxStoreOrder.getOrderId());
            map.put("用户id", yxStoreOrder.getUid());
            map.put("用户姓名", yxStoreOrder.getRealName());
            map.put("用户电话", yxStoreOrder.getUserPhone());
            map.put("详细地址", yxStoreOrder.getUserAddress());
            map.put("购物车id", yxStoreOrder.getCartId());
            map.put("运费金额", yxStoreOrder.getFreightPrice());
            map.put("订单商品总数", yxStoreOrder.getTotalNum());
            map.put("订单总价", yxStoreOrder.getTotalPrice());
            map.put("邮费", yxStoreOrder.getTotalPostage());
            map.put("实际支付金额", yxStoreOrder.getPayPrice());
            map.put("支付邮费", yxStoreOrder.getPayPostage());
            map.put("抵扣金额", yxStoreOrder.getDeductionPrice());
            map.put("优惠券id", yxStoreOrder.getCouponId());
            map.put("优惠券金额", yxStoreOrder.getCouponPrice());
            map.put("支付状态", yxStoreOrder.getPaid());
            map.put("支付时间", yxStoreOrder.getPayTime());
            map.put("支付方式", yxStoreOrder.getPayType());
            map.put("订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：待评价；-1：已退款）", yxStoreOrder.getStatus());
            map.put("0 未退款 1 申请中 2 已退款", yxStoreOrder.getRefundStatus());
            map.put("退款图片", yxStoreOrder.getRefundReasonWapImg());
            map.put("退款用户说明", yxStoreOrder.getRefundReasonWapExplain());
            map.put("退款时间", yxStoreOrder.getRefundReasonTime());
            map.put("前台退款原因", yxStoreOrder.getRefundReasonWap());
            map.put("不退款的理由", yxStoreOrder.getRefundReason());
            map.put("退款金额", yxStoreOrder.getRefundPrice());
            map.put("快递公司编号", yxStoreOrder.getDeliverySn());
            map.put("快递名称/送货人姓名", yxStoreOrder.getDeliveryName());
            map.put("发货类型", yxStoreOrder.getDeliveryType());
            map.put("快递单号/手机号", yxStoreOrder.getDeliveryId());
            map.put("消费赚取积分", yxStoreOrder.getGainIntegral());
            map.put("使用积分", yxStoreOrder.getUseIntegral());
            map.put("给用户退了多少积分", yxStoreOrder.getBackIntegral());
            map.put("备注", yxStoreOrder.getMark());
            map.put("管理员备注", yxStoreOrder.getRemark());
            map.put("拼团产品id0一般产品", yxStoreOrder.getCombinationId());
            map.put("拼团id 0没有拼团", yxStoreOrder.getPinkId());
            map.put("成本价", yxStoreOrder.getCost());
            map.put("秒杀产品ID", yxStoreOrder.getSeckillId());
            map.put("砍价id", yxStoreOrder.getBargainId());
            map.put("核销码", yxStoreOrder.getVerifyCode());
            map.put("门店id", yxStoreOrder.getStoreId());
            map.put("配送方式 1=快递 ，2=门店自提", yxStoreOrder.getShippingType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> queryAll(List<String> ids) {
        List<YxStoreOrder> yxStoreOrders = this.list(new LambdaQueryWrapper<YxStoreOrder>().in(YxStoreOrder::getOrderId, ids));
        List<YxStoreOrderDto> storeOrderDTOS = new ArrayList<>();
        for (YxStoreOrder yxStoreOrder : yxStoreOrders) {
            YxStoreOrderDto yxStoreOrderDto = generator.convert(yxStoreOrder, YxStoreOrderDto.class);

            Integer _status = OrderUtil.orderStatus(yxStoreOrder.getPaid(), yxStoreOrder.getStatus(),
                    yxStoreOrder.getRefundStatus());

            if (yxStoreOrder.getStoreId() > 0) {
                String storeName = systemStoreService.getById(yxStoreOrder.getStoreId()).getName();
                yxStoreOrderDto.setStoreName(storeName);
            }

            //订单状态
            String orderStatusStr = OrderUtil.orderStatusStr(yxStoreOrder.getPaid()
                    , yxStoreOrder.getStatus(), yxStoreOrder.getShippingType()
                    , yxStoreOrder.getRefundStatus());

            if (_status == 3) {
                String refundTime = OrderUtil.stampToDate(String.valueOf(yxStoreOrder
                        .getRefundReasonTime()));
                String str = "<b style='color:#f124c7'>申请退款</b><br/>" +
                        "<span>退款原因：" + yxStoreOrder.getRefundReasonWap() + "</span><br/>" +
                        "<span>备注说明：" + yxStoreOrder.getRefundReasonWapExplain() + "</span><br/>" +
                        "<span>退款时间：" + refundTime + "</span><br/>";
                orderStatusStr = str;
            }
            yxStoreOrderDto.setStatusName(orderStatusStr);

            yxStoreOrderDto.set_status(_status);

            String payTypeName = OrderUtil.payTypeName(yxStoreOrder.getPayType()
                    , yxStoreOrder.getPaid());
            yxStoreOrderDto.setPayTypeName(payTypeName);

            yxStoreOrderDto.setPinkName(orderType(yxStoreOrder.getId()
                    , yxStoreOrder.getPinkId(), yxStoreOrder.getCombinationId()
                    , yxStoreOrder.getSeckillId(), yxStoreOrder.getBargainId(),
                    yxStoreOrder.getShippingType()));

            List<YxStoreOrderCartInfo> cartInfos = storeOrderCartInfoService.list(new LambdaQueryWrapper<YxStoreOrderCartInfo>().eq(YxStoreOrderCartInfo::getOid, yxStoreOrder.getId()));
            List<StoreOrderCartInfoDto> cartInfoDTOS = new ArrayList<>();
            for (YxStoreOrderCartInfo cartInfo : cartInfos) {
                StoreOrderCartInfoDto cartInfoDTO = new StoreOrderCartInfoDto();
                cartInfoDTO.setCartInfoMap(JSON.parseObject(cartInfo.getCartInfo()));

                cartInfoDTOS.add(cartInfoDTO);
            }
            yxStoreOrderDto.setCartInfoList(cartInfoDTOS);
            yxStoreOrderDto.setUserDTO(generator.convert(userService.getOne(new LambdaQueryWrapper<YxUser>().eq(YxUser::getUid, yxStoreOrder.getUid())), YxUserDto.class));

            storeOrderDTOS.add(yxStoreOrderDto);

        }

        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", storeOrderDTOS);

        return map;
    }

    @Override
    public String orderType(Long id, Long pinkId, Long combinationId, Long seckillId,
                            Long bargainId, int shippingType) {
        String str = "[普通订单]";
        if ( pinkId > 0 || combinationId > 0) {
            YxStorePink storePink = storePinkService.getOne(new LambdaQueryWrapper<YxStorePink>().
                    eq(YxStorePink::getOrderIdKey, id));
            if (ObjectUtil.isNull(storePink)) {
                str = "[拼团订单]";
            } else {
                switch (storePink.getStatus()) {
                    case 1:
                        str = "[拼团订单]正在进行中";
                        break;
                    case 2:
                        str = "[拼团订单]已完成";
                        break;
                    case 3:
                        str = "[拼团订单]未完成";
                        break;
                    default:
                        str = "[拼团订单]历史订单";
                        break;
                }
            }

        } else if (seckillId > 0) {
            str = "[秒杀订单]";
        } else if (bargainId > 0) {
            str = "[砍价订单]";
        }
        if (shippingType == 2) {
            str = "[核销订单]";
        }
        return str;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refund(YxStoreOrder resources) {
        if (resources.getPayPrice().doubleValue() < 0) {
            throw new BadRequestException("请输入退款金额");
        }

        if ("yue".equals(resources.getPayType())) {
            //修改状态
            resources.setRefundStatus(2);
            resources.setRefundPrice(resources.getPayPrice());
            this.updateById(resources);

            //退款到余额
            YxUserDto userDTO = generator.convert(userService.getOne(new LambdaQueryWrapper<YxUser>().eq(YxUser::getUid, resources.getUid())), YxUserDto.class);
            userMapper.updateMoney(resources.getPayPrice().doubleValue(),
                    resources.getUid());

            YxUserBill userBill = new YxUserBill();
            userBill.setUid(resources.getUid());

            userBill.setLinkId(resources.getId().toString());
            userBill.setPm(1);
            userBill.setTitle("商品退款");
            userBill.setCategory("now_money");
            userBill.setType("pay_product_refund");
            userBill.setNumber(resources.getPayPrice());
            userBill.setBalance(NumberUtil.add(resources.getPayPrice(), userDTO.getNowMoney()));
            userBill.setMark("订单退款到余额");
            userBill.setStatus(1);
            yxUserBillService.save(userBill);


            YxStoreOrderStatus storeOrderStatus = new YxStoreOrderStatus();
            storeOrderStatus.setOid(resources.getId());
            storeOrderStatus.setChangeType("refund_price");
            storeOrderStatus.setChangeMessage("退款给用户：" + resources.getPayPrice() + "元");
            storeOrderStatus.setChangeTime(new Date());

            yxStoreOrderStatusService.save(storeOrderStatus);
        } else {
            BigDecimal bigDecimal = new BigDecimal("100");
            try {
                payService.refundOrder(resources.getOrderId(),
                        bigDecimal.multiply(resources.getPayPrice()).intValue());

            } catch (WxPayException e) {
                log.info("refund-error:{}", e.getMessage());
                throw new BadRequestException("退款失败:" + e.getMessage());
            }

        }
    }

}
