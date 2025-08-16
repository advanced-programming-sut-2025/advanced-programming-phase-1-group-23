package com.P.common.model.enums.ShopItemLists;

import com.P.common.model.enums.AnimalType;
import com.P.common.model.Objects.ShopAnimal;

import java.util.List;

public enum ShopAnimalLists {
    RANCH(
            new ShopAnimal(AnimalType.Hen, 2),
            new ShopAnimal(AnimalType.Duck, 2),
            new ShopAnimal(AnimalType.Rabbit, 2),
            new ShopAnimal(AnimalType.Dinosaur, 2),
            new ShopAnimal(AnimalType.Cow, 2),
            new ShopAnimal(AnimalType.Goat, 2),
            new ShopAnimal(AnimalType.Sheep, 2),
            new ShopAnimal(AnimalType.Pig, 2));

    private final List<ShopAnimal> shopAnimalList;

    ShopAnimalLists(ShopAnimal... items) {
        this.shopAnimalList = List.of(items);
    }

    public List<ShopAnimal> getShopAnimalList() {
        return shopAnimalList;
    }
}
