package com.P.common.model.Objects;

import com.P.common.model.enums.AnimalType;

import dev.morphia.annotations.Embedded;

@Embedded
public class ShopAnimal extends ShopItem {
    private AnimalType type;

    public ShopAnimal(AnimalType type, int dailyLimit) {
        super(dailyLimit);
        this.type = type;
    }

    public ShopAnimal() {
    }

    public AnimalType getType() {
        return type;
    }
}
