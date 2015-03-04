package com.spring.persistence.dao;

import com.spring.persistence.domain.Category;

import java.util.List;

/**
 * Created by Andrey on 03.03.2015.
 */
public interface CategoryDao {
    List<Category> getAllCategories();

    int store(Category item);
    int clear();
}
