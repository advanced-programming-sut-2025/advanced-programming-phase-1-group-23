package model.Basics;


import model.Maps.Farm;
import model.Maps.Position;
import model.NPC.NPCs;
import model.Maps.Maps;
import model.Objects.Energy;
import model.Objects.Inventory;
import model.Objects.Tool;
import model.enums.Recipe;
import model.enums.ToolLevel;
import model.enums.ToolType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {
    private User user;
    private Farm farm;
    private int farmingSkill=0;
    private int miningSkill=0;
    private int foragingSkill=0;
    private int fishingSkill=0;
    private double energy;
    private Inventory inventory;
    private Inventory refrigerator;
    private int trashCan=0;
    private ArrayList<Recipe> recipes=new ArrayList<>();
    private boolean isDiedYesterday;
    private Tool inHandTool=null;
    private Map<NPCs, Integer> friendships;
    private Position position;
    private String id;
    private double maximumEnergy;
    private int money;
    private double energyUsed;
    private boolean isFainted;


    public Player(User user) {
        this.user = user;
        this.energy = 200;
        this.inventory=initializeInventory();
        this.refrigerator=new Inventory();
        this.id = user.getId();
        this.position = new Position(0,0);
        this.money = 0;
        this.energyUsed = 0;
        this.isFainted = false;
        this.recipes=initializeRecipes;
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

    public Inventory getInventory() {
        return inventory;
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

    public ArrayList<Recipe> getRecipes() {
        return recipes;
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

    private static ArrayList<Recipe> initializeRecipes{
        this.recipes.add(Recipe.FriedEgg);
        this.recipes.add(Recipe.BakedFish);
        this.recipes.add(Recipe.Salad);
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

    public Inventory getRefrigerator() {
        return refrigerator;
    }

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }

    public void upgradeTrashCan(){
        if (this.trashCan==0)this.trashCan=15;
        else if (this.trashCan==15)this.trashCan=30;
        else if (this.trashCan==30)this.trashCan=45;
        else if (this.trashCan==45)this.trashCan=60;
    }

    public Tool getInHandTool() {
        return inHandTool;
    }

    private static Inventory initializeInventory(){
        Inventory inventory1=new Inventory();
        inventory1.getTools().put(new Tool(ToolType.Hoe, ToolLevel.Initial),1);
        inventory1.getTools().put(new Tool(ToolType.Pickaxe, ToolLevel.Initial),1);
        inventory1.getTools().put(new Tool(ToolType.Axe, ToolLevel.Initial),1);
        inventory1.getTools().put(new Tool(ToolType.WateringCan, ToolLevel.Initial),1);
        inventory1.getTools().put(new Tool(ToolType.Scythe, ToolLevel.Initial),1);
        return inventory1;
    }

    public void setInHandTool(Tool inHandTool) {
        this.inHandTool = inHandTool;}

    public int returnFarmingLevel(){
        if (this.farmingSkill<150)return 0;
        else if (this.farmingSkill<250) return 1;
        else if (this.farmingSkill<350) return 2;
        else if (this.farmingSkill<450) return 3;
        else return 4;}

    public int returnMiningLevel(){
        if (this.miningSkill<150) return 0;
        else if (this.miningSkill<250) return 1;
        else if (this.miningSkill<350) return 2;
        else if (this.miningSkill<450) return 3;
        else return 4;}
    public int returnForagingLevel(){
        if (this.foragingSkill<150) return 0;
        else if (this.foragingSkill<250) return 1;
        else if (this.foragingSkill<350) return 2;
        else if (this.foragingSkill<450) return 3;
        else return 4;}

    public int returnFishingLevel(){
        if (this.fishingSkill<150) return 0;
        else if (this.fishingSkill<250) return 1;
        else if (this.fishingSkill<350) return 2;
        else if (this.fishingSkill<450) return 3;
        return 4;}
}
