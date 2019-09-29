package com.personalstudy.designpattern.factoryMethod;

/**
 * 工厂方法模式案例
 * - 工厂方法模式与简单工厂模式在结构上的不同不是很明显。工厂方法类的核心是一个抽象工厂类,而简单工厂模式把核心放在一个具体类上。
 * - 工厂方法模式之所以有一个别名叫多态性工厂模式是因为具体工厂类都有共同的接口,或者有共同的抽象父类。
 * - 当系统扩展需要添加新的产品对象时,仅仅需要添加一个具体对象以及一个具体工厂对象,原有工厂对象不需要进行任何修改,也不需要修改客户端,很好的符合了"开放 一 封闭"原则。而简单工模式在添加新产品对象后不得不修改工厂方法,扩展性不好。
 * - 工厂方法模式退化后可以演变成简单工厂模式。
 */
public class TestFactoryMethod {

    public static void main(String[] args) {
        FruitFactory ff = new AppleFactory();
        IFruit fruit = ff.getFruit();
        fruit.get();
    }
}
