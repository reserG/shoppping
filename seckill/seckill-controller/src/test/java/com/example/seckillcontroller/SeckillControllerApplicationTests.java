package com.example.seckillcontroller;

import com.example.seckillcontroller.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class SeckillControllerApplicationTests {

    @Autowired
    JedisPool jedisPool;
    @Test
    void contextLoads() {
        Set set = new HashSet();
        Jedis jedis = null;
        jedis = jedisPool.getResource();
            jedis.set("9797979797971", "!@3213");
//        if (jedis != null) {
//            jedis.close();
//        }
        jedisPool.returnResource(jedis);
    }


}
