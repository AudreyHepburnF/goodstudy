package com.personalstudy.consumer;

import com.personalstudy.entity.User;
import com.personalstudy.service.TestInterfaceService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class DubboConsumerApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(DubboConsumerApplication.class, args);
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        TestInterfaceService testInterfaceService = applicationContext.getBean(TestInterfaceService.class);
        List<User> allUser = testInterfaceService.findAllUser();
        for (User user : allUser) {
            System.out.println(user.getName());
        }


        System.out.println("调用结束");
        System.in.read();
    }
}
