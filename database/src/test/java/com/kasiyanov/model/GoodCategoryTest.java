/*
package com.kasiyanov.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

public class GoodCategoryTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from GoodCategory").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveGoodCategory() {
        try (Session session = FACTORY.openSession()) {
            GoodCategory category = GoodCategory.builder()
                    .name("category")
                    .build();
            Serializable id = session.save(category);
            Assert.assertNotNull(id);
        }
    }

    @Test
    public void checkById() {
        try (Session session = FACTORY.openSession()) {
            GoodCategory category = GoodCategory.builder()
                    .name("category")
                    .build();
            Serializable savedId = session.save(category);
            Assert.assertNotNull(savedId);
            session.evict(category);
            GoodCategory saveCategory = session.find(GoodCategory.class, savedId);
            Assert.assertNotNull(saveCategory);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            GoodCategory category = GoodCategory.builder()
                    .name("category")
                    .build();
            session.save(category);

            List<GoodCategory> list =
                    session.createQuery("select gc from GoodCategory gc", GoodCategory.class).list();
            Assert.assertEquals(1, list.size());
        }
    }
}*/
