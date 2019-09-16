package com.personalstudy.goodstudy.designpatterns.Memento;

public class Client {
    public static void main(String[] args) {
        // 创建原始类
        Original original = new Original("qwe");
        // 创建备忘录
        Storage storage = new Storage(original.getMemento());
        // 修改原始类的状态
        System.out.println("修改前："+original.getValue());
        original.setValue("qqq");
        System.out.println("修改后："+original.getValue());
        // 回复原始类的状态
        original.resetMemento(storage.getMemento());
        System.out.println("恢复后的状态为："+original.getValue());
    }
}
