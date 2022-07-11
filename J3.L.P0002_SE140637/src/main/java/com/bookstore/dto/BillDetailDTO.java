package com.bookstore.dto;

import com.bookstore.entity.BillDetail;
import com.bookstore.entity.Book;
import com.bookstore.util.DataUtil;

public class BillDetailDTO {

    private Integer id;
    private Integer billId;
    private Integer bookId;
    private Float price;
    private Float subtotal;
    private Integer quantity;
    private Book book;

    public BillDetailDTO(BillDetail billDetail, Book book) {
        this.id = billDetail.getId();
        this.billId = billDetail.getBillId();
        this.bookId = billDetail.getBookId();
        this.price = billDetail.getPrice();
        this.subtotal = billDetail.getSubtotal();
        this.quantity = billDetail.getQuantity();
        this.book = book;
    }

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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getPriceVnd() {
        return DataUtil.formatCurrency(this.price);
    }

    public String getSubtotalVnd() {
        return DataUtil.formatCurrency(this.subtotal);
    }
}