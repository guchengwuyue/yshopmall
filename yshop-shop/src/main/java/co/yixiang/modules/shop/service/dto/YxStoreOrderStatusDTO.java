package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;


/**
* @author hupeng
* @date 2019-11-02
*/
@Data
public class YxStoreOrderStatusDTO implements Serializable {

    private Integer id;

    // 订单id
    private Integer oid;

    // 操作类型
    private String changeType;

    // 操作备注
    private String changeMessage;

    // 操作时间
    private Integer changeTime;
}