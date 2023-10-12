/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service.impl;

import cn.hutool.core.util.NumberUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.OrderInfoEnum;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.activity.domain.YxStoreBargain;
import co.yixiang.modules.activity.domain.YxStoreBargainUser;
import co.yixiang.modules.activity.domain.YxStoreBargainUserHelp;
import co.yixiang.modules.activity.service.YxStoreBargainService;
import co.yixiang.modules.activity.service.YxStoreBargainUserHelpService;
import co.yixiang.modules.activity.service.YxStoreBargainUserService;
import co.yixiang.modules.activity.service.dto.YxStoreBargainDto;
import co.yixiang.modules.activity.service.dto.YxStoreBargainQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreBargainMapper;
import co.yixiang.modules.activity.vo.BargainCountVo;
import co.yixiang.modules.activity.vo.BargainVo;
import co.yixiang.modules.activity.vo.TopCountVo;
import co.yixiang.modules.activity.vo.YxStoreBargainQueryVo;
import co.yixiang.modules.order.domain.YxStoreOrder;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.vo.YxUserQueryVo;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.OrderUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
 * @author hupeng
 * @date 2020-05-13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreBargainServiceImpl extends BaseServiceImpl<YxStoreBargainMapper, YxStoreBargain> implements YxStoreBargainService {

    @Autowired
    private IGenerator generator;

    @Autowired
    private YxStoreBargainMapper yxStoreBargainMapper;


    @Autowired
    private YxStoreBargainUserService storeBargainUserService;
    @Autowired
    private YxStoreOrderService storeOrderService;
    @Autowired
    private YxStoreBargainUserHelpService storeBargainUserHelpService;



    /**
     * 退回库存销量
     * @param num 数量
     * @param bargainId 砍价产品id
     */
    @Override
    public void incStockDecSales(int num, Long bargainId) {
        yxStoreBargainMapper.incStockDecSales(num,bargainId);
    }

    /**
     * 增加销量 减少库存
     * @param num 数量
     * @param bargainId 砍价id
     */
    @Override
    public void decStockIncSales(int num, Long bargainId) {
        int res = yxStoreBargainMapper.decStockIncSales(num,bargainId);
        if(res == 0) {
            throw new YshopException("砍价产品库存不足");
        }
    }

//    @Override
//    public YxStoreBargain getBargain(int bargainId) {
//        QueryWrapper<YxStoreBargain> wrapper = new QueryWrapper<>();
//        int nowTime = OrderUtil.getSecondTimestampTwo();
//        wrapper.eq("id",bargainId).eq("is_del",0).eq("status",1)
//                .le("start_time",nowTime).ge("stop_time",nowTime);
//        return yxStoreBargainMapper.selectOne(wrapper);
//    }



    /**
     * 开始帮助好友砍价
     * @param bargainId 砍价产品id
     * @param bargainUserUid 开启砍价用户id
     * @param uid 当前用户id
     */
    @Override
    public void doHelp(Long bargainId, Long bargainUserUid, Long uid) {
        //开始真正的砍价
        YxStoreBargainUser storeBargainUser = storeBargainUserService
                .getBargainUserInfo(bargainId,bargainUserUid);


        YxStoreBargain storeBargain = this.getById(bargainId);
        //用户可以砍掉的金额 好友砍价之前获取可以砍价金额
        double coverPrice = NumberUtil.sub(storeBargainUser.getBargainPrice()
                ,storeBargainUser.getBargainPriceMin()).doubleValue();

        double random = 0d;
        if(coverPrice > 0 ){
            //用户剩余要砍掉的价格
            double surplusPrice = NumberUtil.sub(coverPrice,
                    storeBargainUser.getPrice()).doubleValue();
            if(surplusPrice == 0) {
                return;
            }


            //生成一个区间随机数
            random = OrderUtil.randomNumber(
                    storeBargain.getBargainMinPrice().doubleValue(),
                    storeBargain.getBargainMaxPrice().doubleValue());
            if(random > surplusPrice) {
                random = surplusPrice;
            }
        }


        //添加砍价帮助表
        YxStoreBargainUserHelp storeBargainUserHelp = YxStoreBargainUserHelp
                .builder()
                .uid(uid)
                .bargainId(bargainId)
                .bargainUserId(storeBargainUser.getId())
                .price(BigDecimal.valueOf(random))
                .build();
        storeBargainUserHelpService.save(storeBargainUserHelp);

        //累计砍掉的金额
        double totalPrice = NumberUtil.add(storeBargainUser.getPrice().doubleValue(),random);

        //更新砍价参与表
        YxStoreBargainUser bargainUser = YxStoreBargainUser
                .builder()
                .id(storeBargainUser.getId())
                .price(BigDecimal.valueOf(totalPrice))
                .build();

        storeBargainUserService.updateById(bargainUser);
    }

    /**
     * 顶部统计
     * @param bargainId 砍价商品id
     * @return TopCountVo
     */
    @Override
    public TopCountVo topCount(Long bargainId) {
        if(bargainId != null) {
            this.addBargainShare(bargainId);
        }
        return TopCountVo.builder()
                .lookCount(yxStoreBargainMapper.lookCount())
                .shareCount(yxStoreBargainMapper.shareCount())
                .userCount(storeBargainUserService.count())
                .build();
    }

    /**
     * 砍价 砍价帮总人数、剩余金额、进度条、已经砍掉的价格
     * @param bargainId 砍价商品id
     * @param uid 砍价用户id
     * @param myUid 当前用户id
     * @return BargainCountVo
     */
    @Override
    public BargainCountVo helpCount(Long bargainId, Long uid, Long myUid) {
        YxStoreBargainUser storeBargainUser = storeBargainUserService
                .getBargainUserInfo(bargainId,uid);
        // 是否帮别人砍,没砍是true，砍了false
        boolean userBargainStatus = true;
        if(storeBargainUser == null) {
            return BargainCountVo
                    .builder()
                    .count(0L)
                    .alreadyPrice(0d)
                    .status(0)
                    .pricePercent(0)
                    .price(0d)
                    .userBargainStatus(userBargainStatus)
                    .build();
        }


        Long helpCount = storeBargainUserHelpService.lambdaQuery()
                .eq(YxStoreBargainUserHelp::getBargainUserId,storeBargainUser.getId())
                .eq(YxStoreBargainUserHelp::getBargainId,bargainId)
                .eq(YxStoreBargainUserHelp::getUid,myUid)
                .count();

        if(helpCount > 0) {
            userBargainStatus = false;
        }


        Long count = storeBargainUserHelpService
                .getBargainUserHelpPeopleCount(bargainId,storeBargainUser.getId());
        //用户可以砍掉的价格
        double diffPrice = NumberUtil.sub(storeBargainUser.getBargainPrice()
                ,storeBargainUser.getBargainPriceMin()).doubleValue();
        //砍价进度条百分比
        int pricePercent = 0;
        if(diffPrice <= 0) {
            pricePercent = 100;
        }else{
            pricePercent = NumberUtil.round(NumberUtil.mul(NumberUtil.div(
                    storeBargainUser.getPrice(),diffPrice),100)
                    ,0).intValue();
        }



        //剩余的砍价金额
        double surplusPrice = NumberUtil.sub(diffPrice,storeBargainUser.getPrice()).doubleValue();

        return BargainCountVo
                .builder()
                .count(count)
                .alreadyPrice(storeBargainUser.getPrice().doubleValue())
                .status(storeBargainUser.getStatus())
                .pricePercent(pricePercent)
                .price(surplusPrice)
                .userBargainStatus(userBargainStatus)
                .build();
    }





    /**
     * 砍价详情
     * @param id 砍价id
     * @param yxUser 用户
     * @return BargainVo
     */
    @Override
    public BargainVo getDetail(Long id, YxUser yxUser) {

        Date now = new Date();
        YxStoreBargain storeBargain = this.lambdaQuery().eq(YxStoreBargain::getId,id)
                .eq(YxStoreBargain::getStatus, ShopCommonEnum.IS_STATUS_1.getValue())
                .le(YxStoreBargain::getStartTime,now)
                .ge(YxStoreBargain::getStopTime,now)
                .one();

        if(storeBargain == null) {
            throw new YshopException("砍价已结束");
        }

        this.addBargainLook(id);

        YxStoreBargainQueryVo storeBargainQueryVo = generator.convert(storeBargain,
                YxStoreBargainQueryVo.class);

        return  BargainVo
                .builder()
                .bargain(storeBargainQueryVo)
                .userInfo(generator.convert(yxUser, YxUserQueryVo.class))
                .bargainSumCount(this.getBargainPayCount(id))
                .build();
    }

    /**
     * 获取砍价商品列表
     * @param page page
     * @param limit limit
     * @return List
     */
    @Override
    public List<YxStoreBargainQueryVo> getList(int page, int limit) {
        Page<YxStoreBargain> pageModel = new Page<>(page, limit);
        LambdaQueryWrapper<YxStoreBargain> wrapper = new LambdaQueryWrapper<>();
        Date nowTime = new Date();
        wrapper.eq(YxStoreBargain::getStatus, ShopCommonEnum.IS_STATUS_1.getValue())
                .lt(YxStoreBargain::getStartTime,nowTime)
                .gt(YxStoreBargain::getStopTime,nowTime);

        List<YxStoreBargainQueryVo> yxStoreBargainQueryVos = generator.convert(
                yxStoreBargainMapper.selectPage(pageModel,wrapper).getRecords(),
                YxStoreBargainQueryVo.class);

        yxStoreBargainQueryVos.forEach(item->{
            item.setPeople(storeBargainUserService.getBargainUserCount(item.getId(),
                    OrderInfoEnum.BARGAIN_STATUS_1.getValue()));
        });

        return yxStoreBargainQueryVos;
    }


    /**
     * 增加分享次数
     * @param id 砍价商品id
     */
    private void addBargainShare(Long id) {
        yxStoreBargainMapper.addBargainShare(id);
    }

    /**
     * 增加浏览次数
     * @param id 砍价商品id
     */
    private void addBargainLook(Long id) {
        yxStoreBargainMapper.addBargainLook(id);
    }


    /**
     * 砍价支付成功订单数量
     * @param bargainId 砍价id
     * @return int
     */
    private Long getBargainPayCount(Long bargainId) {
        return storeOrderService.lambdaQuery().eq(YxStoreOrder::getBargainId,bargainId)
                .eq(YxStoreOrder::getPaid,OrderInfoEnum.PAY_STATUS_1.getValue())
                .eq(YxStoreOrder::getRefundStatus,OrderInfoEnum.REFUND_STATUS_0.getValue())
                .count();
    }


    //===================================================================//

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreBargainQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreBargain> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        List<YxStoreBargainDto> storeBargainDtoList = generator.convert(page.getList(), YxStoreBargainDto.class);
        for (YxStoreBargainDto storeBargainDto : storeBargainDtoList) {

            String statusStr = OrderUtil.checkActivityStatus(storeBargainDto.getStartTime(),
                    storeBargainDto.getStopTime(), storeBargainDto.getStatus());
            storeBargainDto.setStatusStr(statusStr);
        }
        map.put("content", storeBargainDtoList);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreBargain> queryAll(YxStoreBargainQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreBargain.class, criteria));
    }


    @Override
    public void download(List<YxStoreBargainDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreBargainDto yxStoreBargain : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("关联产品ID", yxStoreBargain.getProductId());
            map.put("砍价活动名称", yxStoreBargain.getTitle());
            map.put("砍价活动图片", yxStoreBargain.getImage());
            map.put("单位名称", yxStoreBargain.getUnitName());
            map.put("库存", yxStoreBargain.getStock());
            map.put("销量", yxStoreBargain.getSales());
            map.put("砍价产品轮播图", yxStoreBargain.getImages());
            map.put("砍价开启时间", yxStoreBargain.getStartTime());
            map.put("砍价结束时间", yxStoreBargain.getStopTime());
            map.put("砍价产品名称", yxStoreBargain.getStoreName());
            map.put("砍价金额", yxStoreBargain.getPrice());
            map.put("砍价商品最低价", yxStoreBargain.getMinPrice());
            map.put("每次购买的砍价产品数量", yxStoreBargain.getNum());
            map.put("用户每次砍价的最大金额", yxStoreBargain.getBargainMaxPrice());
            map.put("用户每次砍价的最小金额", yxStoreBargain.getBargainMinPrice());
            map.put("用户每次砍价的次数", yxStoreBargain.getBargainNum());
            map.put("砍价状态 0(到砍价时间不自动开启)  1(到砍价时间自动开启时间)", yxStoreBargain.getStatus());
            map.put("砍价详情", yxStoreBargain.getDescription());
            map.put("反多少积分", yxStoreBargain.getGiveIntegral());
            map.put("砍价活动简介", yxStoreBargain.getInfo());
            map.put("成本价", yxStoreBargain.getCost());
            map.put("排序", yxStoreBargain.getSort());
            map.put("是否推荐0不推荐1推荐", yxStoreBargain.getIsHot());
            map.put("是否包邮 0不包邮 1包邮", yxStoreBargain.getIsPostage());
            map.put("邮费", yxStoreBargain.getPostage());
            map.put("砍价规则", yxStoreBargain.getRule());
            map.put("砍价产品浏览量", yxStoreBargain.getLook());
            map.put("砍价产品分享量", yxStoreBargain.getShare());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 删除砍价海报
     *
     * @param name
     */
    @Override
    public void deleteBargainImg(String name) {
        baseMapper.deleteBargainImg(name);
    }
}
