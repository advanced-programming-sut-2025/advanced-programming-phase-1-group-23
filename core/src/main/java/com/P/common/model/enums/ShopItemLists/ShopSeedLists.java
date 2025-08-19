package com.P.common.model.enums.ShopItemLists;

import com.P.common.model.enums.ForAgingSeeds;
import com.P.common.model.Objects.ShopSeed;

import java.util.List;

public enum ShopSeedLists {
        SPRING_SEEDS_PIERRE(
                new ShopSeed(ForAgingSeeds.parsnipSeeds, 5),
                new ShopSeed(ForAgingSeeds.beanStarter, 5),
                new ShopSeed(ForAgingSeeds.cauliflowerSeeds, 5),
                new ShopSeed(ForAgingSeeds.potatoSeeds, 5),
                new ShopSeed(ForAgingSeeds.tulipBulb, 5),
                new ShopSeed(ForAgingSeeds.kaleSeeds, 5),
                new ShopSeed(ForAgingSeeds.jazzSeeds, 5),
                new ShopSeed(ForAgingSeeds.garlicSeeds, 5),
                new ShopSeed(ForAgingSeeds.riceShoot, 5)),
        SPRING_SEEDS_JOJA(
                new ShopSeed(ForAgingSeeds.parsnipSeeds, 5),
                new ShopSeed(ForAgingSeeds.beanStarter, 5),
                new ShopSeed(ForAgingSeeds.cauliflowerSeeds, 5),
                new ShopSeed(ForAgingSeeds.potatoSeeds, 5),
                new ShopSeed(ForAgingSeeds.strawberrySeeds, 5),
                new ShopSeed(ForAgingSeeds.tulipBulb, 5),
                new ShopSeed(ForAgingSeeds.kaleSeeds, 5),
                new ShopSeed(ForAgingSeeds.coffeeBean, 1),
                new ShopSeed(ForAgingSeeds.carrotSeeds, 10),
                new ShopSeed(ForAgingSeeds.rhubarbSeeds, 5),
                new ShopSeed(ForAgingSeeds.jazzSeeds, 5)),

        SUMMER_SEEDS_PIERRE(
                new ShopSeed(ForAgingSeeds.melonSeeds, 5),
                new ShopSeed(ForAgingSeeds.tomatoSeeds, 5),
                new ShopSeed(ForAgingSeeds.blueberrySeeds, 5),
                new ShopSeed(ForAgingSeeds.pepperSeeds, 5),
                new ShopSeed(ForAgingSeeds.wheatSeeds, 5),
                new ShopSeed(ForAgingSeeds.radishSeeds, 5),
                new ShopSeed(ForAgingSeeds.poppySeeds, 5),
                new ShopSeed(ForAgingSeeds.spangleSeeds, 5),
                new ShopSeed(ForAgingSeeds.hopsStarter, 5),
                new ShopSeed(ForAgingSeeds.cornSeeds, 5),
                new ShopSeed(ForAgingSeeds.sunflowerSeeds, 5),
                new ShopSeed(ForAgingSeeds.redCabbageSeeds, 5)),
        SUMMER_SEEDS_JOJA(
                new ShopSeed(ForAgingSeeds.tomatoSeeds, 5),
                new ShopSeed(ForAgingSeeds.pepperSeeds, 5),
                new ShopSeed(ForAgingSeeds.wheatSeeds, 10),
                new ShopSeed(ForAgingSeeds.summerSquashSeeds, 10),
                new ShopSeed(ForAgingSeeds.radishSeeds, 5),
                new ShopSeed(ForAgingSeeds.melonSeeds, 5),
                new ShopSeed(ForAgingSeeds.hopsStarter, 5),
                new ShopSeed(ForAgingSeeds.poppySeeds, 5),
                new ShopSeed(ForAgingSeeds.spangleSeeds, 5),
                new ShopSeed(ForAgingSeeds.starfruitSeeds, 5),
                new ShopSeed(ForAgingSeeds.coffeeBean, 1),
                new ShopSeed(ForAgingSeeds.sunflowerSeeds, 5)),

        FALL_SEEDS_PIERRE(
                new ShopSeed(ForAgingSeeds.eggplantSeeds, 5),
                new ShopSeed(ForAgingSeeds.cornSeeds, 5),
                new ShopSeed(ForAgingSeeds.pumpkinSeeds, 5),
                new ShopSeed(ForAgingSeeds.bokChoySeeds, 5),
                new ShopSeed(ForAgingSeeds.yamSeeds, 5),
                new ShopSeed(ForAgingSeeds.cranberrySeeds, 5),
                new ShopSeed(ForAgingSeeds.sunflowerSeeds, 5),
                new ShopSeed(ForAgingSeeds.fairySeeds, 5),
                new ShopSeed(ForAgingSeeds.amaranthSeeds, 5),
                new ShopSeed(ForAgingSeeds.grapeStarter, 5),
                new ShopSeed(ForAgingSeeds.wheatSeeds, 5),
                new ShopSeed(ForAgingSeeds.artichokeSeeds, 5)),
        FALL_SEEDS_JOJA(
                new ShopSeed(ForAgingSeeds.cornSeeds, 5),
                new ShopSeed(ForAgingSeeds.eggplantSeeds, 5),
                new ShopSeed(ForAgingSeeds.pumpkinSeeds, 5),
                new ShopSeed(ForAgingSeeds.broccoliSeeds, 5),
                new ShopSeed(ForAgingSeeds.amaranthSeeds, 5),
                new ShopSeed(ForAgingSeeds.grapeStarter, 5),
                new ShopSeed(ForAgingSeeds.beetSeeds, 5),
                new ShopSeed(ForAgingSeeds.yamSeeds, 5),
                new ShopSeed(ForAgingSeeds.bokChoySeeds, 5),
                new ShopSeed(ForAgingSeeds.cranberrySeeds, 5),
                new ShopSeed(ForAgingSeeds.sunflowerSeeds, 5),
                new ShopSeed(ForAgingSeeds.fairySeeds, 5),
                new ShopSeed(ForAgingSeeds.rareSeed, 1),
                new ShopSeed(ForAgingSeeds.wheatSeeds, 5)),

        WINTER_SEEDS_JOJA(
                new ShopSeed(ForAgingSeeds.powdermelonSeeds, 10)
        ),

        YEAR_SEEDS_PIERRE(
                //TODO add saplings
        ),
        YEAR_SEEDS_JOJA(
                new ShopSeed(ForAgingSeeds.ancientSeeds, 1)
                //TODO add grass starter
        );


        private final List<ShopSeed> shopSeedList;

        ShopSeedLists(ShopSeed... items) {
            this.shopSeedList = List.of(items);
        }

        public List<ShopSeed> getShopSeedList() {
            return shopSeedList;
        }

}
