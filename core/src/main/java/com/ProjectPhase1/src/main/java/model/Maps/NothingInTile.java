package model.Maps;

import dev.morphia.annotations.Embedded;

@Embedded
public class NothingInTile extends Objects {

    public NothingInTile() {
        super(true,"yellow",".");
    }
}
