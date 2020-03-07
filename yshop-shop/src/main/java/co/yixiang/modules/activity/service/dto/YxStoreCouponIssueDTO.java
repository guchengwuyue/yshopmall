package co.yixiang.modules.activity.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
* @author hupeng
* @date 2019-11-09
*/
@Data
public class YxStoreCouponIssueDTO implements Serializable {

    private Integer id;

    // 优惠券ID
    private Integer cid;

    private String cname;

    // 优惠券领取开启时间
    private Integer startTime;

    // 优惠券领取结束时间
    private Integer endTime;

    private Date startTimeDate;

    private Date endTimeDate;

    // 优惠券领取数量
    private Integer totalCount;

    // 优惠券剩余领取数量
    private Integer remainCount;

    // 是否无限张数
    private Integer isPermanent;

    // 1 正常 0 未开启 -1 已无效
    private Integer status;

    private Integer isDel;

    // 优惠券添加时间
    private Integer addTime;
}