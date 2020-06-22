package com.goodstudy.rabbitmq_api.limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author congyaozhu
 * @date 2020-06-22 23:22
 * @description
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "test_qos_exchange";
        String queueName = "test_qos_queue";
        String routingKey = "qos.#";
        //声明和绑定
        channel.exchangeDeclare(exchangeName,"topic",true,false,null);
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,routingKey);

        //1 限流方式  第一件事就是 autoAck设置为 false,每次给消费者推送一个消息
        channel.basicQos(0, 1, false);
        channel.basicConsume(queueName, false, new MyConsumer(channel));

    }
}

