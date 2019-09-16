package com.personalstudy.goodstudy.designpatterns.com.pattern.Bridge;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  15:39 2019-02-13
 * @Description :桥接模式 调用入口
 */
public class BridgeTest {

    public static void main(String[] args) {
        Implementor implementor = new ImplementorImpl1();
        Abstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.Operation();
    }
}
