package com.example.seckillcontroller.controller;

import com.example.common.entity.Goods;
import com.example.common.enums.ResultStatus;
import com.example.common.utils.SeckillX;
import com.example.common.utils.resultbean.ResultMAX;
import com.example.seckillcontroller.annotation.RequireLogin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Date;

@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @RequireLogin(seconds = 5 ,maxCount = 5)
    @RequestMapping("/do_seckill")
    public ResultMAX<Integer> doseckill(Goods goods, Date date) {
        ResultMAX<Integer> resultMAX = ResultMAX.build();

        //随机判定是否有资格参与活动
        if (SeckillX.timeOffSet(date)){
            resultMAX.withError(ResultStatus.SECKILL_FAIL);
            return resultMAX;
        }


        return resultMAX;
    }

}
