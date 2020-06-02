package com.goodstudy.interview.juc;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        test1();
    }

    public static void test2(){
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        System.out.println("现在6点下班了.....");
// 3y线程启动
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
// 这⾥调⽤的是await()不是wait()
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("...其他的5个员⼯⾛光了，3y终于可以⾛了");
            }
        }).start();
// 其他员⼯线程启动
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("员⼯xxxx下班了");
                    countDownLatch.countDown();
                }
            }).start();
        }
    }

    public static void test1(){
        final CountDownLatch countDownLatch = new CountDownLatch(1);
// 3y线程启动
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3y终于写完了");
                countDownLatch.countDown();
            }
        }).start();
// 其他员⼯线程启动
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("其他员⼯需要等待3y");
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("3y终于写完了，其他员⼯可以开始了！");
                }
            }).start();
        }
    }
}
