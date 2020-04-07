package co.yixiang.modules.shop.service.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ExpressParam
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/9
 **/
@Data
public class ExpressParam implements Serializable {
    //@NotBlank()
    private String orderCode;
    private String shipperCode;
    private String logisticCode;
}
