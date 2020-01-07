package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-10-18
*/
@Entity
@Data
@Table(name="yx_system_group_data")
public class YxSystemGroupData implements Serializable {

    // 组合数据详情ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 对应的数据名称
    @Column(name = "group_name",nullable = false)
    private String groupName;

    // 数据组对应的数据值（json数据）
    @Column(name = "value",nullable = false)
    private String value;

    // 添加数据时间
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    // 数据排序
    @Column(name = "sort",nullable = false)
    private Integer sort;

    // 状态（1：开启；2：关闭；）
    @Column(name = "status",nullable = false)
    private Integer status;

    public void copy(YxSystemGroupData source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}