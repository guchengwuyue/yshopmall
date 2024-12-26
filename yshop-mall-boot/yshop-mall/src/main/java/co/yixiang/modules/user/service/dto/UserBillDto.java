/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.user.service.dto;

import lombok.Data;

/**
 * @ClassName UserBillDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/11
 **/
@Data
public class UserBillDto {
    private Integer pm;
    private String gtitle;
    private String category;
    private String type;
    private Double number;
    private String nickname;
}
