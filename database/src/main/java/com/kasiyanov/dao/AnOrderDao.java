package com.kasiyanov.dao;

import com.kasiyanov.dto.OrdersFilterDto;
import com.kasiyanov.model.AnOrder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AnOrderDao extends BaseDao<Long, AnOrder> {

    List<AnOrder> findAllByFilterNumberDateBuyer(OrdersFilterDto ordersFilterDto);

    Long anOrdersQuantity(OrdersFilterDto ordersFilterDto);

    Set<Integer> getAllOrderNumbers();

    Set<LocalDate> getAllDates();
}
