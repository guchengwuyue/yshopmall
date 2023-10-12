/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.product.service.impl;

import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.domain.PageResult;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.product.domain.YxStoreProductRelation;
import co.yixiang.modules.product.service.YxStoreProductRelationService;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.modules.product.service.dto.YxStoreProductRelationDto;
import co.yixiang.modules.product.service.dto.YxStoreProductRelationQueryCriteria;
import co.yixiang.modules.product.service.mapper.YxStoreProductRelationMapper;
import co.yixiang.modules.product.vo.YxStoreProductRelationQueryVo;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.utils.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 商品点赞和收藏表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-10-23
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class YxStoreProductRelationServiceImpl extends BaseServiceImpl<YxStoreProductRelationMapper, YxStoreProductRelation> implements YxStoreProductRelationService {

    private final YxStoreProductRelationMapper yxStoreProductRelationMapper;
    private final YxStoreProductService storeProductService;
    private final YxUserService userService;
    private final IGenerator generator;

    /**
     * 获取用户收藏列表
     * @param page page
     * @param limit limit
     * @param uid 用户id
     * @return list
     */
    @Override
    public List<YxStoreProductRelationQueryVo> userCollectProduct(int page, int limit, Long uid,String type) {
        Page<YxStoreProductRelation> pageModel = new Page<>(page, limit);
        List<YxStoreProductRelationQueryVo> list = yxStoreProductRelationMapper.selectRelationList(pageModel,uid,type);
        return list;
    }

    /**
     * 添加收藏
     * @param productId 商品id
     * @param uid 用户id
     */
    @Override
    public void addRroductRelation(long productId,long uid,String category) {
        if(isProductRelation(productId,uid)) {
            throw new YshopException("已收藏");
        }
        YxStoreProductRelation storeProductRelation = YxStoreProductRelation.builder()
                .productId(productId)
                .uid(uid)
                .type(category)
                .build();
        yxStoreProductRelationMapper.insert(storeProductRelation);
    }

    /**
     * 取消收藏
     * @param productId 商品id
     * @param uid 用户id
     */
    @Override
    public void delRroductRelation(long productId,long uid,String category) {
        YxStoreProductRelation productRelation = this.lambdaQuery()
                .eq(YxStoreProductRelation::getProductId,productId)
                .eq(YxStoreProductRelation::getUid,uid)
                .eq(YxStoreProductRelation::getType,category)
                .one();
        if(productRelation == null) {
            throw new YshopException("已取消");
        }
        this.removeById(productRelation.getId());
    }


    /**
     * 是否收藏
     * @param productId 商品ID
     * @param uid 用户ID
     * @return Boolean
     */
    @Override
    public Boolean isProductRelation(long productId, long uid) {
        Long count = yxStoreProductRelationMapper
                .selectCount(Wrappers.<YxStoreProductRelation>lambdaQuery()
                        .eq(YxStoreProductRelation::getUid,uid)
                        .eq(YxStoreProductRelation::getType,"collect")
                        .eq(YxStoreProductRelation::getProductId,productId));
        if(count > 0) {
            return true;
        }

        return false;
    }

    @Override
    //@Cacheable
    public PageResult<YxStoreProductRelationDto> queryAll(YxStoreProductRelationQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreProductRelation> page = new PageInfo<>(queryAll(criteria));
        PageResult<YxStoreProductRelationDto> relationDtoPageResult = generator.convertPageInfo(page, YxStoreProductRelationDto.class);
        relationDtoPageResult.getContent().forEach(i ->{
            i.setProduct(storeProductService.getById(i.getProductId()));
            i.setUserName(userService.getYxUserById(i.getUid()).getNickname());
        });
        return relationDtoPageResult;
    }


    @Override
    //@Cacheable
    public List<YxStoreProductRelation> queryAll(YxStoreProductRelationQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreProductRelation.class, criteria));
    }


    @Override
    public void download(List<YxStoreProductRelationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreProductRelationDto yxStoreProductRelation : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户ID", yxStoreProductRelation.getUid());
            map.put("商品ID", yxStoreProductRelation.getProductId());
            map.put("类型(收藏(collect）、点赞(like))", yxStoreProductRelation.getType());
            map.put("某种类型的商品(普通商品、秒杀商品)", yxStoreProductRelation.getCategory());
            map.put("添加时间", yxStoreProductRelation.getCreateTime());
            map.put(" updateTime",  yxStoreProductRelation.getUpdateTime());
            map.put(" isDel",  yxStoreProductRelation.getIsDel());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void collectDelFoot(List<Long> ids) {
        yxStoreProductRelationMapper.deleteBatchIds(ids);
    }
}
