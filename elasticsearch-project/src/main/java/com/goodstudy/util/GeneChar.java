package com.goodstudy.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author ：congyaozhu
 * @date ：Created in 2019/9/15 0:01
 * @description： 随机生成汉字工具类
 * @version: $
 */
public class GeneChar {
    public static void main(String[] args) {
//        for (int i = 1; i < 24; i++) {
//            System.out.print(getRandomChar() + "");
//        }
        System.out.println(getRandomCharPlus());;
    }
    private static char getRandomChar() {
        String str = "";
        int hightPos; //
        int lowPos;
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }
        return str.charAt(0);
    }

    public static String getRandomCharPlus(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            builder.append(getRandomChar());
        }
        return builder.toString();
    }
}
