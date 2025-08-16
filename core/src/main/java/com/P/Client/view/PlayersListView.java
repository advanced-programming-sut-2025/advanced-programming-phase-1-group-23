package com.P.Client.view;

import com.P.Client.controller.BasicsController;
import com.P.Client.controller.LobbyController;
import com.P.Client.controller.MainMenuController;
import com.P.Client.model.GameAssetManager;
import com.P.Main;
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

public class PlayersListView implements Screen {
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Stage stage;
    public Table table;

    public TextButton back;
    public Label label;
    private BasicsController controller;

    private float timeSinceLastUpdate = 0f;


    public PlayersListView(BasicsController controller, Skin skin) {
        this.controller = controller;
        this.backgroundTexture = new Texture(Gdx.files.internal("background/onlineplayers.png"));
        this.backgroundImage = new Image(backgroundTexture);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Scripture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bigFont;
        style.fontColor = Color.WHITE;
        style.overFontColor = Color.PINK;
        style.downFontColor = Color.SKY;
        this.back = new TextButton("Back", style);

        this.label = new Label("", skin.get("white", Label.LabelStyle.class));

        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        String forLabel = controller.playerList().getAnswer();
        label.setText(forLabel);
        label.setFontScale(3f);
        label.setPosition(500, 600);
        label.setColor(Color.YELLOW);
        stage.addActor(label);

        back.setPosition(1300,200);
        stage.addActor(back);

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.clear();
                Main.getMain().setScreen(new MainView(new MainMenuController(), GameAssetManager.SKIN,null));

            }
        });
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        timeSinceLastUpdate += v;
        if (timeSinceLastUpdate >= 1f) { // هر 1 ثانیه
            String forLabel = controller.playerList().getAnswer();
            label.setText(forLabel);
            timeSinceLastUpdate = 0f;
        }
        stage.act(v);
        stage.draw();

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
