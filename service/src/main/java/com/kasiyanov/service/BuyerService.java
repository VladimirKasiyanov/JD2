package com.kasiyanov.service;

import com.kasiyanov.dao.BuyerDao;
import com.kasiyanov.model.Buyer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyerService {

    private static final BuyerService INSTANCE = new BuyerService();

    public Optional<Buyer> getBuyerById(Long id) {
        return BuyerDao.getInstance().getBuyerById(id);
    }

    public static BuyerService getInstance() {
        return INSTANCE;
    }

}
