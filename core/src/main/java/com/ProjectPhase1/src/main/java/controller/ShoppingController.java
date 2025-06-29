package controller;
import model.Basics.Result;
import model.Command;
import model.Basics.Player;
import model.Basics.App;
import model.Resualt;
import model.enums.IngredientsTypes;
import model.enums.ToolLevel;
import model.Objects.Tool;
import model.enums.ToolType;
import model.Objects.*;
import model.enums.Ingredients;
import model.Maps.Tile;

import java.util.Random;

import static java.lang.Integer.parseInt;
import static java.lang.Math.floor;

public class ShoppingController {
    public static Resualt showAllProducts(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        if(player.getCurrentShop() == null)
            return new Resualt(false, "You are not currently in a shop.");
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
        return new Resualt(true, response.toString());
    }

    public static Resualt showAllAvailableProducts(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        if(player.getCurrentShop() == null)
            return new Resualt(false, "You are not currently in a shop.");
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
        return new Resualt(true, response.toString());
    }

    public static Resualt Purchase(Command request) {
        String itemName = request.body.get("name");
        int amount = parseInt(request.body.get("amount"));
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if(player.getCurrentShop() == null)
            return new Resualt(false, "You are not currently in a shop.");
        for(ShopItem shopItem : player.getCurrentShop().getItems()) {
            if(shopItem instanceof ShopIngredient shopIngredient) {
                if(!shopIngredient.getType().getName().equals(itemName))
                    continue;
                if(amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Resualt(false, "Not enough items available.");
                if(player.getMoney() < amount * shopIngredient.getType().getPrice())
                    return new Resualt(false, "Not enough money.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopIngredient.getType().getPrice());

                Integer currentAmount = player.getInventory().getIngredients().get(shopIngredient.getType());
                Integer finalAmount = 0;
                if(currentAmount != null)
                    finalAmount = currentAmount;
                finalAmount += amount;
                player.getInventory().getIngredients().put(shopIngredient.getType(), finalAmount);
                return new Resualt(true, "Item bought successfully.");
            }
            else if(shopItem instanceof ShopSeed shopSeed) {
                if(!shopSeed.getType().getSeedName().equals(itemName))
                    continue;
                if(amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Resualt(false, "Not enough items available.");
                if(player.getMoney() < amount * shopSeed.getType().getPrice())
                    return new Resualt(false, "Not enough money.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopSeed.getType().getPrice());

                Integer currentAmount = player.getInventory().getSeeds().get(shopSeed.getType());
                Integer finalAmount = 0;
                if(currentAmount != null)
                    finalAmount = currentAmount;
                finalAmount += amount;
                player.getInventory().getSeeds().put(shopSeed.getType(), finalAmount);
                return new Resualt(true, "Item bought successfully.");
            }
            else if(shopItem instanceof ShopRecipe shopRecipe) {
                if(!shopRecipe.getType().getName().equals(itemName))
                    continue;
                if(amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Resualt(false, "Not enough items available.");
                if(player.getMoney() < amount * shopRecipe.getType().getSellPrice())
                    return new Resualt(false, "Not enough money.");
                if(player.getRecipes().contains(shopRecipe.getType()))
                    return new Resualt(false, "You have already learned this recipe.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopRecipe.getType().getSellPrice());
                player.getRecipes().add(shopRecipe.getType());
                return new Resualt(true, "Item bought successfully.");
            }
            else if(shopItem instanceof ShopTool shopTool) {
                if(!shopTool.getType().toString().equals(itemName))
                    continue;
                if(amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Resualt(false, "Not enough items available.");
                if(player.getMoney() < amount * shopTool.getPrice())
                    return new Resualt(false, "Not enough money.");

                shopItem.increaseNumberOfSold(amount);
                player.setMoney(player.getMoney() - amount * shopTool.getPrice());

                player.getInventory().getTools().put(new Tool(shopTool.getType(), ToolLevel.Initial), 1);
                return new Resualt(true, "Item bought successfully.");
            }

        }
        for(ShopItem shopItem : player.getCurrentShop().getSeasonItems()) {
            if(shopItem instanceof ShopSeed shopSeed) {
                if(!shopSeed.getType().getSeedName().equals(itemName))
                    continue;
                if(amount > (shopItem.getDailyLimit() - shopItem.getNumberOfSold()))
                    return new Resualt(false, "Not enough items available.");
                if(player.getMoney() < amount * shopSeed.getType().getPrice() * 3 / 2)
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
        Tile tile = null;
        ShippingBin shippingBin = null;
        for(int i = -1; i <= 1; i++)
            for(int j = -1; j <= 1; j++) {
                tile = player.getFarm().findCellByCoordinate(player.getPosition().getX() + i, player.getPosition().getY() + j);
                if(tile.getObject() instanceof ShippingBin bin)
                    shippingBin = bin;
            }
        if(shippingBin == null)
            return new Resualt(false, "You must be next to a shipping bin.");
        Random random = new Random();
        for(Ingredients ingredients : Ingredients.values())
            if(ingredients.getName().equals(itemName)) {
                if(ingredients.getType() == IngredientsTypes.junk || ingredients.getType() == IngredientsTypes.ore)
                    return new Resualt(false, "You can't sell these objects.");
                Integer number = player.getInventory().getIngredients().get(ingredients);
                if(number == null)
                    return new Resualt(false, "You don't have any of this item.");
                if(number < amount)
                    return new Resualt(false, "Item amount not enough in your inventory.");
                if(amount == 0)
                    amount = number;
                double quality = random.nextDouble(1, 2);
                shippingBin.addTotalMoney((int)floor(amount * ingredients.getPrice() * quality));
                if(number == amount)
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
