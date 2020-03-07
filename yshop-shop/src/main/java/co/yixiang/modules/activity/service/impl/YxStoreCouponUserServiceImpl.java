package co.yixiang.modules.activity.service.impl;

import co.yixiang.modules.activity.domain.YxStoreCouponUser;
import co.yixiang.modules.activity.repository.YxStoreCouponUserRepository;
import co.yixiang.modules.activity.service.YxStoreCouponUserService;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserDTO;
import co.yixiang.modules.activity.service.dto.YxStoreCouponUserQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCouponUserMapper;
import co.yixiang.modules.shop.service.YxUserService;
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
* @date 2019-11-10
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreCouponUserServiceImpl implements YxStoreCouponUserService {

    private final YxStoreCouponUserRepository yxStoreCouponUserRepository;

    private final YxStoreCouponUserMapper yxStoreCouponUserMapper;

    private final YxUserService userService;

    public YxStoreCouponUserServiceImpl(YxStoreCouponUserRepository yxStoreCouponUserRepository, YxStoreCouponUserMapper yxStoreCouponUserMapper, YxUserService userService) {
        this.yxStoreCouponUserRepository = yxStoreCouponUserRepository;
        this.yxStoreCouponUserMapper = yxStoreCouponUserMapper;
        this.userService = userService;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreCouponUserQueryCriteria criteria, Pageable pageable){
        Page<YxStoreCouponUser> page = yxStoreCouponUserRepository.
                findAll((root, criteriaQuery, criteriaBuilder)
                        -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        //List<YxStoreCouponUserDTO> storeOrderDTOS = new ArrayList<>();
        List<YxStoreCouponUserDTO> storeOrderDTOS = yxStoreCouponUserMapper
                .toDto(page.getContent());
        for (YxStoreCouponUserDTO couponUserDTO : storeOrderDTOS) {
            couponUserDTO.setNickname(userService.findById(couponUserDTO.getUid()).getNickname());
        }
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",storeOrderDTOS);
        map.put("totalElements",page.getTotalElements());

        return map;
        //return PageUtil.toPage(page.map(yxStoreCouponUserMapper::toDto));
    }

    @Override
    public List<YxStoreCouponUserDTO> queryAll(YxStoreCouponUserQueryCriteria criteria){
        return yxStoreCouponUserMapper.toDto(yxStoreCouponUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreCouponUserDTO findById(Integer id) {
        Optional<YxStoreCouponUser> yxStoreCouponUser = yxStoreCouponUserRepository.findById(id);
        ValidationUtil.isNull(yxStoreCouponUser,"YxStoreCouponUser","id",id);
        return yxStoreCouponUserMapper.toDto(yxStoreCouponUser.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreCouponUserDTO create(YxStoreCouponUser resources) {
        return yxStoreCouponUserMapper.toDto(yxStoreCouponUserRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreCouponUser resources) {
        Optional<YxStoreCouponUser> optionalYxStoreCouponUser = yxStoreCouponUserRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreCouponUser,"YxStoreCouponUser","id",resources.getId());
        YxStoreCouponUser yxStoreCouponUser = optionalYxStoreCouponUser.get();
        yxStoreCouponUser.copy(resources);
        yxStoreCouponUserRepository.save(yxStoreCouponUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreCouponUserRepository.deleteById(id);
    }
}