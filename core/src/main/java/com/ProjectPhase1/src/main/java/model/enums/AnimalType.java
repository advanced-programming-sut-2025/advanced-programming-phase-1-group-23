package model.enums;

public enum AnimalType {
    Hen("Hen", 800),
    Duck("Duck", 1200),
    Rabbit("Rabbit", 8000),
    Dinosaur("Dinosaur", 14000),
    Cow("Cow", 1500),
    Goat("Goat", 4000),
    Sheep("Sheep", 8000),
    Pig("Pig", 16000);

    private final String kind;
    private final int price;

    AnimalType(String kind, int price) {
        this.kind = kind;
        this.price = price;
    }

    public String getKind() {
        return kind;
    }

    public int getPrice() {
        return price;
    }
}
