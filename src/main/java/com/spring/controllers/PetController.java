package com.spring.controllers;


import com.spring.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public ResponseEntity getTestData() {


        return new ResponseEntity(null, HttpStatus.OK);
    }
}
