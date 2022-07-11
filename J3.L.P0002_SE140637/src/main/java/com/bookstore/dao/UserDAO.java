package com.bookstore.dao;

import com.bookstore.entity.User;

public class UserDAO extends BaseDAO<User, Integer> {

    public UserDAO() {
        super(User.class);
    }

    public User findByUsername(String username) {
        try {
            return this.jdbcTemplate
                    .select(this.generator.selectBy("user_name"), username)
                    .executeQuery();
        } catch(Exception ex) {
            return null;
        }
    }
}