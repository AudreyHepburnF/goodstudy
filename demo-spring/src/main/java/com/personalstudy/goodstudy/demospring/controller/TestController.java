package com.personalstudy.goodstudy.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author congyaozhu
 * @date 2020-05-11 20:03
 * @description 测试控制器，模拟Java性能问题并给出相应的处理方法
 */
@Controller
public class TestController {

    /**
     * 模拟CPU占满
     */
    @GetMapping("/cpu/loop")
    public void testCPULoop() throws InterruptedException {
        System.out.println("请求cpu死循环");
        Thread.currentThread().setName("loop-thread-cpu");
        int num = 0;
        while (true) {
            num++;
            if (num == Integer.MAX_VALUE) {
                System.out.println("reset");
            }
            num = 0;
        }
    }

    /**
     * 模拟内存泄漏
     * 模拟内存泄漏借助了ThreadLocal对象来完成，ThreadLocal是一个线程私有变量，可以绑定到线程上，在整个线程的生命周期都会存在，但是由于ThreadLocal的特殊性，
     * ThreadLocal是基于ThreadLocalMap实现的，ThreadLocalMap的Entry继承WeakReference，而Entry的Key是WeakReference的封装，换句话说Key就是弱引用，弱引用在下次GC之后就会被回收，
     * 如果ThreadLocal在set之后不进行后续的操作，因为GC会把Key清除掉，但是Value由于线程还在存活，所以Value一直不会被回收，最后就会发生内存泄漏。
     */
    @GetMapping(value = "/memory/leak")
    public String leak(Integer count) {
        for (int i = 0; i < count ; i++) {
            System.out.println("模拟内存泄漏 -> " + i);
            ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();
            localVariable.set(new Byte[4096 * 1024]);// 为线程添加变量
        }
        return "ok";
    }

    ExecutorService service = new ThreadPoolExecutor(4, 10,
            0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1024),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    @GetMapping("/cpu/test")
    public String testCPU(Integer count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            System.out.println("请求cpu -> " + i);
            Object lock1 = new Object();
            Object lock2 = new Object();
            service.submit(new DeadLockThread(lock1, lock2), "deadLookThread-" + new Random().nextInt());
            service.submit(new DeadLockThread(lock2, lock1), "deadLookThread-" + new Random().nextInt());
        }
        return "ok";
    }


    private class DeadLockThread implements Runnable {
        private Object lock1;
        private Object lock2;

        public DeadLockThread(Object lock1, Object lock2) {
            this.lock1 = lock1;
            this.lock2 = lock2;
        }

        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName()+"get lock2 and wait lock1");
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName()+"get lock1 and lock2 ");
                }
            }
        }
    }

    /**
     * 线程频繁切换
     * 上下文切换会导致将大量CPU时间浪费在寄存器、内核栈以及虚拟内存的保存和恢复上，导致系统整体性能下降。当你发现系统的性能出现明显的下降时候，需要考虑是否发生了大量的线程上下文切换。
     * @param num
     * @return
     */
    @GetMapping(value = "/thread/swap")
    public String theadSwap(int num) {
        System.out.println("模拟线程切换");
        for (int i = 0; i < num; i++) {
            new Thread(new ThreadSwap1(new AtomicInteger(0)),"thread-swap"+i).start();
        }
        return "ok";
    }
    public class ThreadSwap1 implements Runnable {
        private AtomicInteger integer;

        public ThreadSwap1(AtomicInteger integer) {
            this.integer = integer;
        }

        @Override
        public void run() {
            while (true) {
                integer.addAndGet(1);
                Thread.yield(); //让出CPU资源
            }
        }
    }

}
