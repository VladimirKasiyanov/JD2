package com.kasiyanov.servlet;

import com.kasiyanov.model.Buyer;
import com.kasiyanov.service.BuyerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/buyer")
public class BuyerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        Optional<Buyer> defaultBuyer = BuyerService.getInstance().getBuyerById(id);
        req.setAttribute("buyer", defaultBuyer.get());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/buyer.jsp")
                .forward(req, resp);
    }
}
