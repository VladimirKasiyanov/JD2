package com.kasiyanov.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static org.junit.Assert.*;

public class AnOrderTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from AnOrder").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveOrder() {
        try (Session session = FACTORY.openSession()) {
            AnOrder anOrder = AnOrder.builder()
                    .number(18)
                    .date(Instant.now())
                    .price(1287.34)
                    .build();
            Serializable id = session.save(anOrder);
            assertNotNull(id);
        }
    }

    @Test
    public void checkById() {
        try (Session session = FACTORY.openSession()) {
            AnOrder anOrder = AnOrder.builder()
                    .number(18)
                    .date(Instant.now())
                    .price(1287.34)
                    .build();
            Serializable savedId = session.save(anOrder);
            assertNotNull(savedId);
            AnOrder saveAnOrder = session.find(AnOrder.class, savedId);
            assertNotNull(saveAnOrder);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            List<AnOrder> list =
                    session.createQuery("select o from AnOrder o", AnOrder.class).list();
            System.out.println(list.size());
        }
    }

}