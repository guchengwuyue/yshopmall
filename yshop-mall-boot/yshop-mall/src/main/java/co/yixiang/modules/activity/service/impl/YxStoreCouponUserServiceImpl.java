/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.constant.ShopConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.CouponEnum;
import co.yixiang.enums.CouponGetEnum;
import co.yixiang.modules.activity.domain.YxStoreCoupon;
import co.yixiang.modules.activity.domain.YxStoreCouponUser;
import co.yixiang.modules.activity.service.YxStoreCouponService;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserDto;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCouponUserMapper;
import co.yixiang.modules.activity.vo.StoreCouponUserVo;
import co.yixiang.modules.activity.vo.YxStoreCouponUserQueryVo;
import co.yixiang.modules.cart.service.YxStoreCartService;
import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.utils.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import java.util.*;
import java.util.stream.Collectors;


/**
* @author hupeng
* @date 2020-05-13
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCouponUserServiceImpl extends BaseServiceImpl<YxStoreCouponUserMapper, YxStoreCouponUser> implements YxStoreCouponUserService {

    @Autowired
    private IGenerator generator;


    @Autowired
    private YxStoreCouponUserMapper yxStoreCouponUserMapper;

    @Autowired
    private YxUserService userService;
    @Autowired
    private YxStoreCouponService storeCouponService;
    @Autowired
    private YxStoreCartService yxStoreCartService;

    /**
     * 获取当前用户优惠券数量
     *
     * @param uid uid
     * @return int
     */
    @Override
    public Long getUserValidCouponCount(Long uid) {
        this.checkInvalidCoupon();
        return this.lambdaQuery()
                .eq(YxStoreCouponUser::getStatus, CouponEnum.STATUS_0.getValue())
                .eq(YxStoreCouponUser::getUid,uid)
                .count();
    }

    /**
     * 获取满足条件的可用优惠券
     * @param cartIds 购物车ids
     * @return list
     */
    @Override
    public List<StoreCouponUserVo> beUsableCouponList(Long uid,String cartIds) {

        Map<String, Object> cartGroup = yxStoreCartService.getUserProductCartList(uid,
                cartIds, ShopConstants.YSHOP_ONE_NUM);

        List<YxStoreCartQueryVo> cartInfo = (List<YxStoreCartQueryVo>)cartGroup.get("valid");

        BigDecimal sumPrice = BigDecimal.ZERO;
        for (YxStoreCartQueryVo storeCart : cartInfo) {
            sumPrice = NumberUtil.add(sumPrice,NumberUtil.mul(storeCart.getCartNum(),storeCart.getTruePrice()));
        }

        List<String> productIds = cartInfo.stream()
                .map(YxStoreCartQueryVo::getProductId)
                .map(Object::toString)
                .collect(Collectors.toList());


        return this.getUsableCouponList(uid, sumPrice.doubleValue(), productIds);
    }

    /**
     * 获取下单时候满足的优惠券
     * @param uid uid
     * @param price 总价格
     * @param productIds list
     * @return list
     */
    @Override
    public List<StoreCouponUserVo> getUsableCouponList(Long uid, double price, List<String> productIds) {
        Date now = new Date();
        List<StoreCouponUserVo> storeCouponUsers = yxStoreCouponUserMapper.selectCouponList(now, price, uid);
        return storeCouponUsers.stream()
                .filter(coupon ->
                        CouponEnum.TYPE_2.getValue().equals(coupon.getType()) ||
                                CouponEnum.TYPE_0.getValue().equals(coupon.getType())
                                || (CouponEnum.TYPE_1.getValue().equals(coupon.getType())
                                && isSame(Arrays.asList(coupon.getProductId().split(",")),productIds)))
                .collect(Collectors.toList());

    }




    /**
     * 获取用户优惠券
     * @param id 优惠券id
     * @param uid 用户id
     * @return YxStoreCouponUser
     */
    @Override
    public YxStoreCouponUser getCoupon(Integer id,Long uid) {
        return this.lambdaQuery()
                .eq(YxStoreCouponUser::getIsFail, CouponEnum.FALI_0.getValue())
                .eq(YxStoreCouponUser::getStatus,CouponEnum.STATUS_0.getValue())
                .eq(YxStoreCouponUser::getUid,uid)
                .eq(YxStoreCouponUser::getId,id)
                .one();
    }

    @Override
    public void useCoupon(int id) {
        YxStoreCouponUser couponUser = new YxStoreCouponUser();
        couponUser.setId((long)id);
        couponUser.setStatus(1);
        couponUser.setUseTime(new Date());
        yxStoreCouponUserMapper.updateById(couponUser);
    }



    /**
     * 获取用户优惠券
     * @param uid uid
     * @return list
     */
    @Override
    public List<YxStoreCouponUserQueryVo> getUserCoupon(Long uid) {
        //this.checkInvalidCoupon();
        List<YxStoreCouponUser> storeCouponUsers = yxStoreCouponUserMapper
                .selectList(Wrappers.<YxStoreCouponUser>lambdaQuery()
                        .eq(YxStoreCouponUser::getUid,uid));
        List<YxStoreCouponUserQueryVo> storeCouponUserQueryVoList = new ArrayList<>();
        long nowTime = System.currentTimeMillis();
        for (YxStoreCouponUser couponUser : storeCouponUsers) {
            YxStoreCouponUserQueryVo queryVo = generator.convert(couponUser,YxStoreCouponUserQueryVo.class);
            if(couponUser.getIsFail() == 1){
                queryVo.set_type(CouponEnum.USE_0.getValue());
                queryVo.set_msg("已失效");
            }else if (couponUser.getStatus() == 1){
                queryVo.set_type(CouponEnum.USE_0.getValue());
                queryVo.set_msg("已使用");
            }else if (couponUser.getStatus() == 2){
                queryVo.set_type(CouponEnum.USE_0.getValue());
                queryVo.set_msg("已过期");
            }else if(couponUser.getCreateTime().getTime() > nowTime || couponUser.getEndTime().getTime() < nowTime){
                queryVo.set_type(CouponEnum.USE_0.getValue());
                queryVo.set_msg("已过期");
            }else{
                queryVo.set_type(CouponEnum.USE_1.getValue());
                queryVo.set_msg("可使用");
            }

            storeCouponUserQueryVoList.add(queryVo);
        }
        return storeCouponUserQueryVoList;
    }

    /**
     * 添加优惠券记录
     * @param uid 用户id
     * @param cid 优惠券id
     */
    @Override
    public void addUserCoupon(Long uid, Integer cid) {
        YxStoreCoupon storeCoupon = storeCouponService.getById(cid);
        if(storeCoupon == null) {
            throw new YshopException("优惠劵不存在");
        }

        Date now = new Date();

        Date endTime = DateUtil.offsetDay(now,storeCoupon.getCouponTime());

        YxStoreCouponUser storeCouponUser = YxStoreCouponUser.builder()
                .cid(storeCoupon.getId())
                .uid(uid)
                .couponPrice(storeCoupon.getCouponPrice())
                .couponTitle(storeCoupon.getTitle())
                .useMinPrice(storeCoupon.getUseMinPrice())
                .endTime(endTime)
                .type(CouponGetEnum.GET.getValue())
                .build();

        this.save(storeCouponUser);

    }


    /**
     * 判断两个list是否有相同值
     * @param list1 list
     * @param list2 list
     * @return boolean
     */
    private boolean isSame(List<String> list1,List<String> list2){
        if(list2.isEmpty()) {
            return true;
        }
        list1 = new ArrayList<>(list1);
        list2 = new ArrayList<>(list2);
        list1.addAll(list2);
        int total = list1.size();

        List<String> newList = new ArrayList<>(new HashSet<>(list1));

        int newTotal = newList.size();


        return total > newTotal;
    }


    /**
     * 检查优惠券状态
     */
    private void checkInvalidCoupon() {
        Date nowTime = new Date();
       LambdaQueryWrapper<YxStoreCouponUser> wrapper= new LambdaQueryWrapper<>();
        wrapper.lt(YxStoreCouponUser::getEndTime,nowTime)
                .eq(YxStoreCouponUser::getStatus,CouponEnum.STATUS_0.getValue());
        YxStoreCouponUser couponUser = new YxStoreCouponUser();
        couponUser.setStatus(CouponEnum.STATUS_2.getValue());
        yxStoreCouponUserMapper.update(couponUser,wrapper);

    }



    //=========================================================================================================//

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCouponUserQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCouponUser> page = new PageInfo<>(queryAll(criteria));
        List<YxStoreCouponUserDto> storeOrderDTOS = generator.convert(page.getList(),YxStoreCouponUserDto.class);
        for (YxStoreCouponUserDto couponUserDTO : storeOrderDTOS) {
            couponUserDTO.setNickname(userService.getOne(new LambdaQueryWrapper<YxUser>()
                    .eq(YxUser::getUid,couponUserDTO.getUid())).getNickname());
        }
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",storeOrderDTOS);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCouponUser> queryAll(YxStoreCouponUserQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCouponUser.class, criteria));
    }
    @Override
    public void download(List<YxStoreCouponUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCouponUserDto yxStoreCouponUser : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("兑换的项目id", yxStoreCouponUser.getCid());
            map.put("优惠券所属用户", yxStoreCouponUser.getUid());
            map.put("优惠券名称", yxStoreCouponUser.getCouponTitle());
            map.put("优惠券的面值", yxStoreCouponUser.getCouponPrice());
            map.put("最低消费多少金额可用优惠券", yxStoreCouponUser.getUseMinPrice());
            map.put("优惠券创建时间", yxStoreCouponUser.getAddTime());
            map.put("优惠券结束时间", yxStoreCouponUser.getEndTime());
            map.put("使用时间", yxStoreCouponUser.getUseTime());
            map.put("获取方式", yxStoreCouponUser.getType());
            map.put("状态（0：未使用，1：已使用, 2:已过期）", yxStoreCouponUser.getStatus());
            map.put("是否有效", yxStoreCouponUser.getIsFail());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public Map<String, Object> getUserPCCoupon(Long uid, int page, int limit, Integer type) {
        Page<YxStoreCouponUser> yxStoreCouponUserPage = new Page<>(page, limit);
        yxStoreCouponUserMapper.selectPage(yxStoreCouponUserPage,Wrappers.<YxStoreCouponUser>lambdaQuery()
                        .eq(YxStoreCouponUser::getUid,uid).eq(YxStoreCouponUser::getStatus,type));

        List<YxStoreCouponUserQueryVo> storeCouponUserQueryVoList = new ArrayList<>();
        long nowTime = System.currentTimeMillis();
        for (YxStoreCouponUser couponUser : yxStoreCouponUserPage.getRecords()) {
            YxStoreCouponUserQueryVo queryVo = generator.convert(couponUser,YxStoreCouponUserQueryVo.class);
            if(couponUser.getIsFail() == 1){
                queryVo.set_type(CouponEnum.USE_0.getValue());
                queryVo.set_msg("已失效");
            }else if (couponUser.getStatus() == 1){
                queryVo.set_type(CouponEnum.USE_0.getValue());
                queryVo.set_msg("已使用");
            }else if (couponUser.getStatus() == 2){
                queryVo.set_type(CouponEnum.USE_0.getValue());
                queryVo.set_msg("已过期");
            }else if(couponUser.getCreateTime().getTime() > nowTime || couponUser.getEndTime().getTime() < nowTime){
                queryVo.set_type(CouponEnum.USE_0.getValue());
                queryVo.set_msg("已过期");
            }else{
                queryVo.set_type(CouponEnum.USE_1.getValue());
                queryVo.set_msg("可使用");
            }

            storeCouponUserQueryVoList.add(queryVo);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",storeCouponUserQueryVoList);
        map.put("total",yxStoreCouponUserPage.getTotal());
        map.put("totalPage",yxStoreCouponUserPage.getPages());
        return map;
    }
}
