package com.personalstudy.goodstudy;

import com.personalstudy.goodstudy.base.Employee;
import com.personalstudy.goodstudy.base.Person1;

import java.lang.reflect.Field;

/**

 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  14:39 2019-08-15
 * @Description :
 */
public class Test1 {
    public static void main(String[] args) throws NoSuchFieldException {
//        long num = SnowflakeIdWorker.init().nextId();0
//        System.out.println(num);
        method2();
    }

    public static void method1(){
        Integer i01=59;
        int i02=59;
        Integer i03=Integer.valueOf(59);
        Integer i04=new Integer(59);
        System.out.println(i01 == i02);
        System.out.println(i01 == i03);
        System.out.println(i03 == i04);
        System.out.println(i02 == i04);
    }

    public static void method2() throws NoSuchFieldException {
        int a = 10;
        Integer b = 10;
        Class<Employee> employeeClass = Employee.class;
        Field name = employeeClass.getField("name");
        Field name1 = employeeClass.getDeclaredField("salary");
        Field[] name2 = employeeClass.getDeclaredFields();
        Field[] fields = employeeClass.getFields();
        for (Field f : fields){
            System.out.println(f.getName());
        }
        System.out.println();
    }

    public static void method3(){
        Person1 person = new Person1.Builder("zs" , 10).build();
    }
}
