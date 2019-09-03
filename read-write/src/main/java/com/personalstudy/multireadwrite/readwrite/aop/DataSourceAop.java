package com.personalstudy.multireadwrite.readwrite.aop;

import com.personalstudy.multireadwrite.readwrite.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {
    @Pointcut("!@annotation(com.personalstudy.multireadwrite.readwrite.annotation.Master) " +
            "&& (execution(* com.personalstudy.multireadwrite.readwrite.service..*.select*(..)) " +
            "|| execution(* com.personalstudy.multireadwrite.readwrite.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.personalstudy.multireadwrite.readwrite.annotation.Master) " +
            "|| execution(* com.personalstudy.multireadwrite.readwrite.service..*.insert*(..)) " +
            "|| execution(* com.personalstudy.multireadwrite.readwrite.service..*.add*(..)) " +
            "|| execution(* com.personalstudy.multireadwrite.readwrite.service..*.update*(..)) " +
            "|| execution(* com.personalstudy.multireadwrite.readwrite.service..*.edit*(..)) " +
            "|| execution(* com.personalstudy.multireadwrite.readwrite.service..*.delete*(..)) " +
            "|| execution(* com.personalstudy.multireadwrite.readwrite.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


/**
 * 另一种写法：if...else... 判断哪些需要读从数据库，其余的走主数据库
 */
// @Before("execution(* com.cjs.example.service.impl.*.*(..))")
// public void before(JoinPoint jp) {
// String methodName = jp.getSignature().getName();
//
// if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
// DBContextHolder.slave();
// }else {
// DBContextHolder.master();
// }
// }

}
