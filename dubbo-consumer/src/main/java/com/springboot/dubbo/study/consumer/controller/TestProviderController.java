package com.springboot.dubbo.study.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.personalstudy.entity.User;
import com.personalstudy.service.TestInterfaceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestProviderController {

    @Reference
    private TestInterfaceService testInterfaceService;

    @RequestMapping("/test")
    public List<User> getAllUser(){
        return testInterfaceService.findAllUser();
    }
}
