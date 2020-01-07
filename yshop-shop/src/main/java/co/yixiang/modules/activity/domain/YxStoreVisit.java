package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-11-18
*/
@Entity
@Data
@Table(name="yx_store_visit")
public class YxStoreVisit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 产品ID
    @Column(name = "product_id")
    private Integer productId;

    // 产品类型
    @Column(name = "product_type")
    private String productType;

    // 产品分类ID
    @Column(name = "cate_id")
    private Integer cateId;

    // 产品类型
    @Column(name = "type")
    private String type;

    // 用户ID
    @Column(name = "uid")
    private Integer uid;

    // 访问次数
    @Column(name = "count")
    private Integer count;

    // 备注描述
    @Column(name = "content")
    private String content;

    // 添加时间
    @Column(name = "add_time")
    private Integer addTime;

    public void copy(YxStoreVisit source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}