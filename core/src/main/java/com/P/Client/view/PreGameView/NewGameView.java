package com.P.Client.view.PreGameView;

import com.P.Client.controller.TurnController;
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

import java.util.Objects;

public class NewGameView implements Screen {
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Stage stage;
    public Table table;
    public Table mapTable;

    private TextField player1;
    private TextField player2;
    private TextField player3;
    private TextButton addPlayers;

    private TextField map1;
    private TextField map2;
    private TextField map3;
    private TextField map4;

    private TextButton setMap;

    private TextButton startGame;

    private TurnController controller;

    private Label label;

    private static int check = 0;

    public NewGameView(TurnController controller, Skin skin) {
        this.controller = controller;
        this.backgroundTexture = new Texture(Gdx.files.internal("background/gamemenu.png"));
        this.backgroundImage = new Image(backgroundTexture);

        this.player1 = new TextField("Player1", skin);
        this.player2 = new TextField("Player2", skin);
        this.player3 = new TextField("Player3", skin);
        this.map1 = new TextField("Your Map", skin);
        this.map2 = new TextField("Player 1's Map", skin);
        this.map3 = new TextField("Player 2's Map", skin);
        this.map4 = new TextField("Player 3's Map", skin);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Scripture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bigFont;
        style.fontColor = Color.ORANGE;
        style.overFontColor = Color.BLUE;
        this.addPlayers = new TextButton("Add Players", style);
        this.setMap = new TextButton("Select Map", style);

        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Lighning Ceremony.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 100;
        BitmapFont bigFont2 = generator2.generateFont(parameter2);
        generator2.dispose();
        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle();
        style2.font = bigFont2;
        style2.fontColor = Color.MAGENTA;
        style2.overFontColor = Color.BLUE;
        this.startGame = new TextButton("^_^ Welcome to Stardew Valley game!", style2);

        this.label = new Label("", skin);

        this.table = new Table();
        this.mapTable = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

//        table.setFillParent(true);
//        table.left();
//        table.row().pad(20, 0, 20, 0);
//        table.setPosition(200, 0);
//        table.add(player1).width(500).height(70);
//
//        table.row().pad(20, 0, 20, 0);
//        table.add(player2).width(500).height(70);
//
//        table.row().pad(20, 0, 20, 0);
//        table.add(player3).width(500).height(70);
//
//        table.row().pad(40, 0, 40, 0);
//        table.add(addPlayers).width(500).height(50);
//
//        stage.addActor(table);

        mapTable.setFillParent(true);
        mapTable.center();
        mapTable.row().pad(20, 0, 20, 0);

        mapTable.add(setMap).width(500).height(70);
        mapTable.row().pad(20, 0, 20, 0);
        mapTable.add(map1).width(500).height(70);

        mapTable.add(label).width(500).height(70);

        stage.addActor(mapTable);

        setMap.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Objects.equals(map1.getText(), "1") || Objects.equals(map1.getText(), "2")) {
                    controller.handleMapSelection();
                    mapTable.add(map1).width(500).height(70);
                    check++;
                    table.clear();
                    mapTable.clear();
                    stage.addActor(mapTable);
                    stage.addActor(table);
                    mapTable.setFillParent(true);
                    mapTable.center();
                    mapTable.setPosition(-80, 0);
                    mapTable.add(startGame).width(500).height(70);
                    stage.addActor(mapTable);
                }else{
                    label.setColor(Color.RED);
                    label.setScale(2f);
                    label.setText("ONLY CHOSE 1 OR 2");
                }

            }
        });

        startGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToGame();
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

    public Table getMapTable() {
        return mapTable;
    }

    public void setMapTable(Table mapTable) {
        this.mapTable = mapTable;
    }

    public TextField getPlayer1() {
        return player1;
    }

    public void setPlayer1(TextField player1) {
        this.player1 = player1;
    }

    public TextField getPlayer2() {
        return player2;
    }

    public void setPlayer2(TextField player2) {
        this.player2 = player2;
    }

    public TextField getPlayer3() {
        return player3;
    }

    public void setPlayer3(TextField player3) {
        this.player3 = player3;
    }

    public TextButton getAddPlayers() {
        return addPlayers;
    }

    public void setAddPlayers(TextButton addPlayers) {
        this.addPlayers = addPlayers;
    }

    public TextField getMap1() {
        return map1;
    }

    public void setMap1(TextField map1) {
        this.map1 = map1;
    }

    public TextField getMap2() {
        return map2;
    }

    public void setMap2(TextField map2) {
        this.map2 = map2;
    }

    public TextField getMap3() {
        return map3;
    }

    public void setMap3(TextField map3) {
        this.map3 = map3;
    }

    public TextField getMap4() {
        return map4;
    }

    public void setMap4(TextField map4) {
        this.map4 = map4;
    }

    public TextButton getStartGame() {
        return startGame;
    }

    public void setStartGame(TextButton startGame) {
        this.startGame = startGame;
    }

    public TurnController getController() {
        return controller;
    }

    public void setController(TurnController controller) {
        this.controller = controller;
    }
}
