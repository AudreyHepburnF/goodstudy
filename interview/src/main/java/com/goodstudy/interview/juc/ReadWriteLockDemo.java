package com.goodstudy.interview.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author congyaozhu
 * @date 2020-01-14 14:38
 * @description 读写锁demo
 */

class MyCache{

    // 保证可见性
    private volatile static Map<String , Object> map = new HashMap<>();

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key , Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 开始写入");
            map.put(key, value);
            try{ TimeUnit.MILLISECONDS.sleep(300); } catch ( InterruptedException e ) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 开始读取");
            Object result = map.get(key);
            try{ TimeUnit.MILLISECONDS.sleep(300); } catch ( InterruptedException e ) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t 读取完成 : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/**
 * 多个线程同时操作 单线程情况下没有任何问题
 * 为了满足并发量读取共享资源应该可以同时进行
 * 多线程环境下，如果有一个线程想去写共享资源类  此时不允许其他线程对资源进行读或写
 * <p>
 * 小总结:
 *      读 读能共存
 *      读 写不能共存
 *      写 写不能共存
 *      写操作 原子+独占 整个过程必须是一个完成的统一整体 中间不允许被分割 被打断
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache cache = new MyCache();
        for ( int i = 1; i <= 10 ; i++) {
            final int tempInt = i ;
            new Thread(() -> {
                cache.put(tempInt + "", tempInt + "");
            },String.valueOf(i)).start();
        }

        try{ TimeUnit.SECONDS.sleep(3); } catch ( InterruptedException e ) { e.printStackTrace(); }
        for ( int i = 1; i <= 10 ; i++) {
            final int tempInt = i ;
            new Thread(() -> {
                cache.get(tempInt + "");
            },String.valueOf(i)).start();
        }
    }
}
