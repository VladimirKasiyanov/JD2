package com.kasiyanov.cofiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/home")
                        .hasAnyAuthority("administrator")
                    .anyRequest()
                        .permitAll()
                .and()
                    .formLogin()
                .and()
                    .httpBasic()
                .and()
                    .logout()
                .and()
                    .csrf().disable();
        http.userDetailsService(userDetailsService);
    }
}
