package com.personalstudy.goodstudy.designpatterns.Bridge;

public class HouseCorp extends Corp {
    public HouseCorp() {
    }

    public HouseCorp(House house) {

    }
    //房地产公司就是盖房子
    protected void produce() {
        System.out.println("房地产公司盖房子...");
    }
    //房地产卖房子，自己住那可不赚钱
    protected void sell() {
        System.out.println("房地产公司出售房子...");
    }
    //房地产公司很High了，赚钱，计算利润
    public void makeMoney(){
        super.makeMoney();
        System.out.println("房地产公司赚大钱了...");
    }
}
