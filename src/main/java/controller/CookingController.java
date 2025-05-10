package controller;

import model.Basics.App;
import model.Basics.Player;
import model.Basics.Result;
import model.Command;
import model.enums.Ingredients;
import model.enums.Recipe;

import java.util.Map;

public class CookingController extends ControllersController {
    public Result getFromRefrigerator(String command) {
        return null;
    }

    public Result putInRefrigerator(String command) {
        return null;
    }

    public Result showRecipes() {
        Player player= App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        StringBuilder recipes=new StringBuilder();
        for (Recipe recipe: player.getRecipes()){
            recipes.append("Name: ").append(recipe.getName()).append("  Energy: ").append(recipe.getEnergy()).append("\n");
        }

        if (!recipes.isEmpty())return new Result(true, recipes.toString());
        return new Result(false, "You haven't learned any recipes yet!");
    }

    public Result cookFood (Command command){
        String foodRecipe=command.body.get("recipeName");
        Recipe recipe=null;
        for (Recipe recipe1:Recipe.values()){
            if (recipe1.getName().equals(foodRecipe))
                recipe=recipe1;
        }
        Player player=App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if (!player.getRecipes().contains(recipe) || recipe==null){
            return new Result(false,"You don't know the recipe.");
        }
        for (Map.Entry<Ingredients, Integer> provided: recipe.getIngredients().entrySet()){
            for (Map.Entry<Ingredients, Integer> needed:player.getInventory().getIngredients().entrySet()){
                if (needed.getKey()==provided.getKey()){
                    if (needed.getValue()<provided.getValue()){
                        return new Result(false,"You don't have enough ingredients");
                    }
                }

            }
            for (Map.Entry<Ingredients, Integer> needed:player.getRefrigerator().getIngredients().entrySet()){
                if (needed.getKey()==provided.getKey()){
                    if (needed.getValue()<provided.getValue()){
                        return new Result(false,"You don't have enough ingredients");
                    }
                }

            }
        } // Now we can Start cooking
        for (Map.Entry<Ingredients, Integer> provided: recipe.getIngredients().entrySet()){
            for (Map.Entry<Ingredients, Integer> needed:player.getInventory().getIngredients().entrySet()){
                if (needed.getKey()==provided.getKey()){
                    player.getInventory().getIngredients().put(needed.getKey(), needed.getValue()-provided.getValue());
                }

            }
            for (Map.Entry<Ingredients, Integer> needed:player.getRefrigerator().getIngredients().entrySet()){
                if (needed.getKey()==provided.getKey()){
                    player.getRefrigerator().getIngredients().put(needed.getKey(), needed.getValue()-provided.getValue());
                }

            }
        }
        player.setEnergy(player.getEnergy()-3);



    }
}
