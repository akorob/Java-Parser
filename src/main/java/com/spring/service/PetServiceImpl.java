package com.spring.service;

import com.spring.model.PetDto;
import com.spring.persistence.dao.PetDao;
import com.spring.persistence.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 03.03.2015.
 */
@Service
public class PetServiceImpl implements PetService {


    @Autowired
    private PetDao petDao;

    @Override
    @Transactional
    public List<PetDto> getAllPetsDtos() {
        List<PetDto> resultList = new ArrayList<>();
        for (Pet p :petDao.getAllPets()){
            resultList.add(new PetDto(p));
        }
        return resultList;
    }
}
