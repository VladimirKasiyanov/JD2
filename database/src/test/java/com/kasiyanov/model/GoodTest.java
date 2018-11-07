package com.kasiyanov.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

import static org.junit.Assert.*;

public class GoodTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Good").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveGood() {
        try (Session session = FACTORY.openSession()) {
            Good good = Good.builder()
                    .name("good")
                    .price(120.15)
                    .build();
            Serializable id = session.save(good);
            assertNotNull(id);
        }
    }

    @Test
    public void checkById() {
        try (Session session = FACTORY.openSession()) {
            Good good = Good.builder()
                    .name("good")
                    .price(120.15)
                    .build();
            Serializable savedId = session.save(good);
            assertNotNull(savedId);
            Good saveGood = session.find(Good.class, savedId);
            assertNotNull(saveGood);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            List<Good> list =
                    session.createQuery("select g from Good g", Good.class).list();
            System.out.println(list.size());
        }
    }

}