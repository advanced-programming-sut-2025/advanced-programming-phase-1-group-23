package model.enums.ShopItemLists;

import model.enums.Ingredients;
import model.Objects.ShopIngredient;

import java.util.List;

public enum ShopIngredientLists {
    BLACKSMITH(
            new ShopIngredient(Ingredients.COOPER_ORE, 1000000),
            new ShopIngredient(Ingredients.IRON_ORE, 1000000),
            new ShopIngredient(Ingredients.GOLD_ORE, 1000000),
            new ShopIngredient(Ingredients.COAL, 1000000)),
    CARPENTER(
            new ShopIngredient(Ingredients.WOOD, 1000000),
            new ShopIngredient(Ingredients.STONE, 1000000)),
    RANCH(
            new ShopIngredient(Ingredients.HAY, 1000000)),
    STARDROP(
            new ShopIngredient(Ingredients.BEER, 1000000),
            new ShopIngredient(Ingredients.SALAD, 1000000),
            new ShopIngredient(Ingredients.BREAD, 100000),
            new ShopIngredient(Ingredients.SPAGHETTI, 1000000),
            new ShopIngredient(Ingredients.PIZZA, 1000000),
            new ShopIngredient(Ingredients.COFFEE, 1000000),
            new ShopIngredient(Ingredients.SpeedGro, 1000000),
            new ShopIngredient(Ingredients.BasicRetainingSoil, 1000000),
            new ShopIngredient(Ingredients.QualityRetainingSoil, 1000000),
            new ShopIngredient(Ingredients.DeluxeRetainingSoil, 1000000),
            new ShopIngredient(Ingredients.Bouquet, 1000000),
            new ShopIngredient(Ingredients.WeddingRing, 1000000)
            ),
    PIERRE_GENERAL(
            new ShopIngredient(Ingredients.RICE, 1000000),
            new ShopIngredient(Ingredients.WHEAT_FLOUR, 1000000),
            new ShopIngredient(Ingredients.SUGAR, 1000000),
            new ShopIngredient(Ingredients.OIL, 1000000),
            new ShopIngredient(Ingredients.VINEGAR, 1000000)),
    JOJA_MART(
            new ShopIngredient(Ingredients.JOJA_COLA, 1000000),
            new ShopIngredient(Ingredients.RICE, 1000000),
            new ShopIngredient(Ingredients.WHEAT_FLOUR, 1000000),
            new ShopIngredient(Ingredients.SUGAR, 1000000)),
    FISH(
            new ShopIngredient(Ingredients.TROUT_SOUP, 1)
    );

    private final List<ShopIngredient> shopIngredientList;

    ShopIngredientLists(ShopIngredient... items) {
        this.shopIngredientList = List.of(items);
    }

    public List<ShopIngredient> getShopIngredientList() {
        return shopIngredientList;
    }
}
