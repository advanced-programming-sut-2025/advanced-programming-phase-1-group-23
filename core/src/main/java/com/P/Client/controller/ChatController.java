package com.P.Client.controller;

import com.P.Client.controller.game.ChatController1;
import com.P.Client.model.GameAssetManager;
import com.P.Client.model.Pair;
import com.P.Client.model.Payam;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.game.GameModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ChatController {
    public static Stage chatStage = null;
    public static boolean isChatOpen = false;
    private static ChatController1 controller = new ChatController1();

    public void update(GameModel model, Game game) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            createChatMenu(model, game);
        }
        if (isChatOpen && chatStage != null) {
            chatStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(chatStage);
            chatStage.draw();
        }
    }

    public void createChatMenu(GameModel model, Game game) {
        chatStage = new Stage(new ScreenViewport());
        isChatOpen = true;

        Window chatWindow = new Window("Chat", GameAssetManager.SKIN);
        chatWindow.setSize(800, 600);
        chatWindow.setMovable(true);

        Table contentTable = new Table();
        contentTable.top().pad(10);

        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isChatOpen = false;
                Gdx.input.setInputProcessor(null);
                chatStage.dispose();
                chatStage = null;
            }
        });
        Table playerButtonsTable = new Table();
        final Table[] messagesTable = {new Table()};
        messagesTable[0].top().defaults().pad(5);
        final boolean[] p = {false};
        TextButton publicy = new TextButton("Public", GameAssetManager.LABI_SKIN);
        publicy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                p[0] =true;
                messagesTable[0].clear();

                for (Payam mess : App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPublicChat()) {
                    messagesTable[0].add(new Label(mess.getMatn(), GameAssetManager.FRIEND_SKIN)).left().row();
                }
                messagesTable[0].invalidate();
                messagesTable[0].layout();
            }
        });
        contentTable.add(publicy).row();
        //Player[] playerNames = game.getPlayers().toArray(new Player[0]);
        //final String[] selectedPlayer = {playerNames[0].getUser().getUsername()};

        Player player1=game.getCurrentPlayer();
        final String[] receiver = {null};
        for (int i = 0; i < 4; i++) {
            Player player = game.getPlayers().get(i);
            System.out.println(player.getUser().getUsername());
            TextButton btn = new TextButton(player.getUser().getUsername(), GameAssetManager.LABI_SKIN);
            int finalI = i;
            btn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    //selectedPlayer[0] = player;
                    p[0]=false;
                    receiver[0] = player.getUser().getUsername();
                    messagesTable[0].clear();

                    for (String mess : player1.getPrivateChats().get(finalI)) {
                        messagesTable[0].add(new Label(mess, GameAssetManager.FRIEND_SKIN)).left().row();
                    }
                    messagesTable[0].invalidate();
                    messagesTable[0].layout();
                }
            });
            playerButtonsTable.add(btn).pad(5);
        }
        contentTable.add(playerButtonsTable).row();
        contentTable.add(messagesTable[0]).row();
       // Player player= App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        ScrollPane scrollPane = new ScrollPane(contentTable, GameAssetManager.SKIN);
        scrollPane.setFadeScrollBars(false);

        TextField inputField = new TextField("", GameAssetManager.SKIN);
        TextButton sendButton = new TextButton("Send", GameAssetManager.SKIN);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String msg = inputField.getText();
                if (!msg.isEmpty()) {
                    if (!p[0]) controller.sendPrivateMessage(msg, receiver[0]);
                    else controller.sendPublicMessage(msg);
                    messagesTable[0].add(new Label( msg, GameAssetManager.FRIEND_SKIN)).right().row();
                    inputField.setText("");
                }
            }
        });

        Table bottomBar = new Table();
        bottomBar.add(inputField).expandX().fillX().pad(5);
        bottomBar.add(sendButton).pad(5);
        chatWindow.add(scrollPane).expand().fill().row();
        chatWindow.add(bottomBar).fillX();
        chatWindow.setPosition(
            (chatStage.getViewport().getScreenWidth() - chatWindow.getWidth()) / 2f,
            (chatStage.getViewport().getScreenHeight() - chatWindow.getHeight()) / 2f
        );
        Group group = new Group() {
            @Override
            public void act(float delta) {
                super.act(delta);
                exitButton.setPosition(
                    chatWindow.getX() + chatWindow.getWidth() - exitButton.getWidth() / 2f + 16,
                    chatWindow.getY() + chatWindow.getHeight() - exitButton.getHeight() / 2f
                );
            }
        };
        group.addActor(chatWindow);
        group.addActor(exitButton);

        chatStage.addActor(group);
        isChatOpen = true;
        Gdx.input.setInputProcessor(chatStage);
    }
}
