package com.example.seckillservice.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();

}
