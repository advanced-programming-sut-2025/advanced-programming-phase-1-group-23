package com.P.Client.controller;

import com.P.Client.model.GameAssetManager;
import com.P.Client.model.Pair;
import com.P.Client.view.GameView.ClickEffect;
import com.P.Client.view.GameView.GameView;
import com.P.Main;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.Client.model.Command;
import com.P.common.model.Maps.Position;
import com.P.common.model.Maps.Tile;
import com.P.common.model.Objects.CraftingMachine;
import com.P.common.model.Objects.Inventory;
import com.P.common.model.Resualt;
import com.P.common.model.enums.ForAgingSeeds;
import com.P.common.model.enums.Ingredients;
import com.P.common.model.enums.Recipe;
import com.P.common.model.game.GameModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Map;

public class CookingController extends ControllersController {
    public static Stage cookingStage = null;
    public static boolean isCookingMenuOpen = false;
    public static Stage buildingStage = null;
    public static boolean isBuildingMenuOpen = false;

    public void update(GameModel model) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            showCookingRecipes();
            createCookingMenu(model);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            showCraftingRecipes();
            createBuildingMenu(model);
        }


        if (isCookingMenuOpen && cookingStage != null) {
            cookingStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(cookingStage);
            cookingStage.draw();
        } else if (isBuildingMenuOpen && buildingStage != null) {
            buildingStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(buildingStage);
            buildingStage.draw();
        }
    }

    public void createBuildingMenu(GameModel model) {
        //if (!Gdx.input.isKeyJustPressed(Input.Keys.C))return;
        final Stage[] buildingStage = {new Stage(new ScreenViewport())};
        //Gdx.input.setInputProcessor(cookingStage);

        Group menuGroup = new Group();
        Window window = new Window("Building Menu", GameAssetManager.SKIN);
        window.setSize(1000, 600);
        window.setMovable(false);

        Table table = new Table();
        table.top().pad(20).defaults().pad(10);
        //table.setFillParent(true);

        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isBuildingMenuOpen = false;
                Gdx.input.setInputProcessor(null);
                buildingStage[0].dispose();
                buildingStage[0] = null;
            }
        });

        for (Recipe recipe : Recipe.values()) {
            if (recipe.isEatable()) {
                continue;
            }
            Texture texture = new Texture(Gdx.files.internal(recipe.getTextureName()));
            Image image = new Image(texture);
            Label nameLabel = new Label(recipe.name(), GameAssetManager.SKIN);
            Label priceLabel = new Label("Sell Price: " + recipe.getSellPrice(), GameAssetManager.SKIN);


            TextButton cookButton = new TextButton("Make", GameAssetManager.SKIN);
            cookButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Command n = new Command("cook");
                    n.body.put("itemName", recipe.getName());
                    Resualt response = startCraft(n, model);
                    Dialog dialog = new Dialog("", GameAssetManager.SKIN);
                    dialog.text(response.getAnswer());
                    dialog.button("Put Down");
                    dialog.show(buildingStage[0]);
                }
            });

            table.add(image).size(64, 64);
            table.add(nameLabel).left();
            table.add(priceLabel);
            table.add(cookButton).right();
            table.row();
        }


        ScrollPane scrollPane = new ScrollPane(table, GameAssetManager.SKIN);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setForceScroll(false, true);
        scrollPane.layout();
        window.setSize(1000, 600);
        window.add(scrollPane).expand().fill();

        Group group = new Group() {
            @Override
            public void act(float delta) {
                super.act(delta);
                window.setPosition(
                    (buildingStage[0].getViewport().getScreenWidth() - window.getWidth()) / 2f,
                    (buildingStage[0].getViewport().getScreenHeight() - window.getHeight()) / 2f
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
        buildingStage[0].addActor(menuGroup);

        CookingController.buildingStage = buildingStage[0];
        isBuildingMenuOpen = true;

        Gdx.app.postRunnable(() -> {
            Gdx.input.setInputProcessor(buildingStage[0]);
        });
    }


    public void createCookingMenu(GameModel model) {
        //if (!Gdx.input.isKeyJustPressed(Input.Keys.C))return;
        final Stage[][] cookingStage = {{new Stage(new ScreenViewport())}};
        //Gdx.input.setInputProcessor(cookingStage);

        Group menuGroup = new Group();
        Window window = new Window("Cooking Menu", GameAssetManager.SKIN);
        window.setSize(1000, 600);
        window.setMovable(false);

        Table table = new Table();
        table.top().pad(20).defaults().pad(10);
        //table.setFillParent(true);

        Player player = model.getPlayer();
        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isCookingMenuOpen = false;
                Gdx.input.setInputProcessor(null);
                cookingStage[0][0].dispose();
                cookingStage[0][0] = null;
            }
        });
        TextButton fridgeButton = new TextButton("Show Refrigerator", GameAssetManager.SKIN);
        fridgeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Table ref = creatRefrigeratorTable(cookingStage);

                ScrollPane scrollPane = new ScrollPane(ref, GameAssetManager.SKIN);
                scrollPane.setFadeScrollBars(false);
                Dialog dialog = new Dialog("Refrigerator", GameAssetManager.SKIN);

                dialog.getContentTable().add(scrollPane).width(500).height(400);
                dialog.button("OK");
                dialog.show(cookingStage[0][0]);
            }
        });
        TextButton eat = new TextButton("Eat Food", GameAssetManager.SKIN);
        eat.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                final TextField textField = new TextField("", GameAssetManager.SKIN);
                Dialog inputDialog = new Dialog("Enter something", GameAssetManager.SKIN) {
                    @Override
                    protected void result(Object object) {
                        if ((boolean) object) {
                            String inputText = textField.getText();
                            Command command = new Command("ea");
                            command.body.put("foodName", inputText);
                            String rslt = eatDeliciousFood(command).getAnswer();
                            Dialog resultDialog = new Dialog("Result", GameAssetManager.SKIN);
                            resultDialog.text(rslt);
                            resultDialog.button("OK");
                            resultDialog.show(cookingStage[0][0]);
                            int r = (int) (Math.random() * 3);
                            if (rslt.equals("Bon appetit!")) {
                                cookingStage[0] = null;
                                isCookingMenuOpen = false;
                                GameView.effects.add(new ClickEffect(GameAssetManager.getGameAssetManager().eating, player.getPlayerPosition().first + 1, player.getPlayerPosition().second, 15, 100));
                                if (r == 1)
                                    GameView.effects.add(new ClickEffect(GameAssetManager.getGameAssetManager().buff1, (float) 10 /100, (float) (Gdx.graphics.getHeight() - 250) /100, 60, 60));
                                if (r == 2)
                                    GameView.effects.add(new ClickEffect(GameAssetManager.getGameAssetManager().buff1,(float) 10 /100, (float) (Gdx.graphics.getHeight() - 250) /100, 60, 60));


                            }
                        }
                    }
                };
                inputDialog.getContentTable().add(textField).width(300).pad(10);

                inputDialog.button("OK", true);
                inputDialog.button("Cancel", false);

                inputDialog.show(cookingStage[0][0]);
            }
        });


        table.add(fridgeButton).colspan(5).center().row();
        table.add(eat).colspan(5).center().row();

        table.row();
        for (Recipe recipe : Recipe.values()) {
            if (!recipe.isEatable()) {
                //System.out.println(recipe.getName());
                continue;
            }
            Texture texture = new Texture(Gdx.files.internal(recipe.getTextureName()));
            Image image = new Image(texture);
            Label nameLabel = new Label(recipe.name(), GameAssetManager.SKIN);

            Label energyLabel = new Label("Energy: " + recipe.getEnergy(), GameAssetManager.SKIN);
            Label priceLabel = new Label("Sell Price: " + recipe.getSellPrice(), GameAssetManager.SKIN);


            TextButton cookButton = new TextButton("Cook", GameAssetManager.SKIN);
            cookButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Command n = new Command("cook");
                    n.body.put("recipeName", recipe.getName());
                    Resualt response = cookFood(n);
                    Dialog dialog = new Dialog("", GameAssetManager.SKIN);
                    dialog.text(response.getAnswer());
                    dialog.button("OK");
                    dialog.show(cookingStage[0][0]);
                }
            });

            table.add(image).size(64, 64);
            table.add(nameLabel).left();
            table.add(energyLabel);
            table.add(priceLabel);
            table.add(cookButton).right();
            table.row();
        }


        ScrollPane scrollPane = new ScrollPane(table, GameAssetManager.SKIN);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setForceScroll(false, true);
        scrollPane.layout();
        window.setSize(1000, 600);
        window.add(scrollPane).expand().fill();

        Group group = new Group() {
            @Override
            public void act(float delta) {
                super.act(delta);
                window.setPosition(
                    (cookingStage[0][0].getViewport().getScreenWidth() - window.getWidth()) / 2f,
                    (cookingStage[0][0].getViewport().getScreenHeight() - window.getHeight()) / 2f
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
        cookingStage[0][0].addActor(menuGroup);

        CookingController.cookingStage = cookingStage[0][0];
        isCookingMenuOpen = true;

        Gdx.app.postRunnable(() -> {
            Gdx.input.setInputProcessor(cookingStage[0][0]);
        });
    }


    private Table creatRefrigeratorTable(Stage shopStage[][]) {
        Table table = new Table();
        table.defaults().pad(5);
        TextButton move=new TextButton("Move", GameAssetManager.SKIN);
        move.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final TextField quantityField = new TextField("", GameAssetManager.SKIN);

                Dialog quantityDialog = new Dialog("Move Item", GameAssetManager.SKIN) {
                    @Override
                    protected void result(Object object) {
                        if (object.equals(true)) {
                            Command n = new Command("move");
                            n.body.put("item", quantityField.getText());
                            String input = quantityField.getText();
                            if (input.isEmpty()) input = "1";

                            String rslt = putInRefrigerator(n).getAnswer();

                            Dialog resultDialog = new Dialog("Move Result", GameAssetManager.SKIN);
                            resultDialog.text(rslt);
                            resultDialog.button("OK");
                            resultDialog.show(shopStage[0][0]);
                        }
                    }

                };

                quantityDialog.text("Enter name:");
                quantityDialog.getContentTable().add(quantityField).row();

                quantityDialog.button("move", true);
                quantityDialog.button("cancel", false);

                quantityDialog.show(shopStage[0][0]);
            }
        });
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory refrigerator = player.getRefrigerator();
        table.add(move).row();
        for (Map.Entry<Ingredients, Integer> need : refrigerator.getIngredients().entrySet()) {
            Texture texture = need.getKey().getTexture();
            Image image = new Image(texture);
            Label nameLabel = new Label(need.getKey().getName(), GameAssetManager.SKIN);
            Label energyLabel = new Label("Quantity: " + refrigerator.getIngredients().get(need.getKey()), GameAssetManager.SKIN);
            Label priceLabel = new Label("Sell Price: " + need.getKey().getPrice(), GameAssetManager.SKIN);

            table.add(image).size(64, 64).left().padRight(10);
            table.add(nameLabel).left().padRight(10);
            table.add(energyLabel).left().padRight(10);
            table.add(priceLabel).left();
            table.row().padBottom(10);
        }
        return table;
    }

    public static Resualt getFromRefrigerator(Command command) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Inventory refrigerator = player.getRefrigerator();
        String itemName = command.body.get("item");
        Ingredients item = null;
        for (Ingredients item1 : Ingredients.values()) {
            if (item1.getName().equals(itemName))
                item = item1;
        }
        if (item == null) return new Resualt(false, "Item doesn't exist");
        int exist = 0;
        for (Map.Entry<Ingredients, Integer> need : refrigerator.getIngredients().entrySet()) {
            if (need.getKey().equals(item)) {
                exist = 1;
                break;
            }
        }
        if (exist == 0) return new Resualt(false, "You don't have the item in the refrigerator");
        if (inventory.getCapacity() > 0) {
            inventory.setCapacity(inventory.getCapacity() - 1);
            inventory.getIngredients().put(item, refrigerator.getIngredients().get(item));
            refrigerator.setCapacity(refrigerator.getCapacity() + 1);
            refrigerator.getIngredients().remove(item);
            return new Resualt(true, "Item successfully added to inventory");
        }
        return new Resualt(false, "Inventory is full");
    }

    public static Resualt eatDeliciousFood(Command command) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        String itemName = command.body.get("foodName");
        Ingredients food = null;
        for (Ingredients item1 : Ingredients.values()) {
            if (item1.getName().equals(itemName))
                food = item1;
        }
        if (food == null) return new Resualt(false, "Goshnegi housh o havaseto borde Haaa!!!");
        int exist = 0;
        for (Map.Entry<Ingredients, Integer> need : inventory.getIngredients().entrySet()) {
            if (need.getKey().equals(food)) {
                exist = 1;
                break;
            }
        }
        if (exist == 0) return new Resualt(false, "You don't have the food in the inventory");
        inventory.getIngredients().put(food, inventory.getIngredients().get(food) - 1);
        if (inventory.getIngredients().get(food) == 0) {
            inventory.getIngredients().remove(food);
            inventory.setCapacity(inventory.getCapacity() + 1);
        }
        player.setEnergy(player.getEnergy() + food.getEnergy());
        return new Resualt(true, "Bon appetit!");
    }

    public static Resualt putInRefrigerator(Command command) {

        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Inventory refrigerator = player.getRefrigerator();
        String itemName = command.body.get("item");
        Ingredients item = null;
        for (Ingredients item1 : Ingredients.values()) {
            if (item1.getName().equals(itemName))
                item = item1;
        }
        if (item == null) return new Resualt(false, "Item doesn't exist");
        int exist = 0;
        for (Map.Entry<Ingredients, Integer> need : inventory.getIngredients().entrySet()) {
            if (need.getKey().equals(item)) {
                exist = 1;
                break;
            }
        }
        if (exist == 0) return new Resualt(false, "You don't have the item in the inventory");

        if (refrigerator.getCapacity() > 0) {
            refrigerator.setCapacity(refrigerator.getCapacity() - 1);
            refrigerator.getIngredients().put(item, inventory.getIngredients().get(item));
            inventory.getIngredients().remove(item);
            inventory.setCapacity(inventory.getCapacity() + 1);
            return new Resualt(true, "Item successfully added to refrigerator");
        }
        return new Resualt(false, "Refrigerator is full");

    }

    public static Resualt showCookingRecipes() {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        updateRecipes(player);
        StringBuilder recipes = new StringBuilder();
        for (Recipe recipe : player.getRecipes()) {
            if (recipe.isEatable()) {
                recipes.append("Name: ").append(recipe.getName()).append("  Energy: ").append(recipe.getEnergy()).append("\n");

            }
        }

        if (!recipes.isEmpty()) return new Resualt(true, recipes.toString());
        return new Resualt(false, "You haven't learned any cooking recipes yet!");
    }

    public static Resualt showCraftingRecipes() {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        updateRecipes(player);
        StringBuilder recipes = new StringBuilder();
        for (Recipe recipe : player.getRecipes()) {
            if (!recipe.isEatable()) {
                recipes.append("Name: ").append(recipe.getName()).append("  Energy: ").append(recipe.getEnergy()).append("\n");

            }
        }

        if (!recipes.isEmpty()) return new Resualt(true, recipes.toString());
        return new Resualt(false, "You haven't learned any crafting recipes yet!");
    }

    public static Resualt cookFood(Command command) {
        String foodRecipe = command.body.get("recipeName");
        Recipe recipe = null;
        for (Recipe recipe1 : Recipe.values()) {
            if (recipe1.getName().equals(foodRecipe))
                recipe = recipe1;
        }
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if (!player.getRecipes().contains(recipe) || recipe == null) {
            return new Resualt(false, "You don't know the recipe.");
        }
        for (Map.Entry<Ingredients, Integer> provided : recipe.getIngredients().entrySet()) {
            if (!player.getInventory().getIngredients().containsKey(provided.getKey()))
                return new Resualt(false, "You don't have enough ingredients");
            for (Map.Entry<Ingredients, Integer> needed : player.getInventory().getIngredients().entrySet()) {
                if (needed.getKey() == provided.getKey()) {
                    if (needed.getValue() < provided.getValue()) {
                        return new Resualt(false, "You don't have enough ingredients");
                    }
                }

            }
            for (Map.Entry<Ingredients, Integer> needed : player.getRefrigerator().getIngredients().entrySet()) {
                if (needed.getKey() == provided.getKey()) {
                    if (needed.getValue() < provided.getValue()) {
                        return new Resualt(false, "You don't have enough ingredients");
                    }
                }

            }
        }
        if (player.getInventory().getCapacity() <= 0) return new Resualt(false, "Your inventory is full!");
        if (player.getEnergy() <= 3) {
            player.setFainted(true);
            return new Resualt(false, "Na ki gofte bandeye shekamam");
        }
        // Now we can Start cooking
        for (Map.Entry<Ingredients, Integer> provided : recipe.getIngredients().entrySet()) {
            for (Map.Entry<Ingredients, Integer> needed : player.getInventory().getIngredients().entrySet()) {
                if (needed.getKey() == provided.getKey()) {
                    player.getInventory().getIngredients().put(needed.getKey(), needed.getValue() - provided.getValue());
                }

            }
            for (Map.Entry<Ingredients, Integer> needed : player.getRefrigerator().getIngredients().entrySet()) {
                if (needed.getKey() == provided.getKey()) {
                    player.getRefrigerator().getIngredients().put(needed.getKey(), needed.getValue() - provided.getValue());
                }

            }
        }
        player.setEnergy(player.getEnergy() - 3);
        if (player.getInventory().getIngredients().containsKey(recipe.getFoodMade())) {
            player.getInventory().getIngredients().put(recipe.getFoodMade(), player.getInventory().getIngredients().get(recipe.getFoodMade()) + 1);
        } else {
            player.getInventory().setCapacity(player.getInventory().getCapacity() - 1);
            player.getInventory().getIngredients().put(recipe.getFoodMade(), 1);
        }
        return new Resualt(true, "New food was cooked!");
    }

    public static Resualt startCraft(Command command, GameModel model) {
        String craftRecipe = command.body.get("itemName");
        Recipe recipe = null;
        for (Recipe recipe1 : Recipe.values()) {
            if (recipe1.getName().equals(craftRecipe))
                recipe = recipe1;
        }
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Player player1 = model.getPlayer();
        if (!player.getRecipes().contains(recipe)) {
            return new Resualt(false, "You don't know this recipe.");
        }
        for (Map.Entry<Ingredients, Integer> provided : recipe.getIngredients().entrySet()) {
            if (!player.getInventory().getIngredients().containsKey(provided.getKey()))
                return new Resualt(false, "You don't have enough ingredients");
            for (Map.Entry<Ingredients, Integer> needed : player.getInventory().getIngredients().entrySet()) {
                if (needed.getKey() == provided.getKey()) {
                    if (needed.getValue() < provided.getValue()) {
                        return new Resualt(false, "You don't have enough ingredients");
                    }
                }

            }
        }
        if (player.getInventory().getCapacity() <= 0) return new Resualt(false, "Your inventory is full!");
        /*if (player.getEnergy() <= 2) {
            player.setFainted(true);
            return new Resualt(false, "You don't have enough energy to craft this.");
        }*/
        // Now we can Start crafting
        for (Map.Entry<Ingredients, Integer> provided : recipe.getIngredients().entrySet()) {
            for (Map.Entry<Ingredients, Integer> needed : player.getInventory().getIngredients().entrySet()) {
                if (needed.getKey() == provided.getKey()) {
                    player.getInventory().getIngredients().put(needed.getKey(), needed.getValue() - provided.getValue());
                }

            }
        }
        player.setEnergy(player.getEnergy() - 2);
        if (player.getInventory().getIngredients().containsKey(recipe.getFoodMade())) {
            player.getInventory().getIngredients().put(recipe.getFoodMade(), player.getInventory().getIngredients().get(recipe.getFoodMade()) + 1);
        } else {
            player.getInventory().setCapacity(player.getInventory().getCapacity() - 1);
            player.getInventory().getIngredients().put(recipe.getFoodMade(), 1);
        }
        Command command1=new Command("put");
        command1.body.put("direction", "Right");
        command1.body.put("itemName", recipe.getFoodMade().getName());
        putItem(command1);
        GameView.builds.put(recipe.getFoodMade(), new Pair<>(player1.getPlayerPosition().first, player1.getPlayerPosition().second));
        return new Resualt(true, "New crafting was crafted!");
    }

    public static void updateRecipes(Player player) {
        player.setMiningSkill(160);
        player.setFarmingSkill(160);
        player.setForagingSkill(260);
        if (player.returnMiningLevel() == 1) {
            player.getRecipes().add(Recipe.CherryBomb);
            player.getRecipes().add(Recipe.MinersTreat);
        }
        if (player.returnFishingLevel() == 2) player.getRecipes().add(Recipe.DishOTheSea);
        if (player.returnFishingLevel() == 3) player.getRecipes().add(Recipe.SeafoamPudding);

        if (player.returnMiningLevel() == 2) player.getRecipes().add(Recipe.Bomb);
        if (player.returnMiningLevel() == 3) player.getRecipes().add(Recipe.MegaBomb);
        if (player.returnFarmingLevel() == 1) {
            player.getRecipes().add(Recipe.FarmersLunch);
            player.getRecipes().add(Recipe.Sprinkler);
            player.getRecipes().add(Recipe.BeeHouse);
        }
        if (player.returnFarmingLevel() == 2) {
            player.getRecipes().add(Recipe.QualitySprinkler);
            player.getRecipes().add(Recipe.Scarecrow);
            player.getRecipes().add(Recipe.CheesePress);
            player.getRecipes().add(Recipe.PreservesJar);


        }
        if (player.returnFarmingLevel() == 3) {
            player.getRecipes().add(Recipe.IridiumSprinkler);
            player.getRecipes().add(Recipe.Keg);
            player.getRecipes().add(Recipe.Loom);
            player.getRecipes().add(Recipe.OilMaker);
        }
        if (player.returnForagingLevel() == 1) player.getRecipes().add(Recipe.CharcoalKlin);
        if (player.returnForagingLevel() == 2) player.getRecipes().add(Recipe.VegetableMedley);
        if (player.returnForagingLevel() == 3) player.getRecipes().add(Recipe.SurvivalBurger);


    }

    public static Resualt putItem(Command command) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        String direction = command.body.get("direction");
        String itemName = command.body.get("itemName");
        Ingredients item = null;
        Position position = InventoryFunctionsController.findPositionByDirection(direction, player.getPosition());
        Tile tile = InventoryFunctionsController.findTileByPosition(position, player.getFarm());
        if (tile == null) return new Resualt(false, "Wrong direction.");
        for (Ingredients ingredients : Ingredients.values()) {
            if (ingredients.getName().equals(itemName)) item = ingredients;
        }
        if (!player.getInventory().getIngredients().containsKey(item) || item == null)
            return new Resualt(false, "You don't have this item with you");
        if (tile.getIngredients() != null) return new Resualt(false, " ground is full");
        tile.setIngredients(item);
        tile.setMachine(new CraftingMachine(item));
        player.getInventory().getIngredients().remove(item);
        player.getInventory().setCapacity(player.getInventory().getCapacity() + 1);
        return new Resualt(true, itemName + " was placed on the ground successfully");
    }

    ;

    //    public static Resualt CheatAddInventory(Command command) {
//        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
//        String count = command.body.get("count");
//        String itemName = command.body.get("itemName");
//        Ingredients item = null;
//        for (Ingredients ingredients : Ingredients.values()) {
//            if (ingredients.getName().equals(itemName)) item = ingredients;
//        }
//        if (item == null) return new Resualt(false, "this item doesn't exist!");
//        if (player.getInventory().getCapacity() <= 0) return new Resualt(false, "inventory full");
//        player.getInventory().getIngredients().put(item, Integer.parseInt(count));
//        return null;
//    }
    public static Resualt CheatAddInventory(Command command) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        String count = command.body.get("count");
        String itemName = command.body.get("itemName");
        Ingredients item = null;
        ForAgingSeeds seed = null;
        for (Ingredients ingredients : Ingredients.values()) {
            if (ingredients.getName().equals(itemName)) item = ingredients;
        }
        for (ForAgingSeeds seeds : ForAgingSeeds.values()) {
            if (seeds.getSeedName().equals(itemName)) seed = seeds;
        }
        if (item == null && seed == null) return new Resualt(false, "this item doesn't exist!");
        if (seed != null) {
            player.getInventory().getSeeds().put(seed, 10);
        }
        if (player.getInventory().getCapacity() <= 0) return new Resualt(false, "inventory full");
        if (item != null)
            player.getInventory().getIngredients().put(item, Integer.parseInt(count));
        return new Resualt(true, "You have cheated this item");
    }

}
