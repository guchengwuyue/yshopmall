package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.io.Serializable;


/**
* @author hupeng
* @date 2019-10-10
*/
@Data
public class YxSystemConfigDTO implements Serializable {

    // 配置id
    private Integer id;

    // 字段名称
    private String menuName;


    // 默认值
    private String value;

    // 排序
    private Integer sort;

    // 是否隐藏
    private Integer status;
}