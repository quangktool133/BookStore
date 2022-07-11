package com.bookstore.entity;

import com.bookstore.mapper.annotation.*;

@DBTable(name = "\"user\"")
public class User {

    @ColumnId
    @IgnoreInsert
    @IgnoreUpdate
    @DBColumn(name = "id")
    private Integer id;

    @DBColumn(name = "user_name")
    private String username;

    @DBColumn(name = "password")
    private String password;

    @DBColumn(name = "role")
    private Integer role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}