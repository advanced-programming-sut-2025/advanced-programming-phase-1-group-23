package com.P.Client.view.GameView;

import com.P.common.model.Basics.App;
import com.P.common.model.Maps.Plant;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ClickEffect {
    private Animation<Texture> animation;
    private float stateTime;
    private float x, y;
    private int height, width;
    private boolean finished;

    public ClickEffect(Animation<Texture> animation, float x, float y, int height, int width) {
        this.animation = animation;
        this.x = x;
        this.y = y;
        this.stateTime = 0f;
        this.height=height;
        this.width=width;
        this.finished = false;
    }

    public void update(float delta) {
        stateTime += delta;
        if (animation.isAnimationFinished(stateTime)) {
            finished = true;
        }
    }

    public void draw(Batch batch) {
        Texture frame = animation.getKeyFrame(stateTime, false);
        batch.setColor(1f, 1f, 1f, 0.8f); // R, G, B, Alpha
        batch.draw(frame, x*100, y*100, width,height);
        batch.setColor(1f, 1f, 1f, 1f); // برگردوندن به حالت عادی

    }

    public boolean isFinished() {
        return finished;
    }
}

