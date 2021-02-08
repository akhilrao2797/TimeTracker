package com.akhil.todo.services;

import com.akhil.todo.models.User;
import com.akhil.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createNewUser(User user) throws Exception {
        if(StringUtils.isEmpty(user.getPassword()))
            throw new Exception("No Password mentioned");
        user.setUUID(UUID.randomUUID().toString().replace("-","").toUpperCase());
        for(User u : userRepository.getAllUsers().values()){
            if(u.getUsername().equals(user.getUsername())) {
                throw new Exception("User already exists");
            }
        }
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
