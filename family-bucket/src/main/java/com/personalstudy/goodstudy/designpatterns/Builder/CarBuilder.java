package com.personalstudy.goodstudy.designpatterns.Builder;

import java.util.ArrayList;

public abstract class CarBuilder {
    //建造一个模型，你要给我一个顺序要，就是组装顺序
    public abstract void setSequence(ArrayList<String> sequence);

    public abstract CarModel getCarModel();
}
