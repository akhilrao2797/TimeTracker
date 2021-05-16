package com.akhil.todo.controllers;

import com.akhil.todo.models.ToDo;
import com.akhil.todo.services.NotificationService;
import com.akhil.todo.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/todo/v1/")
public class TodoController {

    @Autowired
    private ToDoService toDoService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<ToDo> createNewToDo(@Validated @RequestBody ToDo toDo) throws NoSuchFieldException {
        return ResponseEntity.ok(toDoService.createNewEntry(toDo));
    }

    @GetMapping("/all")
    public ResponseEntity<Map> createNewToDo(Principal principal){
        return ResponseEntity.ok(toDoService.getAll(principal.getName()));
    }

    @PutMapping("/update")
    public ResponseEntity<ToDo> updateToDo(@Validated @RequestBody ToDo toDo) throws NoSuchFieldException {
        return ResponseEntity.ok(toDoService.updateNewEntry(toDo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getById(@PathVariable("id") int id, Principal principal){
        return ResponseEntity.ok(toDoService.getById(id, principal.getName()));
    }

    @GetMapping("/alerts")
    public ResponseEntity<Set<ToDo>> getAlerts(){
        return ResponseEntity.ok(notificationService.getToDoList());
    }

}
