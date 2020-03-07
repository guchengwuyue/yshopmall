package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.repository.YxStorePinkRepository;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationDTO;
import co.yixiang.modules.activity.service.dto.YxStorePinkDTO;
import co.yixiang.modules.activity.service.dto.YxStorePinkQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStorePinkMapper;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.YxUserDTO;
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
public class YxStorePinkServiceImpl implements YxStorePinkService {

    private final YxStorePinkRepository yxStorePinkRepository;

    private final YxStoreCombinationService combinationService;
    private final YxUserService userService;

    private final YxStorePinkMapper yxStorePinkMapper;

    public YxStorePinkServiceImpl(YxStorePinkRepository yxStorePinkRepository, YxStoreCombinationService combinationService,
                                  YxUserService userService, YxStorePinkMapper yxStorePinkMapper) {
        this.yxStorePinkRepository = yxStorePinkRepository;
        this.combinationService = combinationService;
        this.userService = userService;
        this.yxStorePinkMapper = yxStorePinkMapper;
    }

    /**
     * 参与拼团的人
     * @param id id
     * @return
     */
    @Override
    public int countPeople(int id) {
        return yxStorePinkRepository.countByKId(id) + 1;
    }

    @Override
    public Map<String,Object> queryAll(YxStorePinkQueryCriteria criteria, Pageable pageable){
        criteria.setKId(0);
        Page<YxStorePink> page = yxStorePinkRepository
                .findAll((root, criteriaQuery, criteriaBuilder)
                        -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<YxStorePinkDTO> storePinkDTOS = yxStorePinkMapper.toDto(page.getContent());
        for (YxStorePinkDTO storePinkDTO : storePinkDTOS) {
            YxStoreCombinationDTO combinationDTO = combinationService
                    .findById(storePinkDTO.getCid());
            YxUserDTO userDTO = userService.findById(storePinkDTO.getUid());

            storePinkDTO.setAvatar(userDTO.getAvatar());
            storePinkDTO.setNickname(userDTO.getNickname());
            storePinkDTO.setTitle(combinationDTO.getTitle());
            storePinkDTO.setCountPeople(countPeople(storePinkDTO.getId()));
        }
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",storePinkDTOS);
        map.put("totalElements",page.getTotalElements());

        return map;
    }

    @Override
    public List<YxStorePinkDTO> queryAll(YxStorePinkQueryCriteria criteria){
        return yxStorePinkMapper.toDto(yxStorePinkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStorePinkDTO findById(Integer id) {
        Optional<YxStorePink> yxStorePink = yxStorePinkRepository.findById(id);
        ValidationUtil.isNull(yxStorePink,"YxStorePink","id",id);
        return yxStorePinkMapper.toDto(yxStorePink.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStorePinkDTO create(YxStorePink resources) {
        return yxStorePinkMapper.toDto(yxStorePinkRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStorePink resources) {
        Optional<YxStorePink> optionalYxStorePink = yxStorePinkRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStorePink,"YxStorePink","id",resources.getId());
        YxStorePink yxStorePink = optionalYxStorePink.get();
        yxStorePink.copy(resources);
        yxStorePinkRepository.save(yxStorePink);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStorePinkRepository.deleteById(id);
    }
}