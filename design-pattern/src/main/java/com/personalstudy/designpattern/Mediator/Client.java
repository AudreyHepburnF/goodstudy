package com.personalstudy.designpattern.Mediator;

public class Client {
    public static void main(String[] args) {
        Mediator mediator = new MyMediator();
        mediator.createMediator();
        mediator.workAll();
    }
}
