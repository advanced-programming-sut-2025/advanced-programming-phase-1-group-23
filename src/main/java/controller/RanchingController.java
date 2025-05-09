package src.main.java.controller;
import model.Basics.Result;
import model.Command;
import model.Objects.Animal;
import model.enums.AnimalType;
import model.Basics.Player;
import model.Basics.App;
import model.Basics.User;
import model.Maps.Position;
import model.Maps.Tile;
import model.Maps.Building;
import src.main.java.model.Objects.Barn;
import src.main.java.model.enums.BarnType;

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
                        Position freeTile;
                        //TODO add position
//                        for(Tile tile : barn.getTiles())
//                            if(tile.getObjectOnCell() instanceof Animal)
                        barn.getAnimals().add(new Animal(newAnimalType, name));
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
        return null;
    }
    public Result ShepherdAnimals() {
        return null;
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
    public Result SellAnimal() {
        return null;
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
}
