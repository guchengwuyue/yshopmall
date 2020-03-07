package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreCouponIssueUser;
import co.yixiang.modules.activity.repository.YxStoreCouponIssueUserRepository;
import co.yixiang.modules.activity.service.YxStoreCouponIssueUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueUserDTO;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueUserQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCouponIssueUserMapper;
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
public class YxStoreCouponIssueUserServiceImpl implements YxStoreCouponIssueUserService {

    private final YxStoreCouponIssueUserRepository yxStoreCouponIssueUserRepository;

    private final YxStoreCouponIssueUserMapper yxStoreCouponIssueUserMapper;

    public YxStoreCouponIssueUserServiceImpl(YxStoreCouponIssueUserRepository yxStoreCouponIssueUserRepository, YxStoreCouponIssueUserMapper yxStoreCouponIssueUserMapper) {
        this.yxStoreCouponIssueUserRepository = yxStoreCouponIssueUserRepository;
        this.yxStoreCouponIssueUserMapper = yxStoreCouponIssueUserMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreCouponIssueUserQueryCriteria criteria, Pageable pageable){
        Page<YxStoreCouponIssueUser> page = yxStoreCouponIssueUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxStoreCouponIssueUserMapper::toDto));
    }

    @Override
    public List<YxStoreCouponIssueUserDTO> queryAll(YxStoreCouponIssueUserQueryCriteria criteria){
        return yxStoreCouponIssueUserMapper.toDto(yxStoreCouponIssueUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreCouponIssueUserDTO findById(Integer id) {
        Optional<YxStoreCouponIssueUser> yxStoreCouponIssueUser = yxStoreCouponIssueUserRepository.findById(id);
        ValidationUtil.isNull(yxStoreCouponIssueUser,"YxStoreCouponIssueUser","id",id);
        return yxStoreCouponIssueUserMapper.toDto(yxStoreCouponIssueUser.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreCouponIssueUserDTO create(YxStoreCouponIssueUser resources) {
        return yxStoreCouponIssueUserMapper.toDto(yxStoreCouponIssueUserRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreCouponIssueUser resources) {
        Optional<YxStoreCouponIssueUser> optionalYxStoreCouponIssueUser = yxStoreCouponIssueUserRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreCouponIssueUser,"YxStoreCouponIssueUser","id",resources.getId());
        YxStoreCouponIssueUser yxStoreCouponIssueUser = optionalYxStoreCouponIssueUser.get();
        yxStoreCouponIssueUser.copy(resources);
        yxStoreCouponIssueUserRepository.save(yxStoreCouponIssueUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreCouponIssueUserRepository.deleteById(id);
    }
}