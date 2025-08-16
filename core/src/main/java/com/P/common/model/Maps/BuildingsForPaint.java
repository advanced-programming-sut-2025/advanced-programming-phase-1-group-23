package com.P.common.model.Maps;

import dev.morphia.annotations.Embedded;

@Embedded
public class BuildingsForPaint extends Objects {
    public String buildingType;

    public BuildingsForPaint() {
        super();
    }

    public BuildingsForPaint(boolean canWalk, String buildingType) {

        super(canWalk, "red", "b");

        this.buildingType = buildingType;
    }
}
