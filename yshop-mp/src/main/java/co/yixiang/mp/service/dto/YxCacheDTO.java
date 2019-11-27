package co.yixiang.mp.service.dto;

import lombok.Data;

import java.io.Serializable;


/**
* @author hupeng
* @date 2019-10-06
*/
@Data
public class YxCacheDTO implements Serializable {

    private String key;

    // 缓存数据
    private String result;

    // 缓存时间
    private Integer addTime;
}