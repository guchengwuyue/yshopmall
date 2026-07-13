package co.yixiang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 优惠券获取类型枚举
 */
@Getter
@AllArgsConstructor
public enum CouponGetEnum {
    GET("get","领取"),
    SEND("send","派送");

    private String value;
    private String desc;
}
