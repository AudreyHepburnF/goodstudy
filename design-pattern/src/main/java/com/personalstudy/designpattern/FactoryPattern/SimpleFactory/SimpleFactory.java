package com.personalstudy.designpattern.FactoryPattern.SimpleFactory;

public class SimpleFactory {

    Product product;
    public Product getProduct(int type){
        if(type == 1){
            product = new FactoryProduct();
        }else if(type == 2){
            product = new FactoryProduct1();
        }else if(type == 3){
            product = new FactoryProduct2();
        }
        return product;
    }
}
