/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.shop.domain.YxStoreProductReply;
import co.yixiang.modules.shop.service.YxStoreProductReplyService;
import co.yixiang.modules.shop.service.YxStoreProductService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyDto;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyQueryCriteria;
import co.yixiang.modules.shop.service.mapper.StoreProductReplyMapper;
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
//@CacheConfig(cacheNames = "yxStoreProductReply")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreProductReplyServiceImpl extends BaseServiceImpl<StoreProductReplyMapper, YxStoreProductReply> implements YxStoreProductReplyService {

    private final IGenerator generator;

    private final YxUserService yxUserService;

    private final YxStoreProductService yxStoreProductService;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreProductReplyQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreProductReply> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxStoreProductReplyDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreProductReply> queryAll(YxStoreProductReplyQueryCriteria criteria) {
        List<YxStoreProductReply> storeProductReplyList = baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreProductReply.class, criteria));
        storeProductReplyList.forEach(yxStoreProductReply -> {
            yxStoreProductReply.setUser(yxUserService.getById(yxStoreProductReply.getUid()));
            ;
            yxStoreProductReply.setStoreProduct(yxStoreProductService.getById(yxStoreProductReply.getProductId()));
        });
        return storeProductReplyList;
    }


    @Override
    public void download(List<YxStoreProductReplyDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreProductReplyDto yxStoreProductReply : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("用户ID", yxStoreProductReply.getUid());
            map.put("订单ID", yxStoreProductReply.getOid());
            map.put("唯一id", yxStoreProductReply.getUnique());
            map.put("产品id", yxStoreProductReply.getProductId());
            map.put("某种商品类型(普通商品、秒杀商品）", yxStoreProductReply.getReplyType());
            map.put("商品分数", yxStoreProductReply.getProductScore());
            map.put("服务分数", yxStoreProductReply.getServiceScore());
            map.put("评论内容", yxStoreProductReply.getComment());
            map.put("评论图片", yxStoreProductReply.getPics());
            map.put("评论时间", yxStoreProductReply.getAddTime());
            map.put("管理员回复内容", yxStoreProductReply.getMerchantReplyContent());
            map.put("管理员回复时间", yxStoreProductReply.getMerchantReplyTime());
            map.put("0未回复1已回复", yxStoreProductReply.getIsReply());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
