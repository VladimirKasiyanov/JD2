package com.kasiyanov.converter;

@FunctionalInterface
public interface Converter<T, R> {

    R convert(T object);
}
