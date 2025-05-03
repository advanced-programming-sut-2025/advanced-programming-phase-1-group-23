package model.enums;

import model.Naturals.Crop;

public enum CropName {

        BlueJazz(new Crop("Blue Jazz", "Jazz Seeds", new int[]{1, 2, 2, 2}, 7, true, 0, 50, true, 45, new Season[]{Season.SPRING}, false)),
        Carrot(new Crop("Carrot", "Carrot Seeds", new int[]{1, 1, 1}, 3, true, 0, 35, true, 75, new Season[]{Season.SPRING}, false)),
        Cauliflower(new Crop("Cauliflower", "Cauliflower Seeds", new int[]{1, 2, 4, 4, 1}, 12, true, 0, 175, true, 75, new Season[]{Season.SPRING}, true)),
        CoffeeBean(new Crop("Coffee Bean", "Coffee Bean", new int[]{1, 2, 2, 3, 2}, 10, false, 2, 15, false, 0, new Season[]{Season.SPRING, Season.SUMMER}, false)),
        Garlic(new Crop("Garlic", "Garlic Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 60, true, 20, new Season[]{Season.SPRING}, false)),
        GreenBean(new Crop("Green Bean", "Bean Starter", new int[]{1, 1, 1, 3, 4}, 10, false, 3, 40, true, 25, new Season[]{Season.SPRING}, false)),
        Kale(new Crop("Kale", "Kale Seeds", new int[]{1, 2, 2, 1}, 6, true, 0, 110, true, 50, new Season[]{Season.SPRING}, false)),
        Parsnip(new Crop("Parsnip", "Parsnip Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 35, true, 25, new Season[]{Season.SPRING}, false)),
        Potato(new Crop("Potato", "Potato Seeds", new int[]{1, 1, 1, 2, 1}, 6, true, 0, 80, true, 25, new Season[]{Season.SPRING}, false)),
        Rhubarb(new Crop("Rhubarb", "Rhubarb Seeds", new int[]{2, 2, 2, 3, 4}, 13, true, 0, 220, false, 0, new Season[]{Season.SPRING}, false)),
        Strawberry(new Crop("Strawberry", "Strawberry Seeds", new int[]{1, 1, 2, 2, 2}, 8, false, 4, 120, true, 50, new Season[]{Season.SPRING}, false)),
        Tulip(new Crop("Tulip", "Tulip Bulb", new int[]{1, 1, 2, 2}, 6, true, 0, 30, true, 45, new Season[]{Season.SPRING}, false)),
        UnmilledRice(new Crop("Unmilled Rice", "Rice Shoot", new int[]{1, 2, 2, 3}, 8, true, 0, 30, true, 3, new Season[]{Season.SPRING}, false)),
        Blueberry(new Crop("Blueberry", "Blueberry Seeds", new int[]{1, 3, 3, 4, 2}, 13, false, 4, 50, true, 25, new Season[]{Season.SUMMER}, false)),
        Corn(new Crop("Corn", "Corn Seeds", new int[]{2, 3, 3, 3, 3}, 14, false, 4, 50, true, 25, new Season[]{Season.SUMMER, Season.AUTUMN}, false)),
        Hops(new Crop("Hops", "Hops Starter", new int[]{1, 1, 2, 3, 4}, 11, false, 1, 25, true, 45, new Season[]{Season.SUMMER}, false)),
        HotPepper(new Crop("Hot Pepper", "Pepper Seeds", new int[]{1, 1, 1, 1, 1}, 5, false, 3, 40, true, 13, new Season[]{Season.SUMMER}, false)),
        Melon(new Crop("Melon", "Melon Seeds", new int[]{1, 2, 3, 3, 3}, 12, true, 0, 250, true, 113, new Season[]{Season.SUMMER}, true)),
        Poppy(new Crop("Poppy", "Poppy Seeds", new int[]{1, 2, 2, 2}, 7, true, 0, 140, true, 45, new Season[]{Season.SUMMER}, false)),
        Radish(new Crop("Radish", "Radish Seeds", new int[]{2, 1, 2, 1}, 6, true, 0, 90, true, 45, new Season[]{Season.SUMMER}, false)),
        RedCabbage(new Crop("Red Cabbage", "Red Cabbage Seeds", new int[]{2, 1, 2, 2, 2}, 9, true, 0, 260, true, 75, new Season[]{Season.SUMMER}, false)),
        Starfruit(new Crop("Starfruit", "Starfruit Seeds", new int[]{2, 3, 2, 3, 3}, 13, true, 0, 750, true, 125, new Season[]{Season.SUMMER}, false)),
        SummerSpangle(new Crop("Summer Spangle", "Spangle Seeds", new int[]{1, 2, 3, 1}, 8, true, 0, 90, true, 45, new Season[]{Season.SUMMER}, false)),
        SummerSquash(new Crop("Summer Squash", "Summer Squash Seeds", new int[]{1, 1, 1, 2, 1}, 6, false, 3, 45, true, 63, new Season[]{Season.SUMMER}, false)),
        Sunflower(new Crop("Sunflower", "Sunflower Seeds", new int[]{1, 2, 3, 2}, 8, true, 0, 80, true, 45, new Season[]{Season.SUMMER, Season.AUTUMN}, false)),
        Tomato(new Crop("Tomato", "Tomato Seeds", new int[]{2, 2, 2, 2, 3}, 11, false, 4, 60, true, 20, new Season[]{Season.SUMMER}, false)),
        Wheat(new Crop("Wheat", "Wheat Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 25, false, 0, new Season[]{Season.SUMMER, Season.AUTUMN}, false)),
        Amaranth(new Crop("Amaranth", "Amaranth Seeds", new int[]{1, 2, 2, 2}, 7, true, 0, 150, true, 50, new Season[]{Season.AUTUMN}, false)),
        Artichoke(new Crop("Artichoke", "Artichoke Seeds", new int[]{2, 2, 1, 2, 1}, 8, true, 0, 160, true, 30, new Season[]{Season.AUTUMN}, false)),
        Beet(new Crop("Beet", "Beet Seeds", new int[]{1, 1, 2, 2}, 6, true, 0, 100, true, 30, new Season[]{Season.AUTUMN}, false)),
        BokChoy(new Crop("Bok Choy", "Bok Choy Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 80, true, 25, new Season[]{Season.AUTUMN}, false)),
        Broccoli(new Crop("Broccoli", "Broccoli Seeds", new int[]{2, 2, 2, 2}, 8, false, 4, 70, true, 63, new Season[]{Season.AUTUMN}, false)),
        Cranberries(new Crop("Cranberries", "Cranberry Seeds", new int[]{1, 2, 1, 1, 2}, 7, false, 5, 75, true, 38, new Season[]{Season.AUTUMN}, false)),
        Eggplant(new Crop("Eggplant", "Eggplant Seeds", new int[]{1, 1, 1, 1}, 5, false, 5, 60, true, 20, new Season[]{Season.AUTUMN}, false)),
        FairyRose(new Crop("Fairy Rose", "Fairy Seeds", new int[]{1, 4, 4, 3}, 12, true, 0, 290, true, 45, new Season[]{Season.AUTUMN}, false)),
        Grape(new Crop("Grape", "Grape Starter", new int[]{1, 1, 2, 3, 3}, 10, false, 3, 80, true, 38, new Season[]{Season.AUTUMN}, false)),
        Pumpkin(new Crop("Pumpkin", "Pumpkin Seeds", new int[]{1, 2, 3, 4, 3}, 13, true, 0, 320, false, 0, new Season[]{Season.AUTUMN}, true)),
        Yam(new Crop("Yam", "Yam Seeds", new int[]{1, 3, 3, 3}, 10, true, 0, 160, true, 45, new Season[]{Season.AUTUMN}, false)),
        SweetGemBerry(new Crop("Sweet Gem Berry", "Rare Seed", new int[]{2, 4, 6, 6, 6}, 24, true, 0, 3000, false, 0, new Season[]{Season.AUTUMN}, false)),
        Powdermelon(new Crop("Powdermelon", "Powdermelon Seeds", new int[]{1, 2, 1, 2, 1}, 7, true, 0, 60, true, 63, new Season[]{Season.WINTER}, true)),
        AncientFruit(new Crop("Ancient Fruit", "Ancient Seeds", new int[]{2, 7, 7, 7, 5}, 28, false, 7, 550, false, 0, new Season[]{Season.AUTUMN, Season.SUMMER, Season.SPRING}, false));

        private final Crop crop;

        CropName(Crop crop) {
            this.crop = crop;
        }

        public Crop getCrop() {
            return crop;
        }
    }


