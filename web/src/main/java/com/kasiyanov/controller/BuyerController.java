package com.kasiyanov.controller;

import com.kasiyanov.model.Buyer;
import com.kasiyanov.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BuyerController {

    private final BuyerService buyerService;

    @GetMapping("/buyer")
    public String getBuyer(Model model,
                          @RequestParam("id") String paramId) {
        Long id = Long.parseLong(paramId);
        Optional<Buyer> buyer = buyerService.getBuyerById(id);
        buyer.ifPresent(buyer2 -> model.addAttribute("buyer", buyer2));
        return "buyer";
    }
}