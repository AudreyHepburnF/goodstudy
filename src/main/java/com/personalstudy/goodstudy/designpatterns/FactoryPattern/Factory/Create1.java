package com.personalstudy.goodstudy.designpatterns.FactoryPattern.Factory;


public class Create1 extends Factory {
    @Override
    Product getProduct() {
        return new FactoryProduct();
    }
}

class Create2 extends  Factory{
    @Override
    Product getProduct() {
        return new FactoryProduct1();
    }
}