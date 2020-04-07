package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;


/**
* @author hupeng
* @date 2019-11-03
*/
@Data
public class YxStoreProductReplyDTO implements Serializable {

    // 评论ID
    private Integer id;

    // 用户ID
    private Integer uid;

    private YxUserSmallDTO user;

    // 订单ID
    private Integer oid;

    // 唯一id
    private String unique;

    // 产品id
    private Integer productId;

    private YxStoreProductSmallDTO storeProduct;


    // 某种商品类型(普通商品、秒杀商品）
    private String replyType;

    // 商品分数
    private Integer productScore;

    // 服务分数
    private Integer serviceScore;

    // 评论内容
    private String comment;

    // 评论图片
    private String pics;

    // 评论时间
    private Integer addTime;

    // 管理员回复内容
    private String merchantReplyContent;

    // 管理员回复时间
    private Integer merchantReplyTime;

    // 0未删除1已删除
    private Integer isDel;

    // 0未回复1已回复
    private Integer isReply;

}