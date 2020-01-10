package co.yixiang.modules.shop.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author hupeng
* @date 2020-01-09
*/
@Entity
@Data
@Table(name="yx_material")
public class YxMaterial implements Serializable {

    /** PK */
    @Id
    @Column(name = "id")
    private String id;


    /** 逻辑删除标记（0：显示；1：隐藏） */
    @Column(name = "del_flag",nullable = false,insertable = false)
    private String delFlag;

    /** 创建时间 */
    @Column(name = "create_time",nullable = false)
    @CreationTimestamp
    private Timestamp createTime;


    /** 创建者ID */
    @Column(name = "create_id")
    private String createId;

    /** 类型1、图片；2、视频 */
    @Column(name = "type",nullable = false)
    private String type;

    /** 分组ID */
    @Column(name = "group_id")
    private String groupId;

    /** 素材名 */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /** 素材链接 */
    @Column(name = "url")
    private String url;

    public void copy(YxMaterial source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}