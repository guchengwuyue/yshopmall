package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreCombination;
import co.yixiang.modules.activity.repository.YxStoreCombinationRepository;
import co.yixiang.modules.activity.repository.YxStorePinkRepository;
import co.yixiang.modules.activity.repository.YxStoreVisitRepository;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationDTO;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCombinationMapper;
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
* @date 2019-11-18
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCombinationServiceImpl implements YxStoreCombinationService {

    private final YxStoreCombinationRepository yxStoreCombinationRepository;
    private final YxStorePinkRepository storePinkRepository;
    private final YxStoreVisitRepository storeVisitRepository;

    private final YxStoreCombinationMapper yxStoreCombinationMapper;

    public YxStoreCombinationServiceImpl(YxStoreCombinationRepository yxStoreCombinationRepository, YxStorePinkRepository storePinkRepository,
                                         YxStoreVisitRepository storeVisitRepository, YxStoreCombinationMapper yxStoreCombinationMapper) {
        this.yxStoreCombinationRepository = yxStoreCombinationRepository;
        this.storePinkRepository = storePinkRepository;
        this.storeVisitRepository = storeVisitRepository;
        this.yxStoreCombinationMapper = yxStoreCombinationMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreCombinationQueryCriteria criteria, Pageable pageable){
        criteria.setIsDel(0);
        Page<YxStoreCombination> page = yxStoreCombinationRepository
                .findAll((root, criteriaQuery, criteriaBuilder)
                        -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<YxStoreCombinationDTO> combinationDTOS = yxStoreCombinationMapper
                .toDto(page.getContent());
        for (YxStoreCombinationDTO combinationDTO : combinationDTOS) {
            //参与人数
            combinationDTO.setCountPeopleAll(storePinkRepository
                    .countByCid(combinationDTO.getId()));

            //成团人数
            combinationDTO.setCountPeoplePink(storePinkRepository.countByCidAndKId(combinationDTO.getId(),
                    0));
            //获取查看拼团产品人数
            combinationDTO.setCountPeopleBrowse(storeVisitRepository
                    .countByProductIdAndProductType(combinationDTO.getId(),"combination"));

            //System.out.println(combinationDTO);

        }
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",combinationDTOS);
        map.put("totalElements",page.getTotalElements());

        return map;
        //return PageUtil.toPage(page.map(yxStoreCombinationMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onSale(Integer id, Integer status) {
        if(status == 1){
            status = 0;
        }else{
            status = 1;
        }
        yxStoreCombinationRepository.updateOnsale(status,id);
    }

    @Override
    public List<YxStoreCombinationDTO> queryAll(YxStoreCombinationQueryCriteria criteria){
        return yxStoreCombinationMapper.toDto(yxStoreCombinationRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreCombinationDTO findById(Integer id) {
        Optional<YxStoreCombination> yxStoreCombination = yxStoreCombinationRepository.findById(id);
        ValidationUtil.isNull(yxStoreCombination,"YxStoreCombination","id",id);
        return yxStoreCombinationMapper.toDto(yxStoreCombination.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreCombinationDTO create(YxStoreCombination resources) {
        return yxStoreCombinationMapper.toDto(yxStoreCombinationRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreCombination resources) {
        Optional<YxStoreCombination> optionalYxStoreCombination = yxStoreCombinationRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreCombination,"YxStoreCombination","id",resources.getId());
        YxStoreCombination yxStoreCombination = optionalYxStoreCombination.get();
        yxStoreCombination.copy(resources);
        yxStoreCombinationRepository.save(yxStoreCombination);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreCombinationRepository.deleteById(id);
    }
}