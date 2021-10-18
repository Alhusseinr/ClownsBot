package com.github.clownsbot.utils;

import org.apache.commons.validator.routines.UrlValidator;

public class Link {
    public static boolean validate(String link) {
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(link);
    }
}
