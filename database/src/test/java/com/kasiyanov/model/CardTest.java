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

public class CardTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Card").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveCard() {
        try (Session session = FACTORY.openSession()) {
            Card petr = Card.builder()
                    .number("1111222233334444")
                    .name("PETR PETROV")
                    .date("03/21")
                    .cv("479")
                    .build();
            Serializable id = session.save(petr);
            Assert.assertNotNull(id);
        }
    }

    @Test
    public void checkById() {
        try (Session session = FACTORY.openSession()) {
            Card petr = Card.builder()
                    .number("1111222233334444")
                    .name("PETR PETROV")
                    .date("03/21")
                    .cv("479")
                    .build();
            Serializable savedId = session.save(petr);
            Assert.assertNotNull(savedId);
            session.evict(petr);
            Card saveCard = session.find(Card.class, savedId);
            Assert.assertNotNull(saveCard);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            Card petr = Card.builder()
                    .number("1111222233334444")
                    .name("PETR PETROV")
                    .date("03/21")
                    .cv("479")
                    .build();
            session.save(petr);

            List<Card> list =
                    session.createQuery("select c from Card c", Card.class).list();
            Assert.assertEquals(1, list.size());
        }
    }
}