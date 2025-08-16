package com.P.common.model.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteMine {
    private Sprite sprite;
    private boolean moving = true;

    public SpriteMine(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
