package com.P.common.model.enums;

public enum SeasonSeeds {
    WinterSeed(new ForAgingSeeds[]{ForAgingSeeds.powdermelonSeeds}),
    SummerSeed(new ForAgingSeeds[]{ForAgingSeeds.cornSeeds,ForAgingSeeds.pepperSeeds, ForAgingSeeds.radishSeeds, ForAgingSeeds.wheatSeeds, ForAgingSeeds.poppySeeds,ForAgingSeeds.sunflowerSeeds, ForAgingSeeds.spangleSeeds}),
    SpringSeed(new ForAgingSeeds[]{ForAgingSeeds.cauliflowerSeeds,ForAgingSeeds.potatoSeeds, ForAgingSeeds.parsnipSeeds, ForAgingSeeds.jazzSeeds, ForAgingSeeds.tulipBulb}),
    FallSeeds(new ForAgingSeeds[]{ForAgingSeeds.artichokeSeeds,ForAgingSeeds.cornSeeds,ForAgingSeeds.eggplantSeeds, ForAgingSeeds.pumpkinSeeds, ForAgingSeeds.sunflowerSeeds, ForAgingSeeds.fairySeeds})
    ;

    private final ForAgingSeeds[] seeds;

    SeasonSeeds(ForAgingSeeds[] seeds) {
        this.seeds = seeds;
    }

    public ForAgingSeeds[] getSeeds() {
        return seeds;
    }
}
