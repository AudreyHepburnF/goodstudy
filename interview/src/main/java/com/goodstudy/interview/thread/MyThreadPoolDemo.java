package com.goodstudy.interview.thread;

import java.util.concurrent.*;

/**
 * @author congyaozhu
 * @date 2020-01-19 15:02
 * @description 获得使用java多线程的方式
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        // ThreadPoolExecutor.AbortPolicy() (默认): 超出大小，直接抛出RejectedException异常阻止系统正常运行
        // ThreadPoolExecutor.CallerRunsPolicy() : "调用者运行"一种调节机制,该策略既不会抛弃任务,也不会抛出异常,超出(最大线程数 + 阻塞队列) 大小，会将调用返回给调用者。
        // ThreadPoolExecutor.DiscardOldestPolicy() : 超出大小时，抛弃队列中 等待最久的任务，然后把当前任务加入队列中尝试再次提交
        // ThreadPoolExecutor.DiscardPolicy() : 超出大小时，直接丢弃任务，不予任何处理也不抛出异常，如果允许任务丢失，这是最好的拒绝策略
        ExecutorService threadPool = new ThreadPoolExecutor(2,5,1, TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory() , new ThreadPoolExecutor.DiscardPolicy());

        try {
            for ( int i = 1; i <= 10 ; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        }finally {
            threadPool.shutdown();
        }
    }
}
