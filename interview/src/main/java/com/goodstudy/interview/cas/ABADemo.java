package com.goodstudy.interview.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题 及 解决方式
 */
public class ABADemo {

    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 1);

    public static void main(String[] args) {

        System.out.println("=============ABA问题=============");

        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
            System.out.println(Thread.currentThread().getName() + "current value : " + atomicInteger.get());
        },"t1").start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicInteger.compareAndSet(100, 2019);
            System.out.println(Thread.currentThread().getName() + "\t current value : " + atomicInteger.get());
        },"t2").start();


        System.out.println("=============ABA问题解决方式=============");
        // 使用AtomicStampedReference进行版本控制
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号： " + stamp);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp() , atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + " \t 第2次版本号： " + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp() , atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + " \t 第3次版本号： " + atomicStampedReference.getStamp());
        },"t3").start();


        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第1次版本号： " + stamp);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + " \t 当前版本号： " + atomicStampedReference.getStamp()
                    + "\n 当前 value 值： " + atomicStampedReference.getReference() + "\t 数据是否已被更新： " + result);
        },"t4").start();
    }
}
