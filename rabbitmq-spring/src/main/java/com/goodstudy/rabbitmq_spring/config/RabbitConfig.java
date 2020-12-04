package com.goodstudy.rabbitmq_spring.config;

import com.goodstudy.rabbitmq_spring.adapter.MessageDelegate;
import com.goodstudy.rabbitmq_spring.convert.ImageMessageConverter;
import com.goodstudy.rabbitmq_spring.convert.PDFMessageConverter;
import com.goodstudy.rabbitmq_spring.convert.TextMessageConverter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author congyaozhu
 * @date 2020-06-26 18:10
 * @description
 */
@Configuration
@ComponentScan({"com.goodstudy.rabbitmq_spring"})
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("123.57.54.123:5672");
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
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(queue001(), queue002(), queue003(), queue_image(), queue_pdf());
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(5);
        container.setDefaultRequeueRejected(false);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setExposeListenerChannel(true);
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String queue) {
                return queue + "_" + UUID.randomUUID().toString();
            }
        });
        /**
             container.setMessageListener(new ChannelAwareMessageListener() {
                @Override
                public void onMessage(Message message, Channel channel) throws Exception {
                    String msg = new String(message.getBody());
                    System.err.println("----------消费者: " + msg);
                }
            });
         */

        /**
         * 1 适配器方式. 默认是有自己的方法名字的：handleMessage
         // 可以自己指定一个方法的名字: consumeMessage
         // 也可以添加一个转换器: 从字节数组转换为String
             MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
             adapter.setDefaultListenerMethod("consumeMessage");
             adapter.setMessageConverter(new TextMessageConverter());
             container.setMessageListener(adapter);
         */

        /**
         * 2 适配器方式: 我们的队列名称 和 方法名称 也可以进行一一的匹配
         *
             MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
             adapter.setMessageConverter(new TextMessageConverter());
             Map<String, String> queueOrTagToMethodName = new HashMap<>();
             queueOrTagToMethodName.put("queue001", "method1");
             queueOrTagToMethodName.put("queue002", "method2");
             adapter.setQueueOrTagToMethodName(queueOrTagToMethodName);
             container.setMessageListener(adapter);
         */

        // 1.1 支持json格式的转换器
        /**
             MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
             adapter.setDefaultListenerMethod("consumeMessage");

             Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
             adapter.setMessageConverter(jackson2JsonMessageConverter);

             container.setMessageListener(adapter);
         */



        // 1.2 DefaultJackson2JavaTypeMapper & Jackson2JsonMessageConverter 支持java对象转换
        /**
             MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
             adapter.setDefaultListenerMethod("consumeMessage");

             Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();

             DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
             jackson2JsonMessageConverter.setJavaTypeMapper(javaTypeMapper);

             adapter.setMessageConverter(jackson2JsonMessageConverter);
             container.setMessageListener(adapter);
         */


        //1.3 DefaultJackson2JavaTypeMapper & Jackson2JsonMessageConverter 支持java对象多映射转换
        /**
             MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
             adapter.setDefaultListenerMethod("consumeMessage");
             Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
             DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();

             Map<String, Class<?>> idClassMapping = new HashMap<String, Class<?>>();
             idClassMapping.put("order", com.bfxy.spring.entity.Order.class);
             idClassMapping.put("packaged", com.bfxy.spring.entity.Packaged.class);

             javaTypeMapper.setIdClassMapping(idClassMapping);

             jackson2JsonMessageConverter.setJavaTypeMapper(javaTypeMapper);
             adapter.setMessageConverter(jackson2JsonMessageConverter);
             container.setMessageListener(adapter);
         */

        //1.4 ext convert

        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        adapter.setDefaultListenerMethod("consumeMessage");

        //全局的转换器:
        ContentTypeDelegatingMessageConverter convert = new ContentTypeDelegatingMessageConverter();

        TextMessageConverter textConvert = new TextMessageConverter();
        convert.addDelegate("text", textConvert);
        convert.addDelegate("html/text", textConvert);
        convert.addDelegate("xml/text", textConvert);
        convert.addDelegate("text/plain", textConvert);

        Jackson2JsonMessageConverter jsonConvert = new Jackson2JsonMessageConverter();
        convert.addDelegate("json", jsonConvert);
        convert.addDelegate("application/json", jsonConvert);

        ImageMessageConverter imageConverter = new ImageMessageConverter();
        convert.addDelegate("image/png", imageConverter);
        convert.addDelegate("image", imageConverter);

        PDFMessageConverter pdfConverter = new PDFMessageConverter();
        convert.addDelegate("application/pdf", pdfConverter);


        adapter.setMessageConverter(convert);
        container.setMessageListener(adapter);

        return container;

    }
}
