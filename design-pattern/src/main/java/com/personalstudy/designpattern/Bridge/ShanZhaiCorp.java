package com.personalstudy.designpattern.Bridge;

public class ShanZhaiCorp extends Corp {
    //产什么产品，不知道，等被调用的才知道
    public ShanZhaiCorp(Product product){
        super(product);
    }

    @Override
    protected void produce() {

    }

    @Override
    protected void sell() {

    }

    //狂赚钱
    public void makeMoney(){
        super.makeMoney();
        System.out.println("我赚钱呀...");
    }
}
