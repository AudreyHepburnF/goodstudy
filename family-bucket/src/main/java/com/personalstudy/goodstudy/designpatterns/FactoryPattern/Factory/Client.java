package com.personalstudy.goodstudy.designpatterns.FactoryPattern.Factory;

public class Client {
    public static void main(String[] args) {
        Factory factory = new Create1();
        factory.doSomething();
    }
}
