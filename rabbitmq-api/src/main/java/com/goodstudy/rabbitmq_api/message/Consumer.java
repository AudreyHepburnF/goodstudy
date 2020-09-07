package com.goodstudy.rabbitmq_api.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-18 22:45
 * @description 传递消息及定义属性实例
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

        // 4. 声明(创建)一个队列
        String queueName = "test001";
        channel.queueDeclare(queueName, true, false, false, null);
        // 5. 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 6. 设置Channel
        channel.basicConsume(queueName, true, queueingConsumer);

        // 7. 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("消费端：" + msg);
            Map<String, Object> headers = delivery.getProperties().getHeaders();
            System.out.println("headers get my1 value: " + headers.get("mykey1"));
//            Envelope envelope = delivery.getEnvelope();
        }
    }
}
