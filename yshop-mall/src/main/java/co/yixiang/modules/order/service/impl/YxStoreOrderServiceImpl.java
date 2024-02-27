/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.BusinessException;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.constant.ShopConstants;
import co.yixiang.constant.SystemConfigConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.*;
import co.yixiang.event.TemplateBean;
import co.yixiang.event.TemplateEvent;
import co.yixiang.event.TemplateListenEnum;
import co.yixiang.exception.BadRequestException;
import co.yixiang.exception.EntityExistException;
import co.yixiang.exception.ErrorRequestException;
import co.yixiang.modules.activity.domain.YxStoreCouponUser;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.service.YxStoreBargainService;
import co.yixiang.modules.activity.service.YxStoreBargainUserService;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.activity.vo.StoreCouponUserVo;
import co.yixiang.modules.cart.domain.YxStoreCart;
import co.yixiang.modules.cart.service.YxStoreCartService;
import co.yixiang.modules.cart.service.mapper.StoreCartMapper;
import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import co.yixiang.modules.order.domain.YxExpress;
import co.yixiang.modules.order.domain.YxStoreOrder;
import co.yixiang.modules.order.domain.YxStoreOrderCartInfo;
import co.yixiang.modules.order.domain.YxStoreOrderStatus;
import co.yixiang.modules.order.param.OrderParam;
import co.yixiang.modules.order.service.YxExpressService;
import co.yixiang.modules.order.service.YxStoreOrderCartInfoService;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.order.service.YxStoreOrderStatusService;
import co.yixiang.modules.order.service.dto.*;
import co.yixiang.modules.order.service.mapper.StoreOrderMapper;
import co.yixiang.modules.order.vo.*;
import co.yixiang.modules.product.domain.YxStoreProductReply;
import co.yixiang.modules.product.service.YxStoreProductReplyService;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.modules.product.vo.YxStoreProductQueryVo;
import co.yixiang.modules.sales.domain.StoreAfterSales;
import co.yixiang.modules.sales.service.StoreAfterSalesService;
import co.yixiang.modules.shop.domain.YxSystemStore;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.shop.service.YxSystemStoreStaffService;
import co.yixiang.modules.template.domain.YxShippingTemplates;
import co.yixiang.modules.template.domain.YxShippingTemplatesFree;
import co.yixiang.modules.template.domain.YxShippingTemplatesRegion;
import co.yixiang.modules.template.service.YxShippingTemplatesFreeService;
import co.yixiang.modules.template.service.YxShippingTemplatesRegionService;
import co.yixiang.modules.template.service.YxShippingTemplatesService;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.domain.YxUserAddress;
import co.yixiang.modules.user.service.YxUserAddressService;
import co.yixiang.modules.user.service.YxUserBillService;
import co.yixiang.modules.user.service.YxUserLevelService;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.service.dto.YxUserDto;
import co.yixiang.modules.user.vo.YxUserQueryVo;
import co.yixiang.modules.tools.domain.AlipayConfig;
import co.yixiang.modules.tools.domain.vo.TradeVo;
import co.yixiang.modules.tools.service.AlipayConfigService;
import co.yixiang.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * @author hupeng
 * @date 2020-05-12
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class YxStoreOrderServiceImpl extends BaseServiceImpl<StoreOrderMapper, YxStoreOrder> implements YxStoreOrderService {

    @Autowired
    private IGenerator generator;


    @Autowired
    private YxStorePinkService storePinkService;
    @Autowired
    private YxStoreOrderCartInfoService storeOrderCartInfoService;
    @Autowired
    private YxStoreCartService storeCartService;
    @Autowired
    private YxUserAddressService userAddressService;
    @Autowired
    private YxStoreOrderCartInfoService orderCartInfoService;
    @Autowired
    private YxStoreOrderStatusService orderStatusService;
    @Autowired
    private YxUserBillService billService;
    @Autowired
    private YxStoreCouponUserService couponUserService;
    @Autowired
    private YxUserService userService;
    @Autowired
    private YxStoreProductService productService;
    @Autowired
    private YxStorePinkService pinkService;
    @Autowired
    private YxStoreBargainUserService storeBargainUserService;
    @Autowired
    private YxStoreBargainService storeBargainService;
    @Autowired
    private YxExpressService expressService;
    @Autowired
    private AlipayConfigService alipayService;
    @Autowired
    private YxSystemStoreService systemStoreService;
    @Autowired
    private YxStoreProductReplyService productReplyService;
    @Autowired
    private YxStoreCartService yxStoreCartService;
    @Autowired
    private YxSystemStoreStaffService systemStoreStaffService;
    @Autowired
    private YxShippingTemplatesService shippingTemplatesService;
    @Autowired
    private YxShippingTemplatesRegionService shippingTemplatesRegionService;
    @Autowired
    private YxShippingTemplatesFreeService shippingTemplatesFreeService;
    @Autowired
    private YxSystemConfigService systemConfigService;
    @Autowired
    private YxUserLevelService userLevelService;


    @Autowired
    private StoreOrderMapper yxStoreOrderMapper;
    @Autowired
    private StoreCartMapper storeCartMapper;


    @Autowired
    private RedisUtils redisUtils;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private StoreAfterSalesService storeAfterSalesService;


    /**
     * 返回订单确认数据
     *
     * @param yxUser  yxUser
     * @param cartIds 购物车id
     * @return ConfirmOrderVO
     */
    @Override
    public ConfirmOrderVo confirmOrder(YxUser yxUser, String cartIds) {
        Long uid = yxUser.getUid();
        Map<String, Object> cartGroup = yxStoreCartService.getUserProductCartList(uid,
                cartIds, ShopConstants.YSHOP_ONE_NUM);
        if (ObjectUtil.isNotEmpty(cartGroup.get("invalid"))) {
            throw new YshopException("有失效的商品请重新提交");
        }
        if (ObjectUtil.isEmpty(cartGroup.get("valid"))) {
            throw new YshopException("请提交购买的商品");
        }

        OtherDto other = new OtherDto();
        other.setIntegralRatio(systemConfigService.getData(SystemConfigConstants.INTERGRAL_RATIO));
        other.setIntegralFull(systemConfigService.getData(SystemConfigConstants.INTERGRAL_FULL));
        other.setIntegralMax(systemConfigService.getData(SystemConfigConstants.INTERGRAL_MAX));

        //拼团 砍价 秒杀
        Long combinationId = null;
        Long secKillId = null;
        Long bargainId = null;
        if (cartIds.split(",").length == 1) {
            YxStoreCart cartQueryVo = yxStoreCartService.getById(cartIds);
            combinationId = cartQueryVo.getCombinationId();
            secKillId = cartQueryVo.getSeckillId();
            bargainId = cartQueryVo.getBargainId();
        }

        boolean deduction = false;
        boolean enableIntegral = true;

        //拼团砍价秒杀类产品不参与抵扣
        if ((combinationId != null && combinationId > 0) || (secKillId != null && secKillId > 0)
                || (bargainId != null && bargainId > 0)) {
            deduction = true;
        }

        //获取默认地址
        YxUserAddress userAddress = userAddressService.getOne(Wrappers.<YxUserAddress>lambdaQuery()
                .eq(YxUserAddress::getUid, uid)
                .eq(YxUserAddress::getIsDefault, ShopCommonEnum.DEFAULT_1.getValue()), false);

        List<YxStoreCartQueryVo> cartInfo = (List<YxStoreCartQueryVo>) cartGroup.get("valid");
        PriceGroupDto priceGroup = this.getOrderPriceGroup(cartInfo, userAddress);

        //判断积分是否满足订单额度
        if (priceGroup.getTotalPrice().compareTo(new BigDecimal(other.getIntegralFull())) < 0) {
            enableIntegral = false;
        }

        String cacheKey = this.cacheOrderInfo(uid, cartInfo, priceGroup, other);


        //获取可用优惠券
        List<String> productIds = cartInfo.stream()
                .map(YxStoreCartQueryVo::getProductId)
                .map(Object::toString)
                .collect(Collectors.toList());
        List<StoreCouponUserVo> storeCouponUsers = couponUserService
                .getUsableCouponList(uid, priceGroup.getTotalPrice().doubleValue(), productIds);

        StoreCouponUserVo storeCouponUser = null;
        if (storeCouponUsers != null && !storeCouponUsers.isEmpty()) {
            storeCouponUser = storeCouponUsers.get(0);
        }

        return ConfirmOrderVo.builder()
                .addressInfo(userAddress)
                .cartInfo(cartInfo)
                .priceGroup(priceGroup)
                .userInfo(generator.convert(yxUser, YxUserQueryVo.class))
                .orderKey(cacheKey)
                .deduction(deduction)
                .enableIntegral(enableIntegral)
                .enableIntegralNum(Double.valueOf(other.getIntegralMax()))
                //.integralRatio(d)
                .storeSelfMention(Integer.valueOf(RedisUtil.get(ShopKeyUtils.getStoreSelfMention())))
                .usableCoupon(storeCouponUser)
                .systemStore(systemStoreService.getStoreInfo("", ""))
                .build();

    }


    /**
     * 计算订单价格
     *
     * @param userInfo     用户
     * @param key          订单缓存key
     * @param couponId     优惠券id
     * @param useIntegral  使用积分 1-表示使用
     * @param shippingType 发货类型 OrderInfoEnum
     * @return ComputeVo
     */
    @Override
    public ComputeVo computedOrder(YxUser userInfo, String key, String couponId,
                                   String useIntegral, String shippingType, String addressId) {
        Long uid = userInfo.getUid();
        CacheDto cacheDTO = this.getCacheOrderInfo(uid, key);
        if (ObjectUtil.isNull(cacheDTO)) {
            throw new YshopException("订单已过期,请刷新当前页面");
        }
        BigDecimal payPrice = cacheDTO.getPriceGroup().getTotalPrice();


        //处理运费模板
        YxUserAddress userAddress = null;
        if (StrUtil.isNotBlank(addressId)) {
            userAddress = userAddressService.getById(addressId);
        }
        PriceGroupDto priceGroup = this.getOrderPriceGroup(cacheDTO.getCartInfo(), userAddress);
        BigDecimal payPostage = priceGroup.getStorePostage();

        Integer shippingTypeI = Integer.valueOf(shippingType);
        //1-配送 2-到店
        if (OrderInfoEnum.SHIPPIING_TYPE_1.getValue().equals(shippingTypeI)) {
            payPrice = NumberUtil.add(payPrice, payPostage);
        } else {
            payPostage = BigDecimal.ZERO;
        }

        Long combinationId = null;
        Long seckillId = null;
        Long bargainId = null;
        List<YxStoreCartQueryVo> cartInfo = cacheDTO.getCartInfo();
        for (YxStoreCartQueryVo cart : cartInfo) {
            combinationId = cart.getCombinationId();
            seckillId = cart.getSeckillId();
            bargainId = cart.getBargainId();
        }

        //拼团等不参与抵扣
        if ((combinationId != null && combinationId > 0) || (seckillId != null && seckillId > 0)
                || (bargainId != null && bargainId > 0)) {
            couponId = "";
            useIntegral = "";
        }

        BigDecimal couponPrice = BigDecimal.ZERO;
        if (StrUtil.isNotBlank(couponId) && !ShopConstants.YSHOP_ZERO.equals(couponId)) {//使用优惠券
            YxStoreCouponUser couponUser = couponUserService.getCoupon(Integer.valueOf(couponId), uid);
            if (couponUser == null) {
                throw new YshopException("使用优惠劵失败");
            }

            if (couponUser.getUseMinPrice().compareTo(payPrice) > 0) {
                throw new YshopException("不满足优惠劵的使用条件");
            }
            payPrice = NumberUtil.sub(payPrice, couponUser.getCouponPrice());
            couponPrice = couponUser.getCouponPrice();
        }

        // 积分抵扣
        BigDecimal deductionPrice = BigDecimal.ZERO; //积分抵扣金额
        double usedIntegral = 0; //使用了多少积分
        if (StrUtil.isNotBlank(useIntegral) && ShopConstants.YSHOP_ONE.equals(useIntegral)
                && userInfo.getIntegral().intValue() > 0) {
            Double integralMax = Double.valueOf(cacheDTO.getOther().getIntegralMax());
            BigDecimal integralFull = new BigDecimal(cacheDTO.getOther().getIntegralFull());
            Double integralRatio = Double.valueOf(cacheDTO.getOther().getIntegralRatio());

            if (cacheDTO.getPriceGroup().getTotalPrice().compareTo(integralFull) >= 0) {
                Double userIntegral = userInfo.getIntegral().doubleValue();
                if (integralMax.intValue() > 0 && Double.compare(userIntegral, integralMax) >= 0) {
                    userIntegral = integralMax;
                }
                deductionPrice = BigDecimal.valueOf(NumberUtil.mul(userIntegral, integralRatio));
                if (deductionPrice.compareTo(payPrice) < 0) {
                    payPrice = NumberUtil.sub(payPrice, deductionPrice);
                    usedIntegral = userIntegral;
                } else {
                    deductionPrice = payPrice;
                    payPrice = BigDecimal.ZERO;
                    usedIntegral = NumberUtil.round(NumberUtil.div(deductionPrice,
                            BigDecimal.valueOf(integralRatio)), 2).doubleValue();
                }
            }
        }

        if (payPrice.compareTo(BigDecimal.ZERO) <= 0) {
            payPrice = BigDecimal.ZERO;
        }

        return ComputeVo.builder()
                .totalPrice(cacheDTO.getPriceGroup().getTotalPrice())
                .payPrice(payPrice)
                .payPostage(payPostage)
                .couponPrice(couponPrice)
                .deductionPrice(deductionPrice)
                .usedIntegral(usedIntegral)
                .payIntegral(cacheDTO.getPriceGroup().getPayIntegral())
                .build();
    }


    /**
     * 创建订单
     *
     * @param userInfo 用户信息
     * @param key      key
     * @param param    param
     * @return YxStoreOrder
     */
    @Override
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY, allEntries = true)
    public YxStoreOrder createOrder(YxUser userInfo, String key, OrderParam param) {

        ComputeVo computeVo = this.computedOrder(userInfo, key, param.getCouponId(),
                param.getUseIntegral(), param.getShippingType(), param.getAddressId());

        Long uid = userInfo.getUid();

        //处理选择门店与正常选择地址下单
        YxUserAddress userAddress = null;
        if (OrderInfoEnum.SHIPPIING_TYPE_1.getValue().equals(Integer.valueOf(param.getShippingType()))) {
            if (StrUtil.isEmpty(param.getAddressId())) {
                throw new YshopException("请选择收货地址");
            }
            userAddress = userAddressService.getById(param.getAddressId());
            if (ObjectUtil.isNull(userAddress)) {
                throw new YshopException("地址选择有误");
            }
        } else { //门店
            if (StrUtil.isBlank(param.getRealName()) || StrUtil.isBlank(param.getPhone())) {
                throw new YshopException("请填写姓名和电话");
            }
            userAddress = new YxUserAddress();
            userAddress.setRealName(param.getRealName());
            userAddress.setPhone(param.getPhone());
            userAddress.setProvince("");
            userAddress.setCity("");
            userAddress.setDistrict("");
            userAddress.setDetail("");
        }

        Integer totalNum = 0;
        //Integer gainIntegral = 0;
        List<String> cartIds = new ArrayList<>();
        Long combinationId = 0L;
        Long seckillId = 0L;
        Long bargainId = 0L;

        Boolean isIntegral = false;
        BigDecimal integral = BigDecimal.ZERO;

        CacheDto cacheDTO = this.getCacheOrderInfo(uid, key);
        List<YxStoreCartQueryVo> cartInfo = cacheDTO.getCartInfo();

        for (YxStoreCartQueryVo cart : cartInfo) {
            //检测库存
            yxStoreCartService.checkProductStock(uid, cart.getProductId(), cart.getCartNum(),
                    cart.getProductAttrUnique(), cart.getCombinationId(),
                    cart.getSeckillId(), cart.getBargainId());

            combinationId = cart.getCombinationId();
            seckillId = cart.getSeckillId();
            bargainId = cart.getBargainId();

            cartIds.add(cart.getId().toString());
            totalNum += cart.getCartNum();
            if (cart.getProductInfo().getIsIntegral() != null
                    && cart.getProductInfo().getIsIntegral() == 1) {
                integral = NumberUtil.add(integral,
                        NumberUtil.mul(cart.getCartNum(), cart.getProductInfo().getAttrInfo().getIntegral()));
            }
        }


        //计算奖励积分
        BigDecimal gainIntegral = this.getGainIntegral(cartInfo);
        if (PayTypeEnum.INTEGRAL.getValue().equals(param.getPayType())) {
            gainIntegral = BigDecimal.ZERO;
            isIntegral = true;
        }
        //生成分布式唯一值
        String orderSn = IdUtil.getSnowflake(0, 0).nextIdStr();
        //组合数据
        YxStoreOrder storeOrder = new YxStoreOrder();
        storeOrder.setUid(Long.valueOf(String.valueOf(uid)));
        storeOrder.setOrderId(orderSn);
        storeOrder.setRealName(userAddress.getRealName());
        storeOrder.setUserPhone(userAddress.getPhone());
        storeOrder.setUserAddress(userAddress.getProvince() + " " + userAddress.getCity() +
                " " + userAddress.getDistrict() + " " + userAddress.getDetail());
        storeOrder.setCartId(StrUtil.join(",", cartIds));
        storeOrder.setTotalNum(totalNum);
        storeOrder.setTotalPrice(computeVo.getTotalPrice());
        storeOrder.setTotalPostage(computeVo.getPayPostage());

        storeOrder.setCouponId(param.getCouponId() == null ? null : Integer.valueOf(param.getCouponId()));
        storeOrder.setCouponPrice(computeVo.getCouponPrice());
        storeOrder.setPayPrice(computeVo.getPayPrice());
        storeOrder.setPayPostage(computeVo.getPayPostage());
        storeOrder.setDeductionPrice(computeVo.getDeductionPrice());
        storeOrder.setPaid(OrderInfoEnum.PAY_STATUS_0.getValue());
        storeOrder.setPayType(param.getPayType());
        if (isIntegral) {
            storeOrder.setPayIntegral(integral);
        }
        storeOrder.setUseIntegral(BigDecimal.valueOf(computeVo.getUsedIntegral()));
        storeOrder.setBackIntegral(BigDecimal.ZERO);
        storeOrder.setGainIntegral(gainIntegral);
        storeOrder.setMark(param.getMark());
        storeOrder.setCombinationId(combinationId);
        storeOrder.setPinkId(Long.valueOf(param.getPinkId()));
        storeOrder.setSeckillId(seckillId);
        storeOrder.setBargainId(bargainId);
        storeOrder.setCost(cacheDTO.getPriceGroup().getCostPrice());
        if (AppFromEnum.ROUNTINE.getValue().equals(param.getFrom())) {
            storeOrder.setIsChannel(OrderInfoEnum.PAY_CHANNEL_1.getValue());
        } else {
            storeOrder.setIsChannel(OrderInfoEnum.PAY_CHANNEL_0.getValue());
        }
        storeOrder.setUnique(key);
        storeOrder.setShippingType(Integer.valueOf(param.getShippingType()));
        //处理门店
        if (OrderInfoEnum.SHIPPIING_TYPE_2.getValue().toString().equals(param.getShippingType())) {
            YxSystemStore systemStoreQueryVo = systemStoreService.getById(param.getStoreId());
            if (systemStoreQueryVo == null) {
                throw new ErrorRequestException("暂无门店无法选择门店自提");
            }
            storeOrder.setVerifyCode(StrUtil.sub(orderSn, orderSn.length(), -12));
            storeOrder.setStoreId(systemStoreQueryVo.getId());
        }

        boolean res = this.save(storeOrder);
        if (!res) {
            throw new YshopException("订单生成失败");
        }

        //使用了积分扣积分
        if (computeVo.getUsedIntegral() > 0) {
            this.decIntegral(userInfo, computeVo.getUsedIntegral(), computeVo.getDeductionPrice().doubleValue());
        }

        //使用了优惠券扣优惠券
        if (computeVo.getCouponPrice().compareTo(BigDecimal.ZERO) > 0) {
            couponUserService.useCoupon(Integer.valueOf(param.getCouponId()));
        }


        // 减库存加销量
        this.deStockIncSale(cartInfo);


        //保存购物车商品信息
        orderCartInfoService.saveCartInfo(storeOrder.getId(), storeOrder.getOrderId(),cartInfo);


        //购物车状态修改
        YxStoreCart cartObj = new YxStoreCart();
        cartObj.setIsPay(OrderInfoEnum.PAY_STATUS_1.getValue());
        storeCartMapper.update(cartObj, Wrappers.<YxStoreCart>lambdaQuery()
                .in(YxStoreCart::getId, cartIds));

        //删除缓存
        this.delCacheOrderInfo(uid, key);

        //增加状态
        orderStatusService.create(storeOrder.getId(), OrderLogEnum.CREATE_ORDER.getValue(),
                OrderLogEnum.CREATE_ORDER.getDesc());


        //加入redis，30分钟自动取消
        String redisKey = String.valueOf(StrUtil.format("{}{}",
                ShopConstants.REDIS_ORDER_OUTTIME_UNPAY, storeOrder.getId()));
        redisTemplate.opsForValue().set(redisKey, storeOrder.getOrderId(),
                ShopConstants.ORDER_OUTTIME_UNPAY, TimeUnit.MINUTES);

        //使用MQ延时消息
//        TemplateBean templateBean = TemplateBean.builder()
//                .orderId(storeOrder.getId()+"")
//                .uid(storeOrder.getUid())
//                .templateType(TemplateListenEnum.TYPE_7.getValue())
//                .time(DateUtil.formatTime(new Date()))
//                .build();
//        publisher.publishEvent(new TemplateEvent(this,templateBean));


        return storeOrder;
    }


    /**
     * 订单评价
     *
     * @param orderCartInfo
     * @param user          user
     * @param unique        订单orderCart唯一值
     * @param comment       评论内容
     * @param pics          图片
     * @param productScore  评分
     * @param serviceScore  评分
     */
    @Override
    public void orderComment(YxStoreOrderCartInfo orderCartInfo, YxUser user, String unique, String comment, String pics, String productScore,
                             String serviceScore) {

        if (ObjectUtil.isEmpty(orderCartInfo)) {
            throw new YshopException("评价产品不存在");
        }

        Long count = productReplyService.count(Wrappers.<YxStoreProductReply>lambdaQuery()
                .eq(YxStoreProductReply::getOid, orderCartInfo.getOid())
                .eq(YxStoreProductReply::getProductId, orderCartInfo.getProductId()));
        if (count > 0) {
            throw new YshopException("该产品已评价");
        }


        YxStoreProductReply storeProductReply = YxStoreProductReply.builder()
                .uid(user.getUid())
                .oid(orderCartInfo.getOid())
                .productId(orderCartInfo.getProductId())
                .productScore(Integer.valueOf(productScore))
                .serviceScore(Integer.valueOf(serviceScore))
                .comment(comment)
                .pics(pics)
                .unique(unique)
                .build();

        productReplyService.save(storeProductReply);
        //获取评价商品数量
        Long replyCount = productReplyService.count(new LambdaQueryWrapper<YxStoreProductReply>().eq(YxStoreProductReply::getOid, orderCartInfo.getOid()));
        //购买商品数量
        Long cartCount = storeOrderCartInfoService.count(new LambdaQueryWrapper<YxStoreOrderCartInfo>().eq(YxStoreOrderCartInfo::getOid, orderCartInfo.getOid()));
        if (replyCount == cartCount) {
            YxStoreOrder storeOrder = new YxStoreOrder();
            storeOrder.setStatus(OrderInfoEnum.STATUS_3.getValue());
            storeOrder.setId(orderCartInfo.getOid());
            yxStoreOrderMapper.updateById(storeOrder);

        }
    }

    /**
     * 确认订单退款
     *
     * @param orderId 单号
     * @param price   金额
     * @param type    ShopCommonEnum
     * @param salesId 售后id
     */
    @Override
    public void orderRefund(String orderId, BigDecimal price, Integer type, Long salesId) {

        YxStoreOrderQueryVo orderQueryVo = getOrderInfo(orderId, null);
        if (ObjectUtil.isNull(orderQueryVo)) {
            throw new YshopException("订单不存在");
        }

        YxUserQueryVo userQueryVo = userService.getYxUserById(orderQueryVo.getUid());
        if (ObjectUtil.isNull(userQueryVo)) {
            throw new YshopException("用户不存在");
        }

        if (OrderInfoEnum.REFUND_STATUS_2.getValue().equals(orderQueryVo.getRefundStatus())) {
            throw new YshopException("订单已经退款了哦！");
        }

        if (orderQueryVo.getPayPrice().compareTo(price) < 0) {
            throw new YshopException("退款金额不正确");
        }

        YxStoreOrder storeOrder = new YxStoreOrder();
        //修改状态
        storeOrder.setId(orderQueryVo.getId());
        if (ShopCommonEnum.AGREE_2.getValue().equals(type)) {
            storeOrder.setRefundStatus(OrderInfoEnum.REFUND_STATUS_0.getValue());
            yxStoreOrderMapper.updateById(storeOrder);
            if (null != salesId) {
                StoreAfterSales storeAfterSales = storeAfterSalesService.lambdaQuery()
                        .eq(StoreAfterSales::getUserId, orderQueryVo.getUid())
                        .eq(StoreAfterSales::getOrderCode, orderQueryVo.getOrderId())
                        .eq(StoreAfterSales::getId, salesId)
                        .one();
                if (ObjectUtil.isNotNull(storeAfterSales)) {
                    storeAfterSalesService.lambdaUpdate()
                            .eq(StoreAfterSales::getId, storeAfterSales.getId())
                            .set(StoreAfterSales::getSalesState, ShopCommonEnum.AGREE_2.getValue())
                            .update();
                }
            }
            return;
        }

        //根据支付类型不同退款不同
        if (PayTypeEnum.YUE.getValue().equals(orderQueryVo.getPayType())) {
            storeOrder.setRefundStatus(OrderInfoEnum.REFUND_STATUS_2.getValue());
            storeOrder.setRefundPrice(price);
            yxStoreOrderMapper.updateById(storeOrder);
            //退款到余额
            userService.incMoney(orderQueryVo.getUid(), price);

            //增加流水
            billService.income(orderQueryVo.getUid(), "商品退款", BillDetailEnum.CATEGORY_1.getValue(),
                    BillDetailEnum.TYPE_5.getValue(),
                    price.doubleValue(),
                    NumberUtil.add(price, userQueryVo.getNowMoney()).doubleValue(),
                    "订单退款到余额" + price + "元", orderQueryVo.getId().toString());
            this.returnStock(orderQueryVo.getOrderId());

            if (null != salesId) {
                StoreAfterSales storeAfterSales = storeAfterSalesService.lambdaQuery()
                        .eq(StoreAfterSales::getUserId, orderQueryVo.getUid())
                        .eq(StoreAfterSales::getOrderCode, orderQueryVo.getOrderId())
                        .eq(StoreAfterSales::getId, salesId)
                        .one();
                if (ObjectUtil.isNotNull(storeAfterSales)) {
                    storeAfterSalesService.lambdaUpdate()
                            .eq(StoreAfterSales::getId, storeAfterSales.getId())
                            .set(StoreAfterSales::getState, AfterSalesStatusEnum.STATUS_3.getValue())
                            .update();
                }
            }

        } else if (PayTypeEnum.INTEGRAL.getValue().equals(orderQueryVo.getPayType())) {
            storeOrder.setRefundStatus(OrderInfoEnum.REFUND_STATUS_2.getValue());
            storeOrder.setRefundPrice(price);
            yxStoreOrderMapper.updateById(storeOrder);

            orderStatusService.create(orderQueryVo.getId(), OrderLogEnum.ORDER_EDIT.getValue(), "退款给用户：" + orderQueryVo.getPayIntegral() + "分");
            this.returnStock(orderQueryVo.getOrderId());
        }

        orderStatusService.create(orderQueryVo.getId(), OrderLogEnum.REFUND_ORDER_SUCCESS.getValue(), "退款给用户：" + price + "元");

        TemplateBean templateBean = TemplateBean.builder()
                .orderId(orderQueryVo.getOrderId())
                .price(orderQueryVo.getPayPrice().toString())
                .uid(orderQueryVo.getUid())
                .templateType(TemplateListenEnum.TYPE_2.getValue())
                .time(DateUtil.formatTime(new Date()))
                .payType(orderQueryVo.getPayType())
                .build();
        publisher.publishEvent(new TemplateEvent(this, templateBean));


    }


    /**
     * 订单发货
     *
     * @param orderId      单号
     * @param deliveryId   快递单号
     * @param deliveryName 快递公司code
     * @param deliveryType 快递方式
     */
    @Override
    public void orderDelivery(String orderId, String deliveryId, String deliveryName, String deliveryType) {
        YxStoreOrderQueryVo orderQueryVo = this.getOrderInfo(orderId, null);
        if (ObjectUtil.isNull(orderQueryVo)) {
            throw new YshopException("订单不存在");
        }

        if (!OrderInfoEnum.STATUS_0.getValue().equals(orderQueryVo.getStatus()) ||
                OrderInfoEnum.PAY_STATUS_0.getValue().equals(orderQueryVo.getPaid())) {
            throw new YshopException("订单状态错误");
        }

        if (!OrderInfoEnum.REFUND_STATUS_0.getValue().equals(orderQueryVo.getRefundStatus())) {
            throw new YshopException("订单退款中或已退款");
        }

        YxExpress expressQueryVo = expressService.getOne(new LambdaQueryWrapper<YxExpress>().eq(YxExpress::getName, deliveryName));
        if (ObjectUtil.isNull(expressQueryVo)) {
            throw new YshopException("请后台先添加快递公司");
        }

        //判断拼团产品
        if (orderQueryVo.getPinkId() != null && orderQueryVo.getPinkId() > 0) {
            YxStorePink pink = pinkService.getById(orderQueryVo.getPinkId());
            if (!OrderInfoEnum.PINK_STATUS_2.getValue().equals(pink.getStatus())) {
                throw new YshopException("拼团未成功不能发货");
            }
        }

        YxStoreOrder storeOrder = YxStoreOrder.builder()
                .id(orderQueryVo.getId())
                .status(OrderInfoEnum.STATUS_1.getValue())
                .deliveryId(deliveryId)
                .deliveryName(expressQueryVo.getName())
                .deliveryType(deliveryType)
                .deliverySn(expressQueryVo.getCode())
                .build();

        yxStoreOrderMapper.updateById(storeOrder);

        //增加状态
        orderStatusService.create(orderQueryVo.getId(), OrderLogEnum.DELIVERY_GOODS.getValue(),
                "已发货 快递公司：" + expressQueryVo.getName() + "快递单号：" + deliveryId);

        //模板消息发布事件
        TemplateBean templateBean = TemplateBean.builder()
                .orderId(orderQueryVo.getOrderId())
                .deliveryId(deliveryId)
                .deliveryName(expressQueryVo.getName())
                .uid(orderQueryVo.getUid())
                .templateType(TemplateListenEnum.TYPE_3.getValue())
                .build();
        publisher.publishEvent(new TemplateEvent(this, templateBean));


        //加入redis，7天后自动确认收货
        String redisKey = String.valueOf(StrUtil.format("{}{}",
                ShopConstants.REDIS_ORDER_OUTTIME_UNCONFIRM, orderQueryVo.getId()));
        redisTemplate.opsForValue().set(redisKey, orderQueryVo.getOrderId(),
                ShopConstants.ORDER_OUTTIME_UNCONFIRM, TimeUnit.DAYS);

    }

    /**
     * 修改快递单号
     *
     * @param orderId      单号
     * @param deliveryId   快递单号
     * @param deliveryName 快递公司code
     * @param deliveryType 快递方式
     */
    @Override
    public void updateDelivery(String orderId, String deliveryId, String deliveryName, String deliveryType) {
        YxStoreOrderQueryVo orderQueryVo = this.getOrderInfo(orderId, null);
        if (ObjectUtil.isNull(orderQueryVo)) {
            throw new YshopException("订单不存在");
        }

        if (!OrderInfoEnum.STATUS_1.getValue().equals(orderQueryVo.getStatus()) ||
                OrderInfoEnum.PAY_STATUS_0.getValue().equals(orderQueryVo.getPaid())) {
            throw new YshopException("订单状态错误");
        }

        YxExpress expressQueryVo = expressService.getOne(new LambdaQueryWrapper<YxExpress>().eq(YxExpress::getName, deliveryName));
        if (ObjectUtil.isNull(expressQueryVo)) {
            throw new YshopException("请后台先添加快递公司");
        }


        YxStoreOrder storeOrder = YxStoreOrder.builder()
                .id(orderQueryVo.getId())
                .deliveryId(deliveryId)
                .deliveryName(expressQueryVo.getName())
                .deliveryType(deliveryType)
                .deliverySn(expressQueryVo.getCode())
                .build();

        yxStoreOrderMapper.updateById(storeOrder);
    }


    /**
     * 修改订单价格
     *
     * @param orderId 单号
     * @param price   价格
     */
    @Override
    public void editOrderPrice(String orderId, String price) {
        YxStoreOrderQueryVo orderQueryVo = getOrderInfo(orderId, null);
        if (ObjectUtil.isNull(orderQueryVo)) {
            throw new YshopException("订单不存在");
        }


        if (orderQueryVo.getPayPrice().compareTo(new BigDecimal(price)) == 0) {
            return;
        }


        if (OrderInfoEnum.PAY_STATUS_1.getValue().equals(orderQueryVo.getPaid())) {
            throw new YshopException("订单状态错误");
        }


        YxStoreOrder storeOrder = new YxStoreOrder();
        storeOrder.setId(orderQueryVo.getId());
        storeOrder.setPayPrice(new BigDecimal(price));

        //判断金额是否有变动,生成一个额外订单号去支付
        if (orderQueryVo.getPayPrice().compareTo(new BigDecimal(price)) != 0) {
            String orderSn = IdUtil.getSnowflake(0, 0).nextIdStr();
            storeOrder.setExtendOrderId(orderSn);
        }

        yxStoreOrderMapper.updateById(storeOrder);

        //增加状态
        orderStatusService.create(storeOrder.getId(), OrderLogEnum.ORDER_EDIT.getValue(), "修改实际支付金额");

    }

    /**
     * 获取拼团订单
     *
     * @param pid 拼团id
     * @param uid 用户id
     * @return YxStoreOrder
     */
    @Override
    public YxStoreOrder getOrderPink(Long pid, Long uid) {
        return this.lambdaQuery().eq(YxStoreOrder::getUid, uid)
                .eq(YxStoreOrder::getPinkId, pid)
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .one();
    }


    /**
     * 未付款取消订单
     *
     * @param orderId 订单号
     * @param uid     用户id
     */
    @Override
    public void cancelOrder(String orderId, Long uid) {
        YxStoreOrderQueryVo order = this.getOrderInfo(orderId, uid);
        if (ObjectUtil.isNull(order)) {
            throw new YshopException("订单不存在");
        }

        this.regressionIntegral(order, 0);

        this.regressionStock(order, 0);

        this.regressionCoupon(order, 0);

        yxStoreOrderMapper.deleteById(order.getId());
    }


    /**
     * 删除订单
     *
     * @param orderId 单号
     * @param uid     uid
     */
    @Override
    public void removeOrder(String orderId, Long uid) {
        YxStoreOrderQueryVo order = getOrderInfo(orderId, (long) uid);
        if (order == null) {
            throw new YshopException("订单不存在");
        }
        order = handleOrder(order);
        if (!OrderInfoEnum.STATUS_3.getValue().equals(order.getStatus())) {
            throw new YshopException("该订单无法删除");
        }

        yxStoreOrderMapper.deleteById(order.getId());

        //增加状态
        orderStatusService.create(order.getId(),
                OrderLogEnum.REMOVE_ORDER.getValue(),
                OrderLogEnum.REMOVE_ORDER.getDesc());
    }

    /**
     * 订单确认收货
     *
     * @param orderId 单号
     * @param uid     uid
     */
    @CacheEvict(cacheNames = ShopConstants.YSHOP_REDIS_INDEX_KEY, allEntries = true)
    @Override
    public void takeOrder(String orderId, Long uid) {
        YxStoreOrderQueryVo order = this.getOrderInfo(orderId, uid);
        if (ObjectUtil.isNull(order)) {
            throw new YshopException("订单不存在");
        }
        order = handleOrder(order);
        if (!OrderStatusEnum.STATUS_2.getValue().toString().equals(order.get_status().get_type())) {
            throw new BusinessException("订单状态错误");
        }

        YxStoreOrder storeOrder = new YxStoreOrder();
        storeOrder.setStatus(OrderInfoEnum.STATUS_2.getValue());
        storeOrder.setId(order.getId());
        yxStoreOrderMapper.updateById(storeOrder);

        //增加状态
        orderStatusService.create(order.getId(), OrderLogEnum.TAKE_ORDER_DELIVERY.getValue(), OrderLogEnum.TAKE_ORDER_DELIVERY.getDesc());

        //奖励积分
        this.gainUserIntegral(order);

        //分销计算
        userService.backOrderBrokerage(order);

        //检查是否符合会员升级条件
        // userLevelService.setLevelComplete(uid);
    }


    /**
     * 核销订单
     *
     * @param verifyCode 核销码
     * @param isConfirm  OrderInfoEnum
     * @param uid        uid
     * @return YxStoreOrderQueryVo
     */
    @Override
    public YxStoreOrderQueryVo verifyOrder(String verifyCode, Integer isConfirm, Long uid) {

        YxStoreOrder order = this.getOne(Wrappers.<YxStoreOrder>lambdaQuery()
                .eq(YxStoreOrder::getVerifyCode, verifyCode)
                .eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue()));
        if (order == null) {
            throw new YshopException("核销的订单不存在或未支付或已退款");
        }

        if (uid != null) {
            boolean checkStatus = systemStoreStaffService.checkStatus(uid, order.getStoreId());
            if (!checkStatus) {
                throw new YshopException("您没有当前店铺核销权限");
            }
        }

        if (!OrderInfoEnum.STATUS_0.getValue().equals(order.getStatus())) {
            throw new YshopException("订单已经核销");
        }

        if (order.getCombinationId() != null && order.getCombinationId() > 0
                && order.getPinkId() != null && order.getPinkId() > 0) {
            YxStorePink storePink = storePinkService.getById(order.getPinkId());
            if (!OrderInfoEnum.PINK_STATUS_2.getValue().equals(storePink.getStatus())) {
                throw new YshopException("拼团订单暂未成功无法核销");
            }
        }

        YxStoreOrderQueryVo orderQueryVo = generator.convert(order, YxStoreOrderQueryVo.class);
        if (OrderInfoEnum.CONFIRM_STATUS_0.getValue().equals(isConfirm)) {
            return orderQueryVo;
        }


        YxStoreOrder storeOrder = new YxStoreOrder();
        storeOrder.setStatus(OrderInfoEnum.STATUS_2.getValue());
        storeOrder.setId(order.getId());
        yxStoreOrderMapper.updateById(storeOrder);

        //增加状态
        orderStatusService.create(order.getId(), OrderLogEnum.TAKE_ORDER_DELIVERY.getValue(), "已核销");

        //奖励积分
        this.gainUserIntegral(orderQueryVo);

        //分销计算
        userService.backOrderBrokerage(orderQueryVo);

        //检查是否符合会员升级条件
        userLevelService.setLevelComplete(order.getUid());

        return null;
    }

    /**
     * 申请退款
     *
     * @param explain 退款备注
     * @param Img     图片
     * @param text    理由
     * @param orderId 订单号
     * @param uid     uid
     */
    @Override
    public void orderApplyRefund(String explain, String Img, String text, String orderId, Long uid) {
        YxStoreOrderQueryVo order = getOrderInfo(orderId, uid);
        if (order == null) {
            throw new YshopException("订单不存在");
        }

        if (OrderInfoEnum.REFUND_STATUS_2.getValue().equals(order.getRefundStatus())) {
            throw new YshopException("订单已退款");
        }
        if (OrderInfoEnum.REFUND_STATUS_1.getValue().equals(order.getRefundStatus())) {
            throw new YshopException("正在申请退款中");
        }
        if (OrderInfoEnum.STATUS_1.getValue().equals(order.getStatus())) {
            throw new YshopException("订单当前无法退款");
        }


        YxStoreOrder storeOrder = new YxStoreOrder();
        storeOrder.setRefundStatus(OrderInfoEnum.REFUND_STATUS_1.getValue());
        storeOrder.setRefundReasonTime(new Date());
        storeOrder.setRefundReasonWapExplain(explain);
        storeOrder.setRefundReasonWapImg(Img);
        storeOrder.setRefundReasonWap(text);
        storeOrder.setId(order.getId());
        yxStoreOrderMapper.updateById(storeOrder);

        //增加状态
        orderStatusService.create(order.getId(),
                OrderLogEnum.REFUND_ORDER_APPLY.getValue(),
                "用户申请退款，原因：" + text);

        //模板消息发布事件
        TemplateBean templateBean = TemplateBean.builder()
                .orderId(order.getOrderId())
                .price(order.getPayPrice().toString())
                .uid(order.getUid())
                .templateType(TemplateListenEnum.TYPE_9.getValue())
                .time(DateUtil.formatTime(new Date()))
                .build();
        publisher.publishEvent(new TemplateEvent(this, templateBean));

    }

    /**
     * 订单列表
     *
     * @param uid   用户id
     * @param type  OrderStatusEnum
     * @param page  page
     * @param limit limit
     * @return list
     */
    @Override
    public Map<String, Object> orderList(Long uid, int type, int page, int limit) {
        LambdaQueryWrapper<YxStoreOrder> wrapper = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapper.eq(YxStoreOrder::getUid, uid);
        }
        wrapper.orderByDesc(YxStoreOrder::getId);

        switch (OrderStatusEnum.toType(type)) {
            case STATUS__1:
                break;
            //未支付
            case STATUS_0:
                wrapper.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_0.getValue())
                        .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                        .eq(YxStoreOrder::getStatus, OrderInfoEnum.STATUS_0.getValue());
                break;
            //待发货
            case STATUS_1:
                wrapper.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                        .eq(YxStoreOrder::getStatus, OrderInfoEnum.STATUS_0.getValue());
                break;
            //待收货
            case STATUS_2:
                wrapper.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                        .eq(YxStoreOrder::getStatus, OrderInfoEnum.STATUS_1.getValue());
                break;
            //待评价
            case STATUS_3:
                wrapper.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                        .eq(YxStoreOrder::getStatus, OrderInfoEnum.STATUS_2.getValue());
                break;
            //已完成
            case STATUS_4:
                wrapper.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                        .eq(YxStoreOrder::getStatus, OrderInfoEnum.STATUS_3.getValue());
                break;
            //退款中
            case STATUS_MINUS_1:
                wrapper.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_1.getValue());
                break;
            //已退款
            case STATUS_MINUS_2:
                wrapper.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_0.getValue())
                        .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_2.getValue());
                break;
            //退款
            case STATUS_MINUS_3:
                String[] strs = {"1", "2"};
                wrapper.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                        .in(YxStoreOrder::getRefundStatus, Arrays.asList(strs));
                break;
            default:
        }

        Page<YxStoreOrder> pageModel = new Page<>(page, limit);
        IPage<YxStoreOrder> pageList = yxStoreOrderMapper.selectPage(pageModel, wrapper);
        List<YxStoreOrderQueryVo> list = generator.convert(pageList.getRecords(), YxStoreOrderQueryVo.class);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list.stream()
                .map(this::handleOrder)
                .collect(Collectors.toList()));
        map.put("total", pageList.getTotal());
        map.put("totalPage", pageList.getPages());
        return map;

    }

    /**
     * chart图标统计
     *
     * @param cate
     * @param type
     * @return
     */
    // @Override
    @Deprecated
    /**
     public Map<String,Object> chartCount(int cate,int type) {
     int today = OrderUtil.dateToTimestampT(DateUtil.beginOfDay(new Date()));
     int yesterday = OrderUtil.dateToTimestampT(DateUtil.beginOfDay(DateUtil.
     yesterday()));
     int lastWeek = OrderUtil.dateToTimestampT(DateUtil.beginOfDay(DateUtil.lastWeek()));
     int nowMonth = OrderUtil.dateToTimestampT(DateUtil
     .beginOfMonth(new Date()));
     double price = 0d;
     List<ChartDataDto> list = null;
     LambdaQueryWrapper<YxStoreOrder> wrapper = new LambdaQueryWrapper<>();
     wrapper.eq("paid",1).eq("refund_status",0).eq("is_del",0);

     switch (OrderCountEnum.toType(cate)){
     case TODAY: //今天
     wrapper.ge("pay_time",today);
     break;
     case YESTERDAY: //昨天
     wrapper.lt("pay_time",today).ge("pay_time",yesterday);
     break;
     case WEEK: //上周
     wrapper.ge("pay_time",lastWeek);
     break;
     case MONTH: //本月
     wrapper.ge("pay_time",nowMonth);
     break;
     }
     if(type == 1){
     list = yxStoreOrderMapper.chartList(wrapper);
     price = yxStoreOrderMapper.todayPrice(wrapper);
     }else{
     list = yxStoreOrderMapper.chartListT(wrapper);
     price = yxStoreOrderMapper.selectCount(wrapper).doubleValue();
     }

     Map<String,Object> map = new LinkedHashMap<>();
     map.put("chart",list);
     map.put("time",price);
     return map;
     }
     **/

    /**
     * 获取 今日 昨日 本月 订单金额
     * @return ShoperOrderTimeDataVo
     */
    @Override
    public ShoperOrderTimeDataVo getShoperOrderTimeData() {

        Date today = DateUtil.beginOfDay(new Date());
        Date yesterday = DateUtil.beginOfDay(DateUtil.yesterday());
        Date nowMonth = DateUtil.beginOfMonth(new Date());
        Date lastWeek = DateUtil.beginOfDay(DateUtil.lastWeek());

        ShoperOrderTimeDataVo orderTimeDataVo = new ShoperOrderTimeDataVo();

        //今日成交额
        LambdaQueryWrapper<YxStoreOrder> wrapperOne = new LambdaQueryWrapper<>();
        wrapperOne
                .ge(YxStoreOrder::getPayTime, today)
                .eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue());
        orderTimeDataVo.setTodayPrice(yxStoreOrderMapper.todayPrice(wrapperOne));
        //今日订单数
        orderTimeDataVo.setTodayCount(yxStoreOrderMapper.selectCount(wrapperOne));

        //昨日成交额
        LambdaQueryWrapper<YxStoreOrder> wrapperTwo = new LambdaQueryWrapper<>();
        wrapperTwo
                .lt(YxStoreOrder::getPayTime, today)
                .ge(YxStoreOrder::getPayTime, yesterday)
                .eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue());
        orderTimeDataVo.setProPrice(yxStoreOrderMapper.todayPrice(wrapperTwo));
        //昨日订单数
        orderTimeDataVo.setProCount(yxStoreOrderMapper.selectCount(wrapperTwo));

        //本月成交额
        LambdaQueryWrapper<YxStoreOrder> wrapperThree = new LambdaQueryWrapper<>();
        wrapperThree
                .ge(YxStoreOrder::getPayTime, nowMonth)
                .eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue());
        orderTimeDataVo.setMonthPrice(yxStoreOrderMapper.todayPrice(wrapperThree));
        //本月订单数
        orderTimeDataVo.setMonthCount(yxStoreOrderMapper.selectCount(wrapperThree));

        //上周成交额
        LambdaQueryWrapper<YxStoreOrder> wrapperLastWeek = new LambdaQueryWrapper<>();
        wrapperLastWeek
                .lt(YxStoreOrder::getPayTime, today)
                .ge(YxStoreOrder::getPayTime, lastWeek)
                .eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue());
        orderTimeDataVo.setLastWeekPrice(yxStoreOrderMapper.todayPrice(wrapperLastWeek));
        //上周订单数
        orderTimeDataVo.setLastWeekCount(yxStoreOrderMapper.selectCount(wrapperLastWeek));


        return orderTimeDataVo;
    }

    /**
     * 订单每月统计数据
     *
     * @param page  page
     * @param limit list
     * @return List
     */
    @Override
    public List<OrderDataVo> getOrderDataPriceCount(int page, int limit) {
        Page<YxStoreOrder> pageModel = new Page<>(page, limit);
        return yxStoreOrderMapper.getOrderDataPriceList(pageModel);
    }

    /**
     * 获取某个用户的订单统计数据
     *
     * @param uid uid>0 取用户 否则取所有
     * @return UserOrderCountVo
     */
    @Override
    public UserOrderCountVo orderData(Long uid) {

        //订单支付没有退款 数量
        LambdaQueryWrapper<YxStoreOrder> wrapperOne = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperOne.eq(YxStoreOrder::getUid, uid);
        }
        wrapperOne.eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue());
        Long orderCount = yxStoreOrderMapper.selectCount(wrapperOne);

        //订单支付没有退款 支付总金额
        double sumPrice = yxStoreOrderMapper.sumPrice(uid);

        //订单待支付 数量
        LambdaQueryWrapper<YxStoreOrder> wrapperTwo = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperTwo.eq(YxStoreOrder::getUid, uid);
        }
        wrapperTwo.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_0.getValue())
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(YxStoreOrder::getStatus, OrderInfoEnum.STATUS_0.getValue());
        Long unpaidCount = yxStoreOrderMapper.selectCount(wrapperTwo);

        //订单待发货 数量
        LambdaQueryWrapper<YxStoreOrder> wrapperThree = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperThree.eq(YxStoreOrder::getUid, uid);
        }
        wrapperThree.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(YxStoreOrder::getStatus, OrderInfoEnum.STATUS_0.getValue());
        Long unshippedCount = yxStoreOrderMapper.selectCount(wrapperThree);

        //订单待收货 数量
        LambdaQueryWrapper<YxStoreOrder> wrapperFour = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperFour.eq(YxStoreOrder::getUid, uid);
        }
        wrapperFour.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(YxStoreOrder::getStatus, OrderInfoEnum.STATUS_1.getValue());
        Long receivedCount = yxStoreOrderMapper.selectCount(wrapperFour);

        //订单待评价 数量
        LambdaQueryWrapper<YxStoreOrder> wrapperFive = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperFive.eq(YxStoreOrder::getUid, uid);
        }
        wrapperFive.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(YxStoreOrder::getStatus, OrderInfoEnum.STATUS_2.getValue());
        Long evaluatedCount = yxStoreOrderMapper.selectCount(wrapperFive);

        //订单已完成 数量
        LambdaQueryWrapper<YxStoreOrder> wrapperSix = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperSix.eq(YxStoreOrder::getUid, uid);
        }
        wrapperSix.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(YxStoreOrder::getRefundStatus, OrderInfoEnum.REFUND_STATUS_0.getValue())
                .eq(YxStoreOrder::getStatus, OrderInfoEnum.STATUS_3.getValue());
        Long completeCount = yxStoreOrderMapper.selectCount(wrapperSix);

        //订单退款
        LambdaQueryWrapper<YxStoreOrder> wrapperSeven = new LambdaQueryWrapper<>();
        if (uid != null) {
            wrapperSeven.eq(YxStoreOrder::getUid, uid);
        }
        String[] strArr = {"1", "2"};
        wrapperSeven.eq(YxStoreOrder::getPaid, OrderInfoEnum.PAY_STATUS_1.getValue())
                .in(YxStoreOrder::getRefundStatus, Arrays.asList(strArr));
        Long refundCount = yxStoreOrderMapper.selectCount(wrapperSeven);
        //售后退款
        Long salesCount = storeAfterSalesService.lambdaQuery()
                .eq(Objects.nonNull(uid),StoreAfterSales::getUserId, uid)
                .count();

        return UserOrderCountVo.builder()
                .orderCount(orderCount)
                .sumPrice(sumPrice)
                .unpaidCount(unpaidCount)
                .unshippedCount(unshippedCount)
                .receivedCount(receivedCount)
                .evaluatedCount(evaluatedCount)
                .completeCount(completeCount)
                .refundCount(salesCount)
                .build();
    }

    /**
     * 处理订单返回的状态
     *
     * @param order order
     * @return YxStoreOrderQueryVo
     */
    @Override
    public YxStoreOrderQueryVo handleOrder(YxStoreOrderQueryVo order) {
        LambdaQueryWrapper<YxStoreOrderCartInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreOrderCartInfo::getOid, order.getId());
        List<YxStoreOrderCartInfo> cartInfos = orderCartInfoService.list(wrapper);

        List<YxStoreCartQueryVo> cartInfo = cartInfos.stream()
                .map(cart -> {
                    YxStoreCartQueryVo cartQueryVo = JSON.parseObject(cart.getCartInfo(), YxStoreCartQueryVo.class);
                    cartQueryVo.setUnique(cart.getUnique());
                    cartQueryVo.setIsReply(productReplyService.replyCount(cart.getUnique()));
                    return cartQueryVo;
                })
                .collect(Collectors.toList());
        order.setCartInfo(cartInfo);

        StatusDto statusDTO = new StatusDto();
        if (OrderStatusEnum.STATUS_0.getValue().equals(order.getPaid())) {
            //计算未支付到自动取消订 时间
            int offset = Integer.valueOf(String.valueOf(ShopConstants.ORDER_OUTTIME_UNPAY));
            Date time = DateUtil.offsetMinute(order.getCreateTime(), offset);
            statusDTO.set_class("nobuy");
            statusDTO.set_msg(StrUtil.format("请在{}前完成支付", DateUtil.formatDateTime(time)));
            statusDTO.set_type("0");
            statusDTO.set_title("未支付");
        } else if (OrderInfoEnum.REFUND_STATUS_1.getValue().equals(order.getRefundStatus())) {
            statusDTO.set_class("state-sqtk");
            statusDTO.set_msg("商家审核中,请耐心等待");
            statusDTO.set_type("-1");
            statusDTO.set_title("申请退款中");
        } else if (OrderInfoEnum.REFUND_STATUS_2.getValue().equals(order.getRefundStatus())) {
            statusDTO.set_class("state-sqtk");
            statusDTO.set_msg("已为您退款,感谢您的支持");
            statusDTO.set_type("-2");
            statusDTO.set_title("已退款");
        } else if (OrderInfoEnum.STATUS_0.getValue().equals(order.getStatus())) {
            // 拼团
            if (order.getPinkId() > 0) {
                if (pinkService.pinkIngCount(order.getPinkId()) > 0) {
                    statusDTO.set_class("state-nfh");
                    statusDTO.set_msg("待其他人参加拼团");
                    statusDTO.set_type("1");
                    statusDTO.set_title("拼团中");
                } else {
                    statusDTO.set_class("state-nfh");
                    statusDTO.set_msg("商家未发货,请耐心等待");
                    statusDTO.set_type("1");
                    statusDTO.set_title("未发货");
                }
            } else {
                if (OrderInfoEnum.SHIPPIING_TYPE_1.getValue().equals(order.getShippingType())) {
                    statusDTO.set_class("state-nfh");
                    statusDTO.set_msg("商家未发货,请耐心等待");
                    statusDTO.set_type("1");
                    statusDTO.set_title("未发货");
                } else {
                    statusDTO.set_class("state-nfh");
                    statusDTO.set_msg("待核销,请到核销点进行核销");
                    statusDTO.set_type("1");
                    statusDTO.set_title("待核销");
                }
            }

        } else if (OrderInfoEnum.STATUS_1.getValue().equals(order.getStatus())) {
            statusDTO.set_class("state-ysh");
            statusDTO.set_msg("服务商已发货");
            statusDTO.set_type("2");
            statusDTO.set_title("待收货");
        } else if (OrderInfoEnum.STATUS_2.getValue().equals(order.getStatus())) {
            statusDTO.set_class("state-ypj");
            statusDTO.set_msg("已收货,快去评价一下吧");
            statusDTO.set_type("3");
            statusDTO.set_title("待评价");
        } else if (OrderInfoEnum.STATUS_3.getValue().equals(order.getStatus())) {
            statusDTO.set_class("state-ytk");
            statusDTO.set_msg("交易完成,感谢您的支持");
            statusDTO.set_type("4");
            statusDTO.set_title("交易完成");
        }

        if (PayTypeEnum.WEIXIN.getValue().equals(order.getPayType())) {
            statusDTO.set_payType("微信支付");
        } else if (PayTypeEnum.YUE.getValue().equals(order.getPayType())) {
            statusDTO.set_payType("余额支付");
        } else {
            statusDTO.set_payType("积分支付");
        }

        order.set_status(statusDTO);


        return order;
    }

    /**
     * 支付成功后操作
     *
     * @param orderId 订单号
     * @param payType 支付方式
     */
    @Override
    public void paySuccess(String orderId, String payType) {
        YxStoreOrderQueryVo orderInfo = getOrderInfo(orderId, null);

        //更新订单状态
        LambdaQueryWrapper<YxStoreOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreOrder::getOrderId, orderId);
        YxStoreOrder storeOrder = new YxStoreOrder();
        storeOrder.setPaid(OrderInfoEnum.PAY_STATUS_1.getValue());
        storeOrder.setPayType(payType);
        storeOrder.setPayTime(new Date());
        yxStoreOrderMapper.update(storeOrder, wrapper);

        //增加用户购买次数
        userService.incPayCount(orderInfo.getUid());
        //增加状态
        orderStatusService.create(orderInfo.getId(), OrderLogEnum.PAY_ORDER_SUCCESS.getValue(),
                OrderLogEnum.PAY_ORDER_SUCCESS.getDesc());
        //拼团
        if (orderInfo.getCombinationId() > 0) {
            pinkService.createPink(orderInfo);
        }

        //砍价
        if (orderInfo.getBargainId() > 0) {
            storeBargainUserService.setBargainUserStatus(orderInfo.getBargainId(), orderInfo.getUid());
        }

        YxUser userInfo = userService.getById(orderInfo.getUid());
        //增加流水
        String payTypeMsg = PayTypeEnum.WEIXIN.getDesc();
        if (PayTypeEnum.YUE.getValue().equals(payType)) {
            payTypeMsg = PayTypeEnum.YUE.getDesc();
        }
        billService.expend(userInfo.getUid(), "购买商品",
                BillDetailEnum.CATEGORY_1.getValue(),
                BillDetailEnum.TYPE_3.getValue(),
                orderInfo.getPayPrice().doubleValue(), userInfo.getNowMoney().doubleValue(),
                payTypeMsg + orderInfo.getPayPrice() + "元购买商品");


        //模板消息支付成功发布事件
        TemplateBean templateBean = TemplateBean.builder()
                .orderId(orderInfo.getOrderId())
                .price(orderInfo.getPayPrice().toString())
                .uid(orderInfo.getUid())
                .templateType(TemplateListenEnum.TYPE_1.getValue())
                .build();
        publisher.publishEvent(new TemplateEvent(this, templateBean));

    }


    /**
     * 支付宝支付
     *
     * @param orderId,支付宝支付 本系统已经集成，请自行根据下面找到代码整合下即可
     * @return
     */
    @Override
    public String aliPay(String orderId) throws Exception {
        AlipayConfig alipay = alipayService.find();
        if (ObjectUtil.isNull(alipay)) {
            throw new YshopException("请先配置支付宝");
        }
        YxStoreOrderQueryVo orderInfo = getOrderInfo(orderId, null);
        if (ObjectUtil.isNull(orderInfo)) {
            throw new YshopException("订单不存在");
        }
        if (OrderInfoEnum.PAY_STATUS_1.getValue().equals(orderInfo.getPaid())) {
            throw new YshopException("该订单已支付");
        }

        if (orderInfo.getPayPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new YshopException("该支付无需支付");
        }
        TradeVo trade = new TradeVo();
        trade.setOutTradeNo(orderId);
        String payUrl = alipayService.toPayAsWeb(alipay, trade);
        return payUrl;
    }


    /**
     * 余额支付
     *
     * @param orderId 订单号
     * @param uid     用户id
     */
    @Override
    public void yuePay(String orderId, Long uid) {
        YxStoreOrderQueryVo orderInfo = getOrderInfo(orderId, uid);
        if (ObjectUtil.isNull(orderInfo)) {
            throw new YshopException("订单不存在");
        }

        if (OrderInfoEnum.PAY_STATUS_1.getValue().equals(orderInfo.getPaid())) {
            throw new YshopException("该订单已支付");
        }

        YxUserQueryVo userInfo = userService.getYxUserById(uid);

        if (userInfo.getNowMoney().compareTo(orderInfo.getPayPrice()) < 0) {
            throw new YshopException("余额不足");
        }

        userService.decPrice(uid, orderInfo.getPayPrice());

        //支付成功后处理
        this.paySuccess(orderInfo.getOrderId(), PayTypeEnum.YUE.getValue());
    }


    /**
     * 积分兑换
     *
     * @param orderId 订单号
     * @param uid     用户id
     */
    @Override
    public void integralPay(String orderId, Long uid) {
        YxStoreOrderQueryVo orderInfo = getOrderInfo(orderId, uid);
        if (ObjectUtil.isNull(orderInfo)) {
            throw new YshopException("订单不存在");
        }

        if (OrderInfoEnum.PAY_STATUS_1.getValue().equals(orderInfo.getPaid())) {
            throw new YshopException("该订单已支付");
        }
        orderInfo = handleOrder(orderInfo);
        orderInfo.getCartInfo().forEach(cart -> {
            if (cart.getProductInfo().getIsIntegral() == 0) {
                throw new YshopException("该商品不为积分商品");
            }
        });
        YxUser userInfo = userService.getById(uid);

        if (userInfo.getIntegral().compareTo(orderInfo.getPayIntegral()) < 0) {
            throw new YshopException("积分不足");
        }

        //扣除积分
        //userService.decIntegral(uid,orderInfo.getPayIntegral().doubleValue());
        BigDecimal newIntegral = NumberUtil.sub(userInfo.getIntegral(), orderInfo.getPayIntegral());
        userInfo.setIntegral(newIntegral);
        userService.updateById(userInfo);
        //增加流水
        billService.expend(userInfo.getUid(), "兑换商品", BillDetailEnum.CATEGORY_2.getValue(),
                BillDetailEnum.TYPE_8.getValue(),
                orderInfo.getPayIntegral().doubleValue(),
                newIntegral.doubleValue(),
                "兑换商品扣除" + orderInfo.getPayIntegral().doubleValue() + "积分");
        //支付成功后处理
        this.paySuccess(orderInfo.getOrderId(), PayTypeEnum.INTEGRAL.getValue());
    }


    /**
     * 订单信息
     *
     * @param unique 唯一值或者单号
     * @param uid    用户id
     * @return YxStoreOrderQueryVo
     */
    @Override
    public YxStoreOrderQueryVo getOrderInfo(String unique, Long uid) {
        LambdaQueryWrapper<YxStoreOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(
                i -> i.eq(YxStoreOrder::getOrderId, unique).or().eq(YxStoreOrder::getUnique, unique).or()
                        .eq(YxStoreOrder::getExtendOrderId, unique));
        if (uid != null) {
            wrapper.eq(YxStoreOrder::getUid, uid);
        }

        return generator.convert(yxStoreOrderMapper.selectOne(wrapper), YxStoreOrderQueryVo.class);
    }


    /**
     * 奖励积分
     *
     * @param order 订单
     */
    private void gainUserIntegral(YxStoreOrderQueryVo order) {
        if (order.getGainIntegral().compareTo(BigDecimal.ZERO) > 0) {
            YxUser user = userService.getById(order.getUid());

            BigDecimal newIntegral = NumberUtil.add(user.getIntegral(), order.getGainIntegral());
            user.setIntegral(newIntegral);
            user.setUid(order.getUid());
            userService.updateById(user);

            //增加流水
            billService.income(user.getUid(), "购买商品赠送积分", BillDetailEnum.CATEGORY_2.getValue(),
                    BillDetailEnum.TYPE_9.getValue(),
                    order.getGainIntegral().doubleValue(),
                    newIntegral.doubleValue(),
                    "购买商品赠送" + order.getGainIntegral() + "积分", order.getId().toString());
        }
    }

    /**
     * 减库存增加销量
     *
     * @param cartInfo 购物车
     */
    public void deStockIncSale(List<YxStoreCartQueryVo> cartInfo) {
        for (YxStoreCartQueryVo storeCartVO : cartInfo) {
            Long combinationId = storeCartVO.getCombinationId();
            Long seckillId = storeCartVO.getSeckillId();
            Long bargainId = storeCartVO.getBargainId();
            if (combinationId != null && combinationId > 0) {
                productService.decProductStock(storeCartVO.getCartNum(), storeCartVO.getProductId(),
                        storeCartVO.getProductAttrUnique(), combinationId, ProductTypeEnum.COMBINATION.getValue());
            } else if (seckillId != null && seckillId > 0) {
                productService.decProductStock(storeCartVO.getCartNum(), storeCartVO.getProductId(),
                        storeCartVO.getProductAttrUnique(), seckillId, ProductTypeEnum.SECKILL.getValue());
            } else if (bargainId != null && bargainId > 0) {
                storeBargainService.decStockIncSales(storeCartVO.getCartNum(), bargainId);
            } else {
                productService.decProductStock(storeCartVO.getCartNum(), storeCartVO.getProductId(),
                        storeCartVO.getProductAttrUnique(), 0L, "");
            }
        }
    }


    /**
     * 积分抵扣
     *
     * @param userInfo       用户信息
     * @param usedIntegral   使用得积分
     * @param deductionPrice 抵扣的金额
     */
    private void decIntegral(YxUser userInfo, double usedIntegral, double deductionPrice) {
        userService.decIntegral(userInfo.getUid(), usedIntegral);
        billService.expend(userInfo.getUid(), "积分抵扣", BillDetailEnum.CATEGORY_2.getValue(),
                BillDetailEnum.TYPE_8.getValue(), usedIntegral, userInfo.getIntegral().doubleValue(),
                "购买商品使用" + usedIntegral + "积分抵扣" + deductionPrice + "元");
    }

    /**
     * 计算奖励的积分
     *
     * @param cartInfo cartInfo
     * @return double
     */
    private BigDecimal getGainIntegral(List<YxStoreCartQueryVo> cartInfo) {
        BigDecimal gainIntegral = BigDecimal.ZERO;
        for (YxStoreCartQueryVo cart : cartInfo) {
            if (cart.getCombinationId() > 0 || cart.getSeckillId() > 0 || cart.getBargainId() > 0) {
                continue;
            }
            BigDecimal cartInfoGainIntegral = BigDecimal.ZERO;
            Double gain = cart.getProductInfo().getGiveIntegral().doubleValue();
            if (gain > 0) {
                cartInfoGainIntegral = NumberUtil.round(NumberUtil.mul(cart.getCartNum(), gain), 2);
            }
            gainIntegral = NumberUtil.add(gainIntegral, cartInfoGainIntegral);
        }
        return gainIntegral;
    }


    /**
     * 退回优惠券
     *
     * @param order 订单
     */
    private void regressionCoupon(YxStoreOrderQueryVo order, Integer type) {
        if (type == 0) {
            if (OrderInfoEnum.PAY_STATUS_1.getValue().equals(order.getPaid())
                    || OrderStatusEnum.STATUS_MINUS_2.getValue().equals(order.getStatus())) {
                return;
            }
        } else {
            if (!(OrderInfoEnum.PAY_STATUS_1.getValue().equals(order.getPaid())
                    && OrderInfoEnum.REFUND_STATUS_2.getValue().equals(order.getRefundStatus()))) {
                return;
            }
        }

        if (order.getCouponId() != null && order.getCouponId() > 0) {
            YxStoreCouponUser couponUser = couponUserService
                    .getOne(Wrappers.<YxStoreCouponUser>lambdaQuery()
                            .eq(YxStoreCouponUser::getId, order.getCouponId())
                            .eq(YxStoreCouponUser::getStatus, CouponEnum.STATUS_1.getValue())
                            .eq(YxStoreCouponUser::getUid, order.getUid()));

            if (ObjectUtil.isNotNull(couponUser)) {
                YxStoreCouponUser storeCouponUser = new YxStoreCouponUser();
                storeCouponUser.setStatus(CouponEnum.STATUS_0.getValue());
                storeCouponUser.setUseTime(null);
                couponUserService.update(storeCouponUser, Wrappers.<YxStoreCouponUser>lambdaQuery()
                        .eq(YxStoreCouponUser::getId, order.getCouponId())
                        .eq(YxStoreCouponUser::getUid, order.getUid()));
            }
        }

    }

    /**
     * 退回库存
     *
     * @param order 订单
     */
    private void regressionStock(YxStoreOrderQueryVo order, Integer type) {
        if (type == 0) {
            if (OrderInfoEnum.PAY_STATUS_1.getValue().equals(order.getPaid())
                    || OrderStatusEnum.STATUS_MINUS_2.getValue().equals(order.getStatus())) {
                return;
            }
        } else {
            if (!(OrderInfoEnum.PAY_STATUS_1.getValue().equals(order.getPaid())
                    && OrderInfoEnum.REFUND_STATUS_2.getValue().equals(order.getRefundStatus()))) {
                return;
            }
        }
        LambdaQueryWrapper<YxStoreOrderCartInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(YxStoreOrderCartInfo::getCartId, Arrays.asList(order.getCartId().split(",")));

        List<YxStoreOrderCartInfo> cartInfoList = orderCartInfoService.list(wrapper);
        for (YxStoreOrderCartInfo cartInfo : cartInfoList) {
            YxStoreCartQueryVo cart = JSONObject.parseObject(cartInfo.getCartInfo()
                    , YxStoreCartQueryVo.class);
            if (order.getCombinationId() != null && order.getCombinationId() > 0) {//拼团
                productService.incProductStock(cart.getCartNum(), cart.getProductId(), cart.getProductAttrUnique(), order.getCombinationId(), ProductTypeEnum.COMBINATION.getValue());
            } else if (order.getSeckillId() != null && order.getSeckillId() > 0) {//秒杀
                productService.incProductStock(cart.getCartNum(), cart.getProductId(), cart.getProductAttrUnique(), order.getSeckillId(), ProductTypeEnum.SECKILL.getValue());
            } else if (order.getBargainId() != null && order.getBargainId() > 0) {//砍价
                storeBargainService.incStockDecSales(cart.getCartNum(), order.getBargainId());
            } else {
                productService.incProductStock(cart.getCartNum(), cart.getProductId()
                        , cart.getProductAttrUnique(), 0L, null);
            }

        }
    }

    /**
     * 退回积分
     *
     * @param order 订单
     */
    private void regressionIntegral(YxStoreOrderQueryVo order, Integer type) {
        if (type == 0) {
            if (OrderInfoEnum.PAY_STATUS_1.getValue().equals(order.getPaid())
                    || OrderStatusEnum.STATUS_MINUS_2.getValue().equals(order.getStatus())) {
                return;
            }
        } else {
            if (!(OrderInfoEnum.PAY_STATUS_1.getValue().equals(order.getPaid())
                    && OrderInfoEnum.REFUND_STATUS_2.getValue().equals(order.getRefundStatus()))) {
                return;
            }
        }

        if (order.getPayIntegral().compareTo(BigDecimal.ZERO) > 0) {
            order.setUseIntegral(order.getPayIntegral());
        }
        if (order.getUseIntegral().compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        if (!OrderStatusEnum.STATUS_MINUS_2.getValue().equals(order.getStatus())
                && !OrderInfoEnum.REFUND_STATUS_2.getValue().equals(order.getRefundStatus())
                && order.getBackIntegral().compareTo(BigDecimal.ZERO) > 0) {
            return;
        }

        YxUser yxUser = userService.getById(order.getUid());

        //增加积分
        BigDecimal newIntegral = NumberUtil.add(order.getUseIntegral(), yxUser.getIntegral());
        yxUser.setIntegral(newIntegral);
        userService.updateById(yxUser);

        //增加流水
        billService.income(yxUser.getUid(), "积分回退", BillDetailEnum.CATEGORY_2.getValue(),
                BillDetailEnum.TYPE_8.getValue(),
                order.getUseIntegral().doubleValue(),
                newIntegral.doubleValue(),
                "购买商品失败,回退积分" + order.getUseIntegral(), order.getId().toString());

        //更新回退积分
        YxStoreOrder storeOrder = new YxStoreOrder();
        storeOrder.setBackIntegral(order.getUseIntegral());
        storeOrder.setId(order.getId());
        yxStoreOrderMapper.updateById(storeOrder);
    }

    /**
     * 获取订单缓存
     *
     * @param uid uid
     * @param key key
     * @return CacheDto
     */
    private CacheDto getCacheOrderInfo(Long uid, String key) {
        Object obj = redisUtils.get(ShopConstants.YSHOP_ORDER_CACHE_KEY + uid + key);
        if (obj == null) {
            return null;
        }
        return JSON.parseObject(obj.toString(), CacheDto.class);
    }


    /**
     * 删除订单缓存
     *
     * @param uid uid
     * @param key key
     */
    private void delCacheOrderInfo(Long uid, String key) {
        redisUtils.del(ShopConstants.YSHOP_ORDER_CACHE_KEY + uid + key);
    }

    /**
     * 缓存订单
     *
     * @param uid        uid
     * @param cartInfo   cartInfo
     * @param priceGroup priceGroup
     * @param other      other
     * @return string
     */
    private String cacheOrderInfo(Long uid, List<YxStoreCartQueryVo> cartInfo, PriceGroupDto priceGroup, OtherDto other) {
        String key = IdUtil.simpleUUID();
        CacheDto cacheDTO = new CacheDto();
        cacheDTO.setCartInfo(cartInfo);
        cacheDTO.setPriceGroup(priceGroup);
        cacheDTO.setOther(other);
        redisUtils.set(ShopConstants.YSHOP_ORDER_CACHE_KEY + uid + key,
                JSON.toJSONString(cacheDTO),
                ShopConstants.YSHOP_ORDER_CACHE_TIME);
        return key;
    }

    /**
     * 获取订单价格
     *
     * @param cartInfo 购物车列表
     * @return PriceGroupDto
     */
    private PriceGroupDto getOrderPriceGroup(List<YxStoreCartQueryVo> cartInfo, YxUserAddress userAddress) {

        BigDecimal storePostage = BigDecimal.ZERO;


        String storeFreePostageStr = systemConfigService.getData(SystemConfigConstants.STORE_FREE_POSTAGE);//满额包邮
        BigDecimal storeFreePostage = BigDecimal.ZERO;
        if (NumberUtil.isNumber(storeFreePostageStr) && StrUtil.isNotBlank(storeFreePostageStr)) {
            storeFreePostage = new BigDecimal(storeFreePostageStr);
        }


        BigDecimal totalPrice = this.getOrderSumPrice(cartInfo, "truePrice");//获取订单总金额
        BigDecimal costPrice = this.getOrderSumPrice(cartInfo, "costPrice");//获取订单成本价
        BigDecimal vipPrice = this.getOrderSumPrice(cartInfo, "vipTruePrice");//获取订单会员优惠金额
        BigDecimal payIntegral = this.getOrderSumPrice(cartInfo, "payIntegral");//获取订单需要的积分

        //如果设置满包邮0 表示全局包邮，如果设置大于0表示满这价格包邮，否则走运费模板算法
        if (storeFreePostage.compareTo(BigDecimal.ZERO) != 0 && totalPrice.compareTo(storeFreePostage) <= 0) {
            storePostage = this.handlePostage(cartInfo, userAddress);
        }
        if (cartInfo.size() == 1 && cartInfo.get(0).getProductInfo().getIsIntegral() != null
                && cartInfo.get(0).getProductInfo().getIsIntegral() == 1) {
            totalPrice = BigDecimal.ZERO;
        }

        PriceGroupDto priceGroupDTO = new PriceGroupDto();
        priceGroupDTO.setStorePostage(storePostage);
        priceGroupDTO.setStoreFreePostage(storeFreePostage);
        priceGroupDTO.setTotalPrice(totalPrice);
        priceGroupDTO.setCostPrice(costPrice);
        priceGroupDTO.setVipPrice(vipPrice);
        priceGroupDTO.setPayIntegral(payIntegral);
        return priceGroupDTO;
    }


    /**
     * 根据运费模板算法返回邮费
     *
     * @param cartInfo    购物车
     * @param userAddress 地址
     * @return double
     */
    private BigDecimal handlePostage(List<YxStoreCartQueryVo> cartInfo, YxUserAddress userAddress) {
        BigDecimal storePostage = BigDecimal.ZERO;
        if (userAddress != null) {
            if (userAddress.getCityId() == null) {
                return storePostage;
            }
            //城市包括默认
            int cityId = userAddress.getCityId();
            List<Integer> citys = new ArrayList<>();
            citys.add(cityId);
            citys.add(0);

            List<YxStoreProductQueryVo> storeProductVOList = cartInfo
                    .stream()
                    .map(YxStoreCartQueryVo::getProductInfo)
                    .collect(Collectors.toList());
            List<Integer> tempIdS = storeProductVOList
                    .stream()
                    .map(YxStoreProductQueryVo::getTempId)
                    .collect(Collectors.toList());


            //获取商品用到的运费模板
            List<YxShippingTemplates> shippingTemplatesList = shippingTemplatesService
                    .list(Wrappers.<YxShippingTemplates>lambdaQuery()
                            .in(YxShippingTemplates::getId, tempIdS));
            //获取运费模板区域列表按照城市排序
            List<YxShippingTemplatesRegion> shippingTemplatesRegionList = shippingTemplatesRegionService
                    .list(Wrappers.<YxShippingTemplatesRegion>lambdaQuery()
                            .in(YxShippingTemplatesRegion::getTempId, tempIdS)
                            .in(YxShippingTemplatesRegion::getCityId, citys)
                            .orderByAsc(YxShippingTemplatesRegion::getCityId));
            //提取运费模板类型
            Map<Integer, Integer> shippingTemplatesMap = shippingTemplatesList
                    .stream()
                    .collect(Collectors.toMap(YxShippingTemplates::getId,
                            YxShippingTemplates::getType));
            //提取运费模板有相同值覆盖
            Map<Integer, YxShippingTemplatesRegion> shippingTemplatesRegionMap =
                    shippingTemplatesRegionList.stream()
                            .collect(Collectors.toMap(YxShippingTemplatesRegion::getTempId,
                                    YxShippingTemplatesRegion -> YxShippingTemplatesRegion,
                                    (key1, key2) -> key2));


            Map<Integer, TemplateDto> templateDTOMap = new HashMap<>();
            for (YxStoreCartQueryVo storeCartVO : cartInfo) {
                Integer tempId = storeCartVO.getProductInfo().getTempId();

                //处理拼团等营销商品没有设置运费模板
                if (tempId == null) {
                    return storePostage;
                }

                //根据模板类型获取相应的数量
                double num = 0d;
                if (ShippingTempEnum.TYPE_1.getValue().equals(shippingTemplatesMap.get(tempId))) {
                    num = storeCartVO.getCartNum().doubleValue();
                } else if (ShippingTempEnum.TYPE_2.getValue().equals(shippingTemplatesMap.get(tempId))) {
                    num = NumberUtil.mul(storeCartVO.getCartNum(),
                            storeCartVO.getProductInfo().getAttrInfo().getWeight()).doubleValue();
                } else if (ShippingTempEnum.TYPE_3.getValue().equals(shippingTemplatesMap.get(tempId))) {
                    num = NumberUtil.mul(storeCartVO.getCartNum(),
                            storeCartVO.getProductInfo().getAttrInfo().getVolume()).doubleValue();
                }

                YxShippingTemplatesRegion shippingTemplatesRegion = shippingTemplatesRegionMap.get(tempId);
                BigDecimal price = NumberUtil.round(NumberUtil.mul(storeCartVO.getCartNum(),
                        storeCartVO.getTruePrice()), 2);
                if (!templateDTOMap.containsKey(tempId)) {
                    TemplateDto templateDTO = TemplateDto.builder()
                            .number(num)
                            .price(price)
                            .first(shippingTemplatesRegion.getFirst().doubleValue())
                            .firstPrice(shippingTemplatesRegion.getFirstPrice())
                            ._continue(shippingTemplatesRegion.getContinues().doubleValue())
                            .continuePrice(shippingTemplatesRegion.getContinuePrice())
                            .tempId(tempId)
                            .cityId(cityId)
                            .build();
                    templateDTOMap.put(tempId, templateDTO);
                } else {
                    TemplateDto templateDTO = templateDTOMap.get(tempId);
                    templateDTO.setNumber(templateDTO.getNumber() + num);
                    templateDTO.setPrice(NumberUtil.add(templateDTO.getPrice().doubleValue(), price));
                }


            }

            //处理包邮情况
            jj: for (Map.Entry<Integer, TemplateDto> entry : templateDTOMap.entrySet()) {
                Integer mapKey = entry.getKey();
                TemplateDto mapValue = entry.getValue();

                Long count = shippingTemplatesFreeService.count(Wrappers.<YxShippingTemplatesFree>lambdaQuery()
                        .eq(YxShippingTemplatesFree::getTempId, mapValue.getTempId())
                        .eq(YxShippingTemplatesFree::getCityId, mapValue.getCityId())
                        .le(YxShippingTemplatesFree::getNumber, mapValue.getNumber())
                        .le(YxShippingTemplatesFree::getPrice, mapValue.getPrice()));
                //满足包邮条件剔除
                if (count > 0) {
                    templateDTOMap.remove(mapKey);
                    break jj;
                }
            }

            //处理区域邮费
            boolean isFirst = true; //用来是否多个产品的标识 false表示数量大于1
            for (TemplateDto templateDTO : templateDTOMap.values()) {
                if (isFirst) {//首件
                    //只满足首件
                    if (Double.compare(templateDTO.getNumber(), templateDTO.getFirst()) <= 0) {
                        storePostage = NumberUtil.round(NumberUtil.add(storePostage,
                                templateDTO.getFirstPrice()), 2);
                    } else {
                        BigDecimal firstPrice = NumberUtil.add(storePostage, templateDTO.getFirstPrice());

                        if (templateDTO.get_continue() <= 0) {
                            storePostage = firstPrice;
                        } else {
                            //续件平均值且向上取整数
                            double average = Math.ceil(NumberUtil.div(NumberUtil.sub(templateDTO.getNumber(),
                                    templateDTO.getFirst()),
                                    templateDTO.get_continue().doubleValue()));
                            //最终邮费
                            storePostage = NumberUtil.add(firstPrice, NumberUtil.mul(average,
                                    templateDTO.getContinuePrice()));
                        }

                    }

                    isFirst = false;
                } else {
                    //多件直接在以前的基数继续续建
                    if (templateDTO.get_continue() > 0) {
                        //续件平均值且向上取整数
                        double average = Math.ceil(
                                NumberUtil.div(
                                        templateDTO.getNumber(),
                                        templateDTO.get_continue()
                                )
                        );
                        //最终邮费
                        storePostage = NumberUtil.add(storePostage.doubleValue(), NumberUtil.mul(average,
                                templateDTO.getContinuePrice()));
                    }
                }
            }
        }


        return storePostage;
    }

    /**
     * 获取某字段价格
     *
     * @param cartInfo 购物车
     * @param key      key值
     * @return Double
     */
    private BigDecimal getOrderSumPrice(List<YxStoreCartQueryVo> cartInfo, String key) {
        BigDecimal sumPrice = BigDecimal.ZERO;

        if ("truePrice".equals(key)) {
            for (YxStoreCartQueryVo storeCart : cartInfo) {
                sumPrice = NumberUtil.add(sumPrice, NumberUtil.mul(storeCart.getCartNum(), storeCart.getTruePrice()));
            }
        } else if ("costPrice".equals(key)) {
            for (YxStoreCartQueryVo storeCart : cartInfo) {
                sumPrice = NumberUtil.add(sumPrice,
                        NumberUtil.mul(storeCart.getCartNum(), storeCart.getCostPrice()));
            }
        } else if ("vipTruePrice".equals(key)) {
            for (YxStoreCartQueryVo storeCart : cartInfo) {
                sumPrice = NumberUtil.add(sumPrice,
                        NumberUtil.mul(storeCart.getCartNum(), storeCart.getVipTruePrice()));
            }
        } else if ("payIntegral".equals(key)) {
            for (YxStoreCartQueryVo storeCart : cartInfo) {
                if (storeCart.getProductInfo().getAttrInfo() != null && storeCart.getProductInfo().getAttrInfo().getIntegral() != null) {
                    sumPrice = NumberUtil.add(sumPrice,
                            NumberUtil.mul(storeCart.getCartNum(), storeCart.getProductInfo().getAttrInfo().getIntegral()));
                }

            }
        }

        return sumPrice;
    }


    //=======================================================//


    /**
     * 根据商品分类统计订单占比
     *
     * @return OrderCountDto
     */
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

    /**
     * 首页订单/用户等统计
     *
     * @return OrderTimeDataDto
     */
    @Override
    public OrderTimeDataDto getOrderTimeData() {
        OrderTimeDataDto orderTimeDataDto = new OrderTimeDataDto();

        ShoperOrderTimeDataVo shoperOrderTimeData = this.getShoperOrderTimeData();

        BeanUtil.copyProperties(shoperOrderTimeData, orderTimeDataDto);


        orderTimeDataDto.setUserCount(userService.count());
        orderTimeDataDto.setOrderCount(this.count());
        orderTimeDataDto.setPriceCount(yxStoreOrderMapper.sumTotalPrice());
        orderTimeDataDto.setGoodsCount(productService.count());

        return orderTimeDataDto;
    }

    /**
     * 返回本月订单金额与数量
     *
     * @return map
     */
    @Override
    public Map<String, Object> chartCount() {
        Map<String, Object> map = new LinkedHashMap<>();
        Date nowMonth = DateUtil.beginOfMonth(new Date());

        map.put("chart", yxStoreOrderMapper.chartList(nowMonth));
        map.put("chartT", yxStoreOrderMapper.chartListT(nowMonth));

        return map;
    }

    @Override
    public void returnStock(String orderId) {
        YxStoreOrderQueryVo order = this.getOrderInfo(orderId, null);
        this.regressionIntegral(order, 1);
        this.regressionStock(order, 1);
        this.regressionCoupon(order, 1);
    }

    @Override
    public Map<String, Object> queryAll(YxStoreOrderQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreOrder> page = new PageInfo<>(queryAll(criteria));
        List<YxStoreOrderDto> storeOrderDTOS = new ArrayList<>();
        for (YxStoreOrder yxStoreOrder : page.getList()) {
            this.orderList(storeOrderDTOS, yxStoreOrder);

        }
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", storeOrderDTOS);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    public List<YxStoreOrder> queryAll(YxStoreOrderQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreOrder.class, criteria));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreOrder resources) {
        YxStoreOrder yxStoreOrder = this.getById(resources.getId());
        YxStoreOrder yxStoreOrder1 = this.getOne(new LambdaQueryWrapper<YxStoreOrder>()
                .eq(YxStoreOrder::getUnique, resources.getUnique()));
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
            map.put("唯一id(md5加密)类似id", yxStoreOrder.getUnique());
            map.put("管理员备注", yxStoreOrder.getRemark());
            map.put("商户ID", yxStoreOrder.getMerId());
            map.put(" isMerCheck", yxStoreOrder.getIsMerCheck());
            map.put("拼团产品id0一般产品", yxStoreOrder.getCombinationId());
            map.put("拼团id 0没有拼团", yxStoreOrder.getPinkId());
            map.put("成本价", yxStoreOrder.getCost());
            map.put("秒杀产品ID", yxStoreOrder.getSeckillId());
            map.put("砍价id", yxStoreOrder.getBargainId());
            map.put("核销码", yxStoreOrder.getVerifyCode());
            map.put("门店id", yxStoreOrder.getStoreId());
            map.put("配送方式 1=快递 ，2=门店自提", yxStoreOrder.getShippingType());
            map.put("支付渠道(0微信公众号1微信小程序)", yxStoreOrder.getIsChannel());
            map.put(" isRemind", yxStoreOrder.getIsRemind());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 获取订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public YxStoreOrderDto getOrderDetail(Long orderId) {
        YxStoreOrder yxStoreOrder = this.getById(orderId);
        if (ObjectUtil.isEmpty(yxStoreOrder)) {
            throw new BadRequestException("订单详情不存在");
        }
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

            String refundTime = DateUtil.formatDateTime(yxStoreOrder.getRefundReasonTime());
            String str = "<b style='color:#f124c7'>申请退款</b>" +
                    "<span>退款原因：" + yxStoreOrder.getRefundReasonWap() + "</span>" +
                    "<span>备注说明：" + yxStoreOrder.getRefundReasonWapExplain() + "</span>" +
                    "<span>退款时间：" + refundTime + "</span>";
            orderStatusStr = str;
        }
        yxStoreOrderDto.setStatusName(orderStatusStr);

        yxStoreOrderDto.set_status(_status);

        String payTypeName = OrderUtil.payTypeName(yxStoreOrder.getPayType()
                , yxStoreOrder.getPaid());
        yxStoreOrderDto.setPayTypeName(payTypeName);
        //订单类型处理
        yxStoreOrderDto.setPinkName(this.orderType(yxStoreOrder.getId()
                , yxStoreOrder.getPinkId(), yxStoreOrder.getCombinationId()
                , yxStoreOrder.getSeckillId(), yxStoreOrder.getBargainId(),
                yxStoreOrder.getShippingType(), yxStoreOrder.getPayIntegral()));

        //添加订单状态
        List<YxStoreOrderStatus> storeOrderStatuses = orderStatusService.list(new LambdaQueryWrapper<YxStoreOrderStatus>()
                .eq(YxStoreOrderStatus::getOid, yxStoreOrder.getId()));
        List<YxStoreOrderStatusDto> orderStatusDtos = generator.convert(storeOrderStatuses, YxStoreOrderStatusDto.class);
        yxStoreOrderDto.setStoreOrderStatusList(orderStatusDtos);
        //添加购物车详情
        List<YxStoreOrderCartInfo> cartInfos = storeOrderCartInfoService.list(
                new LambdaQueryWrapper<YxStoreOrderCartInfo>().eq(YxStoreOrderCartInfo::getOid, yxStoreOrder.getId()));
        List<StoreOrderCartInfoDto> cartInfoDTOS = new ArrayList<>();
        for (YxStoreOrderCartInfo cartInfo : cartInfos) {
            StoreOrderCartInfoDto cartInfoDTO = new StoreOrderCartInfoDto();
            cartInfoDTO.setCartInfoMap(JSON.parseObject(cartInfo.getCartInfo()));

            cartInfoDTOS.add(cartInfoDTO);
        }
        yxStoreOrderDto.setCartInfoList(cartInfoDTOS);
        //添加用户信息
        yxStoreOrderDto.setUserDTO(generator.convert(userService.getById(yxStoreOrder.getUid()), YxUserDto.class));
        if (yxStoreOrderDto.getUserDTO() == null) {
            yxStoreOrderDto.setUserDTO(new YxUserDto());
        }
        return yxStoreOrderDto;
    }

    @Override
    public Map<String, Object> queryAll(List<String> ids) {
        List<YxStoreOrder> yxStoreOrders = this.list(new LambdaQueryWrapper<YxStoreOrder>().in(YxStoreOrder::getOrderId, ids));
        List<YxStoreOrderDto> storeOrderDTOS = new ArrayList<>();
        for (YxStoreOrder yxStoreOrder : yxStoreOrders) {
            this.orderList(storeOrderDTOS, yxStoreOrder);
        }

        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", storeOrderDTOS);

        return map;
    }


    /**
     * 处理订单
     *
     * @param storeOrderDTOS 订单列表
     * @param yxStoreOrder   订单
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

            String refundTime = DateUtil.formatDateTime(yxStoreOrder.getRefundReasonTime());
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
        //订单类型处理
        yxStoreOrderDto.setPinkName(this.orderType(yxStoreOrder.getId()
                , yxStoreOrder.getPinkId(), yxStoreOrder.getCombinationId()
                , yxStoreOrder.getSeckillId(), yxStoreOrder.getBargainId(),
                yxStoreOrder.getShippingType(), yxStoreOrder.getPayIntegral()));

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


    /**
     * 订单状态处理
     *
     * @param id            订单id
     * @param pinkId        拼团id
     * @param combinationId 拼团产品id
     * @param seckillId     秒杀id
     * @param bargainId     砍价id
     * @param shippingType  发货类型
     * @return string
     */
    private String orderType(Long id, Long pinkId, Long combinationId, Long seckillId,
                             Long bargainId, Integer shippingType, BigDecimal payIntegral) {
        String str = "[普通订单]";
        if (pinkId > 0 || combinationId > 0) {
            YxStorePink storePink = storePinkService.getOne(new LambdaQueryWrapper<YxStorePink>()
                    .eq(YxStorePink::getOrderIdKey, id));
            if (ObjectUtil.isNull(storePink)) {
                str = "[拼团订单]";
            } else {
                if (OrderInfoEnum.PINK_STATUS_1.getValue().equals(storePink.getStatus())) {
                    str = "[拼团订单]正在进行中";
                } else if (OrderInfoEnum.PINK_STATUS_2.getValue().equals(storePink.getStatus())) {
                    str = "[拼团订单]已完成";
                } else if (OrderInfoEnum.PINK_STATUS_3.getValue().equals(storePink.getStatus())) {
                    str = "[拼团订单]未完成";
                } else {
                    str = "[拼团订单]历史订单";
                }

            }

        } else if (seckillId > 0) {
            str = "[秒杀订单]";
        } else if (bargainId > 0) {
            str = "[砍价订单]";
        }

        if (OrderInfoEnum.SHIPPIING_TYPE_2.getValue().equals(shippingType)) {
            str = "[核销订单]";
        }
        if (payIntegral.compareTo(new BigDecimal("0.00")) == 1) {
            str = "[积分兑换]";
        }
        return str;
    }


}
