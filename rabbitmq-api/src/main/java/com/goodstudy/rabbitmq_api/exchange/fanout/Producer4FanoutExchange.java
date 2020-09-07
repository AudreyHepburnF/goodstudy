package com.goodstudy.rabbitmq_api.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-19 0:54
 * @description 生产者消息队列  交换机类型 - fanout  (发送到交换机的信息都会被转发到绑定该交换机的所有队列上,与routingKey无关)
 */
public class Producer4FanoutExchange {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_fanout_exchange";

        String routeingKey = "test_fanout";

        for (int i = 0; i < 10; i++) {
            String msg = "Hello World RabbitMQ 4 FANOUT Exchange Message ...";
            channel.basicPublish(exchangeName, routeingKey, null, msg.getBytes());
        }

        channel.close();
        connection.close();
    }
}
