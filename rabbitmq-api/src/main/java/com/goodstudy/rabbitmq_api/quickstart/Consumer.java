package com.goodstudy.rabbitmq_api.quickstart;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-18 22:45
 * @description 消费者队列
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        // 1. 创建一个ConnectionFactory，并进行配置
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

        /**
         * queue : 队列名称
         * durable : 是否持久化
         * exclusive : 是否独占(true:独占) ecg：保证顺序消费，多生产者情况下顺序执行
         * autoDelete : 是否自动删除队列 ，为true时，服务器将删除它时，不再使用
         * arguments : 队列的其他属性(构造参数)
         */
        channel.queueDeclare(queueName, true, false, false, null);

        // 5. 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        // 6. 设置Channel
        channel.basicConsume(queueName, true, queueingConsumer);

        // 7. 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            /**
             * delivery.getBody() : 消息内容
             * delivery.getProperties() : 扩展属性
             * delivery.getEnvelope() : 获取环境属性
             * envelope.getDeliveryTag()
             */
            String msg = new String(delivery.getBody());
            System.out.println("消费端：" + msg);
//            Envelope envelope = delivery.getEnvelope();
        }
    }
}
