package com.personalstudy.goodstudy.designpatterns.FactoryPattern.AbstractFactory;

public class BlackMaleHuman extends AbstractBlackHuman {
    @Override
    public void sex() {
        System.out.println("该黑种人的性别为男...");
    }
}
