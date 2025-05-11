package src.main.java.model.enums.ShopItemLists;

import model.enums.Ingredients;
import src.main.java.model.Objects.ShopIngredient;

import java.util.List;

public enum ShopIngredientLists {
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
            new ShopIngredient(Ingredients.COFFEE, 1000000)),
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
            new ShopIngredient(Ingredients.SUGAR, 1000000));

    private final List<ShopIngredient> shopIngredientList;

    ShopIngredientLists(ShopIngredient... items) {
        this.shopIngredientList = List.of(items);
    }

    public List<ShopIngredient> getShopIngredientList() {
        return shopIngredientList;
    }
}
