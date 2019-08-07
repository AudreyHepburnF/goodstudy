package com.personalstudy.goodstudy.designpatterns.com.pattern.Builder;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  16:06 2019-02-13
 * @Description :
 */
public  abstract class Builder {
    //创建产品对象
    protected Product product=new Product();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();
    //返回产品对象
    final public Product getResult()
    {
        return product;
    }
}
