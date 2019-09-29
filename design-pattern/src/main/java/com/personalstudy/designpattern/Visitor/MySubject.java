package com.personalstudy.designpattern.Visitor;

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
