package com.kasiyanov.dao;

import com.kasiyanov.model.Employee;

public class EmployeeDaoImpl extends BaseDaoImpl<Long, Employee> implements EmployeeDao {

    private static final EmployeeDaoImpl INSTANCE = new EmployeeDaoImpl();

    public static EmployeeDaoImpl getINSTANCE() {
        return INSTANCE;
    }
}
