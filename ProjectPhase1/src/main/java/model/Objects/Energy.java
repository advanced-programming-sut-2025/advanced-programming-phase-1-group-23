package model.Objects;

import model.Basics.Player;

public class Energy {
    private final int maxEnergy;
    private int amount;
    private boolean isUnlimited;

    public Energy(int maxEnergy, int initialEnergy) {
        this.maxEnergy = maxEnergy;
        this.amount = initialEnergy;
        this.isUnlimited=false;
    }

    public boolean getIsUnlimited(){return isUnlimited;}
    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getAmount() {
        return amount;
    }

    public void updateEnergy(int amount, Player player){
        this.amount-=amount;
        if (this.amount<=0){
            player.setFainted(true);
            this.amount=0;
        }
    }
}
