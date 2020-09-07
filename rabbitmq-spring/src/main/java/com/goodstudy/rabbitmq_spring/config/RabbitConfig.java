package com.goodstudy.rabbitmq_spring.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author congyaozhu
 * @date 2020-06-26 18:10
 * @description
 */
@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("127.0.0.1:5672");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        // 必须设置为true
        admin.setAutoStartup(true);
        return admin;
    }

    /**
     *  针对消费者配置
     *  1. 设置交换机类型
     *  2. 将队列绑定到交换机
     *      FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     *      HeadersExchange： 通过添加属性key-value 匹配
     *      DirectExchange： 按照routingkey分发到指定队列
     *      TopicExchange： 多关键字匹配
     */

    @Bean
    public TopicExchange exchange001(){
        return new TopicExchange("topic001", true, false);
    }

    @Bean
    public org.springframework.amqp.core.Queue queue001(){
        return new Queue("queue001" , true);
    }

    @Bean
    public Binding binding001(){
        return BindingBuilder.bind(queue001()).to(exchange001()).with("spring.*");
    }

    @Bean
    public TopicExchange exchange002(){
        return new TopicExchange("topic002", true, false);
    }

    @Bean
    public org.springframework.amqp.core.Queue queue002(){
        return new Queue("queue002" , true);
    }

    @Bean
    public Binding binding002(){
        return BindingBuilder.bind(queue002()).to(exchange002()).with("rabbit.*");
    }

    @Bean
    public TopicExchange exchange003(){
        return new TopicExchange("topic003", true, false);
    }

    @Bean
    public org.springframework.amqp.core.Queue queue003(){
        return new Queue("queue003" , true);
    }

    @Bean
    public Binding binding003(){
        return BindingBuilder.bind(queue003()).to(exchange003()).with("mq.*");
    }

    @Bean
    public org.springframework.amqp.core.Queue queue_image(){
        return new Queue("queue_image" , true);
    }

    @Bean
    public org.springframework.amqp.core.Queue queue_pdf(){
        return new Queue("queue_pdf" , true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    /**
     * 简单消息监听容器
     *      设置事务特性、事务管理器、事务属性、事务容量(并发)、是否开启事务、回滚消息等
     *      设置消费者数量、最小最大数量、批量消费
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(5);
        container.setDefaultRequeueRejected(false);
        container.setQueues(queue001(),queue002(),queue003(),queue_image(),queue_pdf());
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setExposeListenerChannel(true);

        // 设置消费者tag
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
            // queue : 队列名称
            @Override
            public String createConsumerTag(String queue) {
                return queue + "_" + UUID.randomUUID().toString();
            }
        });

        // 设置消息监听
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                String msg = new String(message.getBody());
                System.out.println("------------消费者监听服务： " + msg);
            }
        });
        return container;
    }
}
