package com.goodstudy.rabbitmq_api.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-18 22:45
 * @description 生产者队列
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 创建一个ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("123.57.54.123");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 2. 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();

        // 3. 通过connectionFactory创建一个Channel
        Channel channel = connection.createChannel();

        // 4. 通过Channel发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "Hello RabbitMQ";
            channel.basicPublish("", "test001", null, msg.getBytes());
        }
        channel.close();
        connection.close();
    }
}
