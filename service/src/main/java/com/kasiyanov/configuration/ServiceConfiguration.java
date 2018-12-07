package com.kasiyanov.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.kasiyanov")
@EnableTransactionManagement
@Import(DatabaseConfiguration.class)
public class ServiceConfiguration {
}
