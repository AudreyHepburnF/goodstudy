package com.personalstudy.entity;

public class Person {

    private String naem;

    private Integer age;

    public String getNaem() {
        return naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person() {
    }

    public Person(String naem, Integer age) {
        this.naem = naem;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "naem='" + naem + '\'' +
                ", age=" + age +
                '}';
    }
}
