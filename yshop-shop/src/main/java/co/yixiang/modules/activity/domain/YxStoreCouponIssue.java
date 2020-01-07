package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
* @author hupeng
* @date 2019-11-09
*/
@Entity
@Data
@Table(name="yx_store_coupon_issue")
public class YxStoreCouponIssue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 优惠券ID
    @Column(name = "cid")
    private Integer cid;

    @Column(name = "cname")
    private String cname;

    // 优惠券领取开启时间
    @Column(name = "start_time")
    private Integer startTime;

    @NotNull(message = "领取时间不能为空")
    private Date startTimeDate;

    @NotNull(message = "结束时间不能为空")
    private Date endTimeDate;

    // 优惠券领取结束时间
    @Column(name = "end_time")
    private Integer endTime;

    // 优惠券领取数量
    @Column(name = "total_count")
    private Integer totalCount;

    // 优惠券剩余领取数量
    @Column(name = "remain_count")
    private Integer remainCount;

    // 是否无限张数
    @Column(name = "is_permanent",nullable = false)
    private Integer isPermanent;

    // 1 正常 0 未开启 -1 已无效
    @Column(name = "status",nullable = false)
    private Integer status;

    @Column(name = "is_del",nullable = false)
    private Integer isDel;

    // 优惠券添加时间
    @Column(name = "add_time")
    private Integer addTime;

    public void copy(YxStoreCouponIssue source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}