package src.main.java.model.Objects;

import src.main.java.model.enums.BarnType;

public class ShopBarn extends ShopItem {
    private final BarnType type;

    public ShopBarn(BarnType type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public BarnType getType() {
        return type;
    }
}
