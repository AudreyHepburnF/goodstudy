package com.personalstudy.goodstudy.designpatterns.State;

public class Client {
    public static void main(String[] args) {
        State s = new State();
        Context context = new Context(s);
        s.setValue("1");
        context.method();

        s.setValue("2");
        context.method();
    }
}
