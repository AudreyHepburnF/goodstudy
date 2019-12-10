package com.personalstudy.goodstudy.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author congyaozhu
 * @date 2019-11-29 11:37
 * @description
 * Bean 生命周期：
 *  1. @Bean注解
 *  2. InitializingBean , DisposableBean
 *  3. @PostConstruct、 @PreDestroy
 *  4. 实现BeanPostProcessor接口
 */
@Component
public class Dog implements ApplicationContextAware  {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Dog() {
        System.out.println("Dog ... construct");
    }

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @PostConstruct
    public void init(){
        System.out.println("Dog ... init");
    }

    @PreDestroy
//    @Bean(initMethod = "xxx" , destroyMethod = "xxx")
    public void destroy(){
        System.out.println("Dog ... destroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
