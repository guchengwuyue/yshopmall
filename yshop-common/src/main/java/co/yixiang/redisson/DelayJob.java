package co.yixiang.redisson;

import lombok.Data;

import java.io.Serializable;


/**
 * Created by kl on 2018/7/20.
 * Content :延时job
 */
@Data
public class DelayJob implements Serializable {
    private Integer oderId;//job执行参数
    private Class aClass;//具体执行实例实现
}