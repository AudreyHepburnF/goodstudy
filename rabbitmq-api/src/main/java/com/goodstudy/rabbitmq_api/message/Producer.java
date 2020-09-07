package com.goodstudy.rabbitmq_api.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-19 20:35
 * @description 传递消息及定义属性实例
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 创建一个ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        // 2. 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        // 3. 通过connectionFactory创建一个Channel
        Channel channel = connection.createChannel();

        Map<String, Object> map = new HashMap<>();
        map.put("mykey1", "111");
        map.put("mykey2", "222");

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                // 设置消息编码
                .contentEncoding("UTF-8")
                // deliveryMode ：  1 -> 自动删除  2 -> 持久化
                .deliveryMode(2)
                // 设置消息过期时间，超过指定时间后消息自动删除
                .expiration("10000")
                // 自定义headers信息
                .headers(map)
                .build();


        // 4. 通过Channel发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "Hello RabbitMQ";
            channel.basicPublish("", "test001", properties, msg.getBytes());
        }
        channel.close();
        connection.close();
    }
}
