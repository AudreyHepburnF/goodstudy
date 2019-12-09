package com.personalstudy.config;

import com.personalstudy.condition.LinuxCondition;
import com.personalstudy.condition.WindowsCondition;
import com.personalstudy.entity.Color;
import com.personalstudy.entity.Person;
import org.springframework.context.annotation.*;

@Configuration
// 判断条件放在类上，满足条件，执行类中的内容
@Conditional(value = {WindowsCondition.class})
// id默认是组件的全类名
@Import(value = {Color.class , MyImportSelector.class , MyImportBeanDefinitionRegistrat.class })
@EnableAspectJAutoProxy
public class MainConfig2 {

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

    @Bean
    public OrangeFacotryBean orangeFacotryBean(){
        return new OrangeFacotryBean();
    }
}

/**
 * internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator
 *
 * AnnotationAwareAspectJAutoProxyCreator
 *  -> AspectJAwareAdvisorAutoProxyCreator
 *   -> AbstractAdvisorAutoProxyCreator
 *    -> AbstractAutoProxyCreator
 *          implements SmartInstantiationAwareBeanPostProcessor(bean后置处理器), BeanFactoryAware(创建BeanFactory)
 */