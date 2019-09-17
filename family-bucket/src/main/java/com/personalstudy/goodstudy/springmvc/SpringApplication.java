package com.personalstudy.goodstudy.springmvc;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class SpringApplication {

    public static void run(){
        // 创建文件目录
        File file = new File(System.getProperty("java.io.tmpdir"));
        // 启动内嵌tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8888);

        // 手动标识为web项目
        // 存在问题：如果添加本行代码，必须添加jsp依赖，否则报错.SpringBoot默认没有jsp依赖
//         tomcat.addWebapp("/" , file.getAbsolutePath());

        //tomcat 可以正常启动，但是无法加载Spring初始化的方法
        tomcat.addContext("/" , file.getAbsolutePath());

        try {
            // 运行tomcat
            tomcat.start();
            // 开启服务等待
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
