package com.personalstudy.designpattern.Visitor;

public class Client {
    public static void main(String[] args) {
        Visitor v = new MyVisitor();
        Subject s = new MySubject();
        s.accept(v);
    }
}
