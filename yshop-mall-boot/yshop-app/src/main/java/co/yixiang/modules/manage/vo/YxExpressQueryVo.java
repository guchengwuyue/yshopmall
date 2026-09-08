package co.yixiang.modules.manage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 快递公司表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-13
 */
@Data
@Schema(description = "YxExpressQueryVo对象")
public class YxExpressQueryVo implements Serializable{
    private static final long serialVersionUID = 1L;

@Schema(description = "快递公司id")
private Integer id;

@Schema(description = "快递公司简称")
private String code;

@Schema(description = "快递公司全称")
private String name;

@Schema(description = "排序")
private Integer sort;

@Schema(description = "是否显示")
private Boolean isShow;

}