package com.personalstudy.designpattern.abstractFactory;

/**
 * - 抽象工厂( Creator)角色
 * 抽象工厂模式的核心,包含对多个产品结构的声明,任何工厂类都必须实现这个接口。
 */
public interface FruitFactory {

    IFruit getApple();

    IFruit getBanana();
}
