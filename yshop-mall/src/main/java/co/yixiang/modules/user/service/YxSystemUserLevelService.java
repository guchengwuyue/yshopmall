/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.shop.domain.YxSystemUserLevel;
import co.yixiang.modules.user.service.dto.UserLevelDto;
import co.yixiang.modules.user.service.dto.YxSystemUserLevelDto;
import co.yixiang.modules.user.service.dto.YxSystemUserLevelQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @author hupeng
* @date 2020-05-12
*/
public interface YxSystemUserLevelService  extends BaseService<YxSystemUserLevel>{


    /**
     * 获取当前的下一个会员等级id
     * @param levelId 等级id
     * @return int
     */
    int getNextLevelId(int levelId);

    //boolean getClear(int levelId);


    /**
     * 获取会员等级列表及其任务列表
     * @return UserLevelDto
     */
    UserLevelDto getLevelInfo(Long uid);

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxSystemUserLevelQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxSystemUserLevelDto>
    */
    List<YxSystemUserLevel> queryAll(YxSystemUserLevelQueryCriteria criteria);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxSystemUserLevelDto> all, HttpServletResponse response) throws IOException;
}
