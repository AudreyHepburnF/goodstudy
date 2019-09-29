package com.personalstudy.designpattern.simpleFactory;

/**
 * 具体产品(Concrete Product)角色
 * 苹果
 */
public class Apple implements IFruit{

    public void get(){
        System.out.println("我是苹果");
    }
}
