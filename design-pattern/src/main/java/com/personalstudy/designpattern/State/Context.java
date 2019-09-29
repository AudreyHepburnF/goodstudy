package com.personalstudy.designpattern.State;

public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public void method(){
        if(state.getValue()=="1"){
            state.method1();
        }else if(state.getValue() == "2"){
            state.method2();
        }
    }
}
