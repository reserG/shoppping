package com.example.seckillcontroller;

import com.example.seckillcontroller.Test.A;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SeckillControllerApplication {
    public static ConfigurableApplicationContext ac;

    public static void main(String[] args) {
        ac = SpringApplication.run(SeckillControllerApplication.class, args);
       A a= (A) ac.getBean("a");
        System.out.println(a.toString());
    }

}
