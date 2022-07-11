package com.bookstore.entity;

import com.bookstore.mapper.annotation.ColumnId;
import com.bookstore.mapper.annotation.DBColumn;
import com.bookstore.mapper.annotation.DBTable;
import com.bookstore.mapper.annotation.IgnoreInsert;
import com.bookstore.util.DateUtil;

import java.sql.Date;

@DBTable(name = "bill")
public class Bill {

    @ColumnId
    @IgnoreInsert
    @DBColumn(name = "id")
    private Integer id;

    @DBColumn(name = "user_name")
    private String username;

    @DBColumn(name = "created_date")
    private Date createdDate;

    @DBColumn(name = "total")
    private Float total;

    @DBColumn(name = "discount_code")
    private String discountCode;

    @DBColumn(name = "payment_method")
    private String paymentMethod;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getFormattedDate() {
        return DateUtil.parse(this.createdDate);
    }
}