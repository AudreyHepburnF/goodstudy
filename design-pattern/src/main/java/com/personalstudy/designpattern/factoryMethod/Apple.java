package com.personalstudy.designpattern.factoryMethod;

/**
 * - 具体产品(Concrete Product)角色
 * 工广方法模式所创建的具体实例对象
 */
public class Apple implements IFruit {

    public void get(){
        System.out.println("我是苹果");
    }
}
