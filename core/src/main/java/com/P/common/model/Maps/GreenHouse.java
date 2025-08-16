package com.P.common.model.Maps;

import java.util.ArrayList;
import dev.morphia.annotations.Embedded;

@Embedded
public class GreenHouse extends Building{
    public GreenHouse() {

    }

    public GreenHouse(ArrayList<Tile> greenHouse) {
       super(greenHouse);
    }
}
