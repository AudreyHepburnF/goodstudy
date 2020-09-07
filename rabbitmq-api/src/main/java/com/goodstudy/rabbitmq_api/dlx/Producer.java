package com.goodstudy.rabbitmq_api.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-22 23:17
 * @description 死信队列  生产端代码
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("123.57.54.123");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String exchange = "test_dlx_exchange";
        String routingKey = "dlx.save";

        String msg = "Hello RabbitMQ DLX Message";

        for (int i = 0; i < 1; i++) {
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .expiration("10000")
                    .build();

            channel.basicPublish(exchange, routingKey, true, properties, msg.getBytes());
        }
    }
}
