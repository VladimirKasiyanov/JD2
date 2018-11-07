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
            int result = session.createQuery("delete from Card").executeUpdate();
            System.out.println(result);
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
            assertNotNull(id);
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
            assertNotNull(savedId);
            Card saveCard = session.find(Card.class, savedId);
            assertNotNull(saveCard);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            List<Card> list =
                    session.createQuery("select c from Card c", Card.class).list();
            System.out.println(list.size());
        }
    }
}