package com.kasiyanov.dao;

import com.kasiyanov.model.GoodCategory;

public class GoodCategoryDaoImpl extends BaseDaoImpl<Long, GoodCategory> implements GoodCategoryDao {

    private static final GoodCategoryDaoImpl INSTANCE = new GoodCategoryDaoImpl();

    public static GoodCategoryDaoImpl getINSTANCE() {
        return INSTANCE;
    }
}
