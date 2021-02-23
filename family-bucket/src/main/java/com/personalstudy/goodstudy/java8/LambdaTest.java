package com.personalstudy.goodstudy.java8;

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

    /**
     * 四大基础接口简单demo
     */
    @Test
    public void test41(){
        System.out.println("----------------Consumer接口---------------");
        consumerMethod("今天是初七" , x -> System.out.println(x));

        System.out.println("----------------Supplier接口---------------");
        List<Integer> list = supplierMethod(10, () -> (int)(Math.random() * 100));
        System.out.println(list);

        System.out.println("----------------Function接口---------------");
        System.out.println(functionMethod("   zhangsan   \t\t\t123   465" , x -> x.trim().toUpperCase()));

        System.out.println("----------------Predicate接口---------------");
        List<String> strList = Arrays.asList("www" , "ok" , "qwer" , "zhangsan" , "list");
        // 过滤出集合中长度大于3的数据
        List<String> stringList = predicateMethod(strList, x -> x.length() > 3);
        System.out.println(stringList);
    }

    public void consumerMethod(String str , Consumer<String> con){
         con.accept(str);

    }

    public List<Integer> supplierMethod(int num , Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    public String functionMethod(String str , Function<String , String> function){
        return function.apply(str);
    }

    public List<String> predicateMethod(List<String> list , Predicate<String> pre){
        List<String> strList = new ArrayList<>();
        for (String str : list) {
            if (pre.test(str)){
                strList.add(str);
            }
        }
        return strList;
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

        // 类::实例方法名
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
        // 使用无参构造
        Function<Person , String> fun = Person::getName;
        System.out.println(fun.apply(person));
    }

    /**
     * 构造器引用
     *   格式：
     *     ClassName::new
     *   注意: 需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
     */
    @Test
    public void test9(){
        Supplier<Person> sup = Person::new;
        System.out.println(sup.get());

        System.out.println("**********************");

        BiFunction< Integer , String , User > fun = User::new;
        System.out.println(fun.apply(15, "张三"));
    }

    /**
     * 数组引用
     *   Type[]::new
     *
     *
     */
    @Test
    public void test10(){
        Function<Integer , String[]> fun = String[] :: new;
        System.out.println("数组长度：" + fun.apply(5).length);
    }
}
