package src.main.java.controller;
import model.Basics.Result;
import model.Command;
import model.Objects.Animal;
import model.enums.AnimalType;
import model.Basics.Player;
import model.Basics.App;
import model.Basics.User;
import model.Basics.Game;
import model.Maps.Position;
import model.Maps.Tile;
import model.Maps.Building;
import model.enums.Weather;
import src.main.java.model.Objects.Barn;
import src.main.java.model.enums.BarnType;

import static java.lang.Integer.parseInt;
import static java.lang.Math.floor;

public class RanchingController {
    public Result BuildBarn() {
        return null;
    }

    public Result BuyAnimal(Command request) {
        //TODO : go to Marnie's Ranch first
        String animalKind = request.body.get("animalKind");
        String name = request.body.get("name");
        AnimalType newAnimalType = null;
        for(AnimalType animalType : AnimalType.values())
            if(animalType.getKind().equals(animalKind))
                newAnimalType = animalType;
        if(newAnimalType == null)
            return new Result(false, "Invalid Animal Type.");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        if(player.getMoney() < newAnimalType.getPrice())
            return new Result(false, "Not enough money.");
        if(getAnimalByName(name) != null)
            return new Result(false, "This name is already taken.");
        Barn newHome = null;
        for(Building building : player.getFarm().getBuildings())
            if(building.getClass() == Barn.class) {
                Barn barn = (Barn)building;
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
                        for(Tile tile : barn.getTiles())
                            if(!(tile.getObject() instanceof Animal)) {
                                newAnimal = new Animal(newAnimalType, name, tile.getCoordinate());
                                tile.setObject(newAnimal);
                            }
                        barn.getAnimals().add(newAnimal);
                        player.setMoney(player.getMoney() - newAnimalType.getPrice());
                        return new Result(true, "Animal bought successfully!");
                    }
                }
            }
        return new Result(false, "No suitable barn found.");
    }

    public Result NuzPet(Command request) {
        String name = request.body.get("name");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Animal pet = getAnimalByName(name);
        if(pet == null)
            return new Result(false, "No pet found.");
        if(!pet.getPosition().isNextTo(player.getPosition()))
            return new Result(false, "You are not next to " + name);
        if(!pet.getHasBeenNuzzed()) {
            pet.setHasBeenNuzzed(true);
            pet.changeFriendship(15);
        }
        return new Result(true, "Done successfully!");
    }

    public Result ShowAnimalsInfo() {
        StringBuilder response = new StringBuilder();
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for(Building building : player.getFarm().getBuildings())
            if(building.getClass() == Barn.class) {
                Barn barn = (Barn) building;
                for(Animal animal : barn.getAnimals()) {
                    response.append(animal.getName()).append(" (").append(animal.getType().getKind()).append("): ");
                    response.append("friendship: ").append(animal.getFriendship()).append(", ");
                    if(animal.getHasBeenFed())
                        response.append("has been fed, ");
                    else
                        response.append("hasn't been fed yet.");
                    if(animal.getHasBeenNuzzed())
                        response.append("has been nuzzed!\n");
                    else
                        response.append("hasn't been nuzzed yet!\n");
                }
            }
        return new Result(true, response.toString());
    }

    public Result ShepherdAnimals(Command request) {
        String name = request.body.get("name");
        int x = parseInt(request.body.get("x"));
        int y = parseInt(request.body.get("y"));
        Position newPosition = new Position(x, y);
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();

        Animal animal = getAnimalByName(name);
        if(animal == null)
            return new Result(false, "No animal found.");

        Tile currentTile = player.getFarm().findCellByCoordinate(animal.getPosition());
        Tile destination = player.getFarm().findCellByCoordinate(newPosition);
        if(destination == null)
            return new Result(false, "Destination not found.");

        Barn barn = getBarnByAnimal(animal);
        if(barn == null)
            return new Result(false, "This error is never going to occur!!");
        if(destination.isInsideBuilding()) {
            if(barn.getTileByCoordinate(newPosition) == null)
                return new Result(false, "Destination isn't inside " + name + "'s house.");
            if(destination.getObject() instanceof Animal && !newPosition.equals(animal.getPosition()))
                return new Result(false, "Destination occupied.");
            currentTile.setObject(null);
            destination.setObject(animal);
            animal.setPosition(newPosition);
            animal.setInsideBarn(true);
        }
        else {
            Game game = App.getLoggedInUser().getCurrentGame();
            if(game.getWeatherToday() != Weather.SUNNY)
                return new Result(false, "Animals can't go out because of the weather conditions.");
            if(destination.getObject() != null)
                return new Result(false, "Destination occupied.");
            currentTile.setObject(null);
            destination.setObject(animal);
            animal.setPosition(newPosition);
            animal.setInsideBarn(false);
            if(!animal.getHasBeenFed()) {
                animal.setHasBeenFed(true);
                animal.changeFriendship(8);
            }
        }
        return new Result(true, "Done successfully!");
    }
    public Result FeedHay() {
        return null;
    }
    public Result ShowProducts() {
        return null;
    }
    public Result CollectProduct() {
        return null;
    }
    public Result SellAnimal(Command request) {
        String name = request.body.get("name");
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        Animal animal = getAnimalByName(name);
        if(animal == null)
            return new Result(false, "No animal found.");
        Barn barn = getBarnByAnimal(animal);
        if(barn == null)
            return new Result(false, "This error is never going to occur!!");
        double priceDouble = ((animal.getFriendship() / 1000) + 0.3) * animal.getType().getPrice();
        int priceInt = (int)floor(priceDouble);
        Tile currentTile = barn.getTileByCoordinate(animal.getPosition());
        currentTile.setObject(null);
        barn.getAnimals().remove(animal);
        player.setMoney(player.getMoney() + priceInt);
        return new Result(true, "Sold successfully!");
    }

    public Result CheatSetFriendship(Command request) {
        String name = request.body.get("name");
        int amount = parseInt(request.body.get("amount"));
        Animal animal = getAnimalByName(name);
        if(animal == null)
            return new Result(false, "No animal found.");
        if(amount > 1000)
            return new Result(false, "Invalid  friendship amount.");
        animal.changeFriendship(amount - animal.getFriendship());
        return new Result(true, "Friendship changed successfully!");
    }

    private static Animal getAnimalByName(String name) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for(Building building : player.getFarm().getBuildings())
            if(building.getClass() == Barn.class)
                for(Animal animal : ((Barn) building).getAnimals())
                    if(animal.getName().equals(name))
                        return animal;
        return null;
    }

    private Barn getBarnByAnimal(Animal wantedAnimal) {
        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
        for(Building building : player.getFarm().getBuildings())
            if(building.getClass() == Barn.class) {
                Barn barn = (Barn) building;
                for (Animal animal : barn.getAnimals())
                    if (animal.equals(wantedAnimal))
                        return barn;
            }
        return null;
    }
}
