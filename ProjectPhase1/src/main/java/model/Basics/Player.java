package model.Basics;


import model.NPC.NPCs;
import model.Maps.Maps;
import model.Objects.Energy;
import model.Objects.Inventory;
import model.Objects.Tool;
import model.enums.ToolLevel;
import model.enums.ToolType;

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
    private Inventory inventory=new Inventory();
    private Map<NPCs, Integer> friendships;
    private boolean isDiedYesterday;
    private Tool inHandTool=null;
    private boolean isFainted=false;

    public Player(User user, Maps farm, int farmingSkill, int miningSkill, int foragingSkill,
                  int fishingSkill, Energy energy,  Map<NPCs, Integer> friendships) {
        this.user = user;
        this.farm = farm;
        this.farmingSkill = farmingSkill;
        this.miningSkill = miningSkill;
        this.foragingSkill = foragingSkill;
        this.fishingSkill = fishingSkill;
        this.energy = energy;
        this.friendships = friendships;
        this.inventory=initializeInventory();
    }

    public Tool getInHandTool() {
        return inHandTool;
    }

    private static Inventory initializeInventory(){
        Inventory inventory1=new Inventory();
        inventory1.getTools().add(new Tool(ToolType.Hoe, ToolLevel.Initial));
        inventory1.getTools().add(new Tool(ToolType.Pickaxe, ToolLevel.Initial));
        inventory1.getTools().add(new Tool(ToolType.Axe, ToolLevel.Initial));
        inventory1.getTools().add(new Tool(ToolType.WateringCan, ToolLevel.Initial));
        inventory1.getTools().add(new Tool(ToolType.Scythe, ToolLevel.Initial));
        return inventory1;
    }

    public void setInHandTool(Tool inHandTool) {
        this.inHandTool = inHandTool;
    }

    public User getUser() {
        return user;
    }

    public Inventory getInventory() {
        return inventory;
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

    public int returnFarmingSkill(){
        if (this.farmingSkill<150)return 0;
        else if (this.farmingSkill<250) return 1;
        else if (this.farmingSkill<350) return 2;
        else if (this.farmingSkill<450) return 3;
        else return 4;
    }

    public int returnMiningSkill(){
        if (this.miningSkill<150) return 0;
        else if (this.miningSkill<250) return 1;
        else if (this.miningSkill<350) return 2;
        else if (this.miningSkill<450) return 3;
        else return 4;
    }

    public int returnForagingSkill(){
        if (this.foragingSkill<150) return 0;
        else if (this.foragingSkill<250) return 1;
        else if (this.foragingSkill<350) return 2;
        else if (this.foragingSkill<450) return 3;
        else return 4;
    }

    public int returnFishingSkill(){
        if (this.fishingSkill<150) return 0;
        else if (this.fishingSkill<250) return 1;
        else if (this.fishingSkill<350) return 2;
        else if (this.fishingSkill<450) return 3;
        return 4;
    }


}
