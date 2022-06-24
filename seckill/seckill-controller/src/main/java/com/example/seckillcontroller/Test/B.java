package com.example.seckillcontroller.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {
    @Autowired
    A a;

    private String xx="asd";

    @Override
    public String toString() {
        return "B{" +
                ", xx='" + xx + '\'' +
                '}';
    }
}
