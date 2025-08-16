package com.P.common.model.enums;

import com.P.common.model.Objects.Fish;

import java.util.List;

public enum SeasonFish {
    Spring(
            new Fish(Ingredients.Flounder),
            new Fish(Ingredients.LionFish),
            new Fish(Ingredients.Herring),
            new Fish(Ingredients.GhostFish),
            new Fish(Ingredients.Legend)),
    Summer(
            new Fish(Ingredients.Tilapia),
            new Fish(Ingredients.Dorado),
            new Fish(Ingredients.SunFish),
            new Fish(Ingredients.RainbowTrout),
            new Fish(Ingredients.CrimsonFish)),
    Autumn(
            new Fish(Ingredients.Salmon),
            new Fish(Ingredients.Sardine),
            new Fish(Ingredients.Shad),
            new Fish(Ingredients.BlueDiscus),
            new Fish(Ingredients.Angler)),
    Winter(
            new Fish(Ingredients.MidnightCarp),
            new Fish(Ingredients.Squid),
            new Fish(Ingredients.Tuna),
            new Fish(Ingredients.Perch),
            new Fish(Ingredients.GlacierFish));

    private final List<Fish> seasonFish;

    SeasonFish(Fish... items) {
        this.seasonFish = List.of(items);
    }

    public List<Fish> getSeasonFish() {
        return seasonFish;
    }
}
