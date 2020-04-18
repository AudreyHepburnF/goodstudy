package com.goodstudy.interview.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author congyaozhu
 * @date 2020-02-24 20:55
 * @description 使用Callable实现线程调度
 */
public class TestCallable {
    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();

        FutureTask task = new FutureTask(callableDemo);
        new Thread(task).start();

        try {
            Integer result = (Integer)task.get();
            System.out.println("结果为 ： "  + result);
            System.out.println("--------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

/**
 *  可以具有返回值，并且能够抛出异常
 */
class CallableDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 1000000000000L; i++) {
            if ( i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}

/**
 * 无返回值，且无法处理异常
 */
class RunnableDemo implements Runnable{

    @Override
    public void run() {

    }
}