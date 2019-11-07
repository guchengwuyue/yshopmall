package co.yixiang.modules.user.service;

import co.yixiang.modules.user.entity.YxUser;
import co.yixiang.common.service.BaseService;
import co.yixiang.modules.user.web.param.YxUserQueryParam;
import co.yixiang.modules.user.web.vo.YxUserQueryVo;
import co.yixiang.common.web.vo.Paging;

import java.io.Serializable;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hupeng
 * @since 2019-10-16
 */
public interface YxUserService extends BaseService<YxUser> {

    YxUser findByName(String name);

    /**
     * 根据ID获取查询对象
     * @param id
     * @return
     */
    YxUserQueryVo getYxUserById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     * @param yxUserQueryParam
     * @return
     */
    Paging<YxUserQueryVo> getYxUserPageList(YxUserQueryParam yxUserQueryParam) throws Exception;

}
