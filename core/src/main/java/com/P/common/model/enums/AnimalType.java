package com.P.common.model.enums;

import com.P.Client.model.GameAssetManager;
import com.badlogic.gdx.graphics.Texture;

public enum AnimalType {
    Hen("Hen", 800, GameAssetManager.CHICKEN),
    Duck("Duck", 1200,GameAssetManager.DUCK),
    Rabbit("Rabbit", 8000,GameAssetManager.RABBIT),
    Dinosaur("Dinosaur", 14000,GameAssetManager.DINOSAUR),
    Cow("Cow", 1500,GameAssetManager.COW),
    Goat("Goat", 4000,GameAssetManager.GOAT),
    Sheep("Sheep", 8000,GameAssetManager.SHEEP),
    Pig("Pig", 16000,GameAssetManager.PIG);

    private final String kind;
    private final int price;
    private final Texture assetManager;

    AnimalType(String kind, int price, Texture assetManager) {
        this.kind = kind;
        this.price = price;
        this.assetManager = assetManager;
    }

    public String getKind() {
        return kind;
    }

    public int getPrice() {
        return price;
    }

    public Texture getAssetManager() {return assetManager;}
}
