package co.yixiang.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
* @author hupeng
* @date 2019-12-04
*/
@Entity
@Data
@Table(name="yx_system_user_task")
public class YxSystemUserTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 任务名称
    @Column(name = "name",nullable = false)
    private String name;

    // 配置原名
    @Column(name = "real_name",nullable = false)
    private String realName;

    // 任务类型
    @Column(name = "task_type",nullable = false)
    private String taskType;

    // 限定数
    @Column(name = "number",nullable = false)
    private Integer number;

    // 等级id
    @Column(name = "level_id",nullable = false)
    private Integer levelId;

    // 排序
    @Column(name = "sort",nullable = false)
    private Integer sort;

    // 是否显示
    @Column(name = "is_show",nullable = false)
    private Integer isShow;

    // 是否务必达成任务,1务必达成,0=满足其一
    @Column(name = "is_must",nullable = false)
    private Integer isMust;

    // 任务说明
    @Column(name = "illustrate",nullable = false)
    private String illustrate;

    // 新增时间
    @Column(name = "add_time",nullable = false)
    private Integer addTime;

    public void copy(YxSystemUserTask source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}