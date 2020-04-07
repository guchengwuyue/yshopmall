package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-11-03
*/
@Entity
@Data
@Table(name="yx_store_product_reply")
public class YxStoreProductReply implements Serializable {

    // 评论ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 用户ID
//    @Column(name = "uid",nullable = false)
//    private Integer uid;

    @ManyToOne(fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name = "uid")
    private YxUser user;

    // 订单ID
    @Column(name = "oid",nullable = false)
    private Integer oid;

    // 唯一id
    @Column(name = "`unique`",nullable = false)
    private String unique;

    // 产品id
//    @Column(name = "product_id",nullable = false)
//    private Integer productId;

    @ManyToOne(fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name = "product_id")
    private YxStoreProduct storeProduct;

    // 某种商品类型(普通商品、秒杀商品）
    @Column(name = "reply_type",nullable = false)
    private String replyType;

    // 商品分数
    @Column(name = "product_score",nullable = false)
    private Integer productScore;

    // 服务分数
    @Column(name = "service_score",nullable = false)
    private Integer serviceScore;

    // 评论内容
    @Column(name = "comment",nullable = false)
    private String comment;

    // 评论图片
    @Column(name = "pics",nullable = false)
    private String pics;

    // 评论时间
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    // 管理员回复内容
    @Column(name = "merchant_reply_content")
    private String merchantReplyContent;

    // 管理员回复时间
    @Column(name = "merchant_reply_time")
    private Integer merchantReplyTime;

    // 0未删除1已删除
    @Column(name = "is_del",nullable = false)
    private Integer isDel;

    // 0未回复1已回复
    @Column(name = "is_reply",nullable = false)
    private Integer isReply;

    public void copy(YxStoreProductReply source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}