package co.yixiang.modules.wechat.service.impl;

import co.yixiang.modules.wechat.repository.YxWechatReplyRepository;
import co.yixiang.modules.wechat.service.YxWechatReplyService;
import co.yixiang.modules.wechat.service.dto.YxWechatReplyDTO;
import co.yixiang.modules.wechat.service.dto.YxWechatReplyQueryCriteria;
import co.yixiang.modules.wechat.service.mapper.YxWechatReplyMapper;
import co.yixiang.modules.wechat.domain.YxWechatReply;
import co.yixiang.exception.EntityExistException;
import co.yixiang.utils.ValidationUtil;
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
* @date 2019-10-10
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxWechatReplyServiceImpl implements YxWechatReplyService {

    @Autowired
    private YxWechatReplyRepository yxWechatReplyRepository;

    @Autowired
    private YxWechatReplyMapper yxWechatReplyMapper;

    @Override
    public Map<String,Object> queryAll(YxWechatReplyQueryCriteria criteria, Pageable pageable){
        Page<YxWechatReply> page = yxWechatReplyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxWechatReplyMapper::toDto));
    }

    @Override
    public List<YxWechatReplyDTO> queryAll(YxWechatReplyQueryCriteria criteria){
        return yxWechatReplyMapper.toDto(yxWechatReplyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxWechatReplyDTO findById(Integer id) {
        Optional<YxWechatReply> yxWechatReply = yxWechatReplyRepository.findById(id);
        ValidationUtil.isNull(yxWechatReply,"YxWechatReply","id",id);
        return yxWechatReplyMapper.toDto(yxWechatReply.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxWechatReplyDTO create(YxWechatReply resources) {
        if(yxWechatReplyRepository.findByKey(resources.getKey()) != null){
            throw new EntityExistException(YxWechatReply.class,"key",resources.getKey());
        }
        return yxWechatReplyMapper.toDto(yxWechatReplyRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxWechatReply resources) {
        Optional<YxWechatReply> optionalYxWechatReply = yxWechatReplyRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxWechatReply,"YxWechatReply","id",resources.getId());
        YxWechatReply yxWechatReply = optionalYxWechatReply.get();
        YxWechatReply yxWechatReply1 = null;
        yxWechatReply1 = yxWechatReplyRepository.findByKey(resources.getKey());
        if(yxWechatReply1 != null && !yxWechatReply1.getId().equals(yxWechatReply.getId())){
            throw new EntityExistException(YxWechatReply.class,"key",resources.getKey());
        }
        yxWechatReply.copy(resources);
        yxWechatReplyRepository.save(yxWechatReply);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxWechatReplyRepository.deleteById(id);
    }


    @Override
    public YxWechatReply isExist(String key) {
        YxWechatReply yxWechatReply = yxWechatReplyRepository.findByKey(key);

        return yxWechatReply;
    }
}