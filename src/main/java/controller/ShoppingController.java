package src.main.java.controller;
import model.Basics.Result;
import model.Command;
import model.Basics.Player;
import model.Basics.App;
import model.enums.ToolLevel;
import model.Objects.Tool;
import model.enums.ToolType;
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
                response.append(item.getType().getPrice()).append("\n");
            }
            else if(shopItem instanceof ShopRecipe item) {
                response.append(item.getType().getName()).append(" ");
                response.append(item.getType().getSellPrice()).append("\n");
            }
            else if(shopItem instanceof ShopBarn item) {
                response.append(item.getType().getKind()).append(" ").append("\n");
                response.append(item.getType().getPrice());
            }
            else if(shopItem instanceof ShopAnimal item) {
                response.append(item.getType().getKind()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            }
            else if(shopItem instanceof ShopSeed item) {
                response.append(item.getType().getSeedName()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            }
            else if(shopItem instanceof ShopTool item) {
                response.append(item.getType()).append(" ");
                if(item.getType() == ToolType.FishingRod)
                    response.append("25\n");
                else
                    response.append("1000\n");
            }
        }
        for(ShopItem shopItem : player.getCurrentShop().getSeasonItems()) {
            if(shopItem instanceof ShopSeed item) {
                response.append(item.getType().getSeedName()).append(" ");
                response.append(item.getType().getPrice() * 3 / 2).append("\n");
            }
        }
        return new Result(true, response.toString());
    }

    public Result showAllAvailableProducts(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        if(player.getCurrentShop() == null)
            return new Result(false, "You are not currently in a shop.");
        for(ShopItem shopItem : player.getCurrentShop().getItems()) {
            if(shopItem.getDailyLimit() == shopItem.getNumberOfSold())
                continue;
            if(shopItem instanceof ShopIngredient item) {
                response.append(item.getType().getName()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            }
            else if(shopItem instanceof ShopRecipe item) {
                response.append(item.getType().getName()).append(" ");
                response.append(item.getType().getSellPrice()).append("\n");
            }
            else if(shopItem instanceof ShopBarn item) {
                response.append(item.getType().getKind()).append(" ").append("\n");
                response.append(item.getType().getPrice());
            }
            else if(shopItem instanceof ShopAnimal item) {
                response.append(item.getType().getKind()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            }
            else if(shopItem instanceof ShopSeed item) {
                response.append(item.getType().getSeedName()).append(" ");
                response.append(item.getType().getPrice()).append("\n");
            }
            else if(shopItem instanceof ShopTool item) {
                response.append(item.getType()).append(" ");
                if(item.getType() == ToolType.FishingRod)
                    response.append("25\n");
                else
                    response.append("1000\n");
            }
        }
        for(ShopItem shopItem : player.getCurrentShop().getSeasonItems()) {
            if(shopItem instanceof ShopSeed item) {
                response.append(item.getType().getSeedName()).append(" ");
                response.append(item.getType().getPrice() * 3 / 2).append("\n");
            }
        }
        return new Result(true, response.toString());
    }

    public Result Purchase(Command request) {
        String itemName = request.body.get("name");
        int amount = parseInt(request.body.get("amount"));
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if(player.getCurrentShop() == null)
            return new Result(false, "You are not currently in a shop.");
        for(ShopItem shopItem : player.getCurrentShop().getItems()) {
            if(shopItem instanceof ShopIngredient shopIngredient) {
                if(!shopIngredient.getType().getName().equals(itemName))
                    continue;
                if(amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Result(false, "Not enough items available.");
                if(player.getMoney() < amount * shopIngredient.getType().getPrice())
                    return new Result(false, "Not enough money.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopIngredient.getType().getPrice());

                Integer currentAmount = player.getInventory().getIngredients().get(shopIngredient.getType());
                Integer finalAmount = 0;
                if(currentAmount != null)
                    finalAmount = currentAmount;
                finalAmount += amount;
                player.getInventory().getIngredients().put(shopIngredient.getType(), finalAmount);
                return new Result(true, "Item bought successfully.");
            }
            else if(shopItem instanceof ShopSeed shopSeed) {
                if(!shopSeed.getType().getSeedName().equals(itemName))
                    continue;
                if(amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Result(false, "Not enough items available.");
                if(player.getMoney() < amount * shopSeed.getType().getPrice())
                    return new Result(false, "Not enough money.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopSeed.getType().getPrice());

                Integer currentAmount = player.getInventory().getSeeds().get(shopSeed.getType());
                Integer finalAmount = 0;
                if(currentAmount != null)
                    finalAmount = currentAmount;
                finalAmount += amount;
                player.getInventory().getSeeds().put(shopSeed.getType(), finalAmount);
                return new Result(true, "Item bought successfully.");
            }
            else if(shopItem instanceof ShopRecipe shopRecipe) {
                if(!shopRecipe.getType().getName().equals(itemName))
                    continue;
                if(amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Result(false, "Not enough items available.");
                if(player.getMoney() < amount * shopRecipe.getType().getSellPrice())
                    return new Result(false, "Not enough money.");
                if(player.getRecipes().contains(shopRecipe.getType()))
                    return new Result(false, "You have already learned this recipe.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopRecipe.getType().getSellPrice());
                player.getRecipes().add(shopRecipe.getType());
                return new Result(true, "Item bought successfully.");
            }
            else if(shopItem instanceof ShopTool shopTool) {
                if(!shopTool.getType().toString().equals(itemName))
                    continue;
                if(amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Result(false, "Not enough items available.");
                if(player.getMoney() < amount * shopTool.getPrice())
                    return new Result(false, "Not enough money.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopTool.getPrice());

                player.getInventory().getTools().put(new Tool(shopTool.getType(), ToolLevel.Initial), 1);
                return new Result(true, "Item bought successfully.");
            }

        }
        for(ShopItem shopItem : player.getCurrentShop().getSeasonItems()) {
            if(shopItem instanceof ShopSeed shopSeed) {
                if(!shopSeed.getType().getSeedName().equals(itemName))
                    continue;
                if(amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Result(false, "Not enough items available.");
                if(player.getMoney() < amount * shopSeed.getType().getPrice() * 3 / 2)
                    return new Result(false, "Not enough money.");
                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopSeed.getType().getPrice() * 3 / 2);

            }
        }
        return new Result(false, "No item found.");
    }

    public Result Sell(Command request) {

    }

    public Result cheatAddMoney(Command request) {
        int amount = parseInt(request.body.get("amount"));
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        player.setMoney(player.getMoney() + amount);
        return new Result(true, "Money added successfully!");
    }
}
