package controller;
import model.Command;
import model.Basics.Player;
import model.Basics.Result;
import model.Basics.App;
import model.Basics.Game;
import model.enums.Ingredients;
import model.enums.IngredientsTypes;

import static java.lang.Integer.parseInt;

public class FriendshipController extends ControllersController {
    public Result friendship(Command request) {
        StringBuilder response = new StringBuilder();
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        int j = game.getPlayers().indexOf(player);
        for(int i = 0; i < game.getPlayers().size(); i++)
            if(game.getPlayers().get(i).getUser().getUsername() != player.getUser().getUsername()) {
                response.append(game.getPlayers().get(i).getUser.gsetUsername()).append(" ");
                response.append(game.getFriendMatrix().get(i).get(j).getFriendShipLevel()).append("\n");
            }
        return new Result(true, response.toString());
    }

    public Result talk(Command request) {
        String username = request.body.get("username");
        String message = request.body.get("message");
        Result isValid = checkBasics(username, 0);
        if(!isValid.isSuccess())
            return isValid;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);

        if(!game.getFriendMatrix().get(i).get(j).isHaveTalked()) {
            game.getFriendMatrix().get(i).get(j).setHaveTalked(true);
            game.getFriendMatrix().get(j).get(i).setHaveTalked(true);
            game.getFriendMatrix().get(i).get(j).changeXP(20);
            game.getFriendMatrix().get(j).get(i).changeXP(20);
        }
        friend.getInbox().add(player.getUser().getUsername() + ": " + message);
        return new Result(true, "message sent!");
    }

    public Result showInbox(Player player) {
        StringBuilder response = new StringBuilder();
        Game game = App.getLoggedInUser().getCurrentGame();
        int j = game.getPlayers().indexOf(player);
        for(String talk : player.getInbox()) {
            response.append(talk).append("\n");
            String[] words = talk.split(":");
            Player friend = getPlayerByUsername(words[0]);
            if(friend == null)
                continue;
            int i = game.getPlayers().indexOf(friend);
            String message = words[1].trim();
            player.getTalkHistory().get(i).add(words[0] + ": " +message);
            friend.getTalkHistory().get(j).add("You: " + message);
        }
        return new Result(true, response.toString());
    }

    public Result talkHistory(Command request) {
        StringBuilder response = new StringBuilder();
        String username = request.body.get("username");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(friend);
        for(String message : player.getTalkHistory().get(i))
            response.append(message).append("\n");
        return new Result(true, response.toString());
    }

    public Result gift(Command request) {
        String username = request.body.get("username");
        String itemName = request.body.get("name");
        Result isValid = checkBasics(username, 1);
        if(!isValid.isSuccess())
            return isValid;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);

        Ingredients gift = null;
        for(Ingredients ingredients : player.getInventory().getIngredients().keySet())
            if(ingredients.getName().equals(itemName))
                gift = ingredients;
        if(gift == null)
            return new Result(false, "You don't have the gift in your inventory.");
        if(gift.getType() == IngredientsTypes.junk || gift.getType() == IngredientsTypes.ore)
            return new Result(false, "You can't give " + gift.getName() + " to " + username);
        game.getFriendMatrix().get(i).get(j).setHaveGifted(true);
        game.getFriendMatrix().get(j).get(i).setHaveGifted(true);

        Integer amount = player.getInventory().getIngredients().get(gift);
        player.getInventory().getIngredients().put(gift, amount - 1);
        amount = friend.getInventory().getIngredients().get(gift);
        if(amount == null)
            amount = 1;
        else
            amount++;
        friend.getInventory().getIngredients().put(gift, amount);
        friend.getReceivedGifts().add(player.getUser().getUsername() + ": " + gift.getName());
        return new Result(true, "Gift sent successfully!");
    }

    public Result showReceivedGifts(Command request) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        for(int i = 0; i < player.getReceivedGifts().size(); i++) {
            response.append((i + 1)).append("- ");
            response.append(player.getReceivedGifts().get(i)).append("\n");
        }
        return new Result(true, response.toString());
    }

    public Result giftRate(Command request) {
        int index = parseInt(request.body.get("index")) - 1;
        int rate = parseInt(request.body.get("rate"));
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        if(player.getReceivedGifts().size() <= index)
            return new Result(false, "Invalid Index.");
        if(rate < 1 || rate > 5)
            return new Result(false, "Invalid rate.");
        String gift = player.getReceivedGifts().get(index);
        String[] words = gift.split(":");
        Player friend = getPlayerByUsername(words[0]);
        if(friend == null)
            return new Result(false, "This error is never gonna occur!");
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);
        game.getFriendMatrix().get(i).get(j).changeXP((rate - 3) * 30 + 15);
        game.getFriendMatrix().get(j).get(i).changeXP((rate - 3) * 30 + 15);

        gift = words[1].trim();
        player.getGiftHistory().get(j).add("received: " + gift);
        friend.getGiftHistory().get(i).add("sent: " + gift);
        player.getReceivedGifts().remove(index);
        return new Result(true, "Gift rated successfully!");
    }

    public Result giftHistory(Command request) {
        String username = request.body.get("username");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        if(friend == null)
            return new Result(false, "Incorrect username.");
        int j = game.getPlayers().indexOf(friend);
        StringBuilder response = new StringBuilder();
        for(String gift : player.getGiftHistory().get(j))
            response.append(gift).append("\n");
        return new Result(true, response.toString());
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

    public Result marriageRequest(Command request) {
        String username = request.body.get("username");
        Result isValid = checkBasics(username, 3);
        if(!isValid.isSuccess())
            return isValid;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        if(player.getUser().getGender().equals("women") || friend.getUser().getGender().equals("other"))
            return new Result(false, "chi begam vala.");
        if(!player.getInventory().getIngredients().containsKey(Ingredients.WeddingRing))
            return new Result(false, "boro gerdoo bazi kon!");
        friend.getMarriageRequests().add(player.getUser().getUsername());
        return new Result(true, "Request sent successfully.");
    }

    public Result showMarriageRequests(Command request) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        for(String string : player.getMarriageRequests())
            response.append(string).append("\n");
        return new Result(true, response.toString());
    }

    public Result marriageResponse(Command request) {
        String response = request.body.get("response");
        String username = request.body.get("username");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);

        if(response.equals("reject")) {
            int xp = game.getFriendMatrix().get(i).get(j).getXP();
            game.getFriendMatrix().get(i).get(j).changeXP(-xp);
            game.getFriendMatrix().get(j).get(i).changeXP(-xp);
            player.getMarriageRequests().remove(username);
            return new Result(true, "Request rejected.");
        }
        int xp = game.getFriendMatrix().get(i).get(j).getXP();
        game.getFriendMatrix().get(i).get(j).changeXP(1000-xp);
        game.getFriendMatrix().get(j).get(i).changeXP(1000-xp);
        player.getMarriageRequests().clear();
        return new Result(true, "Bada bada mobarak bada!");
    }

    public Result trade(Command request) {
        return null;
    }

    public Result cheatSetFriendship(Command request) {
        String username = request.body.get("username");
        int XP = parseInt(request.body.get("xp"));
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
        if(friend == player)
            return new Result(false, "Na ki gofte khodshifte am?!");
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
