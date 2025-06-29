package model.Maps;



public class Cell {
    private Objects objectOnCell;
    private Position coordinate;
    private boolean isTilled;

    public int distance = 0;
    public double energy = 0;
    public int turns = 0;
    public Cell prev = null;

    public Cell(Tile cell) {
        this.coordinate = cell.getCoordinate();
        this.isTilled = cell.isTilled();
        this.objectOnCell = cell.getObjectOnCell();
    }

    public Cell(Objects objectOnCell, Position coordinate) {
        this.objectOnCell = objectOnCell;
        this.coordinate = coordinate;
        this.isTilled = false;
    }

    public int diffXPrev() {
        return this.coordinate.getX() - this.prev.getCoordinate().getX();
    }

    public int diffYPrev() {
        return this.coordinate.getY() - this.prev.getCoordinate().getY();
    }

    public void setEnergy() {
        this.energy = distance + 10 * turns;
        if(this.energy == 0.0){
            this.energy = 13.5;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile cell = (Tile) o;
        return coordinate.getX() == cell.getCoordinate().getX() && coordinate.getY() == cell.getCoordinate().getY();
    }
}
