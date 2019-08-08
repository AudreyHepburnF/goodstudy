package com.personalstudy.goodstudy.lambda;

import com.personalstudy.goodstudy.base.Person;
import com.personalstudy.goodstudy.base.User;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  22:09 2019-08-07
 * @Description :
 */
public class LambdaTest {

    @Test
    public  void test1(){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京");
            }
        };
        run.run();

        System.out.println("---------------------------");
        Runnable run2 = () -> System.out.println("我爱故宫");
        run2.run();
    }

    @Test
    public void test2(){
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comp.compare(20, 30));

        System.out.println("-----------------------------------");

        Comparator<Integer> comparator = (o1 , o2) -> Integer.compare(o1, o2);
        System.out.println(comparator.compare(10, 5));

        System.out.println("*************************");

        // 方法引用
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(20, 15));
    }

    public List<String> filterString(List<String> list , Predicate<String> p){
        List<String> filterList = new ArrayList<>();
        for (String str : list) {
            if(p.test(str)){
                filterList.add(str);
            }
        }
        return filterList;
    }
    // 内置核心函数式接口之一：Predicate
    @Test
    public void test3(){
        List<String> list = Arrays.asList("北京" , "南京" , "天津" , "普京" , "吴京");
        List<String> str = filterString(list, s -> s.contains("津"));
        System.out.println(str);
    }

    // 方法引用：消费型(非静态方法)
    @Test
    public void test4(){
        PrintStream out = System.out;
        Consumer<String> con = out ::print;
        con.accept("今天气温不错");
    }

    // 方法引用： 供给型(非静态方法)
    @Test
    public void test5(){
        Person person = new Person();
        person.setName("张三");
        Supplier sup =  person ::getName;
        System.out.println(sup.get());
    }

    @Test
    public void test6(){
        Comparator<Integer> comp = Integer::compareTo;
        System.out.println(comp.compare(10, 20));

        System.out.println("****************************");

        BiPredicate<String , String> biPredicate = String::equals;
        System.out.println(biPredicate.test("abc", "abd"));
    }

    //Function中的R apply(T t)
    //Math中的Long round(Double d)
    @Test
    public void test7() {
        Function<Double , Long> function = Math::round;
        System.out.println(function.apply(12.3));
        System.out.println(function.apply(12.6));

        System.out.println("***********************");
        Function<Double , Long> function2 = d -> Math.round(d);
        System.out.println(function2.apply(17.5));
    }

    // Function中的R apply(T t)
    // Employee中的String getName();
    @Test
    public void test8() {
        Person person = new Person();
        person.setName("李四");
        Function<Person , String> fun = Person::getName;
        System.out.println(fun.apply(person));
    }

    // 构造器引用
    @Test
    public void test9(){
        Supplier<Person> sup = Person::new;
        System.out.println(sup.get());

        System.out.println("**********************");

        BiFunction< Integer , String , User > fun = User::new;
        System.out.println(fun.apply(15, "张三"));
    }

    // 数组引用
    @Test
    public void test10(){
        Function<Integer , String[]> fun = String[] :: new;
        System.out.println(Arrays.toString(fun.apply(5)));
    }
}
