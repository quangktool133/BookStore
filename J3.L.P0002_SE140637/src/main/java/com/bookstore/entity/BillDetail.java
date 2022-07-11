package com.bookstore.entity;

import com.bookstore.mapper.annotation.ColumnId;
import com.bookstore.mapper.annotation.DBColumn;
import com.bookstore.mapper.annotation.DBTable;
import com.bookstore.mapper.annotation.IgnoreInsert;

@DBTable(name = "bill_detail")
public class BillDetail {

    @ColumnId
    @IgnoreInsert
    @DBColumn(name = "id")
    private Integer id;

    @DBColumn(name = "bill_id")
    private Integer billId;

    @DBColumn(name = "book_id")
    private Integer bookId;

    @DBColumn(name = "price")
    private Float price;

    @DBColumn(name = "sub_total")
    private Float subtotal;

    @DBColumn(name = "quantity")
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}