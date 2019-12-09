package com.personalstudy.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断是否是Windows系统
 */
public class WindowsCondition implements Condition {

    /**
     * ConditionContext: 能够使用的上下文环境
     * AnnotatedTypeMetadata：注释信息
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        // 1. 获取ioc使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 2. 获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 3. 获取当前环境
        Environment environment = context.getEnvironment();
        // 4. 获取bean定义
        BeanDefinitionRegistry registry = context.getRegistry();

        if(environment.getProperty("os.name").contains("Windows")){
            return true;
        }
        return false;
    }
}
