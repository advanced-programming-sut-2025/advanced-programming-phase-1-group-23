package model.Objects;

import model.NPC.NPC;
import src.main.java.model.Objects.ShopBarn;
import src.main.java.model.Objects.ShopItem;
import model.Maps.Tile;

import java.util.ArrayList;

import dev.morphia.annotations.Embedded;
import src.main.java.model.enums.ShopItemLists.*;
import src.main.java.model.enums.ShopName;
import model.Maps.Building;

@Embedded
public class Shop extends Building{
    private final ShopName name;
    private final NPC owner;
    private final ArrayList<ShopItem> items;
    private final ArrayList<ShopItem> seasonItems;
    //TODO : Add work time

    public Shop(ArrayList<Tile> tiles, ShopName name, NPC owner, ArrayList<ShopItem> items, ArrayList<ShopItem> seasonItems) {
        super(tiles);
        this.name = name;
        this.owner = owner;
        this.items = items;
        this.seasonItems = seasonItems;
    }

    public ShopName getName() {
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
                // TODO add recipes & permanent trees
                this.items.addAll(ShopSeedLists.SPRING_SEEDS_PIERRE.getShopSeedList());
                this.seasonItems.addAll(ShopSeedLists.SUMMER_SEEDS_PIERRE.getShopSeedList());
                this.seasonItems.addAll(ShopSeedLists.FALL_SEEDS_PIERRE.getShopSeedList());
            }
            case FishShop -> {
                this.items.addAll(ShopToolLists.FISH.getShopToolList());
                this.items.addAll(ShopIngredientLists.FISH.getShopIngredientList());
                //TODO : add fish smoker + upgrade
            }
        }
    }

    public void ChangeSeasonSummer() {
        if(this.name != ShopName.JojaMart && this.name != ShopName.PierreGeneralStore)
            return;
        this.items.clear();
        this.seasonItems.clear();
        if(name == ShopName.JojaMart) {
            this.items.addAll(ShopIngredientLists.JOJA_MART.getShopIngredientList());
            this.items.addAll(ShopSeedLists.YEAR_SEEDS_JOJA.getShopSeedList());
            this.items.addAll(ShopSeedLists.SUMMER_SEEDS_JOJA.getShopSeedList());
        }
        else {
            this.items.addAll(ShopIngredientLists.PIERRE_GENERAL.getShopIngredientList());
            // TODO add recipes & permanent trees
            this.seasonItems.addAll(ShopSeedLists.SPRING_SEEDS_PIERRE.getShopSeedList());
            this.items.addAll(ShopSeedLists.SUMMER_SEEDS_PIERRE.getShopSeedList());
            this.seasonItems.addAll(ShopSeedLists.FALL_SEEDS_PIERRE.getShopSeedList());
        }
    }

    public void ChangeSeasonFall() {
        if(this.name != ShopName.JojaMart && this.name != ShopName.PierreGeneralStore)
            return;
        this.items.clear();
        this.seasonItems.clear();
        if(name == ShopName.JojaMart) {
            this.items.addAll(ShopIngredientLists.JOJA_MART.getShopIngredientList());
            this.items.addAll(ShopSeedLists.YEAR_SEEDS_JOJA.getShopSeedList());
            this.items.addAll(ShopSeedLists.FALL_SEEDS_JOJA.getShopSeedList());
        }
        else {
            this.items.addAll(ShopIngredientLists.PIERRE_GENERAL.getShopIngredientList());
            // TODO add recipes & permanent trees
            this.seasonItems.addAll(ShopSeedLists.SPRING_SEEDS_PIERRE.getShopSeedList());
            this.seasonItems.addAll(ShopSeedLists.SUMMER_SEEDS_PIERRE.getShopSeedList());
            this.items.addAll(ShopSeedLists.FALL_SEEDS_PIERRE.getShopSeedList());
        }
    }

    public void ChangeSeasonWinter() {
        if(this.name != ShopName.JojaMart && this.name != ShopName.PierreGeneralStore)
            return;
        this.items.clear();
        this.seasonItems.clear();
        if(name == ShopName.JojaMart) {
            this.items.addAll(ShopIngredientLists.JOJA_MART.getShopIngredientList());
            this.items.addAll(ShopSeedLists.YEAR_SEEDS_JOJA.getShopSeedList());
            this.items.addAll(ShopSeedLists.WINTER_SEEDS_JOJA.getShopSeedList());
        }
        else {
            this.items.addAll(ShopIngredientLists.PIERRE_GENERAL.getShopIngredientList());
            // TODO add recipes & permanent trees
            this.seasonItems.addAll(ShopSeedLists.SPRING_SEEDS_PIERRE.getShopSeedList());
            this.seasonItems.addAll(ShopSeedLists.SUMMER_SEEDS_PIERRE.getShopSeedList());
            this.seasonItems.addAll(ShopSeedLists.FALL_SEEDS_PIERRE.getShopSeedList());
        }
    }
}
