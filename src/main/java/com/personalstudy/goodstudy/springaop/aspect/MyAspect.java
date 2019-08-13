package com.personalstudy.goodstudy.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  17:00 2019-08-12
 * @Description :
 */
@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* com.personalstudy.goodstudy.springaop.service.*.*(..))")
    public void pointcut(){

    }

    @Before("com.personalstudy.goodstudy.springaop.aspect.MyAspect.pointcut()")
    public void before(){
        System.out.println("this is a before method....");
    }

    @After("execution(* com.personalstudy.goodstudy.springaop.service.*.*(..))")
    public void after(){
        System.out.println("this is a after method......");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint point ){
        long startTime = System.currentTimeMillis();
        try {
            point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
    }
}
