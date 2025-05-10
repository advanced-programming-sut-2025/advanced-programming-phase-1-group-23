package model.Maps;
import model.Maps.Tile;
import model.Maps.Position;
import model.Maps.Tile;

import java.util.ArrayList;

public class Building {
    private final ArrayList<Tile> tiles;

    public Building(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile getTileByCoordinate(Position position) {
        for(Tile tile : this.tiles)
            if(tile.getCoordinate().equals(position))
                return tile;
        return null;
    }
}
