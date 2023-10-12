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
import java.io.Serializable;

/**
* @author hupeng
* @date 2020-08-11
*/
@Data
@TableName("yx_wechat_live_goods")
public class YxWechatLiveGoods implements Serializable {

    /** 直播商品id */
    @TableId(type = IdType.INPUT)
    private Long goodsId;


    /** 关联商品id */
    private Long productId;


    /** 商品图片 */
    @TableField(exist = false)
    private String coverImgUrl;


    /** 商品图片 */
    @NotBlank
    private String coverImgeUrl;

    /** 商品小程序路径 */
    @NotBlank
    private String url;


    /** 价格类型 1：一口价（只需要传入price，price2不传） 2：价格区间（price字段为左边界，price2字段为右边界，price和price2必传） 3：显示折扣价（price字段为原价，price2字段为现价， price和price2必传） */
    @NotBlank
    private String priceType;


    @NotBlank
    private String price;


    private String price2;


    /** 商品名称 */
    @NotBlank
    private String name;


    /** 1, 2：表示是为api添加商品，否则是直播控制台添加的商品 */
    private String thirdPartyTag;


    /** 审核单id */
    private Long auditId;


    /** 审核状态 0：未审核，1：审核中，2:审核通过，3审核失败 */
    private Integer auditStatus;


    public void copy(YxWechatLiveGoods source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
