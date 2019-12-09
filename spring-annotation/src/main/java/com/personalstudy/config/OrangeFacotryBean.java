package com.personalstudy.config;

import com.personalstudy.entity.Orange;
import org.springframework.beans.factory.FactoryBean;

/**
 *
 */
public class OrangeFacotryBean implements FactoryBean<Orange> {

    // 将返回的对象添加到容器中
    @Override
    public Orange getObject() throws Exception {
        return new Orange();
    }

    // 返回对象的类型
    @Override
    public Class<?> getObjectType() {
        return Orange.class;
    }

    // 是否单例
    // true: 单例，容器中保留一份
    // false: 多例，每次获取都会创建一个新的bean
    @Override
    public boolean isSingleton() {
        return false;
    }
}
