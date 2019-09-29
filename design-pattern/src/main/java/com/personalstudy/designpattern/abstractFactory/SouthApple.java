package com.personalstudy.designpattern.abstractFactory;

/**
 * - 具体产品(Concrete Product)角色
 * 抽象模式所创建的具体实例对象。
 */
public class SouthApple extends Apple {
    @Override
    public void get() {
        System.out.println("南方水果");
    }
}
