/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.service.impl;


import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.user.domain.YxUserTaskFinish;
import co.yixiang.modules.user.service.YxUserTaskFinishService;
import co.yixiang.modules.user.service.mapper.YxUserTaskFinishMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 用户任务完成记录表 服务实现类
 * </p>
 *
 * @author hupeng
 * @since 2019-12-07
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class YxUserTaskFinishServiceImpl extends BaseServiceImpl<YxUserTaskFinishMapper, YxUserTaskFinish> implements YxUserTaskFinishService {

    private final YxUserTaskFinishMapper yxUserTaskFinishMapper;


    /**
     * 设置任务完成
     * @param uid uid
     * @param taskId 任务id
     */
    @Override
    public void setFinish(Long uid, int taskId) {
        Long count = this.lambdaQuery()
                .eq(YxUserTaskFinish::getUid,uid)
                .eq(YxUserTaskFinish::getTaskId,taskId)
                .count();
        if(count == 0){
            YxUserTaskFinish userTaskFinish = new YxUserTaskFinish();
            userTaskFinish.setUid(uid);
            userTaskFinish.setTaskId(taskId);
            yxUserTaskFinishMapper.insert(userTaskFinish);
        }
    }



}
