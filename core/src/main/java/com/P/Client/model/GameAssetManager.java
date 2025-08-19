package com.P.Client.model;

import com.P.common.model.Objects.Tool;
import com.P.common.model.enums.ToolType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;

import java.awt.image.TileObserver;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    public static final Texture PIXEL;
    public static final BitmapFont MAIN_FONT;

    public static final Skin SKIN = new Skin(Gdx.files.internal("skin/uiskin.json"));
    public static final Skin LABI_SKIN = new Skin(Gdx.files.internal("labiskin/freezing-ui.json"));
    public static final Skin FRIEND_SKIN = new Skin(Gdx.files.internal("friendskin/rainbow-ui.json"));
    public static final Skin RADIO_SKIN = new Skin(Gdx.files.internal("friendskin/rainbow-ui.json"));

    static {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/stardew-valley.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 29;
        parameter.color = Color.FIREBRICK;
        MAIN_FONT = generator.generateFont(parameter);
        generator.dispose();
    }

    private static final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    static {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1);
        pixmap.fill();
        PIXEL = new Texture(pixmap);
        pixmap.dispose();
    }

    // CLOCK
    public static final Texture CLOCK = new Texture("game/clock/clock.png");
    public static final Texture CLOCK_ALL = new Texture("game/clock/fullclock.png");
    public static final TextureRegion CLOCK_MAIN = new TextureRegion(CLOCK_ALL, 0, 0, 72, 59);
    public static final TextureRegion CLOCK_ARROW = new TextureRegion(CLOCK_ALL, 72, 0, 8, 18);
    public static final TextureRegion[] ClOCK_MANNERS = new TextureRegion[12];

    static {
        for (int i = 0; i < 12; i++) {
            ClOCK_MANNERS[i] = new TextureRegion(CLOCK_ALL, 80 + i % 4 * 13, i / 4 * 9, 13, 9);
        }
    }

    public static final Texture CLOCKWITHJOURNAL = new Texture("game/clock/journal.png");
    public static final Texture GOLDDISPLAY = new Texture("game/clock/golddisplay.png");
    public static final Texture WEDDINGDAYHEART = new Texture("game/clock/weddingclock.png");

    // Rain
    public static final TextureRegion[][] RAIN = TextureRegion.split(new Texture("game/tiles/rain.png"), 41, 130);

    // Snow
    public static final Texture SNOW = new Texture("game/tiles/snow.png");

    // Storm
    public static final TextureRegion[][] STORM = TextureRegion.split(new Texture("game/tiles/storm.png"), 192, 384);

    // FEED ANIMAL
    public static final Texture FEED = new Texture("game/feed.png");
    public static final Texture SHEPRED = new Texture("game/Shepherd.png");

    public static final Texture CROW = new Texture("game/Crow.png");
    public static final Texture FISHING = new Texture("game/Fishing.png");
    public static final Texture ENERGY_CHEAT = new Texture("game/energy.png");
    public static final Texture FISHH = new Texture("game/fishh.png");
    public static final Texture NUZ = new Texture("game/nuz.png");
    public static final Texture TALK = new Texture("game/talk.png");
    public static final Texture GIFT = new Texture("game/gift.png");
    public static final Texture RADIO = new Texture("game/radio.png");
    public static final Texture SORT  = new Texture("game/sort.png");
    public static final Texture REACTION = new Texture("game/reaction.png");
    public static final Texture WEDDING = new Texture("game/wedding.png");
    public static final Texture HUG = new Texture("game/hug.png");

    // Reaction
    public static final Texture R_1 = new Texture("game/1.png");
    public static final Texture R_2 = new Texture("game/2.png");
    public static final Texture R_3 = new Texture("game/3.png");
    public static final Texture R_4 = new Texture("game/4.png");
    public static final Texture R_5 = new Texture("game/5.png");
    public static final Texture R_6 = new Texture("game/6.png");


    // Faint
    // Elliott
    private final String character0_idle0 = "game/character/Elliott/faint/1.png";
    private final String character0_idle1 = "game/character/Elliott/faint/2.png";
    private final String character0_idle2 = "game/character/Elliott/faint/3.png";
    private final Texture character0_idle0_tex = new Texture(character0_idle0);
    private final Texture character0_idle1_tex = new Texture(character0_idle1);
    private final Texture character0_idle2_tex = new Texture(character0_idle2);
    private final Animation<TextureRegion> elliott_faint = new Animation<>(
        0.4f,
        new TextureRegion(character0_idle0_tex),
        new TextureRegion(character0_idle1_tex),
        new TextureRegion(character0_idle2_tex)
    );

    public final Animation<Texture> buff1 =new Animation<>( 200f, new Texture("game/heart/max_energy_buff.png"));
    public final Animation<Texture> buff2 =new Animation<>( 200f, new Texture("game/heart/Nauseated.png"));

    public final Animation<Texture> eating =new Animation<>( 0.2f, new Texture("game/heart/one_hearts.png"), new Texture("game/heart/three_hearts.png"), new Texture("game/heart/five_hearts.png"), new Texture("game/heart/seven_hearts.png"), new Texture("game/heart/nine_hearts.png"), new Texture("game/heart/eleven_hearts.png"));
    public final Animation<Texture> plowing=new Animation<>(0.3f, new Texture("game/tiles/soil.png"),new Texture("game/tiles/Flooring_55.png"), new Texture("game/tiles/soil.png"),new Texture("game/tiles/flooring_55.png"));
    public final Animation<Texture> irrigating=new Animation<>(0.3f, new Texture("game/tiles/flooring_09.png"),new Texture("game/tiles/flooring_54.png"), new Texture("game/tiles/flooring_09.png"),new Texture("game/tiles/flooring_54.png"));
    public final Animation<Texture> fertilition=new Animation<>(0.3f, new Texture("game/tiles/1.png"),new Texture("game/tiles/2.png"), new Texture("game/tiles/2.png"),new Texture("game/tiles/1.png"));

    // Haley
    private final String character1_idle0 = "game/character/Haley/faint/1.png";
    private final String character1_idle1 = "game/character/Haley/faint/2.png";
    private final String character1_idle2 = "game/character/Haley/faint/3.png";
    private final Texture character1_idle0_tex = new Texture(character1_idle0);
    private final Texture character1_idle1_tex = new Texture(character1_idle1);
    private final Texture character1_idle2_tex = new Texture(character1_idle2);
    private final Animation<TextureRegion> haley_faint = new Animation<>(
        0.4f,
        new TextureRegion(character1_idle0_tex),
        new TextureRegion(character1_idle1_tex),
        new TextureRegion(character1_idle2_tex)
    );




    // Leah
    private final String character2_idle0 = "game/character/Leah/faint/1.png";
    private final String character2_idle1 = "game/character/Leah/faint/2.png";
    private final String character2_idle2 = "game/character/Leah/faint/3.png";
    private final Texture character2_idle0_tex = new Texture(character2_idle0);
    private final Texture character2_idle1_tex = new Texture(character2_idle1);
    private final Texture character2_idle2_tex = new Texture(character2_idle2);
    private final Animation<TextureRegion> leah_faint = new Animation<>(
        0.4f,
        new TextureRegion(character2_idle0_tex),
        new TextureRegion(character2_idle1_tex),
        new TextureRegion(character2_idle2_tex)
    );

    // Robin
    private final String character3_idle0 = "game/character/Robin/faint/1.png";
    private final String character3_idle1 = "game/character/Robin/faint/2.png";
    private final String character3_idle2 = "game/character/Robin/faint/3.png";
    private final Texture character3_idle0_tex = new Texture(character3_idle0);
    private final Texture character3_idle1_tex = new Texture(character3_idle1);
    private final Texture character3_idle2_tex = new Texture(character3_idle2);
    private final Animation<TextureRegion> robin_faint = new Animation<>(
        0.4f,
        new TextureRegion(character3_idle0_tex),
        new TextureRegion(character3_idle1_tex),
        new TextureRegion(character3_idle2_tex)
    );

    // Sebastian
    private final String character4_idle0 = "game/character/Sebastian/faint/1.png";
    private final String character4_idle1 = "game/character/Sebastian/faint/2.png";
    private final String character4_idle2 = "game/character/Sebastian/faint/3.png";
    private final Texture character4_idle0_tex = new Texture(character4_idle0);
    private final Texture character4_idle1_tex = new Texture(character4_idle1);
    private final Texture character4_idle2_tex = new Texture(character4_idle2);
    private final Animation<TextureRegion> sebastian_faint = new Animation<>(
        0.4f,
        new TextureRegion(character4_idle0_tex),
        new TextureRegion(character4_idle1_tex),
        new TextureRegion(character4_idle2_tex)
    );

    // Shane
    private final String character5_idle0 = "game/character/Shane/faint/1.png";
    private final String character5_idle1 = "game/character/Shane/faint/2.png";
    private final String character5_idle2 = "game/character/Shane/faint/3.png";
    private final Texture character5_idle0_tex = new Texture(character5_idle0);
    private final Texture character5_idle1_tex = new Texture(character5_idle1);
    private final Texture character5_idle2_tex = new Texture(character5_idle2);
    private final Animation<TextureRegion> shane_faint = new Animation<>(
        0.4f,
        new TextureRegion(character5_idle0_tex),
        new TextureRegion(character5_idle1_tex),
        new TextureRegion(character5_idle2_tex)
    );

    // Fishing Pole
    public static final Texture BAMBOOPOLE = new Texture("game/Fishing_Pole/Bamboo_Pole.png");
    public static final Texture FIBERGLASSROD = new Texture("game/Fishing_Pole/Fiberglass_Rod.png");
    public static final Texture IRIDIUMROD = new Texture("game/Fishing_Pole/Iridium_Rod.png");
    public static final Texture TRAININGROD = new Texture("game/Fishing_Pole/Training_Rod.png");

    // Fish
    public static final Texture ANGLER = new Texture("game/Fish/Angler.png");
    public static final Texture BLUE_DISCUS = new Texture("game/Fish/Blue_Discus.png");
    public static final Texture CRIMSONFISH = new Texture("game/Fish/Crimsonfish.png");
    public static final Texture DORADO = new Texture("game/Fish/Dorado.png");
    public static final Texture FLOUNDER = new Texture("game/Fish/Flounder.png");
    public static final Texture GHOSTFISH = new Texture("game/Fish/Ghostfish.png");
    public static final Texture GLACIERFISH = new Texture("game/Fish/Glacierfish.png");
    public static final Texture HERRING = new Texture("game/Fish/Herring.png");
    public static final Texture LEGEND = new Texture("game/Fish/Legend.png");
    public static final Texture LIONFISH = new Texture("game/Fish/Lionfish.png");
    public static final Texture MIDNIGHT_CARP = new Texture("game/Fish/Midnight_Carp.png");
    public static final Texture PERCH = new Texture("game/Fish/Perch.png");
    public static final Texture RAINBOW_TROUT = new Texture("game/Fish/Rainbow_Trout.png");
    public static final Texture SALMON = new Texture("game/Fish/Salmon.png");
    public static final Texture SARDINE = new Texture("game/Fish/Sardine.png");
    public static final Texture SHAD = new Texture("game/Fish/Shad.png");
    public static final Texture SQUID = new Texture("game/Fish/Squid.png");
    public static final Texture SUNFISH = new Texture("game/Fish/Sunfish.png");
    public static final Texture TILAPIA = new Texture("game/Fish/Tilapia.png");
    public static final Texture TUNA = new Texture("game/Fish/Tuna.png");

    // Animals
    public static final Texture CHICKEN = new Texture("game/animals/White_Chicken.png");
    public static final Texture DUCK = new Texture("game/animals/Duck.png");
    public static final Texture RABBIT = new Texture("game/animals/Rabbit.png");
    public static final Texture DINOSAUR = new Texture("game/animals/Dinosaur.png");
    public static final Texture COW = new Texture("game/animals/White_Cow.png");
    public static final Texture GOAT = new Texture("game/animals/Goat.png");
    public static final Texture SHEEP = new Texture("game/animals/Sheep.png");
    public static final Texture PIG = new Texture("game/animals/Pig.png");

    public static final Texture MINI_GAME_BACKGROUND = new Texture("game/background.png");
    public static final Texture MINI_GAME_BAR = new Texture("game/barMini.png");

    // FriendShip
    public static final Texture FRIENDSHIP_ICON = new Texture("game/Friendship.png");

    // Crown
    public static final Texture CROWN = new Texture("game/crown.png");

    public static final Texture SECRETHEART = new Texture("game/Heart/Secret_Heart.png");

    public static final Texture EXIT_BUTTON = new Texture("game/exit.png");

    // Barn
    public static final Texture BARN = new Texture("game/Barn/Barn.png");
    public static final Texture BIG_BARN = new Texture("game/Barn/Big_Barn.png");
    public static final Texture BIG_COOP = new Texture("game/Barn/Big_Coop.png");
    public static final Texture COOP = new Texture("game/Barn/Coop.png");
    public static final Texture DELUXE_BARN = new Texture("game/Barn/Deluxe_Barn.png");
    public static final Texture DELUXE_COOP = new Texture("game/Barn/Deluxe_Coop.png");


    // Ingredients Enum
    public static final Texture WOOD = new Texture("game/Ingredient/Wood.png");
    public static final Texture STONE = new Texture("game/Ingredient/Stone.png");
    public static final Texture HAY = new Texture("game/Ingredient/Hay.png");
    public static final Texture EGG = new Texture("game/Ingredient/Egg.png");
    public static final Texture DUCK_EGG = new Texture("game/Ingredient/Duck_Egg.png");
    public static final Texture DINOSAUR_EGG = new Texture("game/Ingredient/Dinosaur_Egg.png");
    public static final Texture MILK = new Texture("game/Ingredient/Milk.png");
    public static final Texture GOAT_MILK = new Texture("game/Ingredient/Goat_Milk.png");
    public static final Texture DUCK_FEATHER = new Texture("game/Ingredient/Duck_Feather.png");
    public static final Texture WOOL = new Texture("game/Ingredient/Wool.png");
    public static final Texture RABBIT_PIE = new Texture("game/Ingredient/Rabbit_Pie.png");
    public static final Texture TRUFFLE = new Texture("game/Ingredient/Truffle.png");

    public static final Texture RICE = new Texture("game/Ingredient/Rice.png");
    public static final Texture SUGAR = new Texture("game/Ingredient/Sugar.png");
    public static final Texture WHEAT_FLOUR = new Texture("game/Ingredient/Wheat_Flour.png");
    public static final Texture OIL = new Texture("game/Ingredient/Oil.png");
    public static final Texture VINEGAR = new Texture("game/Ingredient/Vinegar.png");

    public static final Texture SPEED_GRO = new Texture("game/Ingredient/Speed-Gro.png");
    public static final Texture BASIC_RETAINING_SOIL = new Texture("game/Ingredient/Basic_Retaining_Soil.png");
    public static final Texture QUALITY_RETAINING_SOIL = new Texture("game/Ingredient/Quality_Retaining_Soil.png");
    public static final Texture DELUXE_RETAINING_SOIL = new Texture("game/Ingredient/Deluxe_Retaining_Soil.png");
    public static final Texture BOUQUET = new Texture("game/Ingredient/Bouquet.png");
    public static final Texture WEDDING_RING = new Texture("game/Ingredient/Wedding_Ring.png");

    public static final Texture JOJA_COLA = new Texture("game/Ingredient/Joja_Cola.png");

    public static final Texture BEER = new Texture("game/Ingredient/Beer.png");
    public static final Texture SALAD = new Texture("game/Ingredient/Salad.png");
    public static final Texture BREAD = new Texture("game/Ingredient/Bread.png");
    public static final Texture SPAGHETTI = new Texture("game/Ingredient/Spaghetti.png");
    public static final Texture PIZZA = new Texture("game/Ingredient/Pizza.png");
    public static final Texture COFFEE = new Texture("game/Ingredient/Coffee.png");
    public static final Texture TROUT_SOUP = new Texture("game/Ingredient/Trout_Soup.png");

    public static final Texture COOPER_ORE = new Texture("game/Ingredient/Copper_Ore.png");
    public static final Texture IRON_ORE = new Texture("game/Ingredient/Iron_Ore.png");
    public static final Texture GOLD_ORE = new Texture("game/Ingredient/Gold_Ore.png");
    public static final Texture IRIDIUM_ORE = new Texture("game/Ingredient/Iridium_Ore.png");

    public static final Texture BLUE_JAZZ = new Texture("game/Ingredient/Blue_Jazz.png");
    public static final Texture CARROT = new Texture("game/Ingredient/Carrot.png");
    public static final Texture CAULIFLOWER = new Texture("game/Ingredient/Cauliflower.png");
    public static final Texture COFFEE_BEAN = new Texture("game/Ingredient/Coffee_Bean.png");
    public static final Texture GARLIC = new Texture("game/Ingredient/Garlic.png");
    public static final Texture GREEN_BEAN = new Texture("game/Ingredient/Green_Bean.png");
    public static final Texture KALE = new Texture("game/Ingredient/Kale.png");
    public static final Texture PARSNIP = new Texture("game/Ingredient/Parsnip.png");
    public static final Texture POTATO = new Texture("game/Ingredient/Potato.png");
    public static final Texture RHUBARB = new Texture("game/Ingredient/Rhubarb.png");
    public static final Texture STRAWBERRY = new Texture("game/Ingredient/Strawberry.png");
    public static final Texture TULIP = new Texture("game/Ingredient/Tulip.png");
    public static final Texture UNMILLED_RICE = new Texture("game/Ingredient/Unmilled_Rice.png");
    public static final Texture BLUEBERRY = new Texture("game/Ingredient/Blueberry.png");
    public static final Texture CORN = new Texture("game/Ingredient/Corn.png");
    public static final Texture HOPS = new Texture("game/Ingredient/Hops.png");
    public static final Texture HOT_PEPPER = new Texture("game/Ingredient/Hot_Pepper.png");
    public static final Texture MELON = new Texture("game/Ingredient/Melon.png");
    public static final Texture POPPY = new Texture("game/Ingredient/Poppy.png");
    public static final Texture RADISH = new Texture("game/Ingredient/Radish.png");
    public static final Texture RED_CABBAGE = new Texture("game/Ingredient/Red_Cabbage.png");
    public static final Texture STARFRUIT = new Texture("game/Ingredient/Starfruit.png");
    public static final Texture SUMMER_SPANGLE = new Texture("game/Ingredient/Summer_Spangle.png");
    public static final Texture SUMMER_SQUASH = new Texture("game/Ingredient/Summer_Squash.png");
    public static final Texture SUNFLOWER = new Texture("game/Ingredient/Sunflower.png");
    public static final Texture TOMATO = new Texture("game/Ingredient/Tomato.png");
    public static final Texture WHEAT = new Texture("game/Ingredient/Wheat.png");
    public static final Texture AMARANTH = new Texture("game/Ingredient/Amaranth.png");
    public static final Texture ARTICHOKE = new Texture("game/Ingredient/Artichoke.png");
    public static final Texture BEET = new Texture("game/Ingredient/Beet.png");
    public static final Texture BOK_CHOY = new Texture("game/Ingredient/Bok_Choy.png");
    public static final Texture BROCCOLI = new Texture("game/Ingredient/Broccoli.png");
    public static final Texture CRANBERRIES = new Texture("game/Ingredient/Cranberries.png");
    public static final Texture EGGPLANT = new Texture("game/Ingredient/Eggplant.png");
    public static final Texture FAIRY_ROSE = new Texture("game/Ingredient/Fairy_Rose.png");
    public static final Texture GRAPE = new Texture("game/Ingredient/Grape.png");
    public static final Texture PUMPKIN = new Texture("game/Ingredient/Pumpkin.png");
    public static final Texture YAM = new Texture("game/Ingredient/Yam.png");
    public static final Texture SWEET_GEM_BERRY = new Texture("game/Ingredient/Sweet_Gem_Berry.png");
    public static final Texture POWDERMELON = new Texture("game/Ingredient/Powdermelon.png");
    public static final Texture ANCIENT_FRUIT = new Texture("game/Ingredient/Ancient_Fruit.png");

    public static final Texture COMMON_MUSHROOM = new Texture("game/Ingredient/Common_Mushroom.png");
    public static final Texture DAFFODIL = new Texture("game/Ingredient/Daffodil.png");
    public static final Texture DANDELION = new Texture("game/Ingredient/Dandelion.png");
    public static final Texture LEEK = new Texture("game/Ingredient/Leek.png");
    public static final Texture MOREL = new Texture("game/Ingredient/Morel.png");
    public static final Texture SALMONBERRY = new Texture("game/Ingredient/salmonberry.png");
    public static final Texture SPRING_ONION = new Texture("game/Ingredient/spring_onion.png");
    public static final Texture WILD_HORSERADISH = new Texture("game/Ingredient/wild_horseradish.png");
    public static final Texture FIDDLEHEAD_FERN = new Texture("game/Ingredient/fiddlehead_fern.png");
    public static final Texture RED_MUSHROOM = new Texture("game/Ingredient/red_mushroom.png");
    public static final Texture SPICE_BERRY = new Texture("game/Ingredient/spice_berry.png");
    public static final Texture SWEET_PEA = new Texture("game/Ingredient/sweet_pea.png");
    public static final Texture BLACKBERRY = new Texture("game/Ingredient/blackberry.png");
    public static final Texture CHANTERELLE = new Texture("game/Ingredient/chanterelle.png");
    public static final Texture HAZELNUT = new Texture("game/Ingredient/hazelnut.png");
    public static final Texture PURPLE_MUSHROOM = new Texture("game/Ingredient/purple_mushroom.png");
    public static final Texture WILD_PLUM = new Texture("game/Ingredient/wild_plum.png");
    public static final Texture CROCUS = new Texture("game/Ingredient/crocus.png");
    public static final Texture CRYSTAL_FRUIT = new Texture("game/Ingredient/crystal_fruit.png");
    public static final Texture HOLLY = new Texture("game/Ingredient/holly.png");
    public static final Texture SNOW_YAM = new Texture("game/Ingredient/snow_yam.png");
    public static final Texture WINTER_ROOT = new Texture("game/Ingredient/winter_root.png");

    public static final Texture APRICOT = new Texture("game/Ingredient/apricot.png");
    public static final Texture CHERRY = new Texture("game/Ingredient/cherry.png");
    public static final Texture BANANA = new Texture("game/Ingredient/banana.png");
    public static final Texture MANGO = new Texture("game/Ingredient/mango.png");
    public static final Texture ORANGE = new Texture("game/Ingredient/orange.png");
    public static final Texture PEACH = new Texture("game/Ingredient/peach.png");
    public static final Texture APPLE = new Texture("game/Ingredient/apple.png");
    public static final Texture POMEGRANATE = new Texture("game/Ingredient/pomegranate.png");
    public static final Texture OAK_RESIN = new Texture("game/Ingredient/oak_resin.png");
    public static final Texture MAPLE_SYRUP = new Texture("game/Ingredient/maple_syrup.png");
    public static final Texture PINE_TAR = new Texture("game/Ingredient/pine_tar.png");
    public static final Texture SAP = new Texture("game/Ingredient/sap.png");
    public static final Texture MYSTIC_SYRUP = new Texture("game/Ingredient/mystic_syrup.png");

    public static final Texture QUARTZ = new Texture("game/Ingredient/quartz.png");
    public static final Texture EARTH_CRYSTAL = new Texture("game/Ingredient/earth_crystal.png");
    public static final Texture FROZEN_TEAR = new Texture("game/Ingredient/frozen_tear.png");
    public static final Texture FIRE_QUARTZ = new Texture("game/Ingredient/fire_quartz.png");
    public static final Texture EMERALD = new Texture("game/Ingredient/emerald.png");
    public static final Texture AQUAMARINE = new Texture("game/Ingredient/aquamarine.png");
    public static final Texture RUBY = new Texture("game/Ingredient/ruby.png");
    public static final Texture AMETHYST = new Texture("game/Ingredient/amethyst.png");
    public static final Texture TOPAZ = new Texture("game/Ingredient/topaz.png");
    public static final Texture JADE = new Texture("game/Ingredient/jade.png");
    public static final Texture DIAMOND = new Texture("game/Ingredient/diamond.png");
    public static final Texture PRISMATIC_SHARD = new Texture("game/Ingredient/prismatic_shard.png");
    public static final Texture COPPER = new Texture("game/Ingredient/copper_bar.png");
    public static final Texture IRON = new Texture("game/Ingredient/iron_bar.png");
    public static final Texture GOLD = new Texture("game/Ingredient/gold_bar.png");
    public static final Texture IRIDIUM = new Texture("game/Ingredient/iridium_bar.png");
    public static final Texture COAL = new Texture("game/Ingredient/coal.png");

    public static final Texture CHERRY_BOMB = new Texture("game/Ingredient/cherry_bomb.png");
    public static final Texture BOMB = new Texture("game/Ingredient/bomb.png");
    public static final Texture MEGA_BOMB = new Texture("game/Ingredient/mega_bomb.png");
    public static final Texture SPRINKLER = new Texture("game/Ingredient/sprinkler.png");
    public static final Texture QUALITY_SPRINKLER = new Texture("game/Ingredient/quality_sprinkler.png");
    public static final Texture IRIDIUM_SPRINKLER = new Texture("game/Ingredient/iridium_sprinkler.png");
    public static final Texture CHARCOAL_KLIN = new Texture("game/Ingredient/charcoal_kiln.png");
    public static final Texture FURNACE = new Texture("game/Ingredient/furnace.png");
    public static final Texture SCARECROW = new Texture("game/Ingredient/scarecrow.png");
    public static final Texture DELUXE_SCARECROW = new Texture("game/Ingredient/deluxe_scarecrow.png");
    public static final Texture BEE_HOUSE = new Texture("game/Ingredient/bee_house.png");
    public static final Texture CHEESE_PRESS = new Texture("game/Ingredient/cheese_press.png");
    public static final Texture KEG = new Texture("game/Ingredient/keg.png");
    public static final Texture LOOM = new Texture("game/Ingredient/loom.png");
    public static final Texture MAYONNAISE_MACHINE = new Texture("game/Ingredient/mayonnaise_machine.png");
    public static final Texture OIL_MAKER = new Texture("game/Ingredient/oil_maker.png");
    public static final Texture PRESERVES_JAR = new Texture("game/Ingredient/preserves_jar.png");
    public static final Texture DEHYDRATOR = new Texture("game/Ingredient/dehydrator.png");
    public static final Texture GRASS_STARTER = new Texture("game/Ingredient/grass_starter.png");
    public static final Texture FISH_SMOKER = new Texture("game/Ingredient/fish_smoker.png");

    public static final Texture HONEY = new Texture("game/Ingredient/honey.png");
    public static final Texture CHEESE = new Texture("game/Ingredient/cheese.png");
    public static final Texture GOAT_CHEESE = new Texture("game/Ingredient/goat_cheese.png");
    public static final Texture JUICE = new Texture("game/Ingredient/juice.png");
    public static final Texture MAED = new Texture("game/Ingredient/juice.png");
    public static final Texture PALE_ALE = new Texture("game/Ingredient/pale_ale.png");
    public static final Texture WINE = new Texture("game/Ingredient/wine.png");
    public static final Texture DRIED_MUSHROOM = new Texture("game/Ingredient/dried_mushrooms.png");
    public static final Texture DRIED_FRUIT = new Texture("game/Ingredient/dried_fruit.png");
    public static final Texture RAISINS = new Texture("game/Ingredient/raisins.png");
    public static final Texture CLOTH = new Texture("game/Ingredient/cloth.png");

    public static final Texture LION_FISH = new Texture("game/Ingredient/lionfish.png");

    public static final Texture GHOST_FISH = new Texture("game/Ingredient/ghostfish.png");
    public static final Texture GLACIER_FISH = new Texture("game/Ingredient/glacierfish.png");

    public static final Texture PUMPKIN_PIE = new Texture("game/Ingredient/pumpkin_pie.png");
    public static final Texture HASH_BROWNS = new Texture("game/Ingredient/hashbrowns.png");
    public static final Texture FRIED_EGG = new Texture("game/Ingredient/fried_egg.png");
    public static final Texture BAKED_FISH = new Texture("game/Ingredient/baked_fish.png");
    public static final Texture OMELET = new Texture("game/Ingredient/omelet.png");
    public static final Texture TORTILLA = new Texture("game/Ingredient/tortilla.png");
    public static final Texture MAKI_ROLL = new Texture("game/Ingredient/maki_roll.png");
    public static final Texture TRIPLE_SHOT_ESPRESSO = new Texture("game/Ingredient/triple_shot_espresso.png");
    public static final Texture COOKIE = new Texture("game/Ingredient/cookie.png");
    public static final Texture PANCAKES = new Texture("game/Ingredient/pancakes.png");
    public static final Texture FRUIT_SALAD = new Texture("game/Ingredient/fruit_salad.png");
    public static final Texture RED_PLATE = new Texture("game/Ingredient/red_plate.png");
    public static final Texture SALMON_DINNER = new Texture("game/Ingredient/salmon_dinner.png");
    public static final Texture VEGETABLE_MEDLEY = new Texture("game/Ingredient/vegetable_medley.png");
    public static final Texture FARMERS_LUNCH = new Texture("game/Ingredient/farmers_lunch.png");
    public static final Texture SURVIVAL_BURGER = new Texture("game/Ingredient/survival_burger.png");
    public static final Texture SEA_DISH = new Texture("game/Ingredient/dish_o_the_sea.png");
    public static final Texture SEAFORM_PUDDING = new Texture("game/Ingredient/seafoam_pudding.png");
    public static final Texture MINERS_TREAT = new Texture("game/Ingredient/miners_treat.png");
    public static final Texture FIBER = new Texture("game/Ingredient/fiber.png");
    public static final Texture WATER_FERTILITY = new Texture("game/Ingredient/Basic_fertilizer.png");
    public static final Texture SPEED_FERTILITY = new Texture("game/Ingredient/Basic_fertilizer.png");

    public static final Texture ApricotSapling = new Texture("game/Ingredient/apricot_sapling.png");
    public static final Texture CherrySapling = new Texture("game/Ingredient/cherry_sapling.png");
    public static final Texture BananaSapling = new Texture("game/Ingredient/banana_sapling.png");
    public static final Texture MangoSapling = new Texture("game/Ingredient/mango_sapling.png");
    public static final Texture OrangeSapling = new Texture("game/Ingredient/orange_sapling.png");
    public static final Texture PeachSapling = new Texture("game/Ingredient/peach_sapling.png");
    public static final Texture AppleSapling = new Texture("game/Ingredient/apple_sapling.png");
    public static final Texture PomegranateSapling = new Texture("game/Ingredient/pomegranate_sapling.png");
    public static final Texture Acorns = new Texture("game/Ingredient/acorn.png");
    public static final Texture MapleSeeds = new Texture("game/Ingredient/maple_seed.png");
    public static final Texture PineCones = new Texture("game/Ingredient/pine_cone.png");
    public static final Texture MahoganySeeds = new Texture("game/Ingredient/mahogany_seed.png");
    public static final Texture MushroomTreeSeeds = new Texture("game/Ingredient/mushroom_tree_seed.png");
    public static final Texture MysticTreeSeeds = new Texture("game/Ingredient/mystic_tree_seed.png");
    public static final Texture JazzSeeds = new Texture("game/Ingredient/jazz_seeds.png");
    public static final Texture CarrotSeeds = new Texture("game/Ingredient/carrot_seeds.png");
    public static final Texture CauliflowerSeeds = new Texture("game/Ingredient/cauliflower_seeds.png");
    public static final Texture CoffeeBean = new Texture("game/Ingredient/coffee_bean.png");
    public static final Texture GarlicSeeds = new Texture("game/Ingredient/garlic_seeds.png");
    public static final Texture BeanStarter = new Texture("game/Ingredient/bean_starter.png");
    public static final Texture KaleSeeds = new Texture("game/Ingredient/kale_seeds.png");
    public static final Texture ParsnipSeeds = new Texture("game/Ingredient/parsnip_seeds.png");
    public static final Texture PotatoSeeds = new Texture("game/Ingredient/potato_seeds.png");
    public static final Texture RhubarbSeeds = new Texture("game/Ingredient/rhubarb_seeds.png");
    public static final Texture StrawberrySeeds = new Texture("game/Ingredient/strawberry_seeds.png");
    public static final Texture TulipBulb = new Texture("game/Ingredient/tulip_bulb.png");
    public static final Texture RiceShoot = new Texture("game/Ingredient/rice_shoot.png");

    public static final Texture BlueberrySeeds = new Texture("game/Ingredient/blueberry_seeds.png");
    public static final Texture CornSeeds = new Texture("game/Ingredient/corn_seeds.png");
    public static final Texture HopsStarter = new Texture("game/Ingredient/hops_starter.png");
    public static final Texture PepperSeeds = new Texture("game/Ingredient/pepper_seeds.png");
    public static final Texture MelonSeeds = new Texture("game/Ingredient/melon_seeds.png");
    public static final Texture PoppySeeds = new Texture("game/Ingredient/poppy_seeds.png");
    public static final Texture RadishSeeds = new Texture("game/Ingredient/radish_seeds.png");
    public static final Texture RedCabbageSeeds = new Texture("game/Ingredient/red_cabbage_seeds.png");
    public static final Texture StarfruitSeeds = new Texture("game/Ingredient/starfruit_seeds.png");
    public static final Texture SpangleSeeds = new Texture("game/Ingredient/spangle_seeds.png");
    public static final Texture SummerSquashSeeds = new Texture("game/Ingredient/summer_squash_seeds.png");
    public static final Texture SunflowerSeeds = new Texture("game/Ingredient/sunflower_seeds.png");
    public static final Texture TomatoSeeds = new Texture("game/Ingredient/tomato_seeds.png");
    public static final Texture WheatSeeds = new Texture("game/Ingredient/wheat_seeds.png");

    public static final Texture AmaranthSeeds = new Texture("game/Ingredient/amaranth_seeds.png");
    public static final Texture ArtichokeSeeds = new Texture("game/Ingredient/artichoke_seeds.png");
    public static final Texture BeetSeeds = new Texture("game/Ingredient/beet_seeds.png");
    public static final Texture BokChoySeeds = new Texture("game/Ingredient/bok_choy_seeds.png");
    public static final Texture BroccoliSeeds = new Texture("game/Ingredient/broccoli_seeds.png");
    public static final Texture CranberrySeeds = new Texture("game/Ingredient/cranberry_seeds.png");
    public static final Texture EggplantSeeds = new Texture("game/Ingredient/eggplant_seeds.png");
    public static final Texture FairySeeds = new Texture("game/Ingredient/fairy_seeds.png");
    public static final Texture GrapeStarter = new Texture("game/Ingredient/grape_starter.png");
    public static final Texture PumpkinSeeds = new Texture("game/Ingredient/pumpkin_seeds.png");
    public static final Texture YamSeeds = new Texture("game/Ingredient/yam_seeds.png");
    public static final Texture RareSeed = new Texture("game/Ingredient/rare_seed.png");

    public static final Texture PowdermelonSeeds = new Texture("game/Ingredient/powdermelon_seeds.png");

    public static final Texture AncientSeeds = new Texture("game/Ingredient/ancient_seeds.png");
    public static final Texture MixedSeeds = new Texture("game/Ingredient/mixed_seeds.png");
    public static final Texture Dark = new Texture("game/Ingredient/dark.jpg");
    public static final Texture trashcan=new Texture("game/Ingredient/trash_can_steel.png");


    public static final Texture axe = new Texture("game/Tools/axe.png");
    public static final Texture hoe = new Texture("game/Tools/hoe.png");
    public static final Texture wateringCan = new Texture("game/Tools/watering_can.png");
    public static final Texture scythe = new Texture("game/Tools/scythe.png");
    public static final Texture pickaxe = new Texture("game/Tools/pickaxe.png");
    public static final Texture milkingCan = new Texture("game/Tools/milkingCan.png");
    public static final Texture trashCan = new Texture("game/Tools/trash_can_gold.png");
    public static final Texture scissors = new Texture("game/Tools/shears.png");

    public static final Texture mineSkill = new Texture("game/Skill/mining_Skill_icon.png");
    public static final Texture farmSkill = new Texture("game/Skill/farming_skill_icon.png");
    public static final Texture fishSkill = new Texture("game/Skill/fishing_Skill_icon.png");
    public static final Texture forageSkill = new Texture("game/Skill/foraging_Skill_icon.png");


    //Trees
    public static final Texture apTree1 = new Texture("game/Ingredient/apricot_stage_1.png");
    public static final Texture apTree2 = new Texture("game/Ingredient/apricot_stage_2.png");
    public static final Texture apTree3 = new Texture("game/Ingredient/apricot_stage_3.png");
    public static final Texture apTree4 = new Texture("game/Ingredient/apricot_stage_4.png");
    public static final Texture apTree5 = new Texture("game/Ingredient/apricot_stage_5.png");
    public static final Texture[] apricotStages = new Texture[]{
        apTree1, apTree2, apTree3, apTree4, apTree5
    };
    public static final Texture[] cherryStages = new Texture[]{
        new Texture("game/Ingredient/cherry_stage_1.png"),
        new Texture("game/Ingredient/cherry_stage_2.png"),
        new Texture("game/Ingredient/cherry_stage_3.png"),
        new Texture("game/Ingredient/cherry_stage_4.png"),
        new Texture("game/Ingredient/cherry_stage_5_fruit.png")
    };
    public static final Texture[] bananaStages = new Texture[]{
        new Texture("game/Ingredient/banana_stage_1.png"),
        new Texture("game/Ingredient/banana_stage_2.png"),
        new Texture("game/Ingredient/banana_stage_3.png"),
        new Texture("game/Ingredient/banana_stage_4.png"),
        new Texture("game/Ingredient/banana_stage_5_fruit.png")
    };
    public static final Texture[] mangoStage = new Texture[]{
        new Texture("game/Ingredient/mango_stage_1.png"),
        new Texture("game/Ingredient/mango_stage_2.png"),
        new Texture("game/Ingredient/mango_stage_3.png"),
        new Texture("game/Ingredient/mango_stage_4.png"),
        new Texture("game/Ingredient/mango_stage_5_fruit.png")
    };
    public static final Texture[] orangeStage = new Texture[]{
        new Texture("game/Ingredient/orange_stage_1.png"),
        new Texture("game/Ingredient/orange_stage_2.png"),
        new Texture("game/Ingredient/orange_stage_3.png"),
        new Texture("game/Ingredient/orange_stage_4.png"),
        new Texture("game/Ingredient/orange_stage_5_fruit.png")
    };
    public static final Texture[] peachStage = new Texture[]{
        new Texture("game/Ingredient/peach_stage_1.png"),
        new Texture("game/Ingredient/peach_stage_2.png"),
        new Texture("game/Ingredient/peach_stage_3.png"),
        new Texture("game/Ingredient/peach_stage_4.png"),
        new Texture("game/Ingredient/peach_stage_5_fruit.png")
    };
    public static final Texture[] appleStage = new Texture[]{
        new Texture("game/Ingredient/apple_stage_1.png"),
        new Texture("game/Ingredient/apple_stage_2.png"),
        new Texture("game/Ingredient/apple_stage_3.png"),
        new Texture("game/Ingredient/apple_stage_4.png"),
        new Texture("game/Ingredient/apple_stage_5_fruit.png")
    };
    public static final Texture[] pomegranateStage = new Texture[]{
        new Texture("game/Ingredient/pomegranate_stage_1.png"),
        new Texture("game/Ingredient/pomegranate_stage_2.png"),
        new Texture("game/Ingredient/pomegranate_stage_3.png"),
        new Texture("game/Ingredient/pomegranate_stage_4.png"),
        new Texture("game/Ingredient/pomegranate_stage_5_fruit.png")
    };
    public static final Texture[] oakStage = new Texture[]{
        new Texture("game/Ingredient/oak_stage_1.png"),
        new Texture("game/Ingredient/oak_stage_2.png"),
        new Texture("game/Ingredient/oak_stage_3.png"),
        new Texture("game/Ingredient/oak_stage_4.png")
    };
    public static final Texture[] mapleStage = new Texture[]{
        new Texture("game/Ingredient/maple_stage_1.png"),
        new Texture("game/Ingredient/maple_stage_2.png"),
        new Texture("game/Ingredient/maple_stage_3.png"),
        new Texture("game/Ingredient/maple_stage_4.png")
    };
    public static final Texture[] pineStage = new Texture[]{
        new Texture("game/Ingredient/pine_stage_1.png"),
        new Texture("game/Ingredient/pine_stage_2.png"),
        new Texture("game/Ingredient/pine_stage_3.png"),
        new Texture("game/Ingredient/pine_stage_4.png")
    };
    public static final Texture[] mahoganyStage = new Texture[]{
        new Texture("game/Ingredient/mahogany_stage_1.png"),
        new Texture("game/Ingredient/mahogany_stage_2.png"),
        new Texture("game/Ingredient/mahogany_stage_3.png"),
        new Texture("game/Ingredient/mahogany_stage_4.png")
    };
    public static final Texture[] mushroomStage = new Texture[]{
        new Texture("game/Ingredient/mushroom_stage_1.png"),
        new Texture("game/Ingredient/mushroom_stage_2.png"),
        new Texture("game/Ingredient/mushroom_stage_3.png"),
        new Texture("game/Ingredient/mushroom_stage_4.png")
    };
    public static final Texture[] mysticStage = new Texture[]{
        new Texture("game/Ingredient/mystic_stage_1.png"),
        new Texture("game/Ingredient/mystic_stage_2.png"),
        new Texture("game/Ingredient/mystic_stage_3.png"),
        new Texture("game/Ingredient/mystic_stage_4.png")
    };
    public static final Texture[] carrotStage = new Texture[]{
        new Texture("game/Ingredient/carrot_stage_1.png"),
        new Texture("game/Ingredient/carrot_stage_2.png"),
        new Texture("game/Ingredient/carrot_stage_3.png"),
        new Texture("game/Ingredient/carrot_stage_4.png")
    };

    public static final Texture[] jazzStage = new Texture[]{
        new Texture("game/Ingredient/Blue_jazz_stage_2.png"),
        new Texture("game/Ingredient/Blue_jazz_stage_3.png"),
        new Texture("game/Ingredient/Blue_jazz_stage_4.png"),
        new Texture("game/Ingredient/Blue_jazz_stage_5.png")
    };





    public static Texture getTexture(Tool tool) {
        ToolType toolType = tool.getToolType();
        Texture tex = null;
        switch (toolType) {
            case WateringCan -> tex = wateringCan;
            case MilkingCan -> tex = milkingCan;
            case FishingRod -> tex = FIBERGLASSROD;
            case TrashCan -> tex = trashCan;
            case Scissors -> tex = scissors;
            case Pickaxe -> tex = pickaxe;
            case Scythe -> tex = scythe;
            case Hoe -> tex = hoe;
            case Axe -> tex = axe;
        }
        return tex;
    }


    private GameAssetManager() {

    }

    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }


    public static void setGameAssetManager(GameAssetManager gameAssetManager) {
        GameAssetManager.gameAssetManager = gameAssetManager;
    }

    public static Skin getSkin() {
        return skin;
    }


    public Texture getCharacter3_idle0_tex() {
        return character3_idle0_tex;
    }

    public String getCharacter0_idle0() {
        return character0_idle0;
    }

    public String getCharacter0_idle1() {
        return character0_idle1;
    }

    public String getCharacter0_idle2() {
        return character0_idle2;
    }

    public Texture getCharacter0_idle0_tex() {
        return character0_idle0_tex;
    }

    public Texture getCharacter0_idle1_tex() {
        return character0_idle1_tex;
    }

    public Texture getCharacter0_idle2_tex() {
        return character0_idle2_tex;
    }

    public Animation<TextureRegion> getElliott_faint() {
        return elliott_faint;
    }

    public String getCharacter1_idle0() {
        return character1_idle0;
    }

    public String getCharacter1_idle1() {
        return character1_idle1;
    }

    public String getCharacter1_idle2() {
        return character1_idle2;
    }

    public Texture getCharacter1_idle0_tex() {
        return character1_idle0_tex;
    }

    public Texture getCharacter1_idle1_tex() {
        return character1_idle1_tex;
    }

    public Texture getCharacter1_idle2_tex() {
        return character1_idle2_tex;
    }

    public Animation<TextureRegion> getHaley_faint() {
        return haley_faint;
    }

    public String getCharacter2_idle0() {
        return character2_idle0;
    }

    public String getCharacter2_idle1() {
        return character2_idle1;
    }

    public String getCharacter2_idle2() {
        return character2_idle2;
    }

    public Texture getCharacter2_idle0_tex() {
        return character2_idle0_tex;
    }

    public Texture getCharacter2_idle1_tex() {
        return character2_idle1_tex;
    }

    public Texture getCharacter2_idle2_tex() {
        return character2_idle2_tex;
    }

    public Animation<TextureRegion> getLeah_faint() {
        return leah_faint;
    }

    public String getCharacter3_idle0() {
        return character3_idle0;
    }

    public String getCharacter3_idle1() {
        return character3_idle1;
    }

    public String getCharacter3_idle2() {
        return character3_idle2;
    }

    public Texture getCharacter3_idle1_tex() {
        return character3_idle1_tex;
    }

    public Texture getCharacter3_idle2_tex() {
        return character3_idle2_tex;
    }

    public Animation<TextureRegion> getRobin_faint() {
        return robin_faint;
    }

    public String getCharacter4_idle0() {
        return character4_idle0;
    }

    public String getCharacter4_idle1() {
        return character4_idle1;
    }

    public String getCharacter4_idle2() {
        return character4_idle2;
    }

    public Texture getCharacter4_idle0_tex() {
        return character4_idle0_tex;
    }

    public Texture getCharacter4_idle1_tex() {
        return character4_idle1_tex;
    }

    public Texture getCharacter4_idle2_tex() {
        return character4_idle2_tex;
    }

    public Animation<TextureRegion> getSebastian_faint() {
        return sebastian_faint;
    }

    public String getCharacter5_idle0() {
        return character5_idle0;
    }

    public String getCharacter5_idle1() {
        return character5_idle1;
    }

    public String getCharacter5_idle2() {
        return character5_idle2;
    }

    public Texture getCharacter5_idle0_tex() {
        return character5_idle0_tex;
    }

    public Texture getCharacter5_idle1_tex() {
        return character5_idle1_tex;
    }

    public Texture getCharacter5_idle2_tex() {
        return character5_idle2_tex;
    }

    public Animation<TextureRegion> getShane_faint() {
        return shane_faint;
    }
}
