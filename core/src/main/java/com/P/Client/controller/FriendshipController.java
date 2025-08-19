package com.P.Client.controller;

import com.P.Client.app.ClientApp;
import com.P.Client.model.Command;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Resualt;
import com.P.common.model.enums.Ingredients;
import com.P.common.model.enums.IngredientsTypes;
import com.P.common.model.NPC.Pair;
import com.P.common.model.Objects.Trade;

import static java.lang.Integer.parseInt;

public class FriendshipController extends ControllersController {
    public static Resualt friendship(Command request) {
        StringBuilder response = new StringBuilder();
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        int j = game.getPlayers().indexOf(player);
        for (int i = 0; i < game.getPlayers().size(); i++)
            if (game.getPlayers().get(i).getUser().getUsername() != player.getUser().getUsername()) {
                response.append(game.getPlayers().get(i).getUser().getUsername()).append(" ");
                response.append(game.getFriendMatrix().get(i).get(j).getFriendShipLevel()).append("\n");
            }
        return new Resualt(true, response.toString());
    }

    public static Resualt talk(String username, String message) {
        Resualt isValid = checkBasics(username, 8);
        if (!isValid.isAccept())
            return isValid;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);

        if (!game.getFriendMatrix().get(i).get(j).isHaveTalked()) {
            game.getFriendMatrix().get(i).get(j).setHaveTalked(true);
            game.getFriendMatrix().get(j).get(i).setHaveTalked(true);
            game.getFriendMatrix().get(i).get(j).changeXP(20);
            game.getFriendMatrix().get(j).get(i).changeXP(20);
        }
        friend.getInbox().add(player.getUser().getUsername() + ": " + message);
        return new Resualt(true, "message sent!");
    }

    public static Resualt showInbox(Player player) {
        StringBuilder response = new StringBuilder();
        Game game = App.getLoggedInUser().getCurrentGame();
        int j = game.getPlayers().indexOf(player);
        for (String talk : player.getInbox()) {
            response.append(talk).append("\n");
            String[] words = talk.split(":");
            Player friend = getPlayerByUsername(words[0]);
            if (friend == null)
                continue;
            int i = game.getPlayers().indexOf(friend);
            String message = words[1].trim();
            player.getTalkHistory().get(i).add(words[0] + ": " + message);
            friend.getTalkHistory().get(j).add("You: " + message);
        }
        player.getInbox().clear();
        return new Resualt(true, response.toString());
    }

    public static Resualt talkHistory(Command request) {
        StringBuilder response = new StringBuilder();
        String username = request.body.get("username");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(friend);
        for (String message : player.getTalkHistory().get(i))
            response.append(message).append("\n");
        return new Resualt(true, response.toString());
    }

    public static Resualt gift(String username, String itemName) {
        Resualt isValid = checkBasics(username, 1);
        if (!isValid.isAccept())
            return isValid;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);

        Ingredients gift = null;
        for (Ingredients ingredients : player.getInventory().getIngredients().keySet())
            if (ingredients.getName().equals(itemName))
                gift = ingredients;
        if (gift == null)
            return new Resualt(false, "You don't have the gift in your inventory.");
        if (gift.getType() == IngredientsTypes.junk || gift.getType() == IngredientsTypes.ore)
            return new Resualt(false, "You can't give " + gift.getName() + " to " + username);
        game.getFriendMatrix().get(i).get(j).setHaveGifted(true);
        game.getFriendMatrix().get(j).get(i).setHaveGifted(true);

        Integer amount = player.getInventory().getIngredients().get(gift);
        player.getInventory().getIngredients().put(gift, amount - 1);
        amount = friend.getInventory().getIngredients().get(gift);
        if (amount == null)
            amount = 1;
        else
            amount++;
        friend.getInventory().getIngredients().put(gift, amount);
        friend.getReceivedGifts().add(player.getUser().getUsername() + ": " + gift.getName());
        return new Resualt(true, "Gift sent successfully!");
    }

    public static Resualt showReceivedGifts() {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < player.getReceivedGifts().size(); i++) {
            response.append((i + 1)).append("- ");
            response.append(player.getReceivedGifts().get(i)).append("\n");
        }
        return new Resualt(true, response.toString());
    }

    public static Resualt showReceivedGifts(Player player) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < player.getReceivedGifts().size(); i++) {
            response.append(player.getReceivedGifts().get(i)).append("\n");
        }
        return new Resualt(true, response.toString());
    }

    public static Resualt giftRate(Command request) {
        int index = parseInt(request.body.get("index")) - 1;
        int rate = parseInt(request.body.get("rate"));
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        if (player.getReceivedGifts().size() <= index)
            return new Resualt(false, "Invalid Index.");
        if (rate < 1 || rate > 5)
            return new Resualt(false, "Invalid rate.");
        String gift = player.getReceivedGifts().get(index);
        String[] words = gift.split(":");
        Player friend = getPlayerByUsername(words[0]);
        if (friend == null)
            return new Resualt(false, "This error is never gonna occur!");
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);
        game.getFriendMatrix().get(i).get(j).changeXP((rate - 3) * 30 + 15);
        game.getFriendMatrix().get(j).get(i).changeXP((rate - 3) * 30 + 15);

        gift = words[1].trim();
        player.getGiftHistory().get(j).add("received: " + gift);
        friend.getGiftHistory().get(i).add("sent: " + gift);
        player.getReceivedGifts().remove(index);
        return new Resualt(true, "Gift rated successfully!");
    }

    public static Resualt giftHistory() {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder response = new StringBuilder();

        for (int i = 0; i < 4; i++)
            if (i != ClientApp.getTurnNumber()) {
                response.append(game.getPlayers().get(i).getUser().getNickname()).append(":\n");
                for (String gift : player.getGiftHistory().get(i))
                    response.append(gift).append("\n");
            }
        return new Resualt(true, response.toString());
    }

    public static Resualt hug(Command request) {
        String username = request.body.get("username");
        Resualt isValid = checkBasics(username, 2);
        if (!isValid.isAccept())
            return isValid;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);

        if (!game.getFriendMatrix().get(i).get(j).isHaveHugged()) {
            game.getFriendMatrix().get(i).get(j).setHaveHugged(true);
            game.getFriendMatrix().get(j).get(i).setHaveHugged(true);
            game.getFriendMatrix().get(i).get(j).changeXP(60);
            game.getFriendMatrix().get(j).get(i).changeXP(60);
        }
        return new Resualt(true, "You have hugged each other now!");
    }

    public static Resualt givingFlowers(String username) {
       // String username = request.body.get("username");
        Resualt isValid = checkBasics(username, 2);
        if (!isValid.isAccept())
            return isValid;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);

        Integer quantity = 1;
        if (quantity == null)
            return new Resualt(false, "You don't have a bouquet.");
        if (game.getFriendMatrix().get(i).get(j).getXP() < 599)
            return new Resualt(false, "Level 2 friendship XP is not completed yet.");
        if (quantity > 1)
            player.getInventory().getIngredients().put(Ingredients.Bouquet, quantity - 1);
        else
            player.getInventory().getIngredients().remove(Ingredients.Bouquet);
        quantity = friend.getInventory().getIngredients().get(Ingredients.Bouquet);
        if (quantity == null)
            quantity = 1;
        else
            quantity++;
        friend.getInventory().getIngredients().put(Ingredients.Bouquet, quantity);
        game.getFriendMatrix().get(i).get(j).changeXP(1);
        game.getFriendMatrix().get(j).get(i).changeXP(1);
        return new Resualt(true, "You are now level 3 friends.");
    }

    public static Resualt marriageRequest(String username) {
        Resualt isValid = checkBasics(username, 3);
        if (!isValid.isAccept())
            return isValid;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        if (player.getUser().getGender().equals("women") || friend.getUser().getGender().equals("other"))
            return new Resualt(false, "chi begam vala.");
        if (!player.getInventory().getIngredients().containsKey(Ingredients.WeddingRing))
            return new Resualt(false, "boro gerdoo bazi kon!");
        friend.getMarriageRequests().add(player.getUser().getUsername());
        return new Resualt(true, "Request sent successfully.");
    }

    public static Resualt showMarriageRequests(Player player) {
        StringBuilder response = new StringBuilder();
        for (String string : player.getMarriageRequests())
            response.append(string).append("\n");
        return new Resualt(true, response.toString());
    }

    public static Resualt marriageResponse(Command request) {
        String response = request.body.get("response");
        String username = request.body.get("username");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);

        if (response.equals("reject")) {
            int xp = game.getFriendMatrix().get(i).get(j).getXP();
            game.getFriendMatrix().get(i).get(j).changeXP(-xp);
            game.getFriendMatrix().get(j).get(i).changeXP(-xp);
            player.getMarriageRequests().remove(username);
            return new Resualt(true, "Request rejected.");
        }
        int xp = game.getFriendMatrix().get(i).get(j).getXP();
        game.getFriendMatrix().get(i).get(j).changeXP(1000 - xp);
        game.getFriendMatrix().get(j).get(i).changeXP(1000 - xp);
        player.getMarriageRequests().clear();
        return new Resualt(true, "Bada bada mobarak bada!");
    }

    public static Resualt trade(Command request) {
        String username = request.body.get("username");
        String type = request.body.get("type");
        String itemName = request.body.get("itemName");
        int amount = parseInt(request.body.get("amount"));
        String targetItemName = request.body.get("targetItem");
        int targetAmount = parseInt(request.body.get("targetAmount"));
        int price = parseInt(request.body.get("price"));
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        if (friend == null)
            return new Resualt(false, "No player found.");
        if (!targetItemName.equals("hich") && price != 0)
            return new Resualt(false, "You can't choose both types.");
        if (amount <= 0)
            return new Resualt(false, "Invalid amount");
        Ingredients item = null;
        Ingredients targetItem = null;
        for (Ingredients ingredients : Ingredients.values())
            if (ingredients.getName().equals(itemName))
                item = ingredients;
        if (item == null)
            return new Resualt(false, "Invalid item.");
        if (targetItemName.equals("hich") && price > player.getMoney())
            return new Resualt(false, "Not enough money");
        if (!targetItemName.equals("hich")) {
            for (Ingredients ingredients : player.getInventory().getIngredients().keySet())
                if (ingredients.getName().equals(targetItemName))
                    targetItem = ingredients;
            if (targetItem == null)
                return new Resualt(false, "No item found.");
            if (player.getInventory().getIngredients().get(targetItem) < targetAmount)
                return new Resualt(false, "Not enough items.");
        }
        friend.getTradeList().add(new Trade(player.getUser().getUsername(), username, type, new Pair(item, amount), new Pair(targetItem, targetAmount), price));
        return new Resualt(true, "Trade request sent successfully.");
    }

    public static Resualt tradeList(Command request) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < player.getTradeList().size(); i++) {
            Trade trade = player.getTradeList().get(i);
            response.append(i + 1).append("- ").append(trade.getFirstPlayer()).append(": ");
            response.append(trade.getRequest().getIngredient().getName()).append(" ").append(trade.getRequest().getNumber()).append(", ");
            if (trade.getOffer().getIngredient() != null)
                response.append(trade.getOffer().getIngredient().getName()).append(" ").append(trade.getOffer().getNumber()).append("\n");
            else
                response.append(trade.getPrice()).append("\n");
        }
        return new Resualt(true, response.toString());
    }

    public static Resualt tradeResponse(Command request) {
        String response = request.body.get("response");
        int index = parseInt(request.body.get("index")) - 1;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        if(player.getTradeList().size() <= index)
            return new Resualt(false, "Invalid index.");
        if(!response.equals("accept") && !response.equals("reject"))
            return new Resualt(false, "Invalid response");
        Trade trade = player.getTradeList().get(index);
        Player friend = getPlayerByUsername(trade.getFirstPlayer());
        if(friend == null)
            return new Resualt(false, "This error is never gonna occur.");
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);
        if(response.equals("reject")) {
            game.getFriendMatrix().get(i).get(j).changeXP(-30);
            game.getFriendMatrix().get(j).get(i).changeXP(-30);
            player.getTradeList().remove(trade);
            player.getTradeHistory().add(trade);
            trade.setAccepted(false);
            return new Resualt(true, "Rejected successfully.");
        }
        Ingredients item = trade.getRequest().getIngredient();
        Integer amount = trade.getRequest().getNumber();
        Integer number = player.getInventory().getIngredients().get(item);
        if (number == null || number < amount)
            return new Resualt(false, "Not enough items.");
        getItem(player, trade.getRequest());
        addItem(friend, trade.getRequest());

        if(trade.getOffer().getIngredient() != null) {
            getItem(friend, trade.getOffer());
            addItem(player, trade.getOffer());
        }
        else {
            player.setMoney(player.getMoney() + trade.getPrice());
            friend.setMoney(friend.getMoney() - trade.getPrice());
        }
        trade.setAccepted(true);
        player.getTradeList().remove(trade);
        player.getTradeHistory().add(trade);
        game.getFriendMatrix().get(i).get(j).changeXP(50);
        game.getFriendMatrix().get(j).get(i).changeXP(50);
        return new Resualt(true, "Trade accepted successfully.");
    }

    public static Resualt showTradeHistory(Command request) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        for(Trade trade : player.getTradeHistory()) {
            response.append(trade.getFirstPlayer()).append(": ");
            response.append(trade.getRequest().getIngredient().getName()).append(" ").append(trade.getRequest().getNumber()).append(", ");
            if(trade.getOffer().getIngredient() != null)
                response.append(trade.getOffer().getIngredient().getName()).append(" ").append(trade.getOffer().getNumber()).append(", ");
            else
                response.append(trade.getPrice()).append(", ");
            if(trade.isAccepted())
                response.append("accepted\n");
            else
                response.append("rejected\n");
        }
        return new Resualt(true, response.toString());
    }


    public static Resualt startTrade(Command request) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        for(Player friend : game.getPlayers())
            if(friend != player)
                response.append(friend.getUser().getUsername()).append("\n");
        return new Resualt(true, response.toString());
    }

    public static Resualt cheatSetFriendship(Command request) {
        String username = request.body.get("username");
        int XP = parseInt(request.body.get("xp"));
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        if(friend == null)
            return new Resualt(false, "Player not found.");
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);
        int xp = game.getFriendMatrix().get(i).get(j).getXP();
        if(XP + xp >= 1000)
            return new Resualt(false, "Invalid XP.");
        game.getFriendMatrix().get(i).get(j).changeXP(XP);
        game.getFriendMatrix().get(j).get(i).changeXP(XP);
        return new Resualt(true, "Friendship changed successfully.");
    }

    private static Resualt checkBasics(String username, int level) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player friend = getPlayerByUsername(username);
        if (friend == null)
            return new Resualt(false, "Player not found.");
        if (friend == player)
            return new Resualt(false, "Na ki gofte khodshifte am?!");
        int i = game.getPlayers().indexOf(player);
        int j = game.getPlayers().indexOf(friend);
        if(level == 8)
            return new Resualt(true, "lalalalala");
        if (game.getFriendMatrix().get(i).get(j).getFriendShipLevel() < level)
            return new Resualt(false, "You are not good friends enough yet.");

        return new Resualt(true, "lalalalala");
    }

    private static Player getPlayerByUsername(String username) {
        Game game = App.getLoggedInUser().getCurrentGame();
        for (Player player : game.getPlayers())
            if (player.getUser().getUsername().equals(username))
                return player;
        return null;
    }

    private static void getItem(Player player, Pair pair) {
        Ingredients item = pair.getIngredient();
        Integer amount = pair.getNumber();
        int number = player.getInventory().getIngredients().get(item);
        if (number == amount)
            player.getInventory().getIngredients().remove(item);
        else
            player.getInventory().getIngredients().put(item, number - amount);
    }

    private static void addItem(Player player, Pair pair) {
        Ingredients item = pair.getIngredient();
        Integer amount = pair.getNumber();
        Integer number = player.getInventory().getIngredients().get(item);
        if (number == null)
            number = 0;
        player.getInventory().getIngredients().put(item, number + amount);
    }

}
