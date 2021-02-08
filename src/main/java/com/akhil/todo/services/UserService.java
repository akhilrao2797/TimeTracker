package com.akhil.todo.services;

import com.akhil.todo.models.User;
import com.akhil.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createNewUser(User user){
        if(userRepository.exists(user.getUsername()))
            throw new DuplicateKeyException("User already exists");
        userRepository.save(user);
        return user;
    }

    public User getById(String id) {
        return userRepository.findById(id);
    }

    public void deleteById(String  id) {
        userRepository.delete(id);
    }

    public boolean exists(String id){
        return userRepository.exists(id);
    }
}
