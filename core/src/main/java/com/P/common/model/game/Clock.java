package com.P.common.model.game;

import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.Client.model.GameAssetManager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Clock {
    private final Sprite mainClock = new Sprite(GameAssetManager.CLOCK_MAIN);
    private final Sprite arrow = new Sprite(GameAssetManager.CLOCK_ARROW);
    private Sprite seasonSprite = new Sprite(GameAssetManager.ClOCK_MANNERS[1]);
    private Sprite weather = new Sprite(GameAssetManager.ClOCK_MANNERS[6]);
    private final Game game = App.getLoggedInUser().getCurrentGame();


    public Sprite updateBatch(SpriteBatch batch) {
        OrthographicCamera camera = GameModel.getCamera();
        float width = 366f;
        float height = 300f;

        drawClock(batch, camera, width, height);
        drawArrow(batch, camera, width, height);
        drawDateTime(batch, width, height);
        drawWeatherAndSeason(batch, width, height);
        drawMoney(batch, width, height);

        return mainClock;
    }

    private void drawClock(SpriteBatch batch, OrthographicCamera camera, float width, float height) {
        mainClock.setSize(width, height);
        mainClock.setPosition(
            camera.position.x + camera.viewportWidth / 2f - width,
            camera.position.y + camera.viewportHeight / 2f - height
        );
        mainClock.draw(batch);
    }

    private void drawArrow(SpriteBatch batch, OrthographicCamera camera, float width, float height) {
        arrow.setSize(width * 0.1f, height * 0.28f);

        int hour = game.getDate().toLocalTime().getHour();
        int minute = game.getDate().toLocalTime().getMinute();

        float rotation = 180f - (180f / 60f / 13f) * ((hour - 9) * 60 + minute);
        arrow.setRotation(rotation);

        float angleRad = (float) Math.toRadians(rotation);
        float arrowX = mainClock.getX() + width * 0.3082f - arrow.getWidth() / 2f * (float) Math.cos(angleRad);
        float arrowY = mainClock.getY() + height / 2f + arrow.getHeight() / 2f - arrow.getWidth() / 2f * (float) Math.sin(angleRad);
        arrow.setPosition(arrowX, arrowY);
        arrow.draw(batch);
    }

    private void drawDateTime(SpriteBatch batch, float width, float height) {
        int hour = game.getDate().toLocalTime().getHour();
        int minute = game.getDate().toLocalTime().getMinute();
        String day = game.getDate().toLocalDate().getDayOfWeek().toString();
        int dayOfMonth = game.getDate().toLocalDate().getDayOfMonth();

        GameAssetManager.MAIN_FONT.draw(batch, day + " " + dayOfMonth,
            mainClock.getX() + width * 0.43f, mainClock.getY() + height * 0.90f);

        String period = hour > 11 ? "p.m." : "a.m.";
        String timeStr = (hour % 12) + ":" + minute + " " + period;
        GameAssetManager.MAIN_FONT.draw(batch, timeStr,
            mainClock.getX() + width * 0.50f, mainClock.getY() + height * 0.50f);
    }

    private void drawWeatherAndSeason(SpriteBatch batch, float width, float height) {
        float weatherWidth = width * 0.18f;
        float weatherHeight = height * 0.20f;

        weather.setSize(weatherWidth, weatherHeight);
        weather.setPosition(mainClock.getX() + 0.405f * width, mainClock.getY() + 0.55f * height);
        weather.draw(batch);

        seasonSprite.setSize(weatherWidth, weatherHeight);
        seasonSprite.setPosition(weather.getX() + 0.33f * width, weather.getY());
        seasonSprite.draw(batch);
    }

    private void drawMoney(SpriteBatch batch, float width, float height) {
        int money = game.getCurrentPlayer().getMoney();
        for (int i = 0; money > 0; money /= 10, i++) {
            int digit = money % 10;
            float x = mainClock.getX() + width * (0.826f - 0.082f * i);
            float y = mainClock.getY() + height * 0.17f;
            GameAssetManager.MAIN_FONT.draw(batch, String.valueOf(digit), x, y);
        }
    }



    public void setWeatherSprite() {
        String weather2 = game.getWeatherToday().string();
        switch (weather2) {
            case "Rain" -> this.weather = new Sprite(GameAssetManager.ClOCK_MANNERS[6]);
            case "Snow" -> this.weather = new Sprite(GameAssetManager.ClOCK_MANNERS[9]);
            case "Storm" -> this.weather = new Sprite(GameAssetManager.ClOCK_MANNERS[11]);
            case "Sunny" -> this.weather = new Sprite(GameAssetManager.ClOCK_MANNERS[7]);
        }
    }

    public void setSeasonSprite() {
        String season = game.getSeason().toString();
        switch (season) {
            case "Spring" -> this.seasonSprite = new Sprite(GameAssetManager.ClOCK_MANNERS[0]);
            case "Summer" -> this.seasonSprite = new Sprite(GameAssetManager.ClOCK_MANNERS[1]);
            case "Autumn" -> this.seasonSprite = new Sprite(GameAssetManager.ClOCK_MANNERS[2]);
            case "Winter" -> this.seasonSprite = new Sprite(GameAssetManager.ClOCK_MANNERS[4]);
        }
    }
}
