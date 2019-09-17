package com.personalstudy.goodstudy.designpatterns.factoryMethod;

/**
 * - 具体工厂( Concrete Creator)角色
 * 具体工厂类是抽象工厂的一个实现,负责实例化产品对象。
 */
public class AppleFactory implements FruitFactory {
    @Override
    public IFruit getFruit() {
        return new Apple();
    }
}