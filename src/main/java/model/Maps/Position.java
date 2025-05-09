package model.Maps;

public class Position {
    private int x;
    private int y;

    public Position() {

    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isNextTo(Position position) {
        return (position.getX() == x - 1 || position.getX() == x || position.getX() == x + 1) &&
                (position.getY() == y - 1 || position.getY() == y || position.getY() == y + 1) &&
                !position.equals(this);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;

        Position position = (Position) o;
        return position.x == x && position.y == y;
    }
}
