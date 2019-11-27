package co.yixiang.mp.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-10-06
*/
@Entity
@Data
@Table(name="yx_cache")
public class YxCache implements Serializable {


    @Id
    @Column(name = "`key`")
    private String key;

    // 缓存数据
    @Column(name = "result")
    private String result;

    // 缓存时间
    @Column(name = "add_time")
    private Integer addTime;

    public void copy(YxCache source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}