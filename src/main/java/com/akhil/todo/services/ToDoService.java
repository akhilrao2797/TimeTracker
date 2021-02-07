package com.akhil.todo.services;

import com.akhil.todo.models.ToDo;
import com.akhil.todo.repositories.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ToDoService {

    @Autowired
    private RedisRepository redisRepository;

    public ToDo createNewEntry(ToDo toDo) {
        return redisRepository.save(toDo);
    }

    public Map<Integer, ToDo> getAll(){
        return redisRepository.findAll();
    }

    public ToDo updateNewEntry(ToDo toDo) {
        return redisRepository.update(toDo);
    }

    public ToDo getById(int id) {
        return redisRepository.findById(id);
    }
}
