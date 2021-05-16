package com.akhil.todo.services;

import com.akhil.todo.models.ToDo;
import com.akhil.todo.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class NotificationService {
    static Set<ToDo> toDoList = new HashSet<>();

    @Autowired
    ToDoRepository toDoRepository;

    @Scheduled(fixedRate = 86400000)
    private void queryRedis(){
        Map<Integer, ToDo> todos = toDoRepository.findAll();
        for(Map.Entry<Integer, ToDo> todo : todos.entrySet()){
            Date date = Date.from(Instant.now());
            if(date.after(todo.getValue().getToDate())) {
                toDoRepository.delete(todo.getKey());
                continue;
            }
            if(!date.before(todo.getValue().getFromDate())
            || !date.after(todo.getValue().getToDate())){
                toDoList.add(todo.getValue());
            }
        }
    }

    public Set<ToDo> getToDoList(){
        Set<ToDo> toDos = new HashSet<>(toDoList);
        toDoList.clear();
        return toDos;
    }
}
