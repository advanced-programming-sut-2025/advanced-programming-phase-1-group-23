package com.P.common.model.Maps;

import dev.morphia.annotations.Embedded;

import java.util.ArrayList;

@Embedded
public class Cottage extends Building{
    public Cottage() {

    }

    public Cottage(ArrayList<Tile> cottages) {
        super(cottages);
    }
}
