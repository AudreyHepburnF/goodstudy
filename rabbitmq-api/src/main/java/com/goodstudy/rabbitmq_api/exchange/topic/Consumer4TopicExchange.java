package com.goodstudy.rabbitmq_api.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-19 0:35
 * @description 生产者消息队列  交换机类型 - topic  (根据routeingKey进行绑定:给满足routekey进行发送)
 * exchange 将routekey和某topic进行模糊匹配，此时队列需要绑定一个topic
 * 可以使用通配符进行模糊匹配
 * # : 匹配一个或者多个词
 * * : 匹配一个
 * ecg ：  user.#   可以匹配：   user.topic1   、  user.topic2.zs
 * user.*   可以匹配：   user.topic1
 */
public class Consumer4TopicExchange {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("123.57.54.123");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_topic_exchange";
        String routeKey = "user.#";
        String exchangeType = "topic";
        String queueName = "test_topic_queue";

        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);

        channel.queueDeclare(queueName, true, false, false, null);

        channel.queueBind(queueName, exchangeName, routeKey);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("topic exchange 收到消息：" + msg);
        }

    }
}
