package com.example.seckillcontroller.rabbitmq;

import com.example.common.entity.SeckillUser;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MQConfig {


    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    MQSender mqSender;

    @Bean
    public RabbitTemplate getRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getMessageConverter());

        //消息传送给交换机
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
//                redisService.del(correlationData.getId());
                System.out.println("消息成功发送到交换机！！" + correlationData + "   cause  " + cause);
            } else {
//                MessageX message = redisService.get(correlationData.getId(), MessageX.class);
                mqSender.sendMsg(new MessageX(new SeckillUser(), 123, 0));
                System.out.println("消息发送交换机失败 " + correlationData + "   cause  " + cause);
            }
        });

        //消息从交换机发送给队列失败，只有失败的时候才会调用
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("消息发送队列-发送失败 " + exchange + " " + routingKey + " " + replyCode + " " + replyText + " " + message);
        });
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter getMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * /usr/sbin/rabbitmq-plugins enable rabbitmq_management
     * mq页面
     */
    public static final String SECKILL_QUEUE = "seckill.queue";

    public static final String EXCHANGE_TOPIC = "exchange_topic";


    //    public static final String QUEUE = "queue";
//    public static final String TOPIC_QUEUE1 = "topic.queue1";
//    public static final String TOPIC_QUEUE2 = "topic.queue2";
//    public static final String HEADER_QUEUE = "header.queue";
    public static final String TOPIC_EXCHANGE = "topicExchage";
//    public static final String FANOUT_EXCHANGE = "fanoutxchage";
//    public static final String HEADERS_EXCHANGE = "headersExchage";

    //delay exchange
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";

    @Bean
    public Queue delayedQueue() {
        return new Queue(DELAYED_QUEUE_NAME);
    }

    //自定义交换机 我们在这里定义的是一个延迟交换机
    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> args = new HashMap<>();
        //延时交换机一定要设置x-delayed-type属性
        args.put("x-delayed-type", "direct");
        //第二个参数type需要设置成x-delayed-message
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
        //x-delayed-message为交换机的类型
    }

    @Bean
    public Binding bindingDelayedQueue(@Qualifier("delayedQueue") Queue queue,
                                       @Qualifier("delayedExchange") CustomExchange
                                               delayedExchange) {
        return
                BindingBuilder.bind(queue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue seckillQueue() {
        return new Queue(SECKILL_QUEUE, true);
    }


    @Bean
    public TopicExchange topicExchage() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }


    @Bean
    public Binding seckillBinding() {
        return BindingBuilder.bind(seckillQueue()).to(topicExchage()).with("seckill.key");
    }


}
