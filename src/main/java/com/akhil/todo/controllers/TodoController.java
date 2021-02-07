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

    @GetMapping("/all")
    public ResponseEntity<Map> createNewToDo(){
        return ResponseEntity.ok(toDoService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<ToDo> updateToDo(@Validated @RequestBody ToDo toDo) throws NoSuchFieldException {
        return ResponseEntity.ok(toDoService.updateNewEntry(toDo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getById(@PathVariable("id") int id){
        return ResponseEntity.ok(toDoService.getById(id));
    }
}
