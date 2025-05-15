package controller;

import model.Basics.App;
import model.Basics.Player;
import model.Basics.Result;
import model.Command;
import model.Objects.Inventory;
import model.Resualt;
import model.enums.Ingredients;
import model.enums.Recipe;
//import org.h2.command.CommandContainer;

import java.util.Map;

public class CookingController extends ControllersController {
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
            refrigerator.setCapacity(refrigerator.getCapacity()+1);
            refrigerator.getIngredients().remove(item);
            return new Resualt(true, "Item successfully added to inventory");
        }
        return new Resualt(false, "Inventory is full");
    }

    public static Resualt eatDeliciousFood(Command command) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        String itemName = command.body.get("foodName");
        Ingredients food=null;
        for (Ingredients item1 : Ingredients.values()) {
            if (item1.getName().equals(itemName))
                food = item1;
        }
        if (food==null)return new Resualt(false, "Goshnegi housh o havaseto borde Haaa!!!");
        int exist = 0;
        for (Map.Entry<Ingredients, Integer> need : inventory.getIngredients().entrySet()) {
            if (need.getKey().equals(food)) {
                exist = 1;
                break;
            }
        }
        if (exist == 0) return new Resualt(false, "You don't have the food in the inventory");
        inventory.getIngredients().put(food, inventory.getIngredients().get(food)-1);
        if (inventory.getIngredients().get(food)==0){
            inventory.getIngredients().remove(food);
            inventory.setCapacity(inventory.getCapacity()+1);
        }
        player.setEnergy(player.getEnergy()+food.getEnergy());
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
            inventory.setCapacity(inventory.getCapacity()+1);
            return new Resualt(true, "Item successfully added to refrigerator");
        }
        return new Resualt(false, "Refrigerator is full");

    }

    public static Resualt showCookingRecipes() {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        StringBuilder recipes = new StringBuilder();
        for (Recipe recipe : player.getRecipes()) {
            if (recipe.isEatable()){
                recipes.append("Name: ").append(recipe.getName()).append("  Energy: ").append(recipe.getEnergy()).append("\n");

            }
        }

        if (!recipes.isEmpty()) return new Resualt(true, recipes.toString());
        return new Resualt(false, "You haven't learned any cooking recipes yet!");
    }

    public static Resualt showCraftingRecipes() {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        StringBuilder recipes = new StringBuilder();
        for (Recipe recipe : player.getRecipes()) {
            if (!recipe.isEatable()){
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

    public static Resualt startCraft(Command command) {
        String craftRecipe = command.body.get("recipeName");
        Recipe recipe = null;
        for (Recipe recipe1 : Recipe.values()) {
            if (recipe1.getName().equals(craftRecipe))
                recipe = recipe1;
        }
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if (!player.getRecipes().contains(recipe) || recipe == null) {
            return new Resualt(false, "You don't know this recipe.");
        }
        for (Map.Entry<Ingredients, Integer> provided : recipe.getIngredients().entrySet()) {
            for (Map.Entry<Ingredients, Integer> needed : player.getInventory().getIngredients().entrySet()) {
                if (needed.getKey() == provided.getKey()) {
                    if (needed.getValue() < provided.getValue()) {
                        return new Resualt(false, "You don't have enough ingredients");
                    }
                }

            }
        }
        if (player.getInventory().getCapacity() <= 0) return new Resualt(false, "Your inventory is full!");
        if (player.getEnergy() <= 2) {
            player.setFainted(true);
            return new Resualt(false, "You don't have enough energy to craft this.");
        }
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
        return new Resualt(true, "New crafting was crafted!");
    }
    public static Resualt putItem(Command command){return null;};

    public static Resualt CheatAddInventory(Command command){return  null;}

    //TODO bye recipes;
}
