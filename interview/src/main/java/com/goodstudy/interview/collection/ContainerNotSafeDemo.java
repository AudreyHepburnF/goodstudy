package com.goodstudy.interview.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全的问题
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        // 栈管运行，堆管存储
    }

    private static void mapNotSafe() {
        //java.util.ConcurrentModificationException
//        Map<String,String> map = new HashMap<>();
//        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String,String> map = new ConcurrentHashMap<>();

        for ( int i = 1; i <= 30 ; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    /**
     * 不安全的Set demo
     */
    private static void setNotSafe() {
        //        Set<String> list =Collections.synchronizedSet(new HashSet<>());
//        Set<String> list = new HashSet<>();
        Set<String> list = new CopyOnWriteArraySet<>();

        for ( int i = 1; i <= 30 ; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        // HashSet 底层是HashMap
        // 为什么HashSet的底层是HashMap，但是调用add方法只需要传递一个参数。HashMap存值需要两个参数，key/value
        // add方法中，包含如下代码：
        //      return map.put(e, PRESENT)==null;
        // 其中，add的内容作为key，value值统一为 Object空对象
        //      Object PRESENT = new Object();
//        new HashSet<>().add("a");
    }

    /**
     * 不安全的List demo
     */
    private static void listNotSafe() {
        // 多线程情况下会报java.util.ConcurrentModificationException异常
//        List<String> list = new ArrayList<>();
        // add方法上加锁，可以保证多线程情况下同时只能有一个线程执行add方法。缺点：并发低
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        // 写时复制。往一个容器里面添加元素的时候，不直接往当前Object[]，而是先将当前Object[]进行Copy，复制出一个新的Object[] newElements，
        // 然后新的Object[] newElements里添加元素，添加元素之后再将原容器的引用指向新的容器 setArray(newElements);这样做的好处是可以对CopyOnWrite容器进行并发的读，
        // 而不需要加锁，因为当前容器不会添加任何元素，所以CopyOnWrite容器也是一种读写分离的思想，读和写是不同的容器
        List<String> list = new CopyOnWriteArrayList<>();

        for ( int i = 1; i <= 30 ; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
