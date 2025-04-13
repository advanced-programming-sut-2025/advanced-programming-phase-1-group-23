package model.Maps;

public class Tile {
    private final Position position;
    private boolean isTraversable;
    //addContain

    public Tile(Position position, boolean isTraversable) {
        this.position = position;
        this.isTraversable = isTraversable;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isTraversable() {
        return isTraversable;
    }

    public void setTraversable(boolean traversable) {
        isTraversable = traversable;
    }
}
