package com.kasiyanov.dao;

import com.kasiyanov.model.Good;

public class GoodDaoImpl extends BaseDaoImpl<Long, Good> implements GoodDao {

    private static final GoodDaoImpl INSTANCE = new GoodDaoImpl();

    public static GoodDaoImpl getINSTANCE() {
        return INSTANCE;
    }
}
