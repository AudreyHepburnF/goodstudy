package com.personalstudy.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

// 自定义导入组件
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 定义返回值，即要导入到容器中的组件全类名
        return new String[]{"com.personalstudy.entity.Blue" , "com.personalstudy.entity.Red" , "com.personalstudy.entity.Yellow"};
    }
}
