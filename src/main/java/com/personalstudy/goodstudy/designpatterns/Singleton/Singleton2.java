package com.personalstudy.goodstudy.designpatterns.Singleton;

public enum  Singleton2 {
    INSTANCE;

    private String objName;


    public String getObjName() {
        return objName;
    }


    public void setObjName(String objName) {
        this.objName = objName;
    }


    public static void main(String[] args) {

        // 单例测试
        Singleton2 firstSingleton2 = Singleton2.INSTANCE;
        firstSingleton2.setObjName("firstName");
        System.out.println(firstSingleton2.getObjName());
        Singleton2 secondSingleton2 = Singleton2.INSTANCE;
        secondSingleton2.setObjName("secondName");
        System.out.println(firstSingleton2.getObjName());
        System.out.println(secondSingleton2.getObjName());

        // 反射获取实例测试
        try {
            Singleton2[] enumConstants = Singleton2.class.getEnumConstants();
            for (Singleton2 enumConstant : enumConstants) {
                System.out.println(enumConstant.getObjName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
