package com.personalstudy.goodstudy.designpatterns.abstractFactory;

public class SouthFruitFactory implements FruitFactory {
    @Override
    public IFruit getApple() {
        return new SouthApple();
    }

    @Override
    public IFruit getBanana() {
        return new SouthBanana();
    }
}
