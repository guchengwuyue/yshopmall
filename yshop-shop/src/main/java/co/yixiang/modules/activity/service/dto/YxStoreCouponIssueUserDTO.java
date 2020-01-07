package co.yixiang.modules.activity.service.dto;

import lombok.Data;

import java.io.Serializable;


/**
* @author hupeng
* @date 2019-11-09
*/
@Data
public class YxStoreCouponIssueUserDTO implements Serializable {

    private Integer id;

    // 领取优惠券用户ID
    private Integer uid;

    // 优惠券前台领取ID
    private Integer issueCouponId;

    // 领取时间
    private Integer addTime;
}