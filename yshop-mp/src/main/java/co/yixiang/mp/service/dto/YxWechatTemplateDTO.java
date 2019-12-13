package co.yixiang.mp.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author xuwenbo
* @date 2019-12-10
*/
@Data
public class YxWechatTemplateDTO implements Serializable {

    // 模板id
    private Integer id;

    // 模板编号
    private String tempkey;

    // 模板名
    private String name;

    // 回复内容
    private String content;

    // 模板ID
    private String tempid;

    // 添加时间
    private String addTime;

    // 状态
    private Integer status;
}