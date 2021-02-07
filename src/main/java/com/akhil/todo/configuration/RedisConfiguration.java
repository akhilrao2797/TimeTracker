package com.akhil.todo.configuration;

import com.akhil.todo.models.ToDo;
import com.akhil.todo.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

    @Bean
    public JedisConnectionFactory getJedisConnectionFactory(){
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        return jedisConFactory;
    }

    @Bean
    public RedisTemplate<Integer, ToDo> createTemplate(){
        RedisTemplate<Integer, ToDo> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(getJedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, User> createUserTemplate(){
        RedisTemplate<String, User> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(getJedisConnectionFactory());
        return redisTemplate;
    }
}
