package model.Maps;

import dev.morphia.annotations.Embedded;

import java.util.ArrayList;

@Embedded
public class Mine extends Building{
    public Mine() {

    }

    public Mine(ArrayList<Tile> mine){
        super(mine);
    }
}
