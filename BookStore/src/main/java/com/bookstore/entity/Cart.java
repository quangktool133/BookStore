package com.bookstore.entity;

import com.bookstore.mapper.annotation.*;
import com.bookstore.validator.annotation.Min;
import com.bookstore.validator.annotation.Required;

@DBTable(name = "cart")
public class Cart {

    @ColumnId
    @IgnoreInsert
    @IgnoreUpdate
    @DBColumn(name = "id")
    private Integer id;

    @DBColumn(name = "book_id")
    @IgnoreUpdate
    @Required(message = "Mã sản phầm không được trống")
    private Integer bookId;


    @Required(message = "Số lượng không được trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn 1")
    @DBColumn(name = "quantity")
    private Integer quantity;

    @Required(message = "Username không thể trống")
    @DBColumn(name = "user_name")
    @IgnoreUpdate
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}