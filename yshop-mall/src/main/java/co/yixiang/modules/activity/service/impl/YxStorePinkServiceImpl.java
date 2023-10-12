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
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.constant.ShopConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.OrderInfoEnum;
import co.yixiang.enums.PinkEnum;
import co.yixiang.modules.activity.domain.YxStoreCombination;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.activity.service.YxStoreVisitService;
import co.yixiang.modules.activity.service.dto.PinkAllDto;
import co.yixiang.modules.activity.service.dto.PinkDto;
import co.yixiang.modules.activity.service.dto.PinkUserDto;
import co.yixiang.modules.activity.service.dto.YxStorePinkDto;
import co.yixiang.modules.activity.service.dto.YxStorePinkQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCombinationMapper;
import co.yixiang.modules.activity.service.mapper.YxStorePinkMapper;
import co.yixiang.modules.activity.vo.PinkInfoVo;
import co.yixiang.modules.activity.vo.YxStoreCombinationQueryVo;
import co.yixiang.modules.activity.vo.YxStorePinkQueryVo;
import co.yixiang.modules.cart.domain.YxStoreCart;
import co.yixiang.modules.cart.service.YxStoreCartService;
import co.yixiang.modules.cart.vo.YxStoreCartQueryVo;
import co.yixiang.modules.order.domain.YxStoreOrder;
import co.yixiang.modules.order.service.YxStoreOrderService;
import co.yixiang.modules.order.vo.YxStoreOrderQueryVo;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.vo.YxUserQueryVo;
import co.yixiang.utils.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
* @author hupeng
* @date 2020-05-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStorePinkServiceImpl extends BaseServiceImpl<YxStorePinkMapper, YxStorePink> implements YxStorePinkService {

    @Autowired
    private IGenerator generator;
    @Autowired
    private YxStorePinkMapper yxStorePinkMapper;
    @Autowired
    private YxStoreCombinationMapper yxStoreCombinationMapper;
    @Autowired
    private YxStoreCombinationService combinationService;
    @Autowired
    private YxStoreOrderService storeOrderService;
    @Autowired
    private YxUserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private YxUserService yxUserService;

    @Autowired
    private YxStoreCartService yxStoreCartService;

    @Autowired
    private YxStoreVisitService yxStoreVisitService;


    /**
     * 取消拼团
     * @param uid 用户id
     * @param cid 团购产品id
     * @param pinkId 拼团id
     */
    @Override
    public void removePink(Long uid, Long cid, Long pinkId) {
        YxStorePink pink = this.lambdaQuery().eq(YxStorePink::getId,pinkId)
                .eq(YxStorePink::getUid,uid)
                .eq(YxStorePink::getCid,cid)
                .eq(YxStorePink::getKId,0)  //团长
                .eq(YxStorePink::getIsRefund,OrderInfoEnum.PINK_REFUND_STATUS_0.getValue())
                .eq(YxStorePink::getStatus,OrderInfoEnum.REFUND_STATUS_1.getValue())
                .gt(YxStorePink::getStopTime,new Date())
                .one();
        if(pink == null) {
            throw new YshopException("拼团不存在或已经取消");
        }

        PinkUserDto pinkUserDto = this.getPinkMemberAndPinK(pink);
        List<YxStorePink> pinkAll = pinkUserDto.getPinkAll();
        YxStorePink pinkT = pinkUserDto.getPinkT();
        List<Long> idAll = pinkUserDto.getIdAll();
        List<Long> uidAll = pinkUserDto.getUidAll();
        int count = pinkUserDto.getCount();
        if(count < 1){
            this.pinkComplete(uidAll,idAll,uid,pinkT);
            throw new YshopException("拼团已完成，无法取消");
        }
        //如果团长取消拼团，团队还有人，就把后面的人作为下一任团长
        YxStorePink nextPinkT = null;
        if(pinkAll.size() > 0){
            nextPinkT = pinkAll.get(0);
        }

        //先退团长的money
        storeOrderService.orderApplyRefund("","","拼团取消开团",pinkT.getOrderId(),pinkT.getUid());
        this.orderPinkFailAfter(pinkT.getUid(),pinkT.getId());

        //把团长下个人设置为团长
        if(ObjectUtil.isNotNull(nextPinkT)){
           LambdaQueryWrapper<YxStorePink> wrapperO = new LambdaQueryWrapper<>();
            YxStorePink storePinkO = new YxStorePink();
            storePinkO.setKId(0L); //设置团长
            storePinkO.setStatus(OrderInfoEnum.PINK_STATUS_1.getValue());
            storePinkO.setStopTime(pinkT.getStopTime());
            storePinkO.setId(nextPinkT.getId());
            yxStorePinkMapper.updateById(storePinkO);

            //原有团长的数据变更成新团长下面
            wrapperO.eq(YxStorePink::getKId,pinkT.getId());
            YxStorePink storePinkT = new YxStorePink();
            storePinkT.setKId(nextPinkT.getId());
            yxStorePinkMapper.update(storePinkT,wrapperO);

            //update order
            YxStoreOrder storeOrder = new YxStoreOrder();
            storeOrder.setPinkId(nextPinkT.getId());
            storeOrder.setId(nextPinkT.getId());
            storeOrderService.updateById(storeOrder);

        }
    }

    /**
     * 计算还差几人拼团
     * @param pink 拼团信息
     * @return int
     */
    @Override
    public int surplusPeople(YxStorePink pink) {
        List<YxStorePink> listT = new ArrayList<>();
        if(pink.getKId() > 0){ //团长存在
            listT = this.getPinkMember(pink.getKId());
        }else{
            listT = this.getPinkMember(pink.getId());
        }

        return pink.getPeople() - (listT.size() + 1);
    }




    /**
     * 拼团明细
     * @param id 拼团id
     * @param uid 用户id
     */
    @Override
    public PinkInfoVo pinkInfo(Long id, Long uid) {
        YxStorePink pink = this.getPinkUserOne(id);
        if(ObjectUtil.isNull(pink)) {
            throw new YshopException("拼团不存在");
        }
        if( OrderInfoEnum.PINK_REFUND_STATUS_1.getValue().equals(pink.getIsRefund())){
            throw new YshopException("订单已退款");
        }

        int isOk = 0;//判断拼团是否完成
        int userBool = 0;//判断当前用户是否在团内  0未在 1在
        int pinkBool = 0;//判断拼团是否成功  0未 1是 -1结束

        PinkUserDto pinkUserDto = this.getPinkMemberAndPinK(pink);
        YxStorePink pinkT = pinkUserDto.getPinkT();
        List<YxStorePink> pinkAll = pinkUserDto.getPinkAll();
        List<Long> idAll = pinkUserDto.getIdAll();
        List<Long> uidAll = pinkUserDto.getUidAll();
        int count = pinkUserDto.getCount();

        if(count < 0) {
            count = 0;
        }
        if(OrderInfoEnum.PINK_STATUS_2.getValue().equals(pinkT.getStatus())){
            pinkBool = PinkEnum.PINK_BOOL_1.getValue();
            isOk = PinkEnum.IS_OK_1.getValue();
        }else if(pinkT.getStatus() == 3){
            pinkBool = PinkEnum.PINK_BOOL_MINUS_1.getValue();
            isOk = PinkEnum.IS_OK_0.getValue();
        }else{
            //组团完成
            if(count < 1){
                isOk = PinkEnum.IS_OK_1.getValue();
                pinkBool = this.pinkComplete(uidAll,idAll,uid,pinkT);
            }else{
                pinkBool = this.pinkFail(pinkAll,pinkT,pinkBool);
            }
        }

        //团员是否在团
        if(ObjectUtil.isNotNull(pinkAll)){
            for (YxStorePink storePink : pinkAll) {
                if(storePink.getUid().equals(uid)) {
                    userBool = PinkEnum.USER_BOOL_1.getValue();
                }
            }
        }
        //团长
        if(pinkT.getUid().equals(uid)) {
            userBool = PinkEnum.USER_BOOL_1.getValue();
        }

        YxStoreCombinationQueryVo storeCombinationQueryVo = yxStoreCombinationMapper.getCombDetail(pink.getCid());
        if(ObjectUtil.isNull(storeCombinationQueryVo)) {
            throw new YshopException("拼团不存在或已下架");
        }

        YxUserQueryVo userInfo = userService.getYxUserById(uid);
        YxStoreOrder yxStoreOrder = storeOrderService.getById(pink.getOrderIdKey());
        YxStoreCart yxStoreCart = yxStoreCartService.getById(yxStoreOrder.getCartId());
        //拼团访问量
        yxStoreCombinationMapper.incBrowseNum(pink.getPid());
        //拼团访客人数
        yxStoreVisitService.addStoreVisit(uid, pink.getPid());
        return PinkInfoVo.builder()
                .count(count)
                .currentPinkOrder(this.getCurrentPinkOrderId(id,uid))
                .isOk(isOk)
                .pinkAll(this.handPinkAll(pinkAll))
                .pinkBool(pinkBool)
                .pinkT(this.handPinkT(pinkT))
                .storeCombination(storeCombinationQueryVo)
                .userBool(userBool)
                .userInfo(userInfo)
                .uniqueId(yxStoreCart.getProductAttrUnique())
                .build();

    }



    /**
     * 返回正在拼团的人数
     *
     * @param id 拼团id
     * @return int
     */
    @Override
    public Long pinkIngCount(Long id) {
        return this.lambdaQuery()
                .eq(YxStorePink::getId,id)
                .eq(YxStorePink::getStatus,OrderInfoEnum.PINK_STATUS_1.getValue())
                .count();
    }


    /**
     * 创建拼团
     * @param order 订单
     */
    @Override
    public void createPink(YxStoreOrderQueryVo order) {
        YxStoreCombination storeCombination = combinationService.getById(order.getCombinationId());
        order = storeOrderService.handleOrder(order);
        YxStoreCart storeCart = yxStoreCartService.getById(order.getCartId());
        Long pinkCount = yxStorePinkMapper.selectCount(Wrappers.<YxStorePink>lambdaQuery()
                .eq(YxStorePink::getOrderId,order.getOrderId()));
        if(pinkCount > 0) {
            return;
        }
        if(storeCombination != null){
            YxStorePink  storePink = YxStorePink.builder()
                    .uid(order.getUid())
                    .orderId(order.getOrderId())
                    .orderIdKey(order.getId())
                    .totalNum(order.getTotalNum())
                    .totalPrice(order.getPayPrice())
                    .build();
            List<YxStoreCartQueryVo> cartInfo = order.getCartInfo();
            for (YxStoreCartQueryVo queryVo : cartInfo) {
                storePink.setCid(queryVo.getCombinationId());
                storePink.setPid(queryVo.getProductId());
                storePink.setPrice(queryVo.getProductInfo().getPrice());
            }

            Date stopTime = DateUtil.offsetHour(new Date(), storeCombination.getEffectiveTime());
            storePink.setPeople(storeCombination.getPeople());
            storePink.setStopTime(stopTime);
            storePink.setUniqueId(storeCart.getProductAttrUnique());
            if(order.getPinkId() > 0){ //其他成员入团
                if(this.getIsPinkUid(order.getPinkId(),order.getUid())) {
                    return;
                }
                storePink.setKId(order.getPinkId());
                storePink.setStopTime(null);
                this.save(storePink);

                //处理拼团完成
                PinkUserDto pinkUserDto = this.getPinkMemberAndPinK(storePink);
                YxStorePink pinkT = pinkUserDto.getPinkT();
                if( OrderInfoEnum.PINK_STATUS_1.getValue().equals(pinkT.getStatus())){
                    //int count = (int)map.get("count");
                    if(pinkUserDto.getCount() == 0){//处理成功
                        this.pinkComplete(pinkUserDto.getUidAll(),pinkUserDto.getIdAll(),order.getUid(), pinkT);
                    }else{
                        this.pinkFail(pinkUserDto.getPinkAll(),pinkT,PinkEnum.PINK_BOOL_0.getValue());
                    }
                }

            }else{//开团
                this.save(storePink);
                //pink_id更新到order表
                YxStoreOrder yxStoreOrder =  new YxStoreOrder();
                yxStoreOrder.setPinkId(storePink.getId());
                yxStoreOrder.setId(order.getId());
                storeOrderService.updateById(yxStoreOrder);

                //开团加入队列
                String redisKey = String.valueOf(StrUtil.format("{}{}",
                        ShopConstants.REDIS_PINK_CANCEL_KEY, storePink.getId()));
                long expireTime = storeCombination.getEffectiveTime().longValue() * 3600;
                redisTemplate.opsForValue().set(redisKey, "1" , expireTime, TimeUnit.SECONDS);

            }


        }
    }

    /**
     * 判断用户是否在团内
     * @param id 拼团id
     * @param uid 用户id
     * @return boolean true=在
     */
    @Override
    public boolean getIsPinkUid(Long id, Long uid) {
        Long count = this.lambdaQuery()
                .eq(YxStorePink::getIsRefund, OrderInfoEnum.PINK_REFUND_STATUS_0.getValue())
                .eq(YxStorePink::getUid,uid)
                .and(i->i.eq(YxStorePink::getKId,id).or().eq(YxStorePink::getId,id))
                .count();
        return count > 0;
    }

    /**
     * 获取拼团完成的商品总件数
     * @return int
     */
    @Override
    public int getPinkOkSumTotalNum() {
        return yxStorePinkMapper.sumNum();
    }

    /**
     * 获取拼团成功的用户
     * @param uid uid
     * @return list
     */
    @Override
    public List<String> getPinkOkList(Long uid) {
        List<String> list = new ArrayList<>();
        List<PinkDto> pinkDTOList = yxStorePinkMapper.getPinkOkList(uid);
        for (PinkDto pinkDTO : pinkDTOList) {
            list.add(pinkDTO.getNickname()+"拼团成功");
        }
        return list;
    }


    /**
     * 获取团长拼团数据
     * @param cid 拼团产品id
     * @return PinkAllDto pindAll-参与的拼团的id 集合  list-团长参与的列表
     */
    @Override
    public PinkAllDto getPinkAll(Long cid) {
        Map<String,Object> map = new LinkedHashMap<>(2);
        List<PinkDto> list = yxStorePinkMapper.getPinks(cid);
        List<Long> pindAll = new ArrayList<>();//参与的拼团的id 集合
        for (PinkDto pinkDto : list) {
            pinkDto.setCount(String.valueOf(this.getPinkPeople(pinkDto.getId()
                    ,pinkDto.getPeople())));
            Date date = pinkDto.getStopTime();
            pinkDto.setH(String.valueOf(DateUtil.hour(date,true)));
            pinkDto.setI(String.valueOf(DateUtil.minute(date)));
            pinkDto.setS(String.valueOf(DateUtil.second(date)));
            pindAll.add(pinkDto.getId());
        }


        return PinkAllDto.builder()
                .list(list)
                .pindAll(pindAll)
                .build();
    }


    /**
     * 处理团员
     * @param pinkAll 拼团数据
     * @return list
     */
    private List<YxStorePinkQueryVo> handPinkAll(List<YxStorePink> pinkAll) {

        List<YxStorePinkQueryVo> list = generator.convert(pinkAll,YxStorePinkQueryVo.class);
        for (YxStorePinkQueryVo queryVo : list) {
            YxUserQueryVo userQueryVo = userService.getYxUserById(queryVo.getUid().longValue());
            queryVo.setAvatar(userQueryVo.getAvatar());
            queryVo.setNickname(userQueryVo.getNickname());
        }
        return list;
    }

    /**
     * 处理团长
     * @param pinkT 拼团
     * @return YxStorePinkQueryVo
     */
    private YxStorePinkQueryVo handPinkT(YxStorePink pinkT) {
        YxStorePinkQueryVo pinkQueryVo =  generator.convert(pinkT,YxStorePinkQueryVo.class);
        YxUserQueryVo userQueryVo = userService.getYxUserById(pinkQueryVo.getUid().longValue());
        pinkQueryVo.setAvatar(userQueryVo.getAvatar());
        pinkQueryVo.setNickname(userQueryVo.getNickname());

        return pinkQueryVo;
    }


    /**
     * 获取当前拼团数据返回订单编号
     * @param id 拼团id
     * @param uid uid
     * @return string
     */
    private String getCurrentPinkOrderId(Long id, Long uid) {
        YxStorePink pink = yxStorePinkMapper.selectOne(Wrappers.<YxStorePink>lambdaQuery()
                .eq(YxStorePink::getId,id).eq(YxStorePink::getUid,uid));
        if(pink == null){
            pink = yxStorePinkMapper.selectOne(Wrappers.<YxStorePink>lambdaQuery()
                    .eq(YxStorePink::getKId,id).eq(YxStorePink::getUid,uid));
            if(pink == null) {
                return "";
            }
        }
        return pink.getOrderId();
    }


    /**
     * 获取拼团的团员
     * @param kid 团长id
     * @return list
     */
    private List<YxStorePink> getPinkMember(Long kid) {
        return this.lambdaQuery().eq(YxStorePink::getKId,kid)
                .eq(YxStorePink::getIsRefund,OrderInfoEnum.PINK_REFUND_STATUS_0.getValue())
                .orderByAsc(YxStorePink::getId)
                .list();
    }



    /**
     * 获取一条拼团数据
     * @param id 拼团id
     * @return YxStorePink
     */
    private YxStorePink getPinkUserOne(Long id) {
        return this.lambdaQuery().eq(YxStorePink::getId,id).one();
    }

    /**
     * 拼团人数完成时，判断全部人都是未退款状态
     * @return boolean
     */
    private boolean getPinkStatus(List<Long> idAll) {
        Long count = this.lambdaQuery().in(YxStorePink::getId,idAll)
                .eq(YxStorePink::getIsRefund,OrderInfoEnum.PINK_REFUND_STATUS_1.getValue())
                .count();
        if(count == 0) {
            return true;
        }
        return false;
    }


    /**
     * 拼团完成更改数据写入内容
     * @param uidAll 用户id集合
     * @param idAll 拼团id集合
     * @param uid uid
     * @param pinkT 团长
     */
    private int pinkComplete(List<Long> uidAll,List<Long> idAll,Long uid,
                             YxStorePink pinkT) {
        boolean pinkStatus = this.getPinkStatus(idAll);//判断是否有退款的
        int pinkBool = PinkEnum.PINK_BOOL_0.getValue();
        if(pinkStatus){
            //更改状态
           LambdaQueryWrapper<YxStorePink> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(YxStorePink::getId,idAll);

            YxStorePink storePink = new YxStorePink();
            storePink.setStopTime(new Date());
            storePink.setStatus(OrderInfoEnum.PINK_STATUS_2.getValue());

            this.update(storePink,wrapper);

            if(uidAll.contains(uid)){
                pinkBool = PinkEnum.PINK_BOOL_1.getValue();
            }

            //todo 模板消息
        }

        return pinkBool;

    }

    /**
     * 拼团失败退款之后
     * @param uid 用户id
     * @param pid 团长id
     */
    private void orderPinkFailAfter(Long uid, Long pid) {
        YxStorePink yxStorePink = new YxStorePink();
       LambdaQueryWrapper<YxStorePink> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStorePink::getId,pid);
        yxStorePink.setStatus(OrderInfoEnum.PINK_STATUS_3.getValue());
        yxStorePink.setStopTime(new Date());
        yxStorePinkMapper.update(yxStorePink,wrapper);

       LambdaQueryWrapper<YxStorePink> wrapperT = new LambdaQueryWrapper<>();
        wrapperT.eq(YxStorePink::getKId,pid);
        yxStorePinkMapper.update(yxStorePink,wrapperT);
        //todo 模板消息
    }


    /**
     * 拼团失败 退款
     * @param pinkAll 拼团数据,不包括团长
     * @param pinkT 团长数据
     * @param pinkBool PinkEnum
     */
    private int pinkFail(List<YxStorePink> pinkAll, YxStorePink pinkT,int pinkBool) {
        Date now = new Date();
        //拼团时间超时  退款
        if(DateUtil.compare(pinkT.getStopTime(),now) < 0){
            pinkBool = PinkEnum.PINK_BOOL_MINUS_1.getValue();
            pinkAll.add(pinkT);
            //处理退款
            for (YxStorePink storePink : pinkAll) {
                storeOrderService.orderApplyRefund("","","拼团时间超时",storePink.getOrderId(),storePink.getUid());
                this.orderPinkFailAfter(pinkT.getUid(),storePink.getId());
            }
        }

        return pinkBool;
    }

    /**
     * 获取参团人和团长和拼团总人数
     * @param pink 拼团
     * @return PinkUserDto
     */
    private PinkUserDto getPinkMemberAndPinK(YxStorePink pink) {
        //查找拼团团员和团长
        List<YxStorePink> pinkAll = null;
        YxStorePink pinkT = null;
        //查找拼团团员和团长
        if(pink.getKId() > 0){ //团长存在
            pinkAll = this.getPinkMember(pink.getKId());
            pinkT =  this.getPinkUserOne(pink.getKId());
        }else{
            pinkAll = this.getPinkMember(pink.getId());
            pinkT =  pink;
        }
        //收集拼团用户id和拼团id
        List<Long> idAll = pinkAll.stream().map(YxStorePink::getId).collect(Collectors.toList());
        List<Long> uidAll = pinkAll.stream().map(YxStorePink::getUid).collect(Collectors.toList());

        idAll.add(pinkT.getId());
        uidAll.add(pinkT.getUid());
        //还差几人
        int count =  pinkT.getPeople() - (pinkAll.size() + 1);


        return PinkUserDto.builder()
                .pinkAll(pinkAll)
                .pinkT(pinkT)
                .idAll(idAll)
                .uidAll(uidAll)
                .count(count)
                .build();
    }


    /**
     * 计算获取团长还差多少人拼团成功
     * @param kid 团长参与拼团id
     * @param people 当前满足拼团的人数
     * @return int
     */
    private Long getPinkPeople(Long kid, int people) {
       LambdaQueryWrapper<YxStorePink> wrapper= new LambdaQueryWrapper<>();
        wrapper.eq(YxStorePink::getKId,kid)
                .eq(YxStorePink::getIsRefund, OrderInfoEnum.PINK_REFUND_STATUS_0.getValue());
        //加上团长自己
        Long count = yxStorePinkMapper.selectCount(wrapper) + 1;
        return people - count;
    }

    //=================================================//

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStorePinkQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStorePink> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        List<YxStorePinkDto> yxStorePinkDtos = generator.convert(page.getList(), YxStorePinkDto.class);
        yxStorePinkDtos.forEach(i ->{
            YxUser yxUser = yxUserService.getById(i.getUid());
            YxStoreCombination storeCombination = combinationService.getById(i.getCid());
            i.setNickname(yxUser.getNickname());
            i.setPhone(yxUser.getPhone());
            i.setUserImg(yxUser.getAvatar());
            i.setProduct(storeCombination.getTitle());
            i.setImage(storeCombination.getImage());
            i.setCountPeople(this.count(new LambdaQueryWrapper<YxStorePink>().eq(YxStorePink::getCid,i.getCid())));
        });
        map.put("content", yxStorePinkDtos);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStorePink> queryAll(YxStorePinkQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStorePink.class, criteria));
    }


    @Override
    public void download(List<YxStorePinkDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStorePinkDto yxStorePink : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户id", yxStorePink.getUid());
            map.put("订单id 生成", yxStorePink.getOrderId());
            map.put("订单id  数据库", yxStorePink.getOrderIdKey());
            map.put("购买商品个数", yxStorePink.getTotalNum());
            map.put("购买总金额", yxStorePink.getTotalPrice());
            map.put("拼团产品id", yxStorePink.getCid());
            map.put("产品id", yxStorePink.getPid());
            map.put("拼团总人数", yxStorePink.getPeople());
            map.put("拼团产品单价", yxStorePink.getPrice());
            map.put(" stopTime",  yxStorePink.getStopTime());
            map.put("团长id 0为团长", yxStorePink.getKId());
            map.put("是否发送模板消息0未发送1已发送", yxStorePink.getIsTpl());
            map.put("是否退款 0未退款 1已退款", yxStorePink.getIsRefund());
            map.put("状态1进行中2已完成3未完成", yxStorePink.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
