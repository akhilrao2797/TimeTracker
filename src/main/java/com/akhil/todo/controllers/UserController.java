package com.akhil.todo.controllers;

import com.akhil.todo.models.User;
import com.akhil.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createNewUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
