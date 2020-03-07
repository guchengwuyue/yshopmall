package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreCoupon;
import co.yixiang.modules.activity.repository.YxStoreCouponRepository;
import co.yixiang.modules.activity.service.YxStoreCouponService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponDTO;
import co.yixiang.modules.activity.service.dto.YxStoreCouponQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCouponMapper;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
* @author hupeng
* @date 2019-11-09
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCouponServiceImpl implements YxStoreCouponService {

    private final YxStoreCouponRepository yxStoreCouponRepository;

    private final YxStoreCouponMapper yxStoreCouponMapper;

    public YxStoreCouponServiceImpl(YxStoreCouponRepository yxStoreCouponRepository, YxStoreCouponMapper yxStoreCouponMapper) {
        this.yxStoreCouponRepository = yxStoreCouponRepository;
        this.yxStoreCouponMapper = yxStoreCouponMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreCouponQueryCriteria criteria, Pageable pageable){
        Page<YxStoreCoupon> page = yxStoreCouponRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxStoreCouponMapper::toDto));
    }

    @Override
    public List<YxStoreCouponDTO> queryAll(YxStoreCouponQueryCriteria criteria){
        return yxStoreCouponMapper.toDto(yxStoreCouponRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreCouponDTO findById(Integer id) {
        Optional<YxStoreCoupon> yxStoreCoupon = yxStoreCouponRepository.findById(id);
        ValidationUtil.isNull(yxStoreCoupon,"YxStoreCoupon","id",id);
        return yxStoreCouponMapper.toDto(yxStoreCoupon.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreCouponDTO create(YxStoreCoupon resources) {
        return yxStoreCouponMapper.toDto(yxStoreCouponRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreCoupon resources) {
        Optional<YxStoreCoupon> optionalYxStoreCoupon = yxStoreCouponRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreCoupon,"YxStoreCoupon","id",resources.getId());
        YxStoreCoupon yxStoreCoupon = optionalYxStoreCoupon.get();
        yxStoreCoupon.copy(resources);
        yxStoreCouponRepository.save(yxStoreCoupon);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreCouponRepository.deleteById(id);
    }
}