package co.yixiang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 日志类型枚举
 *
 * @author yshop
 * @date 2022-03-26 16:59:15
 */
@Getter
@AllArgsConstructor
public enum LogTypeEnum {
    INFO(1,"INFO"),
    ERROR(0,"ERROR");

    private Integer value;
    private String desc;

    public static LogTypeEnum toType(Integer value) {
        return Stream.of(LogTypeEnum.values())
            .filter(p -> p.value.equals(value))
            .findAny()
            .orElse(null);
    }
}
