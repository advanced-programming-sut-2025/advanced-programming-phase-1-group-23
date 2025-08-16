package com.P.common.model.Objects;
import com.P.common.model.enums.ForAgingSeeds;

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
