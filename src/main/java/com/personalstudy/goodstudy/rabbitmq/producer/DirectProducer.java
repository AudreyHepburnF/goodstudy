package com.personalstudy.goodstudy.rabbitmq.producer;

import com.personalstudy.goodstudy.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * direct 模式的生产者
 */
public class DirectProducer {

    public static String EXCHANGE_NAME = "testmq";
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionUtil.getConnection();
            // 创建管道
            Channel channel = connection.createChannel();
            // 设置交换机参数
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            String[] servertities = {"error" , "warning" , "info"};
            for (int i = 0; i < servertities.length; i++) {
                String routing = servertities[i];
                String msg = "Hello RabbitMQ" + i;
                // 发布消息
                channel.basicPublish(EXCHANGE_NAME , routing , null , msg.getBytes());
            }


            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
