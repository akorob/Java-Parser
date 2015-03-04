package com.spring.controllers;

import com.spring.service.CategoryService;
import com.spring.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController  {

    @Autowired
    CategoryService categoryService;

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity getAllCategories() {

        return new ResponseEntity(categoryService.getAllCategories(), HttpStatus.OK);
    }
}
