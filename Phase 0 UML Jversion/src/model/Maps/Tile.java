package model.Maps;

import model.Naturals.Crop;
import model.Naturals.Mineral;
import model.Naturals.Tree;

public class Tile {
    private final Position position;
    private boolean isTraversable;
    private Tree tree;
    private Crop crop;
    private Mineral mineral;

    public Tile(Position position, boolean isTraversable) {
        this.position = position;
        this.isTraversable = isTraversable;
        this.tree = null;
        this.crop = null;
        this.mineral = null;
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

    public void addTree(Tree tree) {}

    public Tree getTree() {}

    public void addCrop(Crop crop) {}

    public Crop getCrop() {return crop;}

    public void addMineral(Mineral mineral) {}

    public Mineral getMineral() {return mineral;}
}
