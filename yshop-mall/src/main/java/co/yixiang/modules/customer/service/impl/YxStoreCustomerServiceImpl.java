/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.customer.service.impl;

import co.yixiang.modules.customer.domain.YxStoreCustomer;
import co.yixiang.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.customer.service.YxStoreCustomerService;
import co.yixiang.modules.customer.service.dto.YxStoreCustomerDto;
import co.yixiang.modules.customer.service.dto.YxStoreCustomerQueryCriteria;
import co.yixiang.modules.customer.service.mapper.YxStoreCustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import co.yixiang.domain.PageResult;
/**
* @author Bug
* @date 2020-12-10
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxStoreCustomer")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCustomerServiceImpl extends BaseServiceImpl<YxStoreCustomerMapper, YxStoreCustomer> implements YxStoreCustomerService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public PageResult<YxStoreCustomerDto> queryAll(YxStoreCustomerQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCustomer> page = new PageInfo<>(queryAll(criteria));
        return generator.convertPageInfo(page,YxStoreCustomerDto.class);
    }


    @Override
    //@Cacheable
    public List<YxStoreCustomer> queryAll(YxStoreCustomerQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCustomer.class, criteria));
    }


    @Override
    public void download(List<YxStoreCustomerDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCustomerDto yxStoreCustomer : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户昵称", yxStoreCustomer.getNickName());
            map.put("openId", yxStoreCustomer.getOpenId());
            map.put("备注", yxStoreCustomer.getRemark());
            map.put("添加时间", yxStoreCustomer.getCreateTime());
            map.put("修改时间", yxStoreCustomer.getUpdateTime());
            map.put(" isDel",  yxStoreCustomer.getIsDel());
            map.put("是否启用", yxStoreCustomer.getIsEnable());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
