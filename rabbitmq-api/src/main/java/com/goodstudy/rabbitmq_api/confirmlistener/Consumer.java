package com.goodstudy.rabbitmq_api.confirmlistener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-20 10:23
 * @description 消费者消费消息
 * 交换机：test_confirm_exchange
 * 路由key：confirm.#
 * 队列名称：test_confirm_queue
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 1. 创建一个ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("123.57.54.123");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        // 2. 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        // 3. 通过connectionFactory创建一个Channel
        Channel channel = connection.createChannel();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.#";
        String queueName = "test_confirm_queue";

        // 声明交换机和队列 然后进行绑定设置，最后指定路由key
        channel.exchangeDeclare(exchangeName, "topic", true, false, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        // 创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String body = new String(delivery.getBody());
            System.err.println("消费端: " + body);
        }

    }
}
