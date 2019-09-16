package com.personalstudy.goodstudy.lambda;

import com.personalstudy.goodstudy.base.Employee;
import com.personalstudy.goodstudy.base.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    // 筛选与切片
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

    // 映射
    @Test
    public void test3(){
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        // 将小写转大写
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        System.out.println("*****************************\n");
        List<Employee> employees = EmployeeData.getEmployees();
        // 筛选集合姓名长度小于3的员工的姓名信息
        employees.stream().map(Employee::getName).filter(name -> name.length()<3).forEach(System.out::println);

        System.out.println("*****************************\n");
        // flagMap
        list.stream().flatMap(StreamTest::fromStringToStream).forEach(System.out::println);

        System.out.println("*****************************\n");

        // 简单 根据age排序
        employees.stream().sorted((e1,e2) -> Integer.compare(e1.getAge() , e2.getAge())).forEach(System.out::println);
        System.out.println("*****************************\n");
//        执行报错，没有实现Comparable接口
//        employees.stream().sorted().forEach(System.out::println);

        System.out.println("*****************************\n");
        //根据age排序 ，age相同，根据salary进行降序排序
        employees.stream().sorted((e1 , e2 ) -> {
            int age = Integer.compare(e1.getAge(), e2.getAge());
            if(age != 0){
                return age;
            }else{
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);

    }

    // Stream 终止操作
    @Test
    public void test4(){
        // 判断是否所有员工的年龄都大于10
        List<Employee> employees = EmployeeData.getEmployees();
        System.out.println(employees.stream().allMatch(e -> e.getAge()>10));

        System.out.println("*****************************");
        // 判断是否有姓名为 猴赛雷的 员工
        System.out.println(employees.stream().anyMatch(e -> e.getName().equals("猴赛雷")));
        System.out.println("*****************************");
        // 判断是否有员工的年龄不为12
        System.out.println(employees.stream().noneMatch(e -> e.getAge()==12));

        System.out.println("*****************************\n");
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);
        System.out.println("*****************************\n");
        Optional<Employee> any = employees.stream().findAny();
        System.out.println(any);

    }

    @Test
    public void test5(){
        List<Employee> employees = EmployeeData.getEmployees();
        // 统计工资大于5000的员工个数
        System.out.println(employees.stream().filter(m -> m.getSalary()>5000).count());

        System.out.println("*****************************");
        // 统计薪资最高的员工的信息
        System.out.println(employees.stream().max((e1 , e2) -> Double.compare(e1.getSalary() , e2.getSalary())));
        System.out.println("*****************************");
        // 统计年龄最小的员工的信息
        System.out.println(employees.stream().min((e1 , e2) -> Double.compare(e1.getAge() , e2.getAge())));
    }

    // 归约
    @Test
    public void test6(){
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 求intList的和
        // 参数一：从什么开始
        System.out.println(intList.stream().reduce(0, Integer::sum));

        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> stream = employees.stream();
        // 计算所有员工的总薪资
        // 方式一
        System.out.println(stream.map(s -> s.getSalary()).reduce(Double::sum));

        System.out.println("****************************");
        // 方式二
        Stream<Double> salaryStream = employees.stream().map(s -> s.getSalary());
        System.out.println(salaryStream.reduce((s1,s2) -> s1+s2));
    }

    // collect
    @Test
    public void test7(){
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream().filter(m -> m.getSalary() > 5000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);
    }

    public  static Stream<Character> fromStringToStream(String str){
        List<Character> list = new ArrayList<>();
        for (Character c :  str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }
}
