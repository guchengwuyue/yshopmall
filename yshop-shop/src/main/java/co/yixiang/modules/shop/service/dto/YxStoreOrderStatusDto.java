/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hupeng
 * @date 2020-05-12
 */
@Data
public class YxStoreOrderStatusDto implements Serializable {

    private Integer id;

    /** 订单id */
    private Integer oid;

    /** 操作类型 */
    private String changeType;

    /** 操作备注 */
    private String changeMessage;

    /** 操作时间 */
    private Integer changeTime;
}
