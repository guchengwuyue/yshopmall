package co.yixiang.modules.shop.service.impl;

import co.yixiang.exception.EntityExistException;
import co.yixiang.modules.shop.domain.YxExpress;
import co.yixiang.modules.shop.repository.YxExpressRepository;
import co.yixiang.modules.shop.service.YxExpressService;
import co.yixiang.modules.shop.service.dto.YxExpressDTO;
import co.yixiang.modules.shop.service.dto.YxExpressQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxExpressMapper;
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
* @date 2019-12-12
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxExpressServiceImpl implements YxExpressService {

    private final YxExpressRepository yxExpressRepository;

    private final YxExpressMapper yxExpressMapper;

    public YxExpressServiceImpl(YxExpressRepository yxExpressRepository, YxExpressMapper yxExpressMapper) {
        this.yxExpressRepository = yxExpressRepository;
        this.yxExpressMapper = yxExpressMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxExpressQueryCriteria criteria, Pageable pageable){
        Page<YxExpress> page = yxExpressRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxExpressMapper::toDto));
    }

    @Override
    public List<YxExpressDTO> queryAll(YxExpressQueryCriteria criteria){
        return yxExpressMapper.toDto(yxExpressRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxExpressDTO findById(Integer id) {
        Optional<YxExpress> yxExpress = yxExpressRepository.findById(id);
        ValidationUtil.isNull(yxExpress,"YxExpress","id",id);
        return yxExpressMapper.toDto(yxExpress.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxExpressDTO create(YxExpress resources) {
        if(yxExpressRepository.findByCode(resources.getCode()) != null){
            throw new EntityExistException(YxExpress.class,"code",resources.getCode());
        }
        return yxExpressMapper.toDto(yxExpressRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxExpress resources) {
        Optional<YxExpress> optionalYxExpress = yxExpressRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxExpress,"YxExpress","id",resources.getId());
        YxExpress yxExpress = optionalYxExpress.get();
        YxExpress yxExpress1 = null;
        yxExpress1 = yxExpressRepository.findByCode(resources.getCode());
        if(yxExpress1 != null && !yxExpress1.getId().equals(yxExpress.getId())){
            throw new EntityExistException(YxExpress.class,"code",resources.getCode());
        }
        yxExpress.copy(resources);
        yxExpressRepository.save(yxExpress);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxExpressRepository.deleteById(id);
    }
}