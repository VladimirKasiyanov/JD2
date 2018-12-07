package com.kasiyanov.dao;

import com.kasiyanov.model.GoodDescription;

public class GoodDescriptionDaoImpl extends BaseDaoImpl<Long, GoodDescription> implements GoodDescriptionDao {

    private static final GoodDescriptionDaoImpl INSTANCE = new GoodDescriptionDaoImpl();

    public static GoodDescriptionDaoImpl getINSTANCE() {
        return INSTANCE;
    }
}
