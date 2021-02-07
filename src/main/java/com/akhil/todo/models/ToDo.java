package com.akhil.todo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@RedisHash("TODO")
public class ToDo implements Serializable {

    @Id
    int id;

    @NotNull
    @NotEmpty
    String todo;

    @NotEmpty
    @NotNull
    String userId;

    @FutureOrPresent
    Date fromDate;

    @FutureOrPresent
    Date toDate;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getUser() {
        return userId;
    }

    public void setUser(String user) {
        this.userId = user;
    }
}
