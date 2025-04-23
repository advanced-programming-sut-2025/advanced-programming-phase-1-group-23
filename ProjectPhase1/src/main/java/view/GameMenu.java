package view;

import controller.*;

import java.util.Scanner;

public class GameMenu implements AppMenu {
    private BuildController buildController = new BuildController();
    private CookingController cookingController = new CookingController();
    private CraftsmanshipController craftsmanshipController = new CraftsmanshipController();
    private FarmingController farmingController = new FarmingController();
    private FriendshipController friendshipController = new FriendshipController();
    private InventoryFunctionsController inventoryFunctionsController = new InventoryFunctionsController();
    private LivestockController livestockController = new LivestockController();
    private TimeController timeController = new TimeController();
    private TransactionController transactionController = new TransactionController();
    private TurnController turnController = new TurnController();
    private WeatherController weatherController = new WeatherController();
    public void check(Scanner scanner){

    }
}
