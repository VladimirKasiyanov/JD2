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

public class EmployeeTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Employee ").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEmployee() {
        try (Session session = FACTORY.openSession()) {
            Employee employee = new Employee("Nikolay",
                    "Nikolaev",
                    "Nikolay",
                    "nikolay",
                    "nikolay@tut.by",
                    "375-29-329-45-67",
                    800.15);
            Serializable id = session.save(employee);
            assertNotNull(id);
        }
    }

    @Test
    public void checkById() {
        try (Session session = FACTORY.openSession()) {
            Employee employee = new Employee("Nikolay",
                    "Nikolaev",
                    "Nikolay",
                    "nikolay",
                    "nikolay@tut.by",
                    "375-29-329-45-67",
                    800.15);
            Serializable savedId = session.save(employee);
            assertNotNull(savedId);
            Employee saveEmployee = session.find(Employee.class, savedId);
            assertNotNull(saveEmployee);
        }
    }

    @Test
    public void checkGetAll() {
        try (Session session = FACTORY.openSession()) {
            List<Employee> list =
                    session.createQuery("select e from Employee e", Employee.class).list();
            System.out.println(list.size());
        }
    }

}