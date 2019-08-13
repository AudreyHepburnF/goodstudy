package com.personalstudy.goodstudy.springaop.controller;

import com.personalstudy.goodstudy.springaop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  16:50 2019-08-12
 * @Description :
 */
@RestController
public class LoginController {

    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;

    @RequestMapping("/login")
    public Boolean login(){
        userService.login();
        return false;
    }
}
