package com.personalstudy.designpattern.Visitor;

public class MyVisitor implements Visitor {
    @Override
    public void visiter(Subject sub) {
        System.out.println("visit the subject："+sub.getSubject());
    }
}
