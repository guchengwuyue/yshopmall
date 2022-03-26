package co.yixiang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 是没有枚举
 *
 * @author yshop
 * @date 2022-03-26 16:59:15
 */
@Getter
@AllArgsConstructor
public enum YesNoEnum {
    YES_NO_1(1,"是"),
    YES_NO_0(0,"否");

    private Integer value;
    private String desc;

    public static YesNoEnum toType(Integer value) {
        return Stream.of(YesNoEnum.values())
            .filter(p -> p.value.equals(value))
            .findAny()
            .orElse(YES_NO_0);
    }
}
