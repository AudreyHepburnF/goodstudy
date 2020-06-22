package com.interview.javabasic.reflect.demo;

/**
 * @author congyaozhu
 * @date 2020-06-01 23:06
 * @description
 */
public class ClassLoaderChecker {

    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader("C:\\Users\\admin\\Desktop\\","myClassLoader");
        try {
            Class<?> wuli = classLoader.loadClass("Wuli");
            // 自定义类加载器，返回 sun.misc.Launcher$AppClassLoader@18b4aac2
            System.out.println(wuli.getClassLoader());
            // 扩展类加载器，返回 sun.misc.Launcher$ExtClassLoader@5d099f62
            System.out.println(wuli.getClassLoader().getParent());
            // BootStrapClassLoader类加载器，返回 null。底层使用C++编写，使用java的方式获取无法显示具体内容
            System.out.println((wuli.getClassLoader().getParent().getParent()));
            // 初始化 static语句块的代码
            wuli.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
