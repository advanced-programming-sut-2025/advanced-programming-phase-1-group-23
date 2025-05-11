package src.main.java.model.Objects;
import model.enums.AnimalType;

public class ShopAnimal extends ShopItem {
    private final AnimalType type;

    public ShopAnimal(AnimalType type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public AnimalType getType() {
        return type;
    }
}