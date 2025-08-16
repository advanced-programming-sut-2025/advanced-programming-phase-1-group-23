package com.P.Client.controller;

import com.P.Client.view.GameView.Effect;
import com.P.Main;
import com.P.Client.model.Command;
import com.P.Client.model.GameAssetManager;
import com.P.common.model.Objects.Animal;
import com.P.common.model.Resualt;
import com.P.common.model.enums.AnimalType;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.App;
import com.P.common.model.Objects.Tool;
import com.P.common.model.Basics.Game;
import com.P.common.model.Maps.Position;
import com.P.common.model.Maps.Tile;
import com.P.common.model.Maps.Building;
import com.P.common.model.enums.Ingredients;
import com.P.common.model.enums.ToolType;
import com.P.common.model.Objects.Barn;
import com.P.common.model.Objects.Fish;
import com.P.common.model.Objects.ShippingBin;
import com.P.common.model.enums.BarnType;
import com.P.common.model.enums.SeasonFish;
import com.P.common.model.enums.ShopName;
import com.P.common.model.game.GameModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.*;


import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

import java.util.*;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import static com.P.common.model.enums.Weather.SUNNY;
import static java.lang.Integer.parseInt;
import static java.lang.Math.floor;

// check 481
public class RanchingController {
    private static final Array<Effect> heartEffects = new Array<>();
    private Stage animalMenuStage = null;
    private boolean isAnimalMenuOpen = false;
    private boolean isFeedAnimal = false;
    private long feedStartTime = 0;
    private boolean isSheperedAnimal = false;
    private long sheperedStartTime = 0;
    private boolean isNuz = false;
    private long nuzStartTime = 0;

    public void update() {
        handleInputs();
        float delta = Gdx.graphics.getDeltaTime();

        for (int i = heartEffects.size - 1; i >= 0; i--) {
            Effect heartEffect = heartEffects.get(i);
            heartEffect.update(delta);
            if (heartEffect.isFinished()) {
                heartEffects.removeIndex(i);
            }
        }

        for (Player player : App.loggedInUser.getCurrentGame().getPlayers()) {
            for (Building building : player.getFarm().getBuildings()) {
                if (building instanceof Barn) {
                    for (Animal animal : ((Barn) building).getAnimals()) {
                        handleAnimalMovement(animal);
                        updateAnimalMovement(delta, animal);
                    }
                }
            }
        }
    }

    public void render() {
        SpriteBatch batch = Main.getBatch();

        for (Player player : App.loggedInUser.getCurrentGame().getPlayers()) {
            for (Building building : player.getFarm().getBuildings()) {
                if (building instanceof Barn) {
                    for (Animal animal : ((Barn) building).getAnimals()) {
                        renderAnimal(animal);
                    }
                }
            }
        }

        for (Effect heartEffect : heartEffects) {
            heartEffect.draw(batch);
        }

        if (isAnimalMenuOpen && animalMenuStage != null) {
            animalMenuStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(animalMenuStage);
            animalMenuStage.draw();
        }

        for (Animal animal : App.loggedInUser.getCurrentGame().getProductAnimals()) {
            float x = animal.getPosition().getX() * Main.TILE_SIZE;
            float y = animal.getPosition().getY() * Main.TILE_SIZE;
            batch.draw(animal.getProduct().getTexture(), x, y, Main.TILE_SIZE, Main.TILE_SIZE);
        }

    }

    private void renderAnimal(Animal animal) {
        int tileSize = Main.TILE_SIZE;

        int houseTileX = animal.getTiles().get(0).getCoordinate().getX();
        int houseTileY = animal.getTiles().get(0).getCoordinate().getY();

        float camX = GameModel.getCamera().position.x;
        float camY = GameModel.getCamera().position.y;
        float viewportWidth = GameModel.getCamera().viewportWidth;
        float viewportHeight = GameModel.getCamera().viewportHeight;

        float cameraLeft = camX - viewportWidth / 2;
        float cameraBottom = camY - viewportHeight / 2;

        float drawX = houseTileX * tileSize - cameraLeft;
        float drawY = houseTileY * tileSize - cameraBottom;


        Main.getBatch().draw(animal.getTexture(), drawX, drawY);
        if (isFeedAnimal) {
            long elapsed = System.currentTimeMillis() - feedStartTime;
            if (elapsed < 10000) {
                float iconX = drawX;
                float iconY = drawY + tileSize;
                Main.getBatch().draw(GameAssetManager.NUZ, iconX, iconY);
            } else {
                isFeedAnimal = false;
            }
        }
        if (isSheperedAnimal) {
            long elapsed = System.currentTimeMillis() - sheperedStartTime;
            if (elapsed < 10000) {
                float iconX = drawX;
                float iconY = drawY + tileSize;
                Main.getBatch().draw(GameAssetManager.SHEPRED, iconX, iconY);
            } else {
                isSheperedAnimal = false;
            }
        }
        if(isNuz) {
            long elapsed = System.currentTimeMillis() - nuzStartTime;
            if (elapsed < 2000) {
                float iconX = drawX;
                float iconY = drawY + tileSize;
                Main.getBatch().draw(GameAssetManager.SHEPRED, iconX, iconY);
            } else {
                isNuz = false;
            }
        }
    }

    private void handleInputs() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            Vector3 worldCoords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            GameModel.getCamera().unproject(worldCoords);
            for (Player player1 : App.loggedInUser.getCurrentGame().getPlayers()) {
                for (Building building : App.loggedInUser.getCurrentGame().getCurrentPlayer().getFarm().getBuildings())
                    if (building instanceof Barn) {
                        for (Animal animal : ((Barn) building).getAnimals()) {
                            isAnimalMenuOpen = true;
                            createAnimalMenu(animal);
                        }
                    }
            }

        }
    }

    final Resualt[] response = {null};

    private void createAnimalMenu(Animal animal) {
        Stage animalStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(animalStage);

        Group menuGroup = new Group();

        Window window = new Window("Aniaml Menu", GameAssetManager.SKIN);
        window.setSize(1000, 600);
        window.setMovable(false);

        Table table = new Table();
        table.setFillParent(true);
        table.top().pad(20).defaults().pad(10).left();

        final Label responseLabel = new Label("", GameAssetManager.SKIN);
        responseLabel.setAlignment(Align.center);
        responseLabel.setWrap(true);
        table.add(responseLabel).colspan(2).width(600).center();
        table.row();

        Label feed = new Label("isFeedToday:  " + animal.getHasBeenFed(), GameAssetManager.SKIN);
        TextButton feedButton = new TextButton("feedAnimal", GameAssetManager.SKIN);
        feedButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                response[0] = FeedHay(animal.getName());
                if(response[0].isAccept()) {
                    isFeedAnimal = true;
                    feedStartTime = System.currentTimeMillis();
                }
                animalStage.clear();
                createAnimalMenu(animal);
            }
        });
        table.add(feed);
        table.add(feedButton).right();
        table.row();

        Label pet = new Label("isPetToday: " + animal.getHasBeenNuzzed(), GameAssetManager.SKIN);
        TextButton petButton = new TextButton("petAnimal", GameAssetManager.SKIN);
        petButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                response[0] = NuzPet(animal.getName());
                if(response[0].isAccept()) {
                    isNuz = true;
                    nuzStartTime = System.currentTimeMillis();
                }
                animalStage.clear();
                createAnimalMenu(animal);
            }
        });
        table.add(pet);
        table.add(petButton).right();
        table.row();

        Label produce;
        Resualt response1 = ShowProducts();
        produce = new Label(response1.getAnswer(), GameAssetManager.SKIN);
        TextButton produceButton = new TextButton("collectProduct", GameAssetManager.SKIN);
        produceButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                response[0] = CollectProduct(animal.getName());
                animalStage.clear();
                createAnimalMenu(animal);
            }
        });
        table.add(produce);
        table.add(produceButton).right();
        table.row();

        Label shepherd = new Label("isAnimalOut:  " + animal.getHasBeenOut(), GameAssetManager.SKIN);
        TextField shepherdPositionX = new TextField("x", GameAssetManager.SKIN);
        TextField shepherdPositionY = new TextField("y", GameAssetManager.SKIN);
        TextButton shepherdButton = new TextButton("ShepherdAnimal", GameAssetManager.SKIN);
        shepherdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    int positionX = Integer.parseInt(shepherdPositionX.getText());
                    int positionY = Integer.parseInt(shepherdPositionY.getText());
                    response[0] = ShepherdAnimals(animal.getName(), positionX, positionY);
                    if(response[0].isAccept()) {
                        isSheperedAnimal = true;
                        sheperedStartTime = System.currentTimeMillis();
                    }
                    animalStage.clear();
                    createAnimalMenu(animal);
                } catch (Exception ignored) {
                }
            }
        });
        table.add(shepherd).colspan(2);
        table.row();
        table.add(shepherdPositionX).width(60);
        table.add(shepherdPositionY).width(60);
        table.add(shepherdButton).right();
        table.row();

        Label sellPrice = new Label("sellPrice: " + animal.getType().getPrice(), GameAssetManager.SKIN);
        Label friendShip = new Label("friendShip: " + animal.getFriendship(), GameAssetManager.SKIN);
        table.add(sellPrice);
        table.add(friendShip).right();
        table.row();

        TextButton sell = new TextButton("sellAnimal", GameAssetManager.SKIN);
        sell.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                response[0] = SellAnimal(animal.getName());
            }
        });
        table.add(sell).colspan(2).center();
        table.row();

        window.add(table).expand().fill();

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
        if (response[0] != null) {
            Dialog dialog = new Dialog("", GameAssetManager.SKIN);
            dialog.text(response[0].getAnswer());
            dialog.button("OK");
            dialog.show(animalStage);
        }

    }


    private boolean collision(Animal animal, float worldX, float worldY) {
        Sprite sprite = animal.getSprite();
        sprite.setPosition(animal.getTiles().get(0).getCoordinate().getX() * Main.TILE_SIZE, animal.getTiles().get(0).getCoordinate().getY() * Main.TILE_SIZE);
        return worldX >= sprite.getX() && worldX <= sprite.getX() + sprite.getWidth() && worldY >= sprite.getY() && worldY <= sprite.getY() + sprite.getHeight();
    }

    private void handleAnimalMovement(Animal animal) {
        Random random = new Random();
        if (random.nextInt(1, 1000) == 1) {
            moveAnimal(animal);
        }
    }


    private void moveAnimal(Animal animal) {
        Random random = new Random();
        int totalDistance = 5;
        int dx = random.nextInt(-1, 2);
        int dy = random.nextInt(-1, 2);

        if (dx == 0 && dy == 0) return;

        Queue<Vector2> path = new LinkedList<>();
        int currentX = animal.getPosition().getX();
        int currentY = animal.getPosition().getY();

        for (int i = 1; i <= totalDistance; i++) {
            int stepX = currentX + dx * i;
            int stepY = currentY + dy * i;
            path.add(new Vector2(stepX, stepY));
        }

        if (!path.isEmpty()) {
            animal.setMovementPath(path);
        }
    }


    private void updateAnimalMovement(float delta, Animal animal) {
        animal.setTimeSinceLastMove(animal.getTimeSinceLastMove() + delta);

        if (animal.getMovementPath() != null && !animal.getMovementPath().isEmpty()) {
            if (animal.getTimeSinceLastMove() > 0.3f) {
                animal.setTimeSinceLastMove(0);
                Vector2 nextStep = animal.getMovementPath().poll();
                animal.getTiles().get(0).getCoordinate().setX((int) nextStep.x);
                animal.getTiles().get(0).getCoordinate().setY((int) nextStep.y);
                animal.setPosition(new Position((int) nextStep.x, (int) nextStep.y));
            }
        }
    }


    public static Resualt BuildBarn(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if (player.getCurrentShop() == null || player.getCurrentShop().getName() != ShopName.CarpenterShop)
            return new Resualt(false, "You must go to carpenter shop first.");
        String buildingName = request.body.get("name");
        int x = parseInt(request.body.get("x"));
        int y = parseInt(request.body.get("y"));
        Position cornerPosition = new Position(x, y);
        if (buildingName.equals("shipping bin")) {
            Tile tile = player.getFarm().findCellByCoordinate(cornerPosition.getX(), cornerPosition.getY());
            if (tile == null || tile.getObject() != null)
                return new Resualt(false, "Position occupied.");
            Integer wood = player.getInventory().getIngredients().get(Ingredients.WOOD);
            if (wood < 150)
                return new Resualt(false, "Not enough material.");
            if (player.getMoney() < 250)
                return new Resualt(false, "Not enough money.");
            ShippingBin shippingBin = new ShippingBin();
            tile.setObject(shippingBin);
            player.getFarm().getShippingBins().add(shippingBin);
            return new Resualt(true, "Shipping bin added successfully!");
        }

        BarnType barnType = null;
        for (BarnType type : BarnType.values())
            if (type.getKind().equals(buildingName))
                barnType = type;
        if (barnType == null)
            return new Resualt(false, "Invalid barn type.");
        Tile corner = player.getFarm().findCellByCoordinate(cornerPosition.getX(), cornerPosition.getY());
        if (corner == null)
            return new Resualt(false, "Invalid position.");
        int width = barnType.getWidth();
        int length = barnType.getLength();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < length; j++) {
                Position position = new Position(x + i, y + j);
                Tile tile = player.getFarm().findCellByCoordinate(position.getX(), position.getY());
                if (tile == null || tile.getObject() != null)
                    return new Resualt(false, "You are building a barn not a tree house!");
            }
        if (player.getMoney() < barnType.getPrice())
            return new Resualt(false, "YNot enough money.");
        Integer wood = player.getInventory().getIngredients().get(Ingredients.WOOD);
        Integer stone = player.getInventory().getIngredients().get(Ingredients.STONE);
        if (wood == null || wood < barnType.getWood() ||
            stone == null || stone < barnType.getStone())
            return new Resualt(false, "Not enough material.");
        player.setMoney(player.getMoney() - barnType.getPrice());
        player.getInventory().getIngredients().put(Ingredients.WOOD, wood - barnType.getWood());
        player.getInventory().getIngredients().put(Ingredients.STONE, stone - barnType.getStone());
        ArrayList<Tile> floor = new ArrayList<>();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < length; j++) {
                Position position = new Position(x + i, y + j);
                Tile tile = player.getFarm().findCellByCoordinate(position.getX(), position.getY());
                floor.add(tile);
            }
        player.getFarm().getBuildings().add(new Barn(floor, barnType));
        return new Resualt(true, "Barn added successfully!");
    }

    public static Resualt BuyAnimal(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if (player.getCurrentShop() == null || player.getCurrentShop().getName() != ShopName.MarnieRanch)
            return new Resualt(false, "You must go to Marnie Ranch first.");
        String animalKind = request.body.get("animalKind");
        String name = request.body.get("name");
        AnimalType newAnimalType = null;
        for (AnimalType animalType : AnimalType.values())
            if (animalType.getKind().equals(animalKind))
                newAnimalType = animalType;
        if (newAnimalType == null)
            return new Resualt(false, "Invalid Animal Type.");
        if (player.getMoney() < newAnimalType.getPrice())
            return new Resualt(false, "Not enough money.");
        if (getAnimalByName(name) != null)
            return new Resualt(false, "This name is already taken.");
        Barn newHome = null;
        for (Building building : player.getFarm().getBuildings())
            if (building.getClass() == Barn.class) {
                Barn barn = (Barn) building;
                if (barn.getAnimals().size() < barn.getCapacity()) {
                    boolean isValid = switch (newAnimalType) {
                        case Hen -> barn.getType() == BarnType.SimpleCoop ||
                            barn.getType() == BarnType.BigCoop ||
                            barn.getType() == BarnType.DeluxeCoop;
                        case Duck, Dinosaur -> barn.getType() == BarnType.BigCoop ||
                            barn.getType() == BarnType.DeluxeCoop;
                        case Rabbit -> barn.getType() == BarnType.DeluxeCoop;
                        case Cow -> barn.getType() == BarnType.SimpleBarn ||
                            barn.getType() == BarnType.BigBarn ||
                            barn.getType() == BarnType.DeluxeBarn;
                        case Goat -> barn.getType() == BarnType.BigBarn ||
                            barn.getType() == BarnType.DeluxeBarn;
                        case Sheep, Pig -> barn.getType() == BarnType.DeluxeBarn;
                        default -> false;
                    };
                    if (isValid) {
                        Animal newAnimal = null;
                        Position freeTile;
                        for (Tile tile : barn.getTiles())
                            if (!(tile.getObject() instanceof Animal)) {
                                newAnimal = new Animal(newAnimalType, name, tile.getCoordinate());
                                tile.setObject(newAnimal);
                                break;
                            }
                        barn.getAnimals().add(newAnimal);
                        player.setMoney(player.getMoney() - newAnimalType.getPrice());
                        return new Resualt(true, "Animal bought successfully!");
                    }
                }
            }
        return new Resualt(false, "No suitable barn found.");
    }

    public static Resualt NuzPet(String name) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Animal pet = getAnimalByName(name);
        if (pet == null) {
            System.out.println("No pet found with name " + name);
            return new Resualt(false, "No pet found.");
        }
        if (!pet.getHasBeenNuzzed()) {
            pet.setHasBeenNuzzed(true);
            pet.changeFriendship(15);
        }

        return new Resualt(true, "Done successfully!");
    }

    /// /// CHECKKKKKKKKKKKKKKKK
    public static Resualt ShowAnimalsInfo(Command request) {
        StringBuilder response = new StringBuilder();
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for (Building building : player.getFarm().getBuildings())
            if (building.getClass() == Barn.class) {
                Barn barn = (Barn) building;
                for (Animal animal : barn.getAnimals()) {
                    response.append(animal.getName()).append(" (").append(animal.getType().getKind()).append("): ");
                    response.append("friendship: ").append(animal.getFriendship()).append(", ");
                    if (animal.getHasBeenFed())
                        response.append("has been fed, ");
                    else
                        response.append("hasn't been fed yet.");
                    if (animal.getHasBeenNuzzed())
                        response.append("has been nuzzed!\n");
                    else
                        response.append("hasn't been nuzzed yet!\n");
                }
            }
        return new Resualt(true, response.toString());
    }

    public static Resualt ShepherdAnimals(String name, int x, int y) {
        Position newPosition = new Position(x, y);
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();

        Animal animal = getAnimalByName(name);
        if (animal == null)
            return new Resualt(false, "No animal found.");

        Tile currentTile = player.getFarm().findCellByCoordinate(animal.getPosition().getX(), animal.getPosition().getY());
        Tile destination = player.getFarm().findCellByCoordinate(newPosition.getX(), newPosition.getY());
        if (destination == null)
            return new Resualt(false, "Destination not found.");

        Barn barn = getBarnByAnimal(animal);
        if (barn == null)
            return new Resualt(false, "This error is never going to occur!!");
        if (destination.isInsideBuilding()) {
            if (barn.getTileByCoordinate(newPosition) == null)
                return new Resualt(false, "Destination isn't inside " + name + "'s house.");
            if (destination.getObject() instanceof Animal && !newPosition.equals(animal.getPosition()))
                return new Resualt(false, "Destination occupied.");
            currentTile.setObject(null);
            destination.setObject(animal);
            animal.setPosition(newPosition);
            animal.setInsideBarn(true);
        } else {
            Game game = App.getLoggedInUser().getCurrentGame();
            if (game.getWeatherToday() != SUNNY)
                return new Resualt(false, "Animals can't go out because of the weather conditions.");
            if (destination.getObject() != null)
                return new Resualt(false, "Destination occupied.");
            currentTile.setObject(null);
            destination.setObject(animal);
            animal.setPosition(newPosition);
            animal.setInsideBarn(false);
            if (!animal.getHasBeenFed()) {
                animal.setHasBeenFed(true);
                animal.changeFriendship(8);
                animal.setHasBeenOut(true);
            }
        }
        return new Resualt(true, "Done successfully!");
    }

    public static Resualt FeedHay(String name) {
        // String name = request.body.get("name");
        Animal animal = getAnimalByName(name);
        if (animal == null)
            return new Resualt(false, "No animal found.");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Integer hay = player.getInventory().getIngredients().get(Ingredients.HAY);
        if (hay == null)
            return new Resualt(false, "No hay available in inventory.");
        if (animal.getHasBeenFed())
            return new Resualt(false, name + " is not hungry!");
        animal.setHasBeenFed(true);
        player.getInventory().getIngredients().put(Ingredients.HAY, hay - 1);
        return new Resualt(true, name + " has been fed now!");
    }

    public static Resualt ShowProducts() {
        StringBuilder response = new StringBuilder();
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for (Building building : player.getFarm().getBuildings())
            if (building.getClass() == Barn.class) {
                Barn barn = (Barn) building;
                for (Animal animal : barn.getAnimals())
                    if (animal.getProduct() != null) {
                        response.append(animal.getName()).append(" (").append(animal.getType().getKind()).append("): ");
                        response.append(animal.getProduct().getName()).append("\n");
                    }
            }
        return new Resualt(true, response.toString());
    }

    public static Resualt CollectProduct(String name) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        // String name = request.body.get("name");
        Animal animal = getAnimalByName(name);
        if (animal == null)
            return new Resualt(false, "No animal found.");
        if (!player.getPosition().isNextTo(animal.getPosition()))
            return new Resualt(false, "You are not next to " + name);
        if (animal.getProduct() == null)
            return new Resualt(false, "No product available");
        Integer amount = player.getInventory().getIngredients().get(animal.getProduct());
        if (amount == null)
            amount = 1;
        else
            amount++;
        animal.collectProduct();
        animal.resetLastProduce();
        player.getInventory().getIngredients().put(animal.getProduct(), amount);
        return new Resualt(true, "Product collected successfully!");
    }

    public static Resualt SellAnimal(String name) {
        // String name = request.body.get("name");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Animal animal = getAnimalByName(name);
        if (animal == null)
            return new Resualt(false, "No animal found.");
        Barn barn = getBarnByAnimal(animal);
        if (barn == null)
            return new Resualt(false, "This error is never going to occur!!");
        double priceDouble = ((animal.getFriendship() / 1000) + 0.3) * animal.getType().getPrice();
        int priceInt = (int) floor(priceDouble);
        Tile currentTile = barn.getTileByCoordinate(animal.getPosition());
        //currentTile.setObject(null);
        barn.getAnimals().remove(animal);
        player.setMoney(player.getMoney() + priceInt);
        return new Resualt(true, "Sold successfully!");
    }

    public static Resualt GoFishing() {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Tool fishingRod = null;
        //In hand tool?
        for (Tool tool : player.getInventory().getTools().keySet())
            if (tool.getToolType() == ToolType.FishingRod)
                fishingRod = tool;
        if (fishingRod == null)
            return new Resualt(false, "You must have a fishing rod to go fishing.");
//        boolean isKenareAbb = false;
//        for (int i = -1; i <= 1; i++)
//            for (int j = -1; j <= 1; j++) {
//                Tile tile = player.getFarm().findCellByCoordinate(player.getPosition().getX() + i, player.getPosition().getY() + j);
//                if (tile == null || tile.getObjectOnCell().type.equals("water"))
//                    isKenareAbb = true;
//            }
        Random random = new Random();
        boolean isKenareAbb = random.nextBoolean();
        if (!isKenareAbb)
            return new Resualt(false, "You must be near a water source to start fishing.");
        double weatherInfluence;
        switch (App.getLoggedInUser().getCurrentGame().getWeatherToday()) {
            case SUNNY -> weatherInfluence = 1.5;
            case RAIN -> weatherInfluence = 1.2;
            case STORM -> weatherInfluence = 0.5;
            default -> weatherInfluence = 1;
        }

        double chanceInfluence = random.nextDouble(0, 1);
        int numberOfFish = (int) floor(chanceInfluence * weatherInfluence * (player.returnFishingLevel() + 2));
        if (numberOfFish > 6)
            numberOfFish = 6;
        double poleInfluence = 0;
        switch (fishingRod.getToolLevel()) {
            case Learning -> poleInfluence = 0.1;
            case Bambou -> poleInfluence = 0.5;
            case FiberGlass -> poleInfluence = 0.9;
            case Iridium -> poleInfluence = 1.2;
        }
        double qualityUseless = chanceInfluence * (player.returnFishingLevel() + 2) * poleInfluence / (7 - weatherInfluence);
        String quality;
        if (qualityUseless < 0.5)
            quality = "ordinary";
        else if (qualityUseless < 0.7)
            quality = "silver";
        else if (qualityUseless < 0.9)
            quality = "gold";
        else
            quality = "iridium";
        StringBuilder response = new StringBuilder();
        response.append("Well done! ").append(numberOfFish).append(" fish of ").append(quality).append(" quality!\n");
        int bound = 4;
        if (player.returnFishingLevel() == 4)
            bound++;
        List<Fish> fishList;
        switch (App.getLoggedInUser().getCurrentGame().getSeason()) {
            case SPRING -> fishList = SeasonFish.Spring.getSeasonFish();
            case SUMMER -> fishList = SeasonFish.Summer.getSeasonFish();
            case AUTUMN -> fishList = SeasonFish.Autumn.getSeasonFish();
            default -> fishList = SeasonFish.Winter.getSeasonFish();
        }
        for (int i = 0; i < numberOfFish; i++) {
            int index = random.nextInt(bound);
            Fish fish = fishList.get(index);
            Integer quantity = player.getInventory().getIngredients().get(fish.getType());
            if (quantity == null)
                quantity = 1;
            else
                quantity++;
            player.getInventory().getIngredients().put(fish.getType(), quantity);
            response.append(fish.getType().getName()).append("\n");
        }
        return new Resualt(true, response.toString());
    }

    public static Resualt CheatSetFriendship(Command request) {
        String name = request.body.get("name");
        int amount = parseInt(request.body.get("amount"));
        Animal animal = getAnimalByName(name);
        if (animal == null)
            return new Resualt(false, "No animal found.");
        if (amount > 1000)
            return new Resualt(false, "Invalid  friendship amount.");
        animal.changeFriendship(amount - animal.getFriendship());
        return new Resualt(true, "Friendship changed successfully!");
    }

    private static Animal getAnimalByName(String name) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for (Building building : player.getFarm().getBuildings())
            if (building.getClass() == Barn.class)
                for (Animal animal : ((Barn) building).getAnimals())
                    if (animal.getName().equals(name))
                        return animal;
        return null;
    }

    private static Barn getBarnByAnimal(Animal wantedAnimal) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for (Building building : player.getFarm().getBuildings())
            if (building.getClass() == Barn.class) {
                Barn barn = (Barn) building;
                for (Animal animal : barn.getAnimals())
                    if (animal.equals(wantedAnimal))
                        return barn;
            }
        return null;
    }
}
