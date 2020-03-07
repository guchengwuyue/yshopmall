package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxSystemGroupData;
import co.yixiang.modules.shop.repository.YxSystemGroupDataRepository;
import co.yixiang.modules.shop.service.YxSystemGroupDataService;
import co.yixiang.modules.shop.service.dto.YxSystemGroupDataDTO;
import co.yixiang.modules.shop.service.dto.YxSystemGroupDataQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxSystemGroupDataMapper;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.ValidationUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author hupeng
* @date 2019-10-18
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemGroupDataServiceImpl implements YxSystemGroupDataService {

    private final YxSystemGroupDataRepository yxSystemGroupDataRepository;

    private final YxSystemGroupDataMapper yxSystemGroupDataMapper;

    public YxSystemGroupDataServiceImpl(YxSystemGroupDataRepository yxSystemGroupDataRepository, YxSystemGroupDataMapper yxSystemGroupDataMapper) {
        this.yxSystemGroupDataRepository = yxSystemGroupDataRepository;
        this.yxSystemGroupDataMapper = yxSystemGroupDataMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxSystemGroupDataQueryCriteria criteria, Pageable pageable){
        Page<YxSystemGroupData> page = yxSystemGroupDataRepository
                .findAll((root, criteriaQuery, criteriaBuilder)
                        -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)
                        ,pageable);
        List<YxSystemGroupDataDTO> systemGroupDataDTOS = new ArrayList<>();
        for (YxSystemGroupData systemGroupData : page.getContent()) {

            YxSystemGroupDataDTO systemGroupDataDTO = yxSystemGroupDataMapper
                    .toDto(systemGroupData);
            systemGroupDataDTO.setMap(JSON.parseObject(systemGroupData.getValue()));
            systemGroupDataDTOS.add(systemGroupDataDTO);
        }
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",systemGroupDataDTOS);
        map.put("totalElements",page.getTotalElements());
        return map;
    }

    @Override
    public List<YxSystemGroupDataDTO> queryAll(YxSystemGroupDataQueryCriteria criteria){
        return yxSystemGroupDataMapper.toDto(yxSystemGroupDataRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxSystemGroupDataDTO findById(Integer id) {
        Optional<YxSystemGroupData> yxSystemGroupData = yxSystemGroupDataRepository.findById(id);
        ValidationUtil.isNull(yxSystemGroupData,"YxSystemGroupData","id",id);
        return yxSystemGroupDataMapper.toDto(yxSystemGroupData.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxSystemGroupDataDTO create(YxSystemGroupData resources) {
        return yxSystemGroupDataMapper.toDto(yxSystemGroupDataRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxSystemGroupData resources) {
        Optional<YxSystemGroupData> optionalYxSystemGroupData = yxSystemGroupDataRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxSystemGroupData,"YxSystemGroupData","id",resources.getId());
        YxSystemGroupData yxSystemGroupData = optionalYxSystemGroupData.get();
        yxSystemGroupData.copy(resources);
        yxSystemGroupDataRepository.save(yxSystemGroupData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxSystemGroupDataRepository.deleteById(id);
    }
}