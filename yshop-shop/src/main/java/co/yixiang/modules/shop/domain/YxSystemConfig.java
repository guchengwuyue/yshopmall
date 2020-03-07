package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-10-10
*/
@Entity
@Data
@Table(name="yx_system_config")
public class YxSystemConfig implements Serializable {

    // 配置id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 字段名称
    @Column(name = "menu_name",nullable = false)
    private String menuName;


    // 默认值
    @Column(name = "value")
    private String value;

    // 排序
    @Column(name = "sort",nullable = false)
    private Integer sort;

    // 是否隐藏
    @Column(name = "status",nullable = false)
    private Integer status;

    public void copy(YxSystemConfig source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}