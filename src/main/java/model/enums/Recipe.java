package model.enums;

import java.util.HashMap;
import java.util.Map;

public enum Recipe {

        FriedEgg("Fried egg", new HashMap<>() {{put(Ingredients.EGG, 1);}}, 50, 35, Ingredients.FRIED_EGG),
        BakedFish("Baked Fish", (HashMap<Ingredients, Integer>) Map.of(Ingredients.SARDINE, 1, Ingredients.SALMON, 1, Ingredients.WHEAT_FLOUR, 1), 75, 100, Ingredients.BAKED_FISH),
        Salad("Salad", (HashMap<Ingredients, Integer>) Map.of(Ingredients.LEEK, 1, Ingredients.DANDELION, 1), 113,  110, Ingredients.SALAD),
        Olmelet("Olmelet", (HashMap<Ingredients, Integer>) Map.of(Ingredients.EGG, 1, Ingredients.MILK, 1), 100, 125, Ingredients.OMELET),
        PumpkinPie("pumpkin pie", (HashMap<Ingredients, Integer>) Map.of(Ingredients.PUMPKIN, 1, Ingredients.WHEAT_FLOUR, 1, Ingredients.MILK, 1, Ingredients.SUGAR, 1), 225,  385, Ingredients.PUMPKIN_PIE),
        Spaghetti("spaghetti", (HashMap<Ingredients, Integer>) Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.TOMATO, 1), 75,  120, Ingredients.SPAGHETTI),
        Pizza("pizza", (HashMap<Ingredients, Integer>) Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.TOMATO, 1, Ingredients.CHEESE, 1), 150, 300, Ingredients.PIZZA),
        Tortilla("Tortilla", (HashMap<Ingredients, Integer>) Map.of(Ingredients.CORN, 1), 50, 50, Ingredients.TORTILLA),
        MakiRoll("Maki Roll", (HashMap<Ingredients, Integer>) Map.of(Ingredients.FIBER, 1, Ingredients.RICE, 1), 100, 220, Ingredients.MAKI_ROLL), // any fish: توضیح در ادامه
        TripleShotEspresso("Triple Shot Espresso", (HashMap<Ingredients, Integer>) Map.of(Ingredients.COFFEE, 3), 200,  450, Ingredients.TRIPLE_SHOT_ESPRESSO),
        Cookie("Cookie", (HashMap<Ingredients, Integer>) Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.SUGAR, 1, Ingredients.EGG, 1), 90,  140, Ingredients.COOKIE),
        HashBrowns("hash browns", (HashMap<Ingredients, Integer>) Map.of(Ingredients.POTATO, 1, Ingredients.OIL, 1), 90,  120, Ingredients.HASH_BROWNS),
        Pancakes("pancakes", (HashMap<Ingredients, Integer>) Map.of(Ingredients.WHEAT_FLOUR, 1, Ingredients.EGG, 1), 90,  80, Ingredients.PANCAKES),
        FruitSalad("fruit salad", (HashMap<Ingredients, Integer>) Map.of(Ingredients.BLUEBERRY, 1, Ingredients.MELON, 1, Ingredients.APRICOT, 1), 263,  450, Ingredients.FRUIT_SALAD),
        RedPlate("red plate", (HashMap<Ingredients, Integer>) Map.of(Ingredients.RED_CABBAGE, 1, Ingredients.RADISH, 1), 240,  400, Ingredients.RED_PLATE),
        Bread("bread", (HashMap<Ingredients, Integer>) Map.of(Ingredients.WHEAT_FLOUR, 1), 50,  60, Ingredients.BREAD),
        SalmonDinner("salmon dinner", (HashMap<Ingredients, Integer>) Map.of(Ingredients.SALMON, 1, Ingredients.AMARANTH, 1, Ingredients.KALE, 1), 125, 300, Ingredients.SALMON_DINNER),
        VegetableMedley("vegetable medley", (HashMap<Ingredients, Integer>) Map.of(Ingredients.TOMATO, 1, Ingredients.BEET, 1), 165, 120, Ingredients.VEGETABLE_MEDLEY),
        FarmersLunch("farmer's lunch", (HashMap<Ingredients, Integer>) Map.of(Ingredients.PARSNIP, 1), 200,  150, Ingredients.FARMERS_LUNCH), // فرض کردیم املت جدا تعریف شده
        SurvivalBurger("survival burger", (HashMap<Ingredients, Integer>) Map.of(Ingredients.BREAD, 1, Ingredients.CARROT, 1, Ingredients.EGGPLANT, 1), 125,  180,Ingredients.SURVIVAL_BURGER),
        DishOTheSea("dish O' the Sea", (HashMap<Ingredients, Integer>) Map.of(Ingredients.SARDINE, 2), 150,  220, Ingredients.SEA_DISH), // فرض بر این که hash browns جدا تعریف شده
        SeafoamPudding("seaform Pudding", (HashMap<Ingredients, Integer>) Map.of(Ingredients.FLOUNDER, 1, Ingredients.MIDNIGHT_CARP, 1), 175,  300, Ingredients.SEAFORM_PUDDING),
        MinersTreat("miner's treat", (HashMap<Ingredients, Integer>) Map.of(Ingredients.CARROT, 2, Ingredients.SUGAR, 1, Ingredients.MILK, 1), 125,  200,Ingredients.MINERS_TREAT)


        ;

        private final String name;
        private final HashMap<Ingredients, Integer> ingredients;
        private final int energy;
        private final int sellPrice;
        private final Ingredients foodMade;



        Recipe(String name, HashMap<Ingredients, Integer> ingredients, int energy, int sellPrice, Ingredients foodMade) {
            this.name = name;
            this.ingredients = ingredients;
            this.energy = energy;
            this.sellPrice = sellPrice;
            this.foodMade = foodMade;
        }

        public Ingredients getFoodMade() {
                return foodMade;
        }

        public String getName() {
                return name;
        }

        public int getEnergy() {
                return energy;
        }

        public HashMap<Ingredients, Integer> getIngredients() {
                return ingredients;
        }

        public int getSellPrice() {
                return sellPrice;
        }
}

