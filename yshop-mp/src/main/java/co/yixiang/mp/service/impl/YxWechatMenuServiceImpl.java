package co.yixiang.mp.service.impl;


import co.yixiang.mp.domain.YxWechatMenu;
import co.yixiang.mp.repository.YxWechatMenuRepository;
import co.yixiang.mp.service.YxWechatMenuService;
import co.yixiang.mp.service.dto.YxWechatMenuDTO;
import co.yixiang.mp.service.dto.YxWechatMenuQueryCriteria;
import co.yixiang.mp.service.mapper.YxWechatMenuMapper;
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
* @date 2019-10-06
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxWechatMenuServiceImpl implements YxWechatMenuService {

    private final YxWechatMenuRepository YxWechatMenuRepository;

    private final YxWechatMenuMapper YxWechatMenuMapper;

    public YxWechatMenuServiceImpl(YxWechatMenuRepository YxWechatMenuRepository, YxWechatMenuMapper YxWechatMenuMapper) {
        this.YxWechatMenuRepository = YxWechatMenuRepository;
        this.YxWechatMenuMapper = YxWechatMenuMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxWechatMenuQueryCriteria criteria, Pageable pageable){
        Page<YxWechatMenu> page = YxWechatMenuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(YxWechatMenuMapper::toDto));
    }

    @Override
    public List<YxWechatMenuDTO> queryAll(YxWechatMenuQueryCriteria criteria){
        return YxWechatMenuMapper.toDto(YxWechatMenuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxWechatMenuDTO findById(String key) {
        Optional<YxWechatMenu> YxWechatMenu = YxWechatMenuRepository.findById(key);
        ValidationUtil.isNull(YxWechatMenu,"YxWechatMenu","key",key);
        return YxWechatMenuMapper.toDto(YxWechatMenu.get());
    }

    @Override
    public boolean isExist(String key) {
        Optional<YxWechatMenu> YxWechatMenu = YxWechatMenuRepository.findById(key);
        if(!YxWechatMenu.isPresent()){
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxWechatMenuDTO create(YxWechatMenu resources) {
        //resources.setKey(IdUtil.simpleUUID());
        return YxWechatMenuMapper.toDto(YxWechatMenuRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxWechatMenu resources) {
        Optional<YxWechatMenu> optionalYxWechatMenu = YxWechatMenuRepository.findById(resources.getKey());
        ValidationUtil.isNull( optionalYxWechatMenu,"YxWechatMenu","id",resources.getKey());
        YxWechatMenu YxWechatMenu = optionalYxWechatMenu.get();
        YxWechatMenu.copy(resources);
        YxWechatMenuRepository.save(YxWechatMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String key) {
        YxWechatMenuRepository.deleteById(key);
    }
}