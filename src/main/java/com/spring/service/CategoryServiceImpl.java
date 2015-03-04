package com.spring.service;

import com.spring.model.CategoryDto;
import com.spring.model.PetDto;
import com.spring.persistence.dao.CategoryDao;
import com.spring.persistence.domain.Category;
import com.spring.persistence.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements  CategoryService {


    @Autowired
    private CategoryDao categoryDao;


    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> resultList = new ArrayList<>();
        for (Category c :categoryDao.getAllCategories()){
            resultList.add(new CategoryDto(c));
        }
        return resultList;
    }



}
