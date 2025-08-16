package com.P.Client.view.PreGameView;

import com.P.Client.app.ClientApp;
import com.P.Client.controller.BasicsController;
import com.P.Client.controller.RegisterController;
import com.P.Client.controller.TurnController;
import com.P.Client.model.GameAssetManager;
import com.P.Client.view.SignupView;
import com.P.Main;
import com.P.common.model.Resualt;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ExactLobbyView implements Screen {
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Stage stage;
    public Table table;
    public Label label;
    public TextButton back;
    public TextButton refresh;


    public TextButton startGame;

    private BasicsController controller;
    private TurnController controllerTurn;

    public ExactLobbyView(BasicsController controller, Skin skin, TurnController controllerTurn) {
        this.backgroundTexture = new Texture(Gdx.files.internal("background/lobby.png"));
        this.backgroundImage = new Image(backgroundTexture);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/stardew-valley.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bigFont;
        style.fontColor = Color.VIOLET;
        style.downFontColor = Color.FOREST;
        this.startGame = new TextButton("^_^ LET'S START NEW GAME", style);
        this.back = new TextButton("^_^ LET'S BACK", style);
        this.refresh = new TextButton("^_^ LET'S REFRESH", style);

        this.label = new Label(" ", skin);

        this.table = new Table();
        this.controller = controller;
        this.controllerTurn = controllerTurn;
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        table.setFillParent(true);
        table.center();
        Resualt resualt = controller.isUserAdmin();
        if (resualt.isAccept()) {
            table.row().pad(10, 40, 10, 0);
            table.add(startGame).width(600).height(70);
        }
        table.row().pad(10, 0, 10, 0);
        table.add(back).width(600).height(70);

        table.row().pad(10, 0, 10, 0);
        table.add(refresh).width(600).height(70);

        stage.addActor(table);

        String forLabel = controller.getLobbyInformation().getAnswer();
        label.setText(forLabel);
        label.setFontScale(3f);
        label.setPosition(100, 400);
        stage.addActor(label);

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameView(new TurnController(), GameAssetManager.LABI_SKIN,null));

            }
        });

        refresh.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ExactLobbyView(controller, GameAssetManager.LABI_SKIN, controllerTurn));
            }
        });

        startGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.letsPlay();
            }
        });

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();

        if(ClientApp.getStartGame().get()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new NewGameView(new TurnController(), GameAssetManager.getGameAssetManager().getSkin()));
        }

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
}
