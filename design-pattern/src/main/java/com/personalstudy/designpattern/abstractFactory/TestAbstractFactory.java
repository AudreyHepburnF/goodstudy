package com.personalstudy.designpattern.abstractFactory;

/**
 * 抽象工厂 案例
 */
public class TestAbstractFactory {

    public static void main(String[] args) {
        FruitFactory ff = new NorthFruitFactory();
        IFruit apple = ff.getApple();
        apple.get();
    }
}
