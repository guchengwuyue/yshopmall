/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxAppVersion;
import co.yixiang.common.service.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import co.yixiang.dozer.service.IGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.shop.service.YxAppVersionService;
import co.yixiang.modules.shop.service.dto.YxAppVersionDto;
import co.yixiang.modules.shop.service.dto.YxAppVersionQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxAppVersionMapper;
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
* @author lioncity
* @date 2020-12-09
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "yxAppVersion")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxAppVersionServiceImpl extends BaseServiceImpl<YxAppVersionMapper, YxAppVersion> implements YxAppVersionService {

    private final IGenerator generator;

    @Override
    //@Cacheable
    public PageResult<YxAppVersionDto> queryAll(YxAppVersionQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxAppVersion> page = new PageInfo<>(queryAll(criteria));
        return generator.convertPageInfo(page,YxAppVersionDto.class);
    }


    @Override
    //@Cacheable
    public List<YxAppVersion> queryAll(YxAppVersionQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxAppVersion.class, criteria));
    }


    @Override
    public void download(List<YxAppVersionDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxAppVersionDto yxAppVersion : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" isDel",  yxAppVersion.getIsDel());
            map.put("更新时间", yxAppVersion.getCreateTime());
            map.put(" updateTime",  yxAppVersion.getUpdateTime());
            map.put("版本code", yxAppVersion.getVersionCode());
            map.put("版本名称", yxAppVersion.getVersionName());
            map.put("版本描述", yxAppVersion.getVersionInfo());
            map.put("安卓下载链接", yxAppVersion.getAndroidUrl());
            map.put("是否强制升级", yxAppVersion.getForceUpdate());
            map.put("ios store应用商店链接", yxAppVersion.getIosUrl());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
