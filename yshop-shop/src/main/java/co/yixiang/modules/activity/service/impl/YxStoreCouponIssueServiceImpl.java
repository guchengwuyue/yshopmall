package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreCouponIssue;
import co.yixiang.modules.activity.repository.YxStoreCouponIssueRepository;
import co.yixiang.modules.activity.service.YxStoreCouponIssueService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueDTO;
import co.yixiang.modules.activity.service.dto.YxStoreCouponIssueQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCouponIssueMapper;
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
public class YxStoreCouponIssueServiceImpl implements YxStoreCouponIssueService {

    private final YxStoreCouponIssueRepository yxStoreCouponIssueRepository;

    private final YxStoreCouponIssueMapper yxStoreCouponIssueMapper;

    public YxStoreCouponIssueServiceImpl(YxStoreCouponIssueRepository yxStoreCouponIssueRepository, YxStoreCouponIssueMapper yxStoreCouponIssueMapper) {
        this.yxStoreCouponIssueRepository = yxStoreCouponIssueRepository;
        this.yxStoreCouponIssueMapper = yxStoreCouponIssueMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreCouponIssueQueryCriteria criteria, Pageable pageable){
        Page<YxStoreCouponIssue> page = yxStoreCouponIssueRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxStoreCouponIssueMapper::toDto));
    }

    @Override
    public List<YxStoreCouponIssueDTO> queryAll(YxStoreCouponIssueQueryCriteria criteria){
        return yxStoreCouponIssueMapper.toDto(yxStoreCouponIssueRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreCouponIssueDTO findById(Integer id) {
        Optional<YxStoreCouponIssue> yxStoreCouponIssue = yxStoreCouponIssueRepository.findById(id);
        ValidationUtil.isNull(yxStoreCouponIssue,"YxStoreCouponIssue","id",id);
        return yxStoreCouponIssueMapper.toDto(yxStoreCouponIssue.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreCouponIssueDTO create(YxStoreCouponIssue resources) {
        return yxStoreCouponIssueMapper.toDto(yxStoreCouponIssueRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreCouponIssue resources) {
        Optional<YxStoreCouponIssue> optionalYxStoreCouponIssue = yxStoreCouponIssueRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreCouponIssue,"YxStoreCouponIssue","id",resources.getId());
        YxStoreCouponIssue yxStoreCouponIssue = optionalYxStoreCouponIssue.get();
        yxStoreCouponIssue.copy(resources);
        yxStoreCouponIssueRepository.save(yxStoreCouponIssue);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreCouponIssueRepository.deleteById(id);
    }
}