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
import javax.persistence.Embedded;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = {"goodDescription", "goodCategory", "anOrders"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "good", schema = "store")
public class Good implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Embedded
    private Discount discount;

    @OneToOne(mappedBy = "good")
    private GoodDescription goodDescription;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private GoodCategory goodCategory;

    @ManyToMany
    @JoinTable(name = "good_order", schema = "store",
            joinColumns = @JoinColumn(name = "good_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<AnOrder> anOrders = new HashSet<>();
}
