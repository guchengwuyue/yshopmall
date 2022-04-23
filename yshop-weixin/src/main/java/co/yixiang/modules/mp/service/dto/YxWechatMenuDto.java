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
public class YxWechatMenuDto implements Serializable {

    private String key;

    /** 缓存数据 */
    private String result;

    /** 缓存时间 */
    private Integer addTime;
}
