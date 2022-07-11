package com.bookstore.dto;

import com.bookstore.entity.Book;
import com.bookstore.entity.Cart;
import com.bookstore.util.DataUtil;

public class CartDTO {

    private Integer id;
    private Book book;
    private Integer quantity;
    private String username;

    public CartDTO() {}

    public CartDTO(Cart cart) {
        this(cart.getId(), null, cart.getQuantity(), cart.getUsername());
    }

    public CartDTO(Integer id, Book book, Integer quantity, String username) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public String getPriceVnd() {
        return DataUtil.formatCurrency(this.book.getPrice());
    }

    public String getSubtotalVnd() {
        return DataUtil.formatCurrency(this.book.getPrice() * this.quantity);
    }

    public float getSubtotal() {
        return this.book.getPrice() * this.quantity;
    }
}