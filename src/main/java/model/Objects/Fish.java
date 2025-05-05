package src.main.java.model.Objects;

public class Fish {
    private final FishType type;
    private final int quality;

    public Fish(FishType type, int quality) {
        this.type = type;
        this.quality = quality;
    }

    public FishType getType() {
        return type;
    }

    public int getQuality() {
        return quality;
    }
}
