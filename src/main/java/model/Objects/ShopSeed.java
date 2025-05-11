package src.main.java.model.Objects;
import model.enums.ForAgingSeeds;

public class ShopSeed extends ShopItem {
    private final ForAgingSeeds type;

    public ShopSeed(ForAgingSeeds type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public ForAgingSeeds getType() {
        return type;
    }
}
