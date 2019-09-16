package com.personalstudy.goodstudy.springmvc;

import com.personalstudy.goodstudy.springmvc.config.WebApplicationConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 初始化Spring环境
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //初始化Spring环境
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        // 将配置类添加到Spring环境中
        annotationConfigWebApplicationContext.register(WebApplicationConfig.class);
        // 刷新配置
        annotationConfigWebApplicationContext.refresh();
        // 初始化Servlet
        DispatcherServlet ds = new DispatcherServlet();

        // 添加Servlet DispatchServlet  放到tomcat容器中
        ServletRegistration.Dynamic  registration = servletContext.addServlet("app" , ds);

        //设置DispatcherServlet的init在web容器启动时候执行init方法
        // <load-on-startup>1</load-on-startup>
        registration.setLoadOnStartup(1);

        // 配置DispatcherServlet拦截所有请求
        // <url-pattern>/</url-pattern>
        registration.addMapping("/");
    }
}
