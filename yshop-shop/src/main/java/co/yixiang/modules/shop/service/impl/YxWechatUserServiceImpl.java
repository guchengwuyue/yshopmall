package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxWechatUser;
import co.yixiang.modules.shop.repository.YxWechatUserRepository;
import co.yixiang.modules.shop.service.YxWechatUserService;
import co.yixiang.modules.shop.service.dto.YxWechatUserDTO;
import co.yixiang.modules.shop.service.dto.YxWechatUserQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxWechatUserMapper;
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
* @date 2019-12-13
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxWechatUserServiceImpl implements YxWechatUserService {

    private final YxWechatUserRepository yxWechatUserRepository;

    private final YxWechatUserMapper yxWechatUserMapper;

    public YxWechatUserServiceImpl(YxWechatUserRepository yxWechatUserRepository, YxWechatUserMapper yxWechatUserMapper) {
        this.yxWechatUserRepository = yxWechatUserRepository;
        this.yxWechatUserMapper = yxWechatUserMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxWechatUserQueryCriteria criteria, Pageable pageable){
        Page<YxWechatUser> page = yxWechatUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxWechatUserMapper::toDto));
    }

    @Override
    public List<YxWechatUserDTO> queryAll(YxWechatUserQueryCriteria criteria){
        return yxWechatUserMapper.toDto(yxWechatUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxWechatUserDTO findById(Integer uid) {
        Optional<YxWechatUser> yxWechatUser = yxWechatUserRepository.findById(uid);
        ValidationUtil.isNull(yxWechatUser,"YxWechatUser","uid",uid);
        return yxWechatUserMapper.toDto(yxWechatUser.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxWechatUserDTO create(YxWechatUser resources) {
        return yxWechatUserMapper.toDto(yxWechatUserRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxWechatUser resources) {
        Optional<YxWechatUser> optionalYxWechatUser = yxWechatUserRepository.findById(resources.getUid());
        ValidationUtil.isNull( optionalYxWechatUser,"YxWechatUser","id",resources.getUid());
        YxWechatUser yxWechatUser = optionalYxWechatUser.get();
        yxWechatUser.copy(resources);
        yxWechatUserRepository.save(yxWechatUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer uid) {
        yxWechatUserRepository.deleteById(uid);
    }
}