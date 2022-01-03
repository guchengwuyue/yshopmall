/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import co.yixiang.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hupeng
 * @date 2020-05-12
 */

@Data
@TableName("yx_store_product_reply")
public class YxStoreProductReply extends BaseDomain {

    /** 评论ID */
    @TableId
    private Long id;


    /** 用户ID */
    private Long uid;


    /** 订单ID */
    private Long oid;


    /** 唯一id */
    @TableField(value = "`unique`")
    private String unique;


    /** 产品id */
    private Long productId;


    /** 某种商品类型(普通商品、秒杀商品） */
    private String replyType;


    /** 商品分数 */
    private Integer productScore;


    /** 服务分数 */
    private Integer serviceScore;


    /** 评论内容 */
    private String comment;


    /** 评论图片 */
    private String pics;


    /** 管理员回复内容 */
    private String merchantReplyContent;


    /** 管理员回复时间 */
    private Date merchantReplyTime;


    /** 0未回复1已回复 */
    private Integer isReply;

    @TableField(exist = false)
    private YxStoreProduct storeProduct;

    @TableField(exist = false)
    private YxUser user;

    public void copy(YxStoreProductReply source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
