package src.main.java.view;
import src.main.java.controller.RanchingController;
import src.main.java.controller.ShoppingController;
import src.main.java.model.enums.RanchingMenuCommands;
import view.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

import model.Basics.Result;
import model.Command;

public class RanchingMenu implements AppMenu {
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
         Result result = null;

         //Ranching
         if(input.matches(RanchingMenuCommands.BUILD_BARN.getCommand()))
             result = getBuildBarn(input);
         else if(input.matches(RanchingMenuCommands.BUY_ANIMAL.getCommand()))
             result = getBuyAnimal(input);
         else if(input.matches(RanchingMenuCommands.NUZ_PET.getCommand()))
             result = getNuzPet(input);
         else if(input.matches(RanchingMenuCommands.SHOW_ANIMALS_INFO.getCommand()))
             result = getShowAnimalsInfo(input);
         else if(input.matches(RanchingMenuCommands.SHEPHERD_ANIMALS.getCommand()))
             result = getShepherdAnimals(input);
         else if(input.matches(RanchingMenuCommands.FEED_HAY.getCommand()))
             result = getFeedHay(input);
         else if(input.matches(RanchingMenuCommands.SHOW_PRODUCTS.getCommand()))
             result = getShowProducts(input);
         else if(input.matches(RanchingMenuCommands.COLLECT_PRODUCT.getCommand()))
             result = getCollectProducts(input);
         else if(input.matches(RanchingMenuCommands.SELL_ANIMAL.getCommand()))
             result = getSellAnimal(input);
         else if(input.matches(RanchingMenuCommands.CHEAT_SET_FRIENDSHIP.getCommand()))
             result = getCheatSetFriendship(input);
         else if(input.matches(RanchingMenuCommands.GO_FISHING.getCommand()))
             result = getGoFishing(input);

         //Shopping
         else if(input.matches(RanchingMenuCommands.SHOW_ALL.getCommand()))
            result = getShowAll(input);
         else if(input.matches(RanchingMenuCommands.SHOW_ALL_AVAILABLE.getCommand()))
             result = getShowAllAvailable(input);
         else if(input.matches(RanchingMenuCommands.PURCHASE.getCommand()))
             result = getPurchase(input);
         else if(input.matches(RanchingMenuCommands.SELL.getCommand()))
             result = getSell(input);
         else if(input.matches(RanchingMenuCommands.CHEAT_ADD_MONEY.getCommand()))
             result = getCheatAddMoney(input);
    }

    private Result getBuildBarn(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.BUILD_BARN.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("name", matcher.group("name"));
        command.body.put("x", matcher.group("x"));
        command.body.put("y", matcher.group("y"));
        result = RanchingController.BuildBarn(command);
        return result;
    }

    private Result getBuyAnimal(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.BUY_ANIMAL.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("animalKind", matcher.group("animalKind"));
        command.body.put("name", matcher.group("name"));
        result = RanchingController.BuyAnimal(command);
        return result;
    }

    private Result getNuzPet(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.NUZ_PET.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("name", matcher.group("name"));
        result = RanchingController.NuzPet(command);
        return result;
    }

    private Result getShowAnimalsInfo(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.SHOW_ANIMALS_INFO.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        result = RanchingController.ShowAnimalsInfo(command);
        return result;
    }

    private Result getShepherdAnimals(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.SHEPHERD_ANIMALS.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("name", matcher.group("name"));
        command.body.put("x", matcher.group("x"));
        command.body.put("y", matcher.group("y"));
        result = RanchingController.ShepherdAnimals(command);
        return result;
    }

    private Result getFeedHay(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.FEED_HAY.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("name", matcher.group("name"));
        result = RanchingController.FeedHay(command);
        return result;
    }

    private Result getShowProducts(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.SHOW_PRODUCTS.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        result = RanchingController.ShowProducts(command);
        return result;
    }

    private Result getCollectProducts(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.COLLECT_PRODUCT.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("name", matcher.group("name"));
        result = RanchingController.CollectProduct(command);
        return result;
    }

    private Result getSellAnimal(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.SELL_ANIMAL.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("name", matcher.group("name"));
        result = RanchingController.SellAnimal(command);
        return result;
    }

    private Result getCheatSetFriendship(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.CHEAT_SET_FRIENDSHIP.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("name", matcher.group("name"));
        command.body.put("amount", matcher.group("amount"));
        result = RanchingController.CheatSetFriendship(command);
        return result;
    }

    private Result getGoFishing(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.GO_FISHING.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        result = RanchingController.GoFishing(command);
        return result;
    }

    //Shopping
    private Result getShowAll(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.SHOW_ALL.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        result = ShoppingController.showAllProducts(command);
        return result;
    }

    private Result getShowAllAvailable(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.SHOW_ALL_AVAILABLE.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        result = ShoppingController.showAllAvailableProducts(command);
        return result;
    }

    private Result getPurchase(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.PURCHASE.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("name", matcher.group("name"));
        if(matcher.group("amount") != null)
            command.body.put("amount", matcher.group("amount"));
        else
            command.body.put("amount", "1");
        result = ShoppingController.Purchase(command);
        return result;
    }

    private Result getSell(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.SELL.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("name", matcher.group("name"));
        if(matcher.group("amount") != null)
            command.body.put("amount", matcher.group("amount"));
        else
            command.body.put("amount", "0");
        result = ShoppingController.Sell(command);
        return result;
    }

    private Result getCheatAddMoney(String input) {
        Result result = null;
        Command command = new Command(input);
        Matcher matcher = RanchingMenuCommands.CHEAT_ADD_MONEY.getMatcher(input);
        if(matcher == null)
            return new Result(false, "This error is never gonna occur!");
        command.body.put("amount", matcher.group("amount"));
        result = ShoppingController.cheatAddMoney(command);
        return result;
    }
}
