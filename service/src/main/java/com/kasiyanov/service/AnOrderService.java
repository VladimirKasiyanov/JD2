package com.kasiyanov.service;

import com.kasiyanov.dao.AnOrderDaoImpl;
import com.kasiyanov.dto.OrdersFilterDto;
import com.kasiyanov.model.AnOrder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class AnOrderService {

    private static final AnOrderService INSTANCE = new AnOrderService();

    public List<AnOrder> findAllByFilterNumberDateBuyer(OrdersFilterDto ordersFilterDto) {
        return AnOrderDaoImpl.getINSTANCE().findAllByFilterNumberDateBuyer(ordersFilterDto);
    }

    public Long anOrdersQuantity(OrdersFilterDto ordersFilterDto) {
        return AnOrderDaoImpl.getINSTANCE().anOrdersQuantity(ordersFilterDto);
    }

    public Set<Integer> getAllOrderNumbers() {
        return AnOrderDaoImpl.getINSTANCE().getAllOrderNumbers();
    }

    public Set<LocalDate> getAllDates() {
        return AnOrderDaoImpl.getINSTANCE().getAllDates();
    }

    public static AnOrderService getINSTANCE() {
        return INSTANCE;
    }
}
