package model.Naturals;

import model.enums.Ingredients;
import model.enums.Recipe;
import dev.morphia.annotations.Embedded;

@Embedded
public class Food {
    private Recipe recipe;
    private Ingredients type;

    public Food() {
    }
}
