package com.goodstudy.rabbitmq_api.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-19 0:34
 * @description 生产者消息队列  交换机类型 - topic  (根据routeingKey进行绑定:给满足routekey进行发送)
 * exchange 将routekey和某topic进行模糊匹配，此时队列需要绑定一个topic
 * 可以使用通配符进行模糊匹配
 * # : 匹配一个或者多个词
 * * : 匹配一个
 * ecg ：  user.#   可以匹配：   user.topic1   、  user.topic2.zs
 *         user.*   可以匹配：   user.topic1
 */
public class Producer4TopicExchange {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_topic_exchange";

        String routingKey1 = "user.save";
        String routingKey2 = "user.update";
        String routingKey3 = "user.delete.abc";

        String msg = "Hello World RabbitMQ 4 Topic Exchange Message ...";

        channel.basicPublish(exchangeName, routingKey1, null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKey2, null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKey3, null, msg.getBytes());

        channel.close();
        connection.close();
    }
}
