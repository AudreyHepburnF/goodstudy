package com.goodstudy.interview.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS是什么？ compareAndSet
 *  比较并交换
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        /**
         * atomicInteger.compareAndSet
         *  参数一：expect  期望值 与 正式值的比较(类似版本控制、assist)
         *      ecg： atomicInteger 初始化为 5 ， 当需要更新值的时候先判断下当前值是否还是5 ，如果值没变，则进行更新操作
         *  参数二：update  更新后的值
         */
        // 条件成立 ， 结果变为 2019
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current value : " + atomicInteger.get());

        // 条件不成立，结果还是2019
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t current value : " + atomicInteger.get());
    }
}
