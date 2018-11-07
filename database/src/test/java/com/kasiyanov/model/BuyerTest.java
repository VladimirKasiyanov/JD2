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

public class BuyerTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Buyer").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveBuyer() {
        try (Session session = FACTORY.openSession()) {
            Buyer buyer = new Buyer("Stas",
                    "Stasov",
                    "Stas",
                    "stas",
                    "stas@mail.ru",
                    "375-29-116-45-67",
                    "Независимости",
                    123,
                    45);
            Serializable id = session.save(buyer);
            assertNotNull(id);
        }
    }

    @Test
    public void checkById() {
        try (Session session = FACTORY.openSession()) {
            Buyer buyer = new Buyer("Stas",
                    "Stasov",
                    "Stas",
                    "stas",
                    "stas@mail.ru",
                    "375-29-116-45-67",
                    "Независимости",
                    123,
                    45);
            Serializable savedId = session.save(buyer);
            assertNotNull(savedId);
            Buyer saveBuyer = session.find(Buyer.class, savedId);
            assertNotNull(saveBuyer);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            List<Buyer> list =
                    session.createQuery("select b from Buyer b", Buyer.class).list();
            System.out.println(list.size());
        }
    }

}