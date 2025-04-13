package model.Maps;

import model.Naturals.Tree;

import java.util.ArrayList;

public class Map {
    private final Tile[][] tiles = new Tile[][];
    private final Area cabin = new Area();
    private final Area greenHouse = new Area();
    private final ArrayList<Area> rivers = new ArrayList<>();
    private final ArrayList<Area> quarries = new ArrayList<>();
    private final ArrayList<Area> barns = new ArrayList<>();
    private final ArrayList<Tree> trees = new ArrayList<>();

    public Tile[][] getTiles() {
        return tiles;
    }

    public Area getCabin() {
        return cabin;
    }

    public Area getGreenHouse() {
        return greenHouse;
    }

    public ArrayList<Area> getRivers() {
        return rivers;
    }

    public ArrayList<Area> getQuarries() {
        return quarries;
    }

    public ArrayList<Area> getBarns() {
        return barns;
    }

    public ArrayList<Tile> gegtTiles(Area area) {};

    public void addBarn(Area barn) {
        this.barns.add(barn);
    }
}
