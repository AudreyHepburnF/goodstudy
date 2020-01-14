package com.goodstudy.interview.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author congyaozhu
 * @date 2020-01-14 17:44
 * @description Semaphore demo
 * 作用：结合了CountDownLatch与CyclicBarrier的功能，定义一个初始大小，并发情况下操作，可以动态伸缩。线程占用的时候，从总数里面--，
 * 当执行完毕，释放资源以后，资源数又会还回去，总数++
 * ecg : 线程池
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        // 模拟3个停车位
        Semaphore semaphore = new Semaphore(3);

        // 模拟6辆汽车
        for ( int i = 1; i <= 6 ; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    try{ TimeUnit.SECONDS.sleep(3); } catch ( InterruptedException e ) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName() + "\t 离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
