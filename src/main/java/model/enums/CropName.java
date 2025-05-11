package model.enums;

import model.Naturals.Crop;

import javax.xml.stream.events.EntityReference;

public enum CropName {

        BlueJazz("Blue Jazz", ForAgingSeeds.jazzSeeds, new int[]{1, 2, 2, 2}, 7, true, 0, 50, true, 45, new Season[]{Season.SPRING}, false),
        Carrot("Carrot", ForAgingSeeds.carrotSeeds, new int[]{1, 1, 1}, 3, true, 0, 35, true, 75, new Season[]{Season.SPRING}, false),
        Cauliflower("Cauliflower", ForAgingSeeds.cauliflowerSeeds, new int[]{1, 2, 4, 4, 1}, 12, true, 0, 175, true, 75, new Season[]{Season.SPRING}, true),
        CoffeeBean("Coffee Bean", ForAgingSeeds.coffeeBean, new int[]{1, 2, 2, 3, 2}, 10, false, 2, 15, false, 0, new Season[]{Season.SPRING, Season.SUMMER}, false),
        Garlic("Garlic", ForAgingSeeds.garlicSeeds, new int[]{1, 1, 1, 1}, 4, true, 0, 60, true, 20, new Season[]{Season.SPRING}, false),
        GreenBean("Green Bean", ForAgingSeeds.beanStarter, new int[]{1, 1, 1, 3, 4}, 10, false, 3, 40, true, 25, new Season[]{Season.SPRING}, false),
        Kale("Kale", ForAgingSeeds.kaleSeeds, new int[]{1, 2, 2, 1}, 6, true, 0, 110, true, 50, new Season[]{Season.SPRING}, false),
        Parsnip("Parsnip", ForAgingSeeds.parsnipSeeds, new int[]{1, 1, 1, 1}, 4, true, 0, 35, true, 25, new Season[]{Season.SPRING}, false),
        Potato("Potato", ForAgingSeeds.potatoSeeds, new int[]{1, 1, 1, 2, 1}, 6, true, 0, 80, true, 25, new Season[]{Season.SPRING}, false),
        Rhubarb("Rhubarb", ForAgingSeeds.rhubarbSeeds, new int[]{2, 2, 2, 3, 4}, 13, true, 0, 220, false, 0, new Season[]{Season.SPRING}, false),
        Strawberry("Strawberry", ForAgingSeeds.strawberrySeeds, new int[]{1, 1, 2, 2, 2}, 8, false, 4, 120, true, 50, new Season[]{Season.SPRING}, false),
        Tulip("Tulip", ForAgingSeeds.tulipBulb, new int[]{1, 1, 2, 2}, 6, true, 0, 30, true, 45, new Season[]{Season.SPRING}, false),
        UnmilledRice("Unmilled Rice", ForAgingSeeds.riceShoot, new int[]{1, 2, 2, 3}, 8, true, 0, 30, true, 3, new Season[]{Season.SPRING}, false),
        Blueberry("Blueberry", ForAgingSeeds.blueberrySeeds , new int[]{1, 3, 3, 4, 2}, 13, false, 4, 50, true, 25, new Season[]{Season.SUMMER}, false),
        Corn("Corn", ForAgingSeeds.cornSeeds, new int[]{2, 3, 3, 3, 3}, 14, false, 4, 50, true, 25, new Season[]{Season.SUMMER, Season.AUTUMN}, false),
        Hops("Hops", ForAgingSeeds.hopsStarter, new int[]{1, 1, 2, 3, 4}, 11, false, 1, 25, true, 45, new Season[]{Season.SUMMER}, false),
        HotPepper("Hot Pepper", ForAgingSeeds.pepperSeeds, new int[]{1, 1, 1, 1, 1}, 5, false, 3, 40, true, 13, new Season[]{Season.SUMMER}, false),
        Melon("Melon", ForAgingSeeds.melonSeeds, new int[]{1, 2, 3, 3, 3}, 12, true, 0, 250, true, 113, new Season[]{Season.SUMMER}, true),
        Poppy("Poppy", ForAgingSeeds.poppySeeds, new int[]{1, 2, 2, 2}, 7, true, 0, 140, true, 45, new Season[]{Season.SUMMER}, false),
        Radish("Radish", ForAgingSeeds.radishSeeds, new int[]{2, 1, 2, 1}, 6, true, 0, 90, true, 45, new Season[]{Season.SUMMER}, false),
        RedCabbage("Red Cabbage", ForAgingSeeds.redCabbageSeeds, new int[]{2, 1, 2, 2, 2}, 9, true, 0, 260, true, 75, new Season[]{Season.SUMMER}, false),
        Starfruit("Starfruit", ForAgingSeeds.starfruitSeeds, new int[]{2, 3, 2, 3, 3}, 13, true, 0, 750, true, 125, new Season[]{Season.SUMMER}, false),
        SummerSpangle("Summer Spangle", ForAgingSeeds.spangleSeeds, new int[]{1, 2, 3, 1}, 8, true, 0, 90, true, 45, new Season[]{Season.SUMMER}, false),
        SummerSquash("Summer Squash", ForAgingSeeds.summerSquashSeeds, new int[]{1, 1, 1, 2, 1}, 6, false, 3, 45, true, 63, new Season[]{Season.SUMMER}, false),
        Sunflower("Sunflower", ForAgingSeeds.sunflowerSeeds, new int[]{1, 2, 3, 2}, 8, true, 0, 80, true, 45, new Season[]{Season.SUMMER, Season.AUTUMN}, false),
        Tomato("Tomato", ForAgingSeeds.tomatoSeeds, new int[]{2, 2, 2, 2, 3}, 11, false, 4, 60, true, 20, new Season[]{Season.SUMMER}, false),
        Wheat("Wheat", ForAgingSeeds.wheatSeeds, new int[]{1, 1, 1, 1}, 4, true, 0, 25, false, 0, new Season[]{Season.SUMMER, Season.AUTUMN}, false),
        Amaranth("Amaranth", ForAgingSeeds.amaranthSeeds, new int[]{1, 2, 2, 2}, 7, true, 0, 150, true, 50, new Season[]{Season.AUTUMN}, false),
        Artichoke("Artichoke", ForAgingSeeds.artichokeSeeds, new int[]{2, 2, 1, 2, 1}, 8, true, 0, 160, true, 30, new Season[]{Season.AUTUMN}, false),
        Beet("Beet", ForAgingSeeds.beetSeeds, new int[]{1, 1, 2, 2}, 6, true, 0, 100, true, 30, new Season[]{Season.AUTUMN}, false),
        BokChoy("Bok Choy", ForAgingSeeds.bokChoySeeds, new int[]{1, 1, 1, 1}, 4, true, 0, 80, true, 25, new Season[]{Season.AUTUMN}, false),
        Broccoli("Broccoli", ForAgingSeeds.broccoliSeeds, new int[]{2, 2, 2, 2}, 8, false, 4, 70, true, 63, new Season[]{Season.AUTUMN}, false),
        Cranberries("Cranberries", ForAgingSeeds.cranberrySeeds, new int[]{1, 2, 1, 1, 2}, 7, false, 5, 75, true, 38, new Season[]{Season.AUTUMN}, false),
        Eggplant("Eggplant", ForAgingSeeds.eggplantSeeds, new int[]{1, 1, 1, 1}, 5, false, 5, 60, true, 20, new Season[]{Season.AUTUMN}, false),
        FairyRose("Fairy Rose", ForAgingSeeds.fairySeeds, new int[]{1, 4, 4, 3}, 12, true, 0, 290, true, 45, new Season[]{Season.AUTUMN}, false),
        Grape("Grape", ForAgingSeeds.grapeStarter, new int[]{1, 1, 2, 3, 3}, 10, false, 3, 80, true, 38, new Season[]{Season.AUTUMN}, false),
        Pumpkin("Pumpkin", ForAgingSeeds.pumpkinSeeds, new int[]{1, 2, 3, 4, 3}, 13, true, 0, 320, false, 0, new Season[]{Season.AUTUMN}, true),
        Yam("Yam", ForAgingSeeds.yamSeeds, new int[]{1, 3, 3, 3}, 10, true, 0, 160, true, 45, new Season[]{Season.AUTUMN}, false),
        SweetGemBerry("Sweet Gem Berry", ForAgingSeeds.rareSeed, new int[]{2, 4, 6, 6, 6}, 24, true, 0, 3000, false, 0, new Season[]{Season.AUTUMN}, false),
        Powdermelon("Powdermelon", ForAgingSeeds.powdermelonSeeds, new int[]{1, 2, 1, 2, 1}, 7, true, 0, 60, true, 63, new Season[]{Season.WINTER}, true),
        AncientFruit("Ancient Fruit", ForAgingSeeds.ancientSeeds, new int[]{2, 7, 7, 7, 5}, 28, false, 7, 550, false, 0, new Season[]{Season.AUTUMN, Season.SUMMER, Season.SPRING}, false),
        CommonMushroom("Common Mushroom", null, null, 0, true, 0, 40, true, 38,new Season[]{Season.AUTUMN, Season.SUMMER, Season.SPRING}, false ),
        Daffodil("Daffodil", null, null, 0, true, 0, 30, true, 0, new Season[]{Season.SPRING}, false),
        Dandelion("Dandelion", null, null, 0, true, 0, 40, true, 25, new Season[]{Season.SPRING}, false),
        Leek("Leek", null, null, 0, true, 0, 60, true, 40, new Season[]{Season.SPRING}, false),
        Morel("Morel", null, null, 0, true, 0, 150, true, 20, new Season[]{Season.SPRING}, false),
        Salmonberry("Salmonberry", null, null, 0, true, 0, 5, true, 25, new Season[]{Season.SPRING}, false),
        SpringOnion("Spring Onion", null, null, 0, true, 0, 8, true, 13, new Season[]{Season.SPRING}, false),
        WildHorseradish("Wild Horseradish", null, null, 0, true, 0, 50, true, 13, new Season[]{Season.SPRING}, false),
        FiddleheadFern("Fiddlehead Fern", null, null, 0, true, 0, 90, true, 25, new Season[]{Season.SUMMER}, false),
        RedMushroom("Red Mushroom", null, null, 0, true, 0, 75, true, -50, new Season[]{Season.SUMMER}, false),
        SpiceBerry("Spice Berry", null, null, 0, true, 0, 80, true, 25, new Season[]{Season.SUMMER}, false),
        SweetPea("Sweet Pea", null, null, 0, true, 0, 50, true, 0, new Season[]{Season.SUMMER}, false),
        Blackberry("Blackberry", null, null, 0, true, 0, 25, true, 25, new Season[]{Season.AUTUMN}, false),
        Chanterelle("Chanterelle", null, null, 0, true, 0, 160, true, 75, new Season[]{Season.AUTUMN}, false),
        Hazelnut("Hazelnut", null, null, 0, true, 0, 40, true, 38, new Season[]{Season.AUTUMN}, false),
        PurpleMushroom("Purple Mushroom", null, null, 0, true, 0, 90, true, 30, new Season[]{Season.AUTUMN}, false),
        WildPlum("Wild Plum", null, null, 0, true, 0, 80, true, 25, new Season[]{Season.AUTUMN}, false),
        Crocus("Crocus", null, null, 0, true, 0, 60, true, 0, new Season[]{Season.WINTER}, false),
        CrystalFruit("Crystal Fruit", null, null, 0, true, 0, 150, true, 63, new Season[]{Season.WINTER}, false),
        Holly("Holly", null, null, 0, true, 0, 80, true, -37, new Season[]{Season.WINTER}, false),
        SnowYam("Snow Yam", null, null, 0, true, 0, 100, true, 30, new Season[]{Season.WINTER}, false),
        WinterRoot("Winter Root", null, null, 0, true, 0, 70, true, 25, new Season[]{Season.WINTER}, false),


        ;

        private final String name;
        private final ForAgingSeeds source;
        private final int[] stages;
        private int totalHarvestTime;
        private boolean oneTime;
        private int regrowthTime;
        private int baseSellPrice;
        private final boolean isEatable;
        private int energy;
        private final Season[] season;
        private boolean canBecomeGiant;

        CropName(String name, ForAgingSeeds source, int[] stages, int totalHarvestTime,
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


        public ForAgingSeeds getSource() {
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


