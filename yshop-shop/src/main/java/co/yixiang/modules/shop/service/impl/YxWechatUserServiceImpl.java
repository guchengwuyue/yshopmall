/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.shop.domain.YxWechatUser;
import co.yixiang.modules.shop.service.YxWechatUserService;
import co.yixiang.modules.shop.service.dto.YxWechatUserDto;
import co.yixiang.modules.shop.service.dto.YxWechatUserQueryCriteria;
import co.yixiang.modules.shop.service.mapper.WechatUserMapper;
import co.yixiang.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
//@CacheConfig(cacheNames = "yxWechatUser")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxWechatUserServiceImpl extends BaseServiceImpl<WechatUserMapper, YxWechatUser> implements YxWechatUserService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxWechatUserQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxWechatUser> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxWechatUserDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxWechatUser> queryAll(YxWechatUserQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxWechatUser.class, criteria));
    }


    @Override
    public void download(List<YxWechatUserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxWechatUserDto yxWechatUser : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段", yxWechatUser.getUnionid());
            map.put("用户的标识，对当前公众号唯一", yxWechatUser.getOpenid());
            map.put("小程序唯一身份ID", yxWechatUser.getRoutineOpenid());
            map.put("用户的昵称", yxWechatUser.getNickname());
            map.put("用户头像", yxWechatUser.getHeadimgurl());
            map.put("用户的性别，值为1时是男性，值为2时是女性，值为0时是未知", yxWechatUser.getSex());
            map.put("用户所在城市", yxWechatUser.getCity());
            map.put("用户的语言，简体中文为zh_CN", yxWechatUser.getLanguage());
            map.put("用户所在省份", yxWechatUser.getProvince());
            map.put("用户所在国家", yxWechatUser.getCountry());
            map.put("公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注", yxWechatUser.getRemark());
            map.put("用户所在的分组ID（兼容旧的用户分组接口）", yxWechatUser.getGroupid());
            map.put("用户被打上的标签ID列表", yxWechatUser.getTagidList());
            map.put("用户是否订阅该公众号标识", yxWechatUser.getSubscribe());
            map.put("关注公众号时间", yxWechatUser.getSubscribeTime());
            map.put("添加时间", yxWechatUser.getAddTime());
            map.put("一级推荐人", yxWechatUser.getStair());
            map.put("二级推荐人", yxWechatUser.getSecond());
            map.put("一级推荐人订单", yxWechatUser.getOrderStair());
            map.put("二级推荐人订单", yxWechatUser.getOrderSecond());
            map.put("佣金", yxWechatUser.getNowMoney());
            map.put("小程序用户会话密匙", yxWechatUser.getSessionKey());
            map.put("用户类型", yxWechatUser.getUserType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
