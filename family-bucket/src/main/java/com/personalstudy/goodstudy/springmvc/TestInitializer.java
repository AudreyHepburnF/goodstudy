package com.personalstudy.goodstudy.springmvc;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * 在web环境下
 */
@HandlesTypes(TestInterface.class)
public class TestInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("------------TestInitializer-----------------");
        // Set<Class<?>> c 中包含所有实现TestInterface接口的集合
        // 并且需要在javax.servlet.ServletContainerInitializer配置文件中，添加上TestInitializer路径
        // 且该配置文件中的类必须实现ServletContainerInitializer接口
        // 其中Set<Class<?>> webAppInitializerClasses 即可获取到所有的WebApplicationInitializer.class实现。
        // 当获取到所有的实现以后，就可以通过反射的方式进行实例化。
        for (Class<?> clazz : c) {
            System.out.println(clazz);
        }
    }
}
