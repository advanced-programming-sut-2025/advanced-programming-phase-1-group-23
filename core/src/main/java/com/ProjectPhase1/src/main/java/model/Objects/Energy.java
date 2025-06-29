package model.Objects;

import dev.morphia.annotations.Embedded;

@Embedded
public class Energy {
    private int maxEnergy;
    private int minEnergy;
    private int initialEnergy;

    public Energy(int maxEnergy, int minEnergy, int initialEnergy) {
        this.maxEnergy = maxEnergy;
        this.minEnergy = minEnergy;
        this.initialEnergy = initialEnergy;
    }

    public Energy() {
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getMinEnergy() {
        return minEnergy;
    }

    public int getInitialEnergy() {
        return initialEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public void setMinEnergy(int minEnergy) {
        this.minEnergy = minEnergy;
    }

    public void changeInitialEnergy(int amount) {
        this.initialEnergy += amount;
    }

    public void resetEnergy() {
        this.initialEnergy = maxEnergy;
    }
}
