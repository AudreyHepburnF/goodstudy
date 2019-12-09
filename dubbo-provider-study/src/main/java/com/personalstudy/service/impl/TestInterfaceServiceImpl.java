package com.personalstudy.service.impl;

import com.personalstudy.entity.User;
import com.personalstudy.service.TestInterfaceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestInterfaceServiceImpl implements TestInterfaceService {

    @Override
    public List<User> findAllUser() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId((long) i);
            user.setName("zhangsan" + i);
            user.setSex(i % 2 == 0 ? "男" : "女");
            list.add(user);
        }
        return list;
    }
}
