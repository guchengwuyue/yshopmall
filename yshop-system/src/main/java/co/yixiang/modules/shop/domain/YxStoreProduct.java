package co.yixiang.modules.shop.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-10-04
*/
@Entity
@Data
@Table(name="yx_store_product")
public class YxStoreProduct implements Serializable {

    // 商品id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 商户Id(0为总后台管理员创建,不为0的时候是商户后台创建)
    @Column(name = "mer_id",nullable = false)
    private Integer merId;

    // 商品图片
    @Column(name = "image",nullable = false)
    private String image;

    // 轮播图
    @Column(name = "slider_image",nullable = false)
    private String sliderImage;

    // 商品名称
    @Column(name = "store_name",nullable = false)
    private String storeName;

    // 商品简介
    @Column(name = "store_info",nullable = false)
    private String storeInfo;

    // 关键字
    @Column(name = "keyword",nullable = false)
    private String keyword;

    // 产品条码（一维码）
    @Column(name = "bar_code",nullable = false)
    private String barCode;

    // 分类id
    @Column(name = "cate_id",nullable = false)
    private String cateId;

    // 商品价格
    @Column(name = "price",nullable = false)
    private BigDecimal price;

    // 会员价格
    @Column(name = "vip_price",nullable = false)
    private BigDecimal vipPrice;

    // 市场价
    @Column(name = "ot_price",nullable = false)
    private BigDecimal otPrice;

    // 邮费
    @Column(name = "postage",nullable = false)
    private BigDecimal postage;

    // 单位名
    @Column(name = "unit_name",nullable = false)
    private String unitName;

    // 排序
    @Column(name = "sort",nullable = false)
    private Integer sort;

    // 销量
    @Column(name = "sales",nullable = false)
    private Integer sales;

    // 库存
    @Column(name = "stock",nullable = false)
    private Integer stock;

    // 状态（0：未上架，1：上架）
    @Column(name = "is_show",nullable = false)
    private Integer isShow;

    // 是否热卖
    @Column(name = "is_hot",nullable = false)
    private Integer isHot;

    // 是否优惠
    @Column(name = "is_benefit",nullable = false)
    private Integer isBenefit;

    // 是否精品
    @Column(name = "is_best",nullable = false)
    private Integer isBest;

    // 是否新品
    @Column(name = "is_new",nullable = false)
    private Integer isNew;

    // 产品描述
    @Column(name = "description",nullable = false)
    private String description;

    // 添加时间
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    // 是否包邮
    @Column(name = "is_postage",nullable = false)
    private Integer isPostage;

    // 是否删除
    @Column(name = "is_del",nullable = false)
    private Integer isDel;

    // 商户是否代理 0不可代理1可代理
    @Column(name = "mer_use",nullable = false)
    private Integer merUse;

    // 获得积分
    @Column(name = "give_integral",nullable = false)
    private BigDecimal giveIntegral;

    // 成本价
    @Column(name = "cost",nullable = false)
    private BigDecimal cost;

    // 秒杀状态 0 未开启 1已开启
    @Column(name = "is_seckill",nullable = false)
    private Integer isSeckill;

    // 砍价状态 0未开启 1开启
    @Column(name = "is_bargain")
    private Integer isBargain;

    // 是否优品推荐
    @Column(name = "is_good",nullable = false)
    private Integer isGood;

    // 虚拟销量
    @Column(name = "ficti")
    private Integer ficti;

    // 浏览量
    @Column(name = "browse")
    private Integer browse;

    // 产品二维码地址(用户小程序海报)
    @Column(name = "code_path",nullable = false)
    private String codePath;

    // 淘宝京东1688类型
    @Column(name = "soure_link")
    private String soureLink;

    public void copy(YxStoreProduct source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}