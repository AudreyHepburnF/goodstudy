package com.personalstudy.designpattern.simpleFactory;

/**
 * 具体产品(Concrete Product)角色
 * 香蕉
 */
public class Banana implements IFruit{

    public void get(){
        System.out.println("我是香蕉");
    }
}
