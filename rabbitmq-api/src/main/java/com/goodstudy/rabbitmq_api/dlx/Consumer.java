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
 * 产生死信消息的几个条件：
 *      1. 消息被拒绝(basic.reject/ basic.nack)并且requeue=false
 *      2. 消息TTL过期
 *      3. 队列达到最大长度
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("123.57.54.123");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "test_dlx_exchange";
        String routingKey = "dlx.#";
        String queueName = "test_dlx_queue";

        Map<String, Object> arguments = new HashMap<>();
        // 设置死信队列必须参数
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
