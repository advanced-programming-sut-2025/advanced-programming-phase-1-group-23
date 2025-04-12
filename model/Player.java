package model;

public class Player {
    private final User user;
    private int farmingSkill;
    private int miningSkill;
    private int foragingSkill;
    private int fishingSkill;
    private Energy energy;

    //add constructor

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
