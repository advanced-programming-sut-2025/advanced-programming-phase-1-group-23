package com.P.Client.controller;

import com.P.Client.app.ClientApp;
import com.P.Client.model.GameAssetManager;
import com.P.Client.view.GameView.GameMenu;
import com.P.Client.view.GameView.GameView;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.Client.model.Command;
import com.P.common.model.Maps.Farm;
import com.P.common.model.Maps.Position;
import com.P.common.model.Maps.Tile;
import com.P.common.model.NPC.NPC;
import com.P.common.model.Naturals.Crop;
import com.P.common.model.Naturals.Tree;
import com.P.common.model.Objects.Animal;
import com.P.common.model.Objects.Inventory;
import com.P.common.model.Objects.Tool;
import com.P.common.model.Resualt;
import com.P.common.model.enums.*;
import com.P.common.model.game.GameModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.HashMap;
import java.util.Map;

public class InventoryFunctionsController extends ControllersController {

    public static Stage inventoryStage = null;
    public static boolean isInventoryOpen = false;
    public static Stage toolStage = null;
    public static boolean isToolsOpen = false;

    public void update(Game game) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            createInventoryMenu(game);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            createToolMenu();
        }
        if (isInventoryOpen && inventoryStage != null) {
            inventoryStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(inventoryStage);
            inventoryStage.draw();
        }
        if (isToolsOpen && toolStage != null) {
            toolStage.act(Gdx.graphics.getDeltaTime());
            Gdx.input.setInputProcessor(toolStage);
            toolStage.draw();
        }
    }

    public void createToolMenu() {
        final Stage[] buildingStage = {new Stage(new ScreenViewport())};
        Group menuGroup = new Group();
        Window window = new Window("Building Menu", GameAssetManager.SKIN);
        window.setSize(600, 400);
        window.setMovable(false);

        Table table = new Table();
        table.top().pad(20).defaults().pad(10);
        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isToolsOpen = false;
                Gdx.input.setInputProcessor(null);
                buildingStage[0].dispose();
                buildingStage[0] = null;
            }
        });

        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        for (Map.Entry<Tool, Integer> need : inventory.getTools().entrySet()) {
            Texture texture = GameAssetManager.getTexture(need.getKey());
            Image image = new Image(GameAssetManager.COAL);
            if (need.getKey().getToolType() != ToolType.planting && need.getKey().getToolType() != ToolType.fertility) {
                image = new Image(texture);
            }
            Label nameLabel = new Label(need.getKey().getToolType().toString(), GameAssetManager.SKIN);
            Label energyLabel = new Label("Quantity: " + inventory.getTools().get(need.getKey()), GameAssetManager.SKIN);
            Label priceLabel = new Label("Use Cost: " + need.getKey().getUseCost(), GameAssetManager.SKIN);
            TextButton cookButton = new TextButton("Equip", GameAssetManager.SKIN);
            cookButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Command n = new Command("cook");
                    n.body.put("toolName", need.getKey().getToolType().toString());
                    Resualt response = toolEquip(n);
                    Dialog dialog = new Dialog("", GameAssetManager.SKIN);
                    dialog.text(response.getAnswer());
                    dialog.button("OK");
                    dialog.show(buildingStage[0]);
                }
            });

            if (need.getKey().getToolType() != ToolType.planting && need.getKey().getToolType() != ToolType.fertility) {
                table.add(image).size(40, 40).left().padRight(10);
            }
            table.add(nameLabel).left().padRight(10);
            table.add(energyLabel).left().padRight(10);
            table.add(priceLabel).left();
            table.add(cookButton).right();
            table.row().padBottom(10);
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
                    (toolStage.getViewport().getScreenWidth() - window.getWidth()) / 2f,
                    (toolStage.getViewport().getScreenHeight() - window.getHeight()) / 2f
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

        InventoryFunctionsController.toolStage = buildingStage[0];
        isToolsOpen = true;

        Gdx.app.postRunnable(() -> {
            Gdx.input.setInputProcessor(buildingStage[0]);
        });
    }

    public void createInventoryMenu(Game game) {
        final Stage[] inventoryStage = {new Stage(new ScreenViewport())};

        Group menuGroup = new Group();
        Window window = new Window("Journal Menu", GameAssetManager.SKIN);
        window.setSize(1000, 600);
        window.setMovable(false);

        Table table = new Table();
        table.top().pad(20).defaults().pad(10);

        ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(GameAssetManager.EXIT_BUTTON)));
        exitButton.setSize(32, 32);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isInventoryOpen = false;
                Gdx.input.setInputProcessor(null);
                inventoryStage[0].dispose();
                inventoryStage[0] = null;
            }
        });
        TextButton thingsButton = new TextButton("Show Ingredients", GameAssetManager.SKIN);
        thingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Table things = creatThingsTable(inventoryStage);

                ScrollPane scrollPane = new ScrollPane(things, GameAssetManager.SKIN);
                scrollPane.setFadeScrollBars(false);
                scrollPane.setScrollingDisabled(false, false);
                scrollPane.setForceScroll(false, true);

                Dialog dialog = new Dialog("Show Inventory", GameAssetManager.SKIN);

                dialog.getContentTable().add(scrollPane).width(500).height(400);
                dialog.button("Close");
                dialog.show(inventoryStage[0]);
            }
        });

        TextButton skillButton = new TextButton("Show Skills", GameAssetManager.SKIN);
        skillButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Table skills = creatSkillssTable();

                Dialog dialog = new Dialog("Skills", GameAssetManager.SKIN);

                dialog.getContentTable().add(skills).width(500).height(400);
                dialog.button("Close");
                dialog.show(inventoryStage[0]);

            }
        });
        TextButton Map = new TextButton("Show Map", GameAssetManager.SKIN);
        Map.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameMenu.showAllMaps = !GameMenu.showAllMaps;

            }
        });

        TooltipManager tooltipManager = TooltipManager.getInstance();
        tooltipManager.initialTime = 0.5f;
        tooltipManager.hideAll();
        TextButton socialButton = new TextButton("Show Friends", GameAssetManager.SKIN);
        socialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Table whole = new Table();
                Table things = creatFriendsTable();
                whole.add(things).row();
                Table NPC;
                for (NPC npc : game.getNpcs()) {
                    System.out.println(npc.getName());
                    NPC = creatNPCTable(npc);
                    whole.add(NPC).row();
                }

                ScrollPane scrollPane = new ScrollPane(whole, GameAssetManager.SKIN);
                scrollPane.setFadeScrollBars(false);
                scrollPane.setScrollingDisabled(false, false);
                scrollPane.setForceScroll(false, true);

                Dialog dialog = new Dialog("Show Friends", GameAssetManager.SKIN);

                dialog.getContentTable().add(scrollPane).width(500).height(400);
                dialog.button("Close");
                dialog.show(inventoryStage[0]);
            }
        });
        TextButton mamorr = new TextButton("Show Quests", GameAssetManager.SKIN);
        mamorr.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Table things = new Table();
                Table NPC;
                for (NPC npc : game.getNpcs()) {
                    System.out.println(npc.getName());
                    NPC = creatmamor(npc);
                    things.add(NPC).row();
                }

                ScrollPane scrollPane = new ScrollPane(things, GameAssetManager.SKIN);
                scrollPane.setFadeScrollBars(false);
                scrollPane.setScrollingDisabled(false, false);
                scrollPane.setForceScroll(false, true);

                Dialog dialog = new Dialog("Show Quests", GameAssetManager.SKIN);

                dialog.getContentTable().add(scrollPane).width(500).height(400);
                dialog.button("Close");
                dialog.show(inventoryStage[0]);
            }
        });


        Label label = new Label("Welcome To Stardew Valley, This Is Your Journal", GameAssetManager.SKIN);
        table.add(label).row();

        TextButton exit = new TextButton("Exit game", GameAssetManager.SKIN);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BasicsController controller = new BasicsController();
                Resualt resualt = controller.isUserAdmin();
                if (!resualt.isAccept()) {
                    Dialog dialog = new Dialog("Show Quests", GameAssetManager.SKIN);
                    dialog.text("You are not Admin");
                    dialog.button("Close");
                    dialog.show(inventoryStage[0]);
                } else {
                    GameMenu.isExit = true;
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("isExist", "true");
                    ClientApp.getServerConnection().sendMessage(new Message(body, Message.MessageType.update));
                }
            }
        });
        TextButton kick = new TextButton("Kick people:)", GameAssetManager.SKIN);
        kick.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO YASAMAN

            }
        });
        Label s = new Label("Settings:", GameAssetManager.SKIN);
        table.add(s).left();
        table.add(kick).left();
        table.add(exit).row();
        ;

        table.add(thingsButton).colspan(5).center();
        table.row();
        table.add(skillButton).colspan(5).center();

        table.row();
        table.add(Map).colspan(5).row();
        table.add(socialButton).colspan(5).row();
        table.add(mamorr).colspan(5).row();

        window.setSize(1000, 600);
        window.add(table).expand().fill();

        Group group = new Group() {
            @Override
            public void act(float delta) {
                super.act(delta);
                window.setPosition(
                    (inventoryStage[0].getViewport().getScreenWidth() - window.getWidth()) / 2f,
                    (inventoryStage[0].getViewport().getScreenHeight() - window.getHeight()) / 2f
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
        inventoryStage[0].addActor(menuGroup);

        InventoryFunctionsController.inventoryStage = inventoryStage[0];
        isInventoryOpen = true;

        Gdx.app.postRunnable(() -> {
            Gdx.input.setInputProcessor(inventoryStage[0]);
        });
    }

    private void addTooltipToActor(Actor actor, String text) {
        Tooltip<Label> tooltip = new Tooltip<>(new Label(text, GameAssetManager.SKIN));
        actor.addListener(tooltip);
    }

    private Table creatmamor(NPC npc) {
        Table table = new Table();
        // table.setFillParent(true);
        table.top().pad(20).defaults().pad(10).left();

        String str = NPCController.ShowQuests(npc).getAnswer();
        final com.badlogic.gdx.scenes.scene2d.ui.Label responseLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("", GameAssetManager.SKIN);
        responseLabel.setText(npc.getName());
        table.add(responseLabel).colspan(2).width(600).left();

        table.row().pad(10, 0, 10, 0);
        final com.badlogic.gdx.scenes.scene2d.ui.Label questLabel = new Label("", GameAssetManager.SKIN);
        questLabel.setText(str);
        table.add(questLabel).width(600);
        table.row().pad(10, 0, 10, 0);
        return table;
        // return table;
    }

    private Table creatNPCTable(NPC npc) {
        Table table = new Table();
        // table.setFillParent(true);
        table.top().pad(20).defaults().pad(10).left();

        final com.badlogic.gdx.scenes.scene2d.ui.Label responseLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("", GameAssetManager.LABI_SKIN);
        responseLabel.setText(npc.getName());
        table.add(responseLabel).colspan(2).width(600).left();

        table.row().pad(10, 0, 10, 0);
        final com.badlogic.gdx.scenes.scene2d.ui.Label friendshipLabel = new Label("", GameAssetManager.LABI_SKIN);

        friendshipLabel.setText(NPCController.ShowFriendship(npc).getAnswer());
        table.add(friendshipLabel).width(600);
        table.row().pad(10, 0, 10, 0);

        final com.badlogic.gdx.scenes.scene2d.ui.Label questLabel = new Label("", GameAssetManager.LABI_SKIN);

        Resualt resualt = NPCController.ShowQuests(npc);
        questLabel.setText(resualt.getAnswer());
        table.add(questLabel).width(600);
        table.row().pad(10, 0, 10, 0);
        return table;
    }

    private Table creatFriendsTable() {
        Table table = new Table();
        // table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(10).left();

        int index = ClientApp.getTurnNumber();

        for (int i = 0; i < 4; i++) {
            String name = App.loggedInUser.getCurrentGame().getPlayers().get(i).getUser().getNickname();
            int friendship = App.loggedInUser.getCurrentGame().getFriendMatrix().get(index).get(i).getFriendShipLevel();


            Label label = new Label(friendship + " " + name, GameAssetManager.FRIEND_SKIN);
            //  label.setAlignment(Align.left);
            //  label.setColor(Color.DARK_GRAY);

            label.setFontScale(3f);
            table.add(label).width(600).left();
            table.row();
        }

        return table;
    }

    private Table creatThingsTable(Stage inventoryStage[]) {
        Table table = new Table();
        table.defaults().pad(5);

        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();

        Label s = new Label("Ingredients:", GameAssetManager.SKIN);
        table.add(s).left().padRight(10);
        table.row();

        for (Map.Entry<Ingredients, Integer> need : inventory.getIngredients().entrySet()) {
            Texture texture = need.getKey().getTexture();
            Image image = new Image(texture);
            Label nameLabel = new Label(need.getKey().getName(), GameAssetManager.SKIN);
            Label energyLabel = new Label("Quantity: " + inventory.getIngredients().get(need.getKey()), GameAssetManager.SKIN);
            Label priceLabel = new Label("Sell Price: " + need.getKey().getPrice(), GameAssetManager.SKIN);
            TextButton sell = new TextButton("Trash", GameAssetManager.SKIN);
            sell.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    final TextField quantityField = new TextField("", GameAssetManager.SKIN);
                    quantityField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

                    Dialog quantityDialog = new Dialog("Put item in TrashCan", GameAssetManager.SKIN) {
                        @Override
                        protected void result(Object object) {
                            if (object.equals(true)) {
                                Command n = new Command("trash");
                                n.body.put("Item", need.getKey().getName());
                                String input = quantityField.getText();
                                if (input.isEmpty()) input = "1";
                                n.body.put("n", input);

                                String rslt = useTrashCan(n).getAnswer();

                                Dialog resultDialog = new Dialog("Trash Result", GameAssetManager.SKIN);
                                resultDialog.text(rslt);
                                resultDialog.button("OK");
                                resultDialog.show(inventoryStage[0]);
                            }
                        }

                    };

                    quantityDialog.text("Enter quantity:");
                    quantityDialog.getContentTable().add(quantityField).row();

                    quantityDialog.button("Trash", true);
                    quantityDialog.button("Cancel", false);

                    quantityDialog.show(inventoryStage[0]);
                }
            });
            table.add(image).size(40, 40).left().padRight(10);
            table.add(nameLabel).left().padRight(10);
            table.add(energyLabel).left().padRight(10);
            table.add(priceLabel).left();
            table.add(sell).left();
            table.row().padBottom(10);
        }
        table.row();
        Label t = new Label("Tools:", GameAssetManager.SKIN);
        table.add(t).left().padRight(10);
        table.row();
        for (Map.Entry<Tool, Integer> need : inventory.getTools().entrySet()) {
            Texture texture = GameAssetManager.getTexture(need.getKey());
            Image image = new Image(GameAssetManager.COAL);
            if (!need.getKey().getToolType().equals(ToolType.planting) && !need.getKey().getToolType().equals(ToolType.fertility)) {
                image = new Image(texture);
            }
            Label nameLabel = new Label(need.getKey().getToolType().toString(), GameAssetManager.SKIN);
            Label energyLabel = new Label("Quantity: " + inventory.getTools().get(need.getKey()), GameAssetManager.SKIN);
            Label priceLabel = new Label("Use Cost: " + need.getKey().getUseCost(), GameAssetManager.SKIN);

            table.add(image).size(40, 40).left().padRight(10);
            table.add(nameLabel).left().padRight(10);
            table.add(energyLabel).left().padRight(10);
            table.add(priceLabel).left();
            table.row().padBottom(10);
        }
        table.row();
        Label r = new Label("Seeds:", GameAssetManager.SKIN);
        table.add(r).left().padRight(10);
        table.row();
        for (Map.Entry<ForAgingSeeds, Integer> need : inventory.getSeeds().entrySet()) {
            Label nameLabel = new Label(need.getKey().getSeedName(), GameAssetManager.SKIN);
            Image image = new Image(need.getKey().getTexture());
            Label energyLabel = new Label("Quantity: " + inventory.getSeeds().get(need.getKey()), GameAssetManager.SKIN);
            Label priceLabel = new Label("Price: " + need.getKey().getPrice(), GameAssetManager.SKIN);

            table.add(image).size(40, 40).left().padRight(10);
            table.add(nameLabel).left().padRight(10);
            table.add(energyLabel).left().padRight(10);
            table.add(priceLabel).left();
            table.row().padBottom(10);
        }

        return table;
    }

    public Table creatSkillssTable() {
        Table skills = new Table();
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();

        Image image1 = new Image(GameAssetManager.farmSkill);
        addTooltipToActor(image1, "Farming skill: increases by farming");
        Label farming = new Label("Farming Skill: " + player.getFarmingSkill(), GameAssetManager.SKIN);
        Label farmingL = new Label("Level: " + player.returnFarmingLevel(), GameAssetManager.SKIN);
        Image image2 = new Image(GameAssetManager.forageSkill);
        addTooltipToActor(image2, "Foraging skill: increases by foraging");

        Label forag = new Label("Foraging Skill: " + player.getForagingSkill(), GameAssetManager.SKIN);
        Label foragL = new Label("Level: " + player.returnForagingLevel(), GameAssetManager.SKIN);
        Image image3 = new Image(GameAssetManager.fishSkill);
        addTooltipToActor(image3, "Fishing skill: increases by fishing");

        Label fishing = new Label("Fishing Skill: " + player.getFishingSkill(), GameAssetManager.SKIN);
        Label fishL = new Label("Level: " + player.returnFishingLevel(), GameAssetManager.SKIN);
        Image image4 = new Image(GameAssetManager.mineSkill);
        addTooltipToActor(image4, "Mining skill: increases by mining");

        Label mine = new Label("Mining Skill: " + player.getMiningSkill(), GameAssetManager.SKIN);
        Label mineL = new Label("Level: " + player.returnMiningLevel(), GameAssetManager.SKIN);

        skills.defaults().pad(10);
        skills.add(image1).size(55).padRight(10);
        skills.add(farming).left();
        skills.add(farmingL).left();
        skills.row();

        skills.add(image2).size(55).padRight(10);
        skills.add(forag).left();
        skills.add(foragL).left();
        skills.row();

        skills.add(image3).size(55).padRight(10);
        skills.add(fishing).left();
        skills.add(fishL).left();
        skills.row();

        skills.add(image4).size(55).padRight(10);
        skills.add(mine).left();
        skills.add(mineL).left();
        skills.row();

        return skills;
    }

    public static Resualt showAllInInventory() {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        StringBuilder allTools = new StringBuilder();
        allTools.append("Tools: ").append("\n");
        for (Tool tool : inventory.getTools().keySet()) {
            allTools.append(tool.getToolType()).append(", ").append(tool.getToolLevel()).append("\n");
        }
        allTools.append("Items: ").append("\n");
        for (Ingredients ingredient : inventory.getIngredients().keySet()) {
            allTools.append(ingredient.getName()).append(": ").append(inventory.getIngredients().get(ingredient)).append("\n");
        }
        allTools.append("Seeds: ").append("\n");
        for (ForAgingSeeds seed : inventory.getSeeds().keySet()) {
            allTools.append(seed.getSeedName()).append(": ").append(inventory.getSeeds().get(seed));
        }
        return new Resualt(true, allTools.toString());

    }

    public static Resualt toolEquip(Command command) {
        String toolName = command.body.get("toolName");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        for (Tool tool : player.getInventory().getTools().keySet()) {
            if (tool.getToolType().toString().equals(toolName)) {
                player.setInHandTool(tool);
                return new Resualt(true, "Now the power is in your hands!");
            }
        }
        return new Resualt(false, "You don't have the tool in the backPack");
    }


    public static Resualt showInHandTool() {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        for (ToolType toolType : ToolType.values()) {
            if (player.getInHandTool().getToolType().equals(toolType)) {
                return new Resualt(true, toolType.toString());
            }
        }
        return new Resualt(false, "You don't have a tool in hand right now");

    }

    public static Resualt showAllTools() {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder allTools = new StringBuilder();
        for (Tool tool : player.getInventory().getTools().keySet()) {
            allTools.append(tool.getToolType()).append(", ").append(tool.getToolLevel()).append("\n");
        }
        return new Resualt(true, allTools.toString());
    }


    public static Resualt toolUpgrade(Command command) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        if (!player.getCurrentShop().getName().equals(ShopName.BlackSmith))
            return new Resualt(false, "You are not in blackSmith");
        Tool tool = player.getInHandTool();
        switch (tool.getToolType()) {
            case Axe, Hoe, Pickaxe, WateringCan -> {
                switch (tool.getToolLevel()) {
                    case Initial -> {
                        if (player.getMoney() < 2000) return new Resualt(false, "not enough money");
                        tool.setToolLevel(ToolLevel.Cooper);
                        player.setMoney(player.getMoney() - 2000);
                        return new Resualt(true, "Tool upgraded to Cooper");
                    }
                    case Cooper -> {
                        if (player.getMoney() < 5000) return new Resualt(false, "not enough money");
                        tool.setToolLevel(ToolLevel.Iron);
                        player.setMoney(player.getMoney() - 5000);
                        return new Resualt(true, "Tool Upgraded to Iron");
                    }
                    case Iron -> {
                        if (player.getMoney() < 10000) return new Resualt(false, "not enough money");
                        tool.setToolLevel(ToolLevel.Gold);
                        player.setMoney(player.getMoney() - 10000);
                        return new Resualt(true, "Tool upgraded to Gold");
                    }
                    case Gold -> {
                        if (player.getMoney() < 25000) return new Resualt(false, "not enough money");
                        tool.setToolLevel(ToolLevel.Iridium);
                        player.setMoney(player.getMoney() - 25000);
                        return new Resualt(true, "Tool upgraded to Iridium");
                    }
                    case Iridium, Learning, Bambou, FiberGlass -> {
                        return new Resualt(false, "Tool can't be upgraded anymore");
                    }

                }
            }

        }
        return new Resualt(false, "The inHandTool can't be upgraded");

    }

    public static Position findPositionByDirection(String direction, Position first) {
        int x = first.getX();
        int y = first.getY();
        switch (direction) {
            case "Right" -> {
                x++;
                return new Position(x, y);
            }
            case "Left" -> {
                x--;
                return new Position(x, y);
            }
            case "Up" -> {
                y++;
                return new Position(x, y);
            }
            case "Down" -> {
                y--;
                return new Position(x, y);
            }
            case "UpLeft" -> {
                y++;
                x--;
                return new Position(x, y);
            }
            case "UpRight" -> {
                x++;
                y++;
                return new Position(x, y);
            }
            case "DownLeft" -> {
                x--;
                y--;
                return new Position(x, y);
            }
            case "DownRight" -> {
                x++;
                y--;
                return new Position(x, y);
            }
        }
        return null;
    }

    public static Resualt useTool(Command request) {
        String direction = request.body.get("direction");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Tool tool = player.getInHandTool();
        Position position = findPositionByDirection(direction, player.getPosition());
        Tile tile = findTileByPosition(position, player.getFarm());

        if (tile == null) return new Resualt(false, "You are going out of the map!");
        if (tool != null) {
            if (player.getEnergy() < tool.getUseCost()) return new Resualt(false, "you don't have enough energy.");
            player.setEnergyUsed(player.getEnergyUsed() + tool.getUseCost());
            player.setEnergy(player.getEnergy() - tool.getUseCost());

            switch (tool.getToolType()) {
                case Axe -> {
                    return useAxe(position, tile);
                }
                case Pickaxe -> {
                    return usePickaxe(position, tile);
                }
                case Hoe -> {
                    return useHoe(position, tile);
                }
                case Scissors -> {
                    return useScissors(position, tile);
                }
                case Scythe -> {
                    return useScythe(position, tile);
                }
                case MilkingCan -> {
                    return useMilkingCan(position, tile);
                }
                case WateringCan -> {
                    return useWateringCan(position, tile);
                }

            }
        }
        return new Resualt(false, "No tool in your hand");
    }

    public static Tile findTileByPosition(Position position, Farm farm) {
        for (Tile tile : farm.getCells()) {
            if (tile.getCoordinate().equals(position)) {
                return tile;
            }
        }
        return null;
    }

    public static Resualt useAxe(Position position, Tile tile) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        if (tile.getObject() instanceof Tree) {
            //  tile.setObject(null);
            int exist = 0;
            for (Map.Entry<Ingredients, Integer> need : inventory.getIngredients().entrySet()) {
                if (need.getKey().equals(Ingredients.WOOD)) {
                    exist = 1;
                    break;
                }
            }
            if (exist != 0) {
                inventory.getIngredients().put(Ingredients.WOOD, inventory.getIngredients().get(Ingredients.WOOD));
            } else if (inventory.getCapacity() > 0) {
                inventory.setCapacity(inventory.getCapacity() - 1);
                inventory.getIngredients().put(Ingredients.WOOD, 1);
            }
            return new Resualt(true, "You have obtained some wood!");
        } else if (tile.getIngredients()==null) return new Resualt(false, "dfdfd");
        else if (tile.getIngredients().equals(Ingredients.WOOD)) {
            //    tile.setIngredients(null);
            return new Resualt(true, "The branch on the ground was destroyed.");
        }
        return new Resualt(false, "Axe cannot be used on this tile");

    }

    public static Resualt useHoe(Position position, Tile tile) {
        if (tile.getObject() == null) {
            tile.setTilled(true);
            return new Resualt(true, "You have Plowed the ground");
        }
        return new Resualt(false, "Go tend to your own garden!");
    }

    public static Resualt usePickaxe(Position position, Tile tile) {
        tile.setTilled(false);
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();

        if (tile.getIngredients() == null) return new Resualt(false, "there is nothing here to pickUp");
        ;
        if (tile.getIngredients().getType().equals(IngredientsTypes.mineral) || tile.getIngredients().getType().equals(IngredientsTypes.craftedObjects)) {
            tile.setIngredients(null);
            if (inventory.getIngredients().containsKey(tile.getIngredients())) {
                inventory.getIngredients().put(tile.getIngredients(), inventory.getIngredients().get(tile.getIngredients()) + 2);
            } else if (inventory.getCapacity() > 0) {
                inventory.setCapacity(inventory.getCapacity() - 1);
                inventory.getIngredients().put(tile.getIngredients(), 2);
            }
            player.setMiningSkill(player.getMiningSkill() + 10);
            if (player.returnMiningLevel() >= 2)
                inventory.getIngredients().put(tile.getIngredients(), inventory.getIngredients().get(tile.getIngredients()) + 1);
            return new Resualt(true, "You successfully picked up objects");
        }
        return new Resualt(false, "there is nothing here to pickUp");


    }


    public static Resualt useScythe(Position position, Tile tile) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if (tile.getObject()==null) return new Resualt(false, "endndnd");
        if (tile.getObject() instanceof Tree tree) {
            tile.setObject(null);
            if (tree.getDaysPassedSincePlanting() > tree.getTreeName().getTotalHarvestTime() && tree.getDaysPassedSinceHarvesting() >= tree.getTreeName().getFruitingCycle()) {
                tree.setDaysPassedSinceHarvesting(0);
                int exist = 0;
                for (Map.Entry<Ingredients, Integer> need : inventory.getIngredients().entrySet()) {
                    if (need.getKey().equals(tree.getTreeName().getFruitType())) {
                        exist = 1;
                        break;
                    }
                }
                if (exist != 0) {
                    inventory.getIngredients().put(tree.getTreeName().getFruitType(), inventory.getIngredients().get(tree.getTreeName().getFruitType()));
                } else if (inventory.getCapacity() > 0) {
                    inventory.setCapacity(inventory.getCapacity() - 1);
                    inventory.getIngredients().put(tree.getTreeName().getFruitType(), 1);
                }
                player.setFarmingSkill(player.getFarmingSkill() + 5);//TODO: set skill in ranching;
            }
            return new Resualt(true, "You've successfully harvested the tree");
        } else if (tile.getObject() instanceof Crop crop) {
            if (crop.getDaysPassedSincePlanting() > crop.getCropName().getTotalHarvestTime() && crop.getDaysPassedSinceHarvesting() >= crop.getCropName().getRegrowthTime()) {
                crop.setDaysPassedSinceHarvesting(0);
                int exist = 0;
                for (Map.Entry<Ingredients, Integer> need : inventory.getIngredients().entrySet()) {
                    if (need.getKey().equals(crop.getCropName().getIngredients())) {
                        exist = 1;
                        break;
                    }
                }
                if (exist != 0) {
                    inventory.getIngredients().put(crop.getCropName().getIngredients(), inventory.getIngredients().get(crop.getCropName().getIngredients()));
                } else if (inventory.getCapacity() > 0) {
                    inventory.setCapacity(inventory.getCapacity() - 1);
                    inventory.getIngredients().put(crop.getCropName().getIngredients(), 1);
                }
                player.setFarmingSkill(player.getFarmingSkill() + 5);

            }
            if (crop.getCropName().isOneTime()) tile.setObject(null);
            return new Resualt(true, "You've successfully harvested the crop");
        }
        return new Resualt(false, "You can't harvest anything here.");
    }

    public static Resualt useWateringCan(Position position, Tile tile) {
        Tool tool = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInHandTool();
        //if (/*tile.getObjectOnCell().type.equals("water")*/true) {
        switch (tool.getToolLevel()) {
            case Cooper -> tool.setIrrigationCapacity(55);
            case Iron -> tool.setIrrigationCapacity(70);
            case Initial -> tool.setIrrigationCapacity(40);
            case Gold -> tool.setIrrigationCapacity(85);
            case Iridium -> tool.setIrrigationCapacity(100);
        }

        // return new Resualt(true, "Now you have your watering can full of water");
        if (App.getLoggedInUser().getCurrentGame().getWeatherToday().equals(Weather.RAIN)) {
            return new Resualt(false, "You don't need to irrigate crops while raining");

        } else if (tile.getObject() instanceof Tree tree) {
            if (tool.getIrrigationCapacity() <= 0)
                return new Resualt(false, "You think the wateringCan is a fountain?? ");
            tree.setWateredToday(true);
            tool.setIrrigationCapacity(tool.getIrrigationCapacity() - 1);
            return new Resualt(true, "You have irrigated " + tree.getTreeName().getName() + " tree");
        } else if (tile.getObject() instanceof Crop crop) {
            if (tool.getIrrigationCapacity() <= 0)
                return new Resualt(false, "You think the wateringCan is a fountain?? ");
            crop.setWateredToday(true);
            tool.setIrrigationCapacity(tool.getIrrigationCapacity() - 1);
            return new Resualt(true, "You have irrigated " + crop.getCropName().getName() + " crop");
        }
        return new Resualt(false, "Watering Can cannot be used on this tile");
    }

    public static Resualt useTrashCan(Command command) {
        String trash = command.body.get("Item");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        String n = (command.body.get("n"));
        Ingredients ingredient = null;
        Inventory inventory = player.getInventory();
        //find item
        for (Ingredients ingredients : inventory.getIngredients().keySet()) {
            if (ingredients.getName().equals(trash)) {
                ingredient = ingredients;
                break;
            }
        }
        if (ingredient == null) return new Resualt(false, "You don't have the item in your inventory");
        if (n == null) {
            player.setMoney(player.getMoney() + (player.getTrashCan() * inventory.getIngredients().get(ingredient) * ingredient.getPrice() / 100));
            inventory.getIngredients().remove(ingredient);
            inventory.setCapacity(inventory.getCapacity() + 1);
            return new Resualt(true, "Item removed completely.");
        } else
            inventory.getIngredients().put(ingredient, (inventory.getIngredients().get(ingredient) - Integer.parseInt(n)));
        player.setMoney(player.getMoney() + (player.getTrashCan() * ingredient.getPrice() * Integer.parseInt(n) / 100));
        return new Resualt(true, "item removed.");

    }

    public static Resualt showEnergy(Command command) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if (player.getUnlimitedEnergy()) return new Resualt(true, "Energy unlimited");
        return new Resualt(true, "Energy: " + player.getEnergy());
    }//TODO: set energy in the morning
//TODO :max per turn for energy

    public static Resualt unlimitedEnergyCheat(Command command) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        player.setEnergy(100000);
        player.setUnlimitedEnergy(true);
        return new Resualt(true, "energy is unlimited now.");
    }

    public static Resualt setEnergyCheat(Command command) {
        String n = command.body.get("value");

        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        player.setEnergy(Integer.parseInt(n));
        return new Resualt(true, "energy is " + player.getEnergy() + "now.");
    }

    public static Resualt useScissors(Position position, Tile tile) {
        if (tile.getObject() instanceof Animal animal) {
            if (animal.getType() != AnimalType.Sheep)
                return new Resualt(false, "There is no sheep.");
            Command request = new Command("collect produce -n " + animal.getName());
            request.body.put("name", animal.getName());
            return RanchingController.CollectProduct(animal.getName());
        }
        return new Resualt(false, "There is no sheep.");
    }

    public static Resualt useMilkingCan(Position position, Tile tile) {
        if (tile.getObject() instanceof Animal animal) {
            if (animal.getType() != AnimalType.Cow)
                return new Resualt(false, "There is no cow.");
            Command request = new Command("collect produce -n " + animal.getName());
            request.body.put("name", animal.getName());
            return RanchingController.CollectProduct(animal.getName());
        }
        return new Resualt(false, "There is no cow.");
    }
}
