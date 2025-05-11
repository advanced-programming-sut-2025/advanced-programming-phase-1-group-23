package model.Objects;

import model.NPC.NPC;
import src.main.java.model.Objects.ShopItem;
import model.Maps.Tile;

import java.util.ArrayList;

public class Shop {
    private final ArrayList<Tile> tiles;
    private final String name;
    private final NPC owner;
    private final ArrayList<ShopItem> items;
    private final ArrayList<ShopItem> seasonItems;
    //TODO : Add work time

    public Shop(ArrayList<Tile> tiles, String name, NPC owner, ArrayList<ShopItem> items, ArrayList<ShopItem> seasonItems) {
        this.tiles = tiles;
        this.name = name;
        this.owner = owner;
        this.items = items;
        this.seasonItems = seasonItems;
    }

    public String getName() {
        return name;
    }

    public NPC getOwner() {
        return owner;
    }

    public ArrayList<ShopItem> getItems() {
        return items;
    }

    public ArrayList<ShopItem> getSeasonItems() {
        return seasonItems;
    }

    public void GoodNight() {
        for(ShopItem shopItem : items)
            shopItem.resetNumberOfSold();
    }
}
