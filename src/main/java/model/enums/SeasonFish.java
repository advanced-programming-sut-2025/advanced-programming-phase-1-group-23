package src.main.java.model.enums;

import java.util.List;

public enum SeasonFish {
    Spring(
            new FishType("Flounder", 100),
            new FishType("LionFish", 100),
            new FishType("Herring", 30),
            new FishType("Ghostfish", 45),
            new FishType("Legend", 5000)),
    Summer(
            new FishType("Tilapia", 75),
            new FishType("Dorado", 100),
            new FishType("Sunfish", 30),
            new FishType("Rainbow Trout", 65),
            new FishType("Crimsonfish", 1500)),
    Autumn(
            new FishType("Salmon", 75),
            new FishType("Sardine", 40),
            new FishType("Shad", 60),
            new FishType("Blue Discus", 120),
            new FishType("Angler", 900)),
    Winter(
            new FishType("Midnight Carp", 150),
            new FishType("Squid", 80),
            new FishType("Tuna", 100),
            new FishType("Perch", 55),
            new FishType("Glacierfish", 1000));

    private final List<FishType> seasonFish;

    SeasonFish(FishType... items) {
        this.seasonFish = List.of(items);
    }

    public List<FishType> getSeasonFish() {
        return seasonFish;
    }
}
