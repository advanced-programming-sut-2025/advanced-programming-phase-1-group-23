package com.P.Client.controller;

import com.P.Client.model.Command;
import com.P.Client.model.GameAssetManager;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.App;
import com.P.common.model.Maps.Building;
import com.P.common.model.Resualt;
import com.P.common.model.Objects.*;
import com.P.common.model.enums.*;
import com.P.common.model.Maps.Tile;
import com.P.common.model.game.GameModel;
import com.P.common.model.game.VillageModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.swing.*;
import java.util.Map;
import java.util.Random;

import static java.lang.Integer.parseInt;
import static java.lang.Math.floor;

public class ShoppingController {
    public static Stage shopStage = null;
    public static boolean isBuildingMenuOpen = false;
    public static boolean stock = false;
    private Table table;

    public void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            createShopMenu("BlackSmith");
        }
       // if (Gdx.input.isKeyJustPressed(Input.Keys.L)) createSellMenu();

//        if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
//            createShopMenu("JojaMart");
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
//            createShopMenu("PierreGeneralStore");
//        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            createShopMenu("CarpenterShop");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            createShopMenu("FishShop");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            createShopMenu("MarnieRanch");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            createShopMenu("StardropSaloon");
        }
        if (isBuildingMenuOpen && shopStage != null) {
            shopStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(shopStage);
            shopStage.draw();
        }
    }
    public static void createSellMenu() {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        final Stage[] shopStage = {new Stage(new ScreenViewport())};

        Group menuGroup = new Group();
        Window window = new Window("Menu", GameAssetManager.SKIN);
        window.setSize(1000, 600);
        window.setMovable(false);

        Table table = new Table();
        table.top().pad(20).defaults().pad(10);

        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isBuildingMenuOpen = false;
                Gdx.input.setInputProcessor(null);
                shopStage[0].dispose();
                shopStage[0] = null;
            }
        });

        for (Map.Entry<Ingredients, Integer> need : player.getInventory().getIngredients().entrySet()) {
            Texture texture = need.getKey().getTexture();
            Image image = new Image(texture);
            Label nameLabel = new Label(need.getKey().getName(), GameAssetManager.SKIN);
            Label energyLabel = new Label("Quantity: " + player.getInventory().getIngredients().get(need.getKey()), GameAssetManager.SKIN);
            Label priceLabel = new Label("Sell Price: " + need.getKey().getPrice(), GameAssetManager.SKIN);

            table.add(image).size(40, 40).left().padRight(10);
            table.add(nameLabel).left().padRight(10);
            table.add(energyLabel).left().padRight(10);
            table.add(priceLabel).left();

            TextButton sell = new TextButton("Sell", GameAssetManager.SKIN);
            sell.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    final TextField quantityField = new TextField("", GameAssetManager.SKIN);
                    quantityField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

                    Dialog quantityDialog = new Dialog("Buy Item", GameAssetManager.SKIN) {
                        @Override
                        protected void result(Object object) {
                            if (object.equals(true)) {
                                Command n = new Command("sell");
                                n.body.put("name", need.getKey().getName());
                                String input = quantityField.getText();
                                if (input.isEmpty()) input = "1";
                                n.body.put("amount", input);

                                String rslt = Sell(n).getAnswer();

                                Dialog resultDialog = new Dialog("Sell Result", GameAssetManager.SKIN);
                                resultDialog.text(rslt);
                                resultDialog.button("OK");
                                resultDialog.show(shopStage[0]);
                            }
                        }

                    };

                    quantityDialog.text("Enter quantity:");
                    quantityDialog.getContentTable().add(quantityField).row();

                    quantityDialog.button("Sell", true);
                    quantityDialog.button("Cancel", false);

                    quantityDialog.show(shopStage[0]);
                }
            });
            table.add(sell).left();
            table.row().padBottom(10);


        }



        ScrollPane scrollPane = new ScrollPane(table, GameAssetManager.SKIN);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setForceScroll(false, true);
        scrollPane.layout();
        window.setSize(1000, 600);
        window.add(scrollPane).

            expand().

            fill();

        Group group = new Group() {
            @Override
            public void act(float delta) {
                super.act(delta);
                window.setPosition(
                    (shopStage[0].getViewport().getScreenWidth() - window.getWidth()) / 2f,
                    (shopStage[0].getViewport().getScreenHeight() - window.getHeight()) / 2f
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
        shopStage[0].

            addActor(menuGroup);

        ShoppingController.shopStage = shopStage[0];
        isBuildingMenuOpen = true;

        Gdx.app.postRunnable(() ->

        {
            Gdx.input.setInputProcessor(shopStage[0]);
        });
    }

    public void createShopMenu(String shopName) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        final Stage[] shopStage = {new Stage(new ScreenViewport())};

        Group menuGroup = new Group();
        Window window = new Window("Menu", GameAssetManager.SKIN);
        window.setSize(1000, 600);
        window.setMovable(false);

        Table table = new Table();
        table.top().pad(20).defaults().pad(10);

        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isBuildingMenuOpen = false;
                Gdx.input.setInputProcessor(null);
                shopStage[0].dispose();
                shopStage[0] = null;
            }
        });
        TextButton stock1 = new TextButton("Show Stock", GameAssetManager.SKIN);
        stock1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stock = !stock;
                createShopMenu(shopName);

            }
        });
        table.add(stock1).row();
        StringBuilder response = new StringBuilder();
        Texture texture = null;
        Shop shop = null;
        String name=" ";
        VillageModel villageModel = App.getLoggedInUser().getCurrentGame().getVillageModel();
        for (Building building : villageModel.getFarm().getBuildings()) {
            if (building instanceof Shop shop1) {
                if (shop1.getName().toString().equals(shopName)) {
                    shop = shop1;
                    break;
                }
            }
        }
        for (ShopItem shopItem : shop.getItems()) {
            response = new StringBuilder();
            if (shopItem.getDailyLimit() <= shopItem.getNumberOfSold() && !stock) continue;
            //shopItem.increaseNumberOfSold(shopItem.getDailyLimit());
            if (shopItem instanceof ShopIngredient item) {

                response.append(item.getType().getName()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
                texture = item.getType().getTexture();
                name=item.getType().getName();
            } else if (shopItem instanceof ShopRecipe item) {
                response.append(item.getType().getName()).append(" ").append("Sell Price:");
                response.append(item.getType().getSellPrice()).append("\n");
                texture = new Texture(item.getType().getTextureName());
                name=item.getType().getName();

            } else if (shopItem instanceof ShopBarn item) {
                response.append(item.getType().getKind()).append(" ").append("\n");
                response.append(item.getType().getPrice());
                name=item.getType().getKind();

            } else if (shopItem instanceof ShopAnimal item) {
                response.append(item.getType().getKind()).append(" ").append("Sell Price:");
                response.append(item.getType().getPrice()).append("\n");
                texture = item.getType().getAssetManager();
                name=item.getType().getKind();

            } else if (shopItem instanceof ShopSeed item) {
                response.append(item.getType().getSeedName()).append(" ").append("Sell Price:");
                response.append(item.getType().getPrice()).append("\n");
                texture = item.getType().getTexture();
                name=item.getType().getSeedName();

            } else if (shopItem instanceof ShopTool item) {
                response.append(item.getType()).append(" ");
                name=item.getType().toString();

                if (item.getType() == ToolType.FishingRod)
                    response.append("25\n");
                else
                    response.append("1000\n");
                Tool tool = new Tool(item.getType(), ToolLevel.Initial);
                texture = GameAssetManager.getTexture(tool);
            }

            Image image = new Image(GameAssetManager.AMARANTH);
            if (texture != null) {
                image = new Image(texture);
            }
            Label nameLabel = new Label(response.toString(), GameAssetManager.SKIN);
            //Label priceLabel = new Label("Sell Price: " + recipe.getSellPrice(), GameAssetManager.SKIN);


            TextButton cookButton = new TextButton("Buy", GameAssetManager.SKIN);
            String finalName = name;
            Shop finalShop = shop;
            cookButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    final TextField quantityField = new TextField("", GameAssetManager.SKIN);
                    quantityField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

                    Dialog quantityDialog = new Dialog("Buy Item", GameAssetManager.SKIN) {
                        @Override
                        protected void result(Object object) {
                            if (object.equals(true)) {
                                Command n = new Command("sell");
                                n.body.put("name", finalName);
                                String input = quantityField.getText();
                                if (input.isEmpty()) input = "1";
                                n.body.put("amount", input);

                                String rslt = Purchase(n, finalShop).getAnswer();

                                Dialog resultDialog = new Dialog("Buy Result", GameAssetManager.SKIN);
                                resultDialog.text(rslt);
                                resultDialog.button("OK");
                                resultDialog.show(shopStage[0]);
                            }
                        }

                    };

                    quantityDialog.text("Enter quantity:");
                    quantityDialog.getContentTable().add(quantityField).row();

                    quantityDialog.button("Buy", true);
                    quantityDialog.button("Cancel", false);

                    quantityDialog.show(shopStage[0]);
                }
            });

            Actor displayImage;
            if (shopItem.getDailyLimit() <= shopItem.getNumberOfSold()) {
                Image darkOverlay = new Image(GameAssetManager.Dark);
                darkOverlay.setColor(0, 0, 0, 0.5f);
                darkOverlay.setSize(64, 64);
                Stack stack = new Stack();
                stack.add(image);
                stack.add(darkOverlay);
                stack.setSize(64, 64);

                displayImage = stack;
            } else {
                displayImage = image;
            }
            table.add(displayImage).size(64, 64);
            table.add(nameLabel).left();
            // table.add(priceLabel);
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
                    (shopStage[0].getViewport().getScreenWidth() - window.getWidth()) / 2f,
                    (shopStage[0].getViewport().getScreenHeight() - window.getHeight()) / 2f
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
        shopStage[0].addActor(menuGroup);

        ShoppingController.shopStage = shopStage[0];
        isBuildingMenuOpen = true;

        Gdx.app.postRunnable(() -> {
            Gdx.input.setInputProcessor(shopStage[0]);
        });
    }

    public static Resualt showAllProducts(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        if (player.getCurrentShop() == null)
            return new Resualt(false, "You are not currently in a shop.");
        for (ShopItem shopItem : player.getCurrentShop().getItems()) {
            if (shopItem instanceof ShopIngredient item) {
                response.append(item.getType().getName()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            } else if (shopItem instanceof ShopRecipe item) {
                response.append(item.getType().getName()).append(" ");
                response.append(item.getType().getSellPrice()).append("\n");
            } else if (shopItem instanceof ShopBarn item) {
                response.append(item.getType().getKind()).append(" ").append("\n");
                response.append(item.getType().getPrice());
            } else if (shopItem instanceof ShopAnimal item) {
                response.append(item.getType().getKind()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            } else if (shopItem instanceof ShopSeed item) {
                response.append(item.getType().getSeedName()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            } else if (shopItem instanceof ShopTool item) {
                response.append(item.getType()).append(" ");
                if (item.getType() == ToolType.FishingRod)
                    response.append("25\n");
                else
                    response.append("1000\n");
            }
        }
        for (ShopItem shopItem : player.getCurrentShop().getSeasonItems()) {
            if (shopItem instanceof ShopSeed item) {
                response.append(item.getType().getSeedName()).append(" ");
                response.append(item.getType().getPrice() * 3 / 2).append("\n");
            }
        }
        return new Resualt(true, response.toString());
    }

    public static Resualt showAllAvailableProducts(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        if (player.getCurrentShop() == null)
            return new Resualt(false, "You are not currently in a shop.");
        for (ShopItem shopItem : player.getCurrentShop().getItems()) {
            if (shopItem.getDailyLimit() == shopItem.getNumberOfSold())
                continue;
            if (shopItem instanceof ShopIngredient item) {
                response.append(item.getType().getName()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            } else if (shopItem instanceof ShopRecipe item) {
                response.append(item.getType().getName()).append(" ");
                response.append(item.getType().getSellPrice()).append("\n");
            } else if (shopItem instanceof ShopBarn item) {
                response.append(item.getType().getKind()).append(" ").append("\n");
                response.append(item.getType().getPrice());
            } else if (shopItem instanceof ShopAnimal item) {
                response.append(item.getType().getKind()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            } else if (shopItem instanceof ShopSeed item) {
                response.append(item.getType().getSeedName()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            } else if (shopItem instanceof ShopTool item) {
                response.append(item.getType()).append(" ");
                if (item.getType() == ToolType.FishingRod)
                    response.append("25\n");
                else
                    response.append("1000\n");
            }
        }
        for (ShopItem shopItem : player.getCurrentShop().getSeasonItems()) {
            if (shopItem instanceof ShopSeed item) {
                response.append(item.getType().getSeedName()).append(" ");
                response.append(item.getType().getPrice() * 3 / 2).append("\n");
            }
        }
        return new Resualt(true, response.toString());
    }

    public static Resualt Purchase(Command request, Shop shop) {
        String itemName = request.body.get("name");
        int amount = parseInt(request.body.get("amount"));
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        player.setCurrentShop(shop);
        player.setMoney(10000);
        if (player.getCurrentShop() == null)
            return new Resualt(false, "You are not currently in a shop.");
        for (ShopItem shopItem : player.getCurrentShop().getItems()) {
            if (shopItem instanceof ShopIngredient shopIngredient) {
                if (!shopIngredient.getType().getName().equals(itemName))
                    continue;
                if (amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Resualt(false, "Not enough items available.");
                if (player.getMoney() < amount * shopIngredient.getType().getPrice())
                    return new Resualt(false, "Not enough money.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopIngredient.getType().getPrice());

                Integer currentAmount = player.getInventory().getIngredients().get(shopIngredient.getType());
                Integer finalAmount = 0;
                if (currentAmount != null)
                    finalAmount = currentAmount;
                finalAmount += amount;
                player.getInventory().getIngredients().put(shopIngredient.getType(), finalAmount);
                return new Resualt(true, "Item bought successfully.");
            } else if (shopItem instanceof ShopSeed shopSeed) {
                if (!shopSeed.getType().getSeedName().equals(itemName))
                    continue;
                if (amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Resualt(false, "Not enough items available.");
                if (player.getMoney() < amount * shopSeed.getType().getPrice())
                    return new Resualt(false, "Not enough money.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopSeed.getType().getPrice());

                Integer currentAmount = player.getInventory().getSeeds().get(shopSeed.getType());
                Integer finalAmount = 0;
                if (currentAmount != null)
                    finalAmount = currentAmount;
                finalAmount += amount;
                player.getInventory().getSeeds().put(shopSeed.getType(), finalAmount);
                return new Resualt(true, "Item bought successfully.");
            } else if (shopItem instanceof ShopRecipe shopRecipe) {
                if (!shopRecipe.getType().getName().equals(itemName))
                    continue;
                if (amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Resualt(false, "Not enough items available.");
                if (player.getMoney() < amount * shopRecipe.getType().getSellPrice())
                    return new Resualt(false, "Not enough money.");
                if (player.getRecipes().contains(shopRecipe.getType()))
                    return new Resualt(false, "You have already learned this recipe.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopRecipe.getType().getSellPrice());
                player.getRecipes().add(shopRecipe.getType());
                return new Resualt(true, "Item bought successfully.");
            } else if (shopItem instanceof ShopTool shopTool) {
                if (!shopTool.getType().toString().equals(itemName))
                    continue;
                if (amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Resualt(false, "Not enough items available.");
                if (player.getMoney() < amount * shopTool.getPrice())
                    return new Resualt(false, "Not enough money.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopTool.getPrice());

                player.getInventory().getTools().put(new Tool(shopTool.getType(), ToolLevel.Initial), 1);
                return new Resualt(true, "Item bought successfully.");
            }

        }
        for (ShopItem shopItem : player.getCurrentShop().getSeasonItems()) {
            if (shopItem instanceof ShopSeed shopSeed) {
                if (!shopSeed.getType().getSeedName().equals(itemName))
                    continue;
                if (amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Resualt(false, "Not enough items available.");
                if (player.getMoney() < amount * shopSeed.getType().getPrice() * 3 / 2)
                    return new Resualt(false, "Not enough money.");
                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopSeed.getType().getPrice() * 3 / 2);

            }
        }
        return new Resualt(false, "No item found.");
    }

    public static Resualt Sell(Command request) {
        String itemName = request.body.get("name");
        int amount = parseInt(request.body.get("amount"));
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
       // Tile tile = null;
        ShippingBin shippingBin = null;
       /* for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                tile = player.getFarm().findCellByCoordinate(player.getPosition().getX() + i, player.getPosition().getY() + j);
                if (tile.getObject() instanceof ShippingBin bin)
                    shippingBin = bin;
            }
        if (shippingBin == null)
            return new Resualt(false, "You must be next to a shipping bin.");*/
        Random random = new Random();
        for (Ingredients ingredients : Ingredients.values())
            if (ingredients.getName().equals(itemName)) {
                if (ingredients.getType() == IngredientsTypes.junk || ingredients.getType() == IngredientsTypes.ore)
                    return new Resualt(false, "You can't sell these objects.");
                Integer number = player.getInventory().getIngredients().get(ingredients);
                if (number == null)
                    return new Resualt(false, "You don't have any of this item.");
                if (number < amount)
                    return new Resualt(false, "Item amount not enough in your inventory.");
                if (amount == 0)
                    amount = number;
                double quality = random.nextDouble(1, 2);
                player.setMoney(player.getMoney()+(int) floor(amount * ingredients.getPrice() * quality));
                if (number == amount)
                    player.getInventory().getIngredients().remove(ingredients);
                else
                    player.getInventory().getIngredients().put(ingredients, number - amount);
                return new Resualt(true, "Item sold successfully.");
            }
        return new Resualt(false, "You can't sell these objects.");
    }

    public static Resualt cheatAddMoney(Command request) {
        int amount = parseInt(request.body.get("amount"));
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        player.setMoney(player.getMoney() + amount);
        return new Resualt(true, "Money added successfully!");
    }

    public static Resualt showMoney(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        return new Resualt(true, "player money: " + player.getMoney());
    }
}
