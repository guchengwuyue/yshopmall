package co.yixiang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 产品相关枚举
 */
@Getter
@AllArgsConstructor
public enum ProductEnum {

	TYPE_1(1,"精品推荐"),
	TYPE_2(2,"热门榜单"),
	TYPE_3(3,"首发新品"),
	TYPE_4(4,"促销单品");


	private Integer value;
	private String desc;


}
