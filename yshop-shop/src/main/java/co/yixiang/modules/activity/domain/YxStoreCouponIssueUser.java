package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-11-09
*/
@Entity
@Data
@Table(name="yx_store_coupon_issue_user")
public class YxStoreCouponIssueUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 领取优惠券用户ID
    @Column(name = "uid")
    private Integer uid;

    // 优惠券前台领取ID
    @Column(name = "issue_coupon_id")
    private Integer issueCouponId;

    // 领取时间
    @Column(name = "add_time")
    private Integer addTime;

    public void copy(YxStoreCouponIssueUser source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}