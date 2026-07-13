/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.user.service.dto;

import lombok.Data;

import java.io.Serializable;


/**
* @author hupeng
* @date 2019-10-06
*/
@Data
public class YxUserSmallDto implements Serializable {

    // 用户id
    private Integer uid;

    // 用户昵称
    private String nickname;

    // 用户头像
    private String avatar;

    // 手机号码
    private String phone;


}
