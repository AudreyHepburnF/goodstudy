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
            System.out.println(wuli.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
