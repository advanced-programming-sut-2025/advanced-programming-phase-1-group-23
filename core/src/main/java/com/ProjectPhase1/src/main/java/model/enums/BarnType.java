package model.enums;

public enum BarnType {
    SimpleBarn("Barn", 6000, 350, 150, 4, 7),
    BigBarn("Big Barn", 12000, 450, 200, 4, 7),
    DeluxeBarn("Deluxe Barn", 25000, 450, 300, 4, 7),
    SimpleCoop("Coop", 4000, 300, 150, 3, 6),
    BigCoop("Simple Coop", 10000, 400, 150, 3, 6),
    DeluxeCoop("Deluxe Coop", 20000, 500, 200, 3, 6);

    private final String kind;
    private final int price;
    private final int wood;
    private final int stone;
    private final int width;
    private final int length;

    BarnType(String kind, int price, int wood, int stone, int width, int length) {
        this.kind = kind;
        this.price = price;
        this.wood = wood;
        this.stone = stone;
        this.width = width;
        this.length = length;
    }

    public String getKind() {
        return kind;
    }

    public int getPrice() {
        return price;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }
}
