package co.yixiang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 优惠券类型枚举
 */
@Getter
@AllArgsConstructor
public enum CartTypeEnum {
    NEW_0(0,"加入购物车"),
    NEW_1(1,"加入购物车直接购买");

    private Integer value;
    private String desc;
}
