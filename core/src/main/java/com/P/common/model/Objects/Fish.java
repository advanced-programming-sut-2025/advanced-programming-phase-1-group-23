package com.P.common.model.Objects;

import com.P.common.model.enums.ToolLevel;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dev.morphia.annotations.Embedded;

import com.P.common.model.enums.Ingredients;

import java.util.Random;

@Embedded
public class Fish {
    private Ingredients type;

    public Fish(Ingredients type) {
        this.type = type;
    }

    public Ingredients getType() {
        return type;
    }

    private FishType fishType;
    private Texture texture;
    private Sprite sprite;
    private Integer containingEnergy = 20; //TODO Energy not found
    private ToolLevel productQuality;
    private int lastDy;
    private final int speed = 200;
    private int coefficientSpeed;
    private float lastMove;


    public Fish(FishType fishType) {
        this.fishType = fishType;
        this.texture = fishType.getTexture();
        this.sprite = new Sprite(fishType.getTexture());
        this.sprite.setSize(100, 100);
    }

    public Fish(FishType fishType, ToolLevel productQuality) {
        this.fishType = fishType;
        this.productQuality = productQuality;
        this.texture = fishType.getTexture();
        this.sprite = new Sprite(fishType.getTexture());
        this.sprite.setSize(100, 100);
    }


    public String getName() {
        return this.fishType.getName();
    }


    public Fish copy() {
        return new Fish(this.fishType, this.productQuality);
    }

    public float updatePositionY(float positionY) {
        this.coefficientSpeed = 1;
        switch (this.fishType.getBehavior()) {
            case MIXED -> {
                return mixedMovement(positionY);
            }
            case SMOOTH -> {
                return smoothMovement(positionY);
            }
            case SINKER -> {
                return sinkerMovement(positionY);
            }
            case FLOATER -> {
                return floaterMovement(positionY);
            }
        }
        return dartMovement(positionY);
    }

    private float mixedMovement(float positionY) {
        Random random = new Random();
        int witchState = random.nextInt(0, 2);
        int dy = random.nextInt(1, 128);
        if (witchState == 0) {
            this.lastDy = dy;
            return positionY + dy;
        } else if (witchState == 1) {
            this.lastDy = -dy;
            return positionY - dy;
        } else {
            this.lastDy = 0;
            return positionY;
        }
    }

    private float smoothMovement(float positionY) {
        Random random = new Random();
        int doLastMove = random.nextInt(0, 10);
        if (doLastMove > 7) return mixedMovement(positionY);
        else return positionY + this.lastDy;
    }

    private float sinkerMovement(float positionY) {
        Random random = new Random();
        int witchState = random.nextInt(0, 2);
        int dy = random.nextInt(1, 128);
        if (witchState == 0) {
            this.lastDy = dy;
            return positionY + dy;
        } else if (witchState == 1) {
            if (this.lastDy == 0) {
                this.coefficientSpeed = 2;
            }
            this.lastDy = -dy;
            return positionY - dy;
        } else {
            this.lastDy = 0;
            return positionY;
        }
    }

    private float floaterMovement(float positionY) {
        Random random = new Random();
        int witchState = random.nextInt(0, 2);
        int dy = random.nextInt(1, 128);
        if (witchState == 0) {
            if (this.lastDy == 0) {
                this.coefficientSpeed = 2;
            }
            this.lastDy = dy;
            return positionY + dy;
        } else if (witchState == 1) {
            this.lastDy = -dy;
            return positionY - dy;
        } else {
            this.lastDy = 0;
            return positionY;
        }
    }

    private float dartMovement(float positionY) {
        Random random = new Random();
        int witchState = random.nextInt(0, 2);
        int dy = random.nextInt(1, 256);
        if (witchState == 0) {
            this.lastDy = dy;
            return positionY + dy;
        } else if (witchState == 1) {
            this.lastDy = -dy;
            return positionY - dy;
        } else {
            this.lastDy = 0;
            return positionY;
        }
    }

    public void setType(Ingredients type) {
        this.type = type;
    }

    public FishType getFishType() {
        return fishType;
    }

    public void setFishType(FishType fishType) {
        this.fishType = fishType;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Integer getContainingEnergy() {
        return containingEnergy;
    }

    public void setContainingEnergy(Integer containingEnergy) {
        this.containingEnergy = containingEnergy;
    }

    public ToolLevel getProductQuality() {
        return productQuality;
    }

    public void setProductQuality(ToolLevel productQuality) {
        this.productQuality = productQuality;
    }

    public int getLastDy() {
        return lastDy;
    }

    public void setLastDy(int lastDy) {
        this.lastDy = lastDy;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCoefficientSpeed() {
        return coefficientSpeed;
    }

    public void setCoefficientSpeed(int coefficientSpeed) {
        this.coefficientSpeed = coefficientSpeed;
    }

    public float getLastMove() {
        return lastMove;
    }

    public void setLastMove(float lastMove) {
        this.lastMove = lastMove;
    }


}
