package com.P.common.model.Basics;

import com.P.Client.controller.WebController;
import com.P.Client.model.Payam;
import com.P.common.model.Maps.Farm;
import com.P.common.model.Maps.Position;
import com.P.common.model.Objects.*;
import com.P.common.model.enums.Ingredients;
import com.P.common.model.enums.Recipe;
import com.P.common.model.enums.ToolLevel;
import com.P.common.model.enums.ToolType;

import com.P.Client.model.Pair;
import com.P.common.model.game.GameModel;
import com.P.common.model.item.ItemDescriptionId;
import com.P.common.model.item.TileDescriptionId;
import com.P.Client.view.GameView.MiniGame;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Transient;

import java.util.*;

import static com.P.Client.controller.WebController.SomeNews;

@Embedded
public class Player {
    @Transient
    private User user;
    private Farm farm;
    private int farmNum;
    private int farmingSkill = 0;
    private int miningSkill;
    private int foragingSkill = 0;
    private int fishingSkill = 150;
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
    private int reaction;
    private long reactionTime;
    private int completedQuests;
    private final ArrayList<Payam> publicChat;
    private final ArrayList<ArrayList<String>> privateChats;

    // Graphic Part
    private Map<ItemDescriptionId, Pair<Integer, Integer>> inventoryForMap;
    private Pair<Float, Float> playerPosition;
    private Stack<Integer> freeIndexes;
    private final Integer maxInventorySize = 9;
    private int selectedSlot = -1;
    private int movingDirection = 0;
    private GameModel gameModel = null;
    private MiniGame miniGame;
    private boolean isFishing = false;


    public Player(User user) {
        System.out.println("creating a player");
        this.user = user;
        this.energy = 200;
        System.out.println("stage 0");
        this.inventory = initializeInventory();
        this.refrigerator = initilizeRef();
        this.id = user.getId();
        this.position = new Position(0, 0);
        this.money = 0;
        this.energyUsed = 0;
        this.isFainted = false;
        this.recipes = initializeRecipes();
        this.currentShop = null;
        System.out.println("stage 1");
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
        this.lastPosition = new Position(3f, 3f);
        this.reactionTime = 1000;//TODO : something better
        this.completedQuests = 0;
        this.publicChat = new ArrayList<>();
        this.privateChats = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            this.privateChats.add(new ArrayList<>());

        System.out.println("stage 2");

        // Graphic edit
        inventoryForMap = new HashMap<>();
        freeIndexes = new Stack<>();
        for (int i = maxInventorySize - 1; i >= 0; i--) {
            freeIndexes.push(i);
        }
        System.out.println("stage 3");
        playerPosition = new Pair<>(3f, 3f);

        addItem(ItemDescriptionId.HOE, 1);
        addItem(ItemDescriptionId.SCYTHE, 1);
        addItem(ItemDescriptionId.WATERING_CAN, 1);
        addItem(ItemDescriptionId.CARROT_SEED, 5);
        System.out.println("stage done");

        Random random = new Random();
        int number = random.nextInt(5) + 1;
        if(number == 1 || number == 4)
            this.miningSkill = 150;
        else if(number == 2)
            this.miningSkill = 300;
        else if (number == 3) {
            this.miningSkill = 450;
        }


    }

    // Graphic edit (we mark end)
    public void addItem(ItemDescriptionId itemId, int count) {
        Pair<Integer, Integer> pair = inventoryForMap.getOrDefault(itemId, new Pair<>(0, freeIndexes.pop()));
        pair.first = pair.first + count;
        inventoryForMap.put(itemId, pair);
    }

    public void useActiveItem(float worldX, float worldY) {
        // Implement item usage logic
    }

    public Pair<Float, Float> getPlayerPosition() {
        return playerPosition;
    }

    private float speed = 2f;
    private float vx = 0, vy = 0;
    private float x = 0, y = 0;

    public void setVelocity(float vx, float vy, float x, float y) {
        this.vx = vx;
        this.vy = vy;
        this.x = x;
        this.y = y;
    }

    public void update(float delta, TileDescriptionId[][] tiles) {
        tryMove(vx * delta, vy * delta, tiles, x * delta, y * delta);
    }

    public boolean tryMove(float dx, float dy, TileDescriptionId[][] tiles, float tx, float ty) {
//        int newX = (int) ((playerPosition.first.intValue()) + x);
        /////  int newY = lastPosition.getY() + y;
//        int newX = (int) (playerPosition.first + dx);
//        int newY = (int) (playerPosition.second + dy);
        //  int newY = (int) ((playerPosition.second.intValue()) + y);
        int newX = (int) (getLastPosition().getFx() + tx);
        int newY = (int) (getLastPosition().getFy() + ty);

        if (newX < 0 || newX >= tiles.length || newY < 0 || newY >= tiles[0].length) return false;

        if (tiles[newX][newY] != TileDescriptionId.WATER) {
            playerPosition.first += dx;
            playerPosition.second += dy;
            getLastPosition().setFx((getLastPosition().getFx() + tx));
            getLastPosition().setFy((getLastPosition().getFy() + ty));
            // System.out.println(newX + " " + newY);
            return true;
        }
        setFishing(true);
        System.out.println("Cannot move: Water tile!");
        return false;
    }


    public Map<ItemDescriptionId, Pair<Integer, Integer>> getInventoryForMap() {
        return inventoryForMap;
    }

    public void setSelectedSlot(int selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public ItemDescriptionId getSelectedItem() {
        return inventoryForMap.entrySet().stream().filter(
            entry -> entry.getValue().second == selectedSlot
        ).map(Map.Entry::getKey).findFirst().orElse(null);
    }

    public int getMovingDirection() {
        return movingDirection;
    }

    public void setMovingDirection(int direction) {
        this.movingDirection = direction;
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public float getSpeed() {
        return speed;
    }

    public int getSelectedSlot() {
        return selectedSlot;
    }

    public void useSelectedItem() {
        ItemDescriptionId selectedItem = getSelectedItem();
        if (selectedItem == ItemDescriptionId.CARROT_SEED) {
            reduceItem();
        }
    }

    private void reduceItem() {
        Pair<Integer, Integer> pair = inventoryForMap.getOrDefault(ItemDescriptionId.CARROT_SEED, null);
        if (pair == null) {
            return;
        }
        pair.first = pair.first - 1;
        if (pair.first == 0) {
            inventoryForMap.remove(ItemDescriptionId.CARROT_SEED);
            freeIndexes.push(pair.second);
        } else {
            inventoryForMap.put(ItemDescriptionId.CARROT_SEED, pair);
        }
    }


    // This is the enddddddddddddddddddddddddddddddddddddddddddddddddddd

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
        SomeNews("farming", farmingSkill);
    }

    public void increaseMiningSkill(int amount) {
        this.miningSkill += amount;
        SomeNews("mining", miningSkill);
    }

    public void increaseForagingSkill(int amount) {
        this.foragingSkill += amount;
        SomeNews("foraging", foragingSkill);
    }

    public void increaseFishingSkill(int amount) {
        this.fishingSkill += amount;
        SomeNews("fishing", fishingSkill);
    }

    public int getCompletedQuests() {
        return completedQuests;
    }

    public void addCompletedQuests() {
        this.completedQuests++;
        SomeNews("quest", completedQuests);
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

    public void updateSkill(String skill, int value) {
        switch (skill) {
            case "farming" -> this.farmingSkill = value;
            case "mining" -> this.miningSkill = value;
            case "foraging" -> this.foragingSkill = value;
            case "fishing" -> this.fishingSkill = value;
            case "quest" -> this.completedQuests = value;
            case "money" -> this.money = value;
        }
        App.getLoggedInUser().getCurrentGame().updateScoreBoard();
        System.out.println(App.getLoggedInUser().getCurrentGame().getScoreboard());
    }

    public void setFarmingSkill(int farmingSkill) {
        this.farmingSkill = farmingSkill;
        SomeNews("farming", this.farmingSkill);
    }

    public void setMiningSkill(int miningSkill) {
        this.miningSkill = miningSkill;
        SomeNews("mining", this.miningSkill);

    }

    public void setForagingSkill(int foragingSkill) {
        this.foragingSkill = foragingSkill;
        SomeNews("foraging", this.foragingSkill);
    }

    public void setFishingSkill(int fishingSkill) {
        this.fishingSkill = fishingSkill;
        SomeNews("fishing", this.fishingSkill);
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
        SomeNews("money", money);
    }

    public int getTrashCan() {
        return trashCan;
    }

    public int getReaction() {
        if((System.currentTimeMillis() - reactionTime) <= 5 * 1000)
            return reaction;
        return -1;
    }

    public void setReaction(int reaction, boolean isFirst) {
        this.reaction = reaction;
        this.reactionTime = System.currentTimeMillis();
        if(isFirst)
            WebController.UpdateReaction(isInVillage, reaction, (int) Math.floor(playerPosition.first), (int) Math.floor(playerPosition.second));
    }

    public long getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(long reactionTime) {
        this.reactionTime = reactionTime;
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

    public ArrayList<Payam> getPublicChat() {
        synchronized (publicChat) {
            return publicChat;
        }
    }

    public ArrayList<ArrayList<String>> getPrivateChats() {
        synchronized (privateChats) {
            return privateChats;
        }
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
        inventory1.getTools().put(new Tool(ToolType.planting, ToolLevel.Initial), 1);
        inventory1.getTools().put(new Tool(ToolType.fertility, ToolLevel.Initial), 1);
        inventory1.getIngredients().put(Ingredients.WOOD, 500);
        //inventory1.getTools().put(new Tool(ToolType.Scissors, ToolLevel.Initial), 1);
      //  inventory1.getTools().put(new Tool(ToolType.FishingRod, ToolLevel.Initial), 1);
        inventory1.getTools().put(new Tool(ToolType.MilkingCan, ToolLevel.Initial), 1);

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

    public int getTotalSkills() {
        return returnFarmingLevel() +
                returnMiningLevel() +
                returnFishingLevel() +
                returnForagingLevel();
    }

    public Farm getCurrentFarm(Game game) {
        return game.getFarmByNumber(currentFarm);
    }

//    public Farm getFarm(){
//        return farm;
//    }

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

    private static Inventory initilizeRef() {
        Inventory ref = new Inventory();
        ref.getIngredients().put(Ingredients.MILK, 4);
        return ref;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setTalkHistory(ArrayList<ArrayList<String>> talkHistory) {
        this.talkHistory = talkHistory;
    }

    public void setInbox(ArrayList<String> inbox) {
        this.inbox = inbox;
    }

    public void setGiftHistory(ArrayList<ArrayList<String>> giftHistory) {
        this.giftHistory = giftHistory;
    }

    public void setReceivedGifts(ArrayList<String> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public void setMarriageRequests(ArrayList<String> marriageRequests) {
        this.marriageRequests = marriageRequests;
    }

    public void setTradeList(ArrayList<Trade> tradeList) {
        this.tradeList = tradeList;
    }

    public void setTradeHistory(ArrayList<Trade> tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

    public void setInventoryForMap(Map<ItemDescriptionId, Pair<Integer, Integer>> inventoryForMap) {
        this.inventoryForMap = inventoryForMap;
    }

    public void setPlayerPosition(Pair<Float, Float> playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Stack<Integer> getFreeIndexes() {
        return freeIndexes;
    }

    public void setFreeIndexes(Stack<Integer> freeIndexes) {
        this.freeIndexes = freeIndexes;
    }

    public MiniGame getMiniGame() {
        return miniGame;
    }

    public void setMiniGame(MiniGame miniGame) {
        this.miniGame = miniGame;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getVx() {
        return vx;
    }

    public boolean isFishing() {
        return isFishing;
    }

    public void setFishing(boolean fishing) {
        isFishing = fishing;
    }

    public int getFarmNum() {
        return farmNum;
    }

    public void setFarmNum(int farmNum) {
        this.farmNum = farmNum;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
