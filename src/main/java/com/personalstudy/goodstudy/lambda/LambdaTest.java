package com.personalstudy.goodstudy.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

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
    @Test
    public void test3(){
        List<String> list = Arrays.asList("北京" , "南京" , "天津" , "普京" , "吴京");
        List<String> str = filterString(list, s -> s.contains("津"));
        System.out.println(str);
    }
}
