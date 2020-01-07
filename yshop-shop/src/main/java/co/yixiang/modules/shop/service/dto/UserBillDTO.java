package co.yixiang.modules.shop.service.dto;

import lombok.Data;

/**
 * @ClassName UserBillDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/11
 **/
@Data
public class UserBillDTO {
    private Integer pm;
    private String gtitle;
    private String category;
    private String type;
    private Double number;
    private String nickname;
}
