package com.goodstudy.rabbitmq_api.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-20 10:23
 * @description 死信队列  消费者
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();


        String exchangeName = "test_dlx_exchange";
        String routingKey = "dlx.#";
        String queueName = "test_dlx_queue";

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "dlx.exchange");
        channel.exchangeDeclare(exchangeName, "topic", true, false, null);
        //这个agruments属性，要设置到声明队列上
        channel.queueDeclare(queueName, true, false, false, arguments);
        channel.queueBind(queueName, exchangeName, routingKey);

        // 死信队列声明
        channel.exchangeDeclare("dlx.exchange", "topic", true, false, null);
        channel.queueDeclare("dlx.queue", true, false, false, null);
        channel.queueBind("dlx.queue", "dlx.exchange", "#");

        channel.basicConsume(queueName, true, new MyConsumer(channel));

    }
}
