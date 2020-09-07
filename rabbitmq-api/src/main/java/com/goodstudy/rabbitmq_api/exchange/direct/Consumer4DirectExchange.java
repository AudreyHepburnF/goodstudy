package com.goodstudy.rabbitmq_api.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-19 0:21
 * @description 消费者消息队列  交换机类型 - direct  (根据routingKey进行绑定:一对一直连，与exchangeName无关)
 */
public class Consumer4DirectExchange {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("123.57.54.123");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 是否可以自动重连
        connectionFactory.setAutomaticRecoveryEnabled(true);
        // 自动重连的时间，3秒
        connectionFactory.setNetworkRecoveryInterval(3000);

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "test_direct_exchange";
        String routeingKey = "test.direct";
        String queueName = "test_direct_queue";
        String exchangeType = "direct";

        // 声明一个交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);

        // 声明一个队列
        channel.queueDeclare(queueName, true, false, false, null);

        // 建立一个绑定关系
        channel.queueBind(queueName, exchangeName, routeingKey);
        // durable : 是否持久化消息
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 参数 : 队列名称、是否自动ack、
        channel.basicConsume(queueName, true, queueingConsumer);
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("direct exchange 收到消息：" + msg);
        }
    }
}
