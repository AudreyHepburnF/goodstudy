package com.personalstudy.designpattern.Observer;

public class Mail implements IMail {
    private String name;

    public Mail(String name) {
        this.name = name;
    }

    @Override
    public void updateEMail(String str) {
        System.out.print(name+"收到新消息：");
        System.out.print(str);
        System.out.println("新消息结束！");
    }
}
