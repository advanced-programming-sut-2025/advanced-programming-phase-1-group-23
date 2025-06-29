package model.Objects;
import model.enums.ForAgingSeeds;

import dev.morphia.annotations.Embedded;

@Embedded
public class ShopSeed extends ShopItem {
    private ForAgingSeeds type;

    public ShopSeed(ForAgingSeeds type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public ShopSeed() {
    }

    public ForAgingSeeds getType() {
        return type;
    }
}
