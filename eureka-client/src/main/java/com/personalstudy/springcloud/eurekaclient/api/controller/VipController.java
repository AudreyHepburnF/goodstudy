package com.personalstudy.springcloud.eurekaclient.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员服务
 */
@RestController
public class VipController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/getInfo")
    public String getInfo(){
        return  "this is a eureka-client demo ， 端口号为：" + serverPort ;
    }
}
