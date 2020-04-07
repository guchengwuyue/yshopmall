package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxSystemStoreStaff;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.shop.service.dto.YxSystemStoreDto;
import co.yixiang.utils.*;
import co.yixiang.modules.shop.repository.YxSystemStoreStaffRepository;
import co.yixiang.modules.shop.service.YxSystemStoreStaffService;
import co.yixiang.modules.shop.service.dto.YxSystemStoreStaffDto;
import co.yixiang.modules.shop.service.dto.YxSystemStoreStaffQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxSystemStoreStaffMapper;
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

/**
* @author hupeng
* @date 2020-03-22
*/
@Service
//@CacheConfig(cacheNames = "yxSystemStoreStaff")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemStoreStaffServiceImpl implements YxSystemStoreStaffService {

    private final YxSystemStoreStaffRepository yxSystemStoreStaffRepository;

    private final YxSystemStoreStaffMapper yxSystemStoreStaffMapper;

    private final YxSystemStoreService systemStoreService;

    public YxSystemStoreStaffServiceImpl(YxSystemStoreStaffRepository yxSystemStoreStaffRepository,
                                         YxSystemStoreStaffMapper yxSystemStoreStaffMapper,
                                         YxSystemStoreService systemStoreService) {
        this.yxSystemStoreStaffRepository = yxSystemStoreStaffRepository;
        this.yxSystemStoreStaffMapper = yxSystemStoreStaffMapper;
        this.systemStoreService = systemStoreService;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(YxSystemStoreStaffQueryCriteria criteria, Pageable pageable){
        Page<YxSystemStoreStaff> page = yxSystemStoreStaffRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxSystemStoreStaffMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<YxSystemStoreStaffDto> queryAll(YxSystemStoreStaffQueryCriteria criteria){
        return yxSystemStoreStaffMapper.toDto(yxSystemStoreStaffRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public YxSystemStoreStaffDto findById(Integer id) {
        YxSystemStoreStaff yxSystemStoreStaff = yxSystemStoreStaffRepository.findById(id).orElseGet(YxSystemStoreStaff::new);
        ValidationUtil.isNull(yxSystemStoreStaff.getId(),"YxSystemStoreStaff","id",id);
        return yxSystemStoreStaffMapper.toDto(yxSystemStoreStaff);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public YxSystemStoreStaffDto create(YxSystemStoreStaff resources) {
        YxSystemStoreDto systemStoreDto = systemStoreService.findById(resources.getStoreId());
        resources.setStoreName(systemStoreDto.getName());
        resources.setAddTime(OrderUtil.getSecondTimestampTwo());
        return yxSystemStoreStaffMapper.toDto(yxSystemStoreStaffRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(YxSystemStoreStaff resources) {
        YxSystemStoreDto systemStoreDto = systemStoreService.findById(resources.getStoreId());
        resources.setStoreName(systemStoreDto.getName());
        YxSystemStoreStaff yxSystemStoreStaff = yxSystemStoreStaffRepository.findById(resources.getId()).orElseGet(YxSystemStoreStaff::new);
        ValidationUtil.isNull( yxSystemStoreStaff.getId(),"YxSystemStoreStaff","id",resources.getId());
        yxSystemStoreStaff.copy(resources);
        yxSystemStoreStaffRepository.save(yxSystemStoreStaff);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            yxSystemStoreStaffRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<YxSystemStoreStaffDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxSystemStoreStaffDto yxSystemStoreStaff : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("微信用户id", yxSystemStoreStaff.getUid());
            map.put("店员头像", yxSystemStoreStaff.getAvatar());
            map.put("门店id", yxSystemStoreStaff.getStoreId());
            map.put("店员名称", yxSystemStoreStaff.getStaffName());
            map.put("手机号码", yxSystemStoreStaff.getPhone());
            map.put("核销开关", yxSystemStoreStaff.getVerifyStatus());
            map.put("状态", yxSystemStoreStaff.getStatus());
            map.put("添加时间", yxSystemStoreStaff.getAddTime());
            map.put("微信昵称", yxSystemStoreStaff.getNickname());
            map.put("所属门店", yxSystemStoreStaff.getStoreName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}