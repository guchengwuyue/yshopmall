package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxSystemStore;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.shop.repository.YxSystemStoreRepository;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.shop.service.dto.YxSystemStoreDto;
import co.yixiang.modules.shop.service.dto.YxSystemStoreQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxSystemStoreMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author hupeng
* @date 2020-03-03
*/
@Service
//@CacheConfig(cacheNames = "yxSystemStore")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemStoreServiceImpl implements YxSystemStoreService {

    private final YxSystemStoreRepository yxSystemStoreRepository;

    private final YxSystemStoreMapper yxSystemStoreMapper;

    public YxSystemStoreServiceImpl(YxSystemStoreRepository yxSystemStoreRepository, YxSystemStoreMapper yxSystemStoreMapper) {
        this.yxSystemStoreRepository = yxSystemStoreRepository;
        this.yxSystemStoreMapper = yxSystemStoreMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(YxSystemStoreQueryCriteria criteria, Pageable pageable){
        Page<YxSystemStore> page = yxSystemStoreRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxSystemStoreMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<YxSystemStoreDto> queryAll(YxSystemStoreQueryCriteria criteria){
        return yxSystemStoreMapper.toDto(yxSystemStoreRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public YxSystemStoreDto findById(Integer id) {
        YxSystemStore yxSystemStore = yxSystemStoreRepository.findById(id).orElseGet(YxSystemStore::new);
        ValidationUtil.isNull(yxSystemStore.getId(),"YxSystemStore","id",id);
        return yxSystemStoreMapper.toDto(yxSystemStore);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public YxSystemStoreDto create(YxSystemStore resources) {
        return yxSystemStoreMapper.toDto(yxSystemStoreRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(YxSystemStore resources) {
        YxSystemStore yxSystemStore = yxSystemStoreRepository.findById(resources.getId()).orElseGet(YxSystemStore::new);
        ValidationUtil.isNull( yxSystemStore.getId(),"YxSystemStore","id",resources.getId());
        yxSystemStore.copy(resources);
        yxSystemStoreRepository.save(yxSystemStore);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            yxSystemStoreRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<YxSystemStoreDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxSystemStoreDto yxSystemStore : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("门店名称", yxSystemStore.getName());
            map.put("简介", yxSystemStore.getIntroduction());
            map.put("手机号码", yxSystemStore.getPhone());
            map.put("省市区", yxSystemStore.getAddress());
            map.put("详细地址", yxSystemStore.getDetailedAddress());
            map.put("门店logo", yxSystemStore.getImage());
            map.put("纬度", yxSystemStore.getLatitude());
            map.put("经度", yxSystemStore.getLongitude());
            map.put("核销有效日期", yxSystemStore.getValidTime());
            map.put("每日营业开关时间", yxSystemStore.getDayTime());
            map.put("添加时间", yxSystemStore.getAddTime());
            map.put("是否显示", yxSystemStore.getIsShow());
            map.put("是否删除", yxSystemStore.getIsDel());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}