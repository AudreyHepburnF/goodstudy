package com.goodstudy.interview.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author congyaozhu
 * @date 2020-01-14 14:11
 * @description 自旋锁demo
 * 优点：不会立即阻塞，减少线程上下文切换的消耗。
 * 缺点：循环会消耗CPU
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 自定义加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in ");
        // 不为null，一直等待
        while(!atomicReference.compareAndSet(null, thread)){

        }
    }

    // 自定义解锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoke myUnLock()");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try{ TimeUnit.SECONDS.sleep(5); } catch ( InterruptedException e ) { e.printStackTrace(); }
            spinLockDemo.myUnLock();
        } , "AA").start();

        try{ TimeUnit.SECONDS.sleep(1); } catch ( InterruptedException e ) { e.printStackTrace(); }

        new Thread(() -> {
            spinLockDemo.myLock();
            try{ TimeUnit.SECONDS.sleep(1); } catch ( InterruptedException e ) { e.printStackTrace(); }
            spinLockDemo.myUnLock();
        } , "BB").start();
    }
}
