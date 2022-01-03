/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author hupeng
 * 优惠券相关枚举
 */
@Getter
@AllArgsConstructor
public enum CouponEnum {

    TYPE_0(0, "全部"),
    TYPE_1(1, "未使用"),
    TYPE_2(2, "已使用");


    private Integer value;
    private String desc;

    public static CouponEnum toType(int value) {
        return Stream.of(CouponEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }


}
