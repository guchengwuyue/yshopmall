/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.product.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.product.domain.YxStoreProductRule;
import co.yixiang.modules.product.service.YxStoreProductRuleService;
import co.yixiang.modules.product.service.dto.YxStoreProductRuleDto;
import co.yixiang.modules.product.service.dto.YxStoreProductRuleQueryCriteria;
import co.yixiang.modules.product.service.mapper.YxStoreProductRuleMapper;
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



/**
* @author hupeng
* @date 2020-06-28
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreProductRuleServiceImpl extends BaseServiceImpl<YxStoreProductRuleMapper, YxStoreProductRule> implements YxStoreProductRuleService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreProductRuleQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreProductRule> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", page.getList());
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreProductRule> queryAll(YxStoreProductRuleQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreProductRule.class, criteria));
    }


    @Override
    public void download(List<YxStoreProductRule> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreProductRule yxStoreProductRule : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("规格名称", yxStoreProductRule.getRuleName());
            map.put("规格值", yxStoreProductRule.getRuleValue());
            map.put(" createTime",  yxStoreProductRule.getCreateTime());
            map.put(" updateTime",  yxStoreProductRule.getUpdateTime());
            map.put(" isDel",  yxStoreProductRule.getIsDel());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
