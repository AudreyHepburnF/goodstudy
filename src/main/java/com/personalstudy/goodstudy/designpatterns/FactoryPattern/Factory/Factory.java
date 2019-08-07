package com.personalstudy.goodstudy.designpatterns.FactoryPattern.Factory;


public abstract class Factory {
    abstract Product getProduct();
    public void doSomething(){
        Product product = getProduct();
        System.out.println("Factory:"+product);
    }
}
