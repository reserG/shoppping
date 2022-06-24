package com.example.seckillcontroller.controller;

import com.example.common.entity.BaseDomain;
import com.example.common.entity.LoginInfo;
import com.example.common.entity.SeckillUser;
import com.example.common.entity.Test;
import com.example.common.enums.ResultStatus;
import com.example.common.exception.GlobleException;
import com.example.common.utils.resultbean.ResultMAX;
import com.example.seckillcontroller.annotation.MyAnnotation;
import com.example.seckillcontroller.redis.GoodsKey;
import com.example.seckillcontroller.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;


@RestController
public class TestController {

    @Autowired
    RedisService redisService;

    @PostMapping("/method1")
    Object test1() {
        return "哈哈哈哈哈啊哈哈";
    }

    @PostMapping("/method2")
    Object test2() {
        throw new GlobleException(ResultStatus.SYSTEM_ERROR);
    }

    @PostMapping("/method3")
    Object test3(@Valid Test test) {
        return test;
    }

    @PostMapping("/method4")
    Object test4() {
        return new SeckillUser(123L, "123", "123213213", "123", "123", "!@3", new Date(), new Date(), 123);
    }

    @MyAnnotation(value = "test method5")
    @PostMapping("/method5")
    Object test5() {
        return ResultMAX.error(ResultStatus.SESSION_ERROR);
    }

    @PostMapping("/method6")
    Object test6(@NotNull(message = "用户id不能为空") Long userId) {
        redisService.set(GoodsKey.getGoodsDetail,"1","132");
        return "hahh";
    }
}
