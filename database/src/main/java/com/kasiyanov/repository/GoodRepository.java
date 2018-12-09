package com.kasiyanov.repository;

import com.kasiyanov.model.Good;
import org.springframework.data.repository.CrudRepository;

public interface GoodRepository extends CrudRepository<Good, Long> {
}
