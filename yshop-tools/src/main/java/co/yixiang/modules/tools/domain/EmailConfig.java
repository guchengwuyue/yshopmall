/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hupeng
 * @date 2020-05-13
 */

@Data
@TableName("email_config")
public class EmailConfig implements Serializable {

    /** ID */
    @TableId
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id")
    private Long id;


    /** 收件人 */
    // @Column(name = "from_user")
    private String fromUser;


    /** 邮件服务器SMTP地址 */
    // @Column(name = "host")
    private String host;


    /** 密码 */
    // @Column(name = "pass")
    private String pass;


    /** 端口 */
    // @Column(name = "port")
    private String port;


    /** 发件者用户名 */
    // @Column(name = "user")
    private String user;


    public void copy(EmailConfig source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
