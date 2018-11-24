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
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Data
@ToString(exclude = "buyer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "card", schema = "store")
public class Card implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true, nullable = false)
    private String number;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "cv", nullable = false)
    private String cv;

    @OneToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public Card(String number, String name, String date, String cv) {
        this.number = number;
        this.name = name;
        this.date = date;
        this.cv = cv;
    }
}
