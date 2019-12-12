package co.yixiang.modules.shop.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
* @author hupeng
* @date 2019-10-03
*/
@Data
public class YxStoreCategoryDTO implements Serializable {

    // 商品分类表ID
    private Integer id;

    // 父id
    private Integer pid;

    // 分类名称
    private String cateName;

    // 排序
    private Integer sort;

    // 图标
    private String pic;

    // 是否推荐
    private Integer isShow;

    // 添加时间
    private Integer addTime;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<YxStoreCategoryDTO> children;

    public String getLabel() {
        return cateName;
    }
}