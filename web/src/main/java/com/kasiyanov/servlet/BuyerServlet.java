package com.kasiyanov.servlet;

import com.kasiyanov.model.Buyer;
import com.kasiyanov.service.BuyerService;
import com.kasiyanov.util.ContextRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buyer")
public class BuyerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        AnnotationConfigApplicationContext context = ContextRunner.getAnnotationContext();
        BuyerService buyerService = context.getBean("buyerService", BuyerService.class);

        Buyer defaultBuyer = buyerService.getBuyerById(id).get();
        req.setAttribute("buyer", defaultBuyer);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/buyer.jsp")
                .forward(req, resp);
    }
}
