package co.yixiang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hupeng
 * 快递公司编码相关枚举
 */
@Getter
@AllArgsConstructor
public enum ShipperCodeEnum {

    SF("SF","顺丰速运");

    private String value;
    private String desc;
}
