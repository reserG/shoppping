package com.example.seckillcontroller.rabbitmq;

import com.example.common.utils.MsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {

    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMiaoshaMessage(MessageX msg) {
        log.info("send message:" + msg);
        amqpTemplate.convertAndSend(MQConfig.DELAYED_EXCHANGE_NAME, MQConfig.DELAYED_ROUTING_KEY, MsgUtil.objToMsg(msg, msg.getDelay() * 1000));
    }

    /**
     * 站内信
     *
     * @param mm
     */
    public void sendMessage(MessageX msg) {
//		String msg = RedisService.beanToString(mm);
        log.info("send message:" + "11111");
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE_TOPIC, "miaosha_*", MsgUtil.objToMsg(msg, msg.getDelay()));
    }

    /**
     * 站内信
     *
     * @param
     */
    public void sendRegisterMessage(MessageX msg) {
        log.info("send message:{}", msg);
        rabbitTemplate.convertAndSend(MQConfig.SECKILL_QUEUE, MsgUtil.objToMsg(msg, msg.getDelay()));
//        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE_TOPIC,"miaosha_*", msg);
    }


    public void sendMsg(MessageX msg) {
        log.info("send message:{}", msg);
        rabbitTemplate.convertAndSend(MQConfig.SECKILL_QUEUE, MsgUtil.objToMsg(msg, msg.getDelay() * 1000));
    }

}
