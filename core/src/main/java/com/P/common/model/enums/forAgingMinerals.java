package com.P.common.model.enums;

public enum forAgingMinerals {
    FOR_AGING_MINERALS(new Ingredients[]{Ingredients.QUARTZ,Ingredients.EARTH_CRYSTAL,Ingredients.FROZEN_TEAR,
    Ingredients.FIRE_QUARTZ,Ingredients.EMERALD,Ingredients.AQUAMARINE,Ingredients.RUBY,Ingredients.AMETHYST,
    Ingredients.TOPAZ,Ingredients.JADE,Ingredients.DIAMOND,Ingredients.PRISMATIC_SHARD,
    Ingredients.COPPER, Ingredients.IRON, Ingredients.GOLD,Ingredients.IRIDIUM,Ingredients.COAL});

    private final Ingredients[] minerals;

    forAgingMinerals(Ingredients[] minerals) {
        this.minerals = minerals;
    }

    public Ingredients[] getMinerals() {
        return minerals;
    }
}
