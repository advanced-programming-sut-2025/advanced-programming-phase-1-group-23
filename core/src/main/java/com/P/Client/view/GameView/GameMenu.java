package com.P.Client.view.GameView;

import com.P.Client.app.ClientApp;
import com.P.Client.controller.MainMenuController;
import com.P.Client.model.GameAssetManager;
import com.P.Client.view.MainView;
import com.P.Main;
import com.P.Client.controller.game.GameController;
import com.P.common.model.Basics.App;
import com.P.common.model.game.GameModel;
import com.P.common.model.game.VillageModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.P.view.GameView.GameMenuInputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameMenu implements Screen {
    private GameView gameView;

    private GameView villageView;
    private GameView gameView1;
    private GameView gameView2;
    private GameView gameView3;
    private GameView gameView4;

    public GameModel gameModel;

    public GameModel gameModel1;
    public GameModel gameModel2;
    public GameModel gameModel3;
    public GameModel gameModel4;

    public VillageModel villageModel;

    private GameMenuInputAdapter gameMenuInputAdapter;
    private GameMenuInputAdapter gameMenuInputAdapter1;
    private GameMenuInputAdapter gameMenuInputAdapter2;
    private GameMenuInputAdapter gameMenuInputAdapter3;
    private GameMenuInputAdapter gameMenuInputAdapter4;
    private GameMenuInputAdapter villageMenuInputAdapter;
    private GameController gameController;
    private boolean isSleeping = false;
    private float sleepAlpha = 0f;
    private float sleepTimer = 0f;
    private static final float SLEEP_DURATION = 2f; // seconds
    private static final float FADE_SPEED = 1.5f;   // speed of fading
    private boolean advancingDay = false;
    private int threeX = 0;
    private int threeY = 0;
    public static boolean isVillage = false;
    public static boolean isExit = false;


    private Texture overlayTexture;
    private Sprite overlaySprite;

    public boolean isShowAllMaps() {
        return showAllMaps;
    }

    public void setShowAllMaps(boolean showAllMaps) {
        GameMenu.showAllMaps = showAllMaps;
    }

    public static boolean showAllMaps = false;

    public GameMenu(GameController gameController) {
        this.gameController = gameController;
        initializeGame();
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1f); // رنگ مشکی، شفافیت کامل
        pixmap.fill();
        overlayTexture = new Texture(pixmap);
        overlaySprite = new Sprite(overlayTexture);
        overlaySprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        pixmap.dispose();

    }

    private void initializeGame() {
        gameModel = App.loggedInUser.getCurrentGame().getCurrentPlayer().getGameModel();

        gameModel1 = App.loggedInUser.getCurrentGame().getPlayers().get(0).getGameModel();
        gameModel2 = App.loggedInUser.getCurrentGame().getPlayers().get(1).getGameModel();
        gameModel3 = App.loggedInUser.getCurrentGame().getPlayers().get(2).getGameModel();
        gameModel4 = App.loggedInUser.getCurrentGame().getPlayers().get(3).getGameModel();

        villageModel = App.loggedInUser.getCurrentGame().getVillageModel();

        villageView = new GameView(villageModel);
        gameView1 = new GameView(gameModel1);
        gameView2 = new GameView(gameModel2);
        gameView3 = new GameView(gameModel3);
        gameView4 = new GameView(gameModel4);

        gameView = new GameView(gameModel);
        gameMenuInputAdapter = new GameMenuInputAdapter(gameModel, gameController);
        gameMenuInputAdapter1 = new GameMenuInputAdapter(gameModel1, gameController);
        gameMenuInputAdapter2 = new GameMenuInputAdapter(gameModel2, gameController);
        gameMenuInputAdapter3 = new GameMenuInputAdapter(gameModel3, gameController);
        gameMenuInputAdapter4 = new GameMenuInputAdapter(gameModel4, gameController);
        villageMenuInputAdapter = new GameMenuInputAdapter(villageModel, gameController);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (!isExit) {
            if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.M)) {
                showAllMaps = !showAllMaps;
            }

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            if (showAllMaps) {
                int w = Gdx.graphics.getWidth() / 2;
                int h = Gdx.graphics.getHeight() / 2;

                renderGameView(delta, gameModel1, gameView1, 0, h, w, h);
                renderGameView(delta, gameModel2, gameView2, w, h, w, h);
                renderGameView(delta, gameModel3, gameView3, 0, 0, w, h);
                renderGameView(delta, gameModel4, gameView4, w, 0, w, h);
                renderVillageView(delta,villageModel,villageView,500,500,w,h);
            } else {
                Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                if (isVillage) {
                    Main.getBatch().begin();
                    Gdx.input.setInputProcessor(villageMenuInputAdapter);
                    villageModel.update(delta);
                    villageView.render(delta);
                    villageMenuInputAdapter.update(delta);
                    gameController.update(gameModel1, App.loggedInUser.getCurrentGame());
                    Main.getBatch().end();
                } else {
                    Main.getBatch().begin();
                    if (ClientApp.getTurnNumber() == 0) {
                        Gdx.input.setInputProcessor(gameMenuInputAdapter1);
                        gameModel1.update(delta);
                        gameView1.render(delta);
                        gameMenuInputAdapter1.update(delta);
                    }
                    if (ClientApp.getTurnNumber() == 1) {
                        Gdx.input.setInputProcessor(gameMenuInputAdapter2);
                        gameModel2.update(delta);
                        gameView2.render(delta);
                        gameMenuInputAdapter2.update(delta);
                    }
                    if (ClientApp.getTurnNumber() == 2) {
                        Gdx.input.setInputProcessor(gameMenuInputAdapter3);
                        gameModel3.update(delta);
                        gameView3.render(delta);
                        gameMenuInputAdapter3.update(delta);
                    }
                    if (ClientApp.getTurnNumber() == 3) {
                        Gdx.input.setInputProcessor(gameMenuInputAdapter4);
                        gameModel4.update(delta);
                        gameView4.render(delta);
                        gameMenuInputAdapter4.update(delta);
                    }

//        gameModel.update(delta);
//        gameView.render();
//        gameMenuInputAdapter.update(delta);
                    gameController.update(gameModel1, App.loggedInUser.getCurrentGame());
                    Main.getBatch().end();
                }

//            gameController.update();


            }
            Main.getBatch().begin();
            if (App.loggedInUser.getCurrentGame().getDate().toLocalTime().getHour() >= 18) {
                float hour = App.loggedInUser.getCurrentGame().getDate().toLocalTime().getHour();
                float alpha = Math.min((hour - 18) / 5f, 0.6f); // حداکثر تیرگی ۰.۶
                overlaySprite.setAlpha(alpha);
                overlaySprite.draw(Main.getBatch());
            }
            Main.getBatch().end();


            if (isSleeping) {
                sleepTimer += delta;
                if (!advancingDay && sleepAlpha < 1f) {
                    sleepAlpha = Math.min(1f, sleepAlpha + delta * FADE_SPEED);
                    if (sleepAlpha >= 1f) {
                        gameModel.advanceToNextDay();
                        advancingDay = true;
                    }
                } else if (advancingDay && sleepAlpha > 0f) {
                    sleepAlpha = Math.max(0f, sleepAlpha - delta * FADE_SPEED);
                    if (sleepAlpha <= 0f) {
                        isSleeping = false;
                        advancingDay = false;
                        sleepTimer = 0f;
                    }
                }

                // Render black overlay
                gameView.getBatch().begin();
                gameView.getBatch().setColor(0f, 0f, 0f, sleepAlpha);
                gameView.getBatch().draw(gameView.getPixel(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                gameView.getBatch().setColor(1f, 1f, 1f, 1f);
                gameView.getBatch().end();
            }
        } else {
            Gdx.input.setInputProcessor(null);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin(),this));
        }
    }

    private void renderGameView(float delta, GameModel model, GameView view, int x, int y, int width, int height) {
        Gdx.gl.glViewport(x, y, width, height);
        model.update(Gdx.graphics.getDeltaTime());
        Main.getBatch().setProjectionMatrix(model.getCamera().combined);
        Main.getBatch().begin();
        view.render(delta);
        Main.getBatch().end();
    }

    private void renderVillageView(float delta, VillageModel model, GameView view, int x, int y, int width, int height) {
        Gdx.gl.glViewport(x, y, width, height);
        model.update(Gdx.graphics.getDeltaTime());
        Main.getBatch().setProjectionMatrix(model.getCamera().combined);
        Main.getBatch().begin();
        view.render(delta);
        Main.getBatch().end();
    }


    public void startSleepTransition() {
        isSleeping = true;
        sleepAlpha = 0f;
        sleepTimer = 0f;
        advancingDay = false;
    }


    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    // Other Screen methods
}
