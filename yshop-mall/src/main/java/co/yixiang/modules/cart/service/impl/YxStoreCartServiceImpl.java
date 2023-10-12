/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.cart.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.CartTypeEnum;
import co.yixiang.enums.OrderInfoEnum;
import co.yixiang.enums.ProductTypeEnum;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.activity.domain.YxStoreBargain;
import co.yixiang.modules.activity.service.YxStoreBargainService;
import co.yixiang.modules.activity.service.mapper.YxStoreBargainMapper;
import co.yixiang.modules.activity.service.mapper.YxStoreCombinationMapper;
import co.yixiang.modules.activity.service.mapper.YxStoreSeckillMapper;
import co.yixiang.modules.cart.domain.YxStoreCart;
import co.yixiang.modules.cart.service.YxStoreCartService;
import co.yixiang.modules.cart.service.dto.YxStoreCartDto;
import co.yixiang.modules.cart.service.dto.YxStoreCartQueryCriteria;
import co.yixiang.modules.cart.service.mapper.StoreCartMapper;
import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import co.yixiang.modules.order.service.dto.CountDto;
import co.yixiang.modules.product.domain.YxStoreProduct;
import co.yixiang.modules.product.domain.YxStoreProductAttrValue;
import co.yixiang.modules.product.service.YxStoreProductAttrService;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.modules.product.vo.YxStoreProductQueryVo;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.utils.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author hupeng
 * @date 2020-05-12
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCartServiceImpl extends BaseServiceImpl<StoreCartMapper, YxStoreCart> implements YxStoreCartService {

    @Autowired
    private IGenerator generator;

    @Autowired
    private StoreCartMapper yxStoreCartMapper;
    @Autowired
    private YxStoreSeckillMapper storeSeckillMapper;
    @Autowired
    private YxStoreBargainMapper yxStoreBargainMapper;
    @Autowired
    private YxStoreCombinationMapper storeCombinationMapper;
    @Autowired
    private YxStoreProductService productService;
    @Autowired
    private YxStoreProductAttrService productAttrService;
    @Autowired
    private YxStoreBargainService storeBargainService;
    @Autowired
    private YxUserService userService;


    /**
     * 删除购物车
     *
     * @param uid uid
     * @param ids 购物车id集合
     */
    @Override
    public void removeUserCart(Long uid, List<String> ids) {
        List<Long> newids = ids.stream().map(Long::new).collect(Collectors.toList());
        yxStoreCartMapper.delete(Wrappers.<YxStoreCart>lambdaQuery()
                .eq(YxStoreCart::getUid, uid)
                .in(YxStoreCart::getId, newids));
    }

    /**
     * 改购物车数量
     *
     * @param cartId  购物车id
     * @param cartNum 数量
     * @param uid     uid
     */
    @Override
    public void changeUserCartNum(Long cartId, int cartNum, Long uid) {
        YxStoreCart cart = this.lambdaQuery()
                .eq(YxStoreCart::getUid, uid)
                .eq(YxStoreCart::getId, cartId)
                .one();
        if (cart == null) {
            throw new YshopException("购物车不存在");
        }

        if (cartNum <= 0) {
            throw new YshopException("库存错误");
        }

        //普通商品库存
        int stock = productService.getProductStock(cart.getProductId()
                , cart.getProductAttrUnique(), "");
        if (stock < cartNum) {
            throw new YshopException("该产品库存不足" + cartNum);
        }

        if (cartNum == cart.getCartNum()) {
            return;
        }

        YxStoreCart storeCart = new YxStoreCart();
        storeCart.setCartNum(cartNum);
        storeCart.setId(cartId);

        yxStoreCartMapper.updateById(storeCart);


    }

    /**
     * 购物车列表
     *
     * @param uid     用户id
     * @param cartIds 购物车id，多个逗号隔开
     * @param status  0-购购物车列表
     * @return map valid-有效购物车 invalid-失效购物车
     */
    @Override
    public Map<String, Object> getUserProductCartList(Long uid, String cartIds, Integer status) {
       LambdaQueryWrapper<YxStoreCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreCart::getUid, uid)
                .eq(YxStoreCart::getIsPay, OrderInfoEnum.PAY_STATUS_0.getValue())
                .orderByDesc(YxStoreCart::getId);
        if (status == null) {
            wrapper.eq(YxStoreCart::getIsNew, CartTypeEnum.NEW_0.getValue());
        }
        if (StrUtil.isNotEmpty(cartIds)) {
            wrapper.in(YxStoreCart::getId, Arrays.asList(cartIds.split(",")));
        }
        List<YxStoreCart> carts = yxStoreCartMapper.selectList(wrapper);

        List<YxStoreCartQueryVo> valid = new ArrayList<>();
        List<YxStoreCartQueryVo> invalid = new ArrayList<>();

        for (YxStoreCart storeCart : carts) {
            YxStoreProductQueryVo storeProduct = null;
            if (storeCart.getCombinationId() != null && storeCart.getCombinationId() > 0) {
                storeProduct = ObjectUtil.clone(storeCombinationMapper.combinatiionInfo(storeCart.getCombinationId()));
            } else if (storeCart.getSeckillId() != null && storeCart.getSeckillId() > 0) {
                storeProduct = ObjectUtil.clone(storeSeckillMapper.seckillInfo(storeCart.getSeckillId()));
            } else if (storeCart.getBargainId() != null && storeCart.getBargainId() > 0) {
                storeProduct = ObjectUtil.clone(yxStoreBargainMapper.bargainInfo(storeCart.getBargainId()));
            } else {
                //必须得重新克隆创建一个新对象
                storeProduct = ObjectUtil.clone(productService
                        .getStoreProductById(storeCart.getProductId()));
            }

            YxStoreCartQueryVo storeCartQueryVo = generator.convert(storeCart, YxStoreCartQueryVo.class);

            if (ObjectUtil.isNull(storeProduct)) {
                this.removeById(storeCart.getId());
            } else if (ShopCommonEnum.SHOW_0.getValue().equals(storeProduct.getIsShow()) || (storeProduct.getStock() == 0 && StrUtil.isEmpty(storeCart.getProductAttrUnique()))) {
                storeCartQueryVo.setProductInfo(storeProduct);
                invalid.add(storeCartQueryVo);
            } else {
                if (StrUtil.isNotEmpty(storeCart.getProductAttrUnique())) {
                    YxStoreProductAttrValue productAttrValue = productAttrService
                            .uniqueByAttrInfo(storeCart.getProductAttrUnique());
                    if (ObjectUtil.isNull(productAttrValue) || productAttrValue.getStock() == 0) {
                        storeCartQueryVo.setProductInfo(storeProduct);
                        invalid.add(storeCartQueryVo);
                    } else {
                        storeProduct.setAttrInfo(productAttrValue);
                        storeCartQueryVo.setProductInfo(storeProduct);

                        //设置真实价格
                        //设置VIP价格
                        double vipPrice = userService.setLevelPrice(
                                productAttrValue.getPrice().doubleValue(), uid);
                        //砍价金额
                        if ( storeCart.getBargainId() > 0
                               ) {
                            vipPrice = storeProduct.getPrice().doubleValue();
                        }
                        //设置拼团价格
                       if(storeCart.getCombinationId() > 0 ){
                            vipPrice = productAttrValue.getPinkPrice().doubleValue();
                        }
                        //设置秒杀价格
                        if( storeCart.getSeckillId() > 0){
                            vipPrice = productAttrValue.getSeckillPrice().doubleValue();
                        }
                        storeCartQueryVo.setTruePrice(vipPrice);
                        //设置会员价
                        storeCartQueryVo.setVipTruePrice(productAttrValue.getPrice()
                                .doubleValue());
                        storeCartQueryVo.setCostPrice(productAttrValue.getCost()
                                .doubleValue());
                        storeCartQueryVo.setTrueStock(productAttrValue.getStock());

                        valid.add(storeCartQueryVo);

                    }
                } else {
                    //设置VIP价格,营销商品不参与
                    double vipPrice = userService.setLevelPrice(
                            storeProduct.getPrice().doubleValue(), uid);
                    if (storeCart.getCombinationId() > 0 || storeCart.getSeckillId() > 0
                            || storeCart.getBargainId() > 0) {
                        vipPrice = storeProduct.getPrice().doubleValue();
                    }

                    storeCartQueryVo.setTruePrice(vipPrice);
                    // 设置会员价
                    storeCartQueryVo.setVipTruePrice(0d);
                    storeCartQueryVo.setCostPrice(storeProduct.getCost()
                            .doubleValue());
                    storeCartQueryVo.setTrueStock(storeProduct.getStock());
                    storeCartQueryVo.setProductInfo(storeProduct);

                    valid.add(storeCartQueryVo);
                }
            }

        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("valid", valid);
        map.put("invalid", invalid);
        return map;
    }

    /**
     * 添加购物车
     * @param uid               用户id
     * @param productId         普通产品编号
     * @param cartNum           购物车数量
     * @param productAttrUnique 属性唯一值
     * @param isNew             1 加入购物车直接购买  0 加入购物车
     * @param combinationId     拼团id
     * @param seckillId         秒杀id
     * @param bargainId         砍价id
     * @return 购物车id
     */
    @Override
    public long addCart(Long uid, Long productId, Integer cartNum, String productAttrUnique,
                        Integer isNew, Long combinationId, Long seckillId, Long bargainId) {

        this.checkProductStock(uid, productId, cartNum, productAttrUnique, combinationId, seckillId, bargainId);
       LambdaQueryWrapper<YxStoreCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreCart::getUid, uid)
                .eq(YxStoreCart::getIsPay, OrderInfoEnum.PAY_STATUS_0.getValue())
                .eq(YxStoreCart::getProductId, productId)
                .eq(YxStoreCart::getIsNew, isNew)
                .eq(YxStoreCart::getProductAttrUnique, productAttrUnique)
                .eq(YxStoreCart::getBargainId, bargainId)
                .eq(YxStoreCart::getCombinationId, combinationId)
                .eq(YxStoreCart::getSeckillId, seckillId)
                .orderByDesc(YxStoreCart::getId)
                .last("limit 1");

        YxStoreCart cart = yxStoreCartMapper.selectOne(wrapper);

        YxStoreCart storeCart = YxStoreCart.builder()
                .cartNum(cartNum)
                .productAttrUnique(productAttrUnique)
                .productId(productId)
                .bargainId(bargainId)
                .combinationId(combinationId)
                .seckillId(seckillId)
                .isNew(isNew)
                .uid(uid)
                .build();
        if (cart != null) {
            if (CartTypeEnum.NEW_0.getValue().equals(isNew)) {
                storeCart.setCartNum(cartNum + cart.getCartNum());
            }
            storeCart.setId(cart.getId());
            yxStoreCartMapper.updateById(storeCart);
        } else {
            yxStoreCartMapper.insert(storeCart);
        }

        return storeCart.getId();
    }

    /**
     * 返回当前用户购物车总数量
     *
     * @param uid 用户id
     * @return int
     */
    @Override
    public int getUserCartNum(Long uid) {
        return yxStoreCartMapper.cartSum(uid);
    }

//    @Override
//    public YxStoreCartQueryVo getYxStoreCartById(Serializable id){
//        return yxStoreCartMapper.getYxStoreCartById(id);
//    }

    /**
     * 检测商品/秒杀/砍价/拼团库存
     *
     * @param uid               用户ID
     * @param productId         产品ID
     * @param cartNum           购买数量
     * @param productAttrUnique 商品属性Unique
     * @param combinationId     拼团产品ID
     * @param seckillId         秒杀产品ID
     * @param bargainId         砍价产品ID
     */
    @Override
    public void checkProductStock(Long uid, Long productId, Integer cartNum, String productAttrUnique,
                                  Long combinationId, Long seckillId, Long bargainId) {
        Date now = new Date();
        //拼团
        if (combinationId != null && combinationId > 0) {
            YxStoreProduct product = productService
                    .lambdaQuery().eq(YxStoreProduct::getId, productId)
                    .eq(YxStoreProduct::getIsShow, ShopCommonEnum.SHOW_1.getValue())
                    .one();
            if (product == null) {
                throw new YshopException("该产品已下架或删除");
            }

            int stock = productService.getProductStock(productId, productAttrUnique, ProductTypeEnum.PINK.getValue());
            if (stock < cartNum) {
                throw new YshopException(product.getStoreName() + "库存不足" + cartNum);
            }
            //秒杀
        } else if (seckillId != null && seckillId > 0) {
            YxStoreProduct product = productService
                    .lambdaQuery().eq(YxStoreProduct::getId, productId)
                    .eq(YxStoreProduct::getIsShow, ShopCommonEnum.SHOW_1.getValue())
                    .one();
            if (product == null) {
                throw new YshopException("该产品已下架或删除");
            }

            int stock = productService.getProductStock(productId, productAttrUnique, ProductTypeEnum.SECKILL.getValue());
            if (stock < cartNum) {
                throw new YshopException(product.getStoreName() + "库存不足" + cartNum);
            }
            //砍价
        } else if (bargainId != null && bargainId > 0) {
            YxStoreBargain yxStoreBargain = storeBargainService
                    .lambdaQuery().eq(YxStoreBargain::getId, bargainId)
                    .eq(YxStoreBargain::getStatus, ShopCommonEnum.IS_STATUS_1.getValue())
                    .le(YxStoreBargain::getStartTime, now)
                    .ge(YxStoreBargain::getStopTime, now)
                    .one();
            if (yxStoreBargain == null) {
                throw new YshopException("该产品已下架或删除");
            }
            if (yxStoreBargain.getStock() < cartNum) {
                throw new YshopException("该产品库存不足");
            }

        } else {
            YxStoreProduct product = productService
                    .lambdaQuery().eq(YxStoreProduct::getId, productId)
                    .eq(YxStoreProduct::getIsShow, ShopCommonEnum.SHOW_1.getValue())
                    .one();
            if (product == null) {
                throw new YshopException("该产品已下架或删除");
            }

            int stock = productService.getProductStock(productId, productAttrUnique, "");
            if (stock < cartNum) {
                throw new YshopException(product.getStoreName() + "库存不足" + cartNum);
            }
        }

    }


    //====================================================================//


    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCartQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCart> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxStoreCartDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCart> queryAll(YxStoreCartQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCart.class, criteria));
    }


    @Override
    public void download(List<YxStoreCartDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCartDto yxStoreCart : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户ID", yxStoreCart.getUid());
            map.put("类型", yxStoreCart.getType());
            map.put("商品ID", yxStoreCart.getProductId());
            map.put("商品属性", yxStoreCart.getProductAttrUnique());
            map.put("商品数量", yxStoreCart.getCartNum());
            map.put("添加时间", yxStoreCart.getAddTime());
            map.put("0 = 未购买 1 = 已购买", yxStoreCart.getIsPay());
            map.put("是否删除", yxStoreCart.getIsDel());
            map.put("是否为立即购买", yxStoreCart.getIsNew());
            map.put("拼团id", yxStoreCart.getCombinationId());
            map.put("秒杀产品ID", yxStoreCart.getSeckillId());
            map.put("砍价id", yxStoreCart.getBargainId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<CountDto> findCateName() {
        return yxStoreCartMapper.findCateName();
    }
}
