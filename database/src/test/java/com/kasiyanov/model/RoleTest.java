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

public class RoleTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Role").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveCourier() {
        try (Session session = FACTORY.openSession()) {
            Role courier = Role.builder()
                    .name("курьер")
                    .build();
            session.save(courier);
            Serializable id = session.save(courier);
            Assert.assertNotNull(id);
        }
    }

    @Test
    public void checkById() {
        try (Session session = FACTORY.openSession()) {
            Role courier = Role.builder()
                    .name("курьер")
                    .build();
            session.save(courier);
            Serializable savedId = session.save(courier);
            Assert.assertNotNull(savedId);
            session.evict(courier);
            Role saveRole = session.find(Role.class, savedId);
            Assert.assertNotNull(saveRole);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            Role courier = Role.builder()
                    .name("курьер")
                    .build();
            session.save(courier);

            List<Role> list =
                    session.createQuery("select r from Role r", Role.class).list();
            Assert.assertEquals(1, list.size());
        }
    }
}