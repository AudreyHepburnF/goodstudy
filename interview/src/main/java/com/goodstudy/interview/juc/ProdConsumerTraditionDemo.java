package com.goodstudy.interview.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author congyaozhu
 * @date 2020-01-15 14:54
 * @description 传统版的生产者消费者模型 demo(生产一条数据，消费一条数据，其他时候处于等待状态)
 *      lock    await    signal/signalAll
 */

class ShareData{

    private Integer number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    // 生产数据
    public void incrument(){
        lock.lock();
        try {
            // 使用while循环进行非0判断的原因？
            //  多线程环境下，为了防止虚假唤醒
            while(number != 0){
                // 停止生产，进入等待
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 消费数据
    public void decrument(){
        lock.lock();
        try {
            // 使用while循环进行非0判断的原因？
            // 多线程环境下，为了防止虚假唤醒
            while(number == 0){
                // 停止生产，进入等待
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

/**
 * 一个初始值为0的变量 两个线程交替操作 一个加1 一个减1来5轮
 */
public class ProdConsumerTraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.incrument();
            }
        } , "AAA").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                shareData.decrument();
            }
        } , "BBB").start();
    }
}
