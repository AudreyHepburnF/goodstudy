package com.personalstudy.goodstudy.designpatterns.Memento;

public class Original {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Original(String value) {
        this.value = value;
    }

    public Memento getMemento(){
        return new Memento(value);
    }

    public void  resetMemento(Memento memento){
        this.value = memento.getValue();
    }
}
