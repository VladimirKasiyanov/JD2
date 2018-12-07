package com.kasiyanov.dao;

import com.kasiyanov.model.Card;

public interface CardDao extends BaseDao<Long, Card> {

    Card findByNumber(String number);
}
