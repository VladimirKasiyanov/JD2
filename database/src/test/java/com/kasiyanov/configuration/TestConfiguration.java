package com.kasiyanov.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfiguration.class)
public class TestConfiguration {
}
