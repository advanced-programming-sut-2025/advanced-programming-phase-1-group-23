package com.P.common.model.Objects;

public enum FishingPoleQulity {
    NORMAL(1.0, 0.0, 0.5, 1),
    SILVER(1.25, 0.5, 0.7, 2),
    GOLD(1.5, 0.7, 0.9, 3),
    IRIDIUM(2.0, 0.9, 100.0, 4);

    private final Double priceCoefficient;
    private final Double startOfTheRange;
    private final Double endOfTheRange;
    private final Integer level;

    FishingPoleQulity(Double priceCoefficient, Double startOfTheRange, Double endOfTheRange, Integer level) {
        this.priceCoefficient = priceCoefficient;
        this.startOfTheRange = startOfTheRange;
        this.endOfTheRange = endOfTheRange;
        this.level = level;
    }

    public static FishingPoleQulity getQualityByDouble(double quality) {
        for (FishingPoleQulity value : FishingPoleQulity.values()) {
            if (quality >= value.getStartOfTheRange() && quality < value.getEndOfTheRange()) {
                return value;
            }
        }
        return FishingPoleQulity.NORMAL;
    }

    public Double getPriceCoefficient() {
        return priceCoefficient;
    }

    public Double getStartOfTheRange() {
        return startOfTheRange;
    }

    public Double getEndOfTheRange() {
        return endOfTheRange;
    }

    public Integer getLevel() {
        return level;
    }
}
