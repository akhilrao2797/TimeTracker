package com.akhil.todo.repositories;

import com.akhil.todo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class UserRepository {

    private RedisTemplate<String, User> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    UserRepository(RedisTemplate<String, User> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public User save(User user){
        hashOperations.put("USER", user.getUsername(), user);
        return user;
    }

    public User findById(String id){
        return (User) hashOperations.get("USER", id);
    }

    public void delete(String id){
        if(hashOperations.hasKey("USER", id))
            hashOperations.delete("USER", id);
        else
            throw new NoSuchElementException();
    }

    public boolean exists(String id){
        return hashOperations.hasKey("USER", id);
    }
}
