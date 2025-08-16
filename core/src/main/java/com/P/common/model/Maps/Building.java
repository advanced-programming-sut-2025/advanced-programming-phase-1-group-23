package com.P.common.model.Maps;

import java.util.ArrayList;

import dev.morphia.annotations.Embedded;

@Embedded
public class Building {
    private  ArrayList<Tile> tiles;

    public Building() {
    }

    public Building(ArrayList<Tile> tiles) {
        this.tiles = tiles;
        for(Tile tile : tiles)
            tile.setInsideBuilding(true);
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
