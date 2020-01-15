package com.goodstudy.interview.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author congyaozhu
 * @date 2020-01-15 15:45
 * @description synchronized 与 Lock 的区别？ Lock的优点
 *      1.  原始构成
 *              synchronized关键字是JVM层面的
 *                  monitorenter：底层通过monitor对象来完成，其实wait/notify等方法也依赖于monitor对象，只有在同步代码块中才能调用wait/notify等方法
 *                  monitorexit
 *              Lock是API层面的
 *      2.  使用方法
 *              synchronized不需要用户手动控制锁的释放，当代码执行完毕或抛异常会自动释放锁的占用
 *              ReentrantLock需要用户主动释放锁，否则会导致死锁。
 *      3.  加锁是否公平
 *              synchronized是非公平锁锁
 *              ReentrantLock支持公平锁与非公平锁。构造参数(false)不写默认非公平锁，构造参数声明为true则代表公平锁
 *      4.  等待是否可中断
 *              synchronized加锁不支持中断，必须得线程执行完毕或者抛异常才可结束
 *              ReentrantLock可中断，
 *                  1. 设置超时方法tryLock(long time, TimeUnit unit)
 *                  2. lockInterruptibly()放代码块中，调用interrupt()方法可以中断
 *      5.  锁绑定多个条件Condition
 *              synchronized没有。只支持随机唤醒一个或者全部唤醒。
 *              ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒。
 *
 *  题目：多线程之间顺序调用，实现A -> B -> C 三个线程启动，要求如下：
 *      A打印5次，B打印10次，C打印15次
 *      紧接着，A打印5次，B打印10次，C打印15次。。。
 */

class ShareData1{

    private Integer number = 1; // 1,2,3 代表3个线程

    // 创建非公平锁
    private Lock lock = new ReentrantLock();

    // 定义三个条件
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            // 判断
            while(number != 1){
                // 等待
                c1.await();
            }
            //
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            // 切换到线程2
            number=2;
            // 唤醒线程
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            // 判断
            while(number != 2){
                // 等待
                c2.await();
            }
            //
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            // 切换到线程3
            number=3;
            // 唤醒线程
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            // 判断
            while(number != 3){
                // 等待
                c3.await();
            }
            //
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            // 切换到线程1
            number=1;
            // 唤醒线程
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareData1 shareData1 = new ShareData1();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData1.print5();
            }
        } , "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData1.print10();
            }
        } , "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData1.print15();
            }
        } , "C").start();
    }
}
