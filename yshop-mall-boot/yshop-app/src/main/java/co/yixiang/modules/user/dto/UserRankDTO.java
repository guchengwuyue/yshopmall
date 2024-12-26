package co.yixiang.modules.user.dto;

import lombok.Data;

/**
 * @ClassName UserRankDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/13
 **/
@Data
public class UserRankDTO {
    private Integer uid;
    private Integer count;
    private String nickname;
    private String avatar;
}
