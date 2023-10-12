/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

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

	FALI_0(0,"有效"),
	FALI_1(1,"无效"),

	USE_0(0,"不可用"),
	USE_1(1,"可用"),

	STATUS_0(0,"未使用"),
	STATUS_1(1,"已使用"),
	STATUS_2(2,"已过期"),

	TYPE_0(0,"通用券"),
	TYPE_1(1,"商品券"),
	TYPE_2(2,"内部券"),

	PERMANENT_0(0,"限量"),
	PERMANENT_1(1,"不限量");




	private Integer value;
	private String desc;

	public static CouponEnum toType(int value) {
		return Stream.of(CouponEnum.values())
				.filter(p -> p.value == value)
				.findAny()
				.orElse(null);
	}


}
