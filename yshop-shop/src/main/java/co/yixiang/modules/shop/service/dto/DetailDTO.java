package co.yixiang.modules.shop.service.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DetailDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/12
 **/
@Data
public class DetailDTO {
    private List<String> data;

    //private List<Map<String,List<Map<String,String>>>> res;

    private List<Map<String,Map<String,String>>> res;
}
