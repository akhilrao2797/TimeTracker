package com.akhil.todo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RedisHash("USER")
public class User {

    @Id
    String id;
    @NotEmpty
    @NotNull
    String username;

    public String getUUID() {
        return id;
    }

    public void setUUID(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
