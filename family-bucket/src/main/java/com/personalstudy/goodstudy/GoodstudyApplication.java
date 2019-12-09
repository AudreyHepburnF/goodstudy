package com.personalstudy.goodstudy;

import com.personalstudy.goodstudy.base.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GoodstudyApplication {

    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(GoodstudyApplication.class, args);
        User bean = act.getBean(User.class);
        System.out.println(bean);
    }

}
