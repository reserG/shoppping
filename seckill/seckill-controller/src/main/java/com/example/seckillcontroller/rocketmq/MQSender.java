package com.example.seckillcontroller.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.example.seckillcontroller.listener.SendCallbackListener;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public class MQSender {
    private static final Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.sync-tag}")
    private String syncTag;

    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.async-tag}")
    private String asyncag;

    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.oneway-tag}")
    private String onewayTag;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println(syncTag + "   " + asyncag + "  " + onewayTag);
        // 构建消息
        String message = "hello";
        rocketMQTemplate.convertAndSend("topic", message);

        Message<String> message2 = MessageBuilder.withPayload(message)
                .setHeader(RocketMQHeaders.KEYS, 1)
                .build();
        SendResult sendResult = rocketMQTemplate.syncSend(syncTag, message2);
        System.out.println(" sendResult " + sendResult.getSendStatus());
        return "wqe";
    }


    /**
     * rocketmq 同步消息
     *
     * @param id 消息
     * @return 结果
     */
    @RequestMapping("/pushMessage")
    public void pushMessage(@RequestParam("id") int id) {
        log.info("pushMessage start : " + id);
        // 构建消息
        String messageStr = "order id : " + id;
        Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .build();
        // 设置发送地和消息信息并发送同步消息
        SendResult sendResult = rocketMQTemplate.syncSend(syncTag, message);
        log.info("pushMessage finish : " + id + ", sendResult : " + JSONObject.toJSONString(sendResult));
        // 解析发送结果
        if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
        }
    }

    /**
     * 发送异步消息
     *
     * @param id 消息
     * @return 结果
     */
    @RequestMapping("/pushAsyncMessage")
    public void pushAsyncMessage(@RequestParam("id") int id) {
        log.info("pushAsyncMessage start : " + id);
        // 构建消息
        String messageStr = "order id : " + id;
        Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .build();
        // 设置发送地和消息信息并发送异步消息
        rocketMQTemplate.asyncSend(asyncag, message, new SendCallbackListener(id));
        log.info("pushAsyncMessage finish : " + id);
    }

    /**
     * 发送单向消息（不关注发送结果：记录日志）
     *
     * @param id 消息
     * @return 结果
     */
    @RequestMapping("/pushOneWayMessage")
    public void pushOneWayMessage(@RequestParam("id") int id) {
        log.info("pushOneWayMessage start : " + id);
        // 构建消息
        String messageStr = "order id : " + id;
        Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .build();
        // 设置发送地和消息信息并发送单向消息
        rocketMQTemplate.sendOneWay(onewayTag, message);
        log.info("pushOneWayMessage finish : " + id);
    }


    /**
     * rocketmq 延迟消息
     *
     * @param id 消息
     * @return 结果
     */
    @RequestMapping("/pushDelayMessage")
    public void pushDelayMessage(@RequestParam("id") int id) {
        log.info("pushDelayMessage start : " + id);
        // 构建消息
        String messageStr = "order id : " + id;
        Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .build();
        // 设置超时和延时推送
        // 超时时针对请求broker然后结果返回给product的耗时
        // 现在RocketMq并不支持任意时间的延时，需要设置几个固定的延时等级，从1s到2h分别对应着等级1到18
        // private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
        SendResult sendResult = rocketMQTemplate.syncSend(syncTag, message, 1 * 1000l, 3);
        log.info("pushDelayMessage finish : " + id + " time : " + new Date().toString() + ",sendResult : " + JSONObject.toJSONString(sendResult));
        // 解析发送结果
        if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
        }
    }


    /**
     * 事务消息
     *
     * @param id 消息
     * @return 结果
     */
    @RequestMapping("/pushTransactionMessage")
    public void pushTransactionMessage(@RequestParam("id") int id) {
        log.info("pushTransactionMessage start : " + id);
        // 创建消息
        String messageStr = "order id : " + id;
        Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .setHeader("money", 10)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, id)
                .build();
        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction(syncTag, message, null);
        log.info("pushTransactionMessage result : " + JSONObject.toJSONString(transactionSendResult));
        log.info("pushTransactionMessage finish : " + id);
    }

}
