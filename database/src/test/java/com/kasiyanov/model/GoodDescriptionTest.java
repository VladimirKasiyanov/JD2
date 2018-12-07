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

public class GoodDescriptionTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from GoodDescription").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveGoodDescription() {
        try (Session session = FACTORY.openSession()) {
            GoodDescription description = GoodDescription.builder()
                    .shortDescription("short")
                    .fullDescription("full")
                    .build();
            Serializable id = session.save(description);
            Assert.assertNotNull(id);
        }
    }

    @Test
    public void checkById() {
        try (Session session = FACTORY.openSession()) {
            GoodDescription description = GoodDescription.builder()
                    .shortDescription("short")
                    .fullDescription("full")
                    .build();
            Serializable savedId = session.save(description);
            Assert.assertNotNull(savedId);
            session.evict(description);
            GoodDescription saveDescription = session.find(GoodDescription.class, savedId);
            Assert.assertNotNull(saveDescription);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            GoodDescription description = GoodDescription.builder()
                    .shortDescription("short")
                    .fullDescription("full")
                    .build();
            session.save(description);

            List<GoodDescription> list =
                    session.createQuery("select gd from GoodDescription gd", GoodDescription.class).list();
            Assert.assertEquals(1, list.size());
        }
    }
}*/
