package com.kasiyanov.repository;

import com.kasiyanov.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
}
