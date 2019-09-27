package com.goodstudy.interview.volatiledemo;

import java.util.PriorityQueue;

/**
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 *
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 */
public class KthLargest {

    // 定义第k个元素标识
    final int k;

    // 定义优先队列
    final PriorityQueue<Integer> minHeap;

    public KthLargest(int k , int[] nums){
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val){
        // 总长度小于 k
        if(minHeap.size() < k){
            // 入队
            minHeap.offer(val);
            // 小根队列 小于 val
            // 将最小值出队，再将val入队
        }else if (minHeap.peek() < val) {
            minHeap.poll();
            minHeap.offer(val);
        }
        // 用于检索或获取Queue的第一个元素或Queue头部的元素。检索到的元素不会从队列中删除或删除。
        return minHeap.peek();
    }
}
