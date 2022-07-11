package com.bookstore.dao;

import com.bookstore.entity.Bill;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BillDAO extends BaseDAO<Bill, Integer> {

    public BillDAO() {
        super(Bill.class);
    }

    public int insert(Bill bill) throws SQLException {
        return this.jdbcTemplate
                .insert(this.generator.insert(), bill.getUsername(), bill.getCreatedDate(), bill.getTotal(), bill.getDiscountCode(), bill.getPaymentMethod())
                .executeSave();
    }

    public List<Bill> search(Bill bill) {
        try {
            StringBuilder search = new StringBuilder("SELECT * FROM bill WHERE user_name = ?");
            List<Object> params = new ArrayList<>();
            params.add(bill.getUsername());

            if (Objects.nonNull(bill.getCreatedDate())) {
                search.append(" AND created_date = ?");
                params.add(bill.getCreatedDate());
            }
            search.append(" ORDER BY created_date, id DESC");
            return this.jdbcTemplate
                    .select(search.toString(), params.toArray())
                    .executeQueryList();
        } catch(Exception ex) {
            return new ArrayList<>();
        }
    }

    public void update(Bill bill) {
        try {
            this.jdbcTemplate
                    .update("UPDATE bill SET discount_code = ? WHERE id = ?", bill.getDiscountCode(), bill.getId())
                    .executeSave();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}