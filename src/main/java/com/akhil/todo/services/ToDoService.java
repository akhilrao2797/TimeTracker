package com.akhil.todo.services;

import com.akhil.todo.models.ToDo;
import com.akhil.todo.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ToDoService {

    private UserService userService;
    private ToDoRepository toDoRepository;

    @Autowired
    ToDoService(UserService userService, ToDoRepository toDoRepository){
        this.userService = userService;
        this.toDoRepository = toDoRepository;
    }

    public ToDo createNewEntry(ToDo toDo) throws NoSuchFieldException {
        if(userService.exists(toDo.getUser()))
            if(!toDoRepository.exists(toDo.getId(), toDo.getUser()))
                return toDoRepository.save(toDo);
            else
                throw new DuplicateKeyException("ToDo ID already exists");
        else
            throw new NoSuchFieldException("Invalid USER");
    }

    public Map<Integer, ToDo> getAll(String user){
        return toDoRepository.findAll(user);
    }

    public ToDo updateNewEntry(ToDo toDo) throws NoSuchFieldException {
        if(userService.exists(toDo.getUser()) && toDoRepository.exists(toDo.getId(), toDo.getUser()))
            return toDoRepository.update(toDo);
        else
            throw new NoSuchFieldException("Invalid USER or TODO ID");
    }

    public ToDo getById(int id, String user) {
        if(toDoRepository.exists(id, user))
            return toDoRepository.findById(id, user);
        else
            throw new NoSuchElementException("Invalid Id");
    }
}
