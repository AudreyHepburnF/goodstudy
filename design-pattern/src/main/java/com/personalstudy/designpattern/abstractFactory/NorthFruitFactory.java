package com.personalstudy.designpattern.abstractFactory;

/**
 * - 具体工厂( Concrete Creator)角色
 * 具体工厂类是抽象工厂的一个实现,负责实例化某个产品族中的产品对象
 */
public class NorthFruitFactory implements FruitFactory {
    @Override
    public IFruit getApple() {
        return new NorthApple();
    }

    @Override
    public IFruit getBanana() {
        return new NorthBanana();
    }
}
