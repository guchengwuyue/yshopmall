package co.yixiang.modules.shop.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
* @author hupeng
* @date 2020-03-03
*/
@Entity
@Data
@Table(name="yx_system_store")
public class YxSystemStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 门店名称 */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /** 简介 */
    @Column(name = "introduction",nullable = false)
    @NotBlank
    private String introduction;

    /** 手机号码 */
    @Column(name = "phone",nullable = false)
    @NotBlank
    private String phone;

    /** 省市区 */
    @Column(name = "address",nullable = false)
    @NotBlank
    private String address;

    /** 详细地址 */
    @Column(name = "detailed_address",insertable = false)
    private String detailedAddress;

    /** 门店logo */
    @Column(name = "image",nullable = false)
    @NotBlank(message = "请上传门店logo")
    private String image;

    /** 纬度 */
    @Column(name = "latitude",nullable = false)
    @NotBlank
    private String latitude;

    /** 经度 */
    @Column(name = "longitude",nullable = false)
    @NotBlank
    private String longitude;

    /** 核销有效日期 */
    @Column(name = "valid_time",nullable = false)
    @NotBlank
    private String validTime;

    @Column(name = "valid_time_start",nullable = false)
    private Date validTimeStart;

    @Column(name = "valid_time_end",nullable = false)
    private Date validTimeEnd;

    /** 每日营业开关时间 */
    @Column(name = "day_time",nullable = false)
    @NotBlank
    private String dayTime;

    @Column(name = "day_time_start",nullable = false)
    private Date dayTimeStart;

    @Column(name = "day_time_end",nullable = false)
    private Date dayTimeEnd;

    /** 添加时间 */
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    /** 是否显示 */
    @Column(name = "is_show")
    @NotNull
    private Integer isShow;

    /** 是否删除 */
    @Column(name = "is_del",insertable = false)
    private Integer isDel;

    public void copy(YxSystemStore source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}