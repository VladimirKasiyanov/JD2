package com.kasiyanov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersFilterDto {

    private Integer orderNumber;
    private LocalDate orderDate;
    private Double maxPrice;
    private Integer pageLimit;
    private Integer pageNumber;
}
