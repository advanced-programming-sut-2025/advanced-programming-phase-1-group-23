package com.P.common.model.NPC;

import com.P.common.model.enums.Ingredients;

public class Pair {
    private final Ingredients ingredient;
    private final Integer number;

    public Pair(Ingredients ingredient, Integer number) {
        this.ingredient = ingredient;
        this.number = number;
    }

    public Ingredients getIngredient() {
        return ingredient;
    }

    public Integer getNumber() {
        return number;
    }
}
