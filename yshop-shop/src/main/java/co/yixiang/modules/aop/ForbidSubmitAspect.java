/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 重复提交aop
 *
 * @author hupeng
 * @since 2020-06-02
 */
@Aspect
@Component
@Slf4j
public class ForbidSubmitAspect {


    @Pointcut("@annotation(forbidSubmit)")
    public void pointCut(ForbidSubmit forbidSubmit) {
    }

    @Around("pointCut(forbidSubmit)")
    public Object around(ProceedingJoinPoint pjp, ForbidSubmit forbidSubmit) throws Throwable {

        //用于拦截演示环境一些禁止操作
        //throw new YshopException("演示环境禁止操作");
        return pjp.proceed();


    }


}
