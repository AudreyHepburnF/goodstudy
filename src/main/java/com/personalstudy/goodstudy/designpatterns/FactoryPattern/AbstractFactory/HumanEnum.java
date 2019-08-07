package com.personalstudy.goodstudy.designpatterns.FactoryPattern.AbstractFactory;

public enum HumanEnum {
    //把世界上所有人类型都定义出来
    YelloMaleHuman("FactoryPattern.AbstractFactory.YellowMaleHuman"),
    YelloFemaleHuman("FactoryPattern.AbstractFactory.YellowFemaleHuman"),
    WhiteFemaleHuman("FactoryPattern.AbstractFactory.WhiteFemaleHuman"),
    WhiteMaleHuman("FactoryPattern.AbstractFactory.WhiteMaleHuman"),
    BlackFemaleHuman("FactoryPattern.AbstractFactory.BlackFemaleHuman"),
    BlackMaleHuman("FactoryPattern.AbstractFactory.BlackMaleHuman");
    private String value = "";
    //定义构造函数，目的是Data(value)类型的相匹配
    private HumanEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
