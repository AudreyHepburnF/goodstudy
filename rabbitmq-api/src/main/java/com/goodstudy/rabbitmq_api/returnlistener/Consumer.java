package com.goodstudy.rabbitmq_api.returnlistener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-20 10:23
 * @description return 消息机制  消费者
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 1. 创建一个ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        // 2. 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        // 3. 通过connectionFactory创建一个Channel
        Channel channel = connection.createChannel();

        String exchangeName = "test_return_exchange";
        String routingKey = "return.#";
        String queueName = "test_return_queue";

        channel.exchangeDeclare(exchangeName, "topic", true, false, false, null);
        channel.queueDeclare(queueName , true , false , false , null);
        channel.queueBind(queueName, exchangeName, routingKey);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(queueName, true, consumer);

        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String body = new String(delivery.getBody());
            System.err.println("消费端: " + body);
        }

    }
}
