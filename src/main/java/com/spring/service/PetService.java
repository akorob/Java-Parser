package com.spring.service;

import com.spring.model.PetDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface PetService {
    long count();

    List<PetDto> getPetsByCategory(int categoryId);

    List<PetDto> getAllPetsDtos();

    @Transactional
    int parseSite() throws IOException;
}
