package com.personalstudy.designpattern.FactoryPattern.AbstractFactory;

public class WhiteMaleHuman extends AbstractWhiteHuman {
    @Override
    public void sex() {
        System.out.println("该白种人的性别为男....");
    }
}
