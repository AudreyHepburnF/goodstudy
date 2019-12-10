package com.personalstudy.goodstudy.springaop;

import com.personalstudy.goodstudy.springaop.config.AspectConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author congyaozhu
 * @date 2019-12-02 16:32
 * @description
 */
public class AspectTest {

    @Test
    public void test1(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AspectConfig.class);
        Object bean1 = applicationContext.getBean("bean1");
        System.out.println(bean1);
    }
}
