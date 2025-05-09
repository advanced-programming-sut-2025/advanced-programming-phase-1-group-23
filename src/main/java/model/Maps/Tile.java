package model.Maps;

import model.Naturals.Crop;
import model.Naturals.Mineral;
import model.Naturals.Objectss;
import model.Naturals.Tree;

public class Tile {
    private Objects objectOnCell;
    private Position coordinate;
    private boolean isTilled;
    private Objectss object;

    public int distance = 0;
    public double energy = 0;
    public int turns = 0;
    public Tile prev = null;

    public Tile(){}

    public Tile(Objects objectOnCell, Position coordinate) {
        this.objectOnCell = objectOnCell;
        this.coordinate = coordinate;
        this.isTilled = false;
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

    @Override
    public Tile clone() {
        return new Tile(objectOnCell, coordinate);
    }
}
