package com.akhil.todo.controllers;

import com.akhil.todo.models.ToDo;
import com.akhil.todo.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/todo/v1/")
public class TodoController {

    @Autowired
    private ToDoService toDoService;

    @PostMapping("/create")
    public ResponseEntity<ToDo> createNewToDo(@Validated @RequestBody ToDo toDo) throws NoSuchFieldException {
        return ResponseEntity.ok(toDoService.createNewEntry(toDo));
    }

    @GetMapping("/{user}/all")
    public ResponseEntity<Map> createNewToDo(@PathVariable("user") String user){
        return ResponseEntity.ok(toDoService.getAll(user));
    }

    @PutMapping("/update")
    public ResponseEntity<ToDo> updateToDo(@Validated @RequestBody ToDo toDo) throws NoSuchFieldException {
        return ResponseEntity.ok(toDoService.updateNewEntry(toDo));
    }

    @GetMapping("/{user}/{id}")
    public ResponseEntity<ToDo> getById(@PathVariable("id") int id, @PathVariable("user") String user){
        return ResponseEntity.ok(toDoService.getById(id, user));
    }
}
