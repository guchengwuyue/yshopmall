package co.yixiang.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 产品类型枚举
 */

@Getter
@AllArgsConstructor
public enum ProductTypeEnum {
    PRODUCT("product","普通商品"),

    PINK("pink","拼团"),

    SECKILL("seckill","秒杀"),

    COMBINATION("combination","拼团产品");

    private String value;
    private String desc;
}
