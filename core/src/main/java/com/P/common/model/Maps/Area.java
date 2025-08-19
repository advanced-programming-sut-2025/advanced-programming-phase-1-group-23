package com.P.common.model.Maps;

import dev.morphia.annotations.Embedded;

@Embedded
public class Area {
    private Position UpperLeftCorner;
    private Position BottomRightCorner;

    public Area(Position upperLeftCorner, Position bottomRightCorner) {
        UpperLeftCorner = upperLeftCorner;
        BottomRightCorner = bottomRightCorner;
    }

    public Area () {
    }

    public Position getUpperLeftCorner() {
        return UpperLeftCorner;
    }

    public Position getBottomRightCorner() {
        return BottomRightCorner;
    }
}
