/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.logging.domain;

import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author hupeng
 * @date 2018-11-24
 */
@Data
@TableName("log")
@NoArgsConstructor
public class Log extends BaseDomain {

    @TableId
    private Long id;

    /** 操作用户 */
    private String username;

    @TableField(exist = false)
    private String nickname;

    /** 描述 */
    private String description;

    /** 方法名 */
    private String method;

    private Long uid;

    private Integer type;

    /** 参数 */
    private String params;

    /** 日志类型 */
    private String logType;

    /** 请求ip */
    private String requestIp;

    /** 地址 */
    private String address;

    /** 浏览器  */
    private String browser;

    /** 请求耗时 */
    private Long time;

    /** 异常详细  */
    private byte[] exceptionDetail;

    public Log(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }
}
