package com.interview.javabasic.reflect.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author congyaozhu
 * @date 2020-06-01 20:31
 * @description
 */
public class RobotSample {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> aClass = Class.forName("com.interview.javabasic.reflect.demo.Robot");
        Robot robot = (Robot)aClass.newInstance();
        System.out.println("Class name is " + aClass.getName());

        // 调用public方法
//        robot.sayHello("zhangsan");

        // 调用private方法
//        Method getSayHello = aClass.getDeclaredMethod("getSayHello", String.class);
//        getSayHello.setAccessible(true);
//        Object getSayHelloInvoke = getSayHello.invoke(robot, "lisi");
//        System.out.println("getsayHello result is " + getSayHelloInvoke);
//
//        Method sayHello = aClass.getMethod("sayHello", String.class);
//        sayHello.invoke(robot, "Welcom ");
//
//        // 调用private属性
//        Field name = aClass.getDeclaredField("name");
//        name.setAccessible(true);
//        name.set(robot, "wangwu");
//        sayHello.invoke(robot, "Welcom ");

        // 测试getDeclaredMethod能否调用实现类的方法(可以)
//        Method[] declaredMethods = aClass.getDeclaredMethods();
//        Method declaredMethods = aClass.getDeclaredMethod("run");
//        declaredMethods.invoke(robot, null);

        // 测试getDeclaredMethod能否调用父类的方法
//        Method[] declaredMethods = aClass.getDeclaredMethods();
//        Method declaredMethods = aClass.getDeclaredMethod("run");
//        declaredMethods.invoke(robot, null);

        Method parentMethod = aClass.getMethod("run", null);
        parentMethod.invoke(robot, null);
    }
}
