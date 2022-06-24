package com.example.common.utils;

import java.security.SecureRandom;
import java.util.Date;

public class SeckillX {

    public static boolean timeOffSet(Date startTime) {
        SecureRandom random = new SecureRandom();
        int timeOffSet = random.nextInt(10);
        System.out.println(timeOffSet);
        if ((new Date().getTime() - startTime.getTime()) > timeOffSet * 100)
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println( new Date()  +  "  "  + new Date().getTime());
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 10; i++) {
            int sleeTime = random.nextInt(10);
            Date dateTime = new Date();
            Thread.sleep(sleeTime * 100);
            System.out.println("模拟请求发起时间 = " + dateTime.getTime() + " sleepTime = " + sleeTime+ "   结果 = " + timeOffSet(dateTime));

        }
    }
}
