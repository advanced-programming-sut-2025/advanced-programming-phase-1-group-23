package com.P.common.model.NPC;

import java.util.*;


import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Maps.Tile;
import com.P.common.model.enums.Ingredients;
import com.P.common.model.Maps.Position;
import com.badlogic.gdx.math.Vector2;

public class NPC {
    private final String name;
    private final ArrayList<Quest> quests;
    private final ArrayList<Ingredients> favorites;
    private final ArrayList<NPCFriendship> friendships;
    private final ArrayList<ArrayList<Quest>> activeQuests;
    private  Position position;
    private final Position homePosition;
    private int movingDirection = 0;
    private Queue<Vector2> movementPath = new LinkedList<>();

    private float timeSinceLastMove = 0f;
    List<Tile> tiles = new ArrayList<>();

    public NPC(String name, List<Pair> information, ArrayList<Ingredients> favorites, Position position , Position homePosition) {
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

        this.homePosition = homePosition;
        this.position = homePosition;
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
        position = homePosition;


    }

    public int getMovingDirection() {
        return movingDirection;
    }

    public void setMovingDirection(int movingDirection) {
        this.movingDirection = movingDirection;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getHomePosition() {
        return homePosition;
    }

    public Queue<Vector2> getMovementPath() {
        return movementPath;
    }

    public void setMovementPath(Queue<Vector2> movementPath) {
        this.movementPath = movementPath;
    }

    public float getTimeSinceLastMove() {
        return timeSinceLastMove;
    }

    public void setTimeSinceLastMove(float timeSinceLastMove) {
        this.timeSinceLastMove = timeSinceLastMove;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }
}
