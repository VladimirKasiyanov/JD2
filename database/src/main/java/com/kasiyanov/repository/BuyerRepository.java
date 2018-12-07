package com.kasiyanov.repository;

import com.kasiyanov.model.Buyer;
import org.springframework.data.repository.CrudRepository;

public interface BuyerRepository extends CrudRepository<Buyer, Long> {
}
