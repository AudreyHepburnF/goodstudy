package com.springboot.dubbo.study.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.personalstudy.entity.User;
import com.personalstudy.service.TestInterfaceService;

import java.util.ArrayList;
import java.util.List;

@Service
@org.springframework.stereotype.Service
public class TestProviderService implements TestInterfaceService{

    @Override
    public List<User> findAllUser() {
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            User user = new User();
            user.setId((long) i);
            user.setName("lisi" + i);
            user.setSex(i % 2 == 0 ? "男" : "女");
            list.add(user);
        }
        return list;
    }
}
