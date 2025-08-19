package com.P.Client.controller.game;

import com.badlogic.gdx.math.MathUtils;

public class FishController {
    private float currentY;
    private FishMovementType type;
    private float timer;

    private int lastMove = 0;

    public FishController(FishMovementType type, float startY) {
        this.type = type;
        this.currentY = startY;
    }

    public void update(float delta) {
        timer += delta;
        if (timer >= 0.5f) {
            timer = 0;
            int movement = decideMove();
            currentY += movement;
        }
    }

    private int decideMove() {
        switch (type) {
            case MIXED:
                return MathUtils.randomSign() * 5 * MathUtils.random(0, 1);
            case SMOOTH:
                return MathUtils.randomBoolean(0.7f) ? lastMove : getRandomMove();
            case SINKER:
                return lastMove == 0 ? -10 : -5;
            case FLOATER:
                return lastMove == 0 ? +10 : +5;
            case DART:
                return MathUtils.randomSign() * 9 * MathUtils.random(0, 1);
            default:
                return 0;
        }
    }

    private int getRandomMove() {
        int[] moves = {-5, 0, +5};
        lastMove = moves[MathUtils.random(0, 2)];
        return lastMove;
    }

    public float getCurrentY() {
        return currentY;
    }
}
