package co.yixiang.modules.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import co.yixiang.exception.BadRequestException;
import co.yixiang.exception.EntityExistException;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.repository.YxStorePinkRepository;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.shop.domain.YxStoreOrder;
import co.yixiang.modules.shop.domain.YxStoreOrderStatus;
import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.repository.YxStoreOrderRepository;
import co.yixiang.modules.shop.repository.YxUserRepository;
import co.yixiang.modules.shop.service.YxStoreOrderService;
import co.yixiang.modules.shop.service.YxStoreOrderStatusService;
import co.yixiang.modules.shop.service.YxUserBillService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.*;
import co.yixiang.modules.shop.service.mapper.YxStoreOrderMapper;
import co.yixiang.utils.OrderUtil;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.ValidationUtil;
import com.alibaba.fastjson.JSON;
import co.yixiang.modules.shop.domain.StoreOrderCartInfo;
import co.yixiang.modules.shop.repository.YxStoreOrderCartInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
* @author hupeng
* @date 2019-10-14
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreOrderServiceImpl implements YxStoreOrderService {

    @Autowired
    private YxStoreOrderRepository yxStoreOrderRepository;

    @Autowired
    private YxStoreOrderCartInfoRepository yxStoreOrderCartInfoRepository;

    @Autowired
    private YxStoreOrderMapper yxStoreOrderMapper;

    @Autowired
    private YxStoreOrderStatusService yxStoreOrderStatusService;

    @Autowired
    private YxUserService userService;

    @Autowired
    private YxUserRepository userRepository;

    @Autowired
    private YxUserBillService yxUserBillService;

    @Autowired
    private YxStorePinkRepository storePinkRepository;

    @Override
    public OrderTimeDataDTO getOrderTimeData() {
        int today = OrderUtil.dateToTimestampT(DateUtil.beginOfDay(new Date()));
        int yesterday = OrderUtil.dateToTimestampT(DateUtil.beginOfDay(DateUtil.
                yesterday()));
        int lastWeek = OrderUtil.dateToTimestampT(DateUtil.beginOfDay(DateUtil.lastWeek()));
        int nowMonth = OrderUtil.dateToTimestampT(DateUtil
                .beginOfMonth(new Date()));
        OrderTimeDataDTO orderTimeDataDTO = new OrderTimeDataDTO();

        orderTimeDataDTO.setTodayCount(yxStoreOrderRepository.countByPayTimeGreaterThanEqual(today));
        orderTimeDataDTO.setTodayPrice(yxStoreOrderRepository.sumPrice(today));

        orderTimeDataDTO.setProCount(yxStoreOrderRepository
                .countByPayTimeLessThanAndPayTimeGreaterThanEqual(today,yesterday));
        orderTimeDataDTO.setProPrice(yxStoreOrderRepository.sumTPrice(today,yesterday));

        orderTimeDataDTO.setLastWeekCount(yxStoreOrderRepository.countByPayTimeGreaterThanEqual(lastWeek));
        orderTimeDataDTO.setLastWeekPrice(yxStoreOrderRepository.sumPrice(lastWeek));

        orderTimeDataDTO.setMonthCount(yxStoreOrderRepository.countByPayTimeGreaterThanEqual(nowMonth));
        orderTimeDataDTO.setMonthPrice(yxStoreOrderRepository.sumPrice(nowMonth));

        return orderTimeDataDTO;
    }

    @Override
    public Map<String, Object> chartCount() {
        Map<String, Object> map = new LinkedHashMap<>();
        int nowMonth = OrderUtil.dateToTimestampT(DateUtil
                .beginOfMonth(new Date()));

        map.put("chart",yxStoreOrderRepository.chartList(nowMonth));
        map.put("chartT",yxStoreOrderRepository.chartListT(nowMonth));

        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refund(YxStoreOrder resources) {
        if(resources.getPayPrice().doubleValue() < 0){
            throw new BadRequestException("请输入退款金额");
        }

        //修改状态
        resources.setRefundStatus(2);
        resources.setRefundPrice(resources.getPayPrice());
        update(resources);

        //退款到余额
        YxUserDTO userDTO = userService.findById(resources.getUid());
        userRepository.updateMoney(resources.getPayPrice().doubleValue(),
                resources.getUid());

        YxUserBill userBill = new YxUserBill();
        userBill.setUid(resources.getUid());

        userBill.setLinkId(resources.getId().toString());
        userBill.setPm(1);
        userBill.setTitle("商品退款");
        userBill.setCategory("now_money");
        userBill.setType("pay_product_refund");
        userBill.setNumber(resources.getPayPrice());
        userBill.setBalance(NumberUtil.add(resources.getPayPrice(),userDTO.getNowMoney()));
        userBill.setMark("订单退款到余额");
        userBill.setAddTime(OrderUtil.getSecondTimestampTwo());
        userBill.setStatus(1);
        yxUserBillService.create(userBill);


        YxStoreOrderStatus storeOrderStatus = new YxStoreOrderStatus();
        storeOrderStatus.setOid(resources.getId());
        storeOrderStatus.setChangeType("refund_price");
        storeOrderStatus.setChangeMessage("退款给用户："+resources.getPayPrice() +"元");
        storeOrderStatus.setChangeTime(OrderUtil.getSecondTimestampTwo());

        yxStoreOrderStatusService.create(storeOrderStatus);
    }

    @Override
    public String orderType(int id,int pinkId, int combinationId) {
        String str = "[普通订单]";
        if(pinkId > 0 || combinationId > 0){
            YxStorePink storePink = storePinkRepository.findByOrderIdKey(id);
            if(ObjectUtil.isNull(storePink)) {
                str = "[拼团订单]";
            }else{
                switch (storePink.getStatus()){
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

        }
        return str;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreOrderQueryCriteria criteria, Pageable pageable){

        Page<YxStoreOrder> page = yxStoreOrderRepository
                .findAll((root, criteriaQuery, criteriaBuilder)
                        -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<YxStoreOrderDTO> storeOrderDTOS = new ArrayList<>();
        for (YxStoreOrder yxStoreOrder : page.getContent()) {
            YxStoreOrderDTO yxStoreOrderDTO = yxStoreOrderMapper.toDto(yxStoreOrder);


            Integer _status = OrderUtil.orderStatus(yxStoreOrder.getPaid(),yxStoreOrder.getStatus(),
                    yxStoreOrder.getRefundStatus());

            //订单状态
            String orderStatusStr = OrderUtil.orderStatusStr(yxStoreOrder.getPaid()
                    ,yxStoreOrder.getStatus(),yxStoreOrder.getShippingType()
                    ,yxStoreOrder.getRefundStatus());
            if(_status == 3){
                String refundTime = OrderUtil.stampToDate(String.valueOf(yxStoreOrder
                        .getRefundReasonTime()));
                String str = "<b style='color:#f124c7'>申请退款</b><br/>"+
                        "<span>退款原因："+yxStoreOrder.getRefundReasonWap()+"</span><br/>" +
                        "<span>备注说明："+yxStoreOrder.getRefundReasonWapExplain()+"</span><br/>" +
                        "<span>退款时间："+refundTime+"</span><br/>";
                orderStatusStr = str;
            }
            yxStoreOrderDTO.setStatusName(orderStatusStr);

            yxStoreOrderDTO.set_status(_status);

            String payTypeName = OrderUtil.payTypeName(yxStoreOrder.getPayType()
                    ,yxStoreOrder.getPaid());
            yxStoreOrderDTO.setPayTypeName(payTypeName);

            yxStoreOrderDTO.setPinkName(orderType(yxStoreOrder.getId()
                    ,yxStoreOrder.getPinkId(),yxStoreOrder.getCombinationId()));

            List<StoreOrderCartInfo> cartInfos = yxStoreOrderCartInfoRepository
                    .findByOid(yxStoreOrder.getId());
            List<StoreOrderCartInfoDTO> cartInfoDTOS = new ArrayList<>();
            for (StoreOrderCartInfo cartInfo : cartInfos) {
                StoreOrderCartInfoDTO cartInfoDTO = new StoreOrderCartInfoDTO();
                cartInfoDTO.setCartInfoMap(JSON.parseObject(cartInfo.getCartInfo()));

                cartInfoDTOS.add(cartInfoDTO);
            }
            yxStoreOrderDTO.setCartInfoList(cartInfoDTOS);

            storeOrderDTOS.add(yxStoreOrderDTO);

        }

        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",storeOrderDTOS);
        map.put("totalElements",page.getTotalElements());

        return map;
    }

    @Override
    public List<YxStoreOrderDTO> queryAll(YxStoreOrderQueryCriteria criteria){
        return yxStoreOrderMapper.toDto(yxStoreOrderRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreOrderDTO findById(Integer id) {
        Optional<YxStoreOrder> yxStoreOrder = yxStoreOrderRepository.findById(id);
        ValidationUtil.isNull(yxStoreOrder,"YxStoreOrder","id",id);
        return yxStoreOrderMapper.toDto(yxStoreOrder.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreOrderDTO create(YxStoreOrder resources) {
        if(yxStoreOrderRepository.findByUnique(resources.getUnique()) != null){
            throw new EntityExistException(YxStoreOrder.class,"unique",resources.getUnique());
        }
        return yxStoreOrderMapper.toDto(yxStoreOrderRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreOrder resources) {
        Optional<YxStoreOrder> optionalYxStoreOrder = yxStoreOrderRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreOrder,"YxStoreOrder","id",resources.getId());
        YxStoreOrder yxStoreOrder = optionalYxStoreOrder.get();
        YxStoreOrder yxStoreOrder1 = yxStoreOrderRepository.findByUnique(resources.getUnique());
        if(yxStoreOrder1 != null && !yxStoreOrder1.getId().equals(yxStoreOrder.getId())){
            throw new EntityExistException(YxStoreOrder.class,"unique",resources.getUnique());
        }
        yxStoreOrder.copy(resources);
        yxStoreOrderRepository.save(yxStoreOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreOrderRepository.deleteById(id);
    }
}