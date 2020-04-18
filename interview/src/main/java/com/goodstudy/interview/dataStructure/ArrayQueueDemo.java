package com.goodstudy.interview.dataStructure;

/**
 * @author congyaozhu
 * @date 2020-02-08 22:47
 * @description
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {

    }




}

class ArrayQueue {
    // 头结点
    private int front;
    // 尾结点
    private int rear;
    // 数组
    private int[] arr;
    // 最大容量
    private int maxSize;

    public ArrayQueue(int maxSize){
        this.front = -1; // 指向队列头部，分析出front是指向队列头的前一个位置.
        this.rear = -1; // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    // 判断队列是否已满
    public boolean isFull(){
        return rear == maxSize -1;
    }

    // 添加数据到队列
    public void addQueue(int n){
        // 判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满，添加数据失败");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    // 获取队列中数据
    public int getQueue(){
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能获取数据");
        }
        front++; // front 后移
        return arr[front];
    }

    // 显示队列中的所有数据
    public void showAllQueue(){
        // 判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空，不能获取数据");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i , arr[i]);
        }
    }

    // 获取队头数据
    public int headQueue(){
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能获取数据");
        }
        return arr[front+1];
    }
}