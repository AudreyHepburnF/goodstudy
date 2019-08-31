package com.personalstudy.goodstudy.designpatterns.simpleFactory;


import org.springframework.util.StringUtils;

/**
 * 工厂(Creator)角色
 * 简单工广模式的核心,它负责实现创建所有实例的内部逻辑。工厂类可以被外界直接调用,创建所需的产品对象。
 */
public class FruitFactory {

    public static IFruit getFruit(String type){
        try {
            if (!StringUtils.isEmpty(type)) {
                Class<?> clazz = Class.forName(type);
                return (IFruit) clazz.newInstance();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
