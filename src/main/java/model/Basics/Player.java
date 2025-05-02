package model.Basics;


import model.Maps.Farm;
import model.Maps.Position;
import model.NPC.NPCs;
import model.Maps.Maps;
import model.Objects.Energy;
import model.Objects.Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {
    private User user;
    private Farm farm;
    private int farmingSkill;
    private int miningSkill;
    private int foragingSkill;
    private int fishingSkill;
    private double energy;
    private ArrayList<Tool> inventory;
    private Map<NPCs, Integer> friendships;
    private Position position;
    private String id;
    private double maximumEnergy;
    private int money;
    private double energyUsed;
    private boolean isFainted;


    public Player(User user, Farm farm, int farmingSkill, int miningSkill, int foragingSkill,
                  int fishingSkill, double energy, ArrayList<Tool> inventory) {
        this.user = user;
        this.farm = farm;
        this.farmingSkill = farmingSkill;
        this.miningSkill = miningSkill;
        this.foragingSkill = foragingSkill;
        this.fishingSkill = fishingSkill;
        this.energy = energy;
        this.inventory = inventory;
    }

    public Player(User user){
        this.user = user;
        this.id = user.getId();
        this.position = new Position(0,0);
        this.money = 0;
        this.energyUsed = 0;
        this.isFainted = false;
    }

    public User getUser() {
        return user;
    }

    public double getEnergy() {
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public void setFarmingSkill(int farmingSkill) {
        this.farmingSkill = farmingSkill;
    }

    public void setMiningSkill(int miningSkill) {
        this.miningSkill = miningSkill;
    }

    public void setForagingSkill(int foragingSkill) {
        this.foragingSkill = foragingSkill;
    }

    public void setFishingSkill(int fishingSkill) {
        this.fishingSkill = fishingSkill;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public ArrayList<Tool> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Tool> inventory) {
        this.inventory = inventory;
    }

    public Map<NPCs, Integer> getFriendships() {
        return friendships;
    }

    public void setFriendships(Map<NPCs, Integer> friendships) {
        this.friendships = friendships;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public double getMaximumEnergy() {
        return maximumEnergy;
    }

    public void setMaximumEnergy(double maximumEnergy) {
        this.maximumEnergy = maximumEnergy;
    }

    public double getEnergyUsed() {
        return energyUsed;
    }

    public void setEnergyUsed(double energyUsed) {
        this.energyUsed = energyUsed;
    }

    public boolean isFainted() {
        return isFainted;
    }

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }
}
