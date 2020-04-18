package com.goodstudy.springbootstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author congyaozhu
 * @date 2020-02-23 17:49
 * @description
 */
@Controller
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "123";
    }
}
