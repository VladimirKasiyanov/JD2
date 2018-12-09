package com.kasiyanov.cofiguration;

import com.kasiyanov.configuration.ServiceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.kasiyanov")
@EnableTransactionManagement
@Import(ServiceConfiguration.class)
public class WebConfiguration {
}
