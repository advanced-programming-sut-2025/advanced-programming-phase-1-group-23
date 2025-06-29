package controller;

import model.Basics.Result;
import model.Command;
import model.Objects.Animal;
import model.Resualt;
import model.enums.AnimalType;
import model.Basics.Player;
import model.Basics.App;
import model.Basics.User;
import model.Objects.Tool;
import model.Basics.Game;
import model.Maps.Position;
import model.Maps.Tile;
import model.Maps.Building;
import model.enums.Ingredients;
import model.enums.ToolType;
import model.enums.Weather;
import model.Objects.Barn;
import model.Objects.Fish;
import model.Objects.ShippingBin;
import model.enums.BarnType;
import model.enums.SeasonFish;
import model.enums.ShopName;

import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.signum;
import static java.lang.Math.floor;
import static java.lang.Math.toRadians;
import static model.enums.Season.*;
import static model.enums.Weather.*;

public class RanchingController {
    public static Resualt BuildBarn(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if (player.getCurrentShop() == null || player.getCurrentShop().getName() != ShopName.CarpenterShop)
            return new Resualt(false, "You must go to carpenter shop first.");
        String buildingName = request.body.get("name");
        int x = parseInt(request.body.get("x"));
        int y = parseInt(request.body.get("y"));
        Position cornerPosition = new Position(x, y);

//        if(buildingName.equals("well")) {
//            for(int i = 0; i < 3; i++)
//                for(int j = 0; j < 3; j++) {
//                    Tile tile = player.getFarm().findCellByCoordinate(new Position(x + i, y + j));
//                    if(tile == null || tile.getObject() != null)
//                        return new Result(false, "Position occupied.");
//                }
//            Integer stone = player.getInventory().getIngredients().get(Ingredients.STONE);
//            if(stone < )
//            if(player.getMoney() < )
//            return null;
//        }
        if (buildingName.equals("shipping bin")) {
            Tile tile = player.getFarm().findCellByCoordinate(cornerPosition.getX(), cornerPosition.getY());
            if (tile == null || tile.getObject() != null)
                return new Resualt(false, "Position occupied.");
            Integer wood = player.getInventory().getIngredients().get(Ingredients.WOOD);
            if (wood < 150)
                return new Resualt(false, "Not enough material.");
            if (player.getMoney() < 250)
                return new Resualt(false, "Not enough money.");
            ShippingBin shippingBin = new ShippingBin();
            tile.setObject(shippingBin);
            player.getFarm().getShippingBins().add(shippingBin);
            return new Resualt(true, "Shipping bin added successfully!");
        }

        BarnType barnType = null;
        for (BarnType type : BarnType.values())
            if (type.getKind().equals(buildingName))
                barnType = type;
        if (barnType == null)
            return new Resualt(false, "Invalid barn type.");
        Tile corner = player.getFarm().findCellByCoordinate(cornerPosition.getX(), cornerPosition.getY());
        if (corner == null)
            return new Resualt(false, "Invalid position.");
        int width = barnType.getWidth();
        int length = barnType.getLength();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < length; j++) {
                Position position = new Position(x + i, y + j);
                Tile tile = player.getFarm().findCellByCoordinate(position.getX(), position.getY());
                if (tile == null || tile.getObject() != null)
                    return new Resualt(false, "You are building a barn not a tree house!");
            }
        if (player.getMoney() < barnType.getPrice())
            return new Resualt(false, "YNot enough money.");
        Integer wood = player.getInventory().getIngredients().get(Ingredients.WOOD);
        Integer stone = player.getInventory().getIngredients().get(Ingredients.STONE);
        if (wood == null || wood < barnType.getWood() ||
                stone == null || stone < barnType.getStone())
            return new Resualt(false, "Not enough material.");
        player.setMoney(player.getMoney() - barnType.getPrice());
        player.getInventory().getIngredients().put(Ingredients.WOOD, wood - barnType.getWood());
        player.getInventory().getIngredients().put(Ingredients.STONE, stone - barnType.getStone());
        ArrayList<Tile> floor = new ArrayList<>();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < length; j++) {
                Position position = new Position(x + i, y + j);
                Tile tile = player.getFarm().findCellByCoordinate(position.getX(), position.getY());
                floor.add(tile);
            }
        player.getFarm().getBuildings().add(new Barn(floor, barnType));
        return new Resualt(true, "Barn added successfully!");
    }

    public static Resualt BuyAnimal(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if (player.getCurrentShop() == null || player.getCurrentShop().getName() != ShopName.MarnieRanch)
            return new Resualt(false, "You must go to Marnie Ranch first.");
        String animalKind = request.body.get("animalKind");
        String name = request.body.get("name");
        AnimalType newAnimalType = null;
        for (AnimalType animalType : AnimalType.values())
            if (animalType.getKind().equals(animalKind))
                newAnimalType = animalType;
        if (newAnimalType == null)
            return new Resualt(false, "Invalid Animal Type.");
        if (player.getMoney() < newAnimalType.getPrice())
            return new Resualt(false, "Not enough money.");
        if (getAnimalByName(name) != null)
            return new Resualt(false, "This name is already taken.");
        Barn newHome = null;
        for (Building building : player.getFarm().getBuildings())
            if (building.getClass() == Barn.class) {
                Barn barn = (Barn) building;
                if (barn.getAnimals().size() < barn.getCapacity()) {
                    boolean isValid = switch (newAnimalType) {
                        case Hen -> barn.getType() == BarnType.SimpleCoop ||
                                barn.getType() == BarnType.BigCoop ||
                                barn.getType() == BarnType.DeluxeCoop;
                        case Duck, Dinosaur -> barn.getType() == BarnType.BigCoop ||
                                barn.getType() == BarnType.DeluxeCoop;
                        case Rabbit -> barn.getType() == BarnType.DeluxeCoop;
                        case Cow -> barn.getType() == BarnType.SimpleBarn ||
                                barn.getType() == BarnType.BigBarn ||
                                barn.getType() == BarnType.DeluxeBarn;
                        case Goat -> barn.getType() == BarnType.BigBarn ||
                                barn.getType() == BarnType.DeluxeBarn;
                        case Sheep, Pig -> barn.getType() == BarnType.DeluxeBarn;
                        default -> false;
                    };
                    if (isValid) {
                        Animal newAnimal = null;
                        Position freeTile;
                        for (Tile tile : barn.getTiles())
                            if (!(tile.getObject() instanceof Animal)) {
                                newAnimal = new Animal(newAnimalType, name, tile.getCoordinate());
                                tile.setObject(newAnimal);
                                break;
                            }
                        barn.getAnimals().add(newAnimal);
                        player.setMoney(player.getMoney() - newAnimalType.getPrice());
                        return new Resualt(true, "Animal bought successfully!");
                    }
                }
            }
        return new Resualt(false, "No suitable barn found.");
    }

    public static Resualt NuzPet(Command request) {
        String name = request.body.get("name");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Animal pet = getAnimalByName(name);
        if (pet == null)
            return new Resualt(false, "No pet found.");
        if (!pet.getPosition().isNextTo(player.getPosition()))
            return new Resualt(false, "You are not next to " + name);
        if (!pet.getHasBeenNuzzed()) {
            pet.setHasBeenNuzzed(true);
            pet.changeFriendship(15);
        }
        return new Resualt(true, "Done successfully!");
    }

    public static Resualt ShowAnimalsInfo(Command request) {
        StringBuilder response = new StringBuilder();
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for (Building building : player.getFarm().getBuildings())
            if (building.getClass() == Barn.class) {
                Barn barn = (Barn) building;
                for (Animal animal : barn.getAnimals()) {
                    response.append(animal.getName()).append(" (").append(animal.getType().getKind()).append("): ");
                    response.append("friendship: ").append(animal.getFriendship()).append(", ");
                    if (animal.getHasBeenFed())
                        response.append("has been fed, ");
                    else
                        response.append("hasn't been fed yet.");
                    if (animal.getHasBeenNuzzed())
                        response.append("has been nuzzed!\n");
                    else
                        response.append("hasn't been nuzzed yet!\n");
                }
            }
        return new Resualt(true, response.toString());
    }

    public static Resualt ShepherdAnimals(Command request) {
        String name = request.body.get("name");
        int x = parseInt(request.body.get("x"));
        int y = parseInt(request.body.get("y"));
        Position newPosition = new Position(x, y);
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();

        Animal animal = getAnimalByName(name);
        if (animal == null)
            return new Resualt(false, "No animal found.");

        Tile currentTile = player.getFarm().findCellByCoordinate(animal.getPosition().getX(), animal.getPosition().getY());
        Tile destination = player.getFarm().findCellByCoordinate(newPosition.getX(), newPosition.getY());
        if (destination == null)
            return new Resualt(false, "Destination not found.");

        Barn barn = getBarnByAnimal(animal);
        if (barn == null)
            return new Resualt(false, "This error is never going to occur!!");
        if (destination.isInsideBuilding()) {
            if (barn.getTileByCoordinate(newPosition) == null)
                return new Resualt(false, "Destination isn't inside " + name + "'s house.");
            if (destination.getObject() instanceof Animal && !newPosition.equals(animal.getPosition()))
                return new Resualt(false, "Destination occupied.");
            currentTile.setObject(null);
            destination.setObject(animal);
            animal.setPosition(newPosition);
            animal.setInsideBarn(true);
        } else {
            Game game = App.getLoggedInUser().getCurrentGame();
            if (game.getWeatherToday() != SUNNY)
                return new Resualt(false, "Animals can't go out because of the weather conditions.");
            if (destination.getObject() != null)
                return new Resualt(false, "Destination occupied.");
            currentTile.setObject(null);
            destination.setObject(animal);
            animal.setPosition(newPosition);
            animal.setInsideBarn(false);
            if (!animal.getHasBeenFed()) {
                animal.setHasBeenFed(true);
                animal.changeFriendship(8);
                animal.setHasBeenOut(true);
            }
        }
        return new Resualt(true, "Done successfully!");
    }

    public static Resualt FeedHay(Command request) {
        String name = request.body.get("name");
        Animal animal = getAnimalByName(name);
        if (animal == null)
            return new Resualt(false, "No animal found.");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Integer hay = player.getInventory().getIngredients().get(Ingredients.HAY);
        if (hay == null)
            return new Resualt(false, "No hay available in inventory.");
        if (animal.getHasBeenFed())
            return new Resualt(false, name + " is not hungry!");
        animal.setHasBeenFed(true);
        player.getInventory().getIngredients().put(Ingredients.HAY, hay - 1);
        return new Resualt(true, name + " has been fed now!");
    }

    public static Resualt ShowProducts(Command request) {
        StringBuilder response = new StringBuilder();
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for (Building building : player.getFarm().getBuildings())
            if (building.getClass() == Barn.class) {
                Barn barn = (Barn) building;
                for (Animal animal : barn.getAnimals())
                    if (animal.getProduct() != null) {
                        response.append(animal.getName()).append(" (").append(animal.getType().getKind()).append("): ");
                        response.append(animal.getProduct().getName()).append("\n");
                    }
            }
        return new Resualt(true, response.toString());
    }

    public static Resualt CollectProduct(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        String name = request.body.get("name");
        Animal animal = getAnimalByName(name);
        if (animal == null)
            return new Resualt(false, "No animal found.");
        if (!player.getPosition().isNextTo(animal.getPosition()))
            return new Resualt(false, "You are not next to " + name);
        if (animal.getProduct() == null)
            return new Resualt(false, "No product available");
        Integer amount = player.getInventory().getIngredients().get(animal.getProduct());
        if (amount == null)
            amount = 1;
        else
            amount++;
        animal.collectProduct();
        animal.resetLastProduce();
        player.getInventory().getIngredients().put(animal.getProduct(), amount);
        return new Resualt(true, "Product collected successfully!");
    }

    public static Resualt SellAnimal(Command request) {
        String name = request.body.get("name");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Animal animal = getAnimalByName(name);
        if (animal == null)
            return new Resualt(false, "No animal found.");
        Barn barn = getBarnByAnimal(animal);
        if (barn == null)
            return new Resualt(false, "This error is never going to occur!!");
        double priceDouble = ((animal.getFriendship() / 1000) + 0.3) * animal.getType().getPrice();
        int priceInt = (int) floor(priceDouble);
        Tile currentTile = barn.getTileByCoordinate(animal.getPosition());
        currentTile.setObject(null);
        barn.getAnimals().remove(animal);
        player.setMoney(player.getMoney() + priceInt);
        return new Resualt(true, "Sold successfully!");
    }

    public static Resualt GoFishing(Command request) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Tool fishingRod = null;
        //In hand tool?
        for (Tool tool : player.getInventory().getTools().keySet())
            if (tool.getToolType() == ToolType.FishingRod)
                fishingRod = tool;
        if (fishingRod == null)
            return new Resualt(false, "You must have a fishing rod to go fishing.");
//        boolean isKenareAbb = false;
//        for (int i = -1; i <= 1; i++)
//            for (int j = -1; j <= 1; j++) {
//                Tile tile = player.getFarm().findCellByCoordinate(player.getPosition().getX() + i, player.getPosition().getY() + j);
//                if (tile == null || tile.getObjectOnCell().type.equals("water"))
//                    isKenareAbb = true;
//            }
        Random random = new Random();
        boolean isKenareAbb = random.nextBoolean();
        if (!isKenareAbb)
            return new Resualt(false, "You must be near a water source to start fishing.");
        double weatherInfluence;
        switch (App.getLoggedInUser().getCurrentGame().getWeatherToday()) {
            case SUNNY -> weatherInfluence = 1.5;
            case RAIN -> weatherInfluence = 1.2;
            case STORM -> weatherInfluence = 0.5;
            default -> weatherInfluence = 1;
        }

        double chanceInfluence = random.nextDouble(0, 1);
        int numberOfFish = (int) floor(chanceInfluence * weatherInfluence * (player.returnFishingLevel() + 2));
        if (numberOfFish > 6)
            numberOfFish = 6;
        double poleInfluence = 0;
        switch (fishingRod.getToolLevel()) {
            case Learning -> poleInfluence = 0.1;
            case Bambou -> poleInfluence = 0.5;
            case FiberGlass -> poleInfluence = 0.9;
            case Iridium -> poleInfluence = 1.2;
        }
        double qualityUseless = chanceInfluence * (player.returnFishingLevel() + 2) * poleInfluence / (7 - weatherInfluence);
        String quality;
        if (qualityUseless < 0.5)
            quality = "ordinary";
        else if (qualityUseless < 0.7)
            quality = "silver";
        else if (qualityUseless < 0.9)
            quality = "gold";
        else
            quality = "iridium";
        StringBuilder response = new StringBuilder();
        response.append("Well done! ").append(numberOfFish).append(" fish of ").append(quality).append(" quality!\n");
        int bound = 4;
        if (player.returnFishingLevel() == 4)
            bound++;
        List<Fish> fishList;
        switch (App.getLoggedInUser().getCurrentGame().getSeason()) {
            case SPRING -> fishList = SeasonFish.Spring.getSeasonFish();
            case SUMMER -> fishList = SeasonFish.Summer.getSeasonFish();
            case AUTUMN -> fishList = SeasonFish.Autumn.getSeasonFish();
            default -> fishList = SeasonFish.Winter.getSeasonFish();
        }
        for (int i = 0; i < numberOfFish; i++) {
            int index = random.nextInt(bound);
            Fish fish = fishList.get(index);
            Integer quantity = player.getInventory().getIngredients().get(fish.getType());
            if (quantity == null)
                quantity = 1;
            else
                quantity++;
            player.getInventory().getIngredients().put(fish.getType(), quantity);
            response.append(fish.getType().getName()).append("\n");
        }
        return new Resualt(true, response.toString());
    }

    public static Resualt CheatSetFriendship(Command request) {
        String name = request.body.get("name");
        int amount = parseInt(request.body.get("amount"));
        Animal animal = getAnimalByName(name);
        if (animal == null)
            return new Resualt(false, "No animal found.");
        if (amount > 1000)
            return new Resualt(false, "Invalid  friendship amount.");
        animal.changeFriendship(amount - animal.getFriendship());
        return new Resualt(true, "Friendship changed successfully!");
    }

    private static Animal getAnimalByName(String name) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for (Building building : player.getFarm().getBuildings())
            if (building.getClass() == Barn.class)
                for (Animal animal : ((Barn) building).getAnimals())
                    if (animal.getName().equals(name))
                        return animal;
        return null;
    }

    private static Barn getBarnByAnimal(Animal wantedAnimal) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for (Building building : player.getFarm().getBuildings())
            if (building.getClass() == Barn.class) {
                Barn barn = (Barn) building;
                for (Animal animal : barn.getAnimals())
                    if (animal.equals(wantedAnimal))
                        return barn;
            }
        return null;
    }
}
