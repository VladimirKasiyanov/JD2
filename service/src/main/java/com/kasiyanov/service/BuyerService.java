package com.kasiyanov.service;

import com.kasiyanov.model.Buyer;
import com.kasiyanov.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public Optional<Buyer> getBuyerById(Long id) {
        return buyerRepository.findById(id);
    }
}
