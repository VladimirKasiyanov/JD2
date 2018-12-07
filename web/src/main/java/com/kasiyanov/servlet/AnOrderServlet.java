package com.kasiyanov.servlet;

import com.kasiyanov.dto.OrdersFilterDto;
import com.kasiyanov.model.AnOrder;
import com.kasiyanov.service.AnOrderService;
import com.kasiyanov.util.ContextRunner;
import com.kasiyanov.util.LocalDateFormatter;
import com.kasiyanov.util.StringUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class AnOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrdersFilterDto ordersFilter = (OrdersFilterDto) req.getSession().getAttribute("ordersFilter");
        if (ordersFilter == null) {
            ordersFilter = OrdersFilterDto.builder()
                    .pageLimit(15)
                    .pageNumber(1)
                    .build();
        }
        if (req.getParameter("page") != null) {
            ordersFilter.setPageNumber(Integer.parseInt(req.getParameter("page")));
        }

        AnnotationConfigApplicationContext context = ContextRunner.getAnnotationContext();
        AnOrderService anOrderService = context.getBean("anOrderService", AnOrderService.class);

        List<AnOrder> orders = anOrderService.findAllByFilterNumberDateBuyer(ordersFilter);
        Long anOrdersQuantity = anOrderService.anOrdersQuantity(ordersFilter);

        req.setAttribute("orders", orders);
        req.setAttribute("anOrdersQuantity", anOrdersQuantity);
        req.setAttribute("numberOfPages", getQuantityOfPages(ordersFilter, anOrdersQuantity));
        req.getSession().setAttribute("ordersFilter", ordersFilter);
        req.getSession().setAttribute("orderNumbers", anOrderService.getAllOrderNumbers());
        req.getSession().setAttribute("orderDates", anOrderService.getAllDates());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/anOrders.jsp")
                .forward(req, resp);
    }

    private Long getQuantityOfPages(OrdersFilterDto ordersFilter, Long anOrdersCount) {
        Long numberOfPages;
        if (anOrdersCount % ordersFilter.getPageLimit() == 0) {
            numberOfPages = anOrdersCount / ordersFilter.getPageLimit();
        } else {
            numberOfPages = anOrdersCount / ordersFilter.getPageLimit() + 1;
        }
        return numberOfPages;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrdersFilterDto ordersFilter = getOrdersFilter(req);

        AnnotationConfigApplicationContext context = ContextRunner.getAnnotationContext();
        AnOrderService anOrderService = context.getBean("anOrderService", AnOrderService.class);

        List<AnOrder> orders = anOrderService.findAllByFilterNumberDateBuyer(ordersFilter);
        Long anOrdersQuantity = anOrderService.anOrdersQuantity(ordersFilter);

        req.setAttribute("orders", orders);
        req.setAttribute("anOrdersQuantity", anOrdersQuantity);
        req.setAttribute("numberOfPages", getQuantityOfPages(ordersFilter, anOrdersQuantity));
        req.getSession().setAttribute("ordersFilter", ordersFilter);
        req.getSession().setAttribute("orderNumbers", anOrderService.getAllOrderNumbers());
        req.getSession().setAttribute("orderDates", anOrderService.getAllDates());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/anOrders.jsp")
                .forward(req, resp);
    }

    private OrdersFilterDto getOrdersFilter(HttpServletRequest req) {
        String orderNumber = req.getParameter("orderNumber");
        String orderDate = req.getParameter("orderDate");
        String maxPrice = req.getParameter("maxPrice");
        String pageLimit = req.getParameter("pageLimit");

        OrdersFilterDto ordersFilter = OrdersFilterDto.builder()
                .pageLimit(Integer.parseInt(pageLimit))
                .pageNumber(1)
                .build();

        if (StringUtil.isNotEmpty(orderNumber)) {
            ordersFilter.setOrderNumber(Integer.parseInt(orderNumber));
        }
        if (StringUtil.isNotEmpty(orderDate)) {
            ordersFilter.setOrderDate(LocalDateFormatter.format(orderDate));
        }
        if (StringUtil.isNotEmpty(maxPrice)) {
            ordersFilter.setMaxPrice(Double.parseDouble(maxPrice));
        }

        return ordersFilter;
    }
}
