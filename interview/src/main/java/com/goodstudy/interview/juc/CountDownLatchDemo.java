package com.goodstudy.interview.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author congyaozhu
 * @date 2020-01-14 16:03
 * @description 程序计数器demo
 *
 * 作用： 让线程等待直到其他线程完成后才被唤醒
 * CountDownLatch主要有两个方法,当一个或多个线程调用await方法时,调用线程会被阻塞.其他线程调用countDown方法计数器减1(调用countDown方法时线程不会阻塞),
 * 当计数器的值变为0,因调用await方法被阻塞的线程会被自动唤醒并继续执行
 */

public class CountDownLatchDemo {

    public static void main(String[] args){
        try {
            sixCountry();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 秦灭六国 一统华夏
     * @throws InterruptedException
     */
    private static void sixCountry() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "国,灭亡");
                countDownLatch.countDown();
            }, CountryEnum.forEach(i).getName()).start();
        }
        countDownLatch.await();
        System.out.println("秦统一");
    }

    /**
     * 添加程序计数器之后，通过计数器控制其他线程先执行，主线程等待直到计数器为0即可自动执行
     * @throws InterruptedException
     */
    private static void afterCountDownLatch() throws InterruptedException {
        // 必须赋初始值，并且递减
        CountDownLatch count = new CountDownLatch(6);
        for (int i = 1 ; i <= 6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "上完自习");
                // 递减
                count.countDown();
            } , String.valueOf(i)).start();
        }
        // 等待计数器为0时自动唤醒
        count.await();
        System.out.println(Thread.currentThread().getName() + "\t班长锁门离开教室");
    }

    /**
     * 添加程序计数器前，无法保证主线程的执行在其他线程执行完成以后。
     */
    private static void beforeCountDownLatch() {

        for (int i = 1 ; i <= 6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "上完自习");
            } , String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t班长锁门离开教室");
    }
}
