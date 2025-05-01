package model.Basics;


import model.NPC.NPCs;
import model.Maps.Maps;
import model.Objects.Energy;
import model.Objects.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private final model.Basics.User user;
    private final Maps farm;
    private int farmingSkill;
    private int miningSkill;
    private int foragingSkill;
    private int fishingSkill;
    private Energy energy;
    private final HashMap<Tool,Integer> inventory;
    private Map<NPCs, Integer> friendships;
    private boolean isDiedYesterday;
    private boolean isFainted=false;

    public Player(model.Basics.User user, Maps farm, int farmingSkill, int miningSkill, int foragingSkill,
                  int fishingSkill, Energy energy, HashMap<Tool,Integer> inventory, Map<NPCs, Integer> friendships) {
        this.user = user;
        this.farm = farm;
        this.farmingSkill = farmingSkill;
        this.miningSkill = miningSkill;
        this.foragingSkill = foragingSkill;
        this.fishingSkill = fishingSkill;
        this.energy = energy;
        this.inventory = inventory;
        this.friendships = friendships;
    }

    public User getUser() {
        return user;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public int getFarmingSkill() {
        return farmingSkill;
    }

    public int getMiningSkill() {
        return miningSkill;
    }

    public int getForagingSkill() {
        return foragingSkill;
    }

    public int getFishingSkill() {
        return fishingSkill;
    }

    public void increaseFarmingSkill(int amount) {
        this.farmingSkill += amount;
    }

    public void increaseMiningSkill(int amount) {
        this.miningSkill += amount;
    }

    public void increaseForagingSkill(int amount) {
        this.foragingSkill += amount;
    }

    public void increaseFishingSkill(int amount) {
        this.fishingSkill += amount;
    }

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }

    public boolean isFainted() {
        return isFainted;
    }
}
