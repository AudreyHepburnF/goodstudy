package com.personalstudy.config;

import com.personalstudy.entity.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

// 自定义手工注册bean
public class MyImportBeanDefinitionRegistrat implements ImportBeanDefinitionRegistrar {

    /**
     * AnnotationMetadata：注解信息
     * BeaneDfinitionRegistry： bean定义
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean isColor = registry.containsBeanDefinition("com.personalstudy.entity.Color");
        boolean isBlue = registry.containsBeanDefinition("com.personalstudy.entity.Blue");
        if( isColor && isBlue){
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            registry.registerBeanDefinition("rainBow" , rootBeanDefinition);
        }
    }
}
