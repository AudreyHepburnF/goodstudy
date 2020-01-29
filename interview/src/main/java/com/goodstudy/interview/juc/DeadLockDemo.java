package com.goodstudy.interview.juc;


class HoldLockThread implements Runnable{

    private String lockA;

    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 自己持有: " + lockA + "\t 尝试获得: " + lockB);
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有: " + lockA + "\t 尝试获得: " + lockB);
            }
        }
    }
}

/**
 * @author congyaozhu
 * @date 2020-01-19 16:26
 * @description 死锁demo
 *   分析产生的原因？解决的方式
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA , lockB) , "AAA").start();
        new Thread(new HoldLockThread(lockB , lockA) , "BBB").start();
    }
}
