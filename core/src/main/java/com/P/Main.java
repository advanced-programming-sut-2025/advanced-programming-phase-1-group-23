package com.P;

import com.P.Client.controller.StartController;
import com.P.Client.model.GameAssetManager;
import com.P.Client.view.StartView;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.P.Server.model.Repo.Connection;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    public static ShaderProgram grayscaleShader;
    public static boolean grayscaleEnabled = false;
    private static String currentCursorPath = "";
    public static final int TILE_SIZE = 100;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        ShaderProgram.pedantic = false;
        setCustomCursor("C.png");
        Connection.printDatabaseName();


        getMain().setScreen(new StartView(new StartController(), GameAssetManager.getGameAssetManager().getSkin()));

    }

    public static void setCustomCursor(String imagePath) {
        if (imagePath.equals(currentCursorPath)) return;

        Pixmap pixmap = new Pixmap(Gdx.files.internal(imagePath));
        Cursor customCursor = Gdx.graphics.newCursor(pixmap, 0, 0);
        Gdx.graphics.setCursor(customCursor);
        pixmap.dispose();
        currentCursorPath = imagePath;
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }
}
