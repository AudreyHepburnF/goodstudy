package com.goodstudy.rabbitmq_api.limit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author congyaozhu
 * @date 2020-06-22 23:22
 * @description 自定义消费者，进行手动确认消息
 */
public class MyConsumer extends DefaultConsumer {

    private Channel channel;

    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.err.println("-----------consume message----------");
        System.err.println("consumerTag: " + consumerTag);
        System.err.println("envelope: " + envelope);
        System.err.println("properties: " + properties);
        System.err.println("body: " + new String(body));

        //确认消息的方法，回调成功以后再执行下一条，表示这条消息我已经处理完了，你可以给我下一条了。multiple：false表示不批量签收
        channel.basicAck(envelope.getDeliveryTag(), false);

    }
}

