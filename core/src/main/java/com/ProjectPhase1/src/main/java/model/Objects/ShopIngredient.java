package model.Objects;

import model.enums.Ingredients;

import dev.morphia.annotations.Embedded;

@Embedded
public class ShopIngredient extends ShopItem {
    private Ingredients type;

    public ShopIngredient(Ingredients type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public ShopIngredient() {
    }

    public Ingredients getType() {
        return type;
    }
}
