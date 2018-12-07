package com.kasiyanov.util;


import com.kasiyanov.cofiguration.WebConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContextRunner {

    public static AnnotationConfigApplicationContext getAnnotationContext() {
        return new AnnotationConfigApplicationContext(WebConfiguration.class);
    }
}
