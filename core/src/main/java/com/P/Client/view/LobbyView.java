package com.P.Client.view;

import com.P.Client.controller.LobbyController;
import com.P.Client.controller.TurnController;
import com.P.Client.model.GameAssetManager;
import com.P.Client.view.PreGameView.PreGameView;
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

public class LobbyView implements Screen {
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Stage stage;
    public Table table;

    private TextField nameField;
    private TextField passwordField;
    private TextButton creatLobbyButton;
    private Label passLabel;
    private CheckBox isVisible;
    private Label message;
    private TextButton backButton;


    private LobbyController controller;

    public LobbyView(LobbyController controller, Skin skin) {
        this.controller = controller;
        this.backgroundTexture = new Texture(Gdx.files.internal("background/lobby.png"));
        this.backgroundImage = new Image(backgroundTexture);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Bahu.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bigFont;
        style.fontColor = Color.SKY;
        style.overFontColor = Color.PINK;
        style.downFontColor = Color.SKY;
        this.creatLobbyButton = new TextButton("^_^ LET'S GO", style);
        this.backButton = new TextButton("^_^ Back", style);

        this.passwordField = new TextField("Lobby's Password?", skin);
        this.nameField = new TextField("Lobby's Name", skin);
        this.passLabel = new Label("Enter 0 if you want public LOBBY", skin);
        this.isVisible = new CheckBox("Visible Lobby?",skin);

        this.message = new Label("", skin.get("title", Label.LabelStyle.class));

        this.table = new Table();

        controller.setView(this);
    }


    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        table.setFillParent(true);
        table.center();
        table.row().pad(10, 0, 10, 0);
        table.add(nameField).width(600).height(70);

        table.row().pad(2, 0, 2, 0);
        passLabel.setFontScale(2);
        passLabel.setColor(Color.BROWN);
        table.add(passLabel).width(600).height(70);

        table.row().pad(10, 0, 10, 0);
        table.add(passwordField).width(600).height(70);

        table.row().pad(10, 0, 10, 0);
        CheckBox.CheckBoxStyle style = new CheckBox.CheckBoxStyle(isVisible.getStyle());
        style.font.getData().setScale(2f);
        style.checkboxOff.setMinWidth(40);
        style.checkboxOff.setMinHeight(40);
        style.checkboxOn.setMinWidth(40);
        style.checkboxOn.setMinHeight(40);
        style.fontColor = Color.BROWN;
        isVisible.setStyle(style);
        table.add(isVisible).width(500).height(50);
        table.pack();


        table.row().pad(10, 0, 10, 0);
        table.add(creatLobbyButton).width(500).height(50);

        table.row().pad(10, 0, 10, 0);
        table.add(backButton).width(500).height(50);

        stage.addActor(table);

        creatLobbyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               Resualt resualt = controller.createLobby();
               message.setText(resualt.getAnswer());
                table.row().pad(10, 0, 10, 0);
                table.add(message).width(500).height(50);

                stage.addActor(table);
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameView(new TurnController(), GameAssetManager.LABI_SKIN,null));
            }
        });

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
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

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public void setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(TextField passwordField) {
        this.passwordField = passwordField;
    }

    public TextButton getCreatLobbyButton() {
        return creatLobbyButton;
    }

    public void setCreatLobbyButton(TextButton creatLobbyButton) {
        this.creatLobbyButton = creatLobbyButton;
    }

    public Label getPassLabel() {
        return passLabel;
    }

    public void setPassLabel(Label passLabel) {
        this.passLabel = passLabel;
    }

    public CheckBox getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(CheckBox isVisible) {
        this.isVisible = isVisible;
    }

    public LobbyController getController() {
        return controller;
    }

    public void setController(LobbyController controller) {
        this.controller = controller;
    }
}
