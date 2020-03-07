package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.repository.YxUserBillRepository;
import co.yixiang.modules.shop.service.YxUserBillService;
import co.yixiang.modules.shop.service.dto.YxUserBillDTO;
import co.yixiang.modules.shop.service.dto.YxUserBillQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxUserBillMapper;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
* @author hupeng
* @date 2019-11-06
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserBillServiceImpl implements YxUserBillService {

    private final YxUserBillRepository yxUserBillRepository;

    private final YxUserBillMapper yxUserBillMapper;

    public YxUserBillServiceImpl(YxUserBillRepository yxUserBillRepository, YxUserBillMapper yxUserBillMapper) {
        this.yxUserBillRepository = yxUserBillRepository;
        this.yxUserBillMapper = yxUserBillMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxUserBillQueryCriteria criteria, Pageable pageable){
        Page<Map> page = yxUserBillRepository.findAllByPageable(criteria.getCategory()
                ,criteria.getType(),criteria.getNickname(),pageable);
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",page.getContent());
        map.put("totalElements",page.getTotalElements());
        return map;
    }

    @Override
    public List<YxUserBillDTO> queryAll(YxUserBillQueryCriteria criteria){
        return yxUserBillMapper.toDto(yxUserBillRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxUserBillDTO findById(Integer id) {
        Optional<YxUserBill> yxUserBill = yxUserBillRepository.findById(id);
        ValidationUtil.isNull(yxUserBill,"YxUserBill","id",id);
        return yxUserBillMapper.toDto(yxUserBill.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxUserBillDTO create(YxUserBill resources) {
        return yxUserBillMapper.toDto(yxUserBillRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxUserBill resources) {
        Optional<YxUserBill> optionalYxUserBill = yxUserBillRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxUserBill,"YxUserBill","id",resources.getId());
        YxUserBill yxUserBill = optionalYxUserBill.get();
        yxUserBill.copy(resources);
        yxUserBillRepository.save(yxUserBill);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxUserBillRepository.deleteById(id);
    }
}