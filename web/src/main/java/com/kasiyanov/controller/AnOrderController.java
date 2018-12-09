package com.kasiyanov.controller;

import com.kasiyanov.dto.OrdersFilterDto;
import com.kasiyanov.model.AnOrder;
import com.kasiyanov.service.AnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@SessionAttributes({"ordersFilter", "orderNumbers", "orderDates"})
public class AnOrderController {

    @Autowired
    AnOrderService anOrderService;

    @ModelAttribute
    public void lists(Model model) {
        model.addAttribute("orderNumbers", anOrderService.getAllOrderNumbers());
        model.addAttribute("orderDates", anOrderService.getAllDates());
    }

    @ModelAttribute("form")
    public void setDefaultSessionAttributes(Model model) {
        if (!model.containsAttribute("ordersFilter")) {
            model.addAttribute("ordersFilter", OrdersFilterDto.builder()
                                                                        .pageLimit(15)
                                                                        .pageNumber(1)
                                                                        .build());
        }
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model,
                               OrdersFilterDto ordersFilter,
                               @ModelAttribute("ordersFilter") OrdersFilterDto sessionFilter) {

        if (sessionFilter.getPageLimit() != null) {
            ordersFilter.setPageLimit(sessionFilter.getPageLimit());
        }
        if (sessionFilter.getPageNumber() != null) {
            ordersFilter.setPageNumber(sessionFilter.getPageNumber());
        }
        if (sessionFilter.getMaxPrice() != null) {
            ordersFilter.setMaxPrice(sessionFilter.getMaxPrice());
        }
        if (sessionFilter.getOrderDate() != null) {
            ordersFilter.setOrderDate(sessionFilter.getOrderDate());
        }
        if (sessionFilter.getOrderNumber() != null) {
            ordersFilter.setOrderNumber(sessionFilter.getOrderNumber());
        }

        List<AnOrder> orders = anOrderService.findAllByFilterNumberDateBuyer(ordersFilter);
        Long anOrdersQuantity = anOrderService.anOrdersQuantity(ordersFilter);

        model.addAttribute("ordersFilter", ordersFilter);
        model.addAttribute("orders", orders);
        model.addAttribute("anOrdersQuantity", anOrdersQuantity);
        model.addAttribute("numberOfPages", getQuantityOfPages(ordersFilter, anOrdersQuantity));

        return "orders";
    }

    private List<Integer> getQuantityOfPages(OrdersFilterDto ordersFilter, Long anOrdersCount) {
        Long numberOfPages;
        if (anOrdersCount % ordersFilter.getPageLimit() == 0) {
            numberOfPages = anOrdersCount / ordersFilter.getPageLimit();
        } else {
            numberOfPages = anOrdersCount / ordersFilter.getPageLimit() + 1;
        }
        List<Integer> pagesList = new ArrayList<>();
        for (int i = 0; i < numberOfPages; i++) {
            pagesList.add(i + 1);
        }
        return pagesList;
    }

    @PostMapping("/orders")
    public String getForm(Model model,
                          OrdersFilterDto ordersFilter) {
        ordersFilter.setPageNumber(1);

        List<AnOrder> orders = anOrderService.findAllByFilterNumberDateBuyer(ordersFilter);
        Long anOrdersQuantity = anOrderService.anOrdersQuantity(ordersFilter);

        model.addAttribute("ordersFilter", ordersFilter);
        model.addAttribute("orders", orders);
        model.addAttribute("anOrdersQuantity", anOrdersQuantity);
        model.addAttribute("numberOfPages", getQuantityOfPages(ordersFilter, anOrdersQuantity));

        return "orders";
    }
}
