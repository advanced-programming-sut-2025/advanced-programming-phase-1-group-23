package com.P.common.model.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProcessingEnum {
    BEEHOUSE("artisan use Honey"),
    CHEESEPRESSCHEESE("artisan use Cheese (Milk|Large Milk)"),
    CHEESEPRESSGOATCHEESE("artisan use Goat Cheese (Goat Milk|Large Goat Milk)"),
    KEGBEER("artisan use Beer Wheat"),
    KEGVINEGAR("artisan use Vinegar Rice");

    private String pattern;

    ProcessingEnum(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
