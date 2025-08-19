package com.P.Client.view.GameView;

import com.P.Main;
import com.P.Client.model.GameAssetManager;
import com.P.common.model.Objects.Fish;
import com.P.common.model.Objects.FishType;
import com.P.common.model.Objects.FishingPole;
import com.P.common.model.game.GameModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;

public class MiniGame {
    private final FishingPole fishingPole;
    private final Fish fish;
    private final OrthographicCamera camera;

    private final Sprite miniGameBackground;
    private final Sprite playerBarSprite;
    private final Sprite fishVisualSprite;

    private final Rectangle fishBounds;
    private final Rectangle playerBarBounds;

    private final float uiOffsetX;
    private final float uiOffsetY;

    private float fishCurrentY;
    private float fishTargetY;

    private float catchProgress = 0f;
    private boolean wasPerfectCatch = true;
    private boolean isGameOver = false;
    private boolean hasPlayerWon = false;
    private boolean showLegendaryFish = false;
    private float legendaryFishTimer = 0;
    private boolean isSnoar = false;
    private float snoarTimer = 4f;

    // Constants
    private static final float BAR_MOVE_SPEED = 200f;
    private static final float BAR_WIDTH_PX = 48f;
    private static final float BAR_HEIGHT_PX = 128f;
    private static final float FISH_WIDTH_PX = 48f;
    private static final float FISH_HEIGHT_PX = 48f;
    private static final float UI_WIDTH_PX = 128f;
    private static final float UI_HEIGHT_PX = 512f;
    private static final float PROGRESS_INCREASE_RATE = 0.10f;
    private static final float PROGRESS_DECREASE_RATE = 0.30f;
    public static final float LEGENDARY_FISH_DURATION = 10f;

    public MiniGame(Fish fish, FishingPole fishingPole, OrthographicCamera camera) {
        this.fish = fish;
        this.fishingPole = fishingPole;
        this.camera = camera;

        this.miniGameBackground = new Sprite(GameAssetManager.MINI_GAME_BACKGROUND);
        this.playerBarSprite = new Sprite(GameAssetManager.MINI_GAME_BAR);
        this.fishVisualSprite = new Sprite(GameAssetManager.FISHH);

        float camX = GameModel.getCamera().position.x;
        float camY = GameModel.getCamera().position.y;
        float cameraLeft = camX - GameModel.getCamera().viewportWidth / 2;
        float cameraBottom = camY - GameModel.getCamera().viewportHeight / 2;

        int xTile = 5;
        int yTile = 12;

        this.uiOffsetX = xTile * Main.TILE_SIZE - cameraLeft;
        this.uiOffsetY = yTile * Main.TILE_SIZE - cameraBottom;

        this.fishBounds = new Rectangle();
        this.playerBarBounds = new Rectangle();

        initializeSpritesAndBounds();
    }

    private void initializeSpritesAndBounds() {
        float fishX = uiOffsetX + 16 + BAR_WIDTH_PX * 0.75f;
        float fishY = uiOffsetY + UI_HEIGHT_PX / 2f - FISH_HEIGHT_PX / 2f + 50;

        fishBounds.set(fishX, fishY, FISH_WIDTH_PX, FISH_HEIGHT_PX);
        fishCurrentY = fishY;

        float barY = uiOffsetY + UI_HEIGHT_PX / 2f - BAR_HEIGHT_PX / 2f;
        playerBarBounds.set(fishX, barY, BAR_WIDTH_PX, BAR_HEIGHT_PX);

        miniGameBackground.setPosition(uiOffsetX, uiOffsetY);
        miniGameBackground.setSize(UI_WIDTH_PX, UI_HEIGHT_PX);

        playerBarSprite.setSize(BAR_WIDTH_PX, BAR_HEIGHT_PX);
        fishVisualSprite.setSize(FISH_WIDTH_PX, FISH_HEIGHT_PX);
    }

    public void draw(SpriteBatch batch, float delta) {
        if (showLegendaryFish) {
            drawLegendaryFish(batch, delta);
            return;
        }

        updateGameState(delta);

        miniGameBackground.draw(batch);
        playerBarSprite.setPosition(playerBarBounds.x, playerBarBounds.y);
        fishVisualSprite.setPosition(fishBounds.x, fishBounds.y);

        playerBarSprite.draw(batch);
        fishVisualSprite.draw(batch);

        drawProgressBar(batch);

        if(isSnoar)
            drawFish(batch, delta);
    }

    private void updateGameState(float delta) {
        handlePlayerInput(delta);
        handleFishLogic(delta);
        updateProgress(delta);
    }

    private void handlePlayerInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerBarBounds.y += BAR_MOVE_SPEED * delta;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerBarBounds.y -= BAR_MOVE_SPEED * delta;
        }

        playerBarBounds.y = Math.min(uiOffsetY + UI_HEIGHT_PX - BAR_HEIGHT_PX,
            Math.max(uiOffsetY, playerBarBounds.y));
    }

    private void handleFishLogic(float delta) {
        fish.setLastMove(fish.getLastMove() + delta);
        if (fish.getLastMove() > 0.5f) {
            fish.setLastMove(0);
            fishTargetY = fish.updatePositionY(fishCurrentY);
            fishTargetY = Math.min(Math.max(fishTargetY, uiOffsetY),
                uiOffsetY + UI_HEIGHT_PX - FISH_HEIGHT_PX);
        }

        float speed = fish.getSpeed() * delta * fish.getCoefficientSpeed();
        if (Math.abs(fishTargetY - fishCurrentY) < speed) {
            fishCurrentY = fishTargetY;
        } else {
            fishCurrentY += (fishTargetY > fishCurrentY ? 1 : -1) * speed;
        }

        fishBounds.setY(fishCurrentY);
    }

    private void updateProgress(float delta) {
        if (playerBarBounds.overlaps(fishBounds)) {
            catchProgress += PROGRESS_INCREASE_RATE * delta;
        } else {
            wasPerfectCatch = false;
            catchProgress -= PROGRESS_DECREASE_RATE * delta * 0.5f;
        }

        catchProgress = Math.max(0f, Math.min(1f, catchProgress));

        if (catchProgress >= 1f) {
            handleCatchSuccess();
        } else if (catchProgress <= 0f) {
            handleCatchFail();
        }
    }

    private void handleCatchSuccess() {
        isGameOver = true;
        hasPlayerWon = true;

        if (fish.getFishType().equals(FishType.Legend)) {
            showLegendaryFish = true;
            legendaryFishTimer = LEGENDARY_FISH_DURATION;
        }
    }

    private void handleCatchFail() {
        isGameOver = true;
        hasPlayerWon = false;
    }

    private void drawProgressBar(SpriteBatch batch) {
        float barWidth = 12f;
        float barHeight = UI_HEIGHT_PX * catchProgress;
        float barX = uiOffsetX + UI_WIDTH_PX - barWidth - 4;
        float barY = uiOffsetY;

        batch.setColor(catchProgress > 0.5f ? 0f : 1f,
            catchProgress > 0.5f ? 1f : 0.6f,
            0f, 1f);
        batch.draw(GameAssetManager.PIXEL, barX, barY, barWidth, barHeight);
        batch.setColor(1f, 1f, 1f, 1f);
    }

    private void drawLegendaryFish(SpriteBatch batch, float delta) {
        legendaryFishTimer -= delta;

        float w = FISH_WIDTH_PX * 4;
        float h = FISH_HEIGHT_PX * 4;
        float x = camera.position.x - w / 2;
        float y = camera.position.y - h / 2;

        batch.draw(fish.getTexture(), x, y, w, h);

        float crownW = w * 0.6f;
        float crownH = h * 0.4f;
        float crownX = x + w / 2 - crownW / 2;
        float crownY = y + h - crownH / 2;

        batch.draw(GameAssetManager.CROWN, crownX, crownY, crownW, crownH);

        if (legendaryFishTimer <= 0) {
            showLegendaryFish = false;
        }
    }

    private void drawFish(SpriteBatch batch, float delta) {
        snoarTimer -= delta;

        float w = FISH_WIDTH_PX * 4;
        float h = FISH_HEIGHT_PX * 4;
        Matrix4 originalProjection = batch.getProjectionMatrix().cpy();
        batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        batch.draw(fish.getTexture(), 10, Gdx.graphics.getHeight() - 300, 70, 70);
        batch.setProjectionMatrix(originalProjection);

        if (snoarTimer <= 0) {
            isSnoar = false;
        }
    }

    // Getter methods (unchanged)
    public boolean isGameOver() { return isGameOver; }
    public boolean isHasPlayerWon() { return hasPlayerWon; }
    public boolean isShowLegendaryFish() { return showLegendaryFish; }
    public boolean isWasPerfectCatch() { return wasPerfectCatch; }
    public float getCatchProgress() { return catchProgress; }
    public float getLegendaryFishTimer() { return legendaryFishTimer; }
    public float getLegendaryFishDuration() { return LEGENDARY_FISH_DURATION; }
    public Fish getFish() { return fish; }
    public FishingPole getFishingPole() { return fishingPole; }
    public OrthographicCamera getCamera() { return camera; }
    public void setShowLegendaryFish(boolean showLegendaryFish) {this.showLegendaryFish = showLegendaryFish;}

    public Sprite getMiniGameBackground() {
        return miniGameBackground;
    }

    public Sprite getPlayerBarSprite() {
        return playerBarSprite;
    }

    public Sprite getFishVisualSprite() {
        return fishVisualSprite;
    }

    public Rectangle getFishBounds() {
        return fishBounds;
    }

    public Rectangle getPlayerBarBounds() {
        return playerBarBounds;
    }

    public float getUiOffsetX() {
        return uiOffsetX;
    }

    public float getUiOffsetY() {
        return uiOffsetY;
    }

    public float getFishCurrentY() {
        return fishCurrentY;
    }

    public void setFishCurrentY(float fishCurrentY) {
        this.fishCurrentY = fishCurrentY;
    }

    public float getFishTargetY() {
        return fishTargetY;
    }

    public void setFishTargetY(float fishTargetY) {
        this.fishTargetY = fishTargetY;
    }

    public void setCatchProgress(float catchProgress) {
        this.catchProgress = catchProgress;
    }

    public void setWasPerfectCatch(boolean wasPerfectCatch) {
        this.wasPerfectCatch = wasPerfectCatch;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public void setHasPlayerWon(boolean hasPlayerWon) {
        this.hasPlayerWon = hasPlayerWon;
    }

    public void setLegendaryFishTimer(float legendaryFishTimer) {
        this.legendaryFishTimer = legendaryFishTimer;
    }

    public boolean isSnoar() {
        return isSnoar;
    }

    public void setSnoar(boolean snoar) {
        isSnoar = snoar;
    }

    public float getSnoarTimer() {
        return snoarTimer;
    }

    public void setSnoarTimer(float snoarTimer) {
        this.snoarTimer = snoarTimer;
    }
}
