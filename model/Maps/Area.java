package model.Maps;

public class Area {
    private final Position UpperLeftCorner;
    private final Position BottomRightCorner;

    public Area(Position upperLeftCorner, Position bottomRightCorner) {
        UpperLeftCorner = upperLeftCorner;
        BottomRightCorner = bottomRightCorner;
    }

    public Position getUpperLeftCorner() {
        return UpperLeftCorner;
    }

    public Position getBottomRightCorner() {
        return BottomRightCorner;
    }
}
