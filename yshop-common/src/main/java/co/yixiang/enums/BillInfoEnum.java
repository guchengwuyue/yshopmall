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
 * 账单相关枚举
 */
@Getter
@AllArgsConstructor
public enum BillInfoEnum {

    DEAFUL_ALL(0, "所有"),
    PAY_PRODUCT(1, "消费"),
    RECHAREGE(2, "充值"),
    BROKERAGE(3, "返佣"),
    EXTRACT(4, "提现"),
    SIGN_INTEGRAL(5, "签到积分"),
    PAY_PRODUCT_REFUND(6, "退款"),
    SYSTEM_ADD(7, "系统添加"),
    SYSTEM_SUB(8, "系统减少");


    private Integer value;
    private String desc;

    public static BillInfoEnum toType(int value) {
        return Stream.of(BillInfoEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }


}
