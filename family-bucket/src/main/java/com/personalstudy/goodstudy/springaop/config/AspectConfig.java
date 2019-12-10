package com.personalstudy.goodstudy.springaop.config;

import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author congyaozhu
 * @date 2019-12-02 16:27
 * @description
 *
 *  @EnableAspectJAutoProxy注解使用 @Import(AspectJAutoProxyRegistrar.class)
 *      AnnotationAwareAspectJAutoProxyCreator.class
 *          registry.registerBeanDefinition(AUTO_PROXY_CREATOR_BEAN_NAME, beanDefinition);
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Before("com.personalstudy.goodstudy.springaop.*.*(..)")
    public void before(){
        System.out.println("this is before method ...");
    }

    @Bean
    public String bean1(){
        return "bean1 init ...";
    }
}
