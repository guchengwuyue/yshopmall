package co.yixiang.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName TemplateBean
 * @Author hupeng <610796224@qq.com>
 * @Date 2020/6/6
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateBean {
    private String templateType;
    private String orderId;
    private String time;
    private String price;
    private String deliveryName;
    private String deliveryId;
    private String payType;
    private Long uid;
    /**
     * 提现申请ID
     */
    private Long extractId;

    /**
     * 微信发货sf的时候必传收件人联系方式
     */
    private String phone;
    /**
     * 必填 商品信息，例如：微信红包抱枕*1个，限120个字以内
     */
    private String productName;
}
