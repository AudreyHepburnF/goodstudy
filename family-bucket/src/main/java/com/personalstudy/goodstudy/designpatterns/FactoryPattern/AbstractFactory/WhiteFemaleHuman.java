package com.personalstudy.goodstudy.designpatterns.FactoryPattern.AbstractFactory;

public class WhiteFemaleHuman extends AbstractWhiteHuman {
    @Override
    public void sex() {
        System.out.println("该白种人的性别为女....");
    }
}
