package com.bookstore.dao;

import com.bookstore.entity.Category;

public class CategoryDAO extends BaseDAO<Category, Integer> {

    public CategoryDAO() {
        super(Category.class);
    }
}