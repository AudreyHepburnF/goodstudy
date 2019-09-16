package com.personalstudy.goodstudy.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("testmq");
        factory.setUsername("test");
        factory.setPassword("test");
        Connection connection = factory.newConnection();
        return connection;
    }
}
