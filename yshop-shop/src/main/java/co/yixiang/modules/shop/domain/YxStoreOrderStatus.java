package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-11-02
*/
@Entity
@Data
@Table(name="yx_store_order_status")
public class YxStoreOrderStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 订单id
    @Column(name = "oid",nullable = false)
    private Integer oid;

    // 操作类型
    @Column(name = "change_type",nullable = false)
    private String changeType;

    // 操作备注
    @Column(name = "change_message",nullable = false)
    private String changeMessage;

    // 操作时间
    @Column(name = "change_time",nullable = false)
    private Integer changeTime;

    public void copy(YxStoreOrderStatus source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}