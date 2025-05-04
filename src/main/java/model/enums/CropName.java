package model.enums;

import model.Naturals.Crop;

public enum CropName {

        BlueJazz("Blue Jazz", "Jazz Seeds", new int[]{1, 2, 2, 2}, 7, true, 0, 50, true, 45, new Season[]{Season.SPRING}, false),
        Carrot("Carrot", "Carrot Seeds", new int[]{1, 1, 1}, 3, true, 0, 35, true, 75, new Season[]{Season.SPRING}, false),
        Cauliflower("Cauliflower", "Cauliflower Seeds", new int[]{1, 2, 4, 4, 1}, 12, true, 0, 175, true, 75, new Season[]{Season.SPRING}, true),
        CoffeeBean("Coffee Bean", "Coffee Bean", new int[]{1, 2, 2, 3, 2}, 10, false, 2, 15, false, 0, new Season[]{Season.SPRING, Season.SUMMER}, false),
        Garlic("Garlic", "Garlic Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 60, true, 20, new Season[]{Season.SPRING}, false),
        GreenBean("Green Bean", "Bean Starter", new int[]{1, 1, 1, 3, 4}, 10, false, 3, 40, true, 25, new Season[]{Season.SPRING}, false),
        Kale("Kale", "Kale Seeds", new int[]{1, 2, 2, 1}, 6, true, 0, 110, true, 50, new Season[]{Season.SPRING}, false),
        Parsnip("Parsnip", "Parsnip Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 35, true, 25, new Season[]{Season.SPRING}, false),
        Potato("Potato", "Potato Seeds", new int[]{1, 1, 1, 2, 1}, 6, true, 0, 80, true, 25, new Season[]{Season.SPRING}, false),
        Rhubarb("Rhubarb", "Rhubarb Seeds", new int[]{2, 2, 2, 3, 4}, 13, true, 0, 220, false, 0, new Season[]{Season.SPRING}, false),
        Strawberry("Strawberry", "Strawberry Seeds", new int[]{1, 1, 2, 2, 2}, 8, false, 4, 120, true, 50, new Season[]{Season.SPRING}, false),
        Tulip("Tulip", "Tulip Bulb", new int[]{1, 1, 2, 2}, 6, true, 0, 30, true, 45, new Season[]{Season.SPRING}, false),
        UnmilledRice("Unmilled Rice", "Rice Shoot", new int[]{1, 2, 2, 3}, 8, true, 0, 30, true, 3, new Season[]{Season.SPRING}, false),
        Blueberry("Blueberry", "Blueberry Seeds", new int[]{1, 3, 3, 4, 2}, 13, false, 4, 50, true, 25, new Season[]{Season.SUMMER}, false),
        Corn("Corn", "Corn Seeds", new int[]{2, 3, 3, 3, 3}, 14, false, 4, 50, true, 25, new Season[]{Season.SUMMER, Season.AUTUMN}, false),
        Hops("Hops", "Hops Starter", new int[]{1, 1, 2, 3, 4}, 11, false, 1, 25, true, 45, new Season[]{Season.SUMMER}, false),
        HotPepper("Hot Pepper", "Pepper Seeds", new int[]{1, 1, 1, 1, 1}, 5, false, 3, 40, true, 13, new Season[]{Season.SUMMER}, false),
        Melon("Melon", "Melon Seeds", new int[]{1, 2, 3, 3, 3}, 12, true, 0, 250, true, 113, new Season[]{Season.SUMMER}, true),
        Poppy("Poppy", "Poppy Seeds", new int[]{1, 2, 2, 2}, 7, true, 0, 140, true, 45, new Season[]{Season.SUMMER}, false),
        Radish("Radish", "Radish Seeds", new int[]{2, 1, 2, 1}, 6, true, 0, 90, true, 45, new Season[]{Season.SUMMER}, false),
        RedCabbage("Red Cabbage", "Red Cabbage Seeds", new int[]{2, 1, 2, 2, 2}, 9, true, 0, 260, true, 75, new Season[]{Season.SUMMER}, false),
        Starfruit("Starfruit", "Starfruit Seeds", new int[]{2, 3, 2, 3, 3}, 13, true, 0, 750, true, 125, new Season[]{Season.SUMMER}, false),
        SummerSpangle("Summer Spangle", "Spangle Seeds", new int[]{1, 2, 3, 1}, 8, true, 0, 90, true, 45, new Season[]{Season.SUMMER}, false),
        SummerSquash("Summer Squash", "Summer Squash Seeds", new int[]{1, 1, 1, 2, 1}, 6, false, 3, 45, true, 63, new Season[]{Season.SUMMER}, false),
        Sunflower("Sunflower", "Sunflower Seeds", new int[]{1, 2, 3, 2}, 8, true, 0, 80, true, 45, new Season[]{Season.SUMMER, Season.AUTUMN}, false),
        Tomato("Tomato", "Tomato Seeds", new int[]{2, 2, 2, 2, 3}, 11, false, 4, 60, true, 20, new Season[]{Season.SUMMER}, false),
        Wheat("Wheat", "Wheat Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 25, false, 0, new Season[]{Season.SUMMER, Season.AUTUMN}, false),
        Amaranth("Amaranth", "Amaranth Seeds", new int[]{1, 2, 2, 2}, 7, true, 0, 150, true, 50, new Season[]{Season.AUTUMN}, false),
        Artichoke("Artichoke", "Artichoke Seeds", new int[]{2, 2, 1, 2, 1}, 8, true, 0, 160, true, 30, new Season[]{Season.AUTUMN}, false),
        Beet("Beet", "Beet Seeds", new int[]{1, 1, 2, 2}, 6, true, 0, 100, true, 30, new Season[]{Season.AUTUMN}, false),
        BokChoy("Bok Choy", "Bok Choy Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 80, true, 25, new Season[]{Season.AUTUMN}, false),
        Broccoli("Broccoli", "Broccoli Seeds", new int[]{2, 2, 2, 2}, 8, false, 4, 70, true, 63, new Season[]{Season.AUTUMN}, false),
        Cranberries("Cranberries", "Cranberry Seeds", new int[]{1, 2, 1, 1, 2}, 7, false, 5, 75, true, 38, new Season[]{Season.AUTUMN}, false),
        Eggplant("Eggplant", "Eggplant Seeds", new int[]{1, 1, 1, 1}, 5, false, 5, 60, true, 20, new Season[]{Season.AUTUMN}, false),
        FairyRose("Fairy Rose", "Fairy Seeds", new int[]{1, 4, 4, 3}, 12, true, 0, 290, true, 45, new Season[]{Season.AUTUMN}, false),
        Grape("Grape", "Grape Starter", new int[]{1, 1, 2, 3, 3}, 10, false, 3, 80, true, 38, new Season[]{Season.AUTUMN}, false),
        Pumpkin("Pumpkin", "Pumpkin Seeds", new int[]{1, 2, 3, 4, 3}, 13, true, 0, 320, false, 0, new Season[]{Season.AUTUMN}, true),
        Yam("Yam", "Yam Seeds", new int[]{1, 3, 3, 3}, 10, true, 0, 160, true, 45, new Season[]{Season.AUTUMN}, false),
        SweetGemBerry("Sweet Gem Berry", "Rare Seed", new int[]{2, 4, 6, 6, 6}, 24, true, 0, 3000, false, 0, new Season[]{Season.AUTUMN}, false),
        Powdermelon("Powdermelon", "Powdermelon Seeds", new int[]{1, 2, 1, 2, 1}, 7, true, 0, 60, true, 63, new Season[]{Season.WINTER}, true),
        AncientFruit("Ancient Fruit", "Ancient Seeds", new int[]{2, 7, 7, 7, 5}, 28, false, 7, 550, false, 0, new Season[]{Season.AUTUMN, Season.SUMMER, Season.SPRING}, false);

        private final String name;
        private final String source;
        private final int[] stages;
        private int totalHarvestTime;
        private boolean oneTime;
        private int regrowthTime;
        private int baseSellPrice;
        private final boolean isEatable;
        private int energy;
        private final Season[] season;
        private boolean canBecomeGiant;

        CropName(String name, String source, int[] stages, int totalHarvestTime,
                 boolean oneTime, int regrowthTime, int baseSellPrice, boolean isEatable, int energy,
                 Season[] season, boolean canBecomeGiant) {
                this.name = name;
                this.source = source;
                this.stages = stages;
                this.totalHarvestTime = totalHarvestTime;
                this.oneTime = oneTime;
                this.regrowthTime = regrowthTime;
                this.baseSellPrice = baseSellPrice;
                this.isEatable = isEatable;
                this.energy = energy;
                this.canBecomeGiant = canBecomeGiant;
                this.season = season;
        }

        public String getName() {
                return name;
        }


        public String getSource() {
                return source;
        }

        public int getTotalHarvestTime() {
                return totalHarvestTime;
        }

        public void setTotalHarvestTime(int totalHarvestTime) {
                this.totalHarvestTime = totalHarvestTime;
        }

        public int[] getStages() {
                return stages;
        }

        public boolean isOneTime() {
                return oneTime;
        }

        public void setOneTime(boolean oneTime) {
                this.oneTime = oneTime;
        }

        public int getRegrowthTime() {
                return regrowthTime;
        }

        public void setRegrowthTime(int regrowthTime) {
                this.regrowthTime = regrowthTime;
        }

        public boolean isEatable() {
                return isEatable;
        }

        public int getBaseSellPrice() {
                return baseSellPrice;
        }

        public void setBaseSellPrice(int baseSellPrice) {
                this.baseSellPrice = baseSellPrice;
        }

        public int getEnergy() {
                return energy;
        }

        public void setEnergy(int energy) {
                this.energy = energy;
        }

        public boolean canBecomeGiant() {
                return canBecomeGiant;
        }

        public Season[] getSeason() {
                return season;
        }
}


