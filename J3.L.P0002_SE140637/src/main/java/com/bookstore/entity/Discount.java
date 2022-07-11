package com.bookstore.entity;

import com.bookstore.mapper.annotation.ColumnId;
import com.bookstore.mapper.annotation.DBColumn;
import com.bookstore.mapper.annotation.DBTable;
import com.bookstore.mapper.annotation.IgnoreInsert;
import com.bookstore.validator.annotation.Range;
import com.bookstore.validator.annotation.Required;

import java.sql.Date;

@DBTable(name = "discount")
public class Discount {

    @ColumnId
    @DBColumn(name = "id")
    @IgnoreInsert
    private Integer id;

    @Required(message = "Mã giảm giá không được trống")
    @DBColumn(name = "code")
    private String code;

    @Required(message = "Tỷ lệ giảm giá không được trống")
    @Range(min = 1, max = 100, message = "Tỷ lệ giảm giá không trong phạm vi (1% - 100%)")
    @DBColumn(name = "_percent")
    private Float percent;

    @Required(message = "Ngày hết hạn không được trống")
    @DBColumn(name = "expire_date")
    private Date expireDate;

    @Required(message = "Trạng thái không được trống")
    @DBColumn(name = "status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percentDiscount) {
        this.percent = percentDiscount;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}