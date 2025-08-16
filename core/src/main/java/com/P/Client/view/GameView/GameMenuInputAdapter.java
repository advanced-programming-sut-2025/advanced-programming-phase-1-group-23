package com.P.view.GameView;

import com.P.Client.controller.WebController;
import com.P.Client.view.GameView.GameMenu;
import com.P.Main;
import com.P.Client.controller.TurnController;
import com.P.Client.controller.game.GameController;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Player;
import com.P.Client.model.Pair;
import com.P.common.model.enums.Season;
import com.P.common.model.enums.Weather;
import com.P.common.model.game.GameModel;
import com.P.common.model.game.VillageModel;
import com.P.common.model.item.ItemDescriptionId;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class GameMenuInputAdapter extends InputAdapter {
    private GameModel game;
    private VillageModel village;

    private final GameController gameController;
    private final Set<Integer> keysHeld = new HashSet<>();
    private int threeX = 0;
    private int threeY = 0;
    private int cnt = 0;

    public GameMenuInputAdapter(GameModel game, GameController gameController) {
        this.game = game;
        this.gameController = gameController;
    }

    public GameMenuInputAdapter(VillageModel game, GameController gameController) {
        this.village = game;
        this.gameController = gameController;
    }
    public boolean isWinter = false;
    @Override
    public boolean keyDown(int keycode) {
        keysHeld.add(keycode);

        if (keycode >= Input.Keys.NUM_1 && keycode <= Input.Keys.NUM_9) {
            int selectedSlot = keycode - Input.Keys.NUM_1;
            if (game != null)
                game.getPlayer().setSelectedSlot(selectedSlot);
            else
                village.getPlayer().setSelectedSlot(selectedSlot);
            return true;
        }

        if (keycode == Input.Keys.F11) {
            // gameController.goToMain();
            App.loggedInUser.getCurrentGame().getCurrentPlayer().setEnergy(0);
            return true;
        }

        if (keycode == Input.Keys.N) {
            //  gameController.advanceToNextDay();
            TurnController.handleNextTurn();
        }

        if (keycode == Input.Keys.SPACE) {
            App.getLoggedInUser().getCurrentGame().setSeason(Season.WINTER);
            App.getLoggedInUser().getCurrentGame().getClock().setSeasonSprite();
        }

        if (keycode == Input.Keys.ENTER) {
            App.getLoggedInUser().getCurrentGame().setSeason(Season.SPRING);
            App.getLoggedInUser().getCurrentGame().getClock().setSeasonSprite();
        }


        if (keycode == Input.Keys.L) {
            App.getLoggedInUser().getCurrentGame().setWeatherToday(Weather.STORM);
            App.getLoggedInUser().getCurrentGame().getClock().setWeatherSprite();
            //  System.out.println(App.getLoggedInUser().getCurrentGame().getWeatherTomorrow());
        }

        if (keycode == Input.Keys.COMMA) {
            GameMenu.isVillage = true;
            App.getLoggedInUser().getCurrentGame().getCurrentPlayer().setInVillage(true);
        }
        if (keycode == Input.Keys.MINUS) {
            GameMenu.isVillage = false;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_LEFT)) {
            //  Main.getMain().setScreen(new MiniGame());
        }


        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keysHeld.remove(keycode);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (game != null) {
            int current = game.getPlayer().getSelectedSlot();
            int size = game.getPlayer().getMaxInventorySize();
            int next = (current + (amountY > 0 ? 1 : -1) + size) % size;
            game.getPlayer().setSelectedSlot(next);
            return true;
        } else {
            int current = village.getPlayer().getSelectedSlot();
            int size = village.getPlayer().getMaxInventorySize();
            int next = (current + (amountY > 0 ? 1 : -1) + size) % size;
            village.getPlayer().setSelectedSlot(next);
            return true;
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            performAction(screenX, screenY);
            return true;
        }
        return false;
    }

    public void update(float delta) {
        Player player;
        if (game != null)
            player = game.getPlayer();
        else
            player = village.getPlayer();

        float vx = 0, vy = 0;
        int dir = 0;
        int x;
        x = 0;
        int y;
        y = 0;

        if (keysHeld.contains(Input.Keys.W)) {
            vy += 1;
            dir = 3;
            y += 1;
            threeY++;
        }
        if (keysHeld.contains(Input.Keys.S)) {
            vy -= 1;
            dir = 1;
            y -= 1;
            threeY--;
        }
        if (keysHeld.contains(Input.Keys.A)) {
            vx -= 1;
            dir = 4;
            x -= 1;
            threeX--;
        }
        if (keysHeld.contains(Input.Keys.D)) {
            vx += 1;
            dir = 2;
            x += 1;
            threeX++;
        }

        if (keysHeld.contains(Input.Keys.P)) {
            //    App.getLoggedInUser().getCurrentGame().getDate().plusDays(1);
            // App.getLoggedInUser().getCurrentGame().setDate(App.getLoggedInUser().getCurrentGame().getDate().plusHours(1));
//            com.P.Client.controller.GameController.handleCheatAdvanceTime(2);
            for (int i = 0; i < 3; i++) {
                App.loggedInUser.getCurrentGame().advanceTime();
            }
        }


        float length = (float) Math.sqrt(vx * vx + vy * vy);
        if (length > 0) {
            vx /= length;
            vy /= length;
            player.setMovingDirection(dir);
//            cnt++;
        } else {
            player.setMovingDirection(0);
//            cnt++;
//            if(cnt > 300) {
//                WebController.UpdatePosition(player.getPlayerPosition().first, player.getPlayerPosition().second);
//                System.out.println("update");
//                cnt = 0;
//            }
        }

        float speed = player.getSpeed();
        player.setVelocity(vx * speed, vy * speed, x * 2.9f, y * 3.5f);
        if (game != null)
            player.update(delta, game.getTiles());
        else
            player.update(delta, village.getTiles());
    }


    private void performAction(int screenX, int screenY) {
        OrthographicCamera camera;
        if (game != null)
            camera = game.getCamera();
        else
            camera = village.getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos;
        if (game != null)
            playerPos = game.getPlayer().getPlayerPosition();
        else
            playerPos = village.getPlayer().getPlayerPosition();

        int tileX = (int) (worldCoordinates.x / Main.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / Main.TILE_SIZE);

        int dx = tileX - Math.round(playerPos.first);
        int dy = tileY - Math.round(playerPos.second);

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            return;
        }

        ItemDescriptionId selectedItem;
        if (game != null)
            selectedItem = game.getPlayer().getSelectedItem();
        else
            selectedItem = village.getPlayer().getSelectedItem();
        if (selectedItem != null) {
            // gameController.useItem(selectedItem, new Point(tileX, tileY), game);
        }
    }
}

