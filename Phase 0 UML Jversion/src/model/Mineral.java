package model;

import model.enums.PlantType;

public class Mineral {
    private PlantType name;
    private boolean isEatable;
    private int energy;
    private boolean canBecomeGiant;
    private String season;

    public Mineral(PlantType name, boolean isEatable, int energy, boolean canBecomeGiant, String season) {
        this.name = name;
        this.isEatable = isEatable;
        this.energy = energy;
        this.canBecomeGiant = canBecomeGiant;
        this.season = season;
    }
}
