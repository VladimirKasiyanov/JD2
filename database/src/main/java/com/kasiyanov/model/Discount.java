package com.kasiyanov.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Embeddable
public class Discount {

    @Column(name = "discount")
    private Double discount;

}
