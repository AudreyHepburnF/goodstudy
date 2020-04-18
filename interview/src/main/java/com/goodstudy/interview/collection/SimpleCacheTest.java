package com.goodstudy.interview.collection;

import org.junit.Test;

/**
 * @author congyaozhu
 * @date 2020-04-18 22:45
 * @description 测试自己实现的LRU算法
 */
public class SimpleCacheTest {

    @Test
    public void test(){
        SimpleCache<Integer , Integer> cache = new SimpleCache<>(3);
        for (int i = 0; i < 10; i++) {
            cache.save(i, i * i);
        }

        System.out.println("插入10个键值对后，缓存的内容：");
        System.out.println(cache + "\n");
        System.out.println("访问键值为7的节点后：缓存内容：");
        System.out.println(cache.getOne(7) + "\n");
        System.out.println("插入键值为1的键值对后，缓存内容：");
        cache.save(1, 1);
        System.out.println(cache + "\n");

    }
}
