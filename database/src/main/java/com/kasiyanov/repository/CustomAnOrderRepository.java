package com.kasiyanov.repository;

import com.kasiyanov.dto.OrdersFilterDto;
import com.kasiyanov.model.AnOrder;

import java.util.List;

public interface CustomAnOrderRepository {

    List<AnOrder> findAllByFilterNumberDateBuyer(OrdersFilterDto ordersFilterDto);

    Long anOrdersQuantity(OrdersFilterDto ordersFilterDto);
}
