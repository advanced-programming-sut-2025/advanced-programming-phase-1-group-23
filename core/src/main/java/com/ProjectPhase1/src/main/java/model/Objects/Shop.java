package model.Objects;

import model.Objects.ShopItem;
import model.Maps.Tile;

import java.util.ArrayList;

import dev.morphia.annotations.Embedded;
import model.enums.ShopItemLists.*;
import model.enums.ShopName;
import model.Maps.Building;

@Embedded
public class Shop extends Building {
    private final ShopName name;
    private final ArrayList<ShopItem> items;
    private final ArrayList<ShopItem> seasonItems;
    private int startingTime;
    private int finishingTime;
    //TODO : Add work time

    public Shop(ArrayList<Tile> tiles, ShopName name, int startingTime, int finishingTime) {
        super(tiles);
        this.name = name;
        this.items = new ArrayList<>();
        this.seasonItems = new ArrayList<>();
        this.startingTime = startingTime;
        this.finishingTime = finishingTime;
    }

    public ShopName getName() {
        return name;
    }

    public ArrayList<ShopItem> getItems() {
        return items;
    }

    public ArrayList<ShopItem> getSeasonItems() {
        return seasonItems;
    }

    public void GoodNight() {
        for (ShopItem shopItem : items)
            shopItem.resetNumberOfSold();
    }

    public void ChangeSeasonSpring() {
        this.items.clear();
        this.seasonItems.clear();
        switch (name) {
            case BlackSmith -> {
                this.items.addAll(ShopIngredientLists.BLACKSMITH.getShopIngredientList());
            }
            case MarnieRanch -> {
                this.items.addAll(ShopIngredientLists.RANCH.getShopIngredientList());
                this.items.addAll(ShopToolLists.RANCH.getShopToolList());
                this.items.addAll(ShopAnimalLists.RANCH.getShopAnimalList());
            }
            case StardropSaloon -> {
                this.items.addAll(ShopIngredientLists.STARDROP.getShopIngredientList());
                this.items.addAll(ShopRecipeLists.STARDROP.getShopRecipeList());
            }
            case CarpenterShop -> {
                this.items.addAll(ShopIngredientLists.CARPENTER.getShopIngredientList());
                this.items.addAll(ShopBarnLists.CARPENTER.getShopBarnList());
            }
            case JojaMart -> {
                this.items.addAll(ShopIngredientLists.JOJA_MART.getShopIngredientList());
                this.items.addAll(ShopSeedLists.YEAR_SEEDS_JOJA.getShopSeedList());
                this.items.addAll(ShopSeedLists.SPRING_SEEDS_JOJA.getShopSeedList());
            }
            case PierreGeneralStore -> {
                this.items.addAll(ShopIngredientLists.PIERRE_GENERAL.getShopIngredientList());
                this.items.addAll(ShopRecipeLists.PIERRE_GENERAL.getShopRecipeList());
                this.items.addAll(ShopSeedLists.SPRING_SEEDS_PIERRE.getShopSeedList());
                this.seasonItems.addAll(ShopSeedLists.SUMMER_SEEDS_PIERRE.getShopSeedList());
                this.seasonItems.addAll(ShopSeedLists.FALL_SEEDS_PIERRE.getShopSeedList());
            }
            case FishShop -> {
                this.items.addAll(ShopToolLists.FISH.getShopToolList());
                this.items.addAll(ShopIngredientLists.FISH.getShopIngredientList());
                this.items.addAll(ShopRecipeLists.FISH.getShopRecipeList());
            }
        }
    }

    public void ChangeSeasonSummer() {
        if (this.name != ShopName.JojaMart && this.name != ShopName.PierreGeneralStore)
            return;
        this.items.clear();
        this.seasonItems.clear();
        if (name == ShopName.JojaMart) {
            this.items.addAll(ShopIngredientLists.JOJA_MART.getShopIngredientList());
            this.items.addAll(ShopSeedLists.YEAR_SEEDS_JOJA.getShopSeedList());
            this.items.addAll(ShopSeedLists.SUMMER_SEEDS_JOJA.getShopSeedList());
        } else {
            this.items.addAll(ShopIngredientLists.PIERRE_GENERAL.getShopIngredientList());
            this.items.addAll(ShopRecipeLists.PIERRE_GENERAL.getShopRecipeList());
            this.seasonItems.addAll(ShopSeedLists.SPRING_SEEDS_PIERRE.getShopSeedList());
            this.items.addAll(ShopSeedLists.SUMMER_SEEDS_PIERRE.getShopSeedList());
            this.seasonItems.addAll(ShopSeedLists.FALL_SEEDS_PIERRE.getShopSeedList());
        }
    }

    public void ChangeSeasonFall() {
        if (this.name != ShopName.JojaMart && this.name != ShopName.PierreGeneralStore)
            return;
        this.items.clear();
        this.seasonItems.clear();
        if (name == ShopName.JojaMart) {
            this.items.addAll(ShopIngredientLists.JOJA_MART.getShopIngredientList());
            this.items.addAll(ShopSeedLists.YEAR_SEEDS_JOJA.getShopSeedList());
            this.items.addAll(ShopSeedLists.FALL_SEEDS_JOJA.getShopSeedList());
        } else {
            this.items.addAll(ShopIngredientLists.PIERRE_GENERAL.getShopIngredientList());
            this.items.addAll(ShopRecipeLists.PIERRE_GENERAL.getShopRecipeList());
            this.seasonItems.addAll(ShopSeedLists.SPRING_SEEDS_PIERRE.getShopSeedList());
            this.seasonItems.addAll(ShopSeedLists.SUMMER_SEEDS_PIERRE.getShopSeedList());
            this.items.addAll(ShopSeedLists.FALL_SEEDS_PIERRE.getShopSeedList());
        }
    }

    public void ChangeSeasonWinter() {
        if (this.name != ShopName.JojaMart && this.name != ShopName.PierreGeneralStore)
            return;
        this.items.clear();
        this.seasonItems.clear();
        if (name == ShopName.JojaMart) {
            this.items.addAll(ShopIngredientLists.JOJA_MART.getShopIngredientList());
            this.items.addAll(ShopSeedLists.YEAR_SEEDS_JOJA.getShopSeedList());
            this.items.addAll(ShopSeedLists.WINTER_SEEDS_JOJA.getShopSeedList());
        } else {
            this.items.addAll(ShopIngredientLists.PIERRE_GENERAL.getShopIngredientList());
            this.items.addAll(ShopRecipeLists.PIERRE_GENERAL.getShopRecipeList());
            this.seasonItems.addAll(ShopSeedLists.SPRING_SEEDS_PIERRE.getShopSeedList());
            this.seasonItems.addAll(ShopSeedLists.SUMMER_SEEDS_PIERRE.getShopSeedList());
            this.seasonItems.addAll(ShopSeedLists.FALL_SEEDS_PIERRE.getShopSeedList());
        }
    }

    public int getStartingTime() {
        return startingTime;
    }

    public int getFinishingTime() {
        return finishingTime;
    }
}
