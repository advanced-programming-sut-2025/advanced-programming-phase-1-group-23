package model.Basics;


import model.NPC.NPCs;
import model.Maps.Maps;
import model.Objects.Energy;
import model.Objects.Inventory;
import model.Objects.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private final model.Basics.User user;
    private final Maps farm;
    private int farmingSkill=0;
    private int miningSkill=0;
    private int foragingSkill=0;
    private int fishingSkill=0;
    private Energy energy;
    private Inventory inventory;
    private Map<NPCs, Integer> friendships;
    private boolean isFainted=false;

    public Player(User user, Maps farm, int farmingSkill, int miningSkill, int foragingSkill,
                  int fishingSkill, Energy energy, Map<NPCs, Integer> friendships) {
        this.user = user;
        this.farm = farm;
        this.farmingSkill = farmingSkill;
        this.miningSkill = miningSkill;
        this.foragingSkill = foragingSkill;
        this.fishingSkill = fishingSkill;
        this.energy = energy;
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

    public int returnFishingLevel(){
        if (fishingSkill<150)return 0;
        else if (fishingSkill<250 )return 1;
        else if (fishingSkill<350)return 2;
        else if (fishingSkill<450)return 3;
        else return 4;
    }

    public int returnFarmingLevel(){
        if (farmingSkill<150)return 0;
        else if (farmingSkill<250)return 1;
        else if (farmingSkill<350) return 2;
        else if (farmingSkill<450) return 3;
        else return 4;
    }

    public int returnMiningLevel(){
        if (miningSkill<150)return 0;
        else if (miningSkill<250) return 1;
        else if (miningSkill<350) return 2;
        else if (miningSkill<450) return 3;
        else return 4;
    }

    public int returnForagingLevel(){
        if (foragingSkill<150) return 0;
        else if (foragingSkill<250) return 1;
        else if (foragingSkill<350) return 2;
        else if (foragingSkill<450) return 3;
        else return 4;
    }
}
