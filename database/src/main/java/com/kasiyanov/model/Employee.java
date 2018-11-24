package com.kasiyanov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Data
@ToString(exclude = "role")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employee", schema = "store")
@PrimaryKeyJoinColumn(name = "person_id")
public class Employee extends Person implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Employee(String name,
                    String surename,
                    String login,
                    String password,
                    String email,
                    String phone,
                    Double salary) {
        super(name, surename, login, password, email, phone);
        this.salary = salary;
    }

}
