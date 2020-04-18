package com.personalstudy.designpattern.principle.depend;

/**
 * @author congyaozhu
 * @date 2020-02-06 15:01
 * @description 依赖倒转原则demo案例(接口传递)
 */
public class DependenceInversion {

    public static void main(String[] args) {
        // 方式一：接口传递
        Person person = new Person();
        person.revice(new Email());

        // 方式二：构造器传递
        ChangHong changHong = new ChangHong();
        OpenAndClose openAndClose = new OpenAndClose(changHong);
        openAndClose.open();
    }
}

interface IReceiver{
    String getInfo();
}

class Email implements IReceiver{

    @Override
    public String getInfo() {
        return "获取 Email 信息";
    }
}

class WeiXin implements IReceiver{

    @Override
    public String getInfo() {
        return "获取 微信 信息";
    }
}

class Person{
    // 这里是对接口的依赖
    public void revice(IReceiver receiver){
        System.out.println(receiver.getInfo());
    }
}

// ------------------------------------------------------------------

interface ITV{
    void play();
}

class ChangHong implements ITV{

    @Override
    public void play() {
        System.out.println("长虹电视机，打开");
    }
}

interface IOpenAndClose {
    void open(); // 抽象方法
}

class OpenAndClose implements IOpenAndClose{

    private ITV itv;

    public OpenAndClose(ITV itv) {
        this.itv = itv;
    }

    @Override
    public void open() {
        itv.play();
    }
}

