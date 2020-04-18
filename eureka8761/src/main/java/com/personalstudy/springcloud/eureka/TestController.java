package com.personalstudy.springcloud.eureka;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author congyaozhu
 * @date 2020-01-14 9:15
 * @description
 */
@RestController
public class TestController {

    @PostMapping("/book")
    public void addBook(@ModelAttribute("b") Book book , @ModelAttribute("a") Author author) {
        System.out.println(book);
        System.out.println(author);
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        Map<String, Object> map = model.asMap();
        System.out.println(map);
        int i = 1 / 0;
        return "hello controller advice";
    }
}
