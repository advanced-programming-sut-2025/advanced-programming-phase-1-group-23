package com.P.common.model.game;

import com.P.Main;
import com.P.Client.controller.GameController;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.User;
import com.P.Client.model.GameAssetManager;
import com.P.common.model.Maps.Farm;
import com.P.common.model.Objects.Barn;
import com.P.Client.model.Pair;
import com.P.common.model.item.GrowingCrop;
import com.P.common.model.item.TileDescriptionId;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameModel {
    private TileDescriptionId[][] tiles;
    private Map<Point, GrowingCrop> growingCrops;
    private Player player;
    private final int mapWidth;
    private final int mapHeight;
    private static OrthographicCamera camera;
    private ArrayList<SpriteMine> rainDrops = new ArrayList<>();
    private ArrayList<Sprite> snow = new ArrayList<>();
    private ArrayList<SpriteMine> storms = new ArrayList<>();
    float delta = 0f;
    private float flashAlpha = 0f;
    private boolean flashActive = false;

    public GameModel(int mapWidth, int mapHeight, int turnNumber) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        tiles = new TileDescriptionId[50][75];
        initializeTiles(turnNumber);

        growingCrops = new HashMap<>();
        player = App.getLoggedInUser().getCurrentGame().getPlayers().get(turnNumber);
        System.out.println("%%%");
        System.out.println("&: " + player.getUser().getUsername() + " " + player.getUser().getNickname());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(player.getPlayerPosition().first, player.getPlayerPosition().second, 0);

    }

    private void initializeTiles(int turnNumber) {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Farm farm = game.getPlayers().get(turnNumber).getCurrentFarm(game);
        tiles = farm.getTiles();
        System.out.println(user.getNickname());
    }


    public void update(float deltaTime) {
        Pair<Float, Float> playerPos = player.getPlayerPosition();
        float playerX = playerPos.first * Main.TILE_SIZE;
        float playerY = playerPos.second * Main.TILE_SIZE;

        this.delta += deltaTime;

        float camX = camera.position.x;
        float camY = camera.position.y;

        float viewHalfWidth = camera.viewportWidth / 2;
        float viewHalfHeight = camera.viewportHeight / 2;

        float border = Main.TILE_SIZE * 5;

        if (playerX < camX - viewHalfWidth + border) {
            camX = playerX + viewHalfWidth - border;
        } else if (playerX > camX + viewHalfWidth - border) {
            camX = playerX - viewHalfWidth + border;
        }

        if (playerY < camY - viewHalfHeight + border) {
            camY = playerY + viewHalfHeight - border;
        } else if (playerY > camY + viewHalfHeight - border) {
            camY = playerY - viewHalfHeight + border;
        }

        camX = Math.max(viewHalfWidth, Math.min(camX, mapWidth * Main.TILE_SIZE - viewHalfWidth));
        camY = Math.max(viewHalfHeight, Math.min(camY, mapHeight * Main.TILE_SIZE - viewHalfHeight));


        camera.position.set(camX, camY, 0);
        camera.update();
    }


    public void advanceToNextDay() {
        growingCrops.forEach((point, growingCrop) -> {
            growingCrop.advance();
        });
    }

    public static OrthographicCamera getCamera() {
        return camera;
    }

    public Player getPlayer() {
        return player;
    }

    public TileDescriptionId[][] getTiles() {
        return tiles;
    }

    public void addGrowingCrop(GrowingCrop growingCrop, Point position) {
        growingCrops.put(position, growingCrop);
    }

    public void water(Point position) {
        GrowingCrop growingCrop = growingCrops.get(position);
        if (growingCrop != null) {
            growingCrop.water();
        }
    }

    public void harvest(Point position) {
        GrowingCrop crop = growingCrops.get(position);
        if (crop != null && crop.isReady()) {
            growingCrops.remove(position);
            player.addItem(crop.getResult(), 1);
        }
    }

    public TileDescriptionId getTile(Point point) {
        return tiles[point.x][point.y];
    }

    public Map<Point, GrowingCrop> getGrowingCrops() {
        return growingCrops;
    }

    public void setTiles(TileDescriptionId[][] tiles) {
        this.tiles = tiles;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void spawnRainDrop() {
        Random rand = new Random();
        if (rand.nextInt(25) != 4) return;

        Sprite sprite = new Sprite(GameAssetManager.RAIN[0][0]);
        sprite.setPosition(
            camera.position.x - camera.viewportWidth / 2f + rand.nextFloat() * camera.viewportWidth,
            camera.position.y + camera.viewportHeight / 2f
        );
        sprite.setScale(1.875f);
        rainDrops.add(new SpriteMine(sprite));
    }

    private void explodeRainDrop(SpriteMine rainDrop) {
        rainDrop.setMoving(false);
        float startX = rainDrop.getSprite().getX();
        float startY = rainDrop.getSprite().getY();

        for (int i = 0; i < 10; i++) {
            int frameIndex = i + 1;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    Sprite newSprite = new Sprite(GameAssetManager.RAIN[0][frameIndex]);
                    newSprite.setPosition(startX, startY);
                    newSprite.setScale(1.875f);
                    rainDrop.setSprite(newSprite);
                }
            }, 0.3f * i + 0.1f);
        }

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                rainDrops.remove(rainDrop);
            }
        }, 3.1f);
    }

    private void updateRainDrop(SpriteMine rainDrop) {
        Random rand = new Random();

        if (rand.nextInt(300) == 4) {
            explodeRainDrop(rainDrop);
            return;
        }
        if (rainDrop.getSprite().getY() + rainDrop.getSprite().getHeight() < camera.position.y - camera.viewportHeight / 2f - 20) {
            rainDrops.remove(rainDrop);
            return;
        }
        if (rainDrop.isMoving()) {
            rainDrop.getSprite().translateY(-1000 * Gdx.graphics.getDeltaTime());
        }
    }

    private void drawRainDrops() {
        for (SpriteMine rainDrop : rainDrops) {
            rainDrop.getSprite().draw(Main.getBatch());
        }
    }

    public void handleRainDrops() {
        spawnRainDrop();
        Iterator<SpriteMine> iterator = rainDrops.iterator();
        while (iterator.hasNext()) {
//            SpriteMine rainDrop = iterator.next();
//            updateRainDrop(rainDrop);
        }

        drawRainDrops();
    }

    private void spawnSnow() {
        if (!snow.isEmpty()) return;

        Random rand = new Random();
        float snowWidth = GameAssetManager.SNOW.getWidth();
        float snowHeight = GameAssetManager.SNOW.getHeight();

        int cols = (int) (camera.viewportWidth / snowWidth);
        int rows = (int) (6 * camera.viewportHeight / snowHeight);

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j <= rows; j++) {
                Sprite sprite = new Sprite(GameAssetManager.SNOW);
                sprite.setScale(1);
                sprite.setPosition(
                    i * snowWidth,
                    j * snowHeight + rand.nextInt((int) snowHeight)
                );
                snow.add(sprite);
            }
        }
    }

    private void updateSnowDrop(Sprite snowDrop) {
        float delta = Gdx.graphics.getDeltaTime();
        float offsetX = camera.position.x - camera.viewportWidth / 2f;

        snowDrop.setY(snowDrop.getY() - 400 * delta);

        if (snowDrop.getY() + snowDrop.getHeight() < camera.position.y - camera.viewportHeight / 2f - 20) {
            snowDrop.setY(camera.position.y + camera.viewportHeight / 2f);
        }
        snowDrop.setX(snowDrop.getX() + offsetX);
        snowDrop.draw(Main.getBatch());
        snowDrop.setX(snowDrop.getX() - offsetX);
    }

    private void drawSnowDrops() {
        for (Sprite snowDrop : snow) {
            snowDrop.draw(Main.getBatch());
        }
    }

    public void handleSnowDrops() {
        spawnSnow();
        for (Sprite snowDrop : snow) {
            updateSnowDrop(snowDrop);
        }
    }

    public void handleStorms() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();

        if (rand.nextInt(2000) == 4) {
            GameController.handleCheatThor(rand.nextInt(50), rand.nextInt(75));
            flashAlpha = 1f;
            flashActive = true;
        }

        if (rand.nextInt(200) == 4) {
            storms.add(createStormSprite());
        }

        updateStorms();

        if (delta > 0.3f) {
            delta = 0f;
        }
    }

    private SpriteMine createStormSprite() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();

        Sprite sprite = new Sprite(
            GameAssetManager.STORM[rand.nextInt(2)][rand.nextInt(4)]
        );

        float x = camera.position.x - camera.viewportWidth / 2f + rand.nextFloat(camera.viewportWidth);
        float y = camera.position.y + camera.viewportHeight / 2f;

        sprite.setPosition(x, y);
        sprite.setScale(0.938f);

        return new SpriteMine(sprite);
    }

    private void updateStorms() {
        Iterator<SpriteMine> iterator = storms.iterator();

        while (iterator.hasNext()) {
            SpriteMine storm = iterator.next();

            if (delta > 0.1f) {
                Sprite s = storm.getSprite();
                s.setY(s.getY() - s.getHeight() / 2.5f);

                if (s.getY() + s.getHeight() < camera.position.y - camera.viewportHeight / 2f - 20) {
                    iterator.remove();
                    continue;
                }

                Sprite newSprite = new Sprite(
                    GameAssetManager.STORM[ThreadLocalRandom.current().nextInt(2)][ThreadLocalRandom.current().nextInt(4)]
                );
                newSprite.setPosition(s.getX(), s.getY());
                newSprite.setScale(0.938f);

                storm.setSprite(newSprite);
            }

            storm.getSprite().draw(Main.getBatch());
        }
    }

    public boolean isFlashActive() {
        return flashActive;
    }

    public void setFlashActive(boolean flashActive) {
        this.flashActive = flashActive;
    }

    public float getFlashAlpha() {
        return flashAlpha;
    }

    public void setFlashAlpha(float flashAlpha) {
        this.flashAlpha = flashAlpha;
    }

    public ArrayList<SpriteMine> getRainDrops() {
        return rainDrops;
    }

    public void setRainDrops(ArrayList<SpriteMine> rainDrops) {
        this.rainDrops = rainDrops;
    }

    public ArrayList<Sprite> getSnow() {
        return snow;
    }

    public void setSnow(ArrayList<Sprite> snow) {
        this.snow = snow;
    }

    public ArrayList<SpriteMine> getStorms() {
        return storms;
    }

    public void setStorms(ArrayList<SpriteMine> storms) {
        this.storms = storms;
    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }
}
