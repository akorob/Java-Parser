package com.spring.controllers;


import com.spring.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

/**
 * Created by Andrey on 30.12.2014.
 */
@RestController
@RequestMapping("/api/pet")
public class PetController {

    @Autowired PetService petService;


    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity getAllPets() {

        return new ResponseEntity(petService.getAllPetsDtos(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addNewUser(@RequestBody String path) {
        try {
            int count = petService.parseSite(path);
            return new ResponseEntity(count, HttpStatus.OK);
        } catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @RequestMapping(value="/test", method = RequestMethod.GET)
    public ResponseEntity getTestData() {


        return new ResponseEntity(null, HttpStatus.OK);
    }
}
