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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = {"card", "anOrder"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "buyer", schema = "store")
@PrimaryKeyJoinColumn(name = "person_id")
public class Buyer extends Person implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house", nullable = false)
    private Integer house;

    @Column(name = "apartment", nullable = false)
    private Integer apartment;

    @OneToMany(mappedBy = "buyer")
    private Set<AnOrder> anOrder = new HashSet<>();

    @OneToOne(mappedBy = "buyer")
    private Card card;

    public Buyer(String name,
                 String surename,
                 String login,
                 String password,
                 String email,
                 String phone,
                 String street,
                 Integer house,
                 Integer apartment) {
        super(name, surename, login, password, email, phone);
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

}
