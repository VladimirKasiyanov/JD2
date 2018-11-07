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
            int result = session.createQuery("delete from Role").executeUpdate();
            System.out.println(result);
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
            assertNotNull(id);
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
            assertNotNull(savedId);
            Role saveRole = session.find(Role.class, savedId);
            assertNotNull(saveRole);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            List<Role> list =
                    session.createQuery("select r from Role r", Role.class).list();
            System.out.println(list.size());
        }
    }

}