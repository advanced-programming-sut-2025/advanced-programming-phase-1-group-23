package src.main.java.model.Objects;
import model.enums.AnimalType;

import dev.morphia.annotations.Embedded;

@Embedded
public class ShopAnimal extends ShopItem {
    private final AnimalType type;

    public ShopAnimal(AnimalType type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public ShopAniaml() {
    }

    public AnimalType getType() {
        return type;
    }
}
