/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.config;

/**
 * @author ：LionCity
 * @date ：Created in 2020-04-10 15:47
 * @description：自动注入时间处理
 * @modified By：
 * @version:
 */

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 */
@Slf4j
@Component
public class MetaHandler implements MetaObjectHandler {


    /**
     * 新增数据执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            Timestamp time = new Timestamp(System.currentTimeMillis());
            if (metaObject.hasSetter("createTime")) {
                log.debug("自动插入 createTime");
                this.setFieldValByName("createTime", time, metaObject);
            }
            if (metaObject.hasSetter("updateTime")) {
                log.debug("自动插入 updateTime");
                this.setFieldValByName("updateTime", time, metaObject);
            }
            if (metaObject.hasSetter("createDate")) {
                log.debug("自动插入 createDate");
                this.setFieldValByName("createDate", time, metaObject);
            }
            if (metaObject.hasSetter("updateDate")) {
                log.debug("自动插入 updateDate");
                this.setFieldValByName("updateDate", time, metaObject);
            }
            if (metaObject.hasSetter("delFlag")) {
                log.debug("自动插入 delFlag");
                this.setFieldValByName("delFlag", false, metaObject);
            }
        } catch (Exception e) {
            log.error("自动注入失败:{}", e);
        }
    }

    /**
     * 更新数据执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            Timestamp time = new Timestamp(System.currentTimeMillis());
            if (metaObject.hasSetter("updateTime")) {
                log.debug("自动插入 updateTime");
                this.setFieldValByName("updateTime", time, metaObject);
            }
            if (metaObject.hasSetter("updateDate")) {
                log.debug("自动插入 updateDate");
                this.setFieldValByName("updateDate", time, metaObject);
            }
            if (metaObject.hasSetter("delFlag")) {
                log.debug("自动插入 delFlag");
                this.setFieldValByName("delFlag", null, metaObject);
            }
            if (metaObject.hasSetter("createTime")) {
                log.debug("自动插入 createTime");
                this.setFieldValByName("createTime", null, metaObject);
            }
        } catch (Exception e) {
            log.error("自动注入失败:{}", e);
        }
    }

}
