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
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = "good")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "good_category", schema = "store")
public class GoodCategory implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "goodCategory")
    private Set<Good> good = new HashSet<>();
}
