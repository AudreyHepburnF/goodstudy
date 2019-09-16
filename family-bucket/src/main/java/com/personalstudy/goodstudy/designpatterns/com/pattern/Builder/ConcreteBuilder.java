package com.personalstudy.goodstudy.designpatterns.com.pattern.Builder;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  16:07 2019-02-13
 * @Description :
 */
public class ConcreteBuilder extends Builder {
    @Override
    public void buildPartA() {
        System.out.println("建造A");
    }

    @Override
    public void buildPartB() {
        System.out.println("建造B");
    }

    @Override
    public void buildPartC() {
        System.out.println("建造C");
    }

}
