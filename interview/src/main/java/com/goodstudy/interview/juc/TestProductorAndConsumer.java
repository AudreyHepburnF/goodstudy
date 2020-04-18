package com.goodstudy.interview.juc;

/**
 * @author congyaozhu
 * @date 2020-02-25 16:17
 * @description 多线程情况下生产者消费者模型测试案例
 */
public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(productor , "生产者 A").start();
        new Thread(consumer , "消费者 B").start();

        new Thread(productor , "生产者 C").start();
        new Thread(consumer , "消费者 D").start();
    }
}

// 店员
class Clerk{

    private int product = 0;

    // 进货
    public synchronized void get(){
        if(product >= 1){
            System.out.println("库存已满！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("当前库存：" + ++product);
            this.notifyAll();
        }
    }

    // 售货
    public synchronized void sale(){
        if(product <= 0){ // 如果使用if进行判断，多线程环境下回存在虚假引用，导致程序一直处于运行状态，无法正常结束。解决方式：使用循环进行判断
            System.out.println("暂无存货！");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("剩余商品：" + --product);
            this.notifyAll();
        }
    }
}

class Productor implements Runnable{

    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}

class Consumer implements Runnable{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}