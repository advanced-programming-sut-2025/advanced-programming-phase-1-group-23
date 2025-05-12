package src.main.java.model.Objects;
import dev.morphia.annotations.Embedded;

@Embedded
public class Fish {
    private final FishType type;
    private final int quality;

    public Fish(FishType type, int quality) {
        this.type = type;
        this.quality = quality;
    }

    public Fish() {
    }

    public FishType getType() {
        return type;
    }

    public int getQuality() {
        return quality;
    }
}
