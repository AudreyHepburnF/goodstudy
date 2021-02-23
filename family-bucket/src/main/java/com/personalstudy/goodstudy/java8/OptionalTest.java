package com.personalstudy.goodstudy.java8;

import com.personalstudy.goodstudy.base.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional容器类的常用方法:
 *      Optional.of(T t) :创建一个Optional实例
 *      Optional.empty() :创建一个空的Optional实例
 *      Optional.ofNullable(T t):若t不为nu11,创建Optional实例,否则创建空实例
 *      isPresent() :判断是否包含值
 *      orElse(T t) :如果调用对象包含值, 返回该值，否则返回t
 *      orElseGet(Supplier s) : 如果调用对象包含值，返回该值，否则返回s获取的值
 *      map(Function f):如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty()
 *      flatMap( Function mapper):与map类似，要求返回值必须是Optional
 */
public class OptionalTest {

    // 为空提示空指针
    @Test
    public void test1(){
        Optional<Employee> employee = Optional.of(null);
        System.out.println(employee.get());
    }

    // 为空提示 java.util.NoSuchElementException: No value present
    @Test
    public void test2(){
        Optional<Employee> employee = Optional.empty();
        System.out.println(employee.get());
    }

    // 为空提示 java.util.NoSuchElementException: No value present
    @Test
    public void test3(){
        Optional<Employee> employee = Optional.ofNullable(null);
        System.out.println(employee.get());
    }

    @Test
    public void test4(){
        Optional<Employee> employee = Optional.ofNullable(new Employee(1, "zhangsan", 18, 7000.0));
//        Optional<Employee> employee = Optional.ofNullable(new Employee());
        Optional<String> name = employee.flatMap(e -> Optional.of(e.getName()));
        System.out.println(name.get());
    }

    // 使用Java8 Optional新特性，处理对象为空导致的空指针问题
    @Test
    public void test5(){
        Optional<Godness> gn = Optional.ofNullable(new Godness("苍老师"));
        Optional<NewMan> nm = Optional.ofNullable(new NewMan(gn));
        String name = getGodnessName(nm);
        System.out.println(name);

    }

    private String getGodnessName(Optional<NewMan> newMan){
        return newMan.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("波多老师"))
                .getName();
    }


}
