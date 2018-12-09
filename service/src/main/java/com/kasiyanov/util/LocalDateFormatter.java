package com.kasiyanov.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.kasiyanov.util.StringUtil.isNotEmpty;

@UtilityClass
public class LocalDateFormatter {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate format(String value) {
        LocalDate result = null;
        if (isNotEmpty(value)) {
            result = LocalDate.parse(value, FORMATTER);
        }
        return result;
    }
}
