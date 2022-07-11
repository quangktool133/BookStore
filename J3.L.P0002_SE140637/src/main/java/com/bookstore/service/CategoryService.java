package com.bookstore.service;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

import java.util.List;

public class CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public List<Category> findAll() {
        return this.categoryDAO.findAll();
    }
}