package src.main.java.model.enums.ShopItemLists;
import model.enums.Recipe;
import src.main.java.model.Objects.ShopRecipe;

import java.util.List;

public enum ShopRecipeLists {
    STARDROP(
            new ShopRecipe(Recipe.HashBrowns, 1),
            new ShopRecipe(Recipe.Olmelet, 1),
            new ShopRecipe(Recipe.Pancakes, 1),
            new ShopRecipe(Recipe.Bread, 1),
            new ShopRecipe(Recipe.Tortilla, 1),
            new ShopRecipe(Recipe.Pizza, 1),
            new ShopRecipe(Recipe.MakiRoll, 1),
            new ShopRecipe(Recipe.TripleShotEspresso, 1),
            new ShopRecipe(Recipe.Cookie, 1));

    private final List<ShopRecipe> shopRecipeList;

    ShopRecipeLists(ShopRecipe... items) {
        this.shopRecipeList = List.of(items);
    }

    public List<ShopRecipe> getShopRecipeList() {
        return shopRecipeList;
    }
}
