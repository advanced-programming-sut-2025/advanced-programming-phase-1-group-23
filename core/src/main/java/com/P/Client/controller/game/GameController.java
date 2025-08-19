package com.P.Client.controller.game;


import com.P.Client.controller.*;
import com.P.Client.controller.ChatController;
import com.P.Main;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.game.GameModel;
import com.P.common.model.item.ItemDescriptionId;
import com.P.common.model.item.TileDescriptionId;
import com.P.Client.view.GameView.GameMenu;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.awt.*;

// 6. Enhanced Game Controller
public class GameController {
    private boolean escapePressed = false;
    private final Main game;
    private final TurnController mainController;
    private static GameMenu gameMenu;
    private Table table;
    private final FishingController fishingController = new FishingController();
    private final RanchingController animalController = new RanchingController();
    private final CookingController cookingController=new CookingController();
    private final InventoryFunctionsController inventoryController=new InventoryFunctionsController();
    private final ShoppingController shoppingController=new ShoppingController();
    private final CraftsmanshipController craftsmanshipController=new CraftsmanshipController();
    private final FarmingController farmingController=new FarmingController();
    private final ChatController chatController=new ChatController();


    public GameController(Main game, TurnController mainController) {
        this.game = game;
        this.mainController = mainController;
        table = new Table();
        table.setFillParent(true);
        table.left();
    }

    public void init() {
        gameMenu = new GameMenu(this);
    }

    public void run() {
        game.setScreen(gameMenu);
    }

//    public void goToMain() {
//        gameMenu.dispose();
//        mainController.run();
//    }

    public void useItem(ItemDescriptionId selectedItem, Point point, GameModel game) {
        TileDescriptionId selectedTile = game.getTile(point);
        if (!selectedItem.getAllowedTiles().contains(selectedTile)) {
            return;
        }

        game.getPlayer().useSelectedItem();
        selectedItem.getFunction().invoke(game, point);
    }

    public static void advanceToNextDay() {
        gameMenu.gameModel.advanceToNextDay();
        gameMenu.startSleepTransition();
    }

    public void update(GameModel model, Game game) {
        fishingController.update();
        animalController.update();
        animalController.render();
        cookingController.update(model);
        inventoryController.update(game);
        shoppingController.update();
        craftsmanshipController.update(model);
        farmingController.update(model);
        chatController.update(model,game);

        Sprite clock = App.getLoggedInUser().getCurrentGame().getClock().updateBatch(Main.getBatch());
        table.setPosition(clock.getX() + clock.getWidth() * 0.3f, clock.getY() - table.getHeight());

        //System.out.println("Weather today: " + App.loggedInUser.getCurrentGame().getWeatherToday().string());

        switch (App.loggedInUser.getCurrentGame().getWeatherToday().string()) {
            case "Snow" -> App.loggedInUser.getCurrentGame().getCurrentPlayer().getGameModel().handleSnowDrops();
            case "Rain" -> App.loggedInUser.getCurrentGame().getCurrentPlayer().getGameModel().handleRainDrops();
            case "Storm" -> App.loggedInUser.getCurrentGame().getCurrentPlayer().getGameModel().handleStorms();
        }

    }


}
