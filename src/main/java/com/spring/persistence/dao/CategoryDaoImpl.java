package com.spring.persistence.dao;

import com.spring.persistence.domain.Category;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
public class CategoryDaoImpl implements CategoryDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> getAllCategories() {
        List<Category> list = em.createQuery(
                "SELECT c FROM Category c", Category.class).getResultList();
        return list;
    }

    @Override
    public int store(Category item) {
        int id= em.merge(item).getId();
        return id;
    }

    @Override
    public int clear() {
        return em.createQuery("DELETE  FROM Category c").executeUpdate();
    }


}
