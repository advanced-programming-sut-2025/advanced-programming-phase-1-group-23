package com.P.Client.view;

import com.P.Client.controller.*;
import com.P.Main;
import com.P.common.model.Basics.App;
import com.P.Client.model.GameAssetManager;
import com.P.Client.view.PreGameView.PreGameView;
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

public class MainView implements Screen {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;
    public Table table;
    private TextButton logoutButton;
    private TextButton profileButton;
    private TextButton gameButton;
    private Texture avatarTexture;
    private Image avatarImage;
    private Label nameLabel;
    private TextButton playersList;

    private MainMenuController controller;
    private com.P.Client.view.GameView.GameMenu gameMenu;

    public MainView(MainMenuController controller, Skin skin,  com.P.Client.view.GameView.GameMenu gameMenu ) {
        this.controller = controller;
        this.backgroundTexture = new Texture("background/mainmenu.png");
        this.backgroundImage = new Image(backgroundTexture);

        this.avatarTexture = new Texture(App.loggedInUser.getAvatar().getIconPath());
        this.avatarImage = new Image(avatarTexture);

        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Scripture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont bigFont2 = generator2.generateFont(parameter2);
        parameter2.size = 100;
        bigFont2 = generator2.generateFont(parameter2);
        generator2.dispose();

        Label.LabelStyle style2 = new Label.LabelStyle(bigFont2, Color.BLUE);
        this.nameLabel = new Label("", style2);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Scripture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        BitmapFont bigFont = generator.generateFont(parameter);
        bigFont = generator.generateFont(parameter);
        generator.dispose();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bigFont;
        style.fontColor = Color.NAVY;
        style.overFontColor = Color.YELLOW;
        style.downFontColor = Color.GREEN;
        this.gameButton = new TextButton("Game Menu", style);
        this.logoutButton = new TextButton("Logout", style);
        this.profileButton = new TextButton("Profile Menu", style);
        this.playersList = new TextButton("Online Players", style);

        this.gameMenu = gameMenu;

        this.table = new Table();
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        avatarImage.setPosition(100,700);
        avatarImage.setSize(200, 200);
        stage.addActor(avatarImage);

        nameLabel.setPosition(310,750);
        nameLabel.setText(App.getLoggedInUser().getNickname());
        stage.addActor(nameLabel);

        table.setFillParent(true);
        table.center();
        table.row().pad(30, 0, 30, 0);
        table.setPosition(0, 100);
        table.add(profileButton).width(500).height(50);

        table.row().pad(30, 0, 30, 0);
        table.add(profileButton).width(500).height(50);

        table.row().pad(30, 0, 30, 0);
        table.add(playersList).width(500).height(50);

        table.row().pad(30, 0, 30, 0);
        table.add(gameButton).width(500).height(50);

        table.row().pad(30, 0, 30, 0);
        table.add(logoutButton).width(500).height(50);

        stage.addActor(table);

        logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleLogout();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SignupView(new RegisterController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });

        profileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               // Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileView(new ProfileController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });

        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameView(new TurnController(), GameAssetManager.LABI_SKIN,gameMenu));
            }
        });

        playersList.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PlayersListView(new BasicsController(), GameAssetManager.SKIN));
            }
        });

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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
