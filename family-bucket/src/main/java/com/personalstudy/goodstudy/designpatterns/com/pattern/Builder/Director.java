package com.personalstudy.goodstudy.designpatterns.com.pattern.Builder;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  16:08 2019-02-13
 * @Description :
 */
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product construct(){
        builder.buildPartB();
        builder.buildPartC();
        builder.buildPartA();
        return builder.getResult();
    }
}
