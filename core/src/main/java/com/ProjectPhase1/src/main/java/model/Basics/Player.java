package model.Basics;

//import dev.morphia.annotations.Embedded;
//import dev.morphia.annotations.Transient;

import model.Basics.User;
import model.Maps.Farm;
import model.Maps.Position;
import model.Objects.Inventory;
import model.Objects.Tool;
import model.enums.Ingredients;
import model.enums.Recipe;
import model.enums.ToolLevel;
import model.enums.ToolType;
import model.Objects.Shop;
import model.Objects.ShippingBin;
import model.Objects.Trade;

import java.util.ArrayList;
import java.util.Map;

//@Embedded
public class Player {
    //    @Transient
    private User user;
    private Farm farm;
    private int farmingSkill = 0;
    private int miningSkill = 0;
    private int foragingSkill = 0;
    private int fishingSkill = 0;
    private double energy;
    private Boolean unlimitedEnergy = false;
    private Inventory inventory;
    private Inventory refrigerator;
    private int trashCan = 0;
    private ArrayList<Recipe> recipes;
    private Tool inHandTool = null;
    private Position position;
    private String id;
    private double maximumEnergy;
    private int money;
    private double energyUsed;
    private boolean isFainted;
    private Shop currentShop;
    private int currentFarm;
    private boolean isInVillage;
    private ArrayList<ArrayList<String>> talkHistory;
    private ArrayList<String> inbox;
    private ArrayList<ArrayList<String>> giftHistory;
    private ArrayList<String> receivedGifts;
    private ArrayList<String> marriageRequests;
    private ArrayList<Trade> tradeList;
    private ArrayList<Trade> tradeHistory;
    private double usedEnergyInTurn;
    private Position lastPosition;


    public Player(User user) {
        this.user = user;
        this.energy = 200;
       this.inventory = initializeInventory();
        this.refrigerator = initilizeRef();
        this.id = user.getId();
        this.position = new Position(0, 0);
        this.money = 0;
        this.energyUsed = 0;
        this.isFainted = false;
        this.recipes = initializeRecipes();
        this.currentShop = null;
        this.talkHistory = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            this.talkHistory.add(new ArrayList<>());
        this.inbox = new ArrayList<>();
        this.giftHistory = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            this.giftHistory.add(new ArrayList<>());
        this.receivedGifts = new ArrayList<>();
        this.marriageRequests = new ArrayList<>();
        this.tradeList = new ArrayList<>();
        this.tradeHistory = new ArrayList<>();
        this.lastPosition = new Position(0, 0);
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Position lastPosition) {
        this.lastPosition = lastPosition;
    }


    public User getUser() {
        return user;
    }

    public double getEnergy() {
        if (!unlimitedEnergy) return Math.min(energy, 200);
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

    public Shop getCurrentShop() {
        return currentShop;
    }

    public void setCurrentShop(Shop currentShop) {
        this.currentShop = currentShop;
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

    public int getTrashCan() {
        return trashCan;
    }

    //    private ArrayList<Recipe> initializeRecipes() {
//        this.recipes.add(Recipe.FriedEgg);
//        this.recipes.add(Recipe.BakedFish);
//        this.recipes.add(Recipe.Salad);
//
//        return this.recipes;
//    }
    private ArrayList<Recipe> initializeRecipes() {
        ArrayList<Recipe> list = new ArrayList<>();
        list.add(Recipe.FriedEgg);
        list.add(Recipe.BakedFish);
        list.add(Recipe.Salad);
        return list;
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

    public ArrayList<ArrayList<String>> getTalkHistory() {
        return talkHistory;
    }

    public ArrayList<String> getInbox() {
        return inbox;
    }

    public ArrayList<ArrayList<String>> getGiftHistory() {
        return giftHistory;
    }

    public ArrayList<String> getReceivedGifts() {
        return receivedGifts;
    }

    public ArrayList<String> getMarriageRequests() {
        return marriageRequests;
    }

    public ArrayList<Trade> getTradeList() {
        return tradeList;
    }

    public ArrayList<Trade> getTradeHistory() {
        return tradeHistory;
    }

    public void upgradeTrashCan() {
        if (this.trashCan == 0) this.trashCan = 15;
        else if (this.trashCan == 15) this.trashCan = 30;
        else if (this.trashCan == 30) this.trashCan = 45;
        else if (this.trashCan == 45) this.trashCan = 60;
    }

    public Tool getInHandTool() {
        return inHandTool;
    }

    private static Inventory initializeInventory() {
        Inventory inventory1 = new Inventory();
        inventory1.getTools().put(new Tool(ToolType.Hoe, ToolLevel.Initial), 1);
        inventory1.getTools().put(new Tool(ToolType.Pickaxe, ToolLevel.Initial), 1);
        inventory1.getTools().put(new Tool(ToolType.Axe, ToolLevel.Initial), 1);
        inventory1.getTools().put(new Tool(ToolType.WateringCan, ToolLevel.Initial), 1);
        inventory1.getTools().put(new Tool(ToolType.Scythe, ToolLevel.Initial), 1);
        return inventory1;
    }

    public void setInHandTool(Tool inHandTool) {
        this.inHandTool = inHandTool;
    }

    public int returnFarmingLevel() {
        if (this.farmingSkill < 150) return 0;
        else if (this.farmingSkill < 250) return 1;
        else if (this.farmingSkill < 350) return 2;
        else if (this.farmingSkill < 450) return 3;
        else return 4;
    }

    public int returnMiningLevel() {
        if (this.miningSkill < 150) return 0;
        else if (this.miningSkill < 250) return 1;
        else if (this.miningSkill < 350) return 2;
        else if (this.miningSkill < 450) return 3;
        else return 4;
    }

    public int returnForagingLevel() {
        if (this.foragingSkill < 150) return 0;
        else if (this.foragingSkill < 250) return 1;
        else if (this.foragingSkill < 350) return 2;
        else if (this.foragingSkill < 450) return 3;
        else return 4;
    }

    public int returnFishingLevel() {
        if (this.fishingSkill < 150) return 0;
        else if (this.fishingSkill < 250) return 1;
        else if (this.fishingSkill < 350) return 2;
        else if (this.fishingSkill < 450) return 3;
        return 4;
    }

    public Farm getCurrentFarm(Game game) {
        return game.getFarmByNumber(currentFarm);
    }

    public int getCurrentFarm() {
        return currentFarm;
    }

    public void setCurrentFarm(int currentFarm) {
        this.currentFarm = currentFarm;
    }

    public void GoodNight() {
        for (ShippingBin shippingBin : this.getFarm().getShippingBins()) {
            this.setMoney(money + shippingBin.getTotalMoney());
            shippingBin.setTotalMoney(0);
        }
    }

    public Boolean getUnlimitedEnergy() {
        return unlimitedEnergy;
    }

    public void setUnlimitedEnergy(Boolean unlimitedEnergy) {
        this.unlimitedEnergy = unlimitedEnergy;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setRefrigerator(Inventory refrigerator) {
        this.refrigerator = refrigerator;
    }

    public void setTrashCan(int trashCan) {
        this.trashCan = trashCan;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public boolean isInVillage() {
        return isInVillage;
    }

    public void setInVillage(boolean inVillage) {
        isInVillage = inVillage;
    }

    public double getUsedEnergyInTurn() {
        return usedEnergyInTurn;
    }

    public void setUsedEnergyInTurn(double usedEnergyInTurn) {
        this.usedEnergyInTurn = usedEnergyInTurn;
    }

    private static Inventory initilizeRef(){
        Inventory ref=new Inventory();
        ref.getIngredients().put(Ingredients.MILK, 4);
        return ref;
    }
}
