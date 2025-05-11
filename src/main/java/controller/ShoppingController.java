package src.main.java.controller;
import model.Basics.Result;
import model.Command;
import model.Basics.Player;
import model.Basics.App;
import src.main.java.model.Objects.*;

import static java.lang.Integer.parseInt;

public class ShoppingController {
    public Result showAllProducts() {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        if(player.getCurrentShop() == null)
            return new Result(false, "You are not currently in a shop.");
        for(ShopItem shopItem : player.getCurrentShop().getItems()) {
            if(shopItem instanceof ShopIngredient item) {
                response.append(item.getType().getName()).append(" ");
                response.append(item.getType().getPrice());
            }
            else if(shopItem instanceof ShopRecipe item) {
                response.append(item.getType().getName()).append(" ");
                response.append(item.getType().getSellPrice());
            }
            else if(shopItem instanceof ShopBarn item) {
                response.append(item.getType().getKind()).append(" ");
                response.append(item.getType().getPrice());
            }
            else if(shopItem instanceof ShopAnimal item) {
                response.append(item.getType().getKind()).append(" ");
                response.append(item.getType().getPrice());
            }
            else if(shopItem instanceof ShopSeed item) {
                response.append(item.getType().getSeedName()).append(" ");
                response.append(item.getType().getPrice());
            }
            else if(shopItem instanceof ShopTool item) {
                response.append(item.getType()).append(" ");
                response.append(item.getType().getPrice());
            }
            //TODO : Add other types;
        }
        //TODO : show season items
        return new Result(true, response.toString());
    }

    public Result showAllAvailableProducts(Command request) {
        //TODO : After completing showAll
        return null;
    }

    public Result Purchase(Command request) {
        return null;
    }

    public Result Sell(Command request) {
        return null;
    }

    public Result cheatAddMoney(Command request) {
        int amount = parseInt(request.body.get("amount"));
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        player.setMoney(player.getMoney() + amount);
        return new Result(true, "Money added successfully!");
    }
}
