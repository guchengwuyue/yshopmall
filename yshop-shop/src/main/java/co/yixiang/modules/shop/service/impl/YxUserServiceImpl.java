/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.impl;

import cn.hutool.core.util.NumberUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.service.YxUserBillService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.UserMoneyDto;
import co.yixiang.modules.shop.service.dto.YxUserDto;
import co.yixiang.modules.shop.service.dto.YxUserQueryCriteria;
import co.yixiang.modules.shop.service.mapper.UserMapper;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.OrderUtil;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxUser")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserServiceImpl extends BaseServiceImpl<UserMapper, YxUser> implements YxUserService {

    private final IGenerator generator;

    private final UserMapper yxUserMapper;

    private final YxUserBillService yxUserBillService;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxUserQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxUser> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxUserDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxUser> queryAll(YxUserQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxUser.class, criteria));
    }


    @Override
    public void download(List<YxUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxUserDto yxUser : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户账户(跟accout一样)", yxUser.getUsername());
            map.put("用户账号", yxUser.getAccount());
            map.put("用户密码（跟pwd）", yxUser.getPassword());
            map.put("用户密码", yxUser.getPwd());
            map.put("真实姓名", yxUser.getRealName());
            map.put("生日", yxUser.getBirthday());
            map.put("身份证号码", yxUser.getCardId());
            map.put("用户备注", yxUser.getMark());
            map.put("合伙人id", yxUser.getPartnerId());
            map.put("用户分组id", yxUser.getGroupId());
            map.put("用户昵称", yxUser.getNickname());
            map.put("用户头像", yxUser.getAvatar());
            map.put("手机号码", yxUser.getPhone());
            map.put("添加时间", yxUser.getAddTime());
            map.put("添加ip", yxUser.getAddIp());
            map.put("最后一次登录时间", yxUser.getLastTime());
            map.put("最后一次登录ip", yxUser.getLastIp());
            map.put("用户余额", yxUser.getNowMoney());
            map.put("佣金金额", yxUser.getBrokeragePrice());
            map.put("用户剩余积分", yxUser.getIntegral());
            map.put("连续签到天数", yxUser.getSignNum());
            map.put("1为正常，0为禁止", yxUser.getStatus());
            map.put("等级", yxUser.getLevel());
            map.put("推广元id", yxUser.getSpreadUid());
            map.put("推广员关联时间", yxUser.getSpreadTime());
            map.put("用户类型", yxUser.getUserType());
            map.put("是否为推广员", yxUser.getIsPromoter());
            map.put("用户购买次数", yxUser.getPayCount());
            map.put("下级人数", yxUser.getSpreadCount());
            map.put("清理会员时间", yxUser.getCleanTime());
            map.put("详细地址", yxUser.getAddres());
            map.put("管理员编号 ", yxUser.getAdminid());
            map.put("用户登陆类型，h5,wechat,routine", yxUser.getLoginType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onStatus(Integer uid, int status) {
        if (status == 1) {
            status = 0;
        } else {
            status = 1;
        }

        yxUserMapper.updateOnstatus(status, uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMoney(UserMoneyDto param) {
        YxUserDto userDTO = generator.convert(getById(param.getUid()), YxUserDto.class);
        Double newMoney = 0d;
        String mark = "";
        String type = "system_add";
        Integer pm = 1;
        String title = "系统增加余额";
        if (param.getPtype() == 1) {
            mark = "系统增加了" + param.getMoney() + "余额";
            newMoney = NumberUtil.add(userDTO.getNowMoney(), param.getMoney()).doubleValue();
        } else {
            title = "系统减少余额";
            mark = "系统扣除了" + param.getMoney() + "余额";
            type = "system_sub";
            pm = 0;
            newMoney = NumberUtil.sub(userDTO.getNowMoney(), param.getMoney()).doubleValue();
            if (newMoney < 0) {
                newMoney = 0d;
            }

        }
        YxUser user = new YxUser();
        user.setUid(userDTO.getUid());
        user.setNowMoney(BigDecimal.valueOf(newMoney));
        saveOrUpdate(user);

        YxUserBill userBill = new YxUserBill();
        userBill.setUid(userDTO.getUid());
        userBill.setLinkId("0");
        userBill.setPm(pm);
        userBill.setTitle(title);
        userBill.setCategory("now_money");
        userBill.setType(type);
        userBill.setNumber(BigDecimal.valueOf(param.getMoney()));
        userBill.setBalance(BigDecimal.valueOf(newMoney));
        userBill.setMark(mark);
        userBill.setStatus(1);
        yxUserBillService.save(userBill);
    }

    @Override
    public void incBrokeragePrice(double price, Long uid) {
        yxUserMapper.incBrokeragePrice(price, uid);
    }
}
