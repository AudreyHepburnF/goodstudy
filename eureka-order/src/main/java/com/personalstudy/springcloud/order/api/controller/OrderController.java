package com.personalstudy.springcloud.order.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getOrder")
    public String getOrder(){
        String result = restTemplate.getForObject("http://eureka-client:8080/getInfo", String.class);
        return result;
    }
}
