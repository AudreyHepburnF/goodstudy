package com.goodstudy.interview.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 元空间溢出异常 java.lang.OutOfMemoryError: Metaspace
 */
public class MetaSpaceDemo {

    static class OOMTest{}

    public static void main(String[] args) {
        int i = 1;

        try {
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o , args);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable e){
            System.out.println("******执行了 : " + i + "次！");
            e.printStackTrace();
            throw e;
        }

    }
}
