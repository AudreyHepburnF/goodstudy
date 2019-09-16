package com.personalstudy.goodstudy.designpatterns.com.pattern.Bridge;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  15:40 2019-02-13
 * @Description :
 */
public class ImplementorImpl1 implements Implementor {
    @Override
    public void OperationImpl() {
        System.out.println("实现1");
    }
}
