package model.Basics;

import model.Maps.Map;
import model.Objects.Energy;
import model.Objects.Tool;

import java.util.ArrayList;

public class Player {
    private final User user;
    private final Map farm;
    private int farmingSkill;
    private int miningSkill;
    private int foragingSkill;
    private int fishingSkill;
    private Energy energy;
    private final ArrayList<Tool> inventory;

    public Player(User user, Map farm, int farmingSkill, int miningSkill, int foragingSkill,
                  int fishingSkill, Energy energy, ArrayList<Tool> inventory) {
        this.user = user;
        this.farm = farm;
        this.farmingSkill = farmingSkill;
        this.miningSkill = miningSkill;
        this.foragingSkill = foragingSkill;
        this.fishingSkill = fishingSkill;
        this.energy = energy;
        this.inventory = inventory;
    }

    public User getUser() {
        return user;
    }

    public Energy getEnergy() {
        return energy;
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
}
