package com.personalstudy.multireadwrite.readwrite.service;

public interface TestService {

    void insertOne(Long id);

    void updateOne(Long id);

    void selectOne(Long id);

    void deleteOne(Long id);

    void getToken(Long id);
}
