package com.bookstore.entity;

import com.bookstore.mapper.annotation.*;
import com.bookstore.util.DataUtil;
import com.bookstore.validator.annotation.Min;
import com.bookstore.validator.annotation.Range;
import com.bookstore.validator.annotation.Required;

@DBTable(name = "book")
public class Book {

    @ColumnId
    @IgnoreUpdate
    @IgnoreInsert
    @DBColumn(name = "id")
    private Integer id;

    @Required(message = "Tiêu đề không được trống")
    @DBColumn(name = "title")
    private String title;

    @Required(message = "Tên sách không được trống")
    @DBColumn(name = "name")
    private String name;

    @Required(message = "Giá tiền không được trống")
    @Min(value = 1, message = "Giá tiền không được nhỏ hơn 1")
    @DBColumn(name = "price")
    private Float price;

    @Required(message = "Tác giả không được trống")
    @DBColumn(name = "author")
    private String author;

    @Required(message = "Số lượng không thể để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    @DBColumn(name = "quantity")
    private Integer quantity;

    @Required(message = "Loại sách không được trống")
    @DBColumn(name = "category_id")
    private Integer categoryId;

    @Required(message = "Hình ảnh không được để trống")
    @DBColumn(name = "image")
    private String image;

    @Required(message = "Trạng thái sách không được trống")
    @Range(min = 0, max = 1, message = "Trạng thái sách phải là 0: active hoặc 1: inactive")
    @DBColumn(name = "status")
    private Integer status;

    @DBColumn(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPriceVnd() {
        return DataUtil.formatCurrency(this.price);
    }
}