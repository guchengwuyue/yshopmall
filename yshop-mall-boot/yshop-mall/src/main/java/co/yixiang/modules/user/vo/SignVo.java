package co.yixiang.modules.user.vo;


import lombok.Data;

import java.io.Serializable;


/**
 * @ClassName SignVo
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/5
 **/
@Data
public class SignVo implements Serializable {
    private String addTime;
    private String title;
    private Integer number;
}
