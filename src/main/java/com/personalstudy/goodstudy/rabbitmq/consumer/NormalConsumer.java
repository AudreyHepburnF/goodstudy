package com.personalstudy.goodstudy.rabbitmq.consumer;

import com.personalstudy.goodstudy.rabbitmq.producer.DirectProducer;
import com.personalstudy.goodstudy.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class NormalConsumer {

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(DirectProducer.EXCHANGE_NAME , BuiltinExchangeType.DIRECT);

        // 队列名
        String queueName = "forcuserror";
        // 声明队列
        channel.queueDeclare(queueName , false , false , false , null);

        // 定义路由键
        String routekey = "error";
        // 绑定队列
        channel.queueBind(queueName , DirectProducer.EXCHANGE_NAME , routekey);

        System.out.println("waiting for message ... ");

        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Receive["+ envelope.getRoutingKey() +"]" + message);
            }
        };
        // 消费消息
        channel.basicConsume(queueName, true,consumer);
    }
}
