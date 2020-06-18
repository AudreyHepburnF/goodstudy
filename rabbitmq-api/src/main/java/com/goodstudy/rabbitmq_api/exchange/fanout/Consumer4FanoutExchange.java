package com.goodstudy.rabbitmq_api.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-19 0:54
 * @description 消费者消息队列  交换机类型 - fanout  (发送到交换机的信息都会被转发到绑定该交换机的所有队列上，与routingKey无关)
 */
public class Consumer4FanoutExchange {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String exchangeName = "test_fanout_exchange";

        String routingKey = "";

        String exchangeType = "fanout";

        String queueName = "test_fanout_queue";

        channel.exchangeDeclare(exchangeName, exchangeType , true , false , false , null);

        channel.queueDeclare(queueName,true, false, false, null);

        channel.queueBind(queueName, exchangeName, routingKey);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(queueName, true, consumer);

        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("fanout exchange 收到消息：" + msg);
        }
    }
}
