package src.main.java.model.Objects;

import src.main.java.model.enums.BarnType;

import java.util.ArrayList;

public class Barn {
    private final BarnType type;
    private final int capacity;
    private final ArrayList<Animal> animals;

    public Barn(BarnType type) {
        this.type = type;
        switch(type) {
            case SimpleBarn, SimpleCoop -> this.capacity = 4;
            case BigBarn, BigCoop -> this.capacity = 8;
            case DeluxeBarn, DeluxeCoop -> this.capacity = 12;
            default -> this.capacity = 0;
        }
        this.animals = new ArrayList<>();
    }

    public BarnType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }
}
