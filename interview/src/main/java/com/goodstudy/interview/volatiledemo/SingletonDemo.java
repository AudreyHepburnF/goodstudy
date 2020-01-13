package com.goodstudy.interview.volatiledemo;

/**
 * @author congyaozhu
 * @date 2020-01-13 10:54
 * @description 线程安全的单例模式
 */
public class SingletonDemo {

    // 禁止指令重排，避免多线程环境下岗分配内存地址，对象还没初始化完成
    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法 SingletonDemo()");
    }

    // DCL 双重校验
    public static SingletonDemo getInstance(){
        if(instance == null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for ( int i = 1; i <= 10 ; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
