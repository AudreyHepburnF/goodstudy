package com.personalstudy.goodstudy.designpatterns.Visitor;

public class MySubject implements Subject {
    @Override
    public void accept(Visitor visitor) {
        visitor.visiter(this);
    }

    @Override
    public String getSubject() {
        return "what???";
    }
}
