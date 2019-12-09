package com.personalstudy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext aptx = new ClassPathXmlApplicationContext("provider.xml");
        aptx.start();

        System.in.read();
    }
}
