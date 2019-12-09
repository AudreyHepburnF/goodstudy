package com.personalstudy.config;

import com.personalstudy.condition.LinuxCondition;
import com.personalstudy.condition.WindowsCondition;
import com.personalstudy.entity.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(value = "com.personalstudy" , excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = {Controller.class, Service.class})
})
// 判断条件放在类上，满足条件，执行类中的内容
@Conditional(value = {WindowsCondition.class})
public class MainConfig {

    @Bean("person")
    public Person person01(){
        return new Person("张三",18);
    }


    @Bean("bill")
    public Person person02(){
        return new Person("比尔盖茨",65);
    }

    // 判断条件放在方法上，满足条件 ，执行方法
    @Conditional(value = {LinuxCondition.class})
    @Bean("linus")
    public Person person03(){
        return new Person("linus",50);
    }
}
