package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.service.mapper.YxUserMapper;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.utils.ValidationUtil;
import co.yixiang.modules.shop.repository.YxUserRepository;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.YxUserDTO;
import co.yixiang.modules.shop.service.dto.YxUserQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2019-10-06
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserServiceImpl implements YxUserService {

    @Autowired
    private YxUserRepository yxUserRepository;

    @Autowired
    private YxUserMapper yxUserMapper;

    @Override
    public Map<String,Object> queryAll(YxUserQueryCriteria criteria, Pageable pageable){
        Page<YxUser> page = yxUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxUserMapper::toDto));
    }

    @Override
    public List<YxUserDTO> queryAll(YxUserQueryCriteria criteria){
        return yxUserMapper.toDto(yxUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxUserDTO findById(Integer uid) {
        Optional<YxUser> yxUser = yxUserRepository.findById(uid);
        ValidationUtil.isNull(yxUser,"YxUser","uid",uid);
        return yxUserMapper.toDto(yxUser.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxUserDTO create(YxUser resources) {
        return yxUserMapper.toDto(yxUserRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxUser resources) {
        Optional<YxUser> optionalYxUser = yxUserRepository.findById(resources.getUid());
        ValidationUtil.isNull( optionalYxUser,"YxUser","id",resources.getUid());
        YxUser yxUser = optionalYxUser.get();
        yxUser.copy(resources);
        yxUserRepository.save(yxUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer uid) {
        yxUserRepository.deleteById(uid);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onStatus(Integer uid, Integer status) {
        if(status == 1){
            status = 0;
        }else{
            status = 1;
        }

        yxUserRepository.updateOnstatus(status,uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void incBrokeragePrice(double price, int uid) {
        yxUserRepository.incBrokeragePrice(price,uid);
    }
}