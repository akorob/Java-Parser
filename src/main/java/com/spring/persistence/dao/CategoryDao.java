package com.spring.persistence.dao;

import com.spring.persistence.domain.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategories();

    int store(Category item);
    int clear();
}
