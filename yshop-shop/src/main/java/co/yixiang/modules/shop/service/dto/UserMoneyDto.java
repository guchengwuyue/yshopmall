/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @ClassName UserMoneyDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/4
 **/
@Data
public class UserMoneyDto implements Serializable {
    //@NotNull(message = "参数缺失")
    private Long uid;
    //@NotNull(message = "请选择修改余额方式")
    private Integer ptype;
    //@NotNull(message = "金额必填")
    @Min(message = "最低金额为0", value = 0)
    private Double money;
}
