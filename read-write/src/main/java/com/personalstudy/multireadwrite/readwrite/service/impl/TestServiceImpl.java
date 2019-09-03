package com.personalstudy.multireadwrite.readwrite.service.impl;

import com.personalstudy.multireadwrite.readwrite.annotation.Master;
import com.personalstudy.multireadwrite.readwrite.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements TestService {

    @Transactional
    @Override
    public void insertOne(Long id) {
        System.out.println("插入一条数据");
    }

    @Override
    public void updateOne(Long id) {
        System.out.println("更新一条数据");
    }

    @Override
    public void selectOne(Long id) {
        System.out.println("查询一条数据");
    }

    @Override
    public void deleteOne(Long id) {
        System.out.println("删除一条数据");
    }

    @Master
    @Override
    public void getToken(Long appId) {
        // 有些读操作必须读主数据库
        // 比如，获取微信access_token，因为高峰时期主从同步可能延迟
        // 这种情况下就必须强制从主数据读
        System.nanoTime();
        System.out.println("获取token");
    }
}
