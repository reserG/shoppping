package com.example.seckillcontroller.listener;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendCallbackListener implements SendCallback {
    private static final Logger log = LoggerFactory.getLogger(SendCallback.class);

    private int id;

    public SendCallbackListener(int id) {
        this.id = id;
    }

    @Override
    public void onSuccess(SendResult sendResult) {
        log.info("CallBackListener on success : " + JSONObject.toJSONString(sendResult));
    }


    @Override
    public void onException(Throwable throwable) {
        log.error("CallBackListener on exception : ", throwable);
    }
}
