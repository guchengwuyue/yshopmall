/**
* Copyright (C) 2018-2022
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package co.yixiang.modules.mp.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
* @author hupeng
* @date 2020-08-10
*/
@Data
@TableName("yx_wechat_live")
public class YxWechatLive implements Serializable {

    /** 直播间id */
    @TableId(type = IdType.INPUT)
    private Long roomId;


    /** 直播间标题 */
    @NotBlank
    private String name;


    /** 背景图 */
    @TableField(exist = false)
    private String coverImg;


    /** 分享图片 */
    @TableField(exist = false)
    private String shareImg;

    /** 背景图 */
    @NotBlank
    private String coverImge;


    /** 分享图片 */
    @NotBlank
    private String shareImge;

    /** 主播头像 */
    private String anchorImge;

    /**
     * 101：直播中，102：未开始，103已结束，104禁播，105：暂停，106：异常，107：已过期
     */
    /** 直播间状态 */
    private Integer liveStatus;


    /** 开始时间 */
    private Long startTime;


    /** 预计结束时间 */
    private Long endTime;


    /** 开始时间 */
    @NotNull
    @TableField(exist = false)
    private Date startDate;


    /** 预计结束时间 */
    @NotNull
    @TableField(exist = false)
    private Date endDate;
    /** 主播昵称 */
    @NotBlank
    private String anchorName;


    /** 主播微信号 */
    @NotBlank
    private String anchorWechat;


    /** 主播头像 */
    @TableField(exist = false)
    private String anchorImg;


    /** 直播间类型 1：推流 0：手机直播 */
    @NotNull
    private Integer type;


    /** 横屏、竖屏 【1：横屏，0：竖屏】 */
    @NotNull
    private Integer screenType;


    /** 是否关闭点赞 【0：开启，1：关闭】 */
    @NotNull
    private Integer closeLike;


    /** 是否关闭货架 【0：开启，1：关闭】 */
    @NotNull
    private Integer closeGoods;

    /** 是否关闭评论 【0：开启，1：关闭】 */
    @NotNull
    private Integer closeComment;
    @NotNull
    private Integer closeReplay  ; // 是否关闭回放 1 关闭
    @NotNull
    private Integer closeShare ;   //  是否关闭分享 1 关闭
    @NotNull
    private Integer closeKf ; // 是否关闭客服，1 关闭

    private String productId;

    /**
     * 购物直播封面图 ，建议尺寸800*800
     */
    @TableField(exist = false)
    private String feedsImg;

    public void copy(YxWechatLive source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
