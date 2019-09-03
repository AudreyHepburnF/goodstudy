package com.personalstudy.goodstudy;

import com.personalstudy.goodstudy.util.SnowflakeIdWorker;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  14:39 2019-08-15
 * @Description :
 */
public class Test1 {
    public static void main(String[] args) {
        long num = SnowflakeIdWorker.init().nextId();
        System.out.println(num);
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
}
