package com.example.seckillcontroller.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MQConfig {
    private static final Logger log = LoggerFactory.getLogger(MQConfig.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.sync-tag}")
    private static String syncTag;


}
