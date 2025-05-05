package src.main.java.model.Objects;

public record FishType(String name, int price) {

    public FishType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
