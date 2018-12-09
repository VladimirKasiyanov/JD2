package com.kasiyanov.repository;

import com.kasiyanov.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
