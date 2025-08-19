package com.P.common.model.Objects;

import com.P.common.model.enums.BarnType;
import com.P.common.model.Maps.Building;
import com.P.common.model.Maps.Tile;

import java.util.ArrayList;

import dev.morphia.annotations.Embedded;

@Embedded
public class Barn extends Building {
    private final BarnType type;
    private final int capacity;
    private final ArrayList<Animal> animals;


    public Barn(ArrayList<Tile> tiles, BarnType type) {
        super(tiles);
        this.type = type;
        switch (type) {
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
