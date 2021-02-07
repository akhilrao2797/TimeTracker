package com.akhil.todo.repositories;

import com.akhil.todo.models.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class ToDoRepository {

    private RedisTemplate<Integer, ToDo> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    ToDoRepository(RedisTemplate<Integer, ToDo> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public Map<Integer, ToDo> findAll(String user){
        return hashOperations.entries("TODO_"+user);
    }

    public ToDo save(ToDo toDo){
        hashOperations.put("TODO_"+toDo.getUser(), toDo.getId(), toDo);
        return toDo;
    }

    public ToDo findById(int id, String user){
        return (ToDo) hashOperations.get("TODO_"+user, id);
    }

    public ToDo update(ToDo toDo){
        hashOperations.put("TODO_"+toDo.getUser(), toDo.getId(), toDo);
        return toDo;
    }

    public void delete(int id, String user){
        if(hashOperations.hasKey("TODO_"+user, id))
            hashOperations.delete("TODO_"+user, id);
        else
            throw new NoSuchElementException();
    }

    public boolean exists(long id, String user) {
        return hashOperations.hasKey("TODO_"+user, id);
    }
}
