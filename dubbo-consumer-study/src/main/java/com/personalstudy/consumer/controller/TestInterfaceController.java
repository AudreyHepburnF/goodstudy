package com.personalstudy.consumer.controller;

import com.personalstudy.entity.User;
import com.personalstudy.service.TestInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 使用dubbo，远程调用dubbo接口
 */
@Service
public class TestInterfaceController {

    @Autowired
    private TestInterfaceService testInterfaceService;

    public List<User> getAll(){
        return testInterfaceService.findAllUser();
    }

}
