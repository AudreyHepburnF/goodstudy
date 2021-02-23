package com.personalstudy.goodstudy.java8;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author congyaozhu
 * @date 2020-02-01 16:54
 * @description 验证Java8以前 日期相关API的问题
 * 使用Java8新日期相关API解决问题
 */
public class TestSimpleDateFormat {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 新日期格式化API
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20200201",df);
            }
        };

        // 新建线程池，设置线程池大小为10
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(pool.submit(task));
        }

        for (Future<LocalDate> fu: list) {
            System.out.println(fu.get());
        }
        pool.shutdown();
    }

    /**
     * Java 8前 日期相关API 线程安全问题
     * java.util.concurrent.ExecutionException: java.lang.NumberFormatException: multiple points
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testSimpleDateFormatNotSafe() throws ExecutionException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task = () -> sdf.parse("20200201");
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(pool.submit(task));
        }

        for (Future<Date> f : list) {
            System.out.println(f.get());
        }
    }
}
