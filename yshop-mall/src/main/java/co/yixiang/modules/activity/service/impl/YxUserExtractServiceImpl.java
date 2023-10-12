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
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.BillDetailEnum;
import co.yixiang.enums.PayTypeEnum;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.event.TemplateBean;
import co.yixiang.event.TemplateEvent;
import co.yixiang.event.TemplateListenEnum;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.param.UserExtParam;
import co.yixiang.modules.activity.service.YxUserExtractService;
import co.yixiang.modules.activity.service.dto.YxUserExtractDto;
import co.yixiang.modules.activity.service.dto.YxUserExtractQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxUserExtractMapper;
import co.yixiang.modules.user.domain.YxUser;
import co.yixiang.modules.user.service.YxUserBillService;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserExtractServiceImpl extends BaseServiceImpl<YxUserExtractMapper, YxUserExtract> implements YxUserExtractService {

    private final IGenerator generator;
    private final YxUserExtractMapper yxUserExtractMapper;
    private final YxUserService userService;
    private final YxUserBillService billService;
    private final ApplicationEventPublisher publisher;

    /**
     * 开始提现
     * @param userInfo 用户
     * @param param UserExtParam
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userExtract(YxUser userInfo, UserExtParam param) {
        BigDecimal extractPrice = userInfo.getBrokeragePrice();
        if(extractPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new YshopException("提现佣金不足");
        }

        double money = Double.valueOf(param.getMoney());
        if( extractPrice.compareTo(BigDecimal.valueOf(money)) < 0) {
            throw new YshopException("提现佣金不足");
        }

        if(money <= 0) {
            throw new YshopException("提现佣金大于0");
        }

        double balance = NumberUtil.sub(extractPrice.doubleValue(),money);
        if(balance < 0) {
            balance = 0;
        }

        YxUserExtract userExtract = new YxUserExtract();
        userExtract.setUid(userInfo.getUid());
        userExtract.setExtractType(param.getExtractType());
        userExtract.setExtractPrice(new BigDecimal(param.getMoney()));
        userExtract.setBalance(BigDecimal.valueOf(balance));

        if(StrUtil.isNotEmpty(param.getName())){
            userExtract.setRealName(param.getName());
        }else {
            userExtract.setRealName(userInfo.getNickname());
        }

        if(StrUtil.isNotEmpty(param.getWeixin())){
            userExtract.setWechat(param.getWeixin());
        }else {
            userExtract.setWechat(userInfo.getNickname());
        }

        String mark = "";

        if(PayTypeEnum.ALI.getValue().equals(param.getExtractType())){
            if(StrUtil.isEmpty(param.getAlipayCode())){
                throw new YshopException("请输入支付宝账号");
            }
            userExtract.setAlipayCode(param.getAlipayCode());
            mark = "使用支付宝提现"+param.getMoney()+"元";
        }else if(PayTypeEnum.WEIXIN.getValue().equals(param.getExtractType())){
            if(StrUtil.isEmpty(param.getWeixin())){
                throw new YshopException("请输入微信账号");
            }
            mark = "使用微信提现"+param.getMoney()+"元";
        }

        yxUserExtractMapper.insert(userExtract);

        //更新佣金
        YxUser yxUser = new YxUser();
        yxUser.setBrokeragePrice(BigDecimal.valueOf(balance));
        yxUser.setUid(userInfo.getUid());
        userService.updateById(yxUser);

        //插入流水
        billService.expend(userInfo.getUid(),"佣金提现", BillDetailEnum.CATEGORY_1.getValue(),
                BillDetailEnum.TYPE_4.getValue(),money,balance, mark);

    }

    /**
     * 累计提现金额
     * @param uid uid
     * @return double
     */
    @Override
    public double extractSum(Long uid) {
        return yxUserExtractMapper.sumPrice(uid);
    }


    //==============================================================//

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxUserExtractQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxUserExtract> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxUserExtractDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxUserExtract> queryAll(YxUserExtractQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxUserExtract.class, criteria));
    }


    @Override
    public void download(List<YxUserExtractDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxUserExtractDto yxUserExtract : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" uid",  yxUserExtract.getUid());
            map.put("名称", yxUserExtract.getRealName());
            map.put("bank = 银行卡 alipay = 支付宝wx=微信", yxUserExtract.getExtractType());
            map.put("银行卡", yxUserExtract.getBankCode());
            map.put("开户地址", yxUserExtract.getBankAddress());
            map.put("支付宝账号", yxUserExtract.getAlipayCode());
            map.put("提现金额", yxUserExtract.getExtractPrice());
            map.put(" mark",  yxUserExtract.getMark());
            map.put(" balance",  yxUserExtract.getBalance());
            map.put("无效原因", yxUserExtract.getFailMsg());
            map.put(" failTime",  yxUserExtract.getFailTime());
            map.put("-1 未通过 0 审核中 1 已提现", yxUserExtract.getStatus());
            map.put("微信号", yxUserExtract.getWechat());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 操作提现
     * @param resources YxUserExtract
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doExtract(YxUserExtract resources){
        if(resources.getStatus() == null){
            throw new BadRequestException("请选择审核状态");
        }

        if(ShopCommonEnum.EXTRACT_0.getValue().equals(resources.getStatus())){
            throw new BadRequestException("请选择审核状态");
        }
        YxUserExtract userExtract = this.getById(resources.getId());
        if(!ShopCommonEnum.EXTRACT_0.getValue().equals(userExtract.getStatus())){
            throw new BadRequestException("该申请已经处理过啦！");
        }
        if(ShopCommonEnum.EXTRACT_MINUS_1.getValue().equals(resources.getStatus())){
            if(StrUtil.isEmpty(resources.getFailMsg())){
                throw new BadRequestException("请填写失败原因");
            }
            //防止无限添加佣金
            if (ObjectUtil.isNull(userExtract.getFailTime())) {
                String mark = "提现失败,退回佣金"+resources.getExtractPrice()+"元";
                YxUser yxUser = userService.getById(resources.getUid());

                double balance = NumberUtil.add(yxUser.getBrokeragePrice(),resources.getExtractPrice()).doubleValue();
                //插入流水
                billService.income(resources.getUid(),"提现失败", BillDetailEnum.CATEGORY_1.getValue(),
                        BillDetailEnum.TYPE_4.getValue(),resources.getExtractPrice().doubleValue(),balance,
                        mark,resources.getId().toString());

                //返回提现金额
                userService.incBrokeragePrice(resources.getExtractPrice(),resources.getUid());

                resources.setFailTime(new Date());
            }

        }else{
            //模板消息支付成功发布事件
            TemplateBean templateBean = TemplateBean.builder()
                    .extractId( resources.getId())
                    .templateType(TemplateListenEnum.TYPE_8.getValue())
                    .build();
            publisher.publishEvent(new TemplateEvent(this,templateBean));
        }
        this.saveOrUpdate(resources);
    }
}
