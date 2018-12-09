package com.kasiyanov.converter;


import com.kasiyanov.model.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConverter implements Converter<Person, UserDetails> {

    @Override
    public UserDetails convert(Person person) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(person.getName())
                .password("{noop}" + person.getPassword())
                .authorities(person.getRole().getName())
                .build();
    }

}
