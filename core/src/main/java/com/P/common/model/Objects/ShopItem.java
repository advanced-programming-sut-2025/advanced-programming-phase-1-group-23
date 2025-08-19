package com.P.common.model.Objects;

import dev.morphia.annotations.Embedded;

@Embedded
public abstract class ShopItem {
    private int dailyLimit;
    private int numberOfSold;

    public ShopItem(int dailyLimit) {
        this.dailyLimit = dailyLimit;
        this.numberOfSold = 0;
    }

    public ShopItem() {
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public int getNumberOfSold() {
        return numberOfSold;
    }

    public void increaseNumberOfSold(int amount) {
        this.numberOfSold += amount;
    }

    public void resetNumberOfSold() {
        this.numberOfSold = 0;
    }

}
