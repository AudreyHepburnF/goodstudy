package com.goodstudy.rabbitmq_api.returnlistener;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author congyaozhu
 * @date 2020-06-20 10:23
 * @description return 消息机制  生产者
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 创建一个ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        // 2. 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        // 3. 通过connectionFactory创建一个Channel
        Channel channel = connection.createChannel();

        String exchangeName = "test_return_exchange";
        String routingKey = "return.save";
        String routingKeyError = "abc.save";

        //5 发送一条消息
        String msg = "Hello RabbitMQ Send return message!";

        // 发送正确的routingKey,并设置mandatory为false(默认)
        // Mandatory:如果为true，则监听器会接收到路由不可达的消息，然后进行后续处理，如果为false,那么broker端自动删除该消息!
//        channel.basicPublish(exchangeName, routingKey, false,null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKeyError, true, null, msg.getBytes());

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println("---------handle  return----------");
                System.err.println("replyCode: " + replyCode);
                System.err.println("replyText: " + replyText);
                System.err.println("exchange: " + exchange);
                System.err.println("routingKey: " + routingKey);
                System.err.println("properties: " + properties);
                System.err.println("body: " + new String(body));
            }
        });
    }
}
