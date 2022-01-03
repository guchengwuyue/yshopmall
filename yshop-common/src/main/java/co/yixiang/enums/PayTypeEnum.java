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
 * 支付相关枚举
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum {

    WEIXIN("weixin", "微信支付"),
    YUE("yue", "余额支付");


    private String value;
    private String desc;

    public static PayTypeEnum toType(String value) {
        return Stream.of(PayTypeEnum.values())
                .filter(p -> p.value.equals(value))
                .findAny()
                .orElse(null);
    }


}
