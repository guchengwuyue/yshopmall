/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.mp.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Data
public class YxWechatReplyDto implements Serializable {

    /** 微信关键字回复id */
    private Integer id;

    /** 关键字 */
    private String key;

    /** 回复类型 */
    private String type;

    /** 回复数据 */
    private String data;

    /** 0=不可用  1 =可用 */
    private Integer status;

    /** 是否隐藏 */
    private Integer hide;
}
