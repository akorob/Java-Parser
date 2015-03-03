package com.spring.service;

import com.spring.model.PetDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Andrey on 03.03.2015.
 */
public interface PetService {
    @Transactional
    List<PetDto> getAllPetsDtos();
}
