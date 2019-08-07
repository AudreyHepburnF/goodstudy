package com.personalstudy.goodstudy.designpatterns.com.pattern.Builder;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  16:14 2019-02-13
 * @Description :
 */
public class Client {
    public static void main(String[] args)
    {
        Builder builder=new ConcreteBuilder();
        Director director=new Director(builder);
        Product product=director.construct();
        product.show();
    }
}
