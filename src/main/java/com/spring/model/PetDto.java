package com.spring.model;

import com.spring.persistence.domain.Pet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Andrey on 03.03.2015.
 */
public class PetDto implements Serializable {
    private int id;
    private String name;
    private String url;
    private String price;
    private String description;
    private Date date;
    private CategoryDto category;
    private OwnerDto owner;
    private boolean enable;

    public PetDto (){}
    public PetDto (Pet pet){
        this.id = pet.getId();
        this.name = pet.getName();
        this.price = pet.getPrice();
        this.description = pet.getDescription();
        this.date = pet.getDate();
        this.category = new CategoryDto(pet.getCategory());
        this.owner = new OwnerDto(pet.getOwner());
        this.enable = pet.isEnable();
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public OwnerDto getOwner() {
        return owner;
    }

    public void setOwner(OwnerDto owner) {
        this.owner = owner;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
