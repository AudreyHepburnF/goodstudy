package com.personalstudy.goodstudy.java8;

import com.personalstudy.goodstudy.base.Employee;
import com.personalstudy.goodstudy.base.EmployeeData;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  15:34 2019-08-08
 * @Description : Stream API 操作案例
 *
 * Stream的三个操作步骤：
 *  1. 创建Stream
 *  2. 中间操作
 *  3. 终止操作(终端操作)
 *
 * 1. 通过Collection系列集合提供的stream() 或 parallelStream()
 * 2. 通过Arrays中的静态方法 stream()获取数组流
 * 3. 通过Stream类中的静态方法 of()
 * 4. 创建无限流
 */
public class StreamTest {

    @Test
    public  void test1(){
        // 无限流，迭代。按照一元运算的规则，从0开始，累计加2，获取十个，打印输出
        Stream.iterate(0, t -> t+2).limit(10).forEach(System.out::println);

        System.out.println("-------------");

        // 无限流，生成5个[0,1)的随机数，打印输出
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
    }

    /**
     * 中间操作
     * 不会执行任何操作，只有进行终止操作，才能一次性执行全部内容，即"惰性求值"
     * 内部迭代:迭代操作由Stream API完成
     *
     *  filter: 接收Lambda，从流中排除某些元素
     *  limit: 截断流，使其元素不超过给定数量
     *  skip(n): 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
     *  distinct: 筛选，通过流所生成元素的hashCode() 和 equals() 去除重复元素
     *
     *
     * @throws InterruptedException
     */
    // 筛选与切片
    @Test
    public void test2() throws InterruptedException {
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

        employees.add(new Employee(10010, "张三", 25, 5000.0));
        employees.add(new Employee(10010, "张三", 25, 5000.0));
        employees.add(new Employee(10010, "张三", 25, 5000.0));
        employees.add(new Employee(10010, "张三", 25, 5000.0));
        employees.add(new Employee(10010, "张三", 25, 5001.0));

        // 需要实现equals() 和 hashCode()
        employees.stream().distinct().forEach(System.out::println);

        employees.wait();
        synchronized (employees){
            employees.wait();
        }
    }


    /**
     * 映射
     * map: 接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数， 该函数会被应用到每个元素上,并将其映射成一个新的元素。
     * flatMap: 接收一个函数作为参数, 将流中的每个值都换成另-个流,然后把所有流连接成一个流
     *
     * 排序
     *  sorted(): 自然排序
     *  sorted(Comparator com): 定制排序
     */
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

        // flatMap
        list.stream().flatMap(StreamTest::fromStringToStream).forEach(System.out::println);

        System.out.println("*****************************\n");

        // 简单 根据age排序
        employees.stream().sorted(Comparator.comparingInt(Employee::getAge)).forEach(System.out::println);
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

    /**
     * Stream 终止操作
     *
     * 查找与匹配
     *  allMatch-检 查是否匹配所有元素
     *  anyMatch-检 查是否至少匹配- -个元素
     *  noneMatch-检查 是否没有匹配所有元素
     *  findFirst-返回第 -个元素
     *  findAny-返回当前流中的任意元素
     *  count-返 回流中元素的总个数
     *  max-返回流中最大值
     *  min-返回流中最小值
     */
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
        // 获取集合的第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first.get());
        System.out.println("*****************************\n");
        // 返回任意一个
        Optional<Employee> any = employees.stream().findAny();
        System.out.println(any.get());
    }

    @Test
    public void test5(){
        List<Employee> employees = EmployeeData.getEmployees();
        // 统计工资大于5000的员工个数
        System.out.println("统计工资大于5000的员工个数 " + employees.stream().filter(m -> m.getSalary()>5000).count());
        System.out.println("统计员工薪水不为空的个数" + employees.stream().map(Employee::getSalary).filter(Objects::nonNull).count());
        System.out.println("*****************************");
        // 统计薪资最高的员工的信息
        System.out.println("统计薪资最高的员工的信息 " + employees.stream().max(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println("*****************************");
        // 统计年龄最小的员工的信息
        System.out.println("统计年龄最小的员工的信息 " + employees.stream().min(Comparator.comparingDouble(Employee::getAge)));

        // 统计年龄最小的员工的薪资额
        System.out.println("年龄最小的员工的薪资额 " + employees.stream().map(Employee::getSalary).min(Double::compare));
    }

    // 归约

    /**
     * 归约
     *  reduce(T identity, BinaryOperator) / reduce(BinaryOperator) : 可以将流中元素反复结合起来，得到一个值。
     */
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

    /**
     * 收集
     *  collect: 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test7(){
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream().filter(m -> m.getSalary() > 5000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);

        // 根据年龄进行分组
        Map<Integer, List<Employee>> groupByAge = employees.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(groupByAge);

        // 分区
        Map<Boolean, List<Employee>> partitionBySalary = employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
        System.out.println(partitionBySalary);


        // 获取员工薪水状态信息。主要用于收集统计信息，例如计数，最小值，最大值，总和，平均值
        DoubleSummaryStatistics doubleSummaryStatistics = employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("薪资最高为：" + doubleSummaryStatistics.getMax());
        System.out.println("薪资平均值为：" + doubleSummaryStatistics.getAverage());
        System.out.println("发薪资数：" + doubleSummaryStatistics.getCount());
        System.out.println("薪资最低为：" + doubleSummaryStatistics.getMin());
        System.out.println("薪资总和：" + doubleSummaryStatistics.getSum());

        // 拼接所有员工的姓名
        String joinName = employees.stream().map(Employee::getName).collect(Collectors.joining());
        System.out.println(joinName);

        // 拼接所有员工的姓名,按逗号分隔。自动去除首位逗号
        String joinNameByDelimiter = employees.stream().map(Employee::getName).collect(Collectors.joining(","));
        System.out.println(joinNameByDelimiter);

    }

    public  static Stream<Character> fromStringToStream(String str){
        List<Character> list = new ArrayList<>();
        for (Character c :  str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test8(){
        List<Employee> employees = EmployeeData.getEmployees();

        // 根据年龄排序，如果年龄相同则根据薪资排序
        employees.stream().sorted((x , y ) -> {
            if(x.getAge().equals(y.getAge())){
                return -x.getSalary().compareTo(y.getSalary());
            }else{
                return x.getAge().compareTo(y.getAge());
            }
        }).forEach(System.out::println);

    }

    // 使用reduce求所有员工的薪资
    @Test
    public void test9(){
        List<Employee> employees = EmployeeData.getEmployees();
        Optional<Double> reduce = employees.stream()
                .map(x -> x.getSalary())
                .reduce(Double::sum);
        System.out.println(reduce.get());
    }

    // collect : 收集
    @Test
    public void test10(){
        List<Employee> employees = EmployeeData.getEmployees();
        employees.add(new Employee(5, "马云", 25, 66666.66));
        System.out.println("-------List------");
        List<String> toList = employees.stream()
                .map(x -> x.getName())
                .collect(Collectors.toList());
        System.out.println(toList);

        System.out.println("-------Set------");
        Set<String> toSet = employees.stream()
                .map(x -> x.getName())
                .collect(Collectors.toSet());
        System.out.println(toSet);

        System.out.println("-------HashSet------");
        HashSet<String> toHashSet = employees.stream()
                .map(x -> x.getName())
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(toHashSet);
    }

    /**
     * 并行流
     */
    @Test
    public void test11(){
        Instant start = Instant.now();

        long sum = LongStream.rangeClosed(0, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("求得结果为：" + sum +", 耗时：" + Duration.between(start, end).toMillis());
    }

}
