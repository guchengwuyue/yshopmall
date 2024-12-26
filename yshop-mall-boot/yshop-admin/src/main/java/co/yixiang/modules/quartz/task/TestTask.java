/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 测试用
 * @author hupeng
 * @date 2019-01-08
 */
@Slf4j
@Component
public class TestTask {

    public void run(){
        log.info("执行成功");
    }

    public void run1(String str){
        log.info("执行成功，参数为： {}" + str);
    }
}
