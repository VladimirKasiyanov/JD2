package com.kasiyanov.service;

import com.kasiyanov.model.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface PersonService extends UserDetailsService {

    Optional<Person> findById(Long id);
}
