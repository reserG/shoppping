package com.example.seckillcontroller.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {
    @Autowired
    B b;

    private String name ="123";

    @Override
    public String toString() {
        return "A{" +
                ", name='" + name + '\'' +
                '}';
    }
}
