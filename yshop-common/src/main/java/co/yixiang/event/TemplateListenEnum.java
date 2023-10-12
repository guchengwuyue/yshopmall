package co.yixiang.event;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;


/**
 * @author hupeng
 * 模板监听枚举
 */
@Getter
@AllArgsConstructor
public enum TemplateListenEnum {
    TYPE_1("1","支付成功模板通知"),
    TYPE_2("2","退款成功通知"),
    TYPE_3("3","发货成功通知"),
    TYPE_4("4","充值成功通知"),
    TYPE_5("5","在线买单/收款成功通知"),
    TYPE_6("6","商家收款通知"),
    TYPE_7("7","用户下单未支付通知"),
    TYPE_8("8","用户提现通知"),
    TYPE_9("9","退款申请通知");
    private String value;
    private String desc;

    public static TemplateListenEnum toType(String value) {
        return Stream.of(TemplateListenEnum.values())
                .filter(p -> p.value.equals(value))
                .findAny()
                .orElse(null);
    }
}
