package com.personalstudy.goodstudy.demospring;

import com.personalstudy.goodstudy.demospring.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoSpringApplication {

    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(DemoSpringApplication.class, args);
        User user = act.getBean(User.class);
        System.out.println(user);
    }

}
