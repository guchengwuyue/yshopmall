package co.yixiang.modules.sales.service.impl;

import cn.hutool.core.util.NumberUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.constant.ShopConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.AfterSalesStatusEnum;
import co.yixiang.enums.OrderInfoEnum;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.exception.ErrorRequestException;
import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import co.yixiang.modules.order.domain.YxStoreOrder;
import co.yixiang.modules.order.domain.YxStoreOrderCartInfo;
import co.yixiang.modules.order.service.mapper.StoreOrderCartInfoMapper;
import co.yixiang.modules.order.service.mapper.StoreOrderMapper;
import co.yixiang.modules.sales.domain.StoreAfterSales;
import co.yixiang.modules.sales.domain.StoreAfterSalesItem;
import co.yixiang.modules.sales.domain.StoreAfterSalesStatus;
import co.yixiang.modules.sales.param.ProsuctParam;
import co.yixiang.modules.sales.param.StoreAfterSalesParam;
import co.yixiang.modules.sales.param.YxStoreAfterSalesDto;
import co.yixiang.modules.sales.param.YxStoreAfterSalesQueryCriteria;
import co.yixiang.modules.sales.service.StoreAfterSalesService;
import co.yixiang.modules.sales.service.mapper.StoreAfterSalesItemMapper;
import co.yixiang.modules.sales.service.mapper.StoreAfterSalesMapper;
import co.yixiang.modules.sales.service.mapper.StoreAfterSalesStatusMapper;
import co.yixiang.modules.sales.service.vo.StoreAfterSalesVo;
import co.yixiang.modules.sales.service.vo.YxStoreOrderCartInfoVo;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.RedisUtils;
import co.yixiang.utils.SecurityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : gzlv 2021/6/27 15:56
 */
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StoreAfterSalesServiceImpl extends BaseServiceImpl<StoreAfterSalesMapper, StoreAfterSales> implements StoreAfterSalesService {

    private final StoreOrderMapper storeOrderMapper;
    private final StoreOrderCartInfoMapper storeOrderCartInfoMapper;
    private final StoreAfterSalesItemMapper storeAfterSalesItemMapper;
    private final StoreAfterSalesStatusMapper storeAfterSalesStatusMapper;
    private final IGenerator generator;
    private final RedisUtils redisUtils;

    @Override
    public void applyForAfterSales(Long userId, String nickname, StoreAfterSalesParam storeAfterSalesParam) {

        YxStoreOrder yxStoreOrder = storeOrderMapper.selectOne(Wrappers.<YxStoreOrder>lambdaQuery().eq(YxStoreOrder::getOrderId, storeAfterSalesParam.getOrderCode()).eq(YxStoreOrder::getUid, userId));
        redisUtils.set(ShopConstants.YSHOP_ORDER_SALE_STATUS_KEY + userId + storeAfterSalesParam.getOrderCode(),
                yxStoreOrder.getStatus());
        checkOrder(yxStoreOrder);
        //商品除去优惠后的总价格
        BigDecimal totalPrice = BigDecimal.ZERO;
        //拿到所有的商品
        List<YxStoreOrderCartInfo> yxStoreOrderCartInfos = storeOrderCartInfoMapper.selectList(Wrappers.<YxStoreOrderCartInfo>lambdaQuery().eq(YxStoreOrderCartInfo::getOid, yxStoreOrder.getId()));
        for (YxStoreOrderCartInfo yxStoreOrderCartInfo : yxStoreOrderCartInfos) {
            YxStoreCartQueryVo cartInfo = JSONObject.parseObject(yxStoreOrderCartInfo.getCartInfo(), YxStoreCartQueryVo.class);
            ProsuctParam prosuctParam = storeAfterSalesParam.getProductParamList().stream().filter(item -> item.getProductId().equals(yxStoreOrderCartInfo.getProductId())).findFirst().orElse(new ProsuctParam());
            if (prosuctParam.getProductId() != null) {
                //商品优惠前总金额
                BigDecimal totalAmountOfGoods = NumberUtil.mul(cartInfo.getTruePrice(), cartInfo.getCartNum());
                //商品优惠总金额
                // BigDecimal commodityDiscountAmount = NumberUtil.mul(NumberUtil.div(totalAmountOfGoods, NumberUtil.sub(yxStoreOrder.getTotalPrice(), yxStoreOrder.getPayPostage())), yxStoreOrder.getCouponPrice());
                BigDecimal totalPriceMinusPostage = NumberUtil.sub(yxStoreOrder.getTotalPrice(), yxStoreOrder.getPayPostage());
                BigDecimal commodityDiscountAmount;

                if (totalPriceMinusPostage.compareTo(BigDecimal.ZERO) != 0) {
                    commodityDiscountAmount = NumberUtil.mul(NumberUtil.div(totalAmountOfGoods, totalPriceMinusPostage), yxStoreOrder.getCouponPrice());
                } else {
                    // 处理除数为零的情况，返回默认值
                    commodityDiscountAmount = BigDecimal.ZERO;
                }

                //商品优惠后总金额
                totalPrice = NumberUtil.add(totalPrice, NumberUtil.sub(totalAmountOfGoods, commodityDiscountAmount));
                // todo 根据自己的业务逻辑修改，是否退运费，默认退运费
                totalPrice = NumberUtil.add(totalPrice, yxStoreOrder.getPayPostage());
                yxStoreOrderCartInfo.setIsAfterSales(0);
                storeOrderCartInfoMapper.updateById(yxStoreOrderCartInfo);

            }
        }
        //更新订单状态
        yxStoreOrder.setStatus(OrderInfoEnum.STATUS_NE1.getValue());
        yxStoreOrder.setRefundStatus(OrderInfoEnum.REFUND_STATUS_1.getValue());
        yxStoreOrder.setRefundReasonWap(storeAfterSalesParam.getReasonForApplication());
        yxStoreOrder.setRefundReasonWapExplain(storeAfterSalesParam.getApplicationInstructions());
        yxStoreOrder.setRefundReasonTime(new Date());
        storeOrderMapper.updateById(yxStoreOrder);
        //生成售后订单
        StoreAfterSales storeAfterSales = new StoreAfterSales();
        storeAfterSales.setOrderCode(storeAfterSalesParam.getOrderCode());
        //此处需要对比原来订单的支付价格
        if(totalPrice.compareTo(yxStoreOrder.getPayPrice()) > 0){
            storeAfterSales.setRefundAmount(yxStoreOrder.getPayPrice());
        }else{
            storeAfterSales.setRefundAmount(totalPrice);
        }
        storeAfterSales.setServiceType(storeAfterSalesParam.getServiceType());
        storeAfterSales.setReasons(storeAfterSalesParam.getReasonForApplication());
        storeAfterSales.setExplains(storeAfterSalesParam.getApplicationInstructions());
        storeAfterSales.setExplainImg(storeAfterSalesParam.getApplicationDescriptionPicture());
        storeAfterSales.setState(AfterSalesStatusEnum.STATUS_0.getValue());
        storeAfterSales.setSalesState(0);
        storeAfterSales.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        storeAfterSales.setIsDel(0);
        storeAfterSales.setUserId(userId);
        baseMapper.insert(storeAfterSales);
        //售后商品详情
        for (ProsuctParam prosuctParam : storeAfterSalesParam.getProductParamList()) {
            YxStoreOrderCartInfo yxStoreOrderCartInfo = yxStoreOrderCartInfos.stream().filter(item -> item.getProductId().equals(prosuctParam.getProductId())).findFirst().orElse(new YxStoreOrderCartInfo());
            StoreAfterSalesItem storeAfterSalesItem = new StoreAfterSalesItem();
            storeAfterSalesItem.setStoreAfterSalesId(storeAfterSales.getId());
            storeAfterSalesItem.setProductId(yxStoreOrderCartInfo.getProductId());
            storeAfterSalesItem.setCartInfo(yxStoreOrderCartInfo.getCartInfo());
            storeAfterSalesItem.setIsDel(0);
            storeAfterSalesItemMapper.insert(storeAfterSalesItem);
        }

        //操作记录
        StoreAfterSalesStatus storeAfterSalesStatus = new StoreAfterSalesStatus();
        storeAfterSalesStatus.setStoreAfterSalesId(storeAfterSales.getId());
        storeAfterSalesStatus.setChangeType(0);
        storeAfterSalesStatus.setChangeMessage("售后订单生成");
        storeAfterSalesStatus.setChangeTime(Timestamp.valueOf(LocalDateTime.now()));
        storeAfterSalesStatus.setOperator(nickname);
        storeAfterSalesStatusMapper.insert(storeAfterSalesStatus);

    }

    @Override
    public List<YxStoreOrderCartInfoVo> checkOrderDetails(String key) {
        List<YxStoreOrderCartInfo> yxStoreOrderCartInfos = storeOrderCartInfoMapper.selectList(Wrappers.<YxStoreOrderCartInfo>lambdaQuery().eq(YxStoreOrderCartInfo::getOid, key));
        YxStoreOrder yxStoreOrder = storeOrderMapper.selectById(key);
        //查询 售后信息
        StoreAfterSales storeAfterSales = baseMapper.selectOne(Wrappers.<StoreAfterSales>lambdaQuery().eq(StoreAfterSales::getOrderCode, yxStoreOrder.getOrderId()));
        List<YxStoreOrderCartInfoVo> yxStoreOrderCartInfoVos = new ArrayList<>();
        for (YxStoreOrderCartInfo yxStoreOrderCartInfo : yxStoreOrderCartInfos) {

            YxStoreOrderCartInfoVo yxStoreOrderCartInfoVo = new YxStoreOrderCartInfoVo();
            yxStoreOrderCartInfoVo.setId(yxStoreOrderCartInfo.getId());
            yxStoreOrderCartInfoVo.setOid(yxStoreOrderCartInfo.getOid());
            yxStoreOrderCartInfoVo.setCartId(yxStoreOrderCartInfo.getCartId());
            yxStoreOrderCartInfoVo.setProductId(yxStoreOrderCartInfo.getProductId());
            YxStoreCartQueryVo cartInfo = JSONObject.parseObject(yxStoreOrderCartInfo.getCartInfo(), YxStoreCartQueryVo.class);
            yxStoreOrderCartInfoVo.setCartInfo(cartInfo);
            yxStoreOrderCartInfoVo.setUnique(yxStoreOrderCartInfo.getUnique());
            yxStoreOrderCartInfoVo.setIsAfterSales(yxStoreOrderCartInfo.getIsAfterSales() == null ? 0 : yxStoreOrderCartInfo.getIsAfterSales());

            //商品优惠前总金额
            BigDecimal totalAmountOfGoods = NumberUtil.mul(cartInfo.getTruePrice(), cartInfo.getCartNum());
            //商品优惠总金额
            BigDecimal commodityDiscountAmount = NumberUtil.mul(NumberUtil.div(totalAmountOfGoods, NumberUtil.sub(yxStoreOrder.getTotalPrice(), yxStoreOrder.getPayPostage())), yxStoreOrder.getCouponPrice());

            yxStoreOrderCartInfoVo.setRefundablePrice(NumberUtil.sub(totalAmountOfGoods, commodityDiscountAmount));

            yxStoreOrderCartInfoVo.setReasons(storeAfterSales.getReasons());
            yxStoreOrderCartInfoVos.add(yxStoreOrderCartInfoVo);
        }

        return yxStoreOrderCartInfoVos;

    }

    @Override
    public Map<String, Object> salesList(Long uid, Integer status, Integer page, String orderCode, Integer limit) {
        Page<StoreAfterSales> storeAfterSalesPage = new Page<>(page, limit);
        List<Integer> integers = new ArrayList<>();
        if (status == 1) {
            integers.add(0);
            integers.add(1);
            integers.add(2);
        }
        if (status == 2) {
            integers.add(3);
        }
        baseMapper.selectPage(storeAfterSalesPage, Wrappers.<StoreAfterSales>lambdaQuery()
                .eq(uid != null, StoreAfterSales::getUserId, uid).in(status.equals(AfterSalesStatusEnum.STATUS_1.getValue()), StoreAfterSales::getState, integers)
                .in(!status.equals(AfterSalesStatusEnum.STATUS_0.getValue()), StoreAfterSales::getState, integers)
                .eq(StringUtils.isNotBlank(orderCode), StoreAfterSales::getOrderCode, orderCode)
                .orderByDesc(StoreAfterSales::getCreateTime)
                .eq(StoreAfterSales::getIsDel, ShopCommonEnum.DELETE_0.getValue()));
        List<StoreAfterSalesVo> storeAfterSalesVos = generator.convert(storeAfterSalesPage.getRecords(), StoreAfterSalesVo.class);
        Map<String, Object> map = new HashMap<>();
        if (uid == null) {
            map.put("content", storeAfterSalesVos.stream().map(this::handleSales).collect(Collectors.toList()));
            map.put("totalElements", storeAfterSalesPage.getTotal());
        } else {
            map.put("list", storeAfterSalesVos.stream().map(this::handleSales).collect(Collectors.toList()));
            map.put("total", storeAfterSalesPage.getTotal());
            map.put("totalPage", storeAfterSalesPage.getPages());
        }
        return map;
    }

    @Override
    public StoreAfterSalesVo getStoreInfoByOrderCodeAndAfterIdAndUid(String key, Long id, Long uid) {
        StoreAfterSales storeAfterSales = baseMapper.selectOne(Wrappers.<StoreAfterSales>lambdaQuery().eq(id != null, StoreAfterSales::getId, id).eq(StoreAfterSales::getUserId, uid).eq(StoreAfterSales::getOrderCode, key));
        StoreAfterSalesVo salesVo = generator.convert(storeAfterSales, StoreAfterSalesVo.class);
//        salesVo.setCloseAfterSaleTime(DateUtil.tomorrow().toTimestamp());
        return salesVo;
    }

    @Override
    public List<StoreAfterSalesVo> getStoreInfoByOrderCodeAndUid(String key, Long uid) {
        List<StoreAfterSales> storeAfterSales = baseMapper.selectList(Wrappers.<StoreAfterSales>lambdaQuery().eq(StoreAfterSales::getUserId, uid).eq(StoreAfterSales::getOrderCode, key));
        return generator.convert(storeAfterSales, StoreAfterSalesVo.class);
    }

    /**
     * 处理售后订单返回的状态
     *
     * @param storeAfterSalesVo /
     * @return StoreAfterSalesVo /
     */
    @Override
    public StoreAfterSalesVo handleSales(StoreAfterSalesVo storeAfterSalesVo) {
        List<StoreAfterSalesItem> storeAfterSalesItems = storeAfterSalesItemMapper.selectList(Wrappers.<StoreAfterSalesItem>lambdaQuery().eq(StoreAfterSalesItem::getStoreAfterSalesId, storeAfterSalesVo.getId()));
        List<YxStoreCartQueryVo> cartInfo = storeAfterSalesItems.stream()
                .map(cart -> JSON.parseObject(cart.getCartInfo(), YxStoreCartQueryVo.class))
                .collect(Collectors.toList());
        storeAfterSalesVo.setCartInfo(cartInfo);
        List<StoreAfterSalesStatus> storeAfterSalesStatuses = storeAfterSalesStatusMapper.selectList(Wrappers.<StoreAfterSalesStatus>lambdaQuery().eq(StoreAfterSalesStatus::getStoreAfterSalesId, storeAfterSalesVo.getId()));

        storeAfterSalesVo.setCompleteTime(storeAfterSalesStatuses.stream().filter(item -> item.getChangeType() == 3).findFirst().orElse(new StoreAfterSalesStatus()).getChangeTime());
        storeAfterSalesVo.setDeliveryTime(storeAfterSalesStatuses.stream().filter(item -> item.getChangeType() == 2).findFirst().orElse(new StoreAfterSalesStatus()).getChangeTime());
        storeAfterSalesVo.setAuditFailedTime(storeAfterSalesStatuses.stream().filter(item -> item.getChangeType() == 4).findFirst().orElse(new StoreAfterSalesStatus()).getChangeTime());
        storeAfterSalesVo.setReviewTime(storeAfterSalesStatuses.stream().filter(item -> item.getChangeType() == 1).findFirst().orElse(new StoreAfterSalesStatus()).getChangeTime());
        storeAfterSalesVo.setRevocationTime(storeAfterSalesStatuses.stream().filter(item -> item.getChangeType() == 5).findFirst().orElse(new StoreAfterSalesStatus()).getChangeTime());
        return storeAfterSalesVo;
    }

    @Override
    public Boolean revoke(String key, Long uid, Long id) {
        StoreAfterSales storeAfterSales = baseMapper.selectOne(Wrappers.<StoreAfterSales>lambdaQuery().
                eq(StoreAfterSales::getUserId, uid).eq(StoreAfterSales::getId, id)
                .eq(StoreAfterSales::getOrderCode, key));
        if (storeAfterSales == null) {
            throw new YshopException("未查询到售后订单信息");
        }
        if (storeAfterSales.getState().equals(AfterSalesStatusEnum.STATUS_2.getValue())
                || storeAfterSales.getState().equals(AfterSalesStatusEnum.STATUS_3.getValue())) {
            throw new YshopException("订单不能撤销");
        }
        storeAfterSales.setSalesState(1);

        YxStoreOrder yxStoreOrder = storeOrderMapper.selectOne(Wrappers.<YxStoreOrder>lambdaQuery().eq(YxStoreOrder::getOrderId, key));
        Object o = redisUtils.get(ShopConstants.YSHOP_ORDER_SALE_STATUS_KEY + uid + key);
        yxStoreOrder.setStatus(o == null ? 0 : Integer.parseInt(o.toString()));
        yxStoreOrder.setRefundStatus(OrderInfoEnum.STATUS_0.getValue());
        storeOrderMapper.updateById(yxStoreOrder);

        List<YxStoreOrderCartInfo> yxStoreOrderCartInfos = storeOrderCartInfoMapper.selectList(Wrappers.<YxStoreOrderCartInfo>lambdaQuery()
                .eq(YxStoreOrderCartInfo::getOid, yxStoreOrder.getId()));
        for (YxStoreOrderCartInfo yxStoreOrderCartInfo : yxStoreOrderCartInfos) {
            yxStoreOrderCartInfo.setIsAfterSales(1);
            storeOrderCartInfoMapper.updateById(yxStoreOrderCartInfo);
        }

        //操作记录
        StoreAfterSalesStatus storeAfterSalesStatus = new StoreAfterSalesStatus();
        storeAfterSalesStatus.setStoreAfterSalesId(storeAfterSales.getId());
        storeAfterSalesStatus.setChangeType(5);
        storeAfterSalesStatus.setChangeMessage("售后订单撤回");
        storeAfterSalesStatus.setChangeTime(Timestamp.valueOf(LocalDateTime.now()));
        storeAfterSalesStatus.setOperator("用户操作");
        storeAfterSalesStatusMapper.insert(storeAfterSalesStatus);

        redisUtils.del(ShopConstants.YSHOP_ORDER_SALE_STATUS_KEY + uid + key);
        return baseMapper.updateById(storeAfterSales) > 0;
    }

    @Override
    public Boolean addLogisticsInformation(String code, String name, String postalCode, String orderCode, Long id) {
        StoreAfterSales storeAfterSales = baseMapper.selectOne(Wrappers.<StoreAfterSales>lambdaQuery()
                .eq(StoreAfterSales::getId, id)
                .eq(StoreAfterSales::getOrderCode, orderCode)
        );
        if (!storeAfterSales.getState().equals(AfterSalesStatusEnum.STATUS_1.getValue())) {
            throw new YshopException("当前状态不能添加物流信息!");
        }
        storeAfterSales.setShipperCode(code);
        storeAfterSales.setDeliverySn(postalCode);
        storeAfterSales.setDeliveryName(name);
        storeAfterSales.setState(AfterSalesStatusEnum.STATUS_2.getValue());

        //操作记录
        StoreAfterSalesStatus storeAfterSalesStatus = new StoreAfterSalesStatus();
        storeAfterSalesStatus.setStoreAfterSalesId(storeAfterSales.getId());
        storeAfterSalesStatus.setChangeType(2);
        storeAfterSalesStatus.setChangeMessage("售后订单生成");
        storeAfterSalesStatus.setChangeTime(Timestamp.valueOf(LocalDateTime.now()));
        storeAfterSalesStatus.setOperator("用户操作");
        storeAfterSalesStatusMapper.insert(storeAfterSalesStatus);

        return baseMapper.updateById(storeAfterSales) > 0;
    }

    @Override
    public Boolean deleteAfterSalesOrder(String orderCode, Long id) {
        StoreAfterSales storeAfterSales = baseMapper.selectOne(Wrappers.<StoreAfterSales>lambdaQuery().eq(StoreAfterSales::getId, id).eq(StoreAfterSales::getOrderCode, orderCode));
        return baseMapper.deleteById(storeAfterSales.getId()) > 0;
    }

    @Override
    public Object salesCheck(Long salesId, String orderCode, Integer approvalStatus, String consignee, String phoneNumber, String address) {
        StoreAfterSales storeAfterSales = baseMapper.selectOne(Wrappers.<StoreAfterSales>lambdaQuery().eq(StoreAfterSales::getOrderCode, orderCode).eq(StoreAfterSales::getId, salesId));
        if (approvalStatus == 0) {
            storeAfterSales.setState(AfterSalesStatusEnum.STATUS_1.getValue());
            if (storeAfterSales.getServiceType() == 1) {
                if (StringUtils.isEmpty(consignee) || StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(address)) {
                    throw new ErrorRequestException("请输入收货人信息");
                }
                storeAfterSales.setConsignee(consignee);
                storeAfterSales.setPhoneNumber(phoneNumber);
                storeAfterSales.setAddress(address);
            }else {
                this.makeMoney(storeAfterSales.getId(),storeAfterSales.getOrderCode());
            }
            //操作记录
            StoreAfterSalesStatus storeAfterSalesStatus = new StoreAfterSalesStatus();
            storeAfterSalesStatus.setStoreAfterSalesId(storeAfterSales.getId());
            storeAfterSalesStatus.setChangeType(1);
            storeAfterSalesStatus.setChangeMessage("平台审核成功");
            storeAfterSalesStatus.setChangeTime(Timestamp.valueOf(LocalDateTime.now()));
            storeAfterSalesStatus.setOperator(SecurityUtils.getUsername());
            storeAfterSalesStatusMapper.insert(storeAfterSalesStatus);

        } else {
            storeAfterSales.setState(AfterSalesStatusEnum.STATUS_3.getValue());
            storeAfterSales.setSalesState(2);
            //操作记录
            StoreAfterSalesStatus storeAfterSalesStatus = new StoreAfterSalesStatus();
            storeAfterSalesStatus.setStoreAfterSalesId(storeAfterSales.getId());
            storeAfterSalesStatus.setChangeType(4);
            storeAfterSalesStatus.setChangeMessage("平台审核失败");
            storeAfterSalesStatus.setChangeTime(Timestamp.valueOf(LocalDateTime.now()));
            storeAfterSalesStatus.setOperator(SecurityUtils.getUsername());
            storeAfterSalesStatusMapper.insert(storeAfterSalesStatus);

            YxStoreOrder yxStoreOrder = storeOrderMapper.selectOne(Wrappers.<YxStoreOrder>lambdaQuery().eq(YxStoreOrder::getOrderId, storeAfterSales.getOrderCode()));
            Object o = redisUtils.get(ShopConstants.YSHOP_ORDER_SALE_STATUS_KEY + storeAfterSales.getUserId() + storeAfterSales.getOrderCode());
            yxStoreOrder.setStatus(o == null ? 0 : Integer.parseInt(o.toString()));
            yxStoreOrder.setRefundStatus(OrderInfoEnum.STATUS_0.getValue());
            storeOrderMapper.updateById(yxStoreOrder);

            redisUtils.del(ShopConstants.YSHOP_ORDER_SALE_STATUS_KEY + storeAfterSales.getUserId() + storeAfterSales.getOrderCode());
        }
        return baseMapper.updateById(storeAfterSales) > 0;
    }

    @Override
    public StoreAfterSales makeMoney(Long salesId, String orderCode) {
        StoreAfterSales storeAfterSales = baseMapper.selectOne(Wrappers.<StoreAfterSales>lambdaQuery().eq(StoreAfterSales::getOrderCode, orderCode).eq(StoreAfterSales::getId, salesId));
        storeAfterSales.setState(AfterSalesStatusEnum.STATUS_3.getValue());
        baseMapper.updateById(storeAfterSales);
        //操作记录
        StoreAfterSalesStatus storeAfterSalesStatus = new StoreAfterSalesStatus();
        storeAfterSalesStatus.setStoreAfterSalesId(storeAfterSales.getId());
        storeAfterSalesStatus.setChangeType(3);
        storeAfterSalesStatus.setChangeMessage("平台打款成功");
        storeAfterSalesStatus.setChangeTime(Timestamp.valueOf(LocalDateTime.now()));
        storeAfterSalesStatus.setOperator(SecurityUtils.getUsername());
        storeAfterSalesStatusMapper.insert(storeAfterSalesStatus);

        YxStoreOrder yxStoreOrder = storeOrderMapper.selectOne(Wrappers.<YxStoreOrder>lambdaQuery().eq(YxStoreOrder::getOrderId, storeAfterSales.getOrderCode()));
        yxStoreOrder.setStatus(-2);
        storeOrderMapper.updateById(yxStoreOrder);
        return storeAfterSales;
    }

    /**
     * 检查订单是否符合售后订单
     *
     * @param yxStoreOrder 订单
     */
    private void checkOrder(YxStoreOrder yxStoreOrder) {
        if (yxStoreOrder == null) {
            throw new YshopException("未查询到订单信息");
        }

        if (!yxStoreOrder.getPaid().equals(OrderInfoEnum.PAY_STATUS_1.getValue())
                || !yxStoreOrder.getRefundStatus().equals(OrderInfoEnum.REFUND_STATUS_0.getValue())
                || yxStoreOrder.getStatus() < OrderInfoEnum.STATUS_0.getValue()) {
            throw new YshopException("订单状态不能售后");
        }
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreAfterSalesQueryCriteria criteria, Pageable pageable) {
        Page<StoreAfterSales> storeAfterSalesPage = new Page<>(pageable.getPageNumber(), pageable.getPageSize());

        LambdaQueryWrapper<StoreAfterSales> eq = Wrappers.<StoreAfterSales>lambdaQuery()
                .in(ObjectUtils.isNotEmpty(criteria.getState()), StoreAfterSales::getState, criteria.getState())
                .eq(StringUtils.isNotBlank(criteria.getOrderCode()), StoreAfterSales::getOrderCode, criteria.getOrderCode())
                .eq(ObjectUtils.isNotEmpty(criteria.getSalesState()), StoreAfterSales::getSalesState, criteria.getSalesState())
                .eq(ObjectUtils.isNotEmpty(criteria.getServiceType()), StoreAfterSales::getServiceType, criteria.getServiceType())
                .orderByDesc(StoreAfterSales::getCreateTime)
                .eq(StoreAfterSales::getIsDel, ShopCommonEnum.DELETE_0.getValue());
        if (CollectionUtils.isNotEmpty(criteria.getTime())) {
            eq.ge(StoreAfterSales::getCreateTime, criteria.getTime().get(0))
                    .le(StoreAfterSales::getCreateTime, criteria.getTime().get(1));
        }

        baseMapper.selectPage(storeAfterSalesPage, eq);
        List<StoreAfterSalesVo> storeAfterSalesVos = generator.convert(storeAfterSalesPage.getRecords(), StoreAfterSalesVo.class);
        Map<String, Object> map = new HashMap<>();
        map.put("content", storeAfterSalesVos.stream().map(this::handleSales).collect(Collectors.toList()));
        map.put("totalElements", storeAfterSalesPage.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<StoreAfterSales> queryAll(YxStoreAfterSalesQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(StoreAfterSales.class, criteria));
    }


    @Override
    public void download(List<YxStoreAfterSalesDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreAfterSalesDto yxStoreAfterSales : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("订单号", yxStoreAfterSales.getOrderCode());
            map.put("退款金额", yxStoreAfterSales.getRefundAmount());
            map.put("服务类型0仅退款1退货退款", yxStoreAfterSales.getServiceType());
            map.put("申请原因", yxStoreAfterSales.getReasons());
            map.put("说明", yxStoreAfterSales.getExplains());
            map.put("说明图片->多个用逗号分割", yxStoreAfterSales.getExplainImg());
            map.put("物流公司编码", yxStoreAfterSales.getShipperCode());
            map.put("物流单号", yxStoreAfterSales.getDeliverySn());
            map.put("物流名称", yxStoreAfterSales.getDeliveryName());
            map.put("状态 0已提交等待平台审核 1平台已审核 等待用户发货/退款 2 用户已发货 3退款成功", yxStoreAfterSales.getState());
            map.put("售后状态-0正常1用户取消2商家拒绝", yxStoreAfterSales.getSalesState());
            map.put("添加时间", yxStoreAfterSales.getCreateTime());
            map.put("逻辑删除", yxStoreAfterSales.getIsDel());
            map.put("用户id", yxStoreAfterSales.getUserId());
            map.put("商家收货人", yxStoreAfterSales.getConsignee());
            map.put("商家手机号", yxStoreAfterSales.getPhoneNumber());
            map.put("商家地址", yxStoreAfterSales.getAddress());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

}
