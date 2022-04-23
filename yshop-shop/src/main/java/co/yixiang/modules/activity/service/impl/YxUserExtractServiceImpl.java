/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.activity.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.service.YxUserExtractService;
import co.yixiang.modules.activity.service.dto.YxUserExtractDto;
import co.yixiang.modules.activity.service.dto.YxUserExtractQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxUserExtractMapper;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.service.YxUserBillService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.WechatUserDto;
import co.yixiang.modules.shop.service.dto.YxUserDto;
import co.yixiang.modules.mp.service.YxPayService;
import co.yixiang.utils.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
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

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxUserExtract")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserExtractServiceImpl extends BaseServiceImpl<YxUserExtractMapper, YxUserExtract> implements YxUserExtractService {

    private final IGenerator generator;
    private final YxUserService yxUserService;
    private final YxUserBillService billService;
    private final YxPayService payService;

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
    public List<YxUserExtract> queryAll(YxUserExtractQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxUserExtract.class, criteria));
    }


    @Override
    public void download(List<YxUserExtractDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxUserExtractDto yxUserExtract : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put(" uid", yxUserExtract.getUid());
            map.put("名称", yxUserExtract.getRealName());
            map.put("bank = 银行卡 alipay = 支付宝wx=微信", yxUserExtract.getExtractType());
            map.put("银行卡", yxUserExtract.getBankCode());
            map.put("开户地址", yxUserExtract.getBankAddress());
            map.put("支付宝账号", yxUserExtract.getAlipayCode());
            map.put("提现金额", yxUserExtract.getExtractPrice());
            map.put(" mark", yxUserExtract.getMark());
            map.put(" balance", yxUserExtract.getBalance());
            map.put("无效原因", yxUserExtract.getFailMsg());
            map.put(" failTime", yxUserExtract.getFailTime());
            map.put("-1 未通过 0 审核中 1 已提现", yxUserExtract.getStatus());
            map.put("微信号", yxUserExtract.getWechat());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void doExtract(YxUserExtract resources) {
        if (resources.getStatus() == null) {
            throw new BadRequestException("请选择审核状态");
        }

        if(ShopCommonEnum.EXTRACT_0.getValue().equals(resources.getStatus())){
            throw new BadRequestException("请选择审核状态");
        }

        if(ShopCommonEnum.EXTRACT_MINUS_1.getValue().equals(resources.getStatus())){
            if (StrUtil.isEmpty(resources.getFailMsg())) {
                throw new BadRequestException("请填写失败原因");
            }
            String mark = "提现失败,退回佣金" + resources.getExtractPrice() + "元";
            YxUserDto userDTO = generator.convert(yxUserService.getOne(new LambdaQueryWrapper<YxUser>().eq(YxUser::getUid, resources.getUid())), YxUserDto.class);

            //增加流水
            YxUserBill userBill = new YxUserBill();
            userBill.setTitle("提现失败");
            userBill.setUid(resources.getUid());
            userBill.setCategory("now_money");
            userBill.setType("extract");
            userBill.setNumber(resources.getExtractPrice());
            userBill.setLinkId(resources.getId().toString());
            userBill.setBalance(NumberUtil.add(userDTO.getBrokeragePrice(), resources.getExtractPrice()));
            userBill.setMark(mark);
            userBill.setStatus(1);
            userBill.setPm(1);
            billService.save(userBill);

            //返回提现金额
            yxUserService.incBrokeragePrice(resources.getExtractPrice().doubleValue()
                    , resources.getUid());

            resources.setFailTime(new Date());

        }
        //todo 此处为企业付款，没经过测试
        boolean isTest = true;
        if (!isTest) {
            String openid = this.getUserOpenid(resources.getUid());
            if (StrUtil.isNotBlank(openid)) {
                try {
                    payService.entPay(openid, resources.getId().toString(),
                            resources.getRealName(),
                            resources.getExtractPrice().multiply(new BigDecimal(100)).intValue());
                } catch (WxPayException e) {
                    throw new BadRequestException(e.getMessage());
                }
            } else {
                throw new BadRequestException("不是微信用户无法退款");
            }

        }
        this.saveOrUpdate(resources);
    }

    /**
     * 获取openid
     * @param uid uid
     * @return String
     */
    private String getUserOpenid(Long uid){
        YxUser yxUser = yxUserService.getById(uid);
        if(yxUser == null) {
            return "";
        }

        WechatUserDto wechatUserDto = yxUser.getWxProfile();
        if(wechatUserDto == null) {
            return "";
        }
        if(StrUtil.isBlank(wechatUserDto.getOpenid())) {
            return "";
        }
        return wechatUserDto.getOpenid();

    }
}
