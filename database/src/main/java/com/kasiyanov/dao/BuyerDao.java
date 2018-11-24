package com.kasiyanov.dao;

import com.kasiyanov.model.Buyer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyerDao {
    private static final BuyerDao INSTANCE = new BuyerDao();
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public Optional<Buyer> getBuyerById(Long id) {
        Buyer buyer = null;
        Session session = FACTORY.openSession();
        buyer = session.find(Buyer.class, id);
        return Optional.ofNullable(buyer);
    }

    public static BuyerDao getInstance() {
        return INSTANCE;
    }
}