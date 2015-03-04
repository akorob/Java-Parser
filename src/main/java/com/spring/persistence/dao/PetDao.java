package com.spring.persistence.dao;

import com.spring.persistence.domain.Pet;

import java.util.List;


public interface PetDao {
    List<Pet> getAllPets();

    int store(Pet item);

    long count();

    int clear();

    List<Pet> getByCategoryId(int CategoryId);
}
