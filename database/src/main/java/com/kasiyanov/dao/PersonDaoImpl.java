package com.kasiyanov.dao;

import com.kasiyanov.model.Person;

public class PersonDaoImpl extends BaseDaoImpl<Long, Person> implements PersonDao {

    private static final PersonDaoImpl INSTANCE = new PersonDaoImpl();

    public static PersonDaoImpl getINSTANCE() {
        return INSTANCE;
    }
}
