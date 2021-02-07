package com.akhil.todo.repositories;

import com.akhil.todo.models.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class RedisRepository {

    private RedisTemplate<Integer, ToDo> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    RedisRepository(RedisTemplate<Integer, ToDo> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public Map<Integer, ToDo> findAll(){
        return hashOperations.entries("TODO");
    }

    public ToDo save(ToDo toDo){
        hashOperations.put("TODO", toDo.getId(), toDo);
        return toDo;
    }

    public ToDo findById(int id){
        return (ToDo) hashOperations.get("TODO", id);
    }

    public ToDo update(ToDo toDo){
        hashOperations.put("TODO", toDo.getId(), toDo);
        return toDo;
    }


}
