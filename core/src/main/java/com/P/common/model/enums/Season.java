package com.P.common.model.enums;


public enum Season {
    SPRING("Spring"),
    SUMMER("Summer"),
    AUTUMN("Autumn"),
    WINTER("Winter");

    private final String name;

    Season(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
