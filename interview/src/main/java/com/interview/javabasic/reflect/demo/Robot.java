package com.interview.javabasic.reflect.demo;

/**
 * @author congyaozhu
 * @date 2020-06-01 20:28
 * @description
 */
public class Robot /*implements Runnable*/ extends Thread{

    private String name;

    public void sayHello(String args){
        System.out.println(args + name);
    }

    private String getSayHello(String args){
        return "bob is getSayHello " + args;
    }

//    @Override
//    public void run() {
//        System.out.println("this is a run methond [implements]");
//    }


    @Override
    public void run() {
        System.out.println("this is a run methond [extend]");
    }
}
