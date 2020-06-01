package com.goodstudy.interview.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author congyaozhu
 * @date 2020-04-23 20:01
 * @description
 */
public class MyCallableTest implements Callable<Integer>{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallableTest test = new MyCallableTest();
        FutureTask task = new FutureTask(test);
        new Thread(task , "有返回值的线程").start();
        System.out.println("子线程的返回值" + task.get());
    }


    public static void test1(){
        Callable callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello callable";
            }
        };
        FutureTask task = new FutureTask(callable);
        new Thread(task).start();
        try {
            Thread.sleep(1000);
            System.out.println("hello begin");
            System.out.println(task.isDone());
            System.out.println(task.get());
            System.out.println(task.isDone());
            System.out.println("hello end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        int i;
        for (i = 0; i < 10; i += 2) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }
}
