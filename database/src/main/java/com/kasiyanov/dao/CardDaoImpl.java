package com.kasiyanov.dao;

import com.kasiyanov.model.Card;
import com.kasiyanov.model.QCard;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.Cleanup;
import org.hibernate.Session;

import static com.kasiyanov.connection.ConnectionManager.getSession;

public class CardDaoImpl extends BaseDaoImpl<Long, Card> implements CardDao {

    private static final CardDao INSTANCE = new CardDaoImpl();

    @Override
    public Card findByNumber(String number) {
        @Cleanup Session session = getSession();
        return new JPAQuery<Card>(session)
                .select(QCard.card)
                .from(QCard.card)
                .where(QCard.card.number.eq(number))
                .fetchOne();
    }

    public static CardDao getInstance() {
        return INSTANCE;
    }
}
