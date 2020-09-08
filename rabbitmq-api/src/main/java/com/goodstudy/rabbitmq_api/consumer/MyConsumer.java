package com.goodstudy.rabbitmq_api.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author congyaozhu
 * @date 2020-06-22 23:18
 * @description 自定义消费者 ，继承DefaultConsumer类，重写handleDelivery方法，添加自定义消费的业务逻辑
 */
public class MyConsumer extends DefaultConsumer {

    public MyConsumer(Channel channel) {
        super(channel);
    }


    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.err.println("-----------consumer message----------");

        System.err.println("consumerTag: " + consumerTag);
        System.err.println("envelope: " + envelope);
        System.err.println("properties: " + properties);
        System.err.println("body: " + new String(body));
    }
}
