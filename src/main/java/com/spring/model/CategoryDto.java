package com.spring.model;

import com.spring.persistence.domain.Category;

import java.io.Serializable;

/**
 * Created by Andrey on 03.03.2015.
 */
public class CategoryDto implements Serializable {
    private int id;
    private String name;
    private String url;
    private boolean enable;

    public CategoryDto (){}

    public CategoryDto (Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.url = category.getUrl();
        this.enable = category.isEnable();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
