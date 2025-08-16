package com.P.common.model.Objects;

import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Player;
import com.P.Client.model.GameAssetManager;
import com.P.common.model.Maps.Tile;
import com.P.common.model.enums.ToolLevel;
import com.P.common.model.enums.ToolType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

public enum FishingPole {
    TRAINING("Training Pole", 25, 0, 0, 8, 0.1, GameAssetManager.TRAININGROD),
    BAMBOO("Bamboo Pole", 500, 1, 0, 8, 0.5, GameAssetManager.BAMBOOPOLE),
    FIBER_GLASS("Fiberglass Pole", 18_000, 2, 2, 6, 0.9, GameAssetManager.FIBERGLASSROD),
    IRIDIUM("Iridium Pole", 7_500, 3, 4, 4, 1.2, GameAssetManager.IRIDIUMROD);

    private final String name;
    private final int price;
    private final int level;
    private final int abilityLevel;
    private final int energyCost;
    private final double qualityPercent;
    private final Texture texture;
    private final Sprite sprite;

    FishingPole(String name, int price, int level, int abilityLevel, int energyCost,
                double qualityPercent, Texture texture) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.abilityLevel = abilityLevel;
        this.energyCost = energyCost;
        this.qualityPercent = qualityPercent;
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.sprite.setSize(50, 75);
    }

    // --- Public Interface ---

    public String getName() {
        return name.toLowerCase();
    }

    public int getSellPrice() {
        return price;
    }

    public int getLevel() {
        return level;
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getEnergy(Player player) {
        int minus = 0;
        if (player.getFishingSkill() == 4) {
            minus += 1;
        }

        return (int) (App.loggedInUser.getCurrentGame().getWeather() * energyCost - minus);
    }

    public boolean canFish(Player player, Fish fish) {
        return player.getEnergy() >= getEnergy(player);
    }

    public Fish fishing(Player player) {
        double qualityScore = calculateQualityScore(player);
        FishType fishType = generateFishType(player);

        Fish fish = new Fish(fishType);
        fish.setProductQuality(determineQualityLevel(qualityScore));

        return fish;
    }

    public int generateNumberOfFish(Player player) {
        Random random = new Random();
        double randomFactor = random.nextDouble(0, 1);
        int skill = player.getFishingSkill();
        double weatherMultiplier = getWeatherMultiplier();

        return (int) Math.ceil(randomFactor * weatherMultiplier * (skill + 2));
    }

    public Tool getToolByLevel(int targetLevel) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        return player.getInventory().getTools().keySet().stream()
            .filter(tool -> tool.getToolType() == ToolType.FishingRod)
            .findFirst()
            .orElse(null);
    }

    public String useTool(Player player, Tile tile) {
        // Reserved for future behavior
        return "";
    }

    public Integer getContainingEnergy() {
        return 0; // Placeholder if needed in future logic
    }

    // --- Private Helpers ---

    private double calculateQualityScore(Player player) {
        Random random = new Random();
        double R = random.nextDouble(0, 1);
        int skill = player.getFishingSkill();
        double weatherMultiplier = getWeatherMultiplier();

        return (R * (skill + 2) * qualityPercent) / (7 - weatherMultiplier);
    }

    private ToolLevel determineQualityLevel(double score) {
        return switch (FishingPoleQulity.getQualityByDouble(score)) {
            case NORMAL -> ToolLevel.Learning;
            case SILVER -> ToolLevel.Bambou;
            case GOLD -> ToolLevel.FiberGlass;
            case IRIDIUM -> ToolLevel.Iridium;
            default -> ToolLevel.Learning;
        };
    }

    private FishType generateFishType(Player player) {
        Random random = new Random();
        int upperBound = (player.getFishingSkill() < 4) ? 13 : FishType.values().length - 1;
        FishType selected;

        do {
            selected = FishType.values()[random.nextInt(0, upperBound)];
        } while (!selected.getSeason().equals(
            App.getLoggedInUser().getCurrentGame().getSeason()));

        return selected;
    }

    private double getWeatherMultiplier() {
        return switch (App.getLoggedInUser().getCurrentGame().getWeatherToday()) {
            case SUNNY -> 1.5;
            case RAIN -> 1.2;
            case SNOW -> 1.0;
            default -> 0.5;
        };
    }
}
