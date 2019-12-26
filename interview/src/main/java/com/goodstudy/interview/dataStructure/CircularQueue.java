package com.goodstudy.interview.dataStructure;

/**
 * @author congyaozhu
 * @date 2019-12-26 9:38
 * @description 循环队列
 */
public class CircularQueue {

    // 数组：items,数组大小n
    private String[] items;

    private int n = 0;

    private int head = 0;
    private int tail = 0;

    // 申请一个大小为capacity的数组
    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    // 入队
    public boolean enqueue(String item) {
        // 判断队列是否已满
        if ((tail + 1) % n == head) {
            return false;
        }
        // 将item添加到队尾
        items[tail] = item;
        // 队尾下标 +1
        tail = (tail + 1) % n;
        return true;
    }

    // 出队
    public String dequeue(){
        // 判断队列是否为空
        if(tail == head){
            return null;
        }
        // 队头数据出队
        String ret = items[head];
        // 队头下标 +1
        head = (head + 1) % n;
        return ret;
    }
}
