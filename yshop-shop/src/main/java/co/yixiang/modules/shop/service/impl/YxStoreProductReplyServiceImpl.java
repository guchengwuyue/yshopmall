package co.yixiang.modules.shop.service.impl;

import co.yixiang.modules.shop.domain.YxStoreProductReply;
import co.yixiang.modules.shop.repository.YxStoreProductReplyRepository;
import co.yixiang.modules.shop.service.YxStoreProductReplyService;
import co.yixiang.modules.shop.service.YxStoreProductService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyDTO;
import co.yixiang.modules.shop.service.dto.YxStoreProductReplyQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxStoreProductReplyMapper;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author hupeng
* @date 2019-11-03
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreProductReplyServiceImpl implements YxStoreProductReplyService {

    private final YxStoreProductReplyRepository yxStoreProductReplyRepository;

    private final YxStoreProductReplyMapper yxStoreProductReplyMapper;



    public YxStoreProductReplyServiceImpl(YxStoreProductReplyRepository yxStoreProductReplyRepository,
                                          YxStoreProductReplyMapper yxStoreProductReplyMapper) {
        this.yxStoreProductReplyRepository = yxStoreProductReplyRepository;
        this.yxStoreProductReplyMapper = yxStoreProductReplyMapper;

    }

    @Override
    public Map<String,Object> queryAll(YxStoreProductReplyQueryCriteria criteria, Pageable pageable){
        Page<YxStoreProductReply> page = yxStoreProductReplyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxStoreProductReplyMapper::toDto));
    }

    @Override
    public List<YxStoreProductReplyDTO> queryAll(YxStoreProductReplyQueryCriteria criteria){
        return yxStoreProductReplyMapper.toDto(yxStoreProductReplyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreProductReplyDTO findById(Integer id) {
        Optional<YxStoreProductReply> yxStoreProductReply = yxStoreProductReplyRepository.findById(id);
        ValidationUtil.isNull(yxStoreProductReply,"YxStoreProductReply","id",id);
        return yxStoreProductReplyMapper.toDto(yxStoreProductReply.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreProductReplyDTO create(YxStoreProductReply resources) {
        return yxStoreProductReplyMapper.toDto(yxStoreProductReplyRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreProductReply resources) {
        Optional<YxStoreProductReply> optionalYxStoreProductReply = yxStoreProductReplyRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreProductReply,"YxStoreProductReply","id",resources.getId());
        YxStoreProductReply yxStoreProductReply = optionalYxStoreProductReply.get();
        yxStoreProductReply.copy(resources);
        yxStoreProductReplyRepository.save(yxStoreProductReply);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreProductReplyRepository.deleteById(id);
    }
}