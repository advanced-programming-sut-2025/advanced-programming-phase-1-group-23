package model.Maps;

import dev.morphia.annotations.Embedded;

@Embedded
abstract public class Objects {
    public String color;
    public boolean canWalk;
    public String type;

    public Objects(boolean canWalk, String color, String type) {
        this.color = color;
        this.canWalk = canWalk;
        this.type = type;
    }

    public Objects(){

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isCanWalk() {
        return canWalk;
    }

    public void setCanWalk(boolean canWalk) {
        this.canWalk = canWalk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String string(){
        return type.substring(0,1).toLowerCase();
    }
}
