package com.bookstore.entity;

import com.bookstore.mapper.annotation.ColumnId;
import com.bookstore.mapper.annotation.DBColumn;
import com.bookstore.mapper.annotation.DBTable;
import com.bookstore.mapper.annotation.IgnoreUpdate;

@DBTable(name = "category")
public class Category {

    @ColumnId
    @DBColumn(name = "id")
    @IgnoreUpdate
    private Integer id;

    @DBColumn(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}