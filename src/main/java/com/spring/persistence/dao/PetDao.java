package com.spring.persistence.dao;

import com.spring.persistence.domain.Pet;

import java.util.List;

/**
 * Created by Andrey on 03.03.2015.
 */
public interface PetDao {
    List<Pet> getAllPets();

    int store(Pet item);
}
