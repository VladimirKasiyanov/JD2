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
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.util.HashSet;
import java.util.Set;
import java.time.Instant;


@Data
@ToString(exclude = {"buyer", "goods"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "anorder", schema = "store")
public class AnOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true, nullable = false)
    private Integer number;

    @Column(name = "order_date", nullable = false)
    private Instant date;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToMany
    @JoinTable(name = "good_order", schema = "store",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "good_id"))
    private Set<Good> goods = new HashSet<>();

    public AnOrder(Integer number, Instant date, Double price) {
        this.number = number;
        this.date = date;
        this.price = price;
    }
}
