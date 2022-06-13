package com.example.seckillcontroller.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();

}
