package com.goodstudy.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author congyaozhu
 * @date 2020-01-29 20:18
 * @description
 */
public class Test1 {
    public static void main(String[] args) {

        // 方式一：将数组转换为可操作的List
//        List list = new ArrayList<>(Arrays.asList("a", "b", "c"));

        String [] s= new String[]{
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
        };
        List<String> list = Arrays.asList(s);
        Collections.reverse(list);
        s=list.toArray(new String[0]);//没有指定类型的话会报错
    }

    /**
     * 使用Guava将数组转换为可以操作的List集合
     */
    private static void topic03() {
//        List<String> l1 = Lists.newArrayList(anotherListOrCollection);    // from collection
//        List<String> l2 = Lists.newArrayList(aStringArray);               // from array
//        List<String> l3 = Lists.newArrayList("or", "string", "elements"); // from varargs
    }

    /**
     * 使用Java1.8的stream将数组转换为可以操作的List集合
     */
    private static void topic02() {
        Integer[] myArray = {1, 2, 3};
        List myList = Arrays.stream(myArray).collect(Collectors.toList());
        System.out.println(myList);
        //基本类型也可以实现转换（依赖boxed的装箱操作）
        int[] myArray2 = {1, 2, 3};
        List myList2 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
        System.out.println(myList2);
    }

    /**
     * 注意Arrays.asList方法
     * 传递的数组必须是对象数组，而不是基本类型。
     */
    private static void topic01() {
        int[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size());//1
        System.out.println(myList.get(0));//数组地址值
        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
        int[] array = (int[]) myList.get(0);
        System.out.println(array[0]);//1
    }

    //JDK1.5+

    /**
     * 数组转List(教育目的)
     *
     * @param array
     * @param <T>
     * @return
     */
    static <T> List<T> arrayToList(final T[] array) {
        final List<T> l = new ArrayList<T>(array.length);

        for (final T s : array) {
            l.add(s);
        }
        return (l);
    }
}
