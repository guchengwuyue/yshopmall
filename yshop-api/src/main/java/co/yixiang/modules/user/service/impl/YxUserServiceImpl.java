package co.yixiang.modules.user.service.impl;

import co.yixiang.modules.user.entity.YxUser;
import co.yixiang.modules.user.mapper.YxUserMapper;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.web.param.YxUserQueryParam;
import co.yixiang.modules.user.web.vo.YxUserQueryVo;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.web.vo.Paging;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-10-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class YxUserServiceImpl extends BaseServiceImpl<YxUserMapper, YxUser> implements YxUserService {

    @Autowired
    private YxUserMapper yxUserMapper;

    @Override
    public YxUserQueryVo getYxUserById(Serializable id) throws Exception{
        return yxUserMapper.getYxUserById(id);
    }

    @Override
    public Paging<YxUserQueryVo> getYxUserPageList(YxUserQueryParam yxUserQueryParam) throws Exception{
        Page page = setPageParam(yxUserQueryParam,OrderItem.desc("create_time"));
        IPage<YxUserQueryVo> iPage = yxUserMapper.getYxUserPageList(page,yxUserQueryParam);
        return new Paging(iPage);
    }

    @Override
    public YxUser findByName(String name) {
        QueryWrapper<YxUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",name);
        return getOne(wrapper);
    }
}
