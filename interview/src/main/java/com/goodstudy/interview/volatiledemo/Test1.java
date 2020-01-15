package com.goodstudy.interview.volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile demo
 *
 */
class MyDate{

    volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }

    public  void addPlus(){
        number++;
    }

    // 引入juc包下的原子Integer类，解决volatile无法保证原子性问题
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}


public class Test1 {

    public static void main(String[] args) {
        seeOKByNoAtomicity();
    }

    /**
     * 测试volatile的非原子性
     * 解决volatile 非原子性问题
     *      1. synchronized
     *      2. 换int为AtomicInteger ( 换基本数据类型为JUC包下的类型 )
     */
    private static void seeOKByNoAtomicity() {
        MyDate date = new MyDate();
        for (int i = 1; i <= 20 ; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000 ; j++) {
                    date.addPlus();
                    date.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        // 判断当前执行线程数
        // 启动时，默认 包含main线程  +  GC线程
        // Thread.activeCount() > 2 保证存在未执行完的线程时，进行yield，保证上面线程全部执行完毕再执行下面内容
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int type , finally number is : " + date.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type , finally atomicInteger is : " + date.atomicInteger);
    }

    /**
     * 测试 volatile的可见性
     *  效果：当未添加volatile关键字时，一下程序线程修改完number结果，会一直停留在while循环中，此时修改的数据对其他线程不可见。
     *  当添加volatile关键字后，线程一修改number数据，其他线程读取到新数据。
     */
    private static void seeOkByVolatile() {
        MyDate date = new MyDate();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t  number is " + date.number + "is starting....");
            try {
                Thread.sleep(3);
                date.addTo60();
                System.out.println("current number is " + date.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();


        while(date.number == 0){

        }
        System.out.println("main is over , current number is " + date.number);
    }
}
