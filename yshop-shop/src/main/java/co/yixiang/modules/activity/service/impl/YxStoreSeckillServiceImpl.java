package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreSeckill;
import co.yixiang.modules.activity.repository.YxStoreSeckillRepository;
import co.yixiang.modules.activity.service.YxStoreSeckillService;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillDTO;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreSeckillMapper;
import co.yixiang.utils.OrderUtil;
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
* @author xuwenbo
* @date 2019-12-14
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreSeckillServiceImpl implements YxStoreSeckillService {

    private final YxStoreSeckillRepository yxStoreSeckillRepository;

    private final YxStoreSeckillMapper yxStoreSeckillMapper;

    public YxStoreSeckillServiceImpl(YxStoreSeckillRepository yxStoreSeckillRepository, YxStoreSeckillMapper yxStoreSeckillMapper) {
        this.yxStoreSeckillRepository = yxStoreSeckillRepository;
        this.yxStoreSeckillMapper = yxStoreSeckillMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreSeckillQueryCriteria criteria, Pageable pageable){
        Page<YxStoreSeckill> page = yxStoreSeckillRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<YxStoreSeckillDTO> storeSeckillDTOS = yxStoreSeckillMapper
                .toDto(page.getContent());
        int nowTime = OrderUtil.getSecondTimestampTwo();
        for (YxStoreSeckillDTO storeSeckillDTO : storeSeckillDTOS){
            if(storeSeckillDTO.getStatus() > 0){
                if(storeSeckillDTO.getStartTime() > nowTime){
                    storeSeckillDTO.setStatusStr("活动未开始");
                }else if(storeSeckillDTO.getStopTime() < nowTime){
                    storeSeckillDTO.setStatusStr("活动已结束");
                }else if(storeSeckillDTO.getStopTime() > nowTime && storeSeckillDTO.getStartTime() < nowTime){
                    storeSeckillDTO.setStatusStr("正在进行中");
                }
            }else {
                storeSeckillDTO.setStatusStr("关闭");
            }

        }
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",storeSeckillDTOS);
        map.put("totalElements",page.getTotalElements());

        return map;
    }

    @Override
    public List<YxStoreSeckillDTO> queryAll(YxStoreSeckillQueryCriteria criteria){
        return yxStoreSeckillMapper.toDto(yxStoreSeckillRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreSeckillDTO findById(Integer id) {
        Optional<YxStoreSeckill> yxStoreSeckill = yxStoreSeckillRepository.findById(id);
        ValidationUtil.isNull(yxStoreSeckill,"YxStoreSeckill","id",id);
        return yxStoreSeckillMapper.toDto(yxStoreSeckill.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreSeckillDTO create(YxStoreSeckill resources) {
        return yxStoreSeckillMapper.toDto(yxStoreSeckillRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreSeckill resources) {
        Optional<YxStoreSeckill> optionalYxStoreSeckill = yxStoreSeckillRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreSeckill,"YxStoreSeckill","id",resources.getId());
        YxStoreSeckill yxStoreSeckill = optionalYxStoreSeckill.get();
        yxStoreSeckill.copy(resources);
        yxStoreSeckillRepository.save(yxStoreSeckill);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreSeckillRepository.deleteById(id);
    }
}