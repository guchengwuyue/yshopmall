package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.repository.YxUserExtractRepository;
import co.yixiang.modules.activity.service.YxUserExtractService;
import co.yixiang.modules.activity.service.dto.YxUserExtractDTO;
import co.yixiang.modules.activity.service.dto.YxUserExtractQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxUserExtractMapper;
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
* @date 2019-11-14
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserExtractServiceImpl implements YxUserExtractService {

    private final YxUserExtractRepository yxUserExtractRepository;

    private final YxUserExtractMapper yxUserExtractMapper;

    public YxUserExtractServiceImpl(YxUserExtractRepository yxUserExtractRepository, YxUserExtractMapper yxUserExtractMapper) {
        this.yxUserExtractRepository = yxUserExtractRepository;
        this.yxUserExtractMapper = yxUserExtractMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxUserExtractQueryCriteria criteria, Pageable pageable){
        Page<YxUserExtract> page = yxUserExtractRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxUserExtractMapper::toDto));
    }

    @Override
    public List<YxUserExtractDTO> queryAll(YxUserExtractQueryCriteria criteria){
        return yxUserExtractMapper.toDto(yxUserExtractRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxUserExtractDTO findById(Integer id) {
        Optional<YxUserExtract> yxUserExtract = yxUserExtractRepository.findById(id);
        ValidationUtil.isNull(yxUserExtract,"YxUserExtract","id",id);
        return yxUserExtractMapper.toDto(yxUserExtract.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxUserExtractDTO create(YxUserExtract resources) {
        return yxUserExtractMapper.toDto(yxUserExtractRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxUserExtract resources) {
        Optional<YxUserExtract> optionalYxUserExtract = yxUserExtractRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxUserExtract,"YxUserExtract","id",resources.getId());
        YxUserExtract yxUserExtract = optionalYxUserExtract.get();
        yxUserExtract.copy(resources);
        yxUserExtractRepository.save(yxUserExtract);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxUserExtractRepository.deleteById(id);
    }
}