package com.personalstudy.goodstudy.lambda;

import com.personalstudy.goodstudy.base.Employee;
import com.personalstudy.goodstudy.base.EmployeeData;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  15:34 2019-08-08
 * @Description : Stream API 操作案例
 */
public class StreamTest {

    // 从0开始，累计加2，输出十次
    @Test
    public  void test1(){
        Stream.iterate(0, t -> t+2).limit(10).forEach(System.out::println);
    }

    @Test
    public void test2(){
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> stream = employees.stream();
        // 过滤薪资大于7000的员工
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);

        System.out.println("********************************");

        // 截取前三个
        employees.stream().limit(3).forEach(System.out::println);

        System.out.println("********************************");

        // 忽略前三个
        employees.stream().skip(3).forEach(System.out::println);

        System.out.println("********************************");

        employees.add(new Employee(10010, "张三", 25, 5000));
        employees.add(new Employee(10010, "张三", 25, 5000));
        employees.add(new Employee(10010, "张三", 25, 5000));
        employees.add(new Employee(10010, "张三", 25, 5000));
        employees.add(new Employee(10010, "张三", 25, 5001));
        employees.stream().distinct().forEach(System.out::println);
    }
}
