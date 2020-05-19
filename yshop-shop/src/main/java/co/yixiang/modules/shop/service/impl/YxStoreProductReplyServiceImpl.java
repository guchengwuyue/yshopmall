/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxStoreProduct;
import co.yixiang.modules.shop.domain.YxStoreProductReply;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.service.YxStoreProductService;
import co.yixiang.modules.shop.service.YxUserService;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.shop.service.YxStoreProductReplyService;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyDto;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyQueryCriteria;
import co.yixiang.modules.shop.service.mapper.StoreProductReplyMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    public List<YxStoreProductReply> queryAll(YxStoreProductReplyQueryCriteria criteria){
        List<YxStoreProductReply> storeProductReplyList =  baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreProductReply.class, criteria));
        List<YxStoreProductReply> storeProductReplys = storeProductReplyList.stream().map(i ->{
            YxStoreProductReply yxStoreProductReply = new YxStoreProductReply();
            BeanUtils.copyProperties(i,yxStoreProductReply);
            yxStoreProductReply.setUser(yxUserService.getById(yxStoreProductReply.getUid()));;
            yxStoreProductReply.setStoreProduct(yxStoreProductService.getById(yxStoreProductReply.getProductId()));
            return yxStoreProductReply;
        }).collect(Collectors.toList());
        return storeProductReplys;
    }


    @Override
    public void download(List<YxStoreProductReplyDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreProductReplyDto yxStoreProductReply : all) {
            Map<String,Object> map = new LinkedHashMap<>();
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
            map.put("0未删除1已删除", yxStoreProductReply.getIsDel());
            map.put("0未回复1已回复", yxStoreProductReply.getIsReply());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
