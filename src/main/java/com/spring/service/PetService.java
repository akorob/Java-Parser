package com.spring.service;

import com.spring.model.PetDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Created by dev on 04.03.2015.
 */
public interface PetService {
    List<PetDto> getAllPetsDtos();

    @Transactional
    int parseSite(String path) throws IOException;
}
