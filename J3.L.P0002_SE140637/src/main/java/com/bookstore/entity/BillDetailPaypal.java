package com.bookstore.entity;

import com.bookstore.dao.BillDetailDAO;

public class BillDetailPaypal {

    private String productName;
    private float subtotal;
    private float shipping;
    private float tax;
    private float total;

    public BillDetailPaypal() {}

    public BillDetailPaypal(String productName, float subtotal, float shipping, float tax, float total) {
        this.productName = productName;
        this.subtotal = subtotal;
        this.shipping = shipping;
        this.tax = tax;
        this.total = total;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getShipping() {
        return shipping;
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}