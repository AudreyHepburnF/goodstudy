package com.personalstudy.goodstudy.designpatterns.com.pattern.Bridge;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  15:46 2019-02-13
 * @Description :
 */
public class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void Operation() {
        System.out.println("继承抽象类....");
        implementor.OperationImpl();
    }
}
