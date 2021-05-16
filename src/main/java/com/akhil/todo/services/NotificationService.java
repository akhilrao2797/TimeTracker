package com.akhil.todo.services;

import com.akhil.todo.models.ToDo;
import com.akhil.todo.models.User;
import com.akhil.todo.repositories.ToDoRepository;
import com.akhil.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class NotificationService {
    static Map<String, Set<ToDo>> toDoMap = new HashMap<>();

    @Autowired
    ToDoRepository toDoRepository;
    @Autowired
    UserRepository userRepository;

    @Scheduled(fixedRate = 86400000)
    private void queryRedis(){
        for(Map.Entry<String, User> user: userRepository.findAll().entrySet()) {
            Map<Integer, ToDo> todos = toDoRepository.findAll(user.getKey());
            for (Map.Entry<Integer, ToDo> todo : todos.entrySet()) {
                Date date = Date.from(Instant.now());
                if (date.after(todo.getValue().getToDate())) {
                    toDoRepository.delete(todo.getKey(), user.getKey());
                    continue;
                }
                if (!date.before(todo.getValue().getFromDate())
                        || !date.after(todo.getValue().getToDate())) {
                    if(toDoMap.containsKey(user.getKey())) {
                        toDoMap.get(user.getKey()).add(todo.getValue());
                    }else{
                        Set<ToDo> toDo = new HashSet<>();
                        toDo.add(todo.getValue());
                        toDoMap.put(user.getKey(), toDo);
                    }
                }
            }
        }
    }

    public Set<ToDo> getToDoList(String user){
        Set<ToDo> toDos = new HashSet<>(toDoMap.get(user));
        toDoMap.replace(user, new HashSet<>());
        return toDos;
    }
}
