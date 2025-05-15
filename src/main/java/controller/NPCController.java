package controller;

import java.util.ArrayList;
import java.util.List;
import model.Resualt;
import model.Command;
import src.main.java.model.NPC.NPC;
import model.Basics.App;
import model.Basics.Game;
import model.Basics.Player;
import model.enums.Ingredients;
import src.main.java.model.NPC.Quest;

import static java.lang.Integer.parseInt;

public class NPCController extends ControllersController {
    public Resualt MeetNPC(Command request) {
        String name = request.body.get("name");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        NPC npc = getNPCbyName(name);
        if(npc == null)
            return new Resualt(false, "No NPC found.");
        if(!player.getPosition().isNextTo(npc.getPosition()))
            return new Resualt(false, name  + " can't hear you! You are so far.");
        int i = game.getPlayers().indexOf(player);
        int previous = npc.getFriendships().get(i).getFriendshipLevel();
        if(!npc.getFriendships().get(i).isHaveTalked()) {
            npc.getFriendships().get(i).changeXp(20);
            npc.getFriendships().get(i).setHaveTalked(true);
        }
        if(npc.getFriendships().get(i).getFriendshipLevel() == 1 && previous == 0 &&
            !npc.getQuests().get(1).isCompleted())
            npc.getActiveQuests().get(i).add(npc.getQuests().get(1));
        return new Resualt(true, name + ": " + getDialogue(npc));
    }

    private String getDialogue(NPC npc) {
        return "Hey!";
    }

    public Resualt GiftNPC(Command request) {
        String name = request.body.get("name");
        String itemName = request.body.get("itemName");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        NPC npc = getNPCbyName(name);
        if(npc == null)
            return new Resualt(false, "No NPC found.");
        if(!player.getPosition().isNextTo(npc.getPosition()))
            return new Resualt(false, "You are so far.");

        Ingredients gift = null;
        for(Ingredients ingredients : player.getInventory().getIngredients().keySet())
            if(ingredients.getName().equals(itemName))
                gift = ingredients;
        if(gift == null)
            return new Resualt(false, "You can't give " + itemName + " to " + name);

        Integer amount = player.getInventory().getIngredients().get(gift);
        if(amount == 1)
            player.getInventory().getIngredients().remove(gift);
        else
            player.getInventory().getIngredients().put(gift, amount - 1);
        int i = game.getPlayers().indexOf(player);
        int previous = npc.getFriendships().get(i).getFriendshipLevel();
        if(!npc.getFriendships().get(i).isHaveGifted()) {
            if(npc.getFavorites().contains(gift))
                npc.getFriendships().get(i).changeXp(200);
            else
                npc.getFriendships().get(i).changeXp(50);
            npc.getFriendships().get(i).setHaveGifted(true);
        }
        if(npc.getFriendships().get(i).getFriendshipLevel() == 1 && previous == 0 &&
                !npc.getQuests().get(1).isCompleted())
            npc.getActiveQuests().get(i).add(npc.getQuests().get(1));
        return new Resualt(true, "Gift sent successfully!");
    }

    public Resualt ShowFriendship(Command request) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        int i = game.getPlayers().indexOf(player);
        StringBuilder response = new StringBuilder();
        for(NPC npc : game.getNpcs())
            response.append(npc.getName()).append(": ").append(npc.getFriendships().get(i).getFriendshipLevel()).append("\n");
        return new Resualt(true, response.toString());
    }

    public Resualt ShowQuests(Command request) {
        String name = request.body.get("name");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        NPC npc = getNPCbyName(name);
        if(npc == null)
            return new Resualt(false, "No NPC found.");
        if(!player.getPosition().isNextTo(npc.getPosition()))
            return new Resualt(false, "You are so far.");
        StringBuilder response = new StringBuilder();
        int i = game.getPlayers().indexOf(player);
        for(int j = 0; j < npc.getActiveQuests().get(i).size(); j++) {
            Quest quest = npc.getActiveQuests().get(i).get(j);
            response.append("Quest ").append(j + 1).append(": ");
            response.append(quest.getRequirement().getIngredient().getName()).append(", ");
            response.append(quest.getRequirement().getNumber()).append(", Status: ");
            if(quest.isCompleted())
                response.append("completed\n");
            else
                response.append("ongoing\n");
        }
        return new Resualt(true, response.toString());
    }

    public Resualt FinishQuest(Command request) {
        String name = request.body.get("name");
        int index = parseInt(request.body.get("index")) - 1;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        NPC npc = getNPCbyName(name);
        if(npc == null)
            return new Resualt(false, "No NPC found.");
        if(!player.getPosition().isNextTo(npc.getPosition()))
            return new Resualt(false, "You are so far.");
        int i = game.getPlayers().indexOf(player);
        if(npc.getActiveQuests().get(i).size() <= index)
            return new Resualt(false, "No quest found.");
        Quest quest = npc.getActiveQuests().get(i).get(index);
        if(quest.isCompleted())
            return new Resualt(false, "This quest has been already completed.");
        Ingredients ingredients = quest.getRequirement().getIngredient();
        Integer amount = quest.getRequirement().getNumber();
        Integer number = player.getInventory().getIngredients().get(ingredients);
        if(number == null || number < amount)
            return new Resualt(false, "Not enough items to complete the quest.");
        if(amount.equals(number))
            player.getInventory().getIngredients().remove(ingredients);
        else
            player.getInventory().getIngredients().put(ingredients, number - amount);
        ingredients = quest.getReward().getIngredient();
        amount = quest.getReward().getNumber();
        number = player.getInventory().getIngredients().get(ingredients);
        if(number == null)
            number = 0;
        if(npc.getFriendships().get(i).getFriendshipLevel() >= 2)
            amount *= 2;
        player.getInventory().getIngredients().put(ingredients, number + amount);
        quest.setCompleted(true);
        return new Resualt(true, "Quest completed successfully!");
    }

    private NPC getNPCbyName(String name) {
        Game game = App.getLoggedInUser().getCurrentGame();
        for(NPC npc : game.getNpcs())
            if(npc.getName().equals(name))
                return npc;
        return null;
    }
}
