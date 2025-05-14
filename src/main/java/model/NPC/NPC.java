package src.main.java.model.NPC;

import java.util.ArrayList;
import model.enums.Ingredients;

public class NPC {
    private final String name;
    private final ArrayList<Quest> quests;
    private final ArrayList<Ingredients> favorites;

    public NPC(String name, ArrayList<Ingredients> keySet, ArrayList<Integer> values, ArrayList<Ingredients> favorites) {
        this.name = name;
        this.quests = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            Pair requirement = new Pair(keySet.get(i), values.get(i));
            Pair reward = new Pair(keySet.get(i + 3), values.get(i + 3));
            this.quests.add(new Quest(requirement, reward));
        }
        this.favorites = favorites;
    }
}
