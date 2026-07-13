package co.yixiang.modules.user.service.dto;

import lombok.Data;

/**
 * @ClassName BillVo
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/11/12
 **/
@Data
public class BillOrderRecordDto {
    private String orderId;
    private String time;
    private Double number;
    private String avatar;
    private String nickname;
}
