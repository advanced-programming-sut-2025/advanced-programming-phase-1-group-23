package controller;

import model.Basics.App;
import model.Basics.Player;
import model.Basics.Result;
import model.Command;
import model.Maps.Position;
import model.Maps.Tile;
import model.Objects.CraftingMachine;
import model.Objects.Inventory;
import model.enums.Ingredients;
import model.enums.IngredientsTypes;

import java.rmi.server.RemoteRef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CraftsmanshipController extends ControllersController {
    public Result useCraftingMachine(Command command) {
        String machineName = command.body.get("itemName");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        CraftingMachine machine = null;
        for (Tile tile : player.getFarm().getCells()) {
            if (tile.getMachine().getType().getName().equals(machineName)) {
                int a = calculateDistance(player.getPosition(), tile.getCoordinate());
                if (a > 1) {
                    return new Result(false, "not next to the machine.");
                }
                machine = tile.getMachine();
                break;
            }
        }
        if (machine == null) return new Result(false, "You don't have this machine");
        switch (machine.getType()) {
            case BEE_HOUSE -> {
                return useBeeHouse(command, machine);
            }
            case CHEESE_PRESS -> {
                return useCheesePress(command, machine);
            }
            case KEG -> {
                return useKeg(command, machine);
            }
            case DEHYDRATOR -> {
                return useDehydrator(command, machine);
            }
            case CHARCOAL_KLIN -> {
                return useKlin(command, machine);
            }
            case LOOM -> {
                return useLoom(command, machine);
            }
            case MAYONNAISE_MACHINE -> {
                return useMayonnaise(command, machine);
            }
            case OIL_MAKER -> {
                return useOilMaker(command, machine);
            }
            case PRESERVES_JAR -> {
                return usePreserver(command, machine);
            }
            case FISH_SMOKER -> {
                return useFishSmoker(command, machine);
            }
            case FURNACE -> {
                return useFurnace(command, machine);
            }
        }
        return new Result(false, "This object can not be used!");
    }

    public Result getCraftingMachineProduct(Command command) {
        String machineName = command.body.get("itemName");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        CraftingMachine machine = null;
        for (Tile tile : player.getFarm().getCells()) {
            if (tile.getMachine().getType().getName().equals(machineName)) {
                int a = calculateDistance(player.getPosition(), tile.getCoordinate());
                if (a > 1) {
                    return new Result(false, "not next to the machine.");
                }
                machine = tile.getMachine();
                break;
            }
        }
        if (machine == null) return new Result(false, "You don't have this machine");
        switch (machine.getType()) {
            case BEE_HOUSE -> {
                return getBeeHouse(command, machine);
            }
            case CHEESE_PRESS -> {
                return getCheesePress(command, machine);
            }
            case KEG -> {
                return getKeg(command, machine);
            }
            case DEHYDRATOR -> {
                return getDehydrator(command, machine);
            }
            case CHARCOAL_KLIN -> {
                return getKlin(command, machine);
            }
            case LOOM -> {
                return getLoom(command, machine);
            }
            case MAYONNAISE_MACHINE -> {
                return getMayonnaise(command, machine);
            }
            case OIL_MAKER -> {
                return getOilMaker(command, machine);
            }
            case PRESERVES_JAR -> {
                return getPreserver(command, machine);
            }
            case FISH_SMOKER -> {
                return getFishSmoker(command, machine);
            }
            case FURNACE -> {
                return getFurnace(command, machine);
            }
        }
        return new Result(false, "This object can not be used!");
    }

    //TODO: increase processingHours function

    //TODO: getting product from machine function


    public Result useBeeHouse(Command command, CraftingMachine machine) {
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        if (list.size() > 1) return new Result(false, "Wrong input.");
        machine.setWorking(true);
        machine.setProduct(Ingredients.HONEY);
        return new Result(true, "Started making Honey");

    }

    public int calculateDistance(Position start, Position end) {
        return (Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY()));
    }

    public Result useCheesePress(Command command, CraftingMachine machine) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        for (String s : list) {
            if (!(s.equals("Milk") || s.equals("GoatMilk"))) return new Result(false, "Wrong input.");
        }
        if (list.getFirst().equals("Milk") && inventory.getIngredients().get(Ingredients.MILK) > 1) {
            if (inventory.getIngredients().get(Ingredients.MILK) == 1) {
                inventory.getIngredients().remove(Ingredients.MILK);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.MILK, inventory.getIngredients().get(Ingredients.MILK) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Cheese);
        } else if (list.getFirst().equals("Milk")) return new Result(false, "not enough ingredients");

        if (list.getFirst().equals("GoatMilk") && inventory.getIngredients().get(Ingredients.GOAT_MILK) > 1) {
            if (inventory.getIngredients().get(Ingredients.GOAT_MILK) == 1) {
                inventory.getIngredients().remove(Ingredients.GOAT_MILK);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.GOAT_MILK, inventory.getIngredients().get(Ingredients.GOAT_MILK) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.GoatCheese);
        }else if (list.getFirst().equals("GoatMilk")) return new Result(false, "not enough ingredients");
        return new Result(true, "Started making Cheese");
    }

    public Result useKeg(Command command, CraftingMachine machine) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        if (list.size()>2)return new Result(false,"Wrong input.");
        Ingredients ingredient=null;
        for (Ingredients ingredients1: Ingredients.values()){
            if (ingredients1.getName().equals(list.getFirst()))ingredient=ingredients1;
        }
        if (ingredient.equals(Ingredients.WHEAT) && inventory.getIngredients().get(ingredient) >= 1){
            if (inventory.getIngredients().get(Ingredients.WHEAT) == 1) {
                inventory.getIngredients().remove(Ingredients.WHEAT);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.WHEAT, inventory.getIngredients().get(Ingredients.WHEAT) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Beer);
            return new Result(true, "Started making.");
        }else if (ingredient.equals(Ingredients.RICE) && inventory.getIngredients().get(ingredient) >= 1){
            if (inventory.getIngredients().get(Ingredients.RICE) == 1) {
                inventory.getIngredients().remove(Ingredients.RICE);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.RICE, inventory.getIngredients().get(Ingredients.RICE) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.VINEGAR);
            return new Result(true, "Started making.");
        }else if (ingredient.equals(Ingredients.COFFEE_BEAN) && inventory.getIngredients().get(ingredient) >= 5){
            if (inventory.getIngredients().get(Ingredients.COFFEE_BEAN) == 5) {
                inventory.getIngredients().remove(Ingredients.COFFEE_BEAN);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.COFFEE_BEAN, inventory.getIngredients().get(Ingredients.COFFEE_BEAN) - 5);
            machine.setWorking(true);
            machine.setProduct(Ingredients.COFFEE);
            return new Result(true, "Started making.");
        }else if (ingredient.getType().equals(IngredientsTypes.vegetable) && inventory.getIngredients().get(ingredient) >= 1){
            if (inventory.getIngredients().get(ingredient) == 1) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Juice);
            return new Result(true, "Started making.");
        }else if (ingredient.equals(Ingredients.HONEY) && inventory.getIngredients().get(ingredient) >= 1){
            if (inventory.getIngredients().get(ingredient) == 1) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Maed);
            return new Result(true, "Started making.");
        }else if (ingredient.equals(Ingredients.HOPS) && inventory.getIngredients().get(ingredient) >= 1){
            if (inventory.getIngredients().get(ingredient) == 1) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(Ingredients.WHEAT, inventory.getIngredients().get(Ingredients.WHEAT) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.PaleAle);
            return new Result(true, "Started making.");
        }else if (ingredient.getType().equals(IngredientsTypes.fruit) && inventory.getIngredients().get(ingredient) >= 1){
            if (inventory.getIngredients().get(ingredient) == 1) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 1);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Wine);
            return new Result(true, "Started making.");
        }
        return new Result(false, "Not enough ingredients.");
    }

    public Result useDehydrator(Command command, CraftingMachine machine) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        if (list.size()>2)return new Result(false,"Wrong input.");
        Ingredients ingredient=null;
        for (Ingredients ingredients1: Ingredients.values()){
            if (ingredients1.getName().equals(list.getFirst()))ingredient=ingredients1;
        }
        if ((ingredient.equals(Ingredients.COMMON_MUSHROOM)|| ingredient.equals(Ingredients.RED_MUSHROOM) || ingredient.equals(Ingredients.PURPLE_MUSHROOM)) && inventory.getIngredients().get(ingredient) >= 5){
            if (inventory.getIngredients().get(ingredient) == 5) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 5);
            machine.setWorking(true);
            machine.setProduct(Ingredients.DriedMushroom);
            return new Result(true, "Started making.");
        }else if ((ingredient.getType().equals(IngredientsTypes.fruit)) && inventory.getIngredients().get(ingredient) >= 5){
            if (inventory.getIngredients().get(ingredient) == 5) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 5);
            machine.setWorking(true);
            machine.setProduct(Ingredients.DriedFruit);
            return new Result(true, "Started making.");
        }else if ((ingredient.equals(Ingredients.GRAPE)) && inventory.getIngredients().get(ingredient) >= 5){
            if (inventory.getIngredients().get(ingredient) == 5) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 5);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Raisins);
            return new Result(true, "Started making.");
        }
        return new Result(false, "Not enough ingredients");
    }

    public Result useKlin(Command command, CraftingMachine machine) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        if (list.size()>2)return new Result(false,"Wrong input.");
        Ingredients ingredient=null;
        for (Ingredients ingredients1: Ingredients.values()){
            if (ingredients1.getName().equals(list.getFirst()))ingredient=ingredients1;
        }
        if ((ingredient.equals(Ingredients.WOOD)) && inventory.getIngredients().get(ingredient) >= 10){
            if (inventory.getIngredients().get(ingredient) == 10) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 10);
            machine.setWorking(true);
            machine.setProduct(Ingredients.COAL);
            return new Result(true, "Started making.");
        }return new Result(false,"not enough ingredients");
    }

    public Result useLoom(Command command, CraftingMachine machine) {
        Inventory inventory = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getInventory();
        String general = command.body.get("string");
        List<String> list = new ArrayList<>(Arrays.asList(general.split(" ")));
        if (list.size()>2)return new Result(false,"Wrong input.");
        Ingredients ingredient=null;
        for (Ingredients ingredients1: Ingredients.values()){
            if (ingredients1.getName().equals(list.getFirst()))ingredient=ingredients1;
        }
        if ((ingredient.equals(Ingredients.WOOL)) && inventory.getIngredients().get(ingredient) >= 2){
            if (inventory.getIngredients().get(ingredient) == 2) {
                inventory.getIngredients().remove(ingredient);
                inventory.setCapacity(inventory.getCapacity() + 1);
            } else
                inventory.getIngredients().put(ingredient, inventory.getIngredients().get(ingredient) - 2);
            machine.setWorking(true);
            machine.setProduct(Ingredients.Cloth);
            return new Result(true, "Started making.");
        }return new Result(false,"not enough ingredients");
    }

    public Result useMayonnaise(Command command, CraftingMachine machine) {
    }

    public Result useOilMaker(Command command, CraftingMachine machine) {
    }

    public Result usePreserver(Command command, CraftingMachine machine) {
    }

    public Result useFishSmoker(Command command, CraftingMachine machine) {
    }

    public Result useFurnace(Command command, CraftingMachine machine) {
    }

    public Result useCraftsmanship(String command) {
        return null;
    }
}
