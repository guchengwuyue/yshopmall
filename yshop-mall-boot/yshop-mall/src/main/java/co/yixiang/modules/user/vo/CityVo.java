package co.yixiang.modules.user.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


/**
 * 商城商品分类CateVO
 * @author hupeng
 * @since 2020-05-07
 */
@Getter
@Setter
@ToString
public class CityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer v; //id

    private String n; //名称

    private Integer pid;

    private List<CityVo> c; //子集


}
