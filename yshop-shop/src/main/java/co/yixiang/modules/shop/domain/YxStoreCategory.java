package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-10-03
*/
@Entity
@Getter
@Setter
@Table(name="yx_store_category")
public class YxStoreCategory implements Serializable {

    // 商品分类表ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 父id
    @Column(name = "pid",nullable = false)
    private Integer pid;

    // 分类名称
    @Column(name = "cate_name",nullable = false)
    @NotBlank(message = "分类名称不能为空")
    private String cateName;

    // 排序
    @Column(name = "sort",nullable = false)
    private Integer sort;

    // 图标
    @Column(name = "pic",nullable = false)
    //@NotBlank(message = "请上传分类图片")
    private String pic;

    // 是否推荐
    @Column(name = "is_show",nullable = false)
    private Integer isShow;

    // 添加时间
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    public void copy(YxStoreCategory source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}