package com.spring.persistence.dao;

import com.spring.persistence.domain.Category;
import com.spring.persistence.domain.Owner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
public class OwnerDaoImpl implements OwnerDao {

    @PersistenceContext
    private EntityManager em;



    public List<Owner> getAllOwners() {
        List<Owner> list = em.createQuery(
                "SELECT c FROM Owner c", Owner.class).getResultList();
        return list;
    }


    public int store(Owner item) {
        int id= em.merge(item).getId();
        return id;
    }

}
