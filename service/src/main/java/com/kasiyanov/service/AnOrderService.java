package com.kasiyanov.service;

import com.kasiyanov.dto.OrdersFilterDto;
import com.kasiyanov.model.AnOrder;
import com.kasiyanov.repository.AnOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AnOrderService {

    @Autowired
    private AnOrderRepository anOrderRepository;

    public List<AnOrder> findAllByFilterNumberDateBuyer(OrdersFilterDto ordersFilterDto) {
        return anOrderRepository.findAllByFilterNumberDateBuyer(ordersFilterDto);
    }

    public Long anOrdersQuantity(OrdersFilterDto ordersFilterDto) {
        return anOrderRepository.anOrdersQuantity(ordersFilterDto);
    }

    public Set<Integer> getAllOrderNumbers() {
        return anOrderRepository.findAllOrderNumbers();
    }

    public Set<LocalDate> getAllDates() {
        return anOrderRepository.findAllDates();
    }
}
