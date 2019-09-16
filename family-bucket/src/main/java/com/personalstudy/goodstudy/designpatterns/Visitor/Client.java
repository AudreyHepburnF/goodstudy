package com.personalstudy.goodstudy.designpatterns.Visitor;

public class Client {
    public static void main(String[] args) {
        Visitor v = new MyVisitor();
        Subject s = new MySubject();
        s.accept(v);
    }
}
