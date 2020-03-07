package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxUserRecharge;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.utils.FileUtil;
import co.yixiang.modules.shop.repository.YxUserRechargeRepository;
import co.yixiang.modules.shop.service.YxUserRechargeService;
import co.yixiang.modules.shop.service.dto.YxUserRechargeDto;
import co.yixiang.modules.shop.service.dto.YxUserRechargeQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxUserRechargeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.yixiang.exception.EntityExistException;
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
* @date 2020-03-02
*/
@Service
//@CacheConfig(cacheNames = "yxUserRecharge")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserRechargeServiceImpl implements YxUserRechargeService {

    private final YxUserRechargeRepository yxUserRechargeRepository;

    private final YxUserRechargeMapper yxUserRechargeMapper;

    public YxUserRechargeServiceImpl(YxUserRechargeRepository yxUserRechargeRepository, YxUserRechargeMapper yxUserRechargeMapper) {
        this.yxUserRechargeRepository = yxUserRechargeRepository;
        this.yxUserRechargeMapper = yxUserRechargeMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(YxUserRechargeQueryCriteria criteria, Pageable pageable){
        Page<YxUserRecharge> page = yxUserRechargeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxUserRechargeMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<YxUserRechargeDto> queryAll(YxUserRechargeQueryCriteria criteria){
        return yxUserRechargeMapper.toDto(yxUserRechargeRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public YxUserRechargeDto findById(Integer id) {
        YxUserRecharge yxUserRecharge = yxUserRechargeRepository.findById(id).orElseGet(YxUserRecharge::new);
        ValidationUtil.isNull(yxUserRecharge.getId(),"YxUserRecharge","id",id);
        return yxUserRechargeMapper.toDto(yxUserRecharge);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public YxUserRechargeDto create(YxUserRecharge resources) {
        if(yxUserRechargeRepository.findByOrderId(resources.getOrderId()) != null){
            throw new EntityExistException(YxUserRecharge.class,"order_id",resources.getOrderId());
        }
        return yxUserRechargeMapper.toDto(yxUserRechargeRepository.save(resources));
    }


    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            yxUserRechargeRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<YxUserRechargeDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxUserRechargeDto yxUserRecharge : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("充值用户UID", yxUserRecharge.getUid());
            map.put("订单号", yxUserRecharge.getOrderId());
            map.put("充值金额", yxUserRecharge.getPrice());
            map.put("充值类型", yxUserRecharge.getRechargeType());
            map.put("是否充值", yxUserRecharge.getPaid());
            map.put("充值支付时间", yxUserRecharge.getPayTime());
            map.put("充值时间", yxUserRecharge.getAddTime());
            map.put("退款金额", yxUserRecharge.getRefundPrice());
            map.put("昵称", yxUserRecharge.getNickname());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}