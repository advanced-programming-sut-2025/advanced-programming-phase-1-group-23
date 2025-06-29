package model.enums;
import model.enums.Ingredients;
import model.NPC.Pair;

import java.util.List;
import java.util.PropertyPermission;

public enum NPCinformation {
    Sebastian(
            new Pair(Ingredients.IRON, 50),
            new Pair(Ingredients.PUMPKIN_PIE, 1),
            new Pair(Ingredients.STONE, 150),
            new Pair(Ingredients.DIAMOND, 2),
            new Pair(Ingredients.CARROT, 12),
            new Pair(Ingredients.QUARTZ, 50)
    ),
    Abigail(
            new Pair(Ingredients.GOLD, 1),
            new Pair(Ingredients.PUMPKIN, 1),
            new Pair(Ingredients.WHEAT, 50),
            new Pair(Ingredients.EMERALD, 1),
            new Pair(Ingredients.IRON_ORE, 500),
            new Pair(Ingredients.IRIDIUM_SPRINKLER, 1)
    ),
    Harvey(
            new Pair(Ingredients.DAFFODIL, 12),
            new Pair(Ingredients.Salmon, 1),
            new Pair(Ingredients.Wine, 1),
            new Pair(Ingredients.Apple, 7),
            new Pair(Ingredients.COAL, 4),
            new Pair(Ingredients.SALAD, 5)
    ),
    Lea(
            new Pair(Ingredients.STONE, 10),
            new Pair(Ingredients.Salmon, 1),
            new Pair(Ingredients.WOOD, 200),
            new Pair(Ingredients.Cheese, 1),
            new Pair(Ingredients.Cherry, 12),
            new Pair(Ingredients.DELUXE_SCARECROW, 3)
    ),
    Robbin(
            new Pair(Ingredients.WOOD, 80),
            new Pair(Ingredients.IRON_ORE, 10),
            new Pair(Ingredients.WOOD, 1000),
            new Pair(Ingredients.Apricot, 5),
            new Pair(Ingredients.HONEY, 3),
            new Pair(Ingredients.TOPAZ, 2)
    );

    private final List<Pair> information;

    NPCinformation(Pair... items) {
        this.information = List.of(items);
    }

    public List<Pair> getInformation() {
        return information;
    }
}
