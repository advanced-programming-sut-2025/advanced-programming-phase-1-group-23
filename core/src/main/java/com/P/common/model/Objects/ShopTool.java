package com.P.common.model.Objects;
import com.P.common.model.enums.ToolType;

import dev.morphia.annotations.Embedded;

@Embedded
public class ShopTool extends ShopItem {
    private  ToolType type;
    private  int price;

    public ShopTool(ToolType type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
        switch (type) {
            case MilkingCan, Scissors -> this.price = 1000;
            case FishingRod -> this.price = 25;
            default -> this.price = 0;
        }
    }

    public ShopTool() {
    }

    public ToolType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}
