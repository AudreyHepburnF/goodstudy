package com.personalstudy.goodstudy.springaop.service.impl;

import com.personalstudy.goodstudy.springaop.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  16:52 2019-08-12
 * @Description :
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public void login() {
        System.out.println("UserServiceImpl .....  login");
    }
}
