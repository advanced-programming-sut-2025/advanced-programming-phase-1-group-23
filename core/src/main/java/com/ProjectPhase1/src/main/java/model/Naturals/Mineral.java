package model.Naturals;

import model.enums.CropName;
import dev.morphia.annotations.Embedded;

@Embedded
public class Mineral implements Objectss {
    private CropName name;
    private boolean isEatable;
    private int energy;
    private boolean canBecomeGiant;
    private String season;

    public Mineral(CropName name, boolean isEatable, int energy, boolean canBecomeGiant, String season) {
        this.name = name;
        this.isEatable = isEatable;
        this.energy = energy;
        this.canBecomeGiant = canBecomeGiant;
        this.season = season;
    }

    public Mineral() {
    }
}
