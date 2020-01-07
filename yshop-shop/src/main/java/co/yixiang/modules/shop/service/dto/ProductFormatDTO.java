package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName ProductFormatDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/12
 **/

@Data
public class ProductFormatDTO {

    private Double price;

    private Double cost;

    private int sales;

    private String pic;

   // private Map<String, List<Map<String,String>>> detail;

    //private List<Map<String, String>> detail;
    private Map<String, String> detail;
    private Boolean check;
    

}
