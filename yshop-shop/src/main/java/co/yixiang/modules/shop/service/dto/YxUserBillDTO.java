/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
public class YxUserBillDto implements Serializable {

    /** 用户账单id */
    private Integer id;

    /** 用户uid */
    private Integer uid;

    /** 关联id */
    private String linkId;

    /** 0 = 支出 1 = 获得 */
    private Integer pm;

    /** 账单标题 */
    private String title;

    /** 明细种类 */
    private String category;

    /** 明细类型 */
    private String type;

    /** 明细数字 */
    private BigDecimal number;

    /** 剩余 */
    private BigDecimal balance;

    /** 备注 */
    private String mark;

    /** 添加时间 */
    private Integer addTime;

    /** 0 = 带确定 1 = 有效 -1 = 无效 */
    private Integer status;

    private String nickname;
}
