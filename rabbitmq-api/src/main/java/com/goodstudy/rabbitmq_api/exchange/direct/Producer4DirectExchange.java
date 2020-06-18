package com.goodstudy.rabbitmq_api.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-19 0:14
 * @description  生产者消息队列  交换机类型 - direct  (根据routeingKey进行绑定:一对一直连，与exchangeName无关)
 */
public class Producer4DirectExchange {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_direct_exchange";
        String routetingKey = "test.direct123";
        String msg = "Hello World RabbitMQ 4  Direct Exchange Message  ... ";
        channel.basicPublish(exchangeName , routetingKey , null , msg.getBytes());

        channel.close();
        connection.close();
    }
}
