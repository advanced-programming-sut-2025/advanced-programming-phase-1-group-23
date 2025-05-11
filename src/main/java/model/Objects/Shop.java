package model.Objects;

import model.NPC.NPCs;
import src.main.java.model.Objects.ShopIngredient;
import src.main.java.model.Objects.ShopItem;

import java.util.ArrayList;

public class Shop {
    private final ArrayList<ShopItem> items;

    public Shop(ArrayList<ShopItem> items) {
        this.items = items;
    }

    public ArrayList<ShopItem> getItems() {
        return items;
    }

    public void GoodNight() {
        for(ShopItem shopItem : items)
            shopItem.resetNumberOfSold();
    }
}
