package com.personalstudy.goodstudy.base;

public class Person1 {
// 姓名（必填），final 修饰 name 一旦被初始化就不能再改变，保证了对象的不可变性。
    private final String name;
    // 年龄（必填）
    private final int age;
    // 身高（选填）
    private final int height;
    // 毕业学校（选填）
    private final String school;
    // 爱好（选填）
    private final String hobby;

    /**
     * 这个私有构造函数的作用：
     * <ol>
     * <li>成员变量的初始化。final 类型的变量必须进行初始化，否则无法编译成功</li>
     * <li>私有构造函数能够保证该对象无法从外部创建，并且 Person1 类无法被继承</li>
     * </ol>
     */
    private Person1(String name, int age, int height, String school, String hobby) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.school = school;
        this.hobby = hobby;
    }

    /**
     * 要执行的动作
     */
    public void doSomething() {
// TODO do what you want!!
    }

    /**
     * 构建器。为什么 Builder 是内部静态类？
     * <ol>
     * <li>必须是 Person1 的内部类。否则，由于 Person1 的构造函数私有，不能通过 new 的
     * 方式创建 Person1 对象</li>
     * <li>必须是静态类。由于 Person1 对象无法从外部创建，如果不是静态类，则外部无
     * 法引用 Builder 对象。</li>
     * </ol>
     * <b>注意</b>：Builder 内部成员变量要与 Person1 的成员变量保持一致。
     *
     * @author xialei
     */
    public static class Builder {
        // 姓名（必填）。注意：这里不能是 final 的
        private String name;
        // 年龄（必填）
        private int age;
        // 身高（选填）
        private int height;
        // 毕业学校（选填）
        private String school;
        // 爱好（选填）
        private String hobby;

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setSchool(String school) {
            this.school = school;
            return this;
        }

        public Builder setHobby(String hobby) {
            this.hobby = hobby;
            return this;
        }

        /**
         * 构建对象
         *
         * @return 返回待构建的对象本身
         */
        public Person1 build() {
            return new Person1(name, age, height, school, hobby);
        }
    }
}
