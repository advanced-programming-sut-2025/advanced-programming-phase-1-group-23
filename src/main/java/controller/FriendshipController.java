package controller;
import model.Command;
import model.Basics.Player;
import model.Basics.Result;
import model.Basics.App;
import model.Basics.Game;
import model.enums.Ingredients;

public class FriendshipController extends ControllersController {
    public Result friendship(String command) {
        return null;
    }

    public Result talk(String command) {

    }

    public Result talkHistory(String command) {
        return null;
    }

    public Result gift(String command) {
        return null;
    }

    public Result listOfGifts(String command) {
        return null;
    }

    public Result giftHistory(String command) {
        return null;
    }

    public Result giftRate(String command) {
        return null;
    }

    public Result hug(Command request) {
        String username = request.body.get("username");
        Result isValid = checkBasics(username, 2);
        if(!isValid.isSuccess())
            return isValid;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);

        if(!game.getFriendMatrix().get(i).get(j).isHaveHugged()) {
            game.getFriendMatrix().get(i).get(j).setHaveHugged(true);
            game.getFriendMatrix().get(j).get(i).setHaveHugged(true);
            game.getFriendMatrix().get(i).get(j).changeXP(60);
            game.getFriendMatrix().get(j).get(i).changeXP(60);
        }
        return new Result(true, "You have hugged each other now!");
    }

    public Result givingFlowers(Command request) {
        String username = request.body.get("username");
        Result isValid = checkBasics(username, 2);
        if(!isValid.isSuccess())
            return isValid;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);

        Integer quantity = player.getInventory().getIngredients().get(Ingredients.Bouquet);
        if(quantity == null)
            return new Result(false, "You don't have a bouquet.");
        if(game.getFriendMatrix().get(i).get(j).getXP() < 599)
            return new Result(false, "Level 2 friendship XP is not completed yet.");
        if(quantity > 1)
            player.getInventory().getIngredients().put(Ingredients.Bouquet, quantity - 1);
        else
            player.getInventory().getIngredients().remove(Ingredients.Bouquet);
        quantity = friend.getInventory().getIngredients().get(Ingredients.Bouquet);
        if(quantity == null)
            quantity = 1;
        else
            quantity++;
        friend.getInventory().getIngredients().put(Ingredients.Bouquet, quantity);
        game.getFriendMatrix().get(i).get(j).changeXP(1);
        game.getFriendMatrix().get(j).get(i).changeXP(1);
        return new Result(true, "You are now level 3 friends.");
    }

    public Result marriageRequest(String command) {
        return null;
    }

    public Result marriageRequestHistory(String command) {
        return null;
    }

    public Result marriageAccept(String command) {
        return null;
    }

    public Result marriageReject(String command) {
        return null;
    }

    public Result cheatSetFriendship(Command request) {
        String username = request.body.get("username");
        int XP = request.body.get("xp");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        if(friend == null)
            return new Result(false, "Player not found.");
        if(XP > 1000)
            return new Result(false, "Invalid XP.");
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);
        game.getFriendMatrix().get(i).get(j).changeXP(XP - game.getFriendMatrix().get(i).get(j).getXP());
        game.getFriendMatrix().get(j).get(i).changeXP(XP - game.getFriendMatrix().get(i).get(j).getXP());
        return new Result(true, "Friendship changed successfully.");
    }

    private Result checkBasics(String username, int level) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        if(friend == null)
            return new Result(false, "Player not found.");
        if(!player.getPosition().isNextTo(friend.getPosition()))
            return new Result(false, "You two are so far!");
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);
        if(game.getFriendMatrix().get(i).get(j).getFriendShipLevel() < level)
            return new Result(false, "You are not good friends enough yet.");
    }

    private Player getPlayerByUsername(String username) {
        Game game = App.getLoggedInUser().getCurrentGame();
        for(Player player : game.getPlayers())
            if(player.getUser().getUsername().equals(username))
                return player;
        return null;
    }
}
