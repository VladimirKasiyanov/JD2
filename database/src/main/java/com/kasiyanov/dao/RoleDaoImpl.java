package com.kasiyanov.dao;

import com.kasiyanov.model.Role;

public class RoleDaoImpl extends BaseDaoImpl<Long, Role> implements RoleDao {

    private static final RoleDaoImpl INSTANCE = new RoleDaoImpl();

    public static RoleDaoImpl getINSTANCE() {
        return INSTANCE;
    }
}
