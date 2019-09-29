package com.personalstudy.designpattern.singleton;

/**
 * 单例模式
 * 使用一个私有化的构造方法，一个私有静态变量，一个公有静态方法
 */
public class Singleton1 {

    private static Singleton1 singleton;

    private Singleton1(){
    }

    /**
     * 懒汉单例设计模式
     * 优点：资源被使用的时候创建，延迟实例化节约资源
     * 缺点：多线程环境下，不安全。
     * @return
     */
    public static Singleton1 getSingleton1(){
        if(singleton == null){
            singleton = new Singleton1();
        }
        return singleton;
    }

    /**
     * 饿汉单例设计模式
     * 优点：线程安全
     * 缺点：若未被调用会产生资源浪费。
     */
    private static Singleton1 singleton1E = new Singleton1();

    /**
     * 懒汉单例设计模式II
     * 优点：避免实例化多次
     * 缺点：多线程同时访问，线程等待。存在性能问题。
     * @return
     */
    public static synchronized Singleton1 getSingleton1Syn(){
        if(singleton == null){
            singleton = new Singleton1();
        }
        return singleton;
    }

    /**
     * 懒汉单例设计模式III  双重校验锁
     * 优点：双重实例化校验。先判断singleton是否被实例化，如果没有实例化，才对实例化语句进行加锁。
     *  singleton = new Singleton1();
     *  分为三步：
     *      1.为singleton分配内存空间
     *      2.初始化singleton
     *      3.将singleton指向分配的内存地址
     * @return
     */

    public static synchronized Singleton1 getSingleton1TwoSyn(){
        if(singleton == null){
            synchronized (Singleton1.class){
                if(singleton == null){
                    singleton = new Singleton1();
                }
            }
        }
        return singleton;
    }

    /**
    *  volatile关键字：
    *  作用：禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
    *  JVM具有指令重排的特性，多线程情况下，执行顺序可能发生变化，导致未初始化就返回的情况
     */
    private volatile static Singleton1 uniqueInstance;

    /**
     * 静态内部类实现
     * 优点：当Singleton类加载时，静态内部类SingletonHolder还没有被加载进内存，只有当调用getUniqueInstance()方法从而触发SingletonHolder.
     * INSTANCE时SingletonHolder才会被加载，此时初始化INSTANCE是你，并且JVM能确保INSTANCE只被实例化一次
     */
    private static class SingletonHolder{
        private static final Singleton1 INSTANCE = new Singleton1();
    }

    public static Singleton1 getUniqueInstance(){
        return SingletonHolder.INSTANCE;
    }
}
