/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.user.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* @author hupeng
* @date 2020-05-12
*/
@Data
public class YxUserBillDto implements Serializable {

    /** 用户账单id */
    private Long id;

    /** 用户uid */
    private Long uid;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /** 0 = 带确定 1 = 有效 -1 = 无效 */
    private Integer status;

    private String nickname;
}
