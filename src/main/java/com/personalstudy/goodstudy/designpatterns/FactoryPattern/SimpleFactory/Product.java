package com.personalstudy.goodstudy.designpatterns.FactoryPattern.SimpleFactory;

public interface Product {
    void sale();
}

class FactoryProduct implements Product{

    @Override
    public void sale() {
        System.out.println("FactoryProduct.sale()");
    }
}

class FactoryProduct1 implements Product{

    @Override
    public void sale() {
        System.out.println("FactoryProduct1.sale()");
    }
}
class FactoryProduct2 implements Product{

    @Override
    public void sale() {
        System.out.println("FactoryProduct2.sale()");
    }
}