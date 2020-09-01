package com.goodstudy.interview.juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    /**
     * 默认开启 进行生产消费的交互
     */
    private volatile boolean flag = true;
    /**
     * 默认值是0
     */
    private AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * BlockingQueue extends Queue
     *
     * 抛异常系列：
         *  add(E e);
         *  remove();
         *  element();
     *
     *  特定值系列：
     *      offer(E e);
     *      poll();
     *      peek();
     *
     *  阻塞系列
     *  void put(E e) throws InterruptedException;
     *  E take() throws InterruptedException;
     *
     *  超时系列
     *  boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;
     *  E poll(long timeout, TimeUnit unit) throws InterruptedException;
     *
     * 其它方法
     * int remainingCapacity(); //剩余容量
    * boolean contains(object o); //是否包含给定元素
    * boolean remove(0bject o); //移除与之匹配的第一个元素
    * int drainTo(collection<? super E> c); //转移元素到给定集合，返回已转移的数量
    * int drainTo(Collection<? super E> C, int maxElements); //限制最大转移数量
     */
    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean returnValue;
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";
            returnValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (returnValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列数据" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列数据" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 停止 表示 flag" + flag);
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (flag) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null==result||"".equalsIgnoreCase(result)){
                flag=false;
                System.out.println(Thread.currentThread().getName()+"\t"+"超过2m没有取到 消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费队列" + result + "成功");

        }
    }
    public void stop() throws Exception{
        flag=false;
    }
}

/**
 * Description
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 * 阻塞队列版生产者消费者
 **/
public class ProdConsumerBlockQueueDemo {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("时间到,停止活动");
        myResource.stop();

//        ExecutorService executorService1 = Executors.newFixedThreadPool(5); // 一个线程池固定大小
//        ExecutorService executorService2 = Executors.newSingleThreadExecutor(); // 一个线程池一个处理
//        ExecutorService executorService3 = Executors.newCachedThreadPool(); // 一个线程池动态扩容处理

    }
}