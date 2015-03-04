package com.spring.persistence.dao;

import com.spring.persistence.domain.Owner;
import com.spring.persistence.domain.Pet;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Andrey on 03.03.2015.
 */
@Component
public class PetDaoImpl implements PetDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Pet> getByCategoryId(int CategoryId) {
        List<Pet> list = em.createQuery(
                "SELECT p FROM Pet p WHERE p.enable = true AND p.category.id = :id", Pet.class)
                .setParameter("id", CategoryId).getResultList();
        return list;
    }


    @Override
    public long count() {
        long count = (long)em.createQuery(
                "SELECT COUNT (p) FROM Pet p WHERE p.enable = true").getSingleResult();

        return count;
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> list = em.createQuery(
                "SELECT p FROM Pet p WHERE p.enable = true", Pet.class).getResultList();
        return list;
    }


    @Override
    public int store(Pet item) {
        int id= em.merge(item).getId();
        return id;
    }

    @Override
    public int clear() {
        return em.createQuery("DELETE  FROM Pet p").executeUpdate();
    }

}
