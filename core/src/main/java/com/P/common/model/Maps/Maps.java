package com.P.common.model.Maps;

import java.util.ArrayList;
import dev.morphia.annotations.Embedded;

@Embedded
public class Maps {
    private ArrayList<Farm> farms;
    private Village village;

    public Maps() {}
    private Maps(ArrayList<Farm> farms, Village village) {
        this.farms = farms;
        this.village = village;
    }

    public static Maps makeMap() {
        ArrayList<Farm> farms = new ArrayList<>();
        Village village = new Village();
        return new Maps(farms, village);
    }

    public void addFarm(Farm farm) {
        farms.add(farm);
    }

    public ArrayList<Farm> getFarms() {
        return farms;
    }

    public Village getVillage() {
        return village;
    }
}
