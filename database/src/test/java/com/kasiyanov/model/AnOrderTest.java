package com.kasiyanov.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

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
            session.createQuery("delete from AnOrder").executeUpdate();
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
            Assert.assertNotNull(id);
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
            Assert.assertNotNull(savedId);
            session.evict(anOrder);
            AnOrder saveAnOrder = session.find(AnOrder.class, savedId);
            Assert.assertNotNull(saveAnOrder);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            AnOrder anOrder = AnOrder.builder()
                    .number(18)
                    .date(Instant.now())
                    .price(1287.34)
                    .build();
            session.save(anOrder);

            List<AnOrder> list =
                    session.createQuery("select o from AnOrder o", AnOrder.class).list();
            Assert.assertEquals(1, list.size());
        }
    }
}