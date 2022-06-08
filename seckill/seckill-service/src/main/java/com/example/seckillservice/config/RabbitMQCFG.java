package com.example.seckillservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootConfiguration
public class RabbitMQCFG {

    @Bean
    public Queue queueWock(){
        return new Queue("queue_work");
    }
}
