package com.P.common.model.Objects;

import com.P.Main;
import com.P.common.model.Maps.Tile;
import com.P.common.model.Naturals.Objectss;
import com.P.common.model.enums.AnimalType;
import com.P.common.model.Maps.Position;
import com.P.common.model.enums.Ingredients;

import java.util.*;

import static java.lang.Math.floor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import dev.morphia.annotations.Embedded;

@Embedded
public class Animal implements Objectss {
    private AnimalType type;
    private String name;
    private int produceCycle;
    private int friendship;
    private int lastProduce;
    private boolean hasBeenFed;
    private boolean hasBeenNuzzed;
    private boolean hasBeenOut;
    private boolean isInsideBarn;
    private Position position;
    private Ingredients product;
    private static Random random = new Random();
    private Texture texture;
    private Sprite sprite;
    private Queue<Vector2> movementPath = new LinkedList<>();
    private float timeSinceLastMove = 0f;
    List<Tile> tiles = new ArrayList<>();

    public Animal(AnimalType type, String name, Position position) {
        this.type = type;
        this.name = name;
        this.friendship = 0;
        this.lastProduce = 0;
        this.hasBeenFed = false;
        this.hasBeenNuzzed = false;
        this.hasBeenOut = false;
        this.isInsideBarn = true;
        this.position = position;
        this.product = null;

        switch (type) {
            case Hen -> {
                this.produceCycle = 1;
            }
            case Duck -> {
                this.produceCycle = 2;
            }
            case Rabbit -> {
                this.produceCycle = 4;
            }
            case Dinosaur -> {
                this.produceCycle = 7;
            }
            case Cow -> {
                this.produceCycle = 1;
            }
            case Goat -> {
                this.produceCycle = 2;
            }
            case Sheep -> {
                this.produceCycle = 3;
            }
            case Pig -> {
                this.produceCycle = 0;
            }
            default -> {
                this.produceCycle = 0;
            }
        }

        this.texture = type.getAssetManager();
        this.sprite = new Sprite(type.getAssetManager());
        this.sprite.setSize(100, 100);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.sprite.setSize(Main.TILE_SIZE, Main.TILE_SIZE);
    }

    public Animal() {
    }

    public AnimalType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Ingredients getProduct() {
        return product;
    }

    public void collectProduct() {
        this.product = null;
    }

    public int getProduceCycle() {
        return produceCycle;
    }

    public int getFriendship() {
        return friendship;
    }

    public void changeFriendship(int amount) {
        this.friendship += amount;
        if (this.friendship > 1000)
            this.friendship = 1000;
    }

    public int getLastProduce() {
        return lastProduce;
    }

    public void resetLastProduce() {
        this.lastProduce = 0;
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

    public boolean getHasBeenOut() {
        return hasBeenOut;
    }

    public void setHasBeenOut(boolean hasBeenOut) {
        this.hasBeenOut = hasBeenOut;
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
        if (this.lastProduce < this.produceCycle)
            this.lastProduce++;
        if (!isInsideBarn)
            this.friendship -= 20;
        if (!hasBeenFed) {
            this.friendship -= 20;
            this.lastProduce = 0;
        }
        if (!hasBeenNuzzed)
            this.friendship -= 10;
        if (this.lastProduce == this.produceCycle && this.product == null) {
            switch (this.type) {
                case Hen -> this.product = Ingredients.EGG;
                case Duck -> {
                    double probability = this.friendship + (150 * random.nextDouble(0.5, 1.5));
                    int probabilityInt = (int) floor(probability);
                    int quality = random.nextInt(1500);
                    if (quality < probabilityInt)
                        this.product = Ingredients.DUCK_FEATHER;
                    else
                        this.product = Ingredients.DUCK_EGG;
                }
                case Rabbit -> {
                    double probability = this.friendship + (150 * random.nextDouble(0.5, 1.5));
                    int probabilityInt = (int) floor(probability);
                    int quality = random.nextInt(1500);
                    if (quality < probabilityInt)
                        this.product = Ingredients.RABBIT_PIE;
                    else
                        this.product = Ingredients.WOOL;
                }
                case Dinosaur -> this.product = Ingredients.DINOSAUR_EGG;
                case Cow -> this.product = Ingredients.MILK;
                case Goat -> this.product = Ingredients.GOAT_MILK;
                case Sheep -> this.product = Ingredients.WOOL;
                case Pig -> {
                    if (this.hasBeenOut)
                        this.product = Ingredients.TRUFFLE;
                }
            }
        }
        this.hasBeenFed = false;
        this.hasBeenOut = false;
        this.hasBeenNuzzed = false;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProduceCycle(int produceCycle) {
        this.produceCycle = produceCycle;
    }

    public void setFriendship(int friendship) {
        this.friendship = friendship;
    }

    public void setLastProduce(int lastProduce) {
        this.lastProduce = lastProduce;
    }

    public boolean isHasBeenFed() {
        return hasBeenFed;
    }

    public boolean isHasBeenNuzzed() {
        return hasBeenNuzzed;
    }

    public boolean isHasBeenOut() {
        return hasBeenOut;
    }

    public boolean isInsideBarn() {
        return isInsideBarn;
    }

    public void setProduct(Ingredients product) {
        this.product = product;
    }

    public static Random getRandom() {
        return random;
    }

    public static void setRandom(Random random) {
        Animal.random = random;
    }

    public Texture getTexture() {
        return texture;
    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Queue<Vector2> getMovementPath() {
        return movementPath;
    }

    public void setMovementPath(Queue<Vector2> movementPath) {
        this.movementPath = movementPath;
    }

    public float getTimeSinceLastMove() {
        return timeSinceLastMove;
    }

    public void setTimeSinceLastMove(float timeSinceLastMove) {
        this.timeSinceLastMove = timeSinceLastMove;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }


}
