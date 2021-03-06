package com.goodstudy.rabbitmq_api.confirmlistener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-20 10:23
 * @description 使用消息确认模式发送一条消息
 * 交换机：test_confirm_exchange
 * 路由key：confirm.save
 * channel.addConfirmListener(new ConfirmListener() {
 * }
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

        // 4. 消息确认模式
        channel.confirmSelect();

        String exchangeName = "test_confirm_exchange";
        String routingKey = "confirm.save";

        //5 发送一条消息
        String msg = "Hello RabbitMQ Send confirm message!";

        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        channel.addConfirmListener(new ConfirmListener() {
            /**
             * deliveryTag : 消息唯一标签
             * @param deliveryTag
             * @param multiple
             * @throws IOException
             */
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息确认后执行的内容");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息接收失败后执行的内容");
            }
        });

        channel.close();
        connection.close();
    }
}
