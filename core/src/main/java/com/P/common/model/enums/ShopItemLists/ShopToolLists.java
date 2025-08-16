package com.P.common.model.enums.ShopItemLists;

import com.P.common.model.enums.ToolType;
import com.P.common.model.Objects.ShopTool;

import java.util.List;

public enum ShopToolLists {
    RANCH(
            new ShopTool(ToolType.MilkingCan, 1),
            new ShopTool(ToolType.Scissors, 1)),
    FISH(
            new ShopTool(ToolType.FishingRod, 1));

    private final List<ShopTool> shopToolList;

    ShopToolLists(ShopTool... items) {
        this.shopToolList = List.of(items);
    }

    public List<ShopTool> getShopToolList() {
        return shopToolList;
    }
}
