package com.example.seckillcontroller.controller;

import com.example.common.entity.BaseDomain;
import com.example.common.entity.LoginInfo;
import com.example.common.entity.SeckillUser;
import com.example.common.enums.ResultStatus;
import com.example.common.exception.GlobleException;
import com.example.common.utils.resultbean.ResultMAX;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController("/test")
public class TestController {

    @PostMapping("/method1")
    Object test1(){
        return "哈哈哈哈哈啊哈哈";
    }

    @PostMapping("/method2")
    Object test2(){
        throw new GlobleException(ResultStatus.SESSION_ERROR);
    }

    @PostMapping("/method3")
    Object test3(BaseDomain baseDomain){
        return baseDomain;
    }
    @PostMapping("/method4")
    Object test4(){
        return new SeckillUser(123L,"123","123213213","123","123","!@3",new Date(),new Date(),123);
    }
    @PostMapping("/method5")
    Object test5(){
        return ResultMAX.error(ResultStatus.SESSION_ERROR);
    }
}
