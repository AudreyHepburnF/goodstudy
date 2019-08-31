package com.personalstudy.goodstudy.designpatterns.simpleFactory;

/**
 * 简单工厂模式
 */
public class TestSimpleFactory {

    public static void main(String[] args) {
        IFruit apple = FruitFactory.getFruit("com.personalstudy.goodstudy.designpatterns.simpleFactory.Apple");
        apple.get();
    }
}
