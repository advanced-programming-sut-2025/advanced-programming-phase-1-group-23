package model.Objects;
import model.enums.AnimalType;
import model.Maps.Position;

public class Animal {
    private final AnimalType type;
    private final String name;
    private final int productPriceI;
    private final int productPriceII;
    private final int produceCycle;
    private int friendship;
    private int lastProduce;
    private boolean hasBeenFed;
    private boolean hasBeenNuzzed;
    private boolean isInsideBarn;
    private Position position;

    public Animal(AnimalType type, String name) {
        this.type = type;
        this.name = name;
        this.friendship = 0;
        this.lastProduce = 0;
        this.hasBeenFed = false;
        this.hasBeenNuzzed = false;
        this.isInsideBarn = true;

        switch(type) {
            case Hen -> {
                this.productPriceI = 50;
                this.productPriceII = 95;
                this.produceCycle = 1;
            }
            case Duck -> {
                this.productPriceI = 95;
                this.productPriceII = 250;
                this.produceCycle = 2;
            }
            case Rabbit -> {
                this.productPriceI = 340;
                this.productPriceII = 565;
                this.produceCycle = 4;
            }
            case Dinosaur -> {
                this.productPriceI = 350;
                this.productPriceII = 0;
                this.produceCycle = 7;
            }
            case Cow -> {
                this.productPriceI = 125;
                this.productPriceII = 190;
                this.produceCycle = 1;
            }
            case Goat -> {
                this.productPriceI = 225;
                this.productPriceII = 345;
                this.produceCycle = 2;
            }
            case Sheep -> {
                this.productPriceI = 340;
                this.productPriceII = 0;
                this.produceCycle = 3;
            }
            case Pig -> {
                this.productPriceI = 625;
                this.productPriceII = 0;
                this.produceCycle = 0;
            }
            default -> {
                this.productPriceI = 0;
                this.productPriceII = 0;
                this.produceCycle = 0;
            }
        }
    }

    public AnimalType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getProductPriceI() {
        return productPriceI;
    }

    public int getProductPriceII() {
        return productPriceII;
    }

    public int getProduceCycle() {
        return produceCycle;
    }

    public int getFriendship() {
        return friendship;
    }

    public void changeFriendship(int amount) {
        this.friendship += amount;
    }

    public int getLastProduce() {
        return lastProduce;
    }

    public void changeLastProduce(int amount) {
        this.lastProduce += amount;
    }

    public boolean getHasBeenFed() {
        return hasBeenFed;
    }

    public void setHasBeenFed(boolean hasBeenFed) {
        this.hasBeenFed = hasBeenFed;
    }

    public boolean getHasBeenNuzzed() {
        return hasBeenNuzzed;
    }

    public void setHasBeenNuzzed(boolean hasBeenNuzzed) {
        this.hasBeenNuzzed = hasBeenNuzzed;
    }

    public boolean getIsInsideBarn() {
        return isInsideBarn;
    }

    public void setInsideBarn(boolean insideBarn) {
        isInsideBarn = insideBarn;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void GoodNight() {
        if(!isInsideBarn)
            this.friendship -= 20;
        if(!hasBeenFed)
            this.friendship -= 20;
        if(!hasBeenNuzzed)
            this.friendship -= 10;
    }
}
