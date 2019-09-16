package com.personalstudy.goodstudy.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  11:30 2019-08-07
 * @Description :
 */
@RunWith(JUnit4.class)
public class ReflexDemo {

    @Test
    public void test1() throws ClassNotFoundException {
        Class personClass = Person.class;
        System.out.println("personClass : " + personClass);
        System.out.println("===================1========================");
        Person person = new Person();
        Class aClass = person.getClass();
        System.out.println("aClass : " + aClass);
        System.out.println("personClass == aClass ?" + (personClass==aClass));
        System.out.println("===================2========================");

        Class<?> clazz = Class.forName("com.personalstudy.goodstudy.base.Person");
        System.out.println("clazz : " +  clazz);
        System.out.println("personClass == clazz ?" + (personClass==clazz));
        System.out.println("===================3========================");

        ClassLoader classLoader = ReflexDemo.class.getClassLoader();
        Class<?> clazz2 = classLoader.loadClass("com.personalstudy.goodstudy.base.Person");
        System.out.println("clazz2 : " + clazz2);
        System.out.println("personClass == clazz2 ?" + (personClass==clazz2));
        System.out.println("===================4========================");

    }

    @Test
    public void test2() throws Exception {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        properties.load(fis);
//        String name = properties.getProperty("com.didispace.blog.name");
//        String title = properties.getProperty("com.didispace.blog.title");
//        System.out.println("name : " + name);
//        System.out.println("title : " +title);

        ClassLoader classLoader = ReflexDemo.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties");
        properties.load(resourceAsStream);
        String name = properties.getProperty("com.didispace.blog.name");
        String title = properties.getProperty("com.didispace.blog.title");
        System.out.println("name : " + name);
        System.out.println("title : " +title);
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
    }

    @Test
    public void test3(){
        Class<Person> clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field :  declaredFields) {
        }
    }
}
