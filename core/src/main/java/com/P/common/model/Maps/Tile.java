package com.P.common.model.Maps;

import com.P.common.model.Naturals.Objectss;
import dev.morphia.annotations.Embedded;
import com.P.common.model.Objects.CraftingMachine;
import com.P.common.model.enums.Ingredients;

@Embedded
public class Tile {
    private Objects objectOnCell;
    private Position coordinate;
    private boolean isTilled;
    private Objectss object;
    private boolean isInsideBuilding;
    private CraftingMachine machine = null;
    private boolean speedFertility = false;
    private boolean waterFertility = false;

    public int distance = 0;
    public double energy = 0;
    public int turns = 0;
    public Tile prev = null;
    private Ingredients ingredients;

    public Tile() {
    }

    public Tile(Objects objectOnCell, Position coordinate) {
        this.objectOnCell = objectOnCell;
        this.coordinate = coordinate;
        this.isTilled = false;
        this.isInsideBuilding = false;
    }

    public CraftingMachine getMachine() {
        return machine;
    }

    public void setMachine(CraftingMachine machine) {
        this.machine = machine;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public Tile getPrev() {
        return prev;
    }

    public void setPrev(Tile prev) {
        this.prev = prev;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public void setObject(Objectss object) {
        this.object = object;
    }

    public Objectss getObject() {
        return object;
    }

    public int diffXPrev() {
        return this.coordinate.getX() - this.prev.coordinate.getX();
    }

    public int diffYPrev() {
        return this.coordinate.getY() - this.prev.coordinate.getY();
    }

    public void setEnergy() {
        energy = distance + 10 * turns;
    }

    public Position getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Position coordinate) {
        this.coordinate = coordinate;
    }

    public Objects getObjectOnCell() {
        return objectOnCell;
    }

    public void setObjectOnCell(Objects objectOnCell) {
        this.objectOnCell = objectOnCell;
    }

    public boolean isTilled() {
        return isTilled;
    }

    public void setTilled(boolean tilled) {
        isTilled = tilled;
    }

    public boolean isInsideBuilding() {
        return isInsideBuilding;
    }

    public void setInsideBuilding(boolean insideBuilding) {
        isInsideBuilding = insideBuilding;
    }

    @Override
    public Tile clone() {
        return new Tile(objectOnCell, coordinate);
    }

    public boolean isSpeedFertility() {
        return speedFertility;
    }

    public void setSpeedFertility(boolean speedFertility) {
        this.speedFertility = speedFertility;
    }

    public boolean isWaterFertility() {
        return waterFertility;
    }

    public void setWaterFertility(boolean waterFertility) {
        this.waterFertility = waterFertility;
    }
}
