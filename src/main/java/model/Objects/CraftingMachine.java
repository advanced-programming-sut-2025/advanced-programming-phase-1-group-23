package model.Objects;

import model.Naturals.Objectss;
import model.enums.Ingredients;

public class CraftingMachine implements Objectss {
    private final Ingredients type;

    public CraftingMachine(Ingredients type) {
        this.type = type;
    }

    public Ingredients getType() {
        return type;
    }
}
