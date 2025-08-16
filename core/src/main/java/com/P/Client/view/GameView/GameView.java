package com.P.Client.view.GameView;

import com.P.Client.app.ClientApp;
import com.P.Client.controller.*;
import com.P.Client.model.Command;
//import com.P.Client.model.HitEffect;
import com.P.Client.view.MainView;
import com.P.Main;
import com.P.common.model.Basics.User;
import com.P.common.model.Maps.Farm;
import com.P.common.model.Maps.Position;
import com.P.common.model.NPC.NPC;
import com.P.common.model.Naturals.Crop;
import com.P.common.model.Objects.Animal;
import com.P.common.model.Resualt;
import com.P.common.model.enums.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;

import com.P.common.model.Animals.Fish;
import com.P.common.model.Animals.FishGame;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.Client.model.GameAssetManager;
import com.P.Client.model.Pair;
import com.P.common.model.Objects.Tool;
import com.P.common.model.game.GameModel;
import com.P.common.model.game.VillageModel;
import com.P.common.model.item.CarrotStages;
import com.P.common.model.item.GrowingCrop;
import com.P.common.model.item.ItemDescriptionId;
import com.P.common.model.item.TileDescriptionId;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.P.common.model.*;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.P.common.model.Naturals.Objectss;
import com.P.common.model.Naturals.Tree;
import com.badlogic.gdx.*; // کتابخانه اصلی LibGDX
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

// =======================
// Java Desktop (برای JFileChooser)
// =======================
import java.io.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFileChooser;
import java.io.File;
import java.net.Socket;
import java.util.Locale;

import javax.sound.midi.Track;
import java.awt.*;
import java.util.*;

//import static com.P.Client.controller.InventoryFunctionsController.Effects;
import static com.P.Client.controller.InventoryFunctionsController.useTool;
import static javax.swing.JColorChooser.showDialog;

public class GameView {
    private GameModel game;
    private VillageModel village;
    private SpriteBatch batch;
    private TextureRegion[][] tileTextures;
    private Map<String, TextureRegion> textures;
    private BitmapFont smallFont;
    private GlyphLayout layout = new GlyphLayout();
    public static HashMap<Ingredients, Pair<Float, Float>> builds = new HashMap<>();
    public static HashMap<Objectss, Pair<Float, Float>> planting = new HashMap<>();

    public static ArrayList<ClickEffect> effects = new ArrayList<>();

    // Charecters
    private TextureAtlas playerAtlas;
    private final ArrayList<Animation<TextureRegion>> playerAnimations = new ArrayList<>();
    private TextureAtlas haleyAtlas;
    private final ArrayList<Animation<TextureRegion>> haleyAnimations = new ArrayList<>();
    private TextureAtlas leahAtlas;
    private final ArrayList<Animation<TextureRegion>> leahAnimations = new ArrayList<>();
    private TextureAtlas ElliottAtlas;
    private final ArrayList<Animation<TextureRegion>> elliottAnimations = new ArrayList<>();
    private TextureAtlas sebastianAtlas;
    private final ArrayList<Animation<TextureRegion>> sebastianAnimations = new ArrayList<>();
    private TextureAtlas shaneAtlas;
    private final ArrayList<Animation<TextureRegion>> shaneAnimations = new ArrayList<>();
    private TextureAtlas robinAtlas;
    private final ArrayList<Animation<TextureRegion>> robinAnimations = new ArrayList<>();

    // NPC
    private TextureAtlas AbigailAtlas;
    private final ArrayList<Animation<TextureRegion>> AbigailAnimation = new ArrayList<>();
    private TextureAtlas HarveyAtlas;
    private final ArrayList<Animation<TextureRegion>> HarveyAnimations = new ArrayList<>();
    private TextureAtlas LeaAtlas;
    private final ArrayList<Animation<TextureRegion>> LeaAnimations = new ArrayList<>();
    private TextureAtlas RobinAtlas;
    private final ArrayList<Animation<TextureRegion>> RobinAnimations = new ArrayList<>();
    private TextureAtlas SebastianAtlas;
    private final ArrayList<Animation<TextureRegion>> SebastianAnimations = new ArrayList<>();

    private float stateTime = 0f;
    private int moveDirection = 0;
    private Texture pixel; // Add this
    private float faintStateTime = 0f;
    private boolean isVillage = false;
    private boolean isGreenHouseOkey = false;
    private Stage stage;

    // Fishin
    private ArrayList<Fish> fishList;
    private Rectangle waterBounds;
    Texture fishMixedTexture = new Texture(Gdx.files.internal("game/animals/fish/mixed.png"));
    Texture fishSmoothTexture = new Texture(Gdx.files.internal("game/animals/fish/smooth.png"));
    Texture fishSinkerTexture = new Texture(Gdx.files.internal("game/animals/fish/sinker.png"));
    Texture fishFloaterTexture = new Texture(Gdx.files.internal("game/animals/fish/floater.png"));
    Texture fishDartTexture = new Texture(Gdx.files.internal("game/animals/fish/dart.png"));


    private Texture scarecrowTexture;
    private Texture scarecrowInfoTexture;
    private boolean isScarecrowInfoVisible = false;


    private final int scarecrowTileX = 2;
    private final int scarecrowTileY = 22;

    private Texture iconTexture;
    private Texture giftTexture;
    private Texture achiveTexture;
    private boolean achivement = false;

    // NPC StartTime
    public static boolean sebastianStart = false;
    public static boolean abigailStart = false;
    public static boolean harveyStart = false;
    public static boolean leaStart = false;
    public static boolean robinStart = false;

    // NPC FinishTime
    public static boolean sebastianEnd = false;
    public static boolean abigailEnd = false;
    public static boolean harveyEnd = false;
    public static boolean leaEnd = false;
    public static boolean robinEnd = false;

    private Music music;
    private boolean isPlaying = false;

    private boolean isHug = false;
    private boolean isWedding = false;

    private void loadFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/stardew-valley.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        smallFont = generator.generateFont(parameter);
        generator.dispose();
    }

    public GameView(GameModel game) {
        this.game = game;
        batch = new SpriteBatch();
        loadTextures();
        loadFont();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    public GameView(VillageModel village) {
        this.village = village;
        batch = new SpriteBatch();
        loadTextures();
        loadFont();
        isVillage = true;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    private void loadTextures() {
        iconTexture = new Texture(Gdx.files.internal("game/NPC.png"));
        giftTexture = new Texture(Gdx.files.internal("game/npcGift.png"));
        achiveTexture = new Texture(Gdx.files.internal("game/NPCachivement.png"));
        textures = new HashMap<>();
        music = Gdx.audio.newMusic(Gdx.files.internal("audio/mysong.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);

        for (TileDescriptionId id : TileDescriptionId.values()) {
            String path = id.getIconPath();
            textures.put(id.name(), new TextureRegion(new Texture(Gdx.files.internal(path))));
        }
        for (ItemDescriptionId id : ItemDescriptionId.values()) {
            String path = id.getIconPath();
            textures.put(id.name(), new TextureRegion(new Texture(Gdx.files.internal(path))));
        }
        for (CarrotStages cs : CarrotStages.values()) {
            String path = cs.getIconPath();
            textures.put(cs.name(), new TextureRegion(new Texture(Gdx.files.internal(path))));
        }

        // Charecters
        playerAtlas = new TextureAtlas(Gdx.files.internal("game/character/sprites_player.atlas"));
        haleyAtlas = new TextureAtlas(Gdx.files.internal("game/character/Haley/sprites_Haley.atlas"));
        leahAtlas = new TextureAtlas(Gdx.files.internal("game/character/Leah/sprites_Leah.atlas"));
        ElliottAtlas = new TextureAtlas(Gdx.files.internal("game/character/Elliott/sprites_Elliott.atlas"));
        sebastianAtlas = new TextureAtlas(Gdx.files.internal("game/character/Sebastian/sprites_Sebastian.atlas"));
        shaneAtlas = new TextureAtlas(Gdx.files.internal("game/character/Shane/sprites_Shane.atlas"));
        robinAtlas = new TextureAtlas(Gdx.files.internal("game/character/Robin/sprites_Robin.atlas"));


        // NPCS
        AbigailAtlas = new TextureAtlas(Gdx.files.internal("game/NPC/sprites_Abigail.atlas"));
        HarveyAtlas = new TextureAtlas(Gdx.files.internal("game/NPC/sprites_Harvey.atlas"));
        LeaAtlas = new TextureAtlas(Gdx.files.internal("game/NPC/sprites_Lea.atlas"));
        RobinAtlas = new TextureAtlas(Gdx.files.internal("game/NPC/sprites_Robin.atlas"));
        SebastianAtlas = new TextureAtlas(Gdx.files.internal("game/NPC/sprites_Sebastian.atlas"));

        // NPCS
        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(AbigailAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(AbigailAtlas.findRegion(region));
                }
            }
            AbigailAnimation.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(HarveyAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(HarveyAtlas.findRegion(region));
                }
            }
            HarveyAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(LeaAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(LeaAtlas.findRegion(region));
                }
            }
            LeaAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(RobinAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(RobinAtlas.findRegion(region));
                }
            }
            RobinAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(SebastianAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(SebastianAtlas.findRegion(region));
                }
            }
            SebastianAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }


        // Charecters
        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(haleyAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(haleyAtlas.findRegion(region));
                }
            }
            haleyAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(playerAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(playerAtlas.findRegion(region));
                }
            }
            playerAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(leahAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(leahAtlas.findRegion(region));
                }
            }
            leahAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(ElliottAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(ElliottAtlas.findRegion(region));
                }
            }
            elliottAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(sebastianAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(sebastianAtlas.findRegion(region));
                }
            }
            sebastianAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(shaneAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(shaneAtlas.findRegion(region));
                }
            }
            shaneAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(robinAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(robinAtlas.findRegion(region));
                }
            }
            robinAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        // HOUSE
        textures.put("SPRING_HOUSE", new TextureRegion(new Texture(Gdx.files.internal("game\\house\\house\\1.png"))));
        textures.put("SUMMER_HOUSE", new TextureRegion(new Texture(Gdx.files.internal("game\\house\\house\\2.png"))));
        textures.put("FALL_HOUSE", new TextureRegion(new Texture(Gdx.files.internal("game\\house\\house\\3.png"))));
        textures.put("WINTER_HOUSE", new TextureRegion(new Texture(Gdx.files.internal("game\\house\\house\\4.png"))));

        // TREE1
        textures.put("TREE1_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/tree1.png"))));
        textures.put("TREE1_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/tree1.png"))));
        textures.put("TREE1_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/tree1.png"))));
        textures.put("TREE1_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/tree1.png"))));

        // TREE2
        textures.put("TREE2_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/tree2.png"))));
        textures.put("TREE2_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/tree2.png"))));
        textures.put("TREE2_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/tree2.png"))));
        textures.put("TREE2_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/tree2.png"))));

        // GRASS
        textures.put("GRASS_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/grass.png"))));
        textures.put("GRASS_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/grass.png"))));
        textures.put("GRASS_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/grass.png"))));
        textures.put("GRASS_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/grass.png"))));

        // WOOD
        textures.put("WOOD_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/wood.png"))));
        textures.put("WOOD_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/wood.png"))));
        textures.put("WOOD_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/wood.png"))));
        textures.put("WOOD_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/wood.png"))));

        // STONE
        textures.put("STONE_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/stone.png"))));
        textures.put("STONE_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/stone.png"))));
        textures.put("STONE_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/stone.png"))));
        textures.put("STONE_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/stone.png"))));

        // VILLAGE
        textures.put("SMITH", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/blackSmith.png"))));
        textures.put("RANCH", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/marnieRanch.png"))));
        textures.put("SALOON", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/stardropSaloon.png"))));
        textures.put("CARPENTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/carpenterShop.png"))));
        textures.put("JOJA", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/jojaMart.png"))));
        textures.put("STORE", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/village/pierreGeneralStore.png"))));

        // GREENHOUSE
        textures.put("BROKEN_GREENHOUSE", new TextureRegion(new Texture(Gdx.files.internal("game/greenhouse/Broken_Greenhouse.png"))));
        textures.put("SCARECROW", new TextureRegion(new Texture(Gdx.files.internal("game/greenhouse/question.png"))));
        textures.put("SCARECROW_INFO", new TextureRegion(new Texture(Gdx.files.internal("game/greenhouse/command.png"))));

        textures.put("Huge", new TextureRegion(new Texture(Gdx.files.internal("game/Large_Milk_RU.png"))));
        textures.put("F_SPRING", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/spring/f.png"))));
        textures.put("F_SUMMER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/summer/f.png"))));
        textures.put("F_FALL", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/fall/f.png"))));
        textures.put("F_WINTER", new TextureRegion(new Texture(Gdx.files.internal("game/tiles/winter/f.png"))));
        // FISHING
        waterBounds = new Rectangle(10 * Main.TILE_SIZE,
            10 * Main.TILE_SIZE,
            3 * Main.TILE_SIZE,
            3 * Main.TILE_SIZE);

        initFishes();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fill();
        pixel = new Texture(pixmap);
        pixmap.dispose();
    }

    Array<Fish> fishes = new Array<>();

    public void initFishes() {
        for (int i = 0; i < 4; i++) {
            float x = MathUtils.random(waterBounds.x, waterBounds.x + waterBounds.width);
            float y = MathUtils.random(waterBounds.y, waterBounds.y + waterBounds.height);

            FishGame type = FishGame.values()[i % 5];
            Texture texture;
            switch (type) {
                case MIXED:
                    texture = fishMixedTexture;
                    break;
                case SMOOTH:
                    texture = fishSmoothTexture;
                    break;
                case SINKER:
                    texture = fishSinkerTexture;
                    break;
                case FLOATER:
                    texture = fishFloaterTexture;
                    break;
                case DART:
                    texture = fishDartTexture;
                    break;
                default:
                    texture = fishMixedTexture;
            }

            fishes.add(new Fish(texture, x, y, type, texture));
        }
    }

    public void render(float delta) {
        // FarmingController.GoodNightFarm();
        batch.setProjectionMatrix(game.getCamera().combined);
        handleAltKey();
        batch.begin();

        if (!isVillage)
            if (game.getPlayer().getMoney() >= 500)
                isGreenHouseOkey = true;


        Game exctGame = App.loggedInUser.getCurrentGame();
        if (exctGame.getDate().getHour() == 9) {
            abigailStart = true;
            leaStart = true;
            harveyStart = true;
            sebastianStart = true;
        }
        if (exctGame.getDate().getHour() == 12)
            robinStart = true;

        if (exctGame.getDate().getHour() == 13)
            robinEnd = true;

        if (exctGame.getDate().getHour() == 17)
            leaEnd = true;

        if (exctGame.getDate().getHour() == 23)
            harveyEnd = true;

        if (exctGame.getDate().getHour() == 20)
            abigailEnd = true;

        if (exctGame.getDate().getHour() == 16)
            sebastianEnd = true;

        if (!isVillage) {
            renderTiles();
            renderHouse();
            FarmingRender(game);
            BuildingRender(game);
            if (!isGreenHouseOkey) {
                renderBrokenGreenHouse();
            }
            renderScarecrow();
            renderScarecrowInfo();
            renderFish();
            if (cheatMenuOpen && cheatMenuStage != null) {
                cheatMenuStage.act(Gdx.graphics.getDeltaTime());
                Gdx.input.setInputProcessor(cheatMenuStage);
                cheatMenuStage.draw();
            }
        }

        for (int i = effects.size() - 1; i >= 0; i--) {
            effects.get(i).update(delta);
            if (effects.get(i).isFinished()) {
                effects.remove(i);
            }
        }
        for (ClickEffect effect : effects) {
            effect.draw(batch);
        }


        if (game != null && game.isFlashActive()) {
            batch.end();

            batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            batch.begin();

            batch.setColor(1f, 1f, 1f, game.getFlashAlpha());
            batch.draw(pixel, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.setColor(1f, 1f, 1f, 1f);

            game.setFlashAlpha(game.getFlashAlpha() - Gdx.graphics.getDeltaTime() * 2f);
            if (game.getFlashAlpha() <= 0f) {
                game.setFlashAlpha(0f);
                game.setFlashActive(false);
            }

            batch.end();


            batch.setProjectionMatrix(game.getCamera().combined);
            batch.begin();
        }

        if (isVillage) {
            renderVillegeTiles();
            smithRender();
            ranchRender();
            saloonRender();
            carpenterRender();
            jojaRender();
            storeRender();
            //BuildingRender();

            if (abigailStart)
                renderAbigail();
            if (harveyStart)
                renderHarvey();
            if (leaStart)
                renderLea();
            if (sebastianStart)
                renderSebastian();
            if (robinStart)
                renderRobin();

        }

        renderPlayer();
        renderOtherPlayers();


        if (App.loggedInUser.getCurrentGame().getDate().getDayOfMonth() >= 15) {
            Hugeproducts();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.EQUALS)) {
            crow = !crow;
        }
        if (crow) {
            batch.draw(GameAssetManager.CROW, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        if (talkCheatMenuOpen && talkCheatMenuStage != null) {
            talkCheatMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(talkCheatMenuStage);
            talkCheatMenuStage.draw();
        }
        if (giftCheatMenuOpen && giftCheatMenuStage != null) {
            giftCheatMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(giftCheatMenuStage);
            giftCheatMenuStage.draw();
        }
        if (radioMenuOpen && radioMenuStage != null) {
            radioMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(radioMenuStage);
            radioMenuStage.draw();
        }
        if (sortMenuOpen && sortMenuStage != null) {
            sortMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(sortMenuStage);
            sortMenuStage.draw();
        }
        if (reactionMenuOpen && reactionMenuStage != null) {
            reactionMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(reactionMenuStage);
            reactionMenuStage.draw();
        }
        batch.end();

    }

    private boolean crow = false;

    private void renderFish() {
        for (Fish fish : fishes) {
            float camX = game.getCamera().position.x;
            float camY = game.getCamera().position.y;
            float viewportWidth = game.getCamera().viewportWidth;
            float viewportHeight = game.getCamera().viewportHeight;

            float cameraLeft = camX - viewportWidth / 2;
            float cameraBottom = camY - viewportHeight / 2;

            float drawX = fish.getPosition().y - cameraLeft;
            float drawY = fish.getPosition().x - cameraBottom;
            fish.update(Gdx.graphics.getDeltaTime(), waterBounds);
            batch.draw(fish.getFishTexture(), drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE);
        }
    }

    private void handleAltKey() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
            fishing = !fishing;
            isScarecrowInfoVisible = !isScarecrowInfoVisible;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F12)) {
            isGreenHouseOkey = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            if (isPlaying) {
                music.pause();
                isPlaying = false;
            } else {
                myMusic.pause();
                music.play();
                isPlaying = true;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F9)) {
            setRadio();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            getRadio();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.F2)){
            isWedding = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            isHug = true;
        }

    }

    public void setRadio(){
        try {
            AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
            SourceDataLine speakers = AudioSystem.getSourceDataLine(format);

            try {
                speakers.open(format);
                speakers.start();

                byte[] buffer = new byte[4096];
                int bytesRead;


                while ((bytesRead = ClientApp.getServerConnection()
                    .getSocket().getInputStream().read(buffer)) != -1) {
                    speakers.write(buffer, 0, bytesRead);
                }

                speakers.drain();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                speakers.close();
            }

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void getRadio(){
        new Thread(() -> {
            try (InputStream fis = Gdx.files.internal(currentPath).read()) {
                Socket socket = ClientApp.getServerConnection().getSocket();
                if (socket == null || socket.isClosed() || !socket.isConnected()) return;

                try (OutputStream out = socket.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                    out.flush();
                    System.out.println("ll");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private boolean fishing = false;

    private void renderScarecrow() {
        float camX = game.getCamera().position.x;
        float camY = game.getCamera().position.y;
        float viewportWidth = game.getCamera().viewportWidth;
        float viewportHeight = game.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = scarecrowTileX * Main.TILE_SIZE - cameraLeft;
        float drawY = scarecrowTileY * Main.TILE_SIZE - cameraBottom;
        TextureRegion houseTexture = textures.get("SCARECROW");

        batch.draw(houseTexture, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);

        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.getCamera().unproject(mousePos);

            if (mousePos.x >= scarecrowTileX && mousePos.x <= scarecrowTileX + Main.TILE_SIZE &&
                mousePos.y >= scarecrowTileY && mousePos.y <= scarecrowTileY + Main.TILE_SIZE * 2) {

                isScarecrowInfoVisible = !isScarecrowInfoVisible;
            }
        }

    }

    private void renderScarecrowInfo() {
        if (!isScarecrowInfoVisible) return;
        float camX = game.getCamera().position.x;
        float camY = game.getCamera().position.y;
        float viewportWidth = game.getCamera().viewportWidth;
        float viewportHeight = game.getCamera().viewportHeight;
        int x = 2; // جای دلخواه روی صفحه
        int y = 24;
        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = x * Main.TILE_SIZE - cameraLeft;
        float drawY = y * Main.TILE_SIZE - cameraBottom;

        TextureRegion houseTexture = textures.get("SCARECROW_INFO");
        batch.draw(houseTexture, drawX, drawY, 300, 200); // اندازه دلخواه
    }

    private void renderVillegeTiles() {
        TileDescriptionId[][] tiles = village.getTiles();

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        int tileSize = Main.TILE_SIZE;


        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        int startX = Math.max(0, (int) (cameraLeft / tileSize) - 300);
        int startY = Math.max(0, (int) (cameraBottom / tileSize) - 300);
        int endX = Math.min(tiles.length, (int) ((camX + viewportWidth / 2) / tileSize) + 300);
        int endY = Math.min(tiles[0].length, (int) ((camY + viewportHeight / 2) / tileSize) + 300);

        // Render base tiles
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                TileDescriptionId id = tiles[x][y];
                if (tiles[x][y] == TileDescriptionId.WATER) {
                    System.out.print(x + " " + y);
                }
                if (id != null) {
                    float drawX = x * tileSize - cameraLeft;
                    float drawY = y * tileSize - cameraBottom;

                    TextureRegion texture = textures.get(id.name());
                    if (texture != null) {
                        batch.draw(texture, drawX, drawY, tileSize, tileSize);
                    }
                }
            }
        }
        // مختصات و ابعاد آیکون
        float iconX = 10;
        float iconY = Gdx.graphics.getHeight() - 200;
        float iconWidth = GameAssetManager.FRIENDSHIP_ICON.getWidth();
        float iconHeight = GameAssetManager.FRIENDSHIP_ICON.getHeight();

        Matrix4 originalProjection = batch.getProjectionMatrix().cpy();
        batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        batch.draw(GameAssetManager.FRIENDSHIP_ICON, iconX, iconY);
        batch.setProjectionMatrix(originalProjection);

        if (Gdx.input.justTouched()) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.input.getY();

            mouseY = Gdx.graphics.getHeight() - mouseY;

            if (mouseX >= iconX && mouseX <= iconX + iconWidth &&
                mouseY >= iconY && mouseY <= iconY + iconHeight) {
                friendshipWindow();
            }
        }
        if (friendshipMenuOpen && friendshipMenuStage != null) {
            friendshipMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(friendshipMenuStage);
            friendshipMenuStage.draw();
        }
        if (giftMenuOpen && giftMenuStage != null) {
            giftMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(giftMenuStage);
            giftMenuStage.draw();
        }
        if (historyGiftMenuOpen && historyGiftMenuStage != null) {
            historyGiftMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(historyGiftMenuStage);
            historyGiftMenuStage.draw();
        }

        if (talkMenuOpen && talkMenuStage != null) {
            talkMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(talkMenuStage);
            talkMenuStage.draw();
        }

        Game vGame = App.loggedInUser.getCurrentGame();
        ArrayList<Player> players = vGame.getPlayers();
        for (int i = 0; i < 4; i++) {
            if (i != ClientApp.getTurnNumber()) {
                Player player = players.get(i);
                if (player.isInVillage()) {
                    Texture avatar = new Texture(player.getUser().getAvatar().getIconPath());
                    batch.draw(avatar, player.getPlayerPosition().first * Main.TILE_SIZE, player.getPlayerPosition().second * Main.TILE_SIZE, 60, 120);
                }
            }
        }

    }

    private Stage friendshipMenuStage = null;
    private boolean friendshipMenuOpen = false;
    final Resualt[] response = {null};

    private void friendshipWindow() {
        Stage friendStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(friendStage);

        // ساخت پنجره
        com.badlogic.gdx.scenes.scene2d.ui.Window window =
            new com.badlogic.gdx.scenes.scene2d.ui.Window("Friend Menu", GameAssetManager.FRIEND_SKIN);
        window.setSize(1600, 600);
        window.setMovable(false);

        // جدول محتوا
        Table table = new Table();
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();

        int index = ClientApp.getTurnNumber();

        for (int i = 0; i < 4; i++) {
            User user = App.loggedInUser.getCurrentGame().getPlayers().get(i).getUser();
            String name = App.loggedInUser.getCurrentGame().getPlayers().get(i).getUser().getNickname();
            int friendship = App.loggedInUser.getCurrentGame().getFriendMatrix().get(index).get(i).getFriendShipLevel();


            Label label = new Label(friendship + " " + name, GameAssetManager.FRIEND_SKIN);
            label.setAlignment(Align.left);
            label.setColor(Color.DARK_GRAY);


            TextButton actionButton = new TextButton("GIFT", GameAssetManager.FRIEND_SKIN);
            int finalI = i;
            actionButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    giftMenuWindow(user.getUsername());
                }
            });

            TextButton merraigeButton = new TextButton("MERRY", GameAssetManager.FRIEND_SKIN);
            merraigeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    response[0] = FriendshipController.marriageRequest(user.getUsername());
                    friendStage.clear();
                    friendshipWindow();
                }
            });

            TextButton flowerButton = new TextButton("FLOWER", GameAssetManager.FRIEND_SKIN);
            flowerButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    response[0] = FriendshipController.givingFlowers(user.getUsername());
                    friendStage.clear();
                    friendshipWindow();
                }
            });

            TextButton talkButton = new TextButton("HUG", GameAssetManager.FRIEND_SKIN);
            talkButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    talkWindow(user.getUsername());
                }
            });

            label.setFontScale(3f);
            table.add(label).width(400).left();
            table.add(actionButton).width(220).left();
            table.add(talkButton).width(220).left();
            table.add(merraigeButton).width(230).left();
            table.add(flowerButton).width(250).left();
            table.row();
        }


        window.add(table).expand().fill();

        // دکمه خروج
        ImageButton exitButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                friendStage.clear();
                friendshipMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        // موقعیت وسط صفحه
        window.setPosition(
            (friendStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
            (friendStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
        );
        exitButton.setPosition(
            window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
            window.getY() + window.getHeight() - exitButton.getHeight() / 2f
        );

        friendStage.addActor(window);
        friendStage.addActor(exitButton);

        this.friendshipMenuStage = friendStage;
        this.friendshipMenuOpen = true;

        if (response[0] != null) {
            Dialog dialog = new Dialog("", GameAssetManager.FRIEND_SKIN);
            dialog.text(response[0].getAnswer());
            dialog.button("OK");
            dialog.show(friendStage);
        }
    }

    private Stage giftMenuStage = null;
    private boolean giftMenuOpen = false;
    final Resualt[] responseGift = {null};

    private void giftMenuWindow(String username) {
        Stage giftStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(giftStage);

        // ساخت پنجره
        com.badlogic.gdx.scenes.scene2d.ui.Window window =
            new com.badlogic.gdx.scenes.scene2d.ui.Window("Gift Menu", GameAssetManager.FRIEND_SKIN);
        window.setSize(1000, 600);
        window.setMovable(false);

        Table table = new Table();
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();

        TextField giftField = new TextField("Item For Gist?", GameAssetManager.FRIEND_SKIN);
        table.add(giftField).width(520).left();
        table.row();

        TextButton sendGift = new TextButton("Sent a gift", GameAssetManager.FRIEND_SKIN);
        sendGift.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                responseGift[0] = FriendshipController.gift(username, giftField.getText());
                giftStage.clear();
                giftMenuWindow(username);
            }
        });

        table.add(sendGift).width(520).left();
        table.row();


        TextButton historyGift = new TextButton("History of S & R Gifts", GameAssetManager.FRIEND_SKIN);
        historyGift.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                historyGiftMenuWindow();
            }
        });

        table.add(historyGift).width(560).left();
        table.row();

        window.add(table).expand().fill();

        ImageButton exitButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                giftStage.clear();
                giftMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        // موقعیت وسط صفحه
        window.setPosition(
            (giftStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
            (giftStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
        );
        exitButton.setPosition(
            window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
            window.getY() + window.getHeight() - exitButton.getHeight() / 2f
        );

        giftStage.addActor(window);
        giftStage.addActor(exitButton);

        this.giftMenuStage = giftStage;
        this.giftMenuOpen = true;

        if (responseGift[0] != null) {
            Dialog dialog = new Dialog("", GameAssetManager.FRIEND_SKIN);
            dialog.text(responseGift[0].getAnswer());
            dialog.button("OK");
            dialog.show(giftStage);
        }
    }

    private Stage talkMenuStage = null;
    private boolean talkMenuOpen = false;
    final Resualt[] responseTalk = {null};

    private void talkWindow(String username) {
        Stage cheatStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(cheatStage);

        com.badlogic.gdx.scenes.scene2d.ui.Window window =
            new com.badlogic.gdx.scenes.scene2d.ui.Window("Talk", GameAssetManager.FRIEND_SKIN);
        window.setSize(700, 400);
        window.setMovable(false);

        Table table = new Table();
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();

        TextField setEnergy = new TextField("WHAT YOU WANT TO SAY?", GameAssetManager.SKIN);
        table.add(setEnergy).width(520).left();
        table.row();

        TextButton set = new TextButton("TALK", GameAssetManager.SKIN);
        set.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                responseTalk[0] = FriendshipController.talk(username, setEnergy.getText());
                cheatStage.clear();
                talkWindow(username);
            }
        });

        table.add(set).width(520).left();
        table.row();


        window.add(table).expand().fill();

        ImageButton exitButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cheatStage.clear();
                talkMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        // موقعیت وسط صفحه
        window.setPosition(
            (cheatStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
            (cheatStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
        );
        exitButton.setPosition(
            window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
            window.getY() + window.getHeight() - exitButton.getHeight() / 2f
        );

        cheatStage.addActor(window);
        cheatStage.addActor(exitButton);

        this.talkMenuStage = cheatStage;
        this.talkMenuOpen = true;

        if (responseTalk[0] != null) {
            Dialog dialog = new Dialog("", GameAssetManager.FRIEND_SKIN);
            dialog.text(responseTalk[0].getAnswer());
            dialog.button("OK");
            dialog.show(cheatStage);
        }
    }

    private Stage historyGiftMenuStage = null;
    private boolean historyGiftMenuOpen = false;

    private void historyGiftMenuWindow() {
        Stage historyStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(historyStage);

        // ساخت پنجره
        com.badlogic.gdx.scenes.scene2d.ui.Window window =
            new com.badlogic.gdx.scenes.scene2d.ui.Window("History of S & R Gifts", GameAssetManager.FRIEND_SKIN);
        window.setSize(1000, 600);
        window.setMovable(false);

        Table table = new Table();
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();

        Label label = new Label(" ", GameAssetManager.FRIEND_SKIN);
        label.setAlignment(Align.left);
        label.setColor(Color.DARK_GRAY);
        label.setText(FriendshipController.showReceivedGifts(App.loggedInUser.getCurrentGame().getCurrentPlayer()).getAnswer());
        label.setFontScale(1.5f);
        table.add(label).width(600).left();
        table.row();

        window.add(table).expand().fill();

        ImageButton exitButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                historyStage.clear();
                historyGiftMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        // موقعیت وسط صفحه
        window.setPosition(
            (historyStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
            (historyStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
        );
        exitButton.setPosition(
            window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
            window.getY() + window.getHeight() - exitButton.getHeight() / 2f
        );

        historyStage.addActor(window);
        historyStage.addActor(exitButton);

        this.historyGiftMenuStage = historyStage;
        this.historyGiftMenuOpen = true;
    }

    private void renderHouse() {
        TextureRegion houseTexture = textures.get("HOUSE");
        Game g = App.loggedInUser.getCurrentGame();

        if (g.getSeason().equals(Season.SPRING)) {
            houseTexture = textures.get("SPRING_HOUSE");
        } else if (g.getSeason().equals(Season.SUMMER)) {
            houseTexture = textures.get("SUMMER_HOUSE");
        } else if (g.getSeason().equals(Season.AUTUMN)) {
            houseTexture = textures.get("FALL_HOUSE");
        } else if (g.getSeason().equals(Season.WINTER)) {
            houseTexture = textures.get("WINTER_HOUSE");
        }

        if (houseTexture != null) {
            int tileSize = Main.TILE_SIZE;

            int houseTileX = 3;
            int houseTileY = 3;

            float camX = game.getCamera().position.x;
            float camY = game.getCamera().position.y;
            float viewportWidth = game.getCamera().viewportWidth;
            float viewportHeight = game.getCamera().viewportHeight;

            float cameraLeft = camX - viewportWidth / 2;
            float cameraBottom = camY - viewportHeight / 2;

            float drawX = houseTileX * tileSize - cameraLeft;
            float drawY = houseTileY * tileSize - cameraBottom;

            batch.draw(houseTexture, drawX, drawY, tileSize * 5, tileSize * 7);
        }
    }

    // GREENHOUSE
    private void renderBrokenGreenHouse() {
        TextureRegion houseTexture = textures.get("BROKEN_GREENHOUSE");

        if (houseTexture != null) {
            int tileSize = Main.TILE_SIZE;

            int houseTileX = 3;
            int houseTileY = 22;

            float camX = game.getCamera().position.x;
            float camY = game.getCamera().position.y;
            float viewportWidth = game.getCamera().viewportWidth;
            float viewportHeight = game.getCamera().viewportHeight;

            float cameraLeft = camX - viewportWidth / 2;
            float cameraBottom = camY - viewportHeight / 2;

            float drawX = houseTileX * tileSize - cameraLeft;
            float drawY = houseTileY * tileSize - cameraBottom;

            batch.draw(houseTexture, drawX, drawY, tileSize * 4, tileSize * 4);
        }

    }

    // VILLAGE
    private void smithRender() {
        TextureRegion houseTexture = textures.get("SMITH");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 16;
        int houseTileY = 16;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 5, tileSize * 4);
        Texture texture = GameAssetManager.trashcan;
        batch.draw(texture, drawX + 10, drawY + 10, tileSize, tileSize);
        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            GameModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX + 10 && mousePos.x <= drawX + 10 + tileSize &&
                mousePos.y >= drawY + 10 && mousePos.y <= drawY + 10 + tileSize) {
                System.out.println("wwwwwww");
                ShoppingController.isBuildingMenuOpen = true;
                ShoppingController.createSellMenu();
            }
        }

    }

    private void ranchRender() {
        TextureRegion houseTexture = textures.get("RANCH");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 9;
        int houseTileY = 9;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 5, tileSize * 4);
        Texture texture = GameAssetManager.trashcan;
        batch.draw(texture, drawX + 10, drawY + 10, tileSize, tileSize);
        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            GameModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX + 10 && mousePos.x <= drawX + 10 + tileSize &&
                mousePos.y >= drawY + 10 && mousePos.y <= drawY + 10 + tileSize) {
                ShoppingController.isBuildingMenuOpen = true;
                ShoppingController.createSellMenu();
            }
        }

    }

    private void saloonRender() {
        TextureRegion houseTexture = textures.get("SALOON");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 9;
        int houseTileY = 9;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 4, tileSize * 5);
        Texture texture = GameAssetManager.trashcan;
        batch.draw(texture, drawX + 10, drawY + 10, tileSize, tileSize);
        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            GameModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX + 10 && mousePos.x <= drawX + 10 + tileSize &&
                mousePos.y >= drawY + 10 && mousePos.y <= drawY + 10 + tileSize) {
                ShoppingController.isBuildingMenuOpen = true;
                ShoppingController.createSellMenu();
            }
        }

    }

    private void carpenterRender() {
        TextureRegion houseTexture = textures.get("CARPENTER");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 25;
        int houseTileY = 25;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 6, tileSize * 7);
        Texture texture = GameAssetManager.trashcan;
        batch.draw(texture, drawX + 10, drawY + 10, tileSize, tileSize);
        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            GameModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX + 10 && mousePos.x <= drawX + 10 + tileSize &&
                mousePos.y >= drawY + 10 && mousePos.y <= drawY + 10 + tileSize) {
                System.out.println("wwwwwww");
                ShoppingController.isBuildingMenuOpen = true;
                ShoppingController.createSellMenu();
            }
        }

    }

    private void jojaRender() {
        TextureRegion houseTexture = textures.get("JOJA");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 35;
        int houseTileY = 35;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 6, tileSize * 3);
        Texture texture = GameAssetManager.trashcan;
        batch.draw(texture, drawX + 10, drawY + 10, tileSize, tileSize);
        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            GameModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX + 10 && mousePos.x <= drawX + 10 + tileSize &&
                mousePos.y >= drawY + 10 && mousePos.y <= drawY + 10 + tileSize) {
                System.out.println("wwwwwww");
                ShoppingController.isBuildingMenuOpen = true;
                ShoppingController.createSellMenu();
            }
        }

    }

    private void BuildingRender(GameModel model) {
        Player player = model.getPlayer();


        for (Objectss objectss : planting.keySet()) {
            if (objectss instanceof Crop tree) {

                int stage = FarmingController.calculateStage(tree.getCropName().getStages(), tree.getDaysPassedSincePlanting());
                Texture texture;

                if (stage >= 4) texture = tree.getCropName().getTexture()[3];
                else texture = tree.getCropName().getTexture()[stage];
                Float houseTileX = planting.get(objectss).first;
                Float houseTileY = planting.get(objectss).second;
                float camX = village.getCamera().position.x;
                float camY = village.getCamera().position.y;
                float viewportWidth = village.getCamera().viewportWidth;
                float viewportHeight = village.getCamera().viewportHeight;

                float cameraLeft = camX - viewportWidth / 2;
                float cameraBottom = camY - viewportHeight / 2;

                float drawX = houseTileX * 100 - cameraLeft;
                float drawY = houseTileY * 100 - cameraBottom;
                batch.draw(texture, drawX, drawY, 100, 100);
            }
            if (objectss instanceof Tree tree) {

                int stage = FarmingController.calculateStage(tree.getTreeName().getStages(), tree.getDaysPassedSincePlanting());
                Texture texture;

                if (stage >= 4) texture = tree.getTreeName().getTexture()[3];
                else texture = tree.getTreeName().getTexture()[stage];
                Float houseTileX = planting.get(objectss).first;
                Float houseTileY = planting.get(objectss).second;
                float camX = village.getCamera().position.x;
                float camY = village.getCamera().position.y;
                float viewportWidth = village.getCamera().viewportWidth;
                float viewportHeight = village.getCamera().viewportHeight;

                float cameraLeft = camX - viewportWidth / 2;
                float cameraBottom = camY - viewportHeight / 2;

                float drawX = houseTileX * 100 - cameraLeft;
                float drawY = houseTileY * 100 - cameraBottom;
                batch.draw(texture, drawX, drawY, 100, 100);
            }

        }
        for (Ingredients building : builds.keySet()) {
            System.out.println(building.getName());
            Texture texture = building.getTexture();

            Float houseTileX = builds.get(building).first;
            Float houseTileY = builds.get(building).second;
            float camX = village.getCamera().position.x;
            float camY = village.getCamera().position.y;
            float viewportWidth = village.getCamera().viewportWidth;
            float viewportHeight = village.getCamera().viewportHeight;

            float cameraLeft = camX - viewportWidth / 2;
            float cameraBottom = camY - viewportHeight / 2;

            float drawX = houseTileX * 100 - cameraLeft;
            float drawY = houseTileY * 100 - cameraBottom;
            batch.draw(texture, drawX, drawY, 100, 100);
            if (Gdx.input.justTouched()) {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                GameModel.getCamera().unproject(mousePos);

                if (mousePos.x >= drawX && mousePos.x <= drawX + 100 &&
                    mousePos.y >= drawY && mousePos.y <= drawY + 100) {
                    CraftsmanshipController.isBuildingMenuOpen = true;
                    CraftsmanshipController.createBuildingMenu(model);
                }
            }

        }
    }

    private void FarmingRender(GameModel model) {
        Player player1 = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();

        if (player1.getInHandTool() != null && Gdx.input.justTouched()) {
            Vector3 mouseWorldPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            GameModel.getCamera().unproject(mouseWorldPos);

            int tileX = (int) (mouseWorldPos.x / Main.TILE_SIZE);
            int tileY = (int) (mouseWorldPos.y / Main.TILE_SIZE);

            Player player = model.getPlayer();
            float playerX = player.getPlayerPosition().first;
            float playerY = player.getPlayerPosition().second;

            int dx = (int) (tileX - playerX);
            int dy = (int) (tileY - playerY);

            String direction;
            if (Math.abs(dx) > Math.abs(dy)) {
                direction = (dx > 0) ? "Right" : "Left";
            } else if (Math.abs(dy) > 0) {
                direction = (dy > 0) ? "Up" : "Down";
            } else {
                direction = "None";
            }

            float targetTileX = playerX;
            float targetTileY = playerY;
            switch (direction) {
                case "Right" -> targetTileX += 1;
                case "Left" -> targetTileX -= 1;
                case "Up" -> targetTileY += 1;
                case "Down" -> targetTileY -= 1;
            }

            Command command = new Command("command");
            command.body.put("direction", direction);
            String rslt = useTool(command).getAnswer();


            Tool currentTool = player1.getInHandTool();
            if (currentTool.getToolType() == ToolType.planting && !FarmingController.isBuildingMenuOpen && direction.equals("Left")) {
                System.out.println("entered");
                FarmingController.createBuildingMenu(model);
                FarmingController.isBuildingMenuOpen = true;
            }
            switch (currentTool.getToolType()) {
                case Hoe -> effects.add(
                    new ClickEffect(
                        GameAssetManager.getGameAssetManager().plowing, targetTileX, targetTileY, 80, 80
                    )
                );
                //case Pickaxe -> ;
                //
                // case Scythe -> ;
                case fertility -> effects.add(new ClickEffect(
                    GameAssetManager.getGameAssetManager().fertilition, targetTileX, targetTileY, 80, 80
                ));
                //case planting -> ;
                case WateringCan -> effects.add(new ClickEffect(
                    GameAssetManager.getGameAssetManager().irrigating, targetTileX, targetTileY, 80, 80
                ));

            }
        }
    }

    private void storeRender() {
        TextureRegion houseTexture = textures.get("STORE");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 45;
        int houseTileY = 35;

        float camX = village.getCamera().position.x;
        float camY = village.getCamera().position.y;
        float viewportWidth = village.getCamera().viewportWidth;
        float viewportHeight = village.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize * 5, tileSize * 3);
        // فرض شده خانه 2x2 کاشی است
        Texture texture = GameAssetManager.trashcan;
        batch.draw(texture, drawX + 10, drawY + 10, tileSize, tileSize);
        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            GameModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX + 10 && mousePos.x <= drawX + 10 + tileSize &&
                mousePos.y >= drawY + 10 && mousePos.y <= drawY + 10 + tileSize) {
                System.out.println("wwwwwww");
                ShoppingController.isBuildingMenuOpen = true;
                ShoppingController.createSellMenu();
            }
        }
    }

    // TILES
    private void renderTiles() {
        TileDescriptionId[][] tiles = game.getTiles();

        float camX = game.getCamera().position.x;
        float camY = game.getCamera().position.y;
        float viewportWidth = game.getCamera().viewportWidth;
        float viewportHeight = game.getCamera().viewportHeight;

        int tileSize = Main.TILE_SIZE;


        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        int startX = Math.max(0, (int) (cameraLeft / tileSize) - 300);
        int startY = Math.max(0, (int) (cameraBottom / tileSize) - 300);
        int endX = Math.min(tiles.length, (int) ((camX + viewportWidth / 2) / tileSize) + 300);
        int endY = Math.min(tiles[0].length, (int) ((camY + viewportHeight / 2) / tileSize) + 300);

        // Render base tiles
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                TileDescriptionId id = tiles[x][y];
                if (id != null) {
                    float drawX = x * tileSize - cameraLeft;
                    float drawY = y * tileSize - cameraBottom;

                    GrowingCrop crop = game.getGrowingCrops().get(new Point(x, y));
                    if (crop != null && crop.watered()) {
                        batch.setColor(0.7f, 0.7f, 0.7f, 1f);
                    } else {
                        batch.setColor(1f, 1f, 1f, 1f);
                    }

                    TextureRegion texture = textures.get(id.name());
                    if (texture != null) {
                        batch.draw(texture, drawX, drawY, tileSize, tileSize);
                    }
                    if (id == TileDescriptionId.TREE1) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "TREE1_SPRING";
                            case SUMMER -> "TREE1_SUMMER";
                            case AUTUMN -> "TREE1_FALL";
                            case WINTER -> "TREE1_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                    if (id == TileDescriptionId.TREE2) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "TREE2_SPRING";
                            case SUMMER -> "TREE2_SUMMER";
                            case AUTUMN -> "TREE2_FALL";
                            case WINTER -> "TREE2_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                    if (id == TileDescriptionId.STONE) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "STONE_SPRING";
                            case SUMMER -> "STONE_SUMMER";
                            case AUTUMN -> "STONE_FALL";
                            case WINTER -> "STONE_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                    if (id == TileDescriptionId.WOOD) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "WOOD_SPRING";
                            case SUMMER -> "WOOD_SUMMER";
                            case AUTUMN -> "WOOD_FALL";
                            case WINTER -> "WOOD_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                    if (id == TileDescriptionId.GRASS) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "GRASS_SPRING";
                            case SUMMER -> "GRASS_SUMMER";
                            case AUTUMN -> "GRASS_FALL";
                            case WINTER -> "GRASS_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                    if (id == TileDescriptionId.F) {
                        String treeKey = switch (App.loggedInUser.getCurrentGame().getSeason()) {
                            case SPRING -> "F_SPRING";
                            case SUMMER -> "F_SUMMER";
                            case AUTUMN -> "F_FALL";
                            case WINTER -> "F_WINTER";

                        };

                        TextureRegion treeTexture = textures.get(treeKey);
                        if (treeTexture != null) {
                            batch.setColor(1f, 1f, 1f, 1f); // ریست رنگ
                            batch.draw(treeTexture, drawX, drawY, tileSize, tileSize); // فرض درخت 1x2 هست
                        }
                    }
                }


            }


        }


        // Render crops on top
        for (Map.Entry<Point, GrowingCrop> entry : game.getGrowingCrops().entrySet()) {
            Point point = entry.getKey();
            GrowingCrop crop = entry.getValue();

            int x = point.x;
            int y = point.y;

            if (x >= startX && x < endX && y >= startY && y < endY) {
                float drawX = x * tileSize - cameraLeft;
                float drawY = y * tileSize - cameraBottom;

                int growth = crop.getGrowth();
                CarrotStages cs;

                if (growth < 2) cs = CarrotStages.CARROT_STAGE_1;
                else if (growth < 4) cs = CarrotStages.CARROT_STAGE_2;
                else if (growth < 6) cs = CarrotStages.CARROT_STAGE_3;
                else cs = CarrotStages.CARROT_STAGE_4;

                TextureRegion cropTexture = textures.get(cs.name());
                if (cropTexture != null) {
                    batch.setColor(1f, 1f, 1f, 1f);
                    batch.draw(cropTexture, drawX, drawY, tileSize, tileSize);
                }
            }
        }

        batch.setColor(1f, 1f, 1f, 1f);
    }

    // NPCS
    // NPCS
    final boolean[] isTrueGift = {false};

    private Stage animalMenuStage = null;
    private boolean isAnimalMenuOpen = false;


    private float abigalIconTimer = 0;
    private boolean showAbigailIcon = false;
    private float abigailIconVisibleTime = 0;
    private String currentDialogAbigail = null;
    String dialog = " ";
    private BitmapFont font = new BitmapFont();

    private void renderAbigail() {
        NPC npc = App.loggedInUser.getCurrentGame().getNpcs().get(1);
        Position pos = npc.getPosition();
        moveDirection = npc.getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> currentAnimation = null;
        currentAnimation = AbigailAnimation.get(moveDirection);

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        int tileSize = Main.TILE_SIZE;

        int houseTileX = pos.getX();
        int houseTileY = pos.getY();

        float camX = VillageModel.getCamera().position.x;
        float camY = VillageModel.getCamera().position.y;
        float viewportWidth = VillageModel.getCamera().viewportWidth;
        float viewportHeight = VillageModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        batch.draw(currentFrame, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);

        abigalIconTimer += Gdx.graphics.getDeltaTime();

        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            VillageModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX && mousePos.x <= drawX + Main.TILE_SIZE &&
                mousePos.y >= drawY && mousePos.y <= drawY + Main.TILE_SIZE * 2) {
                WindowNPC(npc);
            }
        }
        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }


        if (abigalIconTimer >= 10f) { // هر دو دقیقه

            showAbigailIcon = true;
            abigailIconVisibleTime = 0;
            abigalIconTimer = 0;
            //  dialog = NPCController.getGPT(npc);
            dialog = NPCController.getDialogue(npc);
        }

        if (showAbigailIcon) {
            abigailIconVisibleTime += Gdx.graphics.getDeltaTime();

            float iconX = drawX + Main.TILE_SIZE / 2f - 16; // آیکون 32px
            float iconY = drawY + Main.TILE_SIZE * 2 + 10;

            batch.draw(iconTexture, iconX, iconY, 32, 32);


            if (abigailIconVisibleTime >= 5f) {
                showAbigailIcon = false;
            }

            if (Gdx.input.justTouched()) {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                VillageModel.getCamera().unproject(mousePos);

                if (mousePos.x >= iconX && mousePos.x <= iconX + 32 &&
                    mousePos.y >= iconY && mousePos.y <= iconY + 32) {

                    currentDialogAbigail = dialog;
                }
            }
        }

        if (currentDialogAbigail != null) {
            float dialogWidth = 150;
            float dialogHeight = 50;
            float dialogX = drawX;
            float dialogY = drawY - dialogHeight - 5;

            font.setColor(Color.WHITE);
            font.draw(batch, dialog, dialogX + 10, dialogY + dialogHeight - 10);


            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                currentDialogAbigail = null;
            }
        }
        if (isTrueGift[0]) {
            float iconX = drawX;
            float iconY = drawY + Main.TILE_SIZE * 2 - 10;

            batch.draw(giftTexture, iconX, iconY, 32, 32);
        }
        if (achivement) {
            float iconX = drawX;
            float iconY = drawY + Main.TILE_SIZE * 2 - 60;

            batch.draw(achiveTexture, iconX, iconY, 32, 32);
        }

        if (abigailEnd) {
            handleNPCMovement(npc);
            updateNPCMovement(Gdx.graphics.getDeltaTime(), npc);
        }

    }

    private void handleNPCMovement(NPC npc) {
        Random random = new Random();
        if (random.nextInt(1, 1000) == 1 && random.nextInt(1, 10) != 2) {
            moveNPC(npc);
        }
    }

    private void moveNPC(NPC npc) {
        Random random = new Random();
        int totalDistance = 5;
        int dx = random.nextInt(-1, 2);
        int dy = random.nextInt(-1, 2);

        if (dx == 0 && dy == 0) return;

        Queue<Vector2> path = new LinkedList<>();
        int currentX = npc.getPosition().getX();
        int currentY = npc.getPosition().getY();

        for (int i = 1; i <= totalDistance; i++) {
            int stepX = currentX + dx * i;
            int stepY = currentY + dy * i;
            path.add(new Vector2(stepX, stepY));
        }

        if (!path.isEmpty()) {
            npc.setMovementPath(path);
        }
    }

    private void updateNPCMovement(float delta, NPC npc) {
        npc.setTimeSinceLastMove(npc.getTimeSinceLastMove() + delta);

        if (npc.getMovementPath() != null && !npc.getMovementPath().isEmpty()) {
            if (npc.getTimeSinceLastMove() > 0.3f) {
                npc.setTimeSinceLastMove(0);
                Vector2 nextStep = npc.getMovementPath().poll();
                npc.getTiles().get(0).getCoordinate().setX((int) nextStep.x);
                npc.getTiles().get(0).getCoordinate().setY((int) nextStep.y);
                npc.setPosition(new Position((int) nextStep.x, (int) nextStep.y));
            }
        }
    }

    private void WindowNPC(NPC npc) {
        Stage animalStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(animalStage);


        Group menuGroup = new Group();

        com.badlogic.gdx.scenes.scene2d.ui.Window window = new com.badlogic.gdx.scenes.scene2d.ui.Window("NPC Menu", GameAssetManager.LABI_SKIN);
        window.setSize(1200, 600);
        window.setMovable(false);

        Table table = new Table();
        table.setFillParent(true);
        table.top().pad(20).defaults().pad(10).left();

        final com.badlogic.gdx.scenes.scene2d.ui.Label responseLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("", GameAssetManager.LABI_SKIN);
        responseLabel.setAlignment(Align.center);
        responseLabel.setWrap(true);
        responseLabel.setColor(Color.DARK_GRAY);
        responseLabel.setText(npc.getName());
        table.add(responseLabel).colspan(2).width(600).left();

        table.row().pad(10, 0, 10, 0);
        final com.badlogic.gdx.scenes.scene2d.ui.Label friendshipLabel = new Label("", GameAssetManager.LABI_SKIN);
        friendshipLabel.setAlignment(Align.center);
        friendshipLabel.setWrap(true);
        friendshipLabel.setColor(Color.BROWN);
        friendshipLabel.setText(NPCController.ShowFriendship(npc).getAnswer());
        table.add(friendshipLabel).width(600);
        table.row().pad(10, 0, 10, 0);

        final TextField giftField = new TextField("What are you giving as a gift?", GameAssetManager.LABI_SKIN);
        giftField.setAlignment(Align.center);
        table.add(giftField).width(600).height(60);
        table.row().pad(10, 0, 10, 0);

        final TextButton giftButton = new TextButton("Gift", GameAssetManager.LABI_SKIN);
        table.add(giftButton).width(600).height(60);
        table.row().pad(10, 0, 10, 0);

        giftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Resualt resualt = NPCController.GiftNPC(npc, giftField.getText());
                Dialog dialog = new Dialog("", GameAssetManager.LABI_SKIN);
                dialog.text(resualt.getAnswer());
                dialog.button("OK");
                dialog.show(animalStage);
                if (resualt.isAccept())
                    isTrueGift[0] = true;
            }
        });

        final com.badlogic.gdx.scenes.scene2d.ui.Label questLabel = new Label("", GameAssetManager.LABI_SKIN);
        questLabel.setAlignment(Align.center);
        questLabel.setWrap(false);
        questLabel.setColor(Color.BROWN);
        Resualt resualt = NPCController.ShowAllQuests(npc);
        questLabel.setText(resualt.getAnswer());
//        table.add(questLabel).colspan(4).left().expandX();
//        table.pad(20, 0, 20, 0);
        table.add(questLabel).left().expandX().padRight(20);


        TextButton button1 = new TextButton("1", GameAssetManager.LABI_SKIN);
        table.add(button1).width(100).padRight(10);

        TextButton button2 = new TextButton("2", GameAssetManager.LABI_SKIN);
        table.add(button2).width(100);

        table.row().pad(20, 0, 20, 0);

        ScrollPane scrollPane = new ScrollPane(table, GameAssetManager.LABI_SKIN);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);


        //  window.add(table).expand().fill();
        window.add(scrollPane).expand().fill();


        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                animalStage.clear();
                isAnimalMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        Group group = new Group() {
            @Override
            public void act(float delta) {
                super.act(delta);
                window.setPosition(
                    (animalStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
                    (animalStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
                );
                exitButton.setPosition(
                    window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
                    window.getY() + window.getHeight() - exitButton.getHeight() / 2f
                );
            }
        };

        group.addActor(window);
        group.addActor(exitButton);
        menuGroup.addActor(group);
        animalStage.addActor(menuGroup);

        this.animalMenuStage = animalStage;
        this.isAnimalMenuOpen = true;
    }

    private float harveyIconTimer = 0;
    private boolean showHarveyIcon = false;
    private float harveyIconVisibleTime = 0;
    private String currentDialogHarvey = null;
    String dialogHarvey = " ";

    private void renderHarvey() {
        NPC npc = App.loggedInUser.getCurrentGame().getNpcs().get(2);
        Position pos = npc.getPosition();
        moveDirection = npc.getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> currentAnimation = null;
        currentAnimation = HarveyAnimations.get(moveDirection);

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        int tileSize = Main.TILE_SIZE;

        int houseTileX = pos.getX();
        int houseTileY = pos.getY();

        float camX = VillageModel.getCamera().position.x;
        float camY = VillageModel.getCamera().position.y;
        float viewportWidth = VillageModel.getCamera().viewportWidth;
        float viewportHeight = VillageModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        batch.draw(currentFrame, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);


        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            VillageModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX && mousePos.x <= drawX + Main.TILE_SIZE &&
                mousePos.y >= drawY && mousePos.y <= drawY + Main.TILE_SIZE * 2) {
                WindowNPC(npc);
            }
        }
        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }


        harveyIconTimer += Gdx.graphics.getDeltaTime();

        if (harveyIconTimer >= 15f) {
            showHarveyIcon = true;
            harveyIconVisibleTime = 0;
            harveyIconTimer = 0;
            ///dialogHarvey = NPCController.getGPT(npc);
            dialogHarvey = NPCController.getDialogue(npc);
        }


        if (showHarveyIcon) {
            harveyIconVisibleTime += Gdx.graphics.getDeltaTime();

            float iconX = drawX + Main.TILE_SIZE / 2f - 16; // آیکون 32px
            float iconY = drawY + Main.TILE_SIZE * 2 + 10;

            batch.draw(iconTexture, iconX, iconY, 32, 32);

            if (harveyIconVisibleTime >= 5f) {
                showHarveyIcon = false;
            }

            if (Gdx.input.justTouched()) {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                VillageModel.getCamera().unproject(mousePos);

                if (mousePos.x >= iconX && mousePos.x <= iconX + 32 &&
                    mousePos.y >= iconY && mousePos.y <= iconY + 32) {

                    currentDialogHarvey = dialogHarvey;
                }
            }
        }

        if (currentDialogHarvey != null) {
            float dialogWidth = 150;
            float dialogHeight = 50;
            float dialogX = drawX;
            float dialogY = drawY - dialogHeight - 5;

            font.setColor(Color.WHITE);
            font.draw(batch, dialogHarvey, dialogX + 10, dialogY + dialogHeight - 10);


            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                currentDialogHarvey = null;
            }
        }

        if (isTrueGift[0]) {
            float iconX = drawX;
            float iconY = drawY + Main.TILE_SIZE * 2 - 10;

            batch.draw(giftTexture, iconX, iconY, 32, 32);
        }

        if (achivement) {
            float iconX = drawX;
            float iconY = drawY + Main.TILE_SIZE * 2 - 60;

            batch.draw(achiveTexture, iconX, iconY, 32, 32);
        }

        if (harveyEnd) {
            handleNPCMovement(npc);
            updateNPCMovement(Gdx.graphics.getDeltaTime(), npc);
        }
    }

    private float leaIconTimer = 0;
    private boolean showLeaIcon = false;
    private float leaIconVisibleTime = 0;
    private String currentDialogLea = null;
    String dialogLea = " ";

    private void renderLea() {
        NPC npc = App.loggedInUser.getCurrentGame().getNpcs().get(3);
        Position pos = npc.getPosition();
        moveDirection = npc.getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> currentAnimation = null;
        currentAnimation = LeaAnimations.get(moveDirection);

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        int tileSize = Main.TILE_SIZE;

        int houseTileX = pos.getX();
        int houseTileY = pos.getY();

        float camX = VillageModel.getCamera().position.x;
        float camY = VillageModel.getCamera().position.y;
        float viewportWidth = VillageModel.getCamera().viewportWidth;
        float viewportHeight = VillageModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        batch.draw(currentFrame, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);

        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            VillageModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX && mousePos.x <= drawX + Main.TILE_SIZE &&
                mousePos.y >= drawY && mousePos.y <= drawY + Main.TILE_SIZE * 2) {
                WindowNPC(npc);
            }
        }
        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }

        leaIconTimer += Gdx.graphics.getDeltaTime();

        if (leaIconTimer >= 20f) {
            showLeaIcon = true;
            leaIconVisibleTime = 0;
            leaIconTimer = 0;
            //dialogLea = NPCController.getGPT(npc);
            dialogLea = NPCController.getDialogue(npc);
        }


        if (showLeaIcon) {
            leaIconVisibleTime += Gdx.graphics.getDeltaTime();

            float iconX = drawX + Main.TILE_SIZE / 2f - 16; // آیکون 32px
            float iconY = drawY + Main.TILE_SIZE * 2 + 10;

            batch.draw(iconTexture, iconX, iconY, 32, 32);

            if (leaIconVisibleTime >= 5f) {
                showLeaIcon = false;
            }

            if (Gdx.input.justTouched()) {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                VillageModel.getCamera().unproject(mousePos);

                if (mousePos.x >= iconX && mousePos.x <= iconX + 32 &&
                    mousePos.y >= iconY && mousePos.y <= iconY + 32) {

                    currentDialogLea = dialogLea;
                }
            }
        }

        if (currentDialogLea != null) {
            float dialogWidth = 150;
            float dialogHeight = 50;
            float dialogX = drawX;
            float dialogY = drawY - dialogHeight - 5;

            font.setColor(Color.WHITE);
            font.draw(batch, dialogLea, dialogX + 10, dialogY + dialogHeight - 10);


            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                currentDialogLea = null;
            }
        }

        if (isTrueGift[0]) {
            float iconX = drawX;
            float iconY = drawY + Main.TILE_SIZE * 2 - 10;

            batch.draw(giftTexture, iconX, iconY, 32, 32);
        }

        if (achivement) {
            float iconX = drawX;
            float iconY = drawY + Main.TILE_SIZE * 2 - 60;

            batch.draw(achiveTexture, iconX, iconY, 32, 32);
        }

        if (leaEnd) {
            handleNPCMovement(npc);
            updateNPCMovement(Gdx.graphics.getDeltaTime(), npc);
        }
    }

    private float robinIconTimer = 0;
    private boolean showRobinIcon = false;
    private float robinIconVisibleTime = 0;
    private String currentDialogRobin = null;
    String dialogRobin = " ";

    private void renderRobin() {
        NPC npc = App.loggedInUser.getCurrentGame().getNpcs().get(4);
        Position pos = npc.getPosition();
        moveDirection = npc.getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> currentAnimation = null;
        currentAnimation = RobinAnimations.get(moveDirection);

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        int tileSize = Main.TILE_SIZE;

        int houseTileX = pos.getX();
        int houseTileY = pos.getY();

        float camX = VillageModel.getCamera().position.x;
        float camY = VillageModel.getCamera().position.y;
        float viewportWidth = VillageModel.getCamera().viewportWidth;
        float viewportHeight = VillageModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        batch.draw(currentFrame, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);

        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            VillageModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX && mousePos.x <= drawX + Main.TILE_SIZE &&
                mousePos.y >= drawY && mousePos.y <= drawY + Main.TILE_SIZE * 2) {
                WindowNPC(npc);
            }
        }
        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }


        robinIconTimer += Gdx.graphics.getDeltaTime();

        if (robinIconTimer >= 25f) {
            showRobinIcon = true;
            robinIconVisibleTime = 0;
            leaIconTimer = 0;
            // dialogRobin = NPCController.getGPT(npc);
            dialogRobin = NPCController.getDialogue(npc);
        }


        if (showRobinIcon) {
            leaIconVisibleTime += Gdx.graphics.getDeltaTime();

            float iconX = drawX + Main.TILE_SIZE / 2f - 16; // آیکون 32px
            float iconY = drawY + Main.TILE_SIZE * 2 + 10;

            batch.draw(iconTexture, iconX, iconY, 32, 32);

            if (robinIconVisibleTime >= 5f) {
                showRobinIcon = false;
            }

            if (Gdx.input.justTouched()) {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                VillageModel.getCamera().unproject(mousePos);

                if (mousePos.x >= iconX && mousePos.x <= iconX + 32 &&
                    mousePos.y >= iconY && mousePos.y <= iconY + 32) {

                    currentDialogRobin = dialogRobin;
                }
            }
        }

        if (currentDialogRobin != null) {
            float dialogWidth = 150;
            float dialogHeight = 50;
            float dialogX = drawX;
            float dialogY = drawY - dialogHeight - 5;

            font.setColor(Color.WHITE);
            font.draw(batch, dialogRobin, dialogX + 10, dialogY + dialogHeight - 10);


            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                currentDialogRobin = null;
            }
        }

        if (isTrueGift[0]) {
            float iconX = drawX;
            float iconY = drawY + Main.TILE_SIZE * 2 - 10;

            batch.draw(giftTexture, iconX, iconY, 32, 32);
        }

        if (achivement) {
            float iconX = drawX;
            float iconY = drawY + Main.TILE_SIZE * 2 - 60;

            batch.draw(achiveTexture, iconX, iconY, 32, 32);
        }
    }

    private float sebastianIconTimer = 0;
    private boolean showSebastianIcon = false;
    private float sebastianIconVisibleTime = 0;
    private String currentDialogSebastian = null;
    String dialogSebastian = " ";

    private void renderSebastian() {
        NPC npc = App.loggedInUser.getCurrentGame().getNpcs().get(0);
        Position pos = npc.getPosition();
        moveDirection = npc.getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> currentAnimation = null;
        currentAnimation = SebastianAnimations.get(moveDirection);

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        int tileSize = Main.TILE_SIZE;

        int houseTileX = pos.getX();
        int houseTileY = pos.getY();

        float camX = VillageModel.getCamera().position.x;
        float camY = VillageModel.getCamera().position.y;
        float viewportWidth = VillageModel.getCamera().viewportWidth;
        float viewportHeight = VillageModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        batch.draw(currentFrame, drawX, drawY, Main.TILE_SIZE, Main.TILE_SIZE * 2);

        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            VillageModel.getCamera().unproject(mousePos);

            if (mousePos.x >= drawX && mousePos.x <= drawX + Main.TILE_SIZE &&
                mousePos.y >= drawY && mousePos.y <= drawY + Main.TILE_SIZE * 2) {
                WindowNPC(npc);
            }
        }
        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }


        sebastianIconTimer += Gdx.graphics.getDeltaTime();

        if (sebastianIconTimer >= 25f) {
            showSebastianIcon = true;
            sebastianIconVisibleTime = 0;
            sebastianIconTimer = 0;
            dialogSebastian = NPCController.getGPT(npc);
            // dialogSebastian = NPCController.getDialogue(npc);
        }


        if (showSebastianIcon) {
            sebastianIconVisibleTime += Gdx.graphics.getDeltaTime();

            float iconX = drawX + Main.TILE_SIZE / 2f - 16; // آیکون 32px
            float iconY = drawY + Main.TILE_SIZE * 2 + 10;

            batch.draw(iconTexture, iconX, iconY, 32, 32);

            if (sebastianIconVisibleTime >= 5f) {
                showSebastianIcon = false;
            }

            if (Gdx.input.justTouched()) {
                Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                VillageModel.getCamera().unproject(mousePos);

                if (mousePos.x >= iconX && mousePos.x <= iconX + 32 &&
                    mousePos.y >= iconY && mousePos.y <= iconY + 32) {

                    currentDialogSebastian = dialogSebastian;
                }
            }
        }

        if (currentDialogSebastian != null) {
            float dialogWidth = 150;
            float dialogHeight = 50;
            float dialogX = drawX;
            float dialogY = drawY - dialogHeight - 5;

            font.setColor(Color.RED);
            font.draw(batch, dialogSebastian, dialogX + 10, dialogY + dialogHeight - 10);


            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
                currentDialogSebastian = null;
            }
        }

        if (isTrueGift[0]) {
            float iconX = drawX;
            float iconY = drawY + Main.TILE_SIZE * 2 - 10;

            batch.draw(giftTexture, iconX, iconY, 32, 32);
        }

        if (achivement) {
            float iconX = drawX;
            float iconY = drawY + Main.TILE_SIZE * 2 - 60;

            batch.draw(achiveTexture, iconX, iconY, 32, 32);
        }

        if (sebastianEnd) {
            handleNPCMovement(npc);
            updateNPCMovement(Gdx.graphics.getDeltaTime(), npc);
        }
    }

    // PLAYER
    private void renderPlayer() {
        Pair<Float, Float> pos;
        if (!isVillage)
            pos = game.getPlayer().getPlayerPosition();
        else
            pos = village.getPlayer().getPlayerPosition();


        if (!isVillage)
            moveDirection = game.getPlayer().getMovingDirection();
        else
            moveDirection = village.getPlayer().getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();
        Player o;
        if (!isVillage)
            o = game.getPlayer();
        else
            o = village.getPlayer();

        boolean isFainting = (o.getEnergy() == 0);

        if (isFainting) {
            faintStateTime += Gdx.graphics.getDeltaTime();
        } else {
            faintStateTime = 0f; // ریست بشه اگر انرژی برگشته
        }


        Tool toll = o.getInHandTool();
        Texture tool = null;
        if (toll != null) {
            tool = GameAssetManager.getTexture(o.getInHandTool());
        }
        Animation<TextureRegion> currentAnimation = null;


        if (o.getUser().getAvatar().equals(Avatar.HALEY)) {
            if (o.getEnergy() != 0)
                currentAnimation = haleyAnimations.get(moveDirection);
            else {
                currentAnimation = GameAssetManager.getGameAssetManager().getHaley_faint();
            }
        } else if (o.getUser().getAvatar().equals(Avatar.LEAH)) {
            if (o.getEnergy() != 0)
                currentAnimation = leahAnimations.get(moveDirection);
            else
                currentAnimation = GameAssetManager.getGameAssetManager().getLeah_faint();
        } else if (o.getUser().getAvatar().equals(Avatar.ELLIOTT)) {
            if (o.getEnergy() != 0)
                currentAnimation = elliottAnimations.get(moveDirection);
            else
                currentAnimation = GameAssetManager.getGameAssetManager().getElliott_faint();
        } else if (o.getUser().getAvatar().equals(Avatar.SEBASTIAN)) {
            if (o.getEnergy() != 0)
                currentAnimation = sebastianAnimations.get(moveDirection);
            else
                currentAnimation = GameAssetManager.getGameAssetManager().getSebastian_faint();
        } else if (o.getUser().getAvatar().equals(Avatar.ROBIN)) {
            if (o.getEnergy() != 0)
                currentAnimation = robinAnimations.get(moveDirection);
            else
                currentAnimation = GameAssetManager.getGameAssetManager().getRobin_faint();
        } else if (o.getUser().getAvatar().equals(Avatar.SHANE)) {
            if (o.getEnergy() != 0)
                currentAnimation = shaneAnimations.get(moveDirection);
            else
                currentAnimation = GameAssetManager.getGameAssetManager().getShane_faint();
        } else {
            currentAnimation = playerAnimations.get(moveDirection);
        }

        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        if (isFainting) {
            currentFrame = currentAnimation.getKeyFrame(faintStateTime, false); // فقط یک بار اجرا بشه
        }

        if (o.getEnergy() == 0) {
            batch.draw(currentFrame, pos.first * Main.TILE_SIZE, pos.second * Main.TILE_SIZE, 100, 100);
        } else {
            int reaction = App.loggedInUser.getCurrentGame().getCurrentPlayer().getReaction();
            if (reaction == 1)
                batch.draw(GameAssetManager.R_1, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
            if (reaction == 2)
                batch.draw(GameAssetManager.R_2, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
            if (reaction == 3)
                batch.draw(GameAssetManager.R_3, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
            if (reaction == 4)
                batch.draw(GameAssetManager.R_4, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
            if (reaction == 5)
                batch.draw(GameAssetManager.R_5, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
            if (reaction == 6)
                batch.draw(GameAssetManager.R_6, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
            batch.draw(currentFrame, pos.first * Main.TILE_SIZE, pos.second * Main.TILE_SIZE, 60, 120);
        }

        if (tool != null) {
            float playerX = pos.first * Main.TILE_SIZE;
            float playerY = pos.second * Main.TILE_SIZE;

            float toolOffsetX = 50;
            float toolOffsetY = 20;

            batch.draw(tool, playerX + toolOffsetX, playerY + toolOffsetY, 44, 44);
        }
        if (game != null)
            renderInventory();


        int tileSize = Main.TILE_SIZE;

        Matrix4 originalProjection = batch.getProjectionMatrix().cpy();

        batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        font.getData().setScale(3f);
        font.setColor(Color.BLACK);
        font.draw(batch, "Energy: " + o.getEnergy() + " / 200", 10, Gdx.graphics.getHeight() - 10);
        batch.draw(GameAssetManager.ENERGY_CHEAT, 10, Gdx.graphics.getHeight() - 150, 60, 60);
        batch.draw(GameAssetManager.RADIO, 10, Gdx.graphics.getHeight() - 450, 60, 60);
        batch.draw(GameAssetManager.SORT, 10, Gdx.graphics.getHeight() - 500, 60, 60);
        batch.draw(GameAssetManager.REACTION, 10, Gdx.graphics.getHeight() - 570, 60, 60);

        if(isWedding)
            batch.draw(GameAssetManager.WEDDING, 10, Gdx.graphics.getHeight() - 650, 60, 60);
        if(isHug)
            batch.draw(GameAssetManager.HUG, 10, Gdx.graphics.getHeight() - 650, 60, 60);

        if (Gdx.input.isKeyJustPressed(Input.Keys.F8)) {
            isTalk = true;
            App.loggedInUser.getCurrentGame().getFriendMatrix().get(ClientApp.getTurnNumber()).get(0).changeXP(200);
            App.loggedInUser.getCurrentGame().getFriendMatrix().get(ClientApp.getTurnNumber()).get(1).changeXP(200);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F7)) {
            isGift = true;
            App.loggedInUser.getCurrentGame().getFriendMatrix().get(ClientApp.getTurnNumber()).get(0).changeXP(800);
            App.loggedInUser.getCurrentGame().getFriendMatrix().get(ClientApp.getTurnNumber()).get(1).changeXP(800);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F6)) {
            App.loggedInUser.getCurrentGame().getCurrentPlayer().getReceivedGifts().add("Send : hay");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            isTalk2 = true;
            App.loggedInUser.getCurrentGame().getFriendMatrix().get(ClientApp.getTurnNumber()).get(0).changeXP(100);
            App.loggedInUser.getCurrentGame().getFriendMatrix().get(ClientApp.getTurnNumber()).get(1).changeXP(100);
        }
        if (isTalk || isTalk2) {
            batch.draw(GameAssetManager.TALK, 10, Gdx.graphics.getHeight() - 300, 60, 60);
        }
        if (isGift) {
            App.loggedInUser.getCurrentGame().getCurrentPlayer().getReceivedGifts().add("Recieved : hay");
            batch.draw(GameAssetManager.GIFT, 10, Gdx.graphics.getHeight() - 400, 60, 60);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F4))
            isTrueGift[0] = true;

        if (Gdx.input.isKeyJustPressed(Input.Keys.F3))
            achivement = true;

        batch.setProjectionMatrix(originalProjection);

        if (Gdx.input.justTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            mouseY = Gdx.graphics.getHeight() - mouseY;

            float btnX = 10;
            float btnY = Gdx.graphics.getHeight() - 150;
            float btnWidth = 60;
            float btnHeight = 60;

            if (mouseX >= btnX && mouseX <= btnX + btnWidth &&
                mouseY >= btnY && mouseY <= btnY + btnHeight) {
                cheatWindow();
            }
        }

        if (Gdx.input.justTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            mouseY = Gdx.graphics.getHeight() - mouseY;

            float btnX = 10;
            float btnY = Gdx.graphics.getHeight() - 300;
            float btnWidth = 60;
            float btnHeight = 60;

            if (mouseX >= btnX && mouseX <= btnX + btnWidth &&
                mouseY >= btnY && mouseY <= btnY + btnHeight) {
                talk();
            }
        }

        if (Gdx.input.justTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            mouseY = Gdx.graphics.getHeight() - mouseY;

            float btnX = 10;
            float btnY = Gdx.graphics.getHeight() - 400;
            float btnWidth = 60;
            float btnHeight = 60;

            if (mouseX >= btnX && mouseX <= btnX + btnWidth &&
                mouseY >= btnY && mouseY <= btnY + btnHeight) {
                gift();
            }
        }

        if (Gdx.input.justTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            mouseY = Gdx.graphics.getHeight() - mouseY;

            float btnX = 10;
            float btnY = Gdx.graphics.getHeight() - 450;
            float btnWidth = 60;
            float btnHeight = 60;

            if (mouseX >= btnX && mouseX <= btnX + btnWidth &&
                mouseY >= btnY && mouseY <= btnY + btnHeight) {
                radio();
            }
        }

        if (Gdx.input.justTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            mouseY = Gdx.graphics.getHeight() - mouseY;

            float btnX = 10;
            float btnY = Gdx.graphics.getHeight() - 500;
            float btnWidth = 60;
            float btnHeight = 60;

            if (mouseX >= btnX && mouseX <= btnX + btnWidth &&
                mouseY >= btnY && mouseY <= btnY + btnHeight) {
                sortMenu();
            }
        }

        if (Gdx.input.justTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();

            mouseY = Gdx.graphics.getHeight() - mouseY;

            float btnX = 10;
            float btnY = Gdx.graphics.getHeight() - 570;
            float btnWidth = 60;
            float btnHeight = 60;

            if (mouseX >= btnX && mouseX <= btnX + btnWidth &&
                mouseY >= btnY && mouseY <= btnY + btnHeight) {
                reactionMenu();
            }
        }


        if (fishing) {
            batch.draw(GameAssetManager.FISHING, (game.getPlayer().getPlayerPosition().first + 1) * Main.TILE_SIZE, (game.getPlayer().getPlayerPosition().second + 1) * Main.TILE_SIZE, 40, 40);
        }
    }

    private Stage sortMenuStage = null;
    private boolean sortMenuOpen = false;

    private void sortMenu() {
        Stage sortStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(sortStage);
        com.badlogic.gdx.scenes.scene2d.ui.Window window =
            new com.badlogic.gdx.scenes.scene2d.ui.Window("Sort", GameAssetManager.SKIN);
        window.setSize(700, 400);
        window.setMovable(false);
        Table table = new Table();
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();

        TextButton money = new TextButton("MONEY", GameAssetManager.SKIN);
        table.add(money).width(250);
        table.row();
        money.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.loggedInUser.getCurrentGame().setSortFactor(0);
            }
        });

        TextButton skills = new TextButton("SKILLS", GameAssetManager.SKIN);
        table.add(skills).width(250);
        table.row();
        skills.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.loggedInUser.getCurrentGame().setSortFactor(1);
            }
        });

        TextButton quests = new TextButton("QUESTS", GameAssetManager.SKIN);
        table.add(quests).width(250);
        table.row();
        quests.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.loggedInUser.getCurrentGame().setSortFactor(2);
            }
        });

        window.add(table).expand().fill();

        ImageButton exitButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON))
        );
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sortStage.clear();
                sortMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });
        window.setPosition(
            (sortStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
            (sortStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
        );
        exitButton.setPosition(
            window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
            window.getY() + window.getHeight() - exitButton.getHeight() / 2f
        );

        sortStage.addActor(window);
        sortStage.addActor(exitButton);

        this.sortMenuStage = sortStage;
        this.sortMenuOpen = true;

    }

    private Stage reactionMenuStage = null;
    private boolean reactionMenuOpen = false;
    private boolean is1 = true;
    private boolean is2 = true;
    private boolean is3 = false;
    private boolean is4 = false;
    private boolean is5 = true;
    private boolean is6 = false;

    private void reactionMenu() {
        Stage reactionStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(reactionStage);
        com.badlogic.gdx.scenes.scene2d.ui.Window window =
            new com.badlogic.gdx.scenes.scene2d.ui.Window("Reaction", GameAssetManager.LABI_SKIN);
        window.setSize(700, 400);
        window.setMovable(false);
        Table table = new Table();
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();
        Image image1 = null;
        if (is1)
            image1 = new Image(GameAssetManager.R_1);
        else
            image1 = new Image(GameAssetManager.R_3);
        TextButton change = new TextButton("CHANGE", GameAssetManager.LABI_SKIN);
        TextButton yes1 = new TextButton("THIS", GameAssetManager.LABI_SKIN);
        table.add(image1).width(50).height(50);
        table.add(yes1).width(100).height(50);
        table.add(change).width(100).height(50);
        change.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                is1 = !is1;
                is3 = !is3;
            }
        });
        yes1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (is1)
                    App.loggedInUser.getCurrentGame().getCurrentPlayer().setReaction(1, true);
                else
                    App.loggedInUser.getCurrentGame().getCurrentPlayer().setReaction(3, true);
            }
        });
        table.row();
        Image image2 = null;
        if (is2)
            image2 = new Image(GameAssetManager.R_2);
        else
            image2 = new Image(GameAssetManager.R_4);
        TextButton change2 = new TextButton("CHANGE", GameAssetManager.LABI_SKIN);
        TextButton yes2 = new TextButton("THIS", GameAssetManager.LABI_SKIN);
        table.add(image2).width(50).height(50);
        table.add(yes2).width(100).height(50);
        table.add(change2).width(100).height(50);
        change2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                is2 = !is2;
                is4 = !is4;
            }
        });
        yes2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (is2)
                    App.loggedInUser.getCurrentGame().getCurrentPlayer().setReaction(2, true);
                else
                    App.loggedInUser.getCurrentGame().getCurrentPlayer().setReaction(4, true);
            }
        });
        table.row();
        Image image5 = null;
        if (is5)
            image5 = new Image(GameAssetManager.R_5);
        else
            image5 = new Image(GameAssetManager.R_6);
        TextButton change3 = new TextButton("CHANGE", GameAssetManager.LABI_SKIN);
        TextButton yes3 = new TextButton("THIS", GameAssetManager.LABI_SKIN);
        table.add(image5).width(50).height(50);
        table.add(yes3).width(100).height(50);
        table.add(change3).width(100).height(50);
        change3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                is5 = !is5;
                is6 = !is6;
            }
        });
        yes3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (is5)
                    App.loggedInUser.getCurrentGame().getCurrentPlayer().setReaction(5, true);
                else
                    App.loggedInUser.getCurrentGame().getCurrentPlayer().setReaction(6, true);
            }
        });


        window.add(table).expand().fill();

        ImageButton exitButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON))
        );
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                reactionStage.clear();
                reactionMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });
        window.setPosition(
            (reactionStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
            (reactionStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
        );
        exitButton.setPosition(
            window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
            window.getY() + window.getHeight() - exitButton.getHeight() / 2f
        );

        reactionStage.addActor(window);
        reactionStage.addActor(exitButton);

        this.reactionMenuStage = reactionStage;
        this.reactionMenuOpen = true;

    }

    private Stage radioMenuStage = null;
    private boolean radioMenuOpen = false;
    private Music currentMusic = null;
    private Array<String> songPaths = new Array<>();
    private List<String> songList;
    private String currentPath = "E:\\my muse\\amygdala (1).mp3";
    Music myMusic = Gdx.audio.newMusic(Gdx.files.internal("song.mp3"));

    public void radio() {
        Stage radioStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(radioStage);

        Window window = new Window("Radio", GameAssetManager.RADIO_SKIN);
        window.setSize(700, 400);
        window.setMovable(false);

        Table table = new Table();
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();
        window.add(table).expand().fill();

        // دکمه خروج
        ImageButton exitButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON))
        );
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                radioStage.clear();
                radioMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        // دکمه انتخاب آهنگ
        TextButton selectMusicButton = new TextButton("Select Music", GameAssetManager.RADIO_SKIN);
        selectMusicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                    javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
                    chooser.setMultiSelectionEnabled(true);
                    int returnVal = chooser.showOpenDialog(null);
                    if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                        File[] files = chooser.getSelectedFiles();
                        songPaths.clear();
                        for (File file : files) {
                            songPaths.add(file.getAbsolutePath());
                        }
                        updateSongList();
                    }
                }
            }
        });
        table.add(selectMusicButton).left().row();

        songList = new List<>(GameAssetManager.RADIO_SKIN);
        songList.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String selected = songList.getSelected();
                for (String path : songPaths) {
                    if (path.endsWith(selected)) {
                        currentPath = path;
                        playSong(path);
                        break;
                    }
                }
            }
        });
        table.add(songList).expand().fill().row();

        TextButton radio = new TextButton("1", GameAssetManager.RADIO_SKIN);
        table.add(radio).left();
        radio.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!myMusic.isPlaying()) {
                    myMusic.play();
                }
            }
        });

        TextButton radio2 = new TextButton("2", GameAssetManager.RADIO_SKIN);
        table.add(radio2).left();
        TextButton radio3 = new TextButton("3", GameAssetManager.RADIO_SKIN);
        table.add(radio3).left();
        table.row();

        ScrollPane scrollPane = new ScrollPane(table, GameAssetManager.LABI_SKIN);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);

        window.add(scrollPane).expand().fill();

        window.setPosition(
            (radioStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
            (radioStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
        );
        exitButton.setPosition(
            window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
            window.getY() + window.getHeight() - exitButton.getHeight() / 2f
        );

        radioStage.addActor(window);
        radioStage.addActor(exitButton);

        this.radioMenuStage = radioStage;
        this.radioMenuOpen = true;
    }

    private void updateSongList() {
        Array<String> names = new Array<>();
        for (String path : songPaths) {
            names.add(new File(path).getName());
        }
        if (songList != null) {
            songList.setItems(names);
        }
    }

    private void playSong(String path) {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
        }
        currentMusic = Gdx.audio.newMusic(Gdx.files.absolute(path));
        currentMusic.play();
    }

    private boolean isGift = false;
    private Stage giftCheatMenuStage = null;
    private boolean giftCheatMenuOpen = false;

    private void gift() {
        Stage cheatStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(cheatStage);
        com.badlogic.gdx.scenes.scene2d.ui.Window window =
            new com.badlogic.gdx.scenes.scene2d.ui.Window("Cheat", GameAssetManager.FRIEND_SKIN);
        window.setSize(700, 400);
        window.setMovable(false);
        Table table = new Table();
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();

        Label label = new Label("May: hay", GameAssetManager.FRIEND_SKIN);
        table.add(label).width(520).left();
        table.row();
        TextField textField = new TextField("Rate", GameAssetManager.FRIEND_SKIN);
        table.add(textField).width(520).left();
        table.row();

        TextButton button = new TextButton("Ok", GameAssetManager.FRIEND_SKIN);
        table.add(button).width(520).left();
        table.row();

        window.add(table).expand().fill();

        ImageButton exitButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cheatStage.clear();
                giftCheatMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        // موقعیت وسط صفحه
        window.setPosition(
            (cheatStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
            (cheatStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
        );
        exitButton.setPosition(
            window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
            window.getY() + window.getHeight() - exitButton.getHeight() / 2f
        );

        cheatStage.addActor(window);
        cheatStage.addActor(exitButton);

        this.giftCheatMenuStage = cheatStage;
        this.giftCheatMenuOpen = true;
    }

    private boolean isTalk = false;
    private boolean isTalk2 = false;
    private Stage talkCheatMenuStage = null;
    private boolean talkCheatMenuOpen = false;

    private void talk() {
        Stage cheatStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(cheatStage);
        com.badlogic.gdx.scenes.scene2d.ui.Window window =
            new com.badlogic.gdx.scenes.scene2d.ui.Window("Cheat", GameAssetManager.FRIEND_SKIN);
        window.setSize(700, 400);
        window.setMovable(false);
        Table table = new Table();
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();

        Label label = new Label("zzzz", GameAssetManager.FRIEND_SKIN);
        table.add(label).width(520).left();
        if (isTalk2)
            label.setText("Hello sisi");
        table.row();
        window.add(table).expand().fill();

        ImageButton exitButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cheatStage.clear();
                talkCheatMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        // موقعیت وسط صفحه
        window.setPosition(
            (cheatStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
            (cheatStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
        );
        exitButton.setPosition(
            window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
            window.getY() + window.getHeight() - exitButton.getHeight() / 2f
        );

        cheatStage.addActor(window);
        cheatStage.addActor(exitButton);

        this.talkCheatMenuStage = cheatStage;
        this.talkCheatMenuOpen = true;
    }

    private void renderOtherPlayers() {
        if (!isVillage)
            return;
        for (int i = 0; i < 2; i++)
            if (i != ClientApp.getTurnNumber()) {
                stateTime += Gdx.graphics.getDeltaTime();
                Player o = App.getLoggedInUser().getCurrentGame().getPlayers().get(i);
                if(!o.isInVillage())
                    break;
                Pair<Float, Float> pos = o.getPlayerPosition();
                Animation<TextureRegion> currentAnimation = null;

                if (o.getUser().getAvatar().equals(Avatar.HALEY)) {
                    currentAnimation = haleyAnimations.get(0);
                } else if (o.getUser().getAvatar().equals(Avatar.LEAH)) {
                    currentAnimation = leahAnimations.get(0);
                } else if (o.getUser().getAvatar().equals(Avatar.ELLIOTT)) {
                    currentAnimation = elliottAnimations.get(0);
                } else if (o.getUser().getAvatar().equals(Avatar.SEBASTIAN)) {
                    currentAnimation = sebastianAnimations.get(0);
                } else if (o.getUser().getAvatar().equals(Avatar.ROBIN)) {
                    currentAnimation = robinAnimations.get(0);
                } else if (o.getUser().getAvatar().equals(Avatar.SHANE)) {
                    currentAnimation = shaneAnimations.get(0);
                } else {
                    currentAnimation = playerAnimations.get(0);
                }

                TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
                int reaction = o.getReaction();
                if (reaction == 1)
                    batch.draw(GameAssetManager.R_1, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
                if (reaction == 2)
                    batch.draw(GameAssetManager.R_2, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
                if (reaction == 3)
                    batch.draw(GameAssetManager.R_3, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
                if (reaction == 4)
                    batch.draw(GameAssetManager.R_4, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
                if (reaction == 5)
                    batch.draw(GameAssetManager.R_5, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
                if (reaction == 6)
                    batch.draw(GameAssetManager.R_6, pos.first * Main.TILE_SIZE, (pos.second + 1) * Main.TILE_SIZE, 60, 120);
                batch.draw(currentFrame, pos.first * Main.TILE_SIZE, pos.second * Main.TILE_SIZE, 60, 120);

            }
    }

    private Stage cheatMenuStage = null;
    private boolean cheatMenuOpen = false;

    private void cheatWindow() {
        Stage cheatStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(cheatStage);

        com.badlogic.gdx.scenes.scene2d.ui.Window window =
            new com.badlogic.gdx.scenes.scene2d.ui.Window("Cheat", GameAssetManager.SKIN);
        window.setSize(700, 400);
        window.setMovable(false);

        Table table = new Table();
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();

        TextField setEnergy = new TextField("How Much?", GameAssetManager.SKIN);
        table.add(setEnergy).width(520).left();
        table.row();

        TextButton set = new TextButton("Set", GameAssetManager.SKIN);
        set.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getPlayer().setEnergy(Integer.parseInt(setEnergy.getText()));
            }
        });

        table.add(set).width(520).left();
        table.row();


        window.add(table).expand().fill();

        ImageButton exitButton = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cheatStage.clear();
                cheatMenuOpen = false;
                Gdx.input.setInputProcessor(null);
            }
        });

        // موقعیت وسط صفحه
        window.setPosition(
            (cheatStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
            (cheatStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
        );
        exitButton.setPosition(
            window.getX() + window.getWidth() - exitButton.getWidth() / 2f + 16,
            window.getY() + window.getHeight() - exitButton.getHeight() / 2f
        );

        cheatStage.addActor(window);
        cheatStage.addActor(exitButton);

        this.cheatMenuStage = cheatStage;
        this.cheatMenuOpen = true;
    }

    private void Hugeproducts() {
        TextureRegion houseTexture = textures.get("Huge");
        int tileSize = Main.TILE_SIZE;

        int houseTileX = 2;
        int houseTileY = 3;

        float camX = GameModel.getCamera().position.x;
        float camY = GameModel.getCamera().position.y;
        float viewportWidth = GameModel.getCamera().viewportWidth;
        float viewportHeight = GameModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;

        batch.draw(houseTexture, drawX, drawY, tileSize, tileSize);

    }


    // INVENTORY
    private void renderInventory() {
        Player player = game.getPlayer();
        Map<ItemDescriptionId, Pair<Integer, Integer>> inventoryForMap = player.getInventoryForMap();
        int selectedSlot = player.getSelectedSlot(); // Assuming you have this method

        int screenWidth = Gdx.graphics.getWidth();
        int slotSize = Main.TILE_SIZE / 2;
        int numSlots = player.getMaxInventorySize();
        int startX = (screenWidth - numSlots * slotSize) / 2;
        int y = Main.TILE_SIZE / 2;

        for (int i = 0; i < numSlots; i++) {
            int x = startX + i * slotSize;

            batch.draw(textures.get(TileDescriptionId.SLOT.name()), x, y, slotSize, slotSize);

            String slotNum = String.valueOf(i + 1);
            smallFont.draw(batch, slotNum, x + 2, y + slotSize - 2);
        }

        // Highlight selected slot
        if (selectedSlot >= 0 && selectedSlot < numSlots) {
            int highlightX = startX + selectedSlot * slotSize;
            batch.draw(textures.get(TileDescriptionId.HIGHLIGHT.name()), highlightX, y, slotSize, slotSize);
        }

        for (Map.Entry<ItemDescriptionId, Pair<Integer, Integer>> entry : inventoryForMap.entrySet()) {
            ItemDescriptionId id = entry.getKey();
            int quantity = entry.getValue().first;
            int index = entry.getValue().second;

            if (index < 0 || index >= numSlots) continue;

            TextureRegion itemTex = textures.get(id.name());
            if (itemTex != null) {
                int x = startX + index * slotSize;
                batch.draw(itemTex, x, y, slotSize, slotSize);

                // Draw item quantity at bottom-right corner
                String count = String.valueOf(quantity);
                layout.setText(smallFont, count);
                smallFont.draw(batch, count, x + slotSize - layout.width - 2, y + layout.height + 2);
            }
        }
    }

    public Batch getBatch() {
        return batch;
    }


    public Texture getPixel() {
        return pixel;
    }

    public boolean isGreenHouseOkey() {
        return isGreenHouseOkey;
    }

    public void setGreenHouseOkey(boolean greenHouseOkey) {
        isGreenHouseOkey = greenHouseOkey;
    }
}
