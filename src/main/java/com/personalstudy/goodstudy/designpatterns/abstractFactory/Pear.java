package com.personalstudy.goodstudy.designpatterns.abstractFactory;

import com.personalstudy.goodstudy.designpatterns.factoryMethod.IFruit;

/**
 * - 具体产品(Concrete Product)角色
 * 工广方法模式所创建的具体实例对象
 */
public abstract class Pear implements IFruit{

    public abstract void get();
}
