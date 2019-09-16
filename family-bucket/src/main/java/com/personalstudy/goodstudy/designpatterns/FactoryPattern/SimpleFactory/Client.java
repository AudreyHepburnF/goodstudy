package com.personalstudy.goodstudy.designpatterns.FactoryPattern.SimpleFactory;

public class Client {
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Product product = simpleFactory.getProduct(2);
        product.sale();
    }
}
