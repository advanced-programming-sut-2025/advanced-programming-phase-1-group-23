package com.P.Client.view.PreGameView;

import com.P.Client.app.ClientApp;
import com.P.Client.controller.*;
import com.P.Client.view.GameView.GameMenu;
import com.P.Client.view.GameView.GameView;
import com.P.Client.view.LobbyView;
import com.P.Client.view.MainView;
import com.P.Client.view.ProfileView;
import com.P.Main;
import com.P.Client.model.GameAssetManager;
import com.P.Server.model.Lobby;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Resualt;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.HashMap;

public class PreGameView implements Screen {
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Stage stage;
    public Table table;
    private TextButton newGame;
    private TextButton loadGame;
    private TextButton thisGame;
    private TextButton exitGame;
    private Label lobby;

    private TextField setLobby;
    private TextField searchLobby;
    private Label setLobbyLabel;
    private Label searchLobbyLabel;
    private TextButton setLobbyButton;
    private TextButton searchLobbyButton;
    private TextButton newLobby;
    private TextButton refresh;
    private TextButton load;
    private TextButton exit;
    private static String list = new String();

    private String password;
    private GameMenu gameMenu;

    private TurnController controller;
    private BasicsController controllerBasic = new BasicsController();

    public PreGameView(TurnController controller, Skin skin, GameMenu gameMenu) {
        this.controller = controller;
        this.backgroundTexture = new Texture(Gdx.files.internal("background/gamemenu.png"));
        this.backgroundImage = new Image(backgroundTexture);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Scripture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        BitmapFont bigFont = generator.generateFont(parameter);
        generator.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = bigFont;
        style.fontColor = Color.SKY;
        style.overFontColor = Color.BLUE;
        this.newGame = new TextButton("New Game", style);
        this.loadGame = new TextButton("Load Game", style);
        this.thisGame = new TextButton("Current Game", style);
        this.exitGame = new TextButton("Exit & Delete Game", style);
        this.newLobby = new TextButton("New Lobby", style);
        this.setLobbyButton = new TextButton("Set Lobby", skin);
        this.searchLobbyButton = new TextButton("Search Lobby", skin);
        this.refresh = new TextButton("Refresh", skin);
        this.exit = new TextButton("Exit", skin);

        this.lobby = new Label("", skin);
        this.setLobby = new TextField("Enter lobby name", skin);
        this.searchLobby = new TextField("Enter lobby ID", skin);

        this.setLobbyLabel = new Label("If the desired lobby name is\n visible, it is listed on the right!", skin);
        this.searchLobbyLabel = new Label("If it is not visible,\n you must enter its ID!", skin);

        this.table = new Table();
        this.gameMenu = gameMenu;

        controller.setView(this);
        controllerBasic.setView(this);

        this.password = null;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

//        table.setFillParent(true);
//        table.center();
//        table.row().pad(40, 0, 40, 0);
//        table.add(newGame).width(350).height(45);
//
//        table.row().pad(40, 0, 40, 0);
//        table.add(loadGame).width(350).height(45);
//
//        table.row().pad(40, 0, 40, 0);
//        table.add(thisGame).width(350).height(45);
//
//        table.row().pad(40, 0, 40, 0);
//        table.add(exitGame).width(350).height(45);
//
//        stage.addActor(table);
        table.setFillParent(true);
        table.center();
        table.row().pad(20, 0, 20, 0);
        table.add(setLobbyLabel).width(350).height(60);

        table.row().pad(20, 0, 20, 0);
        table.add(setLobby).width(350).height(60);

        table.row().pad(20, 0, 20, 0);
        table.add(setLobbyButton).width(350).height(60);

        table.row().pad(20, 0, 20, 0);
        table.add(searchLobbyLabel).width(350).height(60);

        table.row().pad(20, 0, 20, 0);
        table.add(searchLobby).width(350).height(60);

        table.row().pad(20, 0, 20, 0);
        table.add(searchLobbyButton).width(350).height(60);

        table.row().pad(20, 0, 20, 0);
        table.add(refresh).width(350).height(60);

//        table.row().pad(40, 0, 40, 0);
//        table.add(thisGame).width(350).height(45);
//
//        table.row().pad(40, 0, 40, 0);
//        table.add(exitGame).width(350).height(45);

        stage.addActor(table);

        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                // Main.getMain().setScreen(new NewGameView(controller, GameAssetManager.getGameAssetManager().getSkin()));
                Main.getMain().setScreen(new LobbyView(new LobbyController(), GameAssetManager.LABI_SKIN));
            }
        });

        loadGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameMenu.isExit = false;
                HashMap<String, Object> body = new HashMap<>();
                body.put("isExist", "false");
                ClientApp.getServerConnection().sendMessage(new Message(body, Message.MessageType.update));
                Main.getMain().setScreen(gameMenu);
            }
        });


        stage.addActor(table);
        String LobbiesList = controllerBasic.getLobbyList().getAnswer();
        Resualt resualt = controllerBasic.getLobbyList();
        System.out.println("LobbiesList: " + resualt.getAnswer());
        System.out.println(LobbiesList);
        lobby.setText(LobbiesList);
        lobby.setPosition(100, 100);
        stage.addActor(lobby);

        newLobby.setPosition(1300, 500);
        stage.addActor(newLobby);

        loadGame.setPosition(1300, 300);
        stage.addActor(loadGame);

        exit.setPosition(1300, 700);
        stage.addActor(exit);

        newLobby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().getScreen().dispose();
            }
        });

        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin(),null));
            }
        });

        newLobby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LobbyView(new LobbyController(), GameAssetManager.LABI_SKIN));
            }
        });

        refresh.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameView(controller, GameAssetManager.LABI_SKIN,gameMenu));
            }
        });

        setLobbyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt resualt = controllerBasic.findLobbyWithName();

                if (resualt.isAccept()) {
                    Resualt response = controllerBasic.isLobbyPrivate();
                    if (response.isAccept()) {
                        controllerBasic.add();
                        Main.getMain().getScreen().dispose();
                        Main.getMain().setScreen(new ExactLobbyView(controllerBasic, GameAssetManager.LABI_SKIN,controller));
                    } else {
                        Skin skin = GameAssetManager.LABI_SKIN;

                        final TextField passwordField = new TextField("", skin);
                        passwordField.setMessageText("Enter password");
                        passwordField.setPasswordCharacter('*');
                        passwordField.setPasswordMode(true);

                        Dialog dialog = new Dialog("Enter Password", skin, "dialog") {
                            @Override
                            protected void result(Object object) {
                                if (Boolean.TRUE.equals(object)) {
                                    String password = passwordField.getText();
                                    setPassword(password);
                                    Resualt response = controllerBasic.isCorrectPassword();
                                    if (!response.isAccept()) {
                                        Table redFlash = new Table();
                                        redFlash.setFillParent(true);
                                        redFlash.setColor(1, 0, 0, 1);

                                        stage.addActor(redFlash);
                                        redFlash.addAction(Actions.sequence(
                                            Actions.fadeOut(1f),
                                            Actions.run(() -> redFlash.remove())
                                        ));
                                        stage.addActor(redFlash);
                                    } else {
                                        controllerBasic.add();
                                        Main.getMain().getScreen().dispose();
                                        Main.getMain().setScreen(new ExactLobbyView(controllerBasic, GameAssetManager.LABI_SKIN,controller));
                                    }
                                } else {
                                    this.hide();
                                }
                            }
                        };

                        dialog.getContentTable().add(passwordField).width(200).pad(10);
                        dialog.row();

                        dialog.button("OK", true);
                        dialog.button("Cancel", false);

                        dialog.show(stage);

                    }
                } else {
                    Dialog dialog = new Dialog("We don't have a lobby with that name", GameAssetManager.LABI_SKIN) {
                        @Override
                        protected void result(Object object) {
                            if (Boolean.TRUE.equals(object)) {
                                this.hide();
                            }
                        }
                    };
                    dialog.text(resualt.getAnswer());
                    dialog.button("OK");
                    dialog.show(stage);
                }
            }
        });

        searchLobbyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("WW");
                Resualt resualt = controllerBasic.findLobbyWithID();
                System.out.println("WWWWWWWWWW");

                if (resualt.isAccept()) {
                    Dialog dialog = new Dialog("", GameAssetManager.LABI_SKIN) {
                        @Override
                        protected void result(Object object) {
                            if (Boolean.TRUE.equals(object)) {
                                this.hide();
                            }
                        }
                    };
                    dialog.text(resualt.getAnswer());
                    dialog.button("OK");
                    dialog.show(stage);
                } else {
                    Dialog dialog = new Dialog("", GameAssetManager.LABI_SKIN) {
                        @Override
                        protected void result(Object object) {
                            if (Boolean.TRUE.equals(object)) {
                                this.hide();
                            }
                        }
                    };
                    dialog.text("We don't have a lobby with that ID");
                    dialog.button("OK");
                    dialog.show(stage);
                }
            }
        });


    }

    @Override
    public void render(float delta) {
//        String LobbiesList = controllerBasic.getLobbyList().getAnswer();
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

    public TextButton getNewGame() {
        return newGame;
    }

    public void setNewGame(TextButton newGame) {
        this.newGame = newGame;
    }

    public TextButton getLoadGame() {
        return loadGame;
    }

    public void setLoadGame(TextButton loadGame) {
        this.loadGame = loadGame;
    }

    public TextButton getThisGame() {
        return thisGame;
    }

    public void setThisGame(TextButton thisGame) {
        this.thisGame = thisGame;
    }

    public TextButton getExitGame() {
        return exitGame;
    }

    public void setExitGame(TextButton exitGame) {
        this.exitGame = exitGame;
    }

    public Label getLobby() {
        return lobby;
    }

    public void setLobby(Label lobby) {
        this.lobby = lobby;
    }

    public TextField getSetLobby() {
        return setLobby;
    }

    public void setSetLobby(TextField setLobby) {
        this.setLobby = setLobby;
    }

    public TextField getSearchLobby() {
        return searchLobby;
    }

    public void setSearchLobby(TextField searchLobby) {
        this.searchLobby = searchLobby;
    }

    public Label getSetLobbyLabel() {
        return setLobbyLabel;
    }

    public void setSetLobbyLabel(Label setLobbyLabel) {
        this.setLobbyLabel = setLobbyLabel;
    }

    public Label getSearchLobbyLabel() {
        return searchLobbyLabel;
    }

    public void setSearchLobbyLabel(Label searchLobbyLabel) {
        this.searchLobbyLabel = searchLobbyLabel;
    }

    public TextButton getSetLobbyButton() {
        return setLobbyButton;
    }

    public void setSetLobbyButton(TextButton setLobbyButton) {
        this.setLobbyButton = setLobbyButton;
    }

    public TextButton getSearchLobbyButton() {
        return searchLobbyButton;
    }

    public void setSearchLobbyButton(TextButton searchLobbyButton) {
        this.searchLobbyButton = searchLobbyButton;
    }

    public TextButton getNewLobby() {
        return newLobby;
    }

    public void setNewLobby(TextButton newLobby) {
        this.newLobby = newLobby;
    }

    public TextButton getRefresh() {
        return refresh;
    }

    public void setRefresh(TextButton refresh) {
        this.refresh = refresh;
    }

    public TurnController getController() {
        return controller;
    }

    public void setController(TurnController controller) {
        this.controller = controller;
    }

    public BasicsController getControllerBasic() {
        return controllerBasic;
    }

    public void setControllerBasic(BasicsController controllerBasic) {
        this.controllerBasic = controllerBasic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getList() {
        return list;
    }

    public static void setList(String list) {
        PreGameView.list = list;
    }
}
