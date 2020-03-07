package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxSystemUserTask;
import co.yixiang.modules.shop.repository.YxSystemUserTaskRepository;
import co.yixiang.modules.shop.service.YxSystemUserLevelService;
import co.yixiang.modules.shop.service.YxSystemUserTaskService;
import co.yixiang.modules.shop.service.dto.YxSystemUserTaskDTO;
import co.yixiang.modules.shop.service.dto.YxSystemUserTaskQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxSystemUserTaskMapper;
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
* @date 2019-12-04
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxSystemUserTaskServiceImpl implements YxSystemUserTaskService {

    private final YxSystemUserTaskRepository yxSystemUserTaskRepository;

    private final YxSystemUserTaskMapper yxSystemUserTaskMapper;

    private final YxSystemUserLevelService systemUserLevelService;

    public YxSystemUserTaskServiceImpl(YxSystemUserTaskRepository yxSystemUserTaskRepository, YxSystemUserTaskMapper yxSystemUserTaskMapper, YxSystemUserLevelService systemUserLevelService) {
        this.yxSystemUserTaskRepository = yxSystemUserTaskRepository;
        this.yxSystemUserTaskMapper = yxSystemUserTaskMapper;
        this.systemUserLevelService = systemUserLevelService;
    }

    @Override
    public Map<String,Object> queryAll(YxSystemUserTaskQueryCriteria criteria, Pageable pageable){

        Page<YxSystemUserTask> page = yxSystemUserTaskRepository
                .findAll((root, criteriaQuery, criteriaBuilder)
                        -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);

        List<YxSystemUserTaskDTO> systemUserTaskDTOS = yxSystemUserTaskMapper
                .toDto(page.getContent());
        for (YxSystemUserTaskDTO systemUserTaskDTO : systemUserTaskDTOS) {
            systemUserTaskDTO.setLevalName(systemUserLevelService
                    .findById(systemUserTaskDTO.getLevelId()).getName());
        }

        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",systemUserTaskDTOS);
        map.put("totalElements",page.getTotalElements());

        return map;

    }

    @Override
    public List<YxSystemUserTaskDTO> queryAll(YxSystemUserTaskQueryCriteria criteria){
        return yxSystemUserTaskMapper.toDto(yxSystemUserTaskRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxSystemUserTaskDTO findById(Integer id) {
        Optional<YxSystemUserTask> yxSystemUserTask = yxSystemUserTaskRepository.findById(id);
        ValidationUtil.isNull(yxSystemUserTask,"YxSystemUserTask","id",id);
        return yxSystemUserTaskMapper.toDto(yxSystemUserTask.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxSystemUserTaskDTO create(YxSystemUserTask resources) {
        return yxSystemUserTaskMapper.toDto(yxSystemUserTaskRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxSystemUserTask resources) {
        Optional<YxSystemUserTask> optionalYxSystemUserTask = yxSystemUserTaskRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxSystemUserTask,"YxSystemUserTask","id",resources.getId());
        YxSystemUserTask yxSystemUserTask = optionalYxSystemUserTask.get();
        yxSystemUserTask.copy(resources);
        yxSystemUserTaskRepository.save(yxSystemUserTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxSystemUserTaskRepository.deleteById(id);
    }

    /**
     * 任务类型
     * @return
     */
    @Override
    public List<Map<String, Object>> getTaskType() {
        List<Map<String, Object>> list = null;
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type","SatisfactionIntegral");
        map.put("type","SatisfactionIntegral");
        map.put("type","SatisfactionIntegral");
        map.put("type","SatisfactionIntegral");
        map.put("type","SatisfactionIntegral");
        map.put("type","SatisfactionIntegral");
        return null;
    }
}