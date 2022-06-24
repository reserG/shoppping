package com.example.common.utils;

import java.security.SecureRandom;
import java.util.Date;

public class TestUtils {
    public static void main(String[] args) throws InterruptedException {
       for (int i =0;i<10;i++){
           xx();
       }
    }

    static void xx() throws InterruptedException {
        SecureRandom random1 = new SecureRandom();
        SecureRandom random2 = new SecureRandom();
        Date start = new Date();
        int timeOffSet = random1.nextInt(10);
        int offSet = random2.nextInt(10);
        Thread.sleep(offSet * 100L);
        if ((new Date().getTime() - start.getTime()) > timeOffSet * 100 )
            System.out.println(  timeOffSet + "   " + offSet + "pass");
        else
            System.out.println( timeOffSet + "   " + offSet + "fail");
    }
}
