package com.P.common.model.enums.ShopItemLists;

import com.P.common.model.Objects.ShopBarn;
import com.P.common.model.enums.BarnType;

import java.util.List;

public enum ShopBarnLists {
    CARPENTER (
            new ShopBarn(BarnType.SimpleCoop, 1),
            new ShopBarn(BarnType.BigCoop, 1),
            new ShopBarn(BarnType.DeluxeCoop, 1),
            new ShopBarn(BarnType.SimpleBarn, 1),
            new ShopBarn(BarnType.BigBarn, 1),
            new ShopBarn(BarnType.DeluxeBarn, 1));

    private final List<ShopBarn> shopBarnList;

    ShopBarnLists(ShopBarn... items) {
        this.shopBarnList = List.of(items);
    }

    public List<ShopBarn> getShopBarnList() {
        return shopBarnList;
    }
}
