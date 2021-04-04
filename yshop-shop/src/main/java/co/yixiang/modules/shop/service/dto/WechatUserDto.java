package co.yixiang.modules.shop.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName WechatUserDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/4
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WechatUserDto {

    private String openid;

    private String unionId;

    private String routineOpenid;

    private String nickname;

    private String headimgurl;

    private Integer sex;

    private String city;

    private String language;

    private String province;

    private String country;

    private Boolean subscribe;

    private Long subscribeTime;

}