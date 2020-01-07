package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName FromatDetailDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/12
 **/

@Data
public class FromatDetailDTO {
    private  boolean attrHidden;

    private  String detailValue;

    private List<String> detail;

    private String value;

}
