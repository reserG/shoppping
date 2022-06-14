package com.example.seckillcontroller.controller;

import com.example.common.entity.BaseDomain;
import com.example.common.entity.LoginInfo;
import com.example.common.entity.SeckillUser;
import com.example.common.entity.Test;
import com.example.common.enums.ResultStatus;
import com.example.common.exception.GlobleException;
import com.example.common.utils.resultbean.ResultMAX;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;


//@RestController("/test")
@Controller
public class TestController {

    @PostMapping("/method1")
    Object test1(){
        return "哈哈哈哈哈啊哈哈";
    }

    @PostMapping("/method2")
    Object test2(){
        throw new GlobleException(ResultStatus.SYSTEM_ERROR);
    }

    @PostMapping("/method3")
    @ResponseBody
    Object test3(@Valid @RequestBody Test test){
        return test;
    }
    @PostMapping("/method4")
    Object test4(){
        return new SeckillUser(123L,"123","123213213","123","123","!@3",new Date(),new Date(),123);
    }
    @PostMapping("/method5")
    Object test5(){
        return ResultMAX.error(ResultStatus.SESSION_ERROR);
    }
    @PostMapping("/method6")
    Object test6(@NotNull(message = "用户id不能为空") Long userId){
        return "hahh";
    }
}
