package com.goodstudy.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author congyaozhu
 * @date 2020-01-14 11:25
 * @description 可重入锁demo
 *  1. 是什么？
 *      同一个线程获取外层的锁即可自动获取内层方法的锁。
 *      ecg：只要有门钥匙(外层锁)，就可以进入每一个卧室(内层锁)
 *
 *  2. 作用：
 *      避免死锁
 */

class Phone implements Runnable{

    // synchronized 是 非公平可重入锁
    public synchronized void method01(){
        System.out.println(Thread.currentThread().getId() + "\t method01()");
        // 当前加锁线程调用另一加锁方法
        method02();
    }

    public synchronized void method02(){
        System.out.println(Thread.currentThread().getId() + "\t method02()");
    }


    
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    private void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t get()");
            // 当前加锁线程调用另一加锁方法
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "\t set()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.method01();
        } , "t1").start();

        new Thread(() -> {
            phone.method01();
        } , "t2").start();


        try{ TimeUnit.SECONDS.sleep(3); } catch ( InterruptedException e ) { e.printStackTrace(); }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();

    }
}
