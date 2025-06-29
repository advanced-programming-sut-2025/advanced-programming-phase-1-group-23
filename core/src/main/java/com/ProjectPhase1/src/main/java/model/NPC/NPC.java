package model.NPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import model.Basics.App;
import model.Basics.Game;
import model.Basics.Player;
import model.enums.Ingredients;
import model.Maps.Position;

public class NPC {
    private final String name;
    private final ArrayList<Quest> quests;
    private final ArrayList<Ingredients> favorites;
    private final ArrayList<NPCFriendship> friendships;
    private final ArrayList<ArrayList<Quest>> activeQuests;
    private final Position position;

    public NPC(String name, List<Pair> information, ArrayList<Ingredients> favorites, Position position) {
        this.name = name;
        this.quests = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            this.quests.add(new Quest(information.get(i), information.get(i + 3)));
        this.favorites = favorites;
        this.position = position;
        this.friendships = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            this.friendships.add(new NPCFriendship());
        this.activeQuests = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            this.activeQuests.add(new ArrayList<>());
        for (int i = 0; i < 4; i++)
            this.activeQuests.get(i).add(this.quests.get(0));
    }

    public String getName() {
        return name;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public ArrayList<Ingredients> getFavorites() {
        return favorites;
    }

    public ArrayList<NPCFriendship> getFriendships() {
        return friendships;
    }

    public ArrayList<ArrayList<Quest>> getActiveQuests() {
        return activeQuests;
    }

    public Position getPosition() {
        return position;
    }

    public void GoodNight() {
        Game game = App.getLoggedInUser().getCurrentGame();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            friendships.get(i).setHaveTalked(false);
            friendships.get(i).setHaveGifted(false);
            int level = friendships.get(i).getFriendshipLevel();
            if (level >= 2 && (activeQuests.isEmpty() || activeQuests.get(i).get(activeQuests.size() - 1) == quests.get(1))) {
                int r = random.nextInt(100);
                if (r == 57)
                    this.activeQuests.get(i).add(quests.get(2));
            }
            if (level == 3) {
                int r = random.nextInt(2);
                if (r == 0)
                    continue;
                Player player = game.getCurrentPlayer();
                r = random.nextInt(3);
                Ingredients gift = favorites.get(r);
                Integer amount = player.getInventory().getIngredients().get(gift);
                if (amount == null)
                    amount = 0;
                player.getInventory().getIngredients().put(gift, amount + 1);
            }
        }
    }
}
